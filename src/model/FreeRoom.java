package model;

public class FreeRoom extends Room {
//        private final Double price;
    // I want to see this will work if I comment out this price variable

    public FreeRoom (String roomNumber, Double price, RoomType enumeration){
        super(roomNumber, 0.0, enumeration);
//        this.price = 0.0;
    }

    @Override
    public String toString() {
        return "This room is free " + super.toString();
    }



}
