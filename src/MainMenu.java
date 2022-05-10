import api.HotelResource;
import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


public class MainMenu {


    private static AdminResource adminResourceInstance = AdminResource.getInstance();
    private static HotelResource hotelResourceInstance = HotelResource.getInstance();
    private static Collection<Reservation> reservationHashSet = new HashSet<>();
    private static Collection<IRoom> roomNotOccupiedHashSet = new HashSet<>();
    private static Collection<Customer> customersHashset = new HashSet<>();



    private static MainMenu mainMenuInstance = null;
    private MainMenu() {}
    public static MainMenu getInstance(){
        if (null == mainMenuInstance){
            mainMenuInstance = new MainMenu();
        }
        return mainMenuInstance;
    }


    static Scanner mainMenuScanner = new Scanner(System.in);
//    String mainMenuScannerSwitch = mainMenuScanner.nextLine();


    public static void startMainMenu() {
       boolean keepRunning = true;
       AdminMenu adminResourceInstance = AdminMenu.getInstance();

        while (keepRunning) {

            try {
                System.out.println("Welcome to the one of the WORLD GREATEST HOTEL \n Please choose one of the options below \n ====================================\n");
                System.out.println("1. Find & Reserve a room. ");
                System.out.println("2. See my reservations. ");
                System.out.println("3. Create an account. ");
                System.out.println("4. Admin. ");
                System.out.println("5. Exit. ");
                int selection = Integer.parseInt(mainMenuScanner.nextLine());


                switch (selection){
                    case 1:
                        findAndReservedRoom();
                        break;
                    case 2:
                        seeMyReservation();
                        break;
                    case 3:
                        String email = getEmail();
                        String firstName = createAccountWithFullName("Enter your first name".toUpperCase());
                        String lastName = createAccountWithFullName("Enter your last name".toUpperCase());
                        hotelResourceInstance.createACustomer(email, firstName, lastName);
                        break;
                    case 4:
//                        AdminMenu adminResourceInstance = AdminMenu.getInstance();
                        adminResourceInstance.launchAdminMenu();
                        break;
                    case 5:
                        System.out.println("Exiting program");;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: \nPlease only enter between 1 - 5" + selection);
                }
            }catch (Exception exception){
                System.out.println("Error".toString());
            } finally {
                System.out.println("This is not really needed but I figured I'd put one. ");
            }


        }
    }


    public static void findAndReservedRoom(){
        Date checkInDate = getDate("Check In Date");
        Date checkOutDate = getDate("Check Out Date");

        roomNotOccupiedHashSet = hotelResourceInstance.findARoom(checkInDate, checkOutDate);

        if (roomNotOccupiedHashSet.isEmpty()){
            System.out.println("Sorry, we are unable to find any available rooms.");
        }
        for (IRoom room: roomNotOccupiedHashSet) {
            System.out.println("Number" + room + " has been reserve for you. " );
        }

        Scanner findReserveRoomScanner = new Scanner(System.in);

        boolean keepRunning = true;
        while (keepRunning){
            try{
                System.out.println("Would you like to book a room with us?\n If yes, please enter the room number ");
                String selection = findReserveRoomScanner.nextLine();

                if (selection.equals("y".toLowerCase(Locale.ROOT))){
                    bookRoomNow(checkInDate, checkOutDate);
                }else {
                    throw new IllegalArgumentException("Invalid input\n Please enter 'y' for yes or 'n' for no");
                }

            }
            catch (Exception exception){
                System.out.println("Error, invalid data");
            }

        }
    }


    public static void seeMyReservation(){


        String customerEmail = getEmail();
        reservationHashSet = hotelResourceInstance.getCustomerReservation(customerEmail);
        if (reservationHashSet.isEmpty()){
            System.out.println("Sorry, there are not reservation under this email: " + customerEmail);
        }else{
            for (Reservation customerReservation: reservationHashSet) {
                System.out.println(customerReservation);
            }
        }

    }

