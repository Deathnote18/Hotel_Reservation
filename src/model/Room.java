package model;

public class Room implements IRoom{
    private String roomNumber;
    private Double price;
    private RoomType enumeration;



    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;

    }

    @Override
    public boolean isFree() {
        return false;
    }


    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

//    no need to set setters and getters.

//    public void setRoomNumber(String roomNumber) {
//        this.roomNumber = roomNumber;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public RoomType getEnumeration() {
//        return enumeration;
//    }
//
//    public void setEnumeration(RoomType enumeration) {
//        this.enumeration = enumeration;
//    }




    @Override
    public String toString() {
        return "Please find the available rooms below: \n" +"Room number: " + roomNumber + "\nPrice: " + price + "\nType of Room: " + enumeration;
    }

}
