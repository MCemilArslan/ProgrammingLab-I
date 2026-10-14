/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package courseregistration;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistration {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        // ----------------------------------------------------
        // TASK 3: Course Setup
        // ----------------------------------------------------
        int numCourses = 0;
        while (numCourses < 3) {
            System.out.print("How many courses do you want to create (at least 3)? ");
            numCourses = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme

            if (numCourses < 3) {
                System.out.println("Please enter at least 3 courses!");
            }
        }

        for (int i = 0; i < numCourses; i++) {
            System.out.println("\n--- Entering details for Course " + (i + 1) + " ---");
            System.out.print("Enter course name: ");
            String courseName = scanner.nextLine();

            System.out.print("Enter classroom capacity: ");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme

            Course newCourse = new Course(courseName, capacity);
            courses.add(newCourse);
        }

        // Olusturulan kurslarin listesini goster
        System.out.println("\n========================================");
        System.out.println("Available Courses for Registration:");
        for (Course c : courses) {
            System.out.println("- " + c.getCourseName() + " (Capacity: " + c.getCapacity() + ")");
        }
        System.out.println("========================================\n");


        // ----------------------------------------------------
        // TASK 4: Register Students
        // ----------------------------------------------------
        boolean keepRegistering = true;

        while (keepRegistering) {
            System.out.println("\n--- Student Registration ---");
            System.out.print("Enter Student ID: ");
            String studentID = scanner.nextLine();

            System.out.print("Enter Student Name: ");
            String studentName = scanner.nextLine();

            System.out.print("Enter the exact name of the course to register: ");
            String targetCourseName = scanner.nextLine();

            // Kursu listede arama
            Course foundCourse = null;
            for (Course c : courses) {
                if (c.getCourseName().equalsIgnoreCase(targetCourseName)) {
                    foundCourse = c;
                    break;
                }
            }

            if (foundCourse == null) {
                System.out.println("No course found with that name.");
            } else {
                Student student = new Student(studentID, studentName);
                foundCourse.addStudent(student);
            }

            // Baska ogrenci eklenmek isteniyor mu?
            System.out.print("\nDo you want to register another student? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                keepRegistering = false;
            }
        }


        // ----------------------------------------------------
        // TASK 5: View All Courses
        // ----------------------------------------------------
        System.out.println("\n========================================");
        System.out.println("            ALL COURSES STATUS          ");
        System.out.println("========================================");
        
        for (Course c : courses) {
            c.displayCourseInfo();
        }

        scanner.close();
    }
}
