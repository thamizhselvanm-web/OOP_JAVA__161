package com.t2verify.util;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    
    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(
        Arrays.asList("pdf", "docx", "txt", "png", "jpg", "jpeg")
    );

    private static final long MAX_FILE_SIZE_BYTES = 50 * 1024 * 1024; // 50 MB limit

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        String trimmed = username.trim();
        return trimmed.length() >= 3 && trimmed.length() <= 50 && trimmed.matches("^[a-zA-Z0-9_]+$");
    }

    public static String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }

    public static boolean isSupportedFile(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }
        String ext = getFileExtension(file.getName());
        return ALLOWED_EXTENSIONS.contains(ext);
    }

    public static boolean isWithinSizeLimit(File file) {
        return file != null && file.length() <= MAX_FILE_SIZE_BYTES;
    }
}
