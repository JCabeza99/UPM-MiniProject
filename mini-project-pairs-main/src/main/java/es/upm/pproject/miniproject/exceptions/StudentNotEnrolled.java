package es.upm.pproject.miniproject.exceptions;

public class StudentNotEnrolled extends Exception{
    public StudentNotEnrolled() {
        super("The student is not enrolled in this course");
    }
}
