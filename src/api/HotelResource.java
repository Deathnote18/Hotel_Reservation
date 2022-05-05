package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;


public class HotelResource {

    private static CustomerService customerService = CustomerService.getInstance();
    private static ReservationService reservationService = ReservationService.getInstance();

    private static HotelResource hotelResource = null;
    private void HotelResource() {}
    public static HotelResource getInstance(){
        if (null == hotelResource){
            hotelResource = new HotelResource();

        }
        return hotelResource;
    }


    public Customer getCustomer(String email){

        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getARoom(String roomNumber){

        return reservationService.getARoom(roomNumber);
    }


    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = CustomerService.getInstance().getCustomer(customerEmail);
        if (null == customer) {
            return null;
        } else {
            return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        }
    }
        public Collection<Reservation> getCustomerReservation (String customerEmail){

            return reservationService.getCustomersReservation(customerEmail);
        }

        public Collection<IRoom> findARoom (Date checkInDate, Date checkOutDate){
            return reservationService.findRooms(checkInDate, checkOutDate);
        }



}
