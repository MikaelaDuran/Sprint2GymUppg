package BestGymEver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {


    //Try-with-resources
    public List<Customer> readDataFromFile(String readFromFile) {
        String firstLine;
        String secondLine;
        LocalDate membershipDate = null;

        List<Customer> customerList = new ArrayList<Customer>();
        Path inFilePath = Paths.get(readFromFile);

        try (Scanner fileScanner = new Scanner(inFilePath)) {
            while (fileScanner.hasNext()) {
                firstLine = fileScanner.nextLine();
                String[] customerDataFirstLine = firstLine.split(",");
                String socialSecurityNumber = customerDataFirstLine[0].trim();
                String fullName = customerDataFirstLine[1].trim();

                if (fileScanner.hasNext()) {
                    secondLine = fileScanner.nextLine().trim();
                    membershipDate = LocalDate.parse(secondLine, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }

                //Personnummer, Namn, MembershipDate
                Customer c = new Customer(socialSecurityNumber, fullName, membershipDate);

                customerList.add(c);
            }
        } catch (IOException e) {
            System.out.println("Fel inträffat vid inläsning av fil");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }

        return customerList;
    }



    public void writeDataToFile(String writeToFile, Customer customer) {
        Path outFilePath = Paths.get(writeToFile);
        Customer c = customer;


        try (PrintWriter writer = new PrintWriter(
                Files.newBufferedWriter(outFilePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {

            LocalDateTime DateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            // Skriver personnummer och namn på första raden
            writer.println(c.getSocialSecurityNumber() + ", " + c.getName());
            // Skriver datumet på nästa rad
            writer.println(DateTime.format(formatter));

        }
        catch (FileNotFoundException e){
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e){
            System.out.println("Det gick inte att skriva till fil");
            e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
    }
}