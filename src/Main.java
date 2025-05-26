import dao.UserDao;
import javax.swing.UIManager;
import presentation.controller.LoginController;
import presentation.vue.LoginView;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginView loginView = new LoginView();
        new LoginController(loginView, new UserDao());

   }
}
