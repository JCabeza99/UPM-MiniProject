package es.upm.pproject.miniproject.controller;

import java.util.List;

import es.upm.pproject.miniproject.models.*;
import es.upm.pproject.miniproject.exceptions.*;

public interface ControllerInterface {

    public void newCourse(int id, String name, String coordinator) throws BlankValueException;

    public void registerNewStudent(int id, String name, String email) throws BlankValueException, EmailFormatException;

    public void addStudentToCourse(int idStudent, int idCourse) throws StudentNotRegistered, CourseNotRegistered, CourseLimitReached, StudentAlreadyEnrolled;

    public List<Student> getStudentsFromCourse(int idCourse) throws CourseNotRegistered;

    public void removeStudentFromCourse(int idStudent, int idCourse) throws StudentNotRegistered, CourseNotRegistered, StudentNotEnrolled;

    public void restartCourse(int idCourse) throws CourseNotRegistered;

    public List<Student> getStudents();

    public List<Course> getCourses();

}
