package presentation.controller;


import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import presentation.model.User;
import presentation.vue.*;
import presentation.vue.admin.AdminDashboard;
import presentation.vue.chef.ChefDashboard;
import presentation.vue.waiter.WaiterDashboard;

public class LoginController implements ActionListener {

    private LoginView view;
    private UserDao userDao;


    
    public LoginController(LoginView view, UserDao userDao) {
        this.view = view;
        this.userDao = userDao;
        this.view.getLoginButton().addActionListener(this);
    }
    
    
    private void handleLogin() {
        String email = view.getEmail();
        String password = view.getPassword();
           
        User user = userDao.authenticate(email, password); // Assuming name and lastLogin are not needed for authentication
        if (user != null) {
            redirectBasedOnRole(user);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Invalid email or password", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void redirectBasedOnRole(User user) {

        UserDao userDao = new UserDao();

        switch (user.getRole()) {
            case "admin":
            AdminDashboard adminView = new AdminDashboard();
            new AdminDashboardController(adminView, userDao);
            adminView.setVisible(true);
            break;

            case "waiter":
            WaiterDashboard waiterView = new WaiterDashboard();
            new WaiterDashboardController(waiterView);
            waiterView.setVisible(true);
            break;

            case "chef":
            ChefDashboard chefView = new ChefDashboard();
            new ChefDashboardController(chefView);
            chefView.setVisible(true);
            break;
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getLoginButton()) {
            handleLogin();
        }
    }
}