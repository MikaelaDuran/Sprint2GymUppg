package BestGymEver;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilTest {

    FileUtil fileUtil = new FileUtil();

    String inFile = "Test/BestGymEver/TestData.txt";
    String outFile = "Test/BestGymEver/WorkoutLogg.txt";


    //TODO:TESTA INFILEN, LÄSNING AV OLIKA DATA OCH DATUM
    @Test
    void listSizeTest(){
        List<Customer> customer = fileUtil.readDataFromFile(inFile);
        assertEquals(3, customer.size());
        assertNotEquals(1, customer.size());
    }


    //Kontrollera att läsning av data är korrekt
    @Test
    void readDataFromFileTest() {
        List<Customer> customer = fileUtil.readDataFromFile(inFile);

        //Namn
        assertEquals("Alhambra Aromes", customer.get(0).getName());
        assertNotEquals("Mikaela Duran", customer.get(0).getName());

        //Personnummer (Bear Belle)
        assertTrue(customer.get(1).getSocialSecurityNumber().startsWith("8204021234"));
        assertFalse(customer.get(1).getSocialSecurityNumber().startsWith("9911111111"));
    }

    //Datum
    @Test
    void readDateFromFileTest() {
        List<Customer> customer = fileUtil.readDataFromFile(inFile);
        //Datum
        LocalDate expected = LocalDate.parse("2018-03-12");
        LocalDate fakeDate= LocalDate.parse("2024-10-22");
        assertEquals(customer.get(2).getMembershipDate(), expected);
        assertNotEquals(customer.get(2).getMembershipDate(), fakeDate);
    }

    //TODO: TESTA UTFILEN

    @Test
    void writeDataToFileTest() {

        Customer c1 = new Customer("7703021234", "Alhambra Aromes", LocalDate.parse("2024-07-01"));
        Customer c2 = new Customer("8204021234", "Bear Belle", LocalDate.parse("2019-12-02"));
        Customer c3 = new Customer("8512021234", "Chamade Coriola", LocalDate.parse("2018-03-12"));

        //Rensa filen innan test
        File outFileGym = new File("Test/BestGymEver/WorkoutLogg.txt");
        outFileGym.delete();

        //Lista
        List<Customer> testCustomers = Arrays.asList(c1, c2, c3);

        //Skriv till outFile
        fileUtil.writeDataToFile(outFile, c1);
        fileUtil.writeDataToFile(outFile, c2);
        fileUtil.writeDataToFile(outFile, c3);

        assertEquals(6,countLinesInFile("Test/BestGymEver/WorkoutLogg.txt"));
        assertNotEquals(10,countLinesInFile("Test/BestGymEver/WorkoutLogg.txt"));

        try(BufferedReader b = new BufferedReader(new FileReader(outFile))){

            //Verfiera att fösta personen har skirvits in korrekt
            String line = b.readLine();
            assertTrue(line.contains("7703021234, Alhambra Aromes"), "Fel data för Alhambra Aromes");
            line = b.readLine();
            LocalDateTime d1 = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            assertEquals(d1.format(formatter), line, "Fel datum för Alhambra Aromes");


            //Verifera andra personen
            line = b.readLine();
            assertTrue(line.contains("8204021234, Bear Belle"), "Fel data för Bear Belle");

            assertTrue(outFileGym.exists());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final int countLinesInFile(String fileToCount) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileToCount));) {
            while (reader.readLine() != null) lines++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
