
import javax.swing.JOptionPane;

// ┌───────────────────────────────┐
// │                               │
// │   The messaging application   │
// │                               │
// │   Mehdi Mortazavian           │
// │                               │
// │   HomeWork2                   │
// │                               │
// │   Advance Programming         │
// │                               │
// └───────────────────────────────┘

public class Main {

    // Decide if the app is running or not
    static boolean appIsRunning = true;

    public static void main(String[] args) {

        User currentUser;

        // Create 3 instance of user class
        User.instanceCreator();

        System.out.println("------------------------------------------");

        while (appIsRunning) {

            JOptionPane.showMessageDialog(null, "Welcome to my stupid messager app");
            int userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "1.Sign-Up\n2.Sing-In"));
            switch (userInput) {
                case 1: // Sign-In
                    User.signUp();
                    currentUser = DataBase.users.get(DataBase.users.size() - 1);
                    // testing
                    // System.out.println(currentUser.getName());

                    break;

                case 2: // Log-In
                    currentUser = DataBase.users.get(User.logIn());
                    // System.out.println("son of a bitch");
                    // System.out.println(currentUser.getName());
                    // currentUser = DataBase.users.get(User.logInIndex());
                    homePage(currentUser);
                    break;

                case 3: // Exit
                    appIsRunning = false;
                    break;

                default:
                    break;
            }

        }

        // Pages:
        // 1.Home Page
        // 2.Chat page
        // 3.Search page
        // 4.Profile

    }

    // Contain of Chat-Page, User-Page, Search-Page and Log-Out Option.

    public static void homePage(User currentUser) {

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "this is the home page of the Application\n1.Chat-Page\n2.User-Page\n3.Search-Page\n4.Log-Out",
                "Home Page",
                JOptionPane.DEFAULT_OPTION));

        switch (userInput) {
            case 1: // Chat-Page
                User.chatPage(currentUser);
                break;

            case 2: // User-Page
                User.userPage(currentUser);
                DataBase.users.get(0);
                break;

            case 3: // Search-Page
                User.searchPage(currentUser);
                break;

            case 4: // Log-Out
                appIsRunning = false;
                break;

            default:
                break;
        }

    }

}
