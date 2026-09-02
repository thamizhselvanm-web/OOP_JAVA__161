package com.t2verify.dao;

import com.t2verify.database.DBConnection;
import com.t2verify.model.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {

    public boolean registerDocument(Document doc) throws SQLException {
        String sql = "INSERT INTO documents (user_id, file_name, file_type, file_size, document_hash, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, doc.getUserId());
            pstmt.setString(2, doc.getFileName());
            pstmt.setString(3, doc.getFileType());
            pstmt.setLong(4, doc.getFileSize());
            pstmt.setString(5, doc.getDocumentHash());
            pstmt.setString(6, doc.getStatus() != null ? doc.getStatus() : "REGISTERED");

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        doc.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    public Document findByHash(String hash) throws SQLException {
        String sql = "SELECT d.*, u.full_name as owner_name FROM documents d " +
                     "LEFT JOIN users u ON d.user_id = u.user_id WHERE d.document_hash = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, hash);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Document doc = mapDocument(rs);
                    doc.setOwnerName(rs.getString("owner_name"));
                    return doc;
                }
            }
        }
        return null;
    }

    public List<Document> getDocumentsByUser(int userId) throws SQLException {
        List<Document> list = new ArrayList<>();
        String sql = "SELECT d.*, u.full_name as owner_name FROM documents d " +
                     "LEFT JOIN users u ON d.user_id = u.user_id WHERE d.user_id = ? ORDER BY d.registered_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Document doc = mapDocument(rs);
                    doc.setOwnerName(rs.getString("owner_name"));
                    list.add(doc);
                }
            }
        }
        return list;
    }

    public List<Document> getAllDocuments() throws SQLException {
        List<Document> list = new ArrayList<>();
        String sql = "SELECT d.*, u.full_name as owner_name FROM documents d " +
                     "LEFT JOIN users u ON d.user_id = u.user_id ORDER BY d.registered_at DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Document doc = mapDocument(rs);
                doc.setOwnerName(rs.getString("owner_name"));
                list.add(doc);
            }
        }
        return list;
    }

    public int countTotalDocuments() throws SQLException {
        String sql = "SELECT COUNT(*) FROM documents";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private Document mapDocument(ResultSet rs) throws SQLException {
        return new Document(
            rs.getInt("document_id"),
            rs.getInt("user_id"),
            rs.getString("file_name"),
            rs.getString("file_type"),
            rs.getLong("file_size"),
            rs.getString("document_hash"),
            rs.getString("status"),
            rs.getTimestamp("registered_at")
        );
    }
}
