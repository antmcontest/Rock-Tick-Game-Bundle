/**
 * Start of the program
 * and will load straight
 * into the main console
 *
 * Anthony Contestabile
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

import static javax.swing.JOptionPane.*;


public class Login extends JPanel {
    //global arraylist to store user information
    static ArrayList<Users> userList = new ArrayList<>();

    //Initialize loginFrame
    static JFrame loginFrame = new JFrame("Login");

    private JButton loginButton;
    private static JLabel username;
    private static JLabel password;
    static JTextField usernameText;
    private JMenuBar loginMenuBar;
    private static JPasswordField passwordText;
    private JButton clearButton;
    private JLabel loginmenuLabel;
    private JButton registerButton;

    boolean succeeded = false;

    Login() {

        //construct preComponents
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem contact_usItem = new JMenuItem("Contact Us");
        helpMenu.add(contact_usItem);
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        //construct components
        loginButton = new JButton("Login");
        username = new JLabel("Username:");
        password = new JLabel("Password:");
        usernameText = new JTextField(15);
        loginMenuBar = new JMenuBar();
        loginMenuBar.add(fileMenu);
        loginMenuBar.add(helpMenu);
        passwordText = new JPasswordField(15);
        clearButton = new JButton("Clear");
        loginmenuLabel = new JLabel("Gooey Games' Console");
        registerButton = new JButton("Register");

        //adjust size and set layout
        setPreferredSize(new Dimension(329, 235));
        setLayout(null);

        //add components
        add(loginButton);
        add(username);
        add(password);
        add(usernameText);
        add(loginMenuBar);
        add(passwordText);
        add(clearButton);
        add(loginmenuLabel);
        add(registerButton);

        //set component bounds (only needed by Absolute Positioning)
        loginButton.setBounds(10, 180, 100, 20);
        username.setBounds(25, 95, 70, 25);
        password.setBounds(25, 130, 70, 25);
        usernameText.setBounds(95, 95, 140, 25);
        loginMenuBar.setBounds(0, 0, 335, 25);
        passwordText.setBounds(95, 135, 140, 25);
        clearButton.setBounds(220, 180, 100, 20);
        loginmenuLabel.setBounds(95, 30, 205, 55);
        registerButton.setBounds(115, 180, 100, 20);

        //adding action listeners
        loginButton.addActionListener(click -> {
            try {
                actionPerformed(click);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        clearButton.addActionListener(click -> {
            try {
                actionPerformed(click);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        registerButton.addActionListener(click -> {
            try {
                actionPerformed(click);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Overriding action listener for menu items
        //Exit
        exitItem.addActionListener(e -> System.exit(0));

        //Contact Us
        contact_usItem.addActionListener(e ->
                showMessageDialog(Login.this,
                "Gooey Games Inc.\n" +
                        "(636) 922 - 8000\n" +
                        "help@gooeygames.com",
                "Contact Us",
                INFORMATION_MESSAGE));

        //About / How to log in
        aboutItem.addActionListener(e ->
                showMessageDialog(Login.this,
                "If you are an existing user, please log in.\n" +
                        "If you are a new user, please register a new account.\n" +
                        "For help logging in, please contact the administrator.",
                "About",
                INFORMATION_MESSAGE));

    }

    //Begin Action Listener Functions
    public void actionPerformed(ActionEvent click) throws IOException {
        Object source = click.getSource();

        if (source == loginButton) {
            if(validUser(getUsername(), getPassword(), userList)){
                showMessageDialog(Login.this,
                        "Hi " + getUsername() + "! You have successfully logged in.",
                        "Login",
                        INFORMATION_MESSAGE);
                loginFrame.setVisible(false);
                new RockTickFrame();
                succeeded = true;
            } else {
                showMessageDialog(Login.this,
                        "Invalid username or password\n" +
                                "If you are a new user,\n" +
                                "please register first.",
                        "Login",
                        ERROR_MESSAGE);
                // reset username and password
                //usernameText.setText("");
                //passwordText.setText("");
                succeeded = false;
            }
        }

        if (source == clearButton) {
            usernameText.setText("");
            passwordText.setText("");
        }

        if (source == registerButton) {

               if(!validUser(getUsername(), userList)){
                   String newUsername = "";
                   String newPassword = "";
                   int newCoins;
                   rewardStatus newReward1;
                   rewardStatus newReward2;
                   rewardStatus newReward3;
                if(!getUsername().isEmpty()) {
                    newUsername = getUsername();
                }else{
                    showMessageDialog(Login.this,
                            "Username can not be blank.",
                            "Registration",
                            ERROR_MESSAGE);
                }

                if(!getPassword().isEmpty()) {
                    newPassword = getPassword();
                }else{
                    showMessageDialog(Login.this,
                            "Password can not be blank.",
                            "Registration",
                            ERROR_MESSAGE);
                }
                   newCoins = 0;
                   newReward1 = rewardStatus.locked;
                   newReward2 = rewardStatus.locked;
                   newReward3 = rewardStatus.locked;

                   if(!newUsername.equals("") && !newPassword.equals("")) {
                       Users newUser = new Users(newUsername, newPassword, newCoins, newReward1, newReward2, newReward3);
                       userList.add(newUser);
                       saveList(userList);

                       showMessageDialog(Login.this,
                               "Successfully registered user.\n" +
                                       "Please proceed to log in.",
                               "Registration",
                               INFORMATION_MESSAGE);

                       //USED FOR DEBUGGING FIXME
                       System.out.println("Success");
                   }else{
                       showMessageDialog(Login.this,
                               "Invalid user information. Username and password cannot be blank.",
                               "Registration",
                               ERROR_MESSAGE);
                   }
                }
               else {
                    showMessageDialog(Login.this,
                            "User already exists.",
                            "Registration",
                            ERROR_MESSAGE);
                }
        }
    }


    static void addCoins() throws IOException {
        int numCoins;
        for(Users user : userList){
            if(user.getUsername().equalsIgnoreCase(getUsername())){
                numCoins = user.getNumCoins();
                user.setNumCoins(numCoins + TicTacToe.coinsEarned());
                user.setNumCoins(numCoins + RockPaperScissors.coinsEarned());
                saveList(userList);
            }
            else{
                System.out.print("invalid action");
            }
        }
    }


    static int getCoins(){
        int numCoins = 0;
        for(Users user : userList){
            if(user.getUsername().equalsIgnoreCase(getUsername())){
                numCoins = user.getNumCoins();
            }
        }
        return numCoins;
    }


        static String getUsername() {
            return usernameText.getText().trim();
        }

        private static String getPassword() {
            return new String(passwordText.getPassword());
        }

/*        //Currently not used..
        public static void createFrame () {
            EventQueue.invokeLater(() -> {
                    loginFrame.setVisible(true);
                    usernameText.setText("");
                    passwordText.setText("");
            });
        }*/

        private static void loadUsers(ArrayList<Users> usersList) throws IOException {
            String username;
            String password;
            int numCoins;
            rewardStatus reward1;
            rewardStatus reward2;
            rewardStatus reward3;

            Path listPath = Paths.get("src\\users.txt");

            if(Files.exists(listPath)){
                File users = listPath.toFile();

                BufferedReader reader = new BufferedReader(new FileReader(users));
                String userData = reader.readLine();

                while(userData != null){
                    String[] properties = userData.split(",");

                    username = properties[0];
                    password = properties[1];
                    numCoins = Integer.parseInt(properties[2]);
                    switch (properties[3]){
                        case "unlocked":
                            reward1 = rewardStatus.unlocked;
                            break;
                        default:
                            reward1 = rewardStatus.locked;
                    }
                    switch (properties[4]){
                        case "unlocked":
                            reward2 = rewardStatus.unlocked;
                            break;
                        default:
                            reward2 = rewardStatus.locked;
                    }
                    switch (properties[5]){
                        case "unlocked":
                            reward3 = rewardStatus.unlocked;
                            break;
                        default:
                            reward3 = rewardStatus.locked;
                    }


                    Users userProperties = new Users(username, password, numCoins, reward1, reward2, reward3);

                    usersList.add(userProperties);

                    userData = reader.readLine();
                }

                System.out.println("User list loaded");
                reader.close();
            }
        }

    /**
     * This method is used when creating a new user
     * to check if a user is found on the list
     * and if not, create the new user
     *
     * @param username
     * @param userList
     * @return
     */
    public static boolean validUser(String username, ArrayList<Users> userList){
            boolean found = false;
            Users user;

            Iterator<Users> listItr = userList.listIterator();
            while(listItr.hasNext()) {
                user = listItr.next();
                if (user.getUsername().equalsIgnoreCase(username)) {
                    found = true;			// User is in list
                }
            }
            return found;
        }

    /**
     * This Method is used to
     * check if username and password
     * are valid and if so log in.
     *
     * @param username
     * @param password
     * @param userList
     * @return
     */
        public static boolean validUser(String username, String password, ArrayList<Users> userList){
            boolean found = false;
            Users user;

            Iterator<Users> listItr = userList.listIterator();
            while(listItr.hasNext()) {
                user = listItr.next();
                if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                    found = true;			// User is in list
                }
            }
            return found;
        }

    /**
     * Method to save the ArrayList back to the file
     * @param userList
     * @throws IOException
     */
    public static void saveList(ArrayList<Users> userList) throws IOException {

        Path listPath = Paths.get("src\\users.txt");

        if (Files.exists(listPath)) {
            File users = listPath.toFile();

            FileWriter writer = new FileWriter(users);

            for (int i = 0; i < userList.size(); i++) {
                String newUserList = userList.get(i).toString();
                writer.write(newUserList);
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        }else{
            throw new IOException("File not found");
        }
    }


        public static void main (String[]args) throws IOException {
            loadUsers(userList);

            for(Users data : userList){
                //printUser is defined in Users.java
                //printing for debugging
                data.printUser();
            }

            loginFrame.setResizable(false);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.getContentPane().add(new Login());
            loginFrame.setLocation(350, 260);
            loginFrame.pack();
            loginFrame.setVisible(true);
        }
    }