    public static void bookRoomNow(Date checkInDate, Date checkOutDate){

        boolean keepRunning = true;
        String email = "";
        Scanner bookRoomNowScanner = new Scanner(System.in);

        while (keepRunning){

            try{
                System.out.println("Are you a returning customer?\nDo you have an account?\nPlease enter 'y or n' ");
                String selection = bookRoomNowScanner.nextLine();

                if (selection.equals("y".toLowerCase(Locale.ROOT))){
                    email = getEmail();
                    Customer customer = hotelResourceInstance.getCustomer(email);
                    if(customer.equals(null)){
                        System.out.println(" We are unable to find that email. Are you sure this is correct? ");
                        throw new IllegalArgumentException("This email does not exist. ");
                    }

                    String roomNumber = getRoomNumber();
//                    hotelResourceInstance.getARoom(roomNumber);
                    IRoom room = (hotelResourceInstance.getARoom(roomNumber));
                    hotelResourceInstance.bookARoom(email, room, checkInDate, checkOutDate);
                    keepRunning = false;
                }else if (selection.equals("n".toLowerCase(Locale.ROOT))) {
                    keepRunning = false;
                }
            }catch (Exception exception){
                System.out.println("Error!!");
            }


        }

    }


    public static String getRoomNumber(){
        boolean keepRunning = true;
        String roomNumberRegex = "([0-9]+)";
        String roomNumberSelection = "";
        Pattern pattern = Pattern.compile(roomNumberRegex);
        Scanner getRoomNumberScanner = new Scanner(System.in);

        while (keepRunning){
            try{
                System.out.println("Which room would you like to reserved? ");
                roomNumberSelection = getRoomNumberScanner.nextLine();

                if (!pattern.matcher(roomNumberSelection).matches()){
                    System.out.println("We're sorry, it seems like you have entered invalid data, please try again.");
                    throw new IllegalArgumentException();
//                    The line below gives me an error, not sure why.
//                    keepRunning = false;
                }else {
                    //When I make the boolean into false in the else statement. It works but not in the if statement. Not sure why.
                    keepRunning = false;
                }

            }catch (Exception exception){
                System.out.println("Invalid data! ");
            } finally {
//                System.out.println("Wrong input! ");
            }

        }
        return roomNumberSelection;
    }

    public static String getEmail() {
        String customerEmailInput = "";
        String emailRegex = "^(.+)@(.+).com$";
        boolean keepRunning = true;
        Scanner getEmailScanner = new Scanner(System.in);
//        Pattern pattern = Pattern.compile(emailRegex);

        while (keepRunning) {
            try {
                Pattern pattern = Pattern.compile(emailRegex);
                System.out.println("Enter Email: Ex - JohnDoe@company.com\n");
                customerEmailInput = getEmailScanner.nextLine();
                if (!pattern.matcher(customerEmailInput).matches()) {
                    throw new IllegalArgumentException();

                }else {
                    keepRunning = false;
                }

            } catch (Exception exception) {
                System.out.println("I'm sorry, you have entered the wrong email format. Please try again.\n");
            } finally {
//                System.out.println(" Wrong input ");Commenting this out for debug
            }
        }

        return customerEmailInput;
    }
public static String createAccountWithFullName(String name){
        String fullNameInput = "";
        String nameRegex = "[A-Z]+([ '-]*[a-zA-Z]+)*";
        boolean keepRunning = true;
        Scanner fullNameScanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile(nameRegex);

        while (keepRunning){
            try{

                System.out.println(name);
                fullNameInput = fullNameScanner.nextLine();
                if(!pattern.matcher(fullNameInput).matches()){
                    throw new IllegalArgumentException();

                } else {
                    keepRunning = false;
                }
            }catch (Exception exception){
                System.out.println("Invalid Data!");
            }finally {
//                System.out.println("Sorry, wrong input."); Commenting this line for debugging
            }
        }
    return fullNameInput;
}

public static Date getDate(String date){
        boolean keepRunning = true;
        Scanner getDateScanner = new Scanner(System.in);
        Date dateInput = null;
//        date dateInput = ""; debug
        String whileLoopDate = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        lowercase mm means minute and not Month, lowercase dd is day and uppercase is the day of the year.

        while(keepRunning){
            System.out.println("Example: Month/Day/Year 03/19/2022");
            try {
                whileLoopDate = getDateScanner.nextLine();
                dateInput = dateFormat.parse(whileLoopDate);

            } catch (ParseException parseException) {
                parseException.printStackTrace();
                System.out.println("Invalid Date");
                continue;
            }
            keepRunning = false;
        }

    return dateInput;
}


}
