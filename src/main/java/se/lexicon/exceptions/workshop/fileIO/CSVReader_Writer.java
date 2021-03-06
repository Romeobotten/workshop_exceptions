package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {
	 /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     * @return List<String>of male firstnames
     */
    public static List<String> getMaleFirstNames(){

        BufferedReader reader = null;
        List <String> names = null;

        try {
        	reader = Files.newBufferedReader(Paths.get("firstname_males.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());

        } catch (FileNotFoundException f) {
            f.printStackTrace();
            System.out.println("File not found!"); // catch (FileNotFoundException|IOException e)

        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("An error occurred!");
        }
            finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e){
                System.out.println(e.getMessage());
                System.out.println("An error occurred!");
            }
        }
         	return names;
    }

    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     * @return
     */
    public static List<String> getFemaleFirstNames(){

        List<String> names=null;

        try { // reader declared inside try block
            BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());

        } catch (FileNotFoundException f) {
            f.printStackTrace();
            System.out.println("File not found!");
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("An error occurred!");
        }
        return names;  //  No need for finally here, resources declared inside try block
    }

    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     * @return List <String> of last names
     * @throws IOException
     */
    public static List<String> getLastNames() throws IOException { // Throws handles in Main

        BufferedReader reader = null;
        List<String> names = null;

//        try { Not here
            reader = Files.newBufferedReader(Paths.get("lastnames.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
//        } catch (FileNotFoundException e) { // This code should be in Main
//            e.printStackTrace();
//            System.out.println("File not found!");
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//            System.out.println("An error occurred!");
//        }

 //       finally {
            if(reader != null){
                reader.close();
            }
//        }
        return names;
    }

    public static void saveLastNames(List <String> lastNames){
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("lastnames.txt"));
                for (String toWrite : lastNames) {
                    writer.append(toWrite + ",");
                }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("An error occurred!");
        }
        finally {
            if (writer != null) {
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred!");
                }
            }
        }
    }

    public static void saveFemaleNames(List <String> femaleNames){

        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"));
                for(String toWrite : femaleNames){
                    writer.append(toWrite+",");
                }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("An error occurred!");
        }
         finally {
            if (writer != null) {
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred!");
                }
            }
        }
    }

    public static void saveMaleNames(List <String> maleNames){
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"));
                for(String toWrite : maleNames){
                    writer.append(toWrite+",");
                }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("An error occurred!");
        }
         finally {
            if (writer != null) {
                try {
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred!");
                }
            }
        }
    }
}
