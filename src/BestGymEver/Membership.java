package BestGymEver;

import java.time.LocalDate;
import java.util.List;


public class Membership {

    protected List<Customer> customerList;

    public Membership(List<Customer> customerList) {
        this.customerList = customerList;
    }


    public LocalDate oneYearAgo() {
        return LocalDate.now().minusYears(1);
    }

    public Customer findCustomerByInput(String input) {
        for (Customer customer : customerList) {
            if (customer.getSocialSecurityNumber().trim().equalsIgnoreCase(input) ||
                    customer.getName().trim().equalsIgnoreCase(input)) {
                return customer;
            }
        }
        return null;
    }

    //Kontrollera om nuvarande medlem, föredetta eller obehörig
    public String checkMembershipByInput(String input) {
        //Anropa metoden findCustomerByInput. For-loop igenom kund listan.
        Customer customer = findCustomerByInput(input);
        return checkMembership(customer);
    }

    public String checkMembership(Customer customer) {
        if(customer == null){
            return "Obehörig";
        }

        if(customer.getMembershipDate() == null) {
            return "Obehörig";

        }
            if (customer.getMembershipDate().isAfter(oneYearAgo())) {
                return "Nuvarande medlem";
            } else {
                return "Föredetta medlem";
            }
    }

}



