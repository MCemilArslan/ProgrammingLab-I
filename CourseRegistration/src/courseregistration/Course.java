package courseregistration;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private int capacity;
    private ArrayList<Student> enrolledStudents;

    // Constructor
    public Course(String courseName, int capacity) {
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getter metotlari
    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Kursa ogrenci ekleme metodu (Kapasite ve Mükerrer Kayıt Kontrolü ile)
    public boolean addStudent(Student student) {
        // 1. Kapasite kontrolü
        if (enrolledStudents.size() >= capacity) {
            System.out.println("This course is full. Registration failed.");
            return false;
        }

        // 2. Ayni studentID ile kayitli mi kontrolü
        for (Student s : enrolledStudents) {
            if (s.getStudentID().equalsIgnoreCase(student.getStudentID())) {
                System.out.println("This student is already registered for this course.");
                return false;
            }
        }

        // 3. Sorun yoksa kayit et
        enrolledStudents.add(student);
        System.out.println("Registration successful.");
        return true;
    }

    // Kurs bilgilerini ve kayitli ogrencileri detayli yazdiran metot
    public void displayCourseInfo() {
        System.out.println("\n----------------------------------------");
        System.out.println("Course Name: " + courseName);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled Students Count: " + enrolledStudents.size());
        System.out.print("Enrolled Students: ");
        
        if (enrolledStudents.isEmpty()) {
            System.out.println("None");
        } else {
            System.out.println();
            for (Student s : enrolledStudents) {
                System.out.println("  - " + s.getName() + " (ID: " + s.getStudentID() + ")");
            }
        }
        System.out.println("----------------------------------------");
    }
}
