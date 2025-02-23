package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

public class CourseController {

    @FXML
    private TextField courseIdField, titleField, creditHoursField, departmentField, prerequisitesField,
            maxEnrollmentField;

    private Connection connection;

    // Initialize Database Connection
    public CourseController() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/course_registration", "root",
                    "yourpassword");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addCourse() {
        try {
            String query = "INSERT INTO courses (course_id, title, credit_hours, department, prerequisites, max_enrollment) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, courseIdField.getText());
            stmt.setString(2, titleField.getText());
            stmt.setInt(3, Integer.parseInt(creditHoursField.getText()));
            stmt.setString(4, departmentField.getText());
            stmt.setString(5, prerequisitesField.getText());
            stmt.setInt(6, Integer.parseInt(maxEnrollmentField.getText()));

            stmt.executeUpdate();
            showAlert("Success", "Course added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateCourse() {
        try {
            String query = "UPDATE courses SET title=?, credit_hours=?, department=?, prerequisites=?, max_enrollment=? WHERE course_id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, titleField.getText());
            stmt.setInt(2, Integer.parseInt(creditHoursField.getText()));
            stmt.setString(3, departmentField.getText());
            stmt.setString(4, prerequisitesField.getText());
            stmt.setInt(5, Integer.parseInt(maxEnrollmentField.getText()));
            stmt.setString(6, courseIdField.getText());

            stmt.executeUpdate();
            showAlert("Success", "Course updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteCourse() {
        try {
            String query = "DELETE FROM courses WHERE course_id=?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, courseIdField.getText());

            stmt.executeUpdate();
            showAlert("Success", "Course deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearFields() {
        courseIdField.clear();
        titleField.clear();
        creditHoursField.clear();
        departmentField.clear();
        prerequisitesField.clear();
        maxEnrollmentField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
