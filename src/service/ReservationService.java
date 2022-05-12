package service;
import model.Customer;
import model.IRoom;
import model.Reservation;
//import model.Room;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class ReservationService {



    private static ReservationService reservationService = null;
    private ReservationService() {}
    public static ReservationService getInstance(){
        if (null == reservationService){
            reservationService = new ReservationService();

        }
        return reservationService;
    }


    Collection<IRoom> roomsAddedHashSet = new HashSet<>();
    public void addRoom(IRoom room){
        if (!roomsAddedHashSet.add(room)){
            System.out.println("This room as already been added");
        } else {
            System.out.println("Room \n" + room + " has been added! " );
        }
    }

    public IRoom getARoom(String roomID) {
        for (IRoom room : roomsAddedHashSet) {
            if (room.getRoomNumber().equals(roomID)) {
                return room;
            }
        }
//        System.out.println("I'm sorry, we are unable to find " + roomID + " in our system, please try again. "); Causing room not to save, commenting this out.
        return null;
    }

    Collection<Reservation> reservationHashSet = new HashSet<>();
    Collection<IRoom> roomsNotOccupied = new HashSet<>();
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
       roomsNotOccupied = findRooms(checkInDate, checkOutDate);
       if (roomsNotOccupied.contains(room)){
           Reservation reservationAdded = new Reservation(customer, room, checkInDate, checkOutDate);
           if (!reservationHashSet.add(reservationAdded)) {
               System.out.println("Sorry, we have no rooms available on that day. Please try again. ");
           } else  {
               System.out.println("Hooray!! We have place your booking. We'll see you on that day. Please find your reservation below. \n" + reservationHashSet);
               return reservationAdded;
           }
       }

        return null;
    }


    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        for (IRoom availableRooms: roomsAddedHashSet) {
            if (!reservationHashSet.contains(availableRooms)){
                roomsNotOccupied.add(availableRooms);
                return roomsNotOccupied;
            }
            for (Reservation reservation: reservationHashSet) {
                if (reservation.getRoom().equals(availableRooms)){
                    if (checkOutDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckInDate()) ||
                            checkInDate.before(reservation.getCheckInDate()) && checkInDate.after(reservation.getCheckInDate())) {
                        roomsNotOccupied.add(availableRooms);
                    }
                }
            }
        }

        return roomsNotOccupied;
    }

    Collection<Reservation> customerReservation_HashSet = new HashSet<>();
    public Collection<Reservation> getCustomersReservation(String customer){
        for (Reservation reservation: customerReservation_HashSet) {
            if (reservation.getCustomer().equals(customer)){
                customerReservation_HashSet.add(reservation);
            }
        }
        System.out.println(customerReservation_HashSet);
        return customerReservation_HashSet;
    }

    public void printAllReservation(){
        for (Reservation reservation: reservationHashSet) {
             System.out.println(reservation);
        }
    }


    public Collection<IRoom> getAllRooms() {
        return roomsAddedHashSet;
    }
}
