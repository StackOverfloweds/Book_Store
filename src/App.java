import gui.LoginView;
import utils.DatabaseInitializer;

public class App {
    public static void main(String[] args) {
        try {
            // Inisialisasi database
            DatabaseInitializer.initializeDB();

            // Jalankan LoginView
            new LoginView();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
