package application.models;

public class Student {
    private int id;
    private String name;
    private String program;

    public Student(int id, String name, String program) {
        this.id = id;
        this.name = name;
        this.program = program;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getProgram() { return program; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setProgram(String program) { this.program = program; }
}
