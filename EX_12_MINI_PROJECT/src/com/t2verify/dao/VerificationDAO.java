package com.t2verify.dao;

import com.t2verify.database.DBConnection;
import com.t2verify.model.Verification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VerificationDAO {

    public boolean saveVerification(Verification v) throws SQLException {
        String sql = "INSERT INTO verification_history (verifier_id, submitted_file_name, submitted_hash, matched_document_id, result) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (v.getVerifierId() != null) {
                pstmt.setInt(1, v.getVerifierId());
            } else {
                pstmt.setNull(1, Types.INTEGER);
            }

            pstmt.setString(2, v.getSubmittedFileName());
            pstmt.setString(3, v.getSubmittedHash());

            if (v.getMatchedDocumentId() != null) {
                pstmt.setInt(4, v.getMatchedDocumentId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            pstmt.setString(5, v.getResult());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        v.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    public List<Verification> getHistoryByUser(int userId) throws SQLException {
        List<Verification> list = new ArrayList<>();
        String sql = "SELECT v.*, u.full_name as verifier_name FROM verification_history v " +
                     "LEFT JOIN users u ON v.verifier_id = u.user_id WHERE v.verifier_id = ? ORDER BY v.verified_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Verification v = mapVerification(rs);
                    v.setVerifierName(rs.getString("verifier_name"));
                    list.add(v);
                }
            }
        }
        return list;
    }

    public List<Verification> getAllHistory() throws SQLException {
        List<Verification> list = new ArrayList<>();
        String sql = "SELECT v.*, u.full_name as verifier_name FROM verification_history v " +
                     "LEFT JOIN users u ON v.verifier_id = u.user_id ORDER BY v.verified_at DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Verification v = mapVerification(rs);
                v.setVerifierName(rs.getString("verifier_name"));
                list.add(v);
            }
        }
        return list;
    }

    public List<Verification> getRecentActivity(int limit) throws SQLException {
        List<Verification> list = new ArrayList<>();
        String sql = "SELECT v.*, u.full_name as verifier_name FROM verification_history v " +
                     "LEFT JOIN users u ON v.verifier_id = u.user_id ORDER BY v.verified_at DESC LIMIT ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limit);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Verification v = mapVerification(rs);
                    v.setVerifierName(rs.getString("verifier_name"));
                    list.add(v);
                }
            }
        }
        return list;
    }

    public int countTotalVerifications() throws SQLException {
        String sql = "SELECT COUNT(*) FROM verification_history";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public int countVerificationsByResult(String result) throws SQLException {
        String sql = "SELECT COUNT(*) FROM verification_history WHERE result = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, result);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    private Verification mapVerification(ResultSet rs) throws SQLException {
        int verifierId = rs.getInt("verifier_id");
        Integer verifierIdObj = rs.wasNull() ? null : verifierId;

        int matchedDocId = rs.getInt("matched_document_id");
        Integer matchedDocIdObj = rs.wasNull() ? null : matchedDocId;

        return new Verification(
            rs.getInt("verification_id"),
            verifierIdObj,
            rs.getString("submitted_file_name"),
            rs.getString("submitted_hash"),
            matchedDocIdObj,
            rs.getString("result"),
            rs.getTimestamp("verified_at")
        );
    }
}
