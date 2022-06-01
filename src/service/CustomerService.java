package service;
import model.Customer;

import java.util.Collection;
import java.util.HashSet;


public class CustomerService {


    private static CustomerService customerService = null;
    private CustomerService() {}
    public static CustomerService getInstance(){
        if (null == customerService){
            customerService = new CustomerService();

        }
        return customerService;
    }

    Collection<Customer> customersNames = new HashSet<>();

    public void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        if (!customersNames.add(customer)){
            System.out.println("I'm sorry, this account already exist! ");
        } else {
            System.out.println("Welcome: " + firstName + " " + lastName +"\n" + "Email: " + email + ".\n " + "Your account has been created.\n" );
        }
    }

    public Customer getCustomer(String customerEmail){
        for (Customer customer: customersNames) {
            if(customer.getEmail().equals(customerEmail)) {
                System.out.println(customer);
                return customer;
            }
        }
        System.out.println("I'm sorry, we are unable to find you in our system, please try again. ");
        return null;
    }

    public Collection<Customer> getAllCustomer(){
        for (Customer customer: customersNames) {
            System.out.println(customer);

        }
        return customersNames;
    }


}
