package es.upm.pproject.miniproject.exceptions;

public class CourseLimitReached extends Exception{
    public CourseLimitReached() {
        super("The course has reached the max number of students that can be enrolled");
    }
}
