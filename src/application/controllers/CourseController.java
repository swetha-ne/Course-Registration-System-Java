package application.controllers;

import application.DBConnection;
import application.models.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CourseController {
    @FXML private TextField titleField;
    @FXML private TextField creditsField;
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> titleColumn;
    @FXML private TableColumn<Course, Integer> creditsColumn;

    private ObservableList<Course> courses = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadCourses();
    }

    private void loadCourses() {
        courses.clear();
        String query = "SELECT * FROM courses";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("title"), rs.getInt("credits")));
            }
            courseTable.setItems(courses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addCourse() {
        String title = titleField.getText();
        int credits = Integer.parseInt(creditsField.getText());

        String query = "INSERT INTO courses (title, credits) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setInt(2, credits);
            stmt.executeUpdate();
            loadCourses();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
