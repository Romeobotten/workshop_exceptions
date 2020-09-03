package se.lexicon.exceptions.workshop;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.exceptions.DuplicateNameException;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List <String> maleFirstNames = CSVReader_Writer.getMaleFirstNames();
        List <String> femaleFirstNames = CSVReader_Writer.getFemaleFirstNames();
        List<String> lastNames = null; // Has to be declared outside try block ?

        try {
            lastNames = CSVReader_Writer.getLastNames();

        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("An error occurred!");
        }

        NameService nameService = new NameService(maleFirstNames, femaleFirstNames, lastNames);

        Person test = nameService.getNewRandomPerson(); // This is working OK
        System.out.println(test);

        try {
                nameService.addFemaleFirstName("Ebba"); // This is working OK
        } catch(DuplicateNameException x) {
                System.out.println(x.getMessage());
        }
        try {
            nameService.addMaleFirstName("Ali"); // This is working OK
        } catch(DuplicateNameException x) {
            System.out.println(x.getMessage());
        }
        try {
            nameService.addLastName("Ali"); // This is working OK, I removed Ali before
        } catch(DuplicateNameException x) {
            System.out.println(x.getMessage());
        }

            test = nameService.getNewRandomPerson(); // This is working OK too again
            System.out.println(test);

        try {
            nameService.addFemaleFirstName("Ella"); // Catching exception OK
        } catch(DuplicateNameException x) {
            System.out.println(x.getMessage()); // Friendly message?
        }
        try {
            nameService.addMaleFirstName("Ali"); // Catching exception OK
        } catch(DuplicateNameException x) {
            System.out.println(x.getMessage());
        }
        try {
            nameService.addLastName("Lind"); // Catching exception OK
        } catch(DuplicateNameException x) {
            System.out.println(x.getMessage());
        }
	}
}
