package se.lexicon.exceptions.workshop.exceptions;

public class DuplicateNameException extends Exception {

    public DuplicateNameException(String s) {
        // Call constructor super
        super(s + " This name was not added to the list.");
    }
}
