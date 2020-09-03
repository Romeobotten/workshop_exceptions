package se.lexicon.exceptions.workshop.data_access;

import se.lexicon.exceptions.workshop.domain.Gender;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.exceptions.DuplicateNameException;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NameService {

    private List<String> maleFirstNames;
    private List<String> femaleFirstNames;
    private List<String> lastNames;
    private static Random random = new Random();

    //should be no nulls
    public NameService(List<String> maleFirstNames, List<String> femaleFirstNames, List<String> lastNames) {

        this.maleFirstNames = maleFirstNames;
        this.femaleFirstNames = femaleFirstNames;
        this.lastNames = lastNames;
    }

    public Person getNewRandomPerson() {
        Gender gender = getRandomGender();
        Person person = null;
        switch (gender) {
            case MALE:
                person = new Person(getRandomMaleFirstName(), getRandomLastName(), gender);
                break;
            case FEMALE:
                person = new Person(getRandomFemaleFirstName(), getRandomLastName(), gender);
                break;
        }
        return person;
    }

    public String getRandomFemaleFirstName() {
        return femaleFirstNames.get(random.nextInt(femaleFirstNames.size()));
    }

    public String getRandomMaleFirstName() {
        return maleFirstNames.get(random.nextInt(maleFirstNames.size()));
    }

    public String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    public Gender getRandomGender() {
        return random.nextInt(100) > 50 ? Gender.FEMALE : Gender.MALE;
    }

    /**
     * Here you need to check if List<String> femaleFirstNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param name
     */
    public void addFemaleFirstName(String name) throws DuplicateNameException {

            if (femaleFirstNames.contains(name)) {
                throw new DuplicateNameException("Are you a complete idiot? " +
                        name + " already exists!"); // Friendly message
            } else {
                femaleFirstNames.add(name);
                CSVReader_Writer.saveFemaleNames(femaleFirstNames);
                System.out.println(name + " was added to textfile.");
            }
    }

    /**
     * Here you need to check if List<String> maleFirstNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param name
     */
    public void addMaleFirstName(String name) throws DuplicateNameException {

            if (maleFirstNames.contains(name)) {
                throw new DuplicateNameException("Are you a total moron? "+
                        name + " already exists!"); // Friendly message
            } else {
                maleFirstNames.add(name);
                CSVReader_Writer.saveMaleNames(maleFirstNames);
                System.out.println(name + " was added to textfile.");
            }
    }

    /**
     * Here you need to check if List<String> lastNames already contains the name
     * If name already exists throw a new custom exception you will have to create called
     * DuplicateNameException.
     *
     * @param lastName
     */
    public void addLastName(String lastName) throws DuplicateNameException {

//        try { // Not here
            if (lastNames.contains(lastName)) {
                throw new DuplicateNameException("Are you ****ing stupid? "+
                        lastName + " already exists!"); // Friendly message
            } else {
                lastNames.add(lastName);
                Collections.sort(lastNames); // Looks better in text file
                CSVReader_Writer.saveLastNames(lastNames);
                System.out.println(lastName + " was added to textfile.");
            }

//        } catch (DuplicateNameException x) { // This code has moved to Main, try and catch
//            System.out.println(x.getMessage());
//            System.out.println(lastName + " was not added again to textfile.");
//        }
    }
}

