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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentManagementSystemMainTest {
    StudentService studentService;
    CourseService courseService;
    EnrollmentService enrollmentService;

    Student s1;
    Course c1;

    @BeforeEach
    void setup() {
        studentService = new StudentService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService();

        // Sample student
        s1 = new Student(1, "Rahul", 21, "Computer Science");
        Student s2 = new Student(2, "Priya", 22, "Artificial Intelligent");

        studentService.addStudent(s1);
        studentService.addStudent(s2);

        // Sample course
        c1 = new Course(101, "Java Backend", 4);
        Course c2 = new Course(102, "Python Backend", 5);

        courseService.addCourse(c1);
        courseService.addCourse(c2);

        // Enrollment
        enrollmentService.enrollStudent(s1, c1, 85.0);
        enrollmentService.enrollStudent(s2, c2, 90.0);
    }

    // --------------------------- Student Tests ---------------------------

    @Test
    void testGetStudentSuccess() throws StudentNotFoundException {
        Student student = new Student(1, "Rahul", 21, "Computer Science");
        studentService.getStudent(1, "Rahul");
        assertEquals("Rahul", student.getName());
    }

    @Test
    void testGetStudentFail() {
        assertThrows(StudentNotFoundException.class,
                () -> studentService.getStudent(99, "XYZ"));
    }

    @Test
    void testSearchStudent() {
        List<Student> result = studentService.search("Rahul");
        assertEquals(1, result.size());
        assertEquals("Rahul", result.get(0).getName());
    }

    // --------------------------- Course Tests ---------------------------

    @Test
    void testGetCourseSuccess() throws CourseNotFoundException {
        Course course =new Course(101, "Java Backend", 4);
        courseService.getCourse(101, "Java Backend");
        assertEquals("Java Backend", course.getCourseName());
    }

    @Test
    void testGetCourseFail() {
        assertThrows(CourseNotFoundException.class,
                () -> courseService.getCourse(999, "Unknown"));
    }

    // --------------------------- Enrollment Tests ---------------------------

    @Test
    void testEnrollmentCount() {
        List<Enrollment> list = enrollmentService.getAll();
        assertEquals(2, list.size());
    }

    @Test
    void testGradeByStudentName() {
        List<String> grade = enrollmentService.grade("Priya");
        assertNotNull(grade);
    }

    // --------------------------- Helper Function Tests ---------------------------

    @Test
    void testAutoGrade() {
        List<String> keywords = Arrays.asList("Java", "Python");
        String submission = "Java assignment done";

        double result = Helper.autoGrade(submission, keywords);
        assertTrue(result > 0);
    }

    // --------------------------- Input Validator Tests ---------------------------

    @Test
    void testValidName() {
        assertTrue(InputValidator.isValidName("Rahul"));
        assertFalse(InputValidator.isValidName("123@@@"));
    }
}
