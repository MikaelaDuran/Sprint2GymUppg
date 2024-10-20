package BestGymEver;

import java.util.List;


public class Main {

    //Används för att läsa och skriva till filer
    FileUtil fileUtil = new FileUtil();


    String fileClientInfo = "src/BestGymEver/ClientInformation";
    String outFileToPersonalTrainer = "src/BestGymEver/PersonalTrainer";

    //Konstruktor
    Main () {

        //Läs in data från fil (fileClientInfo)
        //Membership passerar customerList till dess konstruktor
        //InputSystem passerar medlemar(fileUtil) och sökväg till PT-filen
        //runProgram. Startar while-loopen/program där användaren kan mata in information.


        List<Customer> customerList = fileUtil.readDataFromFile(fileClientInfo);
        Membership membership = new Membership(customerList);
        InputSystem inputSystem = new InputSystem(membership, fileUtil, outFileToPersonalTrainer);
        inputSystem.runProgram();

    }


    public static void main(String[] args) {
        Main main = new Main();
    }
}
