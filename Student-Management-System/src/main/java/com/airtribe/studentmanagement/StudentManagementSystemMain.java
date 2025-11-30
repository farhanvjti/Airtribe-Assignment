package com.airtribe.studentmanagement;

import com.airtribe.studentmanagement.entity.Course;
import com.airtribe.studentmanagement.entity.Enrollment;
import com.airtribe.studentmanagement.entity.Student;
import com.airtribe.studentmanagement.exception.CourseNotFoundException;
import com.airtribe.studentmanagement.exception.StudentNotFoundException;
import com.airtribe.studentmanagement.service.CourseService;
import com.airtribe.studentmanagement.service.EnrollmentService;
import com.airtribe.studentmanagement.service.StudentService;
import com.airtribe.studentmanagement.util.Helper;
import com.airtribe.studentmanagement.util.InputValidator;

import java.util.Arrays;
import java.util.List;

public class StudentManagementSystemMain {
    public static void main(String[] args) {
        System.out.println("Student Management System Started...");
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();

        // Sample Data
        Student s1 = new Student(1, "Rahul", 21, "Computer Science");
        Student s2 = new Student(2, "Priya", 22, "Artificial Intelligent");
        Student s3 = new Student(3, "Farhan", 22, "Information Technology");
        Student s4 = new Student(4, "Sonia", 23, "Data Base Management");
        Student s5 = new Student(5, "Shubham", 23, "Computer Science");
        Student s6 = new Student(6, "Fatima", 24, "Artificial Intelligent");
        Student s7 = new Student(7, "Ajay", 24, "Information Technology");
        Student s8 = new Student(8, "Jyoti", 25, "Data Base Management");
        Student s9 = new Student(9, "Diptesh", 25, "Computer Science");
        Student s10 = new Student(10, "Neha", 26, "Artificial Intelligent");

        studentService.addStudent(s1);
        studentService.addStudent(s2);
        studentService.addStudent(s3);
        studentService.addStudent(s4);
        studentService.addStudent(s5);
        studentService.addStudent(s6);
        studentService.addStudent(s7);
        studentService.addStudent(s8);
        studentService.addStudent(s9);
        studentService.addStudent(s10);

        Course c1 = new Course(101, "Java Backend", 4);
        Course c2 = new Course(102, "NodeJS Backend", 5);
        Course c3 = new Course(103, "Python Backend", 6);
        Course c4 = new Course(104, "DataBase Management", 7);
        Course c5 = new Course(105, "AI/ML Management", 8);

        courseService.addCourse(c1);
        courseService.addCourse(c2);
        courseService.addCourse(c3);
        courseService.addCourse(c4);
        courseService.addCourse(c5);

        enrollmentService.enrollStudent(s1, c1, 85.0);
        enrollmentService.enrollStudent(s2, c5, 90.0);
        enrollmentService.enrollStudent(s3, c3, 88.0);
        enrollmentService.enrollStudent(s4, c4, 72.0);
        enrollmentService.enrollStudent(s5, c2, 95.0);
        enrollmentService.enrollStudent(s6, c5, 80.0);
        enrollmentService.enrollStudent(s7, c3, 78.0);
        enrollmentService.enrollStudent(s8, c4, 92.0);
        enrollmentService.enrollStudent(s9, c1, 87.0);
        enrollmentService.enrollStudent(s10, c5, 91.0);

        studentService.getAllStudents();
        try {
            studentService.getStudent(11, "Raju");
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
        courseService.getAllCourses();
        try {
            courseService.getCourse(110, "Civil Engineering");
        } catch (CourseNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Student Enrollments are given below:");
        for (Enrollment enrolled : enrollmentService.getAll()) {
            System.out.println(enrolled);
        }

        System.out.println("Get Student name with course name:-" + enrollmentService.findStudentNameWithCourse(s1.getId(), c1.getCourseId()));
        System.out.println("Search Student Detail by name:-" + studentService.search("Diptesh"));
        System.out.println("Get Student Grades by name:-" + enrollmentService.grade("Fatima"));

        List<String> keywords = Arrays.asList("Java", "NodeJS", "Python", "DataBase", "AI/ML");
        String submission = "Java & NodeJS & Python assignment completed Successfully";
        System.out.println("Evaluate the Grade According to submission of assignment:-" + Helper.autoGrade(submission, keywords));

        if (InputValidator.isValidName("333@")) {
            System.out.println("Valid Name Your are using");
        } else {
            System.out.println("Invalid Name");
        }
    }
}
