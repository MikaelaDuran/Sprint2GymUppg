package BestGymEver;

import java.util.Scanner;

public class InputSystem {
    protected Membership membership;
    protected FileUtil fileUtil;
    protected String outFileToPersonalTrainer;

    public void runProgram(){
        while (true) {
            //Frågar om input
            String input = getInput();

            if (input.equalsIgnoreCase("avsluta")) {
                System.out.println("Programmet avslutas.");
                break;
            }
            //Kontrollerar om personen är medlem, om Nuvarande medlem skrivs det i PT filen
            writeMembershipStatus(input);
        }
    }

    public InputSystem(Membership membership, FileUtil fileUtil, String outFileToPersonalTrainer) {
        this.membership = membership;
        this.fileUtil = fileUtil;
        this.outFileToPersonalTrainer = outFileToPersonalTrainer;
    }

    //Läser in användarens input
    public String getInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ange hela namnet, personnummer eller avsluta: ");
        return scanner.nextLine().trim();
    }

    //Hantera input och kontrollera medlem.
    public void writeMembershipStatus (String input) {
        //Anropar metod checkMembership
        String membershipStatus = membership.checkMembershipByInput(input); //checkMembership. Kopplat till for-loop. Går igenom alla kunder
        System.out.println(membershipStatus);

        //Kontrollera om kund är nuvarande medlem och logga besöket.
        if (membershipStatus.equals("Nuvarande medlem")) {
            writeToPersonalTrainerFile(input);
        }
    }

    //Om Nuvarande medlem skriv till PT filen. if sats för att slippa Nullpoint exeptions
        public void writeToPersonalTrainerFile (String input){
            Customer customer = membership.findCustomerByInput(input);
            if (customer != null) {
                fileUtil.writeDataToFile(outFileToPersonalTrainer, customer);
                System.out.println("Besöket har registrerats för " + customer.getName());
            }
        }
}