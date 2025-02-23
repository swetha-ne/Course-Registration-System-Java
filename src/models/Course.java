package models;

public class Course {
    private int courseId;
    private String title;
    private int creditHours;
    private int maxEnrollment;

    public Course(int courseId, String title, int creditHours, int maxEnrollment) {
        this.courseId = courseId;
        this.title = title;
        this.creditHours = creditHours;
        this.maxEnrollment = maxEnrollment;
    }

    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCreditHours() { return creditHours; }
    public void setCreditHours(int creditHours) { this.creditHours = creditHours; }

    public int getMaxEnrollment() { return maxEnrollment; }
    public void setMaxEnrollment(int maxEnrollment) { this.maxEnrollment = maxEnrollment; }
}
