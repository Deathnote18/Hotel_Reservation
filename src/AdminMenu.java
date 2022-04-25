import api.HotelResource;
import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;
import model.Room;
import model.FreeRoom;
import model.*;

import java.util.*;
import java.util.regex.Pattern;

public class AdminMenu {
    private static AdminResource adminResourceInstance = AdminResource.getInstance();
    private static HotelResource hotelResourceInstance = HotelResource.getInstance();
    private static Collection<Reservation> reservationHashSet = new HashSet<>();
    private static Collection<IRoom> roomNotOccupiedHashSet = new HashSet<>();
    private static Collection<Customer> customersHashset = new HashSet<>();


    private static AdminMenu adminMenuInstance = null;
    private AdminMenu() {}
    public static AdminMenu getInstance(){
        if (null == adminMenuInstance){
            adminMenuInstance = new AdminMenu();
        }
        return adminMenuInstance;
    }

    static Scanner adminMenuScanner = new Scanner(System.in);

    public static void launchAdminMenu(){
        boolean keepRunning = true;

        while(keepRunning) {

            try {


                System.out.println("Please choose an option in the menu below".toUpperCase(Locale.ROOT));
                System.out.println("\n-----------------------------\n");
                System.out.println("1: See all customers".toUpperCase(Locale.ROOT));
                System.out.println("2: See all Rooms".toUpperCase(Locale.ROOT));
                System.out.println("3: See all Reservations".toUpperCase(Locale.ROOT));
                System.out.println("4: add a room".toUpperCase(Locale.ROOT));
                System.out.println("5: Back to Main Menu".toUpperCase(Locale.ROOT));


                int selection = Integer.parseInt(adminMenuScanner.nextLine());

                switch (selection) {
                    case 1:
                        customersHashset = adminResourceInstance.getAllCustomer();
                        if (customersHashset.isEmpty()) {
                            System.out.println("Unable to find customers in database.");
                            continue;
                        } else {
                            for (Customer customer : customersHashset) {
                                System.out.println("Customers that are in the database\n\t" + customer);
                            }
                        }
                        break;
//                    keepRunning = false;
                    case 2:
                        roomNotOccupiedHashSet = adminResourceInstance.getAllRooms();
                        if (roomNotOccupiedHashSet.isEmpty()) {
                            System.out.println("Unable to find any rooms in the database.");
                            continue;
                        } else {
                            for (IRoom roomNotFound : roomNotOccupiedHashSet) {
                                System.out.println(roomNotFound);
                            }
                        }
                        break;
                    case 3:
                        adminResourceInstance.displayAllReservations();
                        break;
                    case 4:
                        adminResourceInstance.addRoom((List<IRoom>) roomAdded());
                        break;
                    case 5:
                        MainMenu mainMenuInstance = MainMenu.getInstance();
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("I'm sorry, that is not an option. Please try again");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("It seems like you've entered an invalid input, sorry. We are unable to read it.");
                e.printStackTrace();
            }
        }



    }

private static Collection<IRoom> roomAdded(){
        boolean keepRunning = true;
        String roomAddedInputFromAdmin = "";
        String yesOrNoResponseRegex = "";
        Scanner wouldYouLikeToAddAnotherRoomScanner = new Scanner(System.in);

        while (keepRunning){
            yesOrNoResponseRegex = "[yYnN]";
            Pattern pattern = Pattern.compile(yesOrNoResponseRegex);
            String roomNumber = getRoomNumber();
            Double roomPrice = getRoomPrice();
            RoomType roomType = getRoomType();
            IRoom room = (IRoom)new Room(roomNumber, roomPrice, roomType);
            roomNotOccupiedHashSet.add(room);
            System.out.println("adding room\n".toUpperCase(Locale.ROOT) + room);
            boolean askAgain = true;
            while (askAgain){
                try {
                    System.out.println("Add another room?\n'Yes or No' ".toUpperCase(Locale.ROOT));
                    roomAddedInputFromAdmin = wouldYouLikeToAddAnotherRoomScanner.nextLine();
                    if (!pattern.matcher(roomAddedInputFromAdmin).matches()){
                        askAgain = true;
                        throw new IllegalArgumentException();

                    }else if (roomAddedInputFromAdmin.equals("y") || roomAddedInputFromAdmin.equals("yes")){
                        askAgain = false;
                        keepRunning = true;
                    }else if (roomAddedInputFromAdmin.equals("n") || roomAddedInputFromAdmin.equals("no")){
                        askAgain = false;
                        keepRunning = false;
                    }
                }catch (Exception exception){
                    System.out.println("Please enter 'Yes or No' or 'y' or 'n'");
                    keepRunning = true;
                }finally {
                    System.out.println("Please enter 'Yes or No' or 'y' or 'n'");
                }
            }


        }
    return roomNotOccupiedHashSet;
}

    public static String getRoomNumber() {

        String roomID = null;
        roomID = validateInputNumber();
        return roomID;

    }

    private static RoomType getRoomType(){
        boolean keepRunning = true;
        RoomType roomType = RoomType.SINGLE;
        String userResponse = "";
//        String roomTypeRegex = "([ds])";
//        Pattern patter = Pattern.compile(roomTypeRegex);
        Scanner roomTypeScanner = new Scanner(System.in);


        while (keepRunning){
            try {
                String roomTypeRegex = "([ds])";
                Pattern pattern = Pattern.compile(roomTypeRegex.toLowerCase(Locale.ROOT));
                System.out.println("What kind of room would you like? D for 'Double' or S for 'Single':  ");
                userResponse = roomTypeScanner.nextLine();

                if (!pattern.matcher(userResponse).matches()){
                    throw  new IllegalArgumentException("Please enter D for 'Double'' or S for 'Single'");
                }
            }catch (Exception exception){
                System.out.println("I'm sorry, that input is invalid. ");
                continue;
            }finally {
                keepRunning = false;
            }

        }
        if (userResponse.equals("D".toLowerCase(Locale.ROOT))){
            roomType = RoomType.DOUBLE;
        }
        else {
            roomType = RoomType.DOUBLE;
        }
        return roomType;
    }

    private static Double getRoomPrice(){
        boolean keepRunning = true;
        Double roomPrice = 00.00;
//        String roomPriceRegex = "([0-9]+)\\.?([0-9]+)?";
        Scanner getRoomPriceScanner = new Scanner(System.in);

        while (keepRunning){
            try {
                String roomPriceRegex = "([0-9]+)\\.?([0-9]+)?";
                Pattern pattern = Pattern.compile(roomPriceRegex);
                System.out.println("How much is the price for this room per night? ");
                roomPrice = getRoomPriceScanner.nextDouble();
                if (!pattern.matcher(roomPrice.toString()).matches()){
                    throw new IllegalArgumentException("Unable to read input.");
                }


            }catch (Exception exception){
                System.out.println("Please enter the correct dollar amount with two decimal places, \nSuch as '00.00'");
                continue;
            }finally {
                System.out.println("Please enter the correct dollar amount with two decimal places");
            }
            keepRunning = false;
        }
        return roomPrice;
    }

    private static String validateInputNumber(){
        boolean validInput = false;
        boolean keepRunning = true;
        boolean roomIDExist = false;
        boolean isThereARoomInTheHashset = false;
        String userResponse = "";
        Scanner roomNumberScan = new Scanner(System.in);

        while (keepRunning){
            try {
                String roomNumberRegex = "([0-9]+)";
                Pattern pattern = Pattern.compile(roomNumberRegex);
                System.out.println("What is the room number for the room that you would like to create? ");
                userResponse = roomNumberScan.nextLine();
                if (!pattern.matcher(userResponse).matches()){
                    throw new IllegalArgumentException(("Not a valid number. "));

                }
                roomIDExist = confirmedIDNumbers(userResponse);

                if (roomIDExist){
                    throw new IllegalArgumentException("ID already exist, cannot enter duplicate IDs. ");
                }

                for (IRoom roomExistHashset: roomNotOccupiedHashSet) {
                    if (roomExistHashset.getRoomNumber().compareTo(userResponse) == 0){
                        throw new IllegalArgumentException("ID already exist, unable to create duplicate IDs. Please try again. ");
                    }
                }
            }catch (Exception exception){
                continue;

            }finally {
                System.out.println("Invalid data. ");
            }keepRunning = false;
        }
        return userResponse;
    }
    public static boolean confirmedIDNumbers(String userInput){
        if (hotelResourceInstance.getARoom(userInput) == null){
            return false;
        }
        return true;
    }

}




