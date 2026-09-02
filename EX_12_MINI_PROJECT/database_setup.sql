-- T2Verify Database Setup Script
CREATE DATABASE IF NOT EXISTS T2Verify_db;
USE T2Verify_db;

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Documents Table
CREATE TABLE IF NOT EXISTS documents (
    document_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(50),
    file_size BIGINT,
    document_hash CHAR(64) NOT NULL UNIQUE,
    status VARCHAR(30) DEFAULT 'REGISTERED',
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);

-- 3. Verification History Table
CREATE TABLE IF NOT EXISTS verification_history (
    verification_id INT PRIMARY KEY AUTO_INCREMENT,
    verifier_id INT NULL,
    submitted_file_name VARCHAR(255) NOT NULL,
    submitted_hash CHAR(64) NOT NULL,
    matched_document_id INT NULL,
    result VARCHAR(30) NOT NULL,
    verified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (verifier_id)
        REFERENCES users(user_id)
        ON DELETE SET NULL,

    FOREIGN KEY (matched_document_id)
        REFERENCES documents(document_id)
        ON DELETE SET NULL
);

-- Insert Default Seed Data (Admin & Test User)
-- Passwords are SHA-256 hashed strings:
-- admin / admin123 -> 240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9
-- thamizhselvan / Thamizh1. -> afac270993b2323f9272b54e5b3d8074c5175259bfb7c9eba7ba8951f4bd9867
-- thamizhselvan / user123 -> e606e38b0d8c19b24cf0ee3808183162ea7cd63ff7912dbb22b5e803286b4446
INSERT IGNORE INTO users (user_id, full_name, email, username, password_hash, role) VALUES 
(1, 'System Administrator', 'admin@T2Verify.com', 'admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'ADMIN'),
(2, 'Thamizhselvan', 'thamizhselvan@example.com', 'thamizhselvan', 'afac270993b2323f9272b54e5b3d8074c5175259bfb7c9eba7ba8951f4bd9867', 'USER');
