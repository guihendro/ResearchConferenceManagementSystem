package helloworld;

import javax.swing.*;

public class LogoutController {
    User u = new User();
    public void Logout(JFrame frame, JButton btnLogout){
        u.Logout(frame,btnLogout);
    }
}
