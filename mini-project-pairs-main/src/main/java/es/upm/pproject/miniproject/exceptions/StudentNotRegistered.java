package es.upm.pproject.miniproject.exceptions;

public class StudentNotRegistered extends Exception {

    public StudentNotRegistered() {
        super("The student is not registered in the system");
    }
}
