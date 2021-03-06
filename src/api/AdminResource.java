package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.List;


public class AdminResource {

    private static CustomerService customerService = CustomerService.getInstance();
    private static ReservationService reservationService = ReservationService.getInstance();

    private static AdminResource adminResource = null;
    private AdminResource() {}
    public static AdminResource getInstance(){
        if (null == adminResource){
            adminResource = new AdminResource();

        }
        return adminResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms){
        for (IRoom room: rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }

}
