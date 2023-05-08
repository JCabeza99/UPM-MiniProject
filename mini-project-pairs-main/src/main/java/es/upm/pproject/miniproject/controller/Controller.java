package es.upm.pproject.miniproject.controller;

import es.upm.pproject.miniproject.models.*;
import es.upm.pproject.miniproject.exceptions.*;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class Controller implements ControllerInterface {

    private SortedMap<Integer, Student> students;
    private SortedMap<Integer, Course> courses;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller() {
        students = new TreeMap<>();
        courses = new TreeMap<>();
    }

    public void newCourse(int id, String name, String coordinator) throws BlankValueException {
        checkCourseFormat(id, name, coordinator);
        Course course = new Course(id, name, coordinator);
        courses.put(course.getId(), course);
        logger.info("Course registered");
    }

    public void registerNewStudent(int id, String name, String email) throws BlankValueException, EmailFormatException {
        checkStudentFormat(id, name, email);
        if (!email.contains("@") || email.charAt(email.length() - 1) == '.') {
            throw new EmailFormatException();
        }
        Student student = new Student(id, name, email);
        students.put(id, student);
        logger.info("Student registered");
    }

    public void addStudentToCourse(int idStudent, int idCourse)
            throws StudentNotRegistered, CourseNotRegistered, CourseLimitReached, StudentAlreadyEnrolled {
        Course course = courses.get(idCourse);
        Student student = students.get(idStudent);
        if (student == null) {
            throw new StudentNotRegistered();
        } else if (course == null) {
            throw new CourseNotRegistered();
        }
        course.addStudent(student);
        logger.info("Student {} enrolled to course {}", idStudent, idCourse);
    }

    public List<Student> getStudentsFromCourse(int idCourse) throws CourseNotRegistered {
        if (!courses.containsKey(idCourse)) {
            throw new CourseNotRegistered();
        }
        logger.info("Retrieved students from {}", idCourse);
        return courses.get(idCourse).getStudents();
    }

    public void removeStudentFromCourse(int idStudent, int idCourse)
            throws StudentNotRegistered, CourseNotRegistered, StudentNotEnrolled {
        Course course = courses.get(idCourse);
        Student student = students.get(idStudent);
        if (student == null) {
            throw new StudentNotRegistered();
        } else if (course == null) {
            throw new CourseNotRegistered();
        }
        course.removeStudent(student);
        logger.info("Removed student {} from course {}",idStudent,idCourse);
    }

    public void restartCourse(int idCourse) throws CourseNotRegistered {
        Course course = courses.get(idCourse);
        if(course == null) {
            throw new CourseNotRegistered();
        }
        course.clearCourse();
        logger.info("Course {} has been cleared", idCourse);
    }

    public ArrayList<Student> getStudents() {
        logger.info("Retrieved registered student list");
        return new ArrayList<>(students.values());
    }

    public ArrayList<Course> getCourses() {
        logger.info("Retrieved registered courses list");
        return new ArrayList<>(courses.values());
    }

    // aux methods
    private void checkCourseFormat(int id, String name, String coordinator) throws BlankValueException {
        if (id <= 0) {
            throw new BlankValueException("The course code must be a positive number greater than 0");
        } else if (name.isBlank()) {
            throw new BlankValueException("The course name must not be blank");
        } else if (coordinator.isBlank()) {
            throw new BlankValueException("The coordinator must not be blank");
        }
    }

    private void checkStudentFormat(int id, String name, String email) throws BlankValueException {
        if (id <= 0) {
            throw new BlankValueException("The student code must be a positive number greater than 0");
        } else if (name.isBlank()) {
            throw new BlankValueException("The student name must not be blank");
        } else if (email.isBlank()) {
            throw new BlankValueException("The email must not be blank");
        }
    }
}
