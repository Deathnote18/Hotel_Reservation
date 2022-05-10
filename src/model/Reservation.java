package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    I will comment these out, I don't believe that I will need them.}

    public IRoom getRoom() {
        return room;
    }

//    public void setRoom(IRoom room) {
//        this.room = room;
//I will comment these out, I don't believe that I will need them.}

    public Date getCheckInDate() {
        return checkInDate;
    }

//    public void setCheckInDate(Date checkInDate) {
//        this.checkInDate = checkInDate;
//   I will comment these out, I don't believe that I will need them. }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

//    public void setCheckOutDate(Date checkOutDate) {
//        this.checkOutDate = checkOutDate;
//  I will comment these out, I don't believe that I will need them.  }

    @Override
    public String toString() {
        return "Welcome to the Living Lavishly Hotel: \n" + customer +
                "\n You are in room: \n" + room +
                "\n You are allowed to check-in on " + checkInDate + " after 3PM \n" +
                "Check out is on " + checkOutDate + " before 12 PM \n";
    }
}
