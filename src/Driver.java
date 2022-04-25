public class Driver {

    public static void main(String[] args) {
        try {
            MainMenu mainMenuInstance = MainMenu.getInstance();
            mainMenuInstance.startMainMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
