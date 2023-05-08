package es.upm.pproject.miniproject.exceptions;

public class CourseNotRegistered extends Exception{

    public CourseNotRegistered() {
        super("The course is not registered");
    }
}
