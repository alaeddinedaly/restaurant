package presentation.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;




public class LoginView extends JFrame{

   private BufferedImage image;
   private JPanel leftPanel;

   private JPanel rightPanel;

   private JPanel imagePanel;
   private BufferedImage icon;
   private JPanel infoPanel;

   private JLabel titleLabel;

   private JPanel accessPanel;

   private JPanel emailPanel;
   private JLabel emailLabel;
   private JTextField emailField;

   private JPanel passwordPanel;
   private JLabel passwordLabel;
   private JPasswordField passwordField;
   
   private JButton loginButton;

   public LoginView(){
      
      this.setLayout(new BorderLayout());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      this.setLocation(40,80);
      this.setSize(1000,700);
      this.setResizable(false);

      this.leftPanel = new JPanel(new GridLayout());
      this.leftPanel.setSize(200,200);
      try{
         image = ImageIO.read(new File("images/hez.PNG"));
         this.leftPanel.add(new JLabel(new ImageIcon(image)));
      } catch(IOException e){
         e.printStackTrace();
      }

      this.rightPanel = new JPanel(new BorderLayout());

      this.imagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      this.imagePanel.setBackground(new Color(28,10,1));
      this.imagePanel.setOpaque(true);
      try{
         icon = ImageIO.read(new File("images/restau.PNG"));
         this.imagePanel.add(new JLabel(new ImageIcon(icon)));
      } catch(IOException e){
         e.printStackTrace();
      }

      this.rightPanel.add(this.imagePanel, BorderLayout.NORTH);

      this.infoPanel = new JPanel(new BorderLayout());
      this.infoPanel.setBackground(new Color(28,10,1));
      this.infoPanel.setOpaque(true);

      this.titleLabel = new JLabel("Restaurant - Login");
      this.titleLabel.setFont(this.titleLabel.getFont().deriveFont(24f));
      this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
      this.titleLabel.setForeground(new Color(255,161,64));
      this.infoPanel.add(this.titleLabel, BorderLayout.NORTH);
      
      this.emailPanel = new JPanel(new GridLayout(1,2));
      this.emailPanel.setBackground(new Color(28,10,1));
      this.emailPanel.setOpaque(true);
      this.emailLabel = new JLabel("        Email : ");
      this.emailLabel.setFont(this.emailLabel.getFont().deriveFont(24f));
      this.emailLabel.setForeground(new Color(255,161,64));
      this.emailField = new JTextField();
      this.emailField.setForeground(Color.BLACK);
      this.emailField.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(new Color(28,10,1)),
         BorderFactory.createEmptyBorder(5, 10, 5, 10) 
   ));
      this.emailField.setBackground(new Color(131,79,42));
      this.emailPanel.add(this.emailLabel);
      this.emailPanel.add(this.emailField);

      this.passwordPanel = new JPanel(new GridLayout(1,2));
      this.passwordPanel.setBackground(new Color(28,10,1));
      this.passwordPanel.setOpaque(true);
      this.passwordLabel = new JLabel("        Password : ");
      this.passwordLabel.setFont(this.passwordLabel.getFont().deriveFont(24f));
      this.passwordLabel.setForeground(new Color(255,161,64));
      this.passwordField = new JPasswordField();
      this.passwordField.setForeground(Color.BLACK);
      this.passwordField.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(new Color(28,10,1)),
         BorderFactory.createEmptyBorder(5, 10, 5, 10) 
   ));
      this.passwordField.setBackground(new Color(131,79,42));
      this.passwordPanel.add(this.passwordLabel);
      this.passwordPanel.add(this.passwordField);

      this.accessPanel = new JPanel(new GridLayout(2,1,10,10));
      this.accessPanel.add(this.emailPanel);
      this.accessPanel.add(this.passwordPanel);
      this.accessPanel.setBackground(new Color(28,10,1));
      this.accessPanel.setOpaque(true);

      JPanel centerPanel = new JPanel(new GridBagLayout());
      centerPanel.setBackground(new Color(28,10,1));
      centerPanel.setOpaque(true);
      centerPanel.add(this.accessPanel);

      this.infoPanel.add(centerPanel, BorderLayout.CENTER);

      this.loginButton = new JButton("Login");
      this.loginButton.setBackground(new Color(131,79,42));
      this.loginButton.setForeground(new Color(255,161,64));
      this.loginButton.setBorder(BorderFactory.createCompoundBorder(
         BorderFactory.createLineBorder(new Color(28,10,1)),
         BorderFactory.createEmptyBorder(5, 10, 5, 10) 
      ));
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      buttonPanel.add(this.loginButton);
      buttonPanel.setBackground(new Color(28,10,1));
      buttonPanel.setOpaque(true);

      this.infoPanel.add(buttonPanel, BorderLayout.SOUTH);

      this.rightPanel.add(this.infoPanel, BorderLayout.CENTER);

      this.add(this.leftPanel, BorderLayout.WEST);
      this.add(this.rightPanel, BorderLayout.CENTER);
      

      this.setVisible(true);
   }

   public JButton getLoginButton(){
      return loginButton;
   }

   public String getEmail(){
      return emailField.getText();
   }

   public String getPassword(){
      return String.valueOf(passwordField.getPassword());
   }

   public void paintComponent(Graphics g){
      super.paintComponents(g);
      if(image != null){
         g.drawImage(image, 0, 0, null);
      }
      if(icon != null){
         g.drawImage(icon, 0, 0, null);
      }
   }
}