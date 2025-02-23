package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class StudentController {

    @FXML
    private TextField studentIdField, nameField, programField, yearField, contactField;
    
    @FXML
    private DatePicker dobField;

    private Connection connection;

    // Initialize Database Connection
    public StudentController() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_registration", "root", "yourpassword");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addStudent() {
        try {
            String query = "INSERT INTO students (student_id, name, dob, program, year, contact) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, studentIdField.getText());
            stmt.setString(2, nameField.getText());
            stmt.setDate(3, Date.valueOf(dobField.getValue()));
            stmt.setString(4, programField.getText());
            stmt.setString(5, yearField.getText());
            stmt.setString(6, contactField.getText());

            stmt.executeUpdate();
            showAlert("Success", "Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateStudent() {
        try {
            String query = "UPDATE students SET name=?, dob=?, program=?, year=?, contact=? WHERE student_id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nameField.getText());
            stmt.setDate(2, Date.valueOf(dobField.getValue()));
            stmt.setString(3, programField.getText());
            stmt.setString(4, yearField.getText());
            stmt.setString(5, contactField.getText());
            stmt.setString(6, studentIdField.getText());

            stmt.executeUpdate();
            showAlert("Success", "Student updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteStudent() {
        try {
            String query = "DELETE FROM students WHERE student_id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, studentIdField.getText());

            stmt.executeUpdate();
            showAlert("Success", "Student deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearFields() {
        studentIdField.clear();
        nameField.clear();
        dobField.setValue(null);
        programField.clear();
        yearField.clear();
        contactField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
