package se.lexicon.exceptions.workshop;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
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

        Person test = nameService.getNewRandomPerson();
        System.out.println(test);
        nameService.addFemaleFirstName("Eva");
        nameService.addMaleFirstName("Allan");
        nameService.addFemaleFirstName("Lisa");
        nameService.addLastName("Andersson");
        nameService.addMaleFirstName("Johan");
        nameService.addFemaleFirstName("Eva");
        nameService.addLastName("Svensk");
        nameService.addMaleFirstName("Kent");
        nameService.addFemaleFirstName("Åsa");
	}

}
