package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConfig;
import models.Course;

public class CourseDAO {
    // Insert a new course
    public void addCourse(Course course) {
        String sql = "INSERT INTO Courses (title, credit_hours, max_enrollment) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, course.getTitle());
            stmt.setInt(2, course.getCreditHours());
            stmt.setInt(3, course.getMaxEnrollment());
            stmt.executeUpdate();
            System.out.println("Course added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all courses
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("course_id"),
                    rs.getString("title"),
                    rs.getInt("credit_hours"),
                    rs.getInt("max_enrollment")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Update a course
    public void updateCourse(Course course) {
        String sql = "UPDATE Courses SET title=?, credit_hours=?, max_enrollment=? WHERE course_id=?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getTitle());
            stmt.setInt(2, course.getCreditHours());
            stmt.setInt(3, course.getMaxEnrollment());
            stmt.setInt(4, course.getCourseId());

            stmt.executeUpdate();
            System.out.println("Course updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a course
    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM Courses WHERE course_id=?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            stmt.executeUpdate();
            System.out.println("Course deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
