package application.controllers;

import application.DBConnection;
import application.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentController {
    @FXML private TextField nameField;
    @FXML private TextField programField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> programColumn;

    private ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadStudents();
    }

    private void loadStudents() {
        students.clear();
        String query = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("program")));
            }
            studentTable.setItems(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addStudent() {
        String name = nameField.getText();
        String program = programField.getText();

        String query = "INSERT INTO students (name, program) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, program);
            stmt.executeUpdate();
            loadStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
