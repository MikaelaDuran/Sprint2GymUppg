package BestGymEver;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MembershipTest {

    // Att inläsning av korrekt data görs,på korrekt sätt
    // Att datat kontrolleras och behandlas på rätt sätt
    // Att korrekta utskrifter skrivs till fil

    Membership membership;
    Customer currentCustomer;
    Customer formerCustomer;
    Customer nonCustomer;

    @BeforeEach
    public void setUp() {
        List<Customer>customerList=new ArrayList<>();

        //Nuvarande medlem
        currentCustomer = new Customer("9912124444","Anna Svensson",
                LocalDate.now().minusMonths(2));

        //Föredetta medlem
        formerCustomer = new Customer("8811223333","Sven Eriksson",
                LocalDate.now().minusYears(3));

        //Obehörig
        nonCustomer = new Customer("7010112345","Fredrik Persson",null);

        customerList.add(currentCustomer);
        customerList.add(formerCustomer);
        customerList.add(nonCustomer);
        membership=new Membership(customerList);
    }
    @Test
    public void currentMemberTest() {
        assertEquals("Nuvarande medlem",membership.checkMembership(currentCustomer));
        assertNotEquals("Nuvarande medlem",membership.checkMembership(formerCustomer));
    }

    @Test
    public void formerMemberTest() {
        assertEquals("Föredetta medlem",membership.checkMembership(formerCustomer));
        assertNotEquals("Föredetta medlem",membership.checkMembership(nonCustomer));

    }

    @Test
    public void nonMemberTest() {
        assertEquals("Obehörig",membership.checkMembership(nonCustomer));
        assertNotEquals("Obehörig",membership.checkMembership(formerCustomer));


    }

}
