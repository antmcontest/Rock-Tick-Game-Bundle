import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class RockTickFrame implements ActionListener {
    private JLabel loginLabel;
    private JLabel currencyLabel;
    private JTextField loginField;
    private JTextField currencyField;
    private JButton RPSButton;
    private JButton TTTButton;
    private JButton settingsButton;
    private JButton tutorialButton;
    private JButton  exitButton;
    private JButton cashShopButton;
    private JButton hiddenGameButton;


    static JFrame frame = new JFrame ("Gooey Games Console");

    RockTickFrame() {

        // Specify GUI layout
        GridBagConstraints layoutConst;

        //  Set labels for login id and digital currency fields
        Font myFont = new Font("Calibri", Font.BOLD, 18);
        loginLabel = new JLabel("User Login: ");
        loginLabel.setFont(myFont);
        loginLabel.setForeground(Color.WHITE);
        currencyLabel = new JLabel("Digital Currency: ");
        currencyLabel.setFont(myFont);
        currencyLabel.setForeground(Color.WHITE);

        //  set up user login field which is NOT editable
        loginField = new JTextField(14);
        loginField.setEditable(false);
        loginField.setBackground(Color.BLACK);
        ActionListener updateUsername = evt -> loginField.setText(getUsername());
        Timer usernameTimer = new Timer(100 ,updateUsername); // Execute task each 100 miliseconds
        usernameTimer.setRepeats(true);
        usernameTimer.start();


        loginField.setForeground(Color.white);

        //  set up currency field which is NOT editable
        currencyField = new JTextField(14);
        currencyField.setEditable(false);
        currencyField.setBackground(Color.BLACK);
        currencyField.setForeground(Color.WHITE);

        //This method will update the coin total every 100 milliseconds
        ActionListener updateCoins = evt -> currencyField.setText(String.valueOf(Login.getCoins()));
        Timer timer = new Timer(100 ,updateCoins); // Execute task each 100 miliseconds
        timer.setRepeats(true);
        timer.start();


        // set up icon photos
        Icon RPS = new ImageIcon(getClass().getResource("rock-paper-scissors.jpg"));
        Icon TTT = new ImageIcon(getClass().getResource("BBTicTacToe.jpg"));
        Icon Coins = new ImageIcon(getClass().getResource("coins.jpg"));
        Icon Games = new ImageIcon(getClass().getResource("games.jpg"));
        Icon Exit = new ImageIcon(getClass().getResource("exit.jpg"));

        //  set up main screen buttons as well as their action listeners

        RPSButton = new JButton("Play Rock-Paper-Scissors", RPS);
        RPSButton.setPreferredSize(new Dimension(250, 150));
        RPSButton.setBackground(Color.BLACK);
        RPSButton.setForeground(Color.WHITE);
        RPSButton.addActionListener(this);

        TTTButton = new JButton("Play Tic-Tac-Toe",TTT);
        TTTButton.setPreferredSize(new Dimension(250, 150));
        TTTButton.setBackground(Color.BLACK);
        TTTButton.setForeground(Color.WHITE);
        TTTButton.addActionListener(this);

        settingsButton = new JButton("Settings");
        settingsButton.setBackground(Color.BLACK);
        settingsButton.setForeground(Color.WHITE);
        settingsButton.addActionListener(this);

        tutorialButton = new JButton("Tutorial");
        tutorialButton.setBackground(Color.BLACK);
        tutorialButton.setForeground(Color.WHITE);
        tutorialButton.addActionListener(this);

        exitButton = new JButton("Exit", Exit);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(this);

        cashShopButton = new JButton("Currency Shop", Coins);
        cashShopButton.setBackground(Color.BLACK);
        cashShopButton.setForeground(Color.WHITE);
        cashShopButton.addActionListener(this);

        hiddenGameButton = new JButton("Hidden Game", Games);
        hiddenGameButton.setBackground(Color.BLACK);
        hiddenGameButton.setForeground(Color.WHITE);
        hiddenGameButton.addActionListener(this);


        // set up layout for fields and buttons
        frame.setLayout(new GridBagLayout());

        // label for login name display
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        frame.add(loginLabel, layoutConst);

        //  user name display field
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        frame.add(loginField, layoutConst);

        //  label for digital currency display
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        frame.add(currencyLabel, layoutConst);

        //  field for displaying current digital currency
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        frame.add(currencyField, layoutConst);

        //  button to play Rock-Paper-Scissors
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        frame.add(RPSButton, layoutConst);

        //  button to play Tic-Tac-Toe
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 2;
        layoutConst.gridy = 2;
        frame.add(TTTButton, layoutConst);

        //  button to go to the currency shop
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        frame.add(cashShopButton, layoutConst);

        //  button to go to the hidden game - currently just a teaser
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 2;
        layoutConst.gridy = 3;
        frame.add(hiddenGameButton, layoutConst);

        //  button to go to the settings menu
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        frame.add(settingsButton, layoutConst);

        //  button to go to the tutorial page
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        frame.add(tutorialButton, layoutConst);

        //  exit button from the main console
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(20, 20, 20, 20);
        layoutConst.fill = GridBagConstraints.HORIZONTAL;
        layoutConst.gridx = 2;
        layoutConst.gridy = 4;
        frame.add(exitButton, layoutConst);

        // set frame size as well as default options to bring up main console
        frame.setPreferredSize(new Dimension(750,450));

        GG_Settings.setSoundStatus("Off");			//  sound defaults to OFF

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.pack();
        frame.setVisible(true);


    }

    public void createFrame() { //used to instantiate main console after login
        EventQueue.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(Color.BLUE);
            frame.pack();
            frame.setVisible(true);
            GG_Settings.setSoundStatus("Off");
        });
    }

    //  get the user login name to display on console
    public String getUsername(){
        return Login.getUsername();
    }

    //  override actionPerformed for buttons on main console
    @Override
    public void actionPerformed(ActionEvent event)  {

        String actionCommand;
        String soundStatus;

        //  get action which sent us to this method
        actionCommand = event.getActionCommand();


        if (actionCommand.equals("Play Rock-Paper-Scissors")) {
            //  check to see if sound is on, and if it is, play tada sound
            //  could be updated later to choose a particular sound
            soundStatus = GG_Settings.getSoundStatus();
            System.out.println("soundStatus: " + soundStatus);
            if (soundStatus.equals("On")) {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src//tada.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                    Thread.sleep(clip.getMicrosecondLength()/1000);

                }catch(Exception x) { x.printStackTrace(); }
            }
            RockPaperScissors.createFrame();
            System.out.println("Go to Rock-Paper-Scissors");
        }

        else if (actionCommand.equals("Play Tic-Tac-Toe")) {
            //  check to see if sound is on, and if it is, play tada sound
            //  could be updated later to choose a particular sound
            soundStatus = GG_Settings.getSoundStatus();
            if (soundStatus.equals("On")) {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src//tada.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                    Thread.sleep(clip.getMicrosecondLength()/1000);

                }catch(Exception x) { x.printStackTrace(); }
            }
            TicTacToe.createFrame();
        }

        else if (actionCommand.equals("Settings")) {
            //  check to see if sound is on, and if it is, play tada sound
            //  could be updated later to choose a particular sound
            soundStatus = GG_Settings.getSoundStatus();
            if (soundStatus.equals("On")) {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src//tada.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                    Thread.sleep(clip.getMicrosecondLength()/1000);

                }catch(Exception x) { x.printStackTrace(); }
            }
            GG_Settings.createFrame();
        }

        else if (actionCommand.equals("Tutorial")) {
            //  check to see if sound is on, and if it is, play tada sound
            //  could be updated later to choose a particular sound
            soundStatus = GG_Settings.getSoundStatus();
            if (soundStatus.equals("On")) {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src//tada.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                    Thread.sleep(clip.getMicrosecondLength()/1000);

                }catch(Exception x) { x.printStackTrace(); }
            }
            Tutorial.createGUI();
        }

        else if (actionCommand.equals("Exit")) {
            // exit Gooey Games console
            System.exit(0);
        }

        else if (actionCommand.equals("Currency Shop")) {
            //  check to see if sound is on, and if it is, play tada sound
            //  could be updated later to choose a particular sound
            soundStatus = GG_Settings.getSoundStatus();
            if (soundStatus.equals("On")) {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src//tada.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                    Thread.sleep(clip.getMicrosecondLength()/1000);

                }catch(Exception x) { x.printStackTrace(); }
            }
            CurrencyShop.createFrame();
            //new CurrencyShop();
        }

        else if (actionCommand.equals("Hidden Game")) {
            showMessageDialog(null,
                    "Hidden game coming soon.  Check back soon!");

        }
        else {
            JOptionPane.showMessageDialog(frame, "Invalid data entered.");
        }

    }

    /*public static void main(String... args) {
        new RockTickFrame();
    }*/


}