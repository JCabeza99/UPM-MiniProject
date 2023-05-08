package es.upm.pproject.miniproject.exceptions;

public class StudentAlreadyEnrolled extends Exception {
    public StudentAlreadyEnrolled() {
        super("The student has already enrolled into this course");
    }
}
