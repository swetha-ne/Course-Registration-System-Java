import dao.StudentDAO;
import models.Student;

public class Main {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        // Add a new student
        Student newStudent = new Student(0, "David Miller", "2002-10-12", "Software Engineering", 2, "david@example.com");
        studentDAO.addStudent(newStudent);

        // Fetch and display all students
        System.out.println("All Students:");
        for (Student student : studentDAO.getAllStudents()) {
            System.out.println(student.getStudentId() + " - " + student.getName());
        }

        // Update a student
        Student updatedStudent = new Student(1, "Alice Johnson", "2000-05-15", "Computer Science", 4, "alice_updated@example.com");
        studentDAO.updateStudent(updatedStudent);

        // Delete a student
        studentDAO.deleteStudent(3);
    }
}
