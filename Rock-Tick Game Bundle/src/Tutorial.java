/**@author Rachel Gruber*/
/**@created July 6 , 2019 To provide a tutorial to explain how to play the RockTick Game package*/

/**import all parent classes to use*/
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Tutorial {
    /**main class
     * set up and initialize java built in classes to use*/
    static JFrame tutorialFrame = new JFrame();
    static Container con;  /**container to support JPanel*/

    static JPanel titlePanel;
    static JPanel textexplanation;
    static JPanel buttonPanel;
    static JPanel MenuOpPanel;
    static JLabel titleLabel1;
    static JLabel MenuOpLabel;

    /**create font types to use*/
    static Font titlefont = new Font("Serif", Font.BOLD, 50);
    static Font buttonFont = new Font("Arial Rounded MT Bold",Font.PLAIN, 15);
    static Font textfont = new Font("Times New Roman",Font.PLAIN, 24);
    static JTextArea textExplainContent;
    static JButton buttonAbout;
    static JButton buttonPlayGames;
    static JButton buttonEarnCur;
    static JButton buttonUseCur;
    static JButton buttonQuit;

    /**initialize class ButtonHandler so screen text content can switch when a button is pushed
     * class contains ActionListener*/
    static ButtonHandler buttonchoice = new ButtonHandler();

    public static void createGUI() {
        /**constructor of GuI
         * main Graphic user interface set up */
        /**set TTTframe*/
        tutorialFrame.setTitle("Gooey Games Tutorial");
        tutorialFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        /**tutorialFrame.pack();(not used)*/
        tutorialFrame.setSize(850,625);
        tutorialFrame.getContentPane().setBackground(Color.BLUE);
        /**tutorialFrame.setLayout(new GridBagLayout());*/
        tutorialFrame.setLayout(null);
        /**link container to JFrame*/
        con= tutorialFrame.getContentPane();

        /**title Panel*/
        /**space for title text  x-axis 100, y 100, width 600, height 150*/
        titlePanel = new JPanel();
        titlePanel.setBounds(250,0,350,150);
        /**Change to Blue*/
        titlePanel.setBackground(Color.blue);
        con.add(titlePanel); /**container place panel*/

        /**label on title Panel and settings of all its attributes*/
        titleLabel1 = new JLabel("<html>GG Tutorial<br/> (How to Play)<html>", SwingConstants.CENTER);
        titleLabel1.setBounds(250, 0,350, 150);
        titleLabel1.setBackground(Color.blue);
        titleLabel1.setForeground(Color.white);
        titleLabel1.setFont(titlefont);
        titleLabel1.setVisible(true);
        titlePanel.add(titleLabel1);

        /*text area to provide explanation to button click
         * set up panel to contain text
         * set up JTextArea**/

        textexplanation = new JPanel();
        textexplanation.setBounds(200,200,600,400);
        textexplanation.setBackground(Color.blue);
        con.add(textexplanation);

        /**main text area*/
        textExplainContent = new JTextArea();
        textExplainContent.setBounds(200,200,600,400);
        textExplainContent.setBackground(Color.blue);
        textExplainContent.setForeground(Color.white);
        textExplainContent.setFont(textfont);
        textExplainContent.setLineWrap(true);
        textexplanation.add(textExplainContent);

        /**Menu Options Label*/
        MenuOpPanel = new JPanel();
        MenuOpPanel.setBounds(0,125,200,75);
        MenuOpPanel.setBackground(Color.blue);
        con.add(MenuOpPanel);
        MenuOpLabel = new JLabel("<html>Click on a Menu <br/> Option below to learn <br/> more ..........<html>");
        MenuOpLabel.setBounds(200,200,600,400);
        MenuOpLabel.setBackground(Color.blue);
        MenuOpLabel.setForeground(Color.black);
        MenuOpLabel.setFont(buttonFont);
        MenuOpLabel.setVisible(true);
        MenuOpPanel.add(MenuOpLabel);


        /**area set aside for buttons*/
        buttonPanel = new JPanel();
        buttonPanel.setBounds(0,200,200,375);
        buttonPanel.setBackground(Color.green);
        buttonPanel.setLayout(new GridLayout(5,1)); /**spaces out buttons vertically*/
        con.add(buttonPanel);

        /**about our group button appearance*/
        buttonAbout= new JButton("About");
        buttonAbout.setBackground(Color.gray);
        buttonAbout.setForeground(Color.black);
        buttonAbout.setFont(buttonFont);
        buttonAbout.setFocusPainted(false);
        buttonAbout.addActionListener(buttonchoice);

        /**about how to play the game button appearance*/
        buttonPanel.add(buttonAbout);
        buttonPlayGames= new JButton("How to Play");
        buttonPlayGames.setBackground(Color.gray);
        buttonPlayGames.setForeground(Color.black);
        buttonPlayGames.setFont(buttonFont);
        buttonPlayGames.setFocusPainted(false);
        buttonPlayGames.addActionListener(buttonchoice);

        /**about earning coins button appearance*/
        buttonPanel.add(buttonPlayGames);
        buttonEarnCur= new JButton("How to Earn Coins");
        buttonEarnCur.setBackground(Color.gray);
        buttonEarnCur.setForeground(Color.black);
        buttonEarnCur.setFont(buttonFont);
        buttonEarnCur.setFocusPainted(false);
        buttonEarnCur.addActionListener(buttonchoice);

        /**about using coins button appearance*/
        buttonPanel.add(buttonEarnCur);
        buttonUseCur = new JButton("How to Use Coins");
        buttonUseCur.setBackground(Color.gray);
        buttonUseCur.setForeground(Color.black);
        buttonUseCur.setFont(buttonFont);
        buttonUseCur.setFocusPainted(false);
        buttonUseCur.addActionListener(buttonchoice);

        /**quit button appearance*/
        buttonPanel.add(buttonUseCur);
        buttonQuit = new JButton("Quit Tutorial");
        buttonQuit.setBackground(Color.gray);
        buttonQuit.setForeground(Color.black);
        buttonQuit.setFont(buttonFont);
        buttonQuit.setFocusPainted(false);
        buttonQuit.addActionListener(buttonchoice);
        buttonPanel.add(buttonQuit);


        /**display TTTframe to make it visible
         * at bottom of code because when at top of code application
         * did not work as well*/
        tutorialFrame.setVisible(true);
    }



    public static class ButtonHandler implements ActionListener{
        /**class set up to use actionListener. class handles if a button is pushed
         * then the main text area will display that buttons topic*/

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceEvent = (JButton) e.getSource();
            if(sourceEvent ==buttonAbout ) {
                textExplainContent.setText("-----------------------ABOUT-----------------------\n\n" +
                        "Gooey Games is a LLC created in 2019 by \nLancaster Graham, Tara Bowman, " +
                        "Anthony Contestabile, \nRobert Jankowski, and Rachel Gruber. \n\nOur Goal is too provide a " +
                        "centralized location \nwith easy access to your favorite classic games." +
                        "\n\nAlso you can " +
                        "accrue coins for playing \nwhich you can use in our store. \n" +
                        "\nWe hope" +
                        " you enjoy playing RockTick!");

            }
            if(sourceEvent == buttonPlayGames) {
                textExplainContent.setText("----------------------------HOW TO PLAY-----------------------\n" +
                        "\n 1) Log in using the main login screen" +
                        "\n\n 2) If you don't have a login yet, create a new user name \nand password and press 'Register'" +
                        "\n \n3) After Logging in, the Main Menu Screen will pop up. " +
                        "\n Under the main menu you can view your coins and username." +
                        "\n You can choose to play 'Rock Paper Scissors' or 'Tic Tac Toe' by clicking on their buttons. " +
                        "\n\n You can view the 'Store' to use your coins.");
            }
            if(sourceEvent ==buttonEarnCur) {
                textExplainContent.setText("---------------------HOW TO EARN COINS-----------------------\n" +
                        "\nEarn Coins by playing our games." +
                        "\n You earn 1 coin just for playing. " +
                        "\n You earn 3 coins for a draw with the computer. " +
                        "\n You earn 5 coins for a win!");
            }
            if(sourceEvent== buttonUseCur) {
                textExplainContent.setText("------------------------HOW TO USE COINS-----------------------\n\n" +
                        "Use your coins in our Store. " +
                        "\nYour coins can be used to upgrade the game settings where" +
                        "\nyou have the option to choose your game background color.");
            }
            if(sourceEvent== buttonQuit) {
                //FIXME set this area to go back to main menu screen please.
                tutorialFrame.dispose();
                //Goes back to main menu
            }

        }
    }

    public static void main(String[] args) {

        /**commands to call program These commands can be transferred to the actionListener
         * of the main menu program of the tutorial button*/
        //FIXME use this to implement in main program
        Tutorial tutorialFrame = new Tutorial();
        tutorialFrame.createGUI();

    }

}