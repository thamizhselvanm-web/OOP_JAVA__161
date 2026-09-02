import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;

public class StudentManagementApp extends Application {

    private Connection connection;

    private TextField idField = new TextField();
    private TextField nameField = new TextField();
    private TextField ageField = new TextField();
    private TextField courseField = new TextField();
    private TextArea displayArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        connectToDatabase();

        // Set up the layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Set up font
        Font font = new Font("Arial", 14);

        idField.setPromptText("ID (for Update/Delete)");
        idField.setFont(font);
        nameField.setPromptText("Name");
        nameField.setFont(font);
        ageField.setPromptText("Age");
        ageField.setFont(font);
        courseField.setPromptText("Course");
        courseField.setFont(font);

        Button createButton = new Button("Create");
        createButton.setFont(font);
        createButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        createButton.setOnAction(e -> createStudent());

        Button readButton = new Button("Display");
        readButton.setFont(font);
        readButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        readButton.setOnAction(e -> readStudents());

        Button updateButton = new Button("Update");
        updateButton.setFont(font);
        updateButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        updateButton.setOnAction(e -> updateStudent());

        Button deleteButton = new Button("Delete");
        deleteButton.setFont(font);
        deleteButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> deleteStudent());

        displayArea.setFont(font);
        displayArea.setEditable(false);
        displayArea.setWrapText(true);

        // Arrange elements in the grid
        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(new Label("Age:"), 0, 2);
        gridPane.add(ageField, 1, 2);
        gridPane.add(new Label("Course:"), 0, 3);
        gridPane.add(courseField, 1, 3);

        // Arrange buttons in two rows with gaps
        gridPane.add(createButton, 0, 4);
        gridPane.add(readButton, 1, 4);
        gridPane.add(updateButton, 0, 5);
        gridPane.add(deleteButton, 1, 5);

        GridPane.setMargin(createButton, new Insets(5, 5, 5, 5));
        GridPane.setMargin(readButton, new Insets(5, 5, 5, 5));
        GridPane.setMargin(updateButton, new Insets(5, 5, 5, 5));
        GridPane.setMargin(deleteButton, new Insets(5, 5, 5, 5));

        // Add display area
        gridPane.add(displayArea, 0, 6, 2, 1);

        // Set up the scene
        Scene scene = new Scene(gridPane, 400, 500);
        primaryStage.setTitle("Student Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void connectToDatabase() {
        String[] candidatePasswords = new String[]{
            System.getenv("DB_PASSWORD"),
            System.getProperty("db.password"),
            "Thamizh1.",
            "Thamizh1",
            "1234",
            "",
            "root",
            "admin",
            "password",
            "123456"
        };

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            displayArea.setText("Error: MySQL JDBC Driver not found in classpath.");
            return;
        }

        for (String pass : candidatePasswords) {
            if (pass == null) continue;
            try {
                // Connect to MySQL server and ensure database and table exist
                try (Connection initConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?allowPublicKeyRetrieval=true&useSSL=false", "root", pass);
                     Statement stmt = initConn.createStatement()) {
                    stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS studentdb");
                    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS studentdb.students (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(100) NOT NULL, " +
                            "age INT NOT NULL, " +
                            "course VARCHAR(100) NOT NULL)");
                }

                // Connect to studentdb
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?allowPublicKeyRetrieval=true&useSSL=false", "root", pass);
                displayArea.setText("Connected to database 'studentdb' successfully!");
                return;
            } catch (SQLException ignored) {
                // Try next password fallback
            }
        }

        displayArea.setText("Database Connection Error:\n" +
                "Could not connect to MySQL server at localhost:3306.\n" +
                "1. Ensure MySQL Server is running.\n" +
                "2. If your MySQL password is not default ('1234', '', 'root'), run with:\n" +
                "   java -Ddb.password=YOUR_PASSWORD ...");
    }

    private boolean isConnectionValid() {
        if (connection == null) {
            connectToDatabase();
        }
        return connection != null;
    }

    private void createStudent() {
        if (!isConnectionValid()) return;
        try {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String course = courseField.getText().trim();

            if (name.isEmpty() || ageText.isEmpty() || course.isEmpty()) {
                displayArea.setText("Error: Please fill in Name, Age, and Course.");
                return;
            }

            int age = Integer.parseInt(ageText);
            String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, course);
                pstmt.executeUpdate();
                displayArea.setText("Student created successfully.");
            } catch (SQLException e) {
                displayArea.setText("Database Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Error: Age must be a valid number.");
        }
    }

    private void readStudents() {
        if (!isConnectionValid()) return;
        String sql = "SELECT * FROM students";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(", Name: ").append(rs.getString("name"))
                        .append(", Age: ").append(rs.getInt("age"))
                        .append(", Course: ").append(rs.getString("course"))
                        .append("\n");
            }
            if (sb.length() == 0) {
                displayArea.setText("No students found in database.");
            } else {
                displayArea.setText(sb.toString());
            }
        } catch (SQLException e) {
            displayArea.setText("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateStudent() {
        if (!isConnectionValid()) return;
        try {
            String idText = idField.getText().trim();
            if (idText.isEmpty()) {
                displayArea.setText("Error: Please enter ID to update.");
                return;
            }
            int id = Integer.parseInt(idText);
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String course = courseField.getText().trim();

            if (name.isEmpty() || ageText.isEmpty() || course.isEmpty()) {
                displayArea.setText("Error: Please fill in Name, Age, and Course for update.");
                return;
            }

            int age = Integer.parseInt(ageText);
            String sql = "UPDATE students SET name = ?, age = ?, course = ? WHERE id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, course);
                pstmt.setInt(4, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    displayArea.setText("Student updated successfully.");
                } else {
                    displayArea.setText("No student found with ID: " + id);
                }
            } catch (SQLException e) {
                displayArea.setText("Database Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Error: ID and Age must be valid numbers.");
        }
    }

    private void deleteStudent() {
        if (!isConnectionValid()) return;
        try {
            String idText = idField.getText().trim();
            if (idText.isEmpty()) {
                displayArea.setText("Error: Please enter ID to delete.");
                return;
            }
            int id = Integer.parseInt(idText);
            String sql = "DELETE FROM students WHERE id = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    displayArea.setText("Student deleted successfully.");
                } else {
                    displayArea.setText("No student found with ID: " + id);
                }
            } catch (SQLException e) {
                displayArea.setText("Database Error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Error: ID must be a valid number.");
        }
    }

    @Override
    public void stop() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        super.stop();
    }
}
