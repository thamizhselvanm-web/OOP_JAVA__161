package com.t2verify.model;

import java.sql.Timestamp;

public class Verification {

    private int id;
    private Integer verifierId; // Nullable if guest/anonymous
    private String submittedFileName;
    private String submittedHash;
    private Integer matchedDocumentId; // Nullable if NOT REGISTERED
    private String result; // 'VERIFIED' or 'NOT REGISTERED'
    private Timestamp verifiedAt;
    private String verifierName; // Helper for display

    public Verification() {}

    public Verification(int id, Integer verifierId, String submittedFileName, String submittedHash, Integer matchedDocumentId, String result, Timestamp verifiedAt) {
        this.id = id;
        this.verifierId = verifierId;
        this.submittedFileName = submittedFileName;
        this.submittedHash = submittedHash;
        this.matchedDocumentId = matchedDocumentId;
        this.result = result;
        this.verifiedAt = verifiedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getVerifierId() {
        return verifierId;
    }

    public void setVerifierId(Integer verifierId) {
        this.verifierId = verifierId;
    }

    public String getSubmittedFileName() {
        return submittedFileName;
    }

    public void setSubmittedFileName(String submittedFileName) {
        this.submittedFileName = submittedFileName;
    }

    public String getSubmittedHash() {
        return submittedHash;
    }

    public void setSubmittedHash(String submittedHash) {
        this.submittedHash = submittedHash;
    }

    public Integer getMatchedDocumentId() {
        return matchedDocumentId;
    }

    public void setMatchedDocumentId(Integer matchedDocumentId) {
        this.matchedDocumentId = matchedDocumentId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Timestamp getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Timestamp verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public String getVerifierName() {
        return verifierName;
    }

    public void setVerifierName(String verifierName) {
        this.verifierName = verifierName;
    }
}
