package es.upm.pproject.miniproject.exceptions;

public class EmailFormatException extends Exception {

    public EmailFormatException() {
        super("The email must contain the '@' and  it cannot end with the character '.' ");
    }
}
