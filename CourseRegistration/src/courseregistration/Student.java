package courseregistration;

public class Student {
    private String studentID;
    private String name;

    // Constructor
    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    // Getter metotlari
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    // Ogrenci bilgilerini yazdiran metot
    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentID + ", Name: " + name);
    }
}
