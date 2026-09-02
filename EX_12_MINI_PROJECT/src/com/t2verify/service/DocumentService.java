package com.t2verify.service;

import com.t2verify.dao.DocumentDAO;
import com.t2verify.model.Document;
import com.t2verify.model.User;
import com.t2verify.util.HashUtil;
import com.t2verify.util.ValidationUtil;

import java.io.File;
import java.util.List;

public class DocumentService {

    private final DocumentDAO documentDAO = new DocumentDAO();

    public Document registerDocument(File file, User user) throws Exception {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("Selected file does not exist.");
        }
        if (!ValidationUtil.isSupportedFile(file)) {
            throw new IllegalArgumentException("Unsupported file type. Supported formats: PDF, DOCX, TXT, PNG, JPG, JPEG.");
        }
        if (!ValidationUtil.isWithinSizeLimit(file)) {
            throw new IllegalArgumentException("File size exceeds maximum allowed limit (50 MB).");
        }
        if (user == null) {
            throw new IllegalArgumentException("User session expired. Please log in again.");
        }

        // Generate SHA-256 hash via chunked stream
        String hash = HashUtil.generateSHA256(file);

        // Check duplicate hash
        Document existingDoc = documentDAO.findByHash(hash);
        if (existingDoc != null) {
            throw new DuplicateDocumentException("This exact document is already registered in the system (Document ID #" 
                + existingDoc.getId() + ", Registered on " + existingDoc.getRegisteredAt() + ").");
        }

        // Prepare new Document record
        String fileName = file.getName();
        String fileType = ValidationUtil.getFileExtension(fileName).toUpperCase();
        long fileSize = file.length();

        Document doc = new Document(0, user.getId(), fileName, fileType, fileSize, hash, "REGISTERED", null);
        boolean success = documentDAO.registerDocument(doc);
        if (!success) {
            throw new RuntimeException("Failed to save document metadata in database.");
        }
        return doc;
    }

    public List<Document> getDocumentsForCurrentUser(User user) throws Exception {
        if (user == null) return List.of();
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return documentDAO.getAllDocuments();
        } else {
            return documentDAO.getDocumentsByUser(user.getId());
        }
    }

    public static class DuplicateDocumentException extends Exception {
        public DuplicateDocumentException(String message) {
            super(message);
        }
    }
}
