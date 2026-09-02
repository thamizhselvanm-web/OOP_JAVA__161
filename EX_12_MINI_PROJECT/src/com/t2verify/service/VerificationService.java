package com.t2verify.service;

import com.t2verify.dao.DocumentDAO;
import com.t2verify.dao.VerificationDAO;
import com.t2verify.model.Document;
import com.t2verify.model.User;
import com.t2verify.model.Verification;
import com.t2verify.util.HashUtil;
import com.t2verify.util.ValidationUtil;

import java.io.File;
import java.util.List;

public class VerificationService {

    private final DocumentDAO documentDAO = new DocumentDAO();
    private final VerificationDAO verificationDAO = new VerificationDAO();

    public static class VerificationResult {
        private final String hash;
        private final String resultStatus; // 'VERIFIED' or 'NOT REGISTERED'
        private final Document matchedDocument;
        private final Verification historyRecord;

        public VerificationResult(String hash, String resultStatus, Document matchedDocument, Verification historyRecord) {
            this.hash = hash;
            this.resultStatus = resultStatus;
            this.matchedDocument = matchedDocument;
            this.historyRecord = historyRecord;
        }

        public String getHash() {
            return hash;
        }

        public String getResultStatus() {
            return resultStatus;
        }

        public Document getMatchedDocument() {
            return matchedDocument;
        }

        public Verification getHistoryRecord() {
            return historyRecord;
        }

        public boolean isVerified() {
            return "VERIFIED".equalsIgnoreCase(resultStatus);
        }
    }

    public VerificationResult verifyDocument(File file, User verifier) throws Exception {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("Selected verification file does not exist.");
        }
        if (!ValidationUtil.isWithinSizeLimit(file)) {
            throw new IllegalArgumentException("File size exceeds maximum allowed limit (50 MB).");
        }

        // Generate hash of submitted document
        String calculatedHash = HashUtil.generateSHA256(file);

        // Search database for matching registered hash
        Document matchedDoc = documentDAO.findByHash(calculatedHash);

        String status = (matchedDoc != null) ? "VERIFIED" : "NOT REGISTERED";
        Integer matchedDocId = (matchedDoc != null) ? matchedDoc.getId() : null;
        Integer verifierId = (verifier != null) ? verifier.getId() : null;

        // Record verification in history audit trail
        Verification record = new Verification(0, verifierId, file.getName(), calculatedHash, matchedDocId, status, null);
        verificationDAO.saveVerification(record);

        return new VerificationResult(calculatedHash, status, matchedDoc, record);
    }

    public List<Verification> getHistoryForUser(User user) throws Exception {
        if (user == null) return List.of();
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return verificationDAO.getAllHistory();
        } else {
            return verificationDAO.getHistoryByUser(user.getId());
        }
    }
}
