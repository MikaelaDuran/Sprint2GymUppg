package BestGymEver;

import java.time.LocalDate;

public class Customer {

    protected String socialSecurityNumber;
    protected String fullName;
    protected LocalDate membershipDate; //for payment


    public Customer(String socialSecurityNumber, String fullName, LocalDate membershipDate) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.fullName = fullName;
        this.membershipDate = membershipDate;

    }
    //Getters

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getName() {
        return fullName;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

}

