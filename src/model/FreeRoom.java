package model;

public class FreeRoom extends Room {
//        private final Double price;
    // I want to see if this will work if I comment out this price variable

    public FreeRoom (String roomNumber, Double price, RoomType enumeration){
        super(roomNumber, 0.0, enumeration);
//        this.price = 0.0;
    }

    @Override
    public String toString() {
        return "This is a free room\n" + "Room number: " + getRoomNumber() + "\nPrice" + getRoomPrice() +"\nType of room: " + getRoomType() + super.toString();
    }



}
