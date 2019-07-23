/**
 * This is subject to change
 * just a basic layout to get started
 * Need to get buttons working
 *
 * Anthony Contestabile
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;


public class CurrencyShop extends JPanel {
    private JButton usernameChangeButton;
    private JButton passwordChangeButton;
    private JButton unlockDarkThemeButton;
    private JButton unlockLightThemeButton;
    private JButton unlockUSAThemeButton;
    private JButton reward6Button;

    private JLabel mainShopLabel;
    private JLabel usernameChangeLabel;
    private JLabel passwordChangeLabel;
    private JLabel reward3Label;
    private JLabel reward4Label;
    private JLabel reward5Label;
    private JLabel reward6Label;
    private JLabel nameAndCurrencyLabel;
    private JLabel currencyLabel;

    private JMenuBar menu;


    private Font shopFont = new Font("Ravie", Font.BOLD, 16);
    private Font titleFont = new Font("Arial", Font.BOLD, 36);


    public CurrencyShop() throws IOException {

        //construct preComponents
        JMenu fileMenu = new JMenu ("File");
        JMenuItem aboutItem = new JMenuItem ("About");
        fileMenu.add (aboutItem);
        JMenuItem exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);

        setBackground(Color.DARK_GRAY);
        //setFont(shopFont);

        //construct components
        //Buttons
        usernameChangeButton = new JButton ("100 Coins");
        passwordChangeButton = new JButton ("Free");
        unlockDarkThemeButton = new JButton ("500 Coins");// Just for Example
        unlockLightThemeButton = new JButton ("500 Coins");
        unlockUSAThemeButton = new JButton ("350 Coins");
        reward6Button = new JButton ("1000 Coins");

        //Button Colors
        usernameChangeButton.setBackground(Color.WHITE);
        passwordChangeButton.setBackground(Color.WHITE);
        unlockDarkThemeButton.setBackground(Color.WHITE);
        unlockLightThemeButton.setBackground(Color.WHITE);
        unlockUSAThemeButton.setBackground(Color.WHITE);
        reward6Button.setBackground(Color.WHITE);

        //Menu
        menu = new JMenuBar();
        menu.add (fileMenu);



        //Labels
        mainShopLabel = new JLabel ("Gooey Games Currency Shop");
        usernameChangeLabel = new JLabel ("Username Change -->");
        passwordChangeLabel = new JLabel ("Password Change -->");
        reward3Label = new JLabel ("Dark Theme -->");
        reward4Label = new JLabel ("Light Theme -->");
        reward5Label = new JLabel ("Mystery Theme -->");
        reward6Label = new JLabel ("Secret Game -->");
        nameAndCurrencyLabel = new JLabel();
        currencyLabel = new JLabel();

        //Changing colors
        usernameChangeLabel.setForeground(Color.GREEN);
        passwordChangeLabel.setForeground(Color.GREEN);
        reward3Label.setForeground(Color.GREEN);
        reward4Label.setForeground(Color.GREEN);
        reward5Label.setForeground(Color.GREEN);
        reward6Label.setForeground(Color.GREEN);
        mainShopLabel.setForeground(Color.GREEN);
        nameAndCurrencyLabel.setForeground(Color.GREEN);
        currencyLabel.setForeground(Color.green);

        //adjust size and set layout
        setPreferredSize (new Dimension (915, 489));
        setLayout (null);

        //add components
        add (usernameChangeButton);
        add (passwordChangeButton);
        add (mainShopLabel);
        add (usernameChangeLabel);
        add (passwordChangeLabel);
        add (unlockDarkThemeButton);
        add (unlockLightThemeButton);
        add (reward3Label);
        add (reward4Label);
        add (unlockUSAThemeButton);
        add (reward6Button);
        add (reward5Label);
        add (reward6Label);
        add (menu);
        add (nameAndCurrencyLabel);
        add (currencyLabel);

        //set component bounds (only needed by Absolute Positioning)
        usernameChangeButton.setBounds (250, 175, 150, 75);
        passwordChangeButton.setBounds (650, 175, 150, 75);
        unlockDarkThemeButton.setBounds (250, 275, 150, 75);
        unlockLightThemeButton.setBounds (650, 275, 150, 75);
        unlockUSAThemeButton.setBounds (250, 375, 150, 75);
        reward6Button.setBounds (650, 375, 150, 75);
        mainShopLabel.setBounds (200, 50, 600, 60);
        usernameChangeLabel.setBounds (45, 200, 200, 25);
        passwordChangeLabel.setBounds (450, 200, 200, 25);
        reward3Label.setBounds (45, 300, 150, 25);
        reward4Label.setBounds (450, 300, 150, 25);
        reward5Label.setBounds (45, 400, 150, 25);
        reward6Label.setBounds (450, 400, 150, 25);
        menu.setBounds (0, 0, 30, 35);
        nameAndCurrencyLabel.setBounds (670, 0, 275, 45);
        currencyLabel.setBounds (670, 25, 275, 45);

        //Setting fonts
        usernameChangeLabel.setFont(shopFont);
        passwordChangeLabel.setFont(shopFont);
        reward3Label.setFont(shopFont);
        reward4Label.setFont(shopFont);
        reward5Label.setFont(shopFont);
        reward6Label.setFont(shopFont);
        mainShopLabel.setFont(titleFont);
        nameAndCurrencyLabel.setFont(shopFont);
        currencyLabel.setFont(shopFont);


        //ADDING ACTION LISTENERS
        ActionListener updateUserAndCoins = evt ->{
                nameAndCurrencyLabel.setText("User: " + Login.getUsername());
                currencyLabel.setText("Coins: " + Login.getCoins());
        };
        Timer usernameTimer = new Timer(100 ,updateUserAndCoins); // Execute task each 100 miliseconds
        usernameTimer.setRepeats(true);
        usernameTimer.start();

        usernameChangeButton.addActionListener(click -> {
            try {
                actionPerformed(click);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        passwordChangeButton.addActionListener(click -> {
            try {
                actionPerformed(click);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        unlockLightThemeButton.addActionListener(click-> {
            try{
                actionPerformed(click);
            } catch(IOException e){
                e.printStackTrace();
            }
        });
        unlockDarkThemeButton.addActionListener(click-> {
            try{
                actionPerformed(click);
            } catch(IOException e){
                e.printStackTrace();
            }
        });
        unlockUSAThemeButton.addActionListener(click-> {
            try{
                actionPerformed(click);
            } catch(IOException e){
                e.printStackTrace();
            }
        });


    }

    static void createFrame() { //used to instantiate the currency shop from main program console
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame ("Gooey Games Currency Shop");
            frame.setLocation(350, 260);
            frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            try {
                frame.getContentPane().add (new CurrencyShop());
            } catch (IOException e) {
                e.printStackTrace();
            }
            frame.pack();
            frame.setVisible (true);
        });
    }

    private void actionPerformed(ActionEvent click) throws IOException {
        Object source = click.getSource();

        if (source == passwordChangeButton) {
            String newPassword;
            int option;
            newPassword = getInput("Enter new password:", null);
            for (Users user : Login.userList) {
                if (user.getUsername().equals(Login.getUsername())) {

                    if (newPassword == null) {}
                    if (newPassword == "") {
                        showMessage("Invalid username");
                    }
                    if (newPassword.equals(user.getPassword())) {
                        showMessage("Password must be different than previous password.");
                    }
                    else if(!newPassword.equals(user.getPassword())){
                        option = askMessage("Are you sure you would like\n" +
                                " to change your Password?", "Password Change", JOptionPane.YES_NO_OPTION);
                        if(option == JOptionPane.YES_OPTION){
                            user.setPassword(newPassword);
                            System.out.println("success password change");
                            Login.saveList(Login.userList);
                            showMessage("Password changed.");
                        }
                    }
                }
            }
        }

        else if (source == usernameChangeButton) {
            String newUsername;
            int option;
            newUsername = getInput("Enter new username:", Login.getUsername());
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.getNumCoins() >= 100) {
                        if (newUsername == null) {}
                        else if (newUsername == "") {
                            showMessage("Invalid username");
                        }
                        else if (newUsername.equalsIgnoreCase(user.getUsername())) {
                            option = askMessage("New username is equal to old username." +
                                    "\nare you sure you would like to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                Login.usernameText.setText(newUsername);
                                user.setUsername(newUsername);
                                user.setNumCoins(user.getNumCoins() - 100);
                                Login.saveList(Login.userList);
                                showMessage("Username changed.");
                            }
                        }
                        else if(!newUsername.equals(user.getUsername())){
                            option = askMessage("Are you sure you would like\n" +
                                    " to change your username?", "Username Change", JOptionPane.YES_NO_OPTION);
                            if(option == JOptionPane.YES_OPTION){
                                Login.usernameText.setText(newUsername);
                                user.setUsername(newUsername);
                                user.setNumCoins(user.getNumCoins() - 100);
                                Login.saveList(Login.userList);
                                showMessage("Username changed.");
                            }
                        }
                    }
                    else{
                        showMessage("Not Enough Coins.");
                    }
                }
            }
        }
        else if(source == unlockDarkThemeButton){
            int option;
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.isLightThemeUnlocked().equals(rewardStatus.locked)) {
                        if (user.getNumCoins() >= 500) {
                            option = askMessage("Are you sure you would like\n" +
                                    " to change your username?", "Username Change", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                user.setDarkThemeUnlocked(rewardStatus.unlocked);
                                user.setNumCoins(user.getNumCoins() - 500);
                                Login.saveList(Login.userList);
                                showMessage("Dark Theme Unlocked");
                                unlockDarkThemeButton.setEnabled(false);
                            }
                        } else {
                            showMessage("Not Enough Coins.");
                        }
                    }else{
                        showMessage("You have already unlocked this.");
                        unlockDarkThemeButton.setEnabled(false);
                    }
                }
            }

        }
        else if(source == unlockLightThemeButton){
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.isLightThemeUnlocked().equals(rewardStatus.locked)){
                        if (user.getNumCoins() >= 500) {
                            user.setLightThemeUnlocked(rewardStatus.unlocked);
                            user.setNumCoins(user.getNumCoins() - 500);
                            Login.saveList(Login.userList);
                            showMessage("Light Theme Unlocked");
                            unlockLightThemeButton.setEnabled(false);
                        } else {
                            showMessage("Not Enough Coins.");
                        }
                    }else{
                        showMessage("You have already unlocked this.");
                        unlockLightThemeButton.setEnabled(false);
                    }
                }
            }
        }
        else if(source == unlockUSAThemeButton){
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.isUSAThemeUnlocked().equals(rewardStatus.locked)){
                        if (user.getNumCoins() >= 350) {
                            user.setUSAThemeUnlocked(rewardStatus.unlocked);
                            user.setNumCoins(user.getNumCoins() - 350);
                            Login.saveList(Login.userList);
                            showMessage("Secret Theme Unlocked");
                            unlockUSAThemeButton.setEnabled(false);
                        } else {
                            showMessage("Not Enough Coins.");
                        }
                    }else{
                        showMessage("You have already unlocked this.");
                        unlockUSAThemeButton.setEnabled(false);
                    }
                }
            }
        }
        Login.saveList(Login.userList);
    }

    private int askMessage(String msg, String tle, int op)	{
        return JOptionPane.showConfirmDialog(null, msg, tle, op);
    }
    private String getInput(String msg, String setText)	{
        return JOptionPane.showInputDialog(null, msg, setText);
    }
    private void showMessage(String msg)	{
        JOptionPane.showMessageDialog(null, msg);
    }

/*    //FOR TESTING PURPOSES, DELETE LATER
    //FIXME
    public static void main (String[] args) throws IOException {
        JFrame frame = new JFrame ("Gooey Games Currency Shop");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add (new CurrencyShop());
        frame.pack();
        frame.setVisible (true);
    }*/
}