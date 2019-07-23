/**
 * This is Tic-Tac-Toe
 *
 *
 * Anthony Contestabile
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class TicTacToe implements ActionListener	{
    static final String VERSION = "1.0";

    //Setting up ALL the variables
    static JFrame TTTframe = new JFrame("Tic-Tac-Toe v" + VERSION);

    JMenuBar mainMenu = new JMenuBar();

    JMenuItem 	mnuNewGame = new JMenuItem("New Game"),
                mnuInstruction = new JMenuItem("Instructions"),
                mnuExit = new JMenuItem("Exit"),
                mnuAbout = new JMenuItem("About");

    JButton     playerVsPlayerButton = new JButton("Player vs Player"),
                playerVsComputerButton = new JButton("Player vs Computer"),
                quitButton = new JButton("Quit"),
                setNameButton = new JButton("Set Player Names"),
                darkThemeButton = new JButton("Set Dark Theme"),
                USAThemeButton = new JButton("Set Secret Theme"),
                lightThemeButton = new JButton("Set Light Theme"),
                continueButton = new JButton("Continue..."),
                tryAgainButton = new JButton("Try Again?");

    JButton[] buttons = new JButton[10];

    JPanel newGamePanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel quitAndTryAgainPanel = new JPanel();
    JPanel playFieldPanel = new JPanel();

    JLabel turnLabel = new JLabel();
    static JLabel statusLabel = new JLabel("", JLabel.CENTER);
    JLabel modeLabel = new JLabel("", JLabel.LEFT);

    JTextArea txtMessage = new JTextArea();

    final int winCombo[][] = new int[][]	{
            {1, 2, 3}, 			{1, 4, 7}, 		{1, 5, 9},
            {4, 5, 6}, 			{2, 5, 8}, 		{3, 5, 7},
            {7, 8, 9}, 			{3, 6, 9}
    /*Horizontal Wins*/	/*Vertical Wins*/ /*Diagonal Wins*/
    };

    static final int X = 650;
    static final int Y = 550;

    static int turn = 1;
    static int player1Points = 0;
    static int player2Points = 0;
    static int wonNumber1 = 1;
    int wonNumber2 = 1;
    int wonNumber3 = 1;
    int option;

    boolean inGame = false;
    boolean CPUGame = false;
    static boolean win = false;
    static boolean userWin = false;

    static String 	message;
    static String Player1 = Login.getUsername();
    String Player2 = "Guest";
    String tempPlayer2 = "Guest";

    //The colors created for the frame
    Color navy = new Color(0,0,128);//Border Color
    Color lightBlue = new Color(30, 144, 255);
    Color steelBlue = new Color(115, 216, 235);//Background Color
    static Color DarkRed = new Color(255,0,0);

    public TicTacToe()	{
        //Setting game properties and layout

        //Setting Menu, Main, Top, Bottom Panel Layout/Backgrounds
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Adding color to the loginFrame
        newGamePanel.setBackground(steelBlue);
        menuPanel.setBackground(navy);
        mainPanel.setBackground(steelBlue);
        topPanel.setBackground(steelBlue);
        bottomPanel.setBackground(navy);

        //Setting up Panel QuitNTryAgain
        quitAndTryAgainPanel.setLayout(new GridLayout(1, 2, 2, 2));
        quitAndTryAgainPanel.setBackground(navy);
        quitAndTryAgainPanel.add(tryAgainButton);
        quitAndTryAgainPanel.add(quitButton);

        //Adding menu items to menu bar
        mainMenu.add(mnuNewGame);
        mainMenu.add(mnuInstruction);
        mainMenu.add(mnuAbout);
        mainMenu.add(mnuExit);//	Menu Bar is Complete

        //Adding buttons to NewGame panel
        newGamePanel.setLayout(new GridLayout(7, 1, 2, 7));
        newGamePanel.add(continueButton);
        newGamePanel.add(playerVsPlayerButton);
        newGamePanel.add(playerVsComputerButton);
        newGamePanel.add(setNameButton);
        newGamePanel.add(darkThemeButton);
        newGamePanel.add(lightThemeButton);
        newGamePanel.add(USAThemeButton);

        //Setting Button properties
        tryAgainButton.setEnabled(false);
        continueButton.setEnabled(false);

        //Setting txtMessage Properties
        txtMessage.setBackground(steelBlue);
        txtMessage.setOpaque(true);
        txtMessage.setForeground(Color.black);
        txtMessage.setEditable(false);

        //Adding Action Listener to all the Buttons and Menu Items
        mnuNewGame.addActionListener(this);
        mnuExit.addActionListener(this);
        mnuInstruction.addActionListener(this);
        mnuAbout.addActionListener(this);
        playerVsPlayerButton.addActionListener(this);
        playerVsComputerButton.addActionListener(this);
        quitButton.addActionListener(this);
        setNameButton.addActionListener(this);
        continueButton.addActionListener(this);
        tryAgainButton.addActionListener(this);
        darkThemeButton.addActionListener(this);
        USAThemeButton.addActionListener(this);
        lightThemeButton.addActionListener(this);

        //Setting up the playing field
        playFieldPanel.setLayout(new GridLayout(3, 3, 2, 2));
        playFieldPanel.setBackground(navy);

        //Adding the buttons
        for(int i = 1; i <= 9; i++)	{
            buttons[i] = new JButton();
            buttons[i].setBackground(steelBlue);
            buttons[i].addActionListener(this);
            UIManager.getDefaults().put("Button.disabledText",Color.BLACK);
            playFieldPanel.add(buttons[i]);//	Playing Field
        }

        //Adding everything needed to menuPanel and mainPanel
        modeLabel.setForeground(Color.white);
        menuPanel.add(modeLabel);
        menuPanel.add(mainMenu);
        //mainPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
        menuPanel.setBackground(navy);

        //Setting TTTframe properties:
        TTTframe.setSize(X, Y);
        TTTframe.setLocation(350, 260);
        TTTframe.setLayout(new BorderLayout());
        TTTframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        TTTframe.add(menuPanel, BorderLayout.NORTH);
        TTTframe.add(mainPanel, BorderLayout.CENTER);
        TTTframe.setVisible(true);
    }

/*    public static void main(String[] args)	{
        new TicTacToe();
        //	PROGRAM STARTS HERE!
    }*/

    /**
     * this method is needed to create the loginFrame
     * from within the main console.
     */
    public static void createFrame() { //used to instantiate tic tac toe from main program console
        EventQueue.invokeLater(TicTacToe::new);
    }

    /**
     * this method is used to show what was on the game screen
     * if the user happened to navigate away from that panel
     */
    public void showGame()	{
        //	Shows the Playing Field
        //	*IMPORTANT*- Does not start out brand new (meaning just shows what it had before)
        clearPanelSouth();
        setDefaultLayout();
        mainPanel.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new BorderLayout());
        topPanel.add(playFieldPanel);
        bottomPanel.add(turnLabel, BorderLayout.WEST);
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(quitAndTryAgainPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        playFieldPanel.requestFocus();
        inGame = true;
        checkTurn();
        checkCoins();
    }
    //-----------------------------------------------------------------------------------------------------------------------------------
    public void newGame()	{
        //	Sets all the game required variables to default
        //	and then shows the playing field.
        //	(Basically: Starts a new 1v1 Game)
        for(int i = 1; i < 10; i++)	{
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        turn = 1;
        win = false;
        showGame();
    }

    public void quit() throws IOException {
        inGame = false;
        modeLabel.setText("");
        continueButton.setEnabled(false);
        clearPanelSouth();
        setDefaultLayout();
        topPanel.add(newGamePanel);
        mainPanel.add(topPanel);
        Login.addCoins();
    }
    //-----------------------------------------------------------------------------------------------------------------------------------
    public void checkForWin() {
        //	checks if there are 3 symbols in a row vertically, diagonally, or horizontally.
        //	then shows a message and disables buttons.
        for(int i = 0; i < 8; i++)	{
            if (!buttons[winCombo[i][0]].getText().equals("") &&
                 buttons[winCombo[i][0]].getText().equals(buttons[winCombo[i][1]].getText()) &&
                 //								if {1 == 2 && 2 == 3}
                 buttons[winCombo[i][1]].getText().equals(buttons[winCombo[i][2]].getText()))	{

                win = true;
                wonNumber1 = winCombo[i][0];
                wonNumber2 = winCombo[i][1];
                wonNumber3 = winCombo[i][2];
                break;
            }
        }

        if(win || (!win && turn > 9))	{
            if(win)	{
                if(buttons[wonNumber1].getText().equals("X"))	{
                    message = Player1 + " has won";
                    userWin = true;
                    try {
                        Login.addCoins();
                        checkCoins();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else	{
                    message = Player2 + " has won";
                    userWin = false;
                    try {
                        Login.addCoins();
                        checkCoins();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }	else if(!win && turn > 9) {
                message = "Both players have tied!\nBetter luck next time.";
                try {
                    Login.addCoins();
                    checkCoins();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            showMessage(message);
            for(int i = 1; i <= 9; i++)	{
                buttons[i].setEnabled(false);
            }
            tryAgainButton.setEnabled(true);
            checkCoins();
        } else
            checkTurn();
    }
    //----------------------------------------------------------------------------------------------------------------------------------
    //Beginning of computer AI methods
    public void AI()	{
        Font XOFont = new Font("Ravie", Font.BOLD, 36);
        int computerButton;
        if(turn <= 9)	{
            turn++;
            computerButton = cpuDoMove(
                    buttons[1], buttons[2], buttons[3],
                    buttons[4], buttons[5], buttons[6],
                    buttons[7], buttons[8], buttons[9]);
            if(computerButton == 0)
                Random();
            else {
                buttons[computerButton].setText("O");
                buttons[computerButton].setFont(XOFont);
                buttons[computerButton].setEnabled(false);
                buttons[computerButton].setForeground(Color.black);
            }
            checkForWin();
        }
    }

    public void Random()	{
        Font XOFont = new Font("Ravie", Font.BOLD, 36);
        int random;
        if(turn <= 9)	{
            random = 0;
            while(random == 0)	{
                random = (int)(Math.random() * 10);
            }
            if(doRandomMove(buttons[random]))	{
                buttons[random].setText("O");
                buttons[random].setFont(XOFont);
                buttons[random].setForeground(Color.black);
                buttons[random].setEnabled(false);
            } else {
                Random();
            }
        }
    }

    public static boolean doRandomMove(JButton button)	{
        if(button.getText().equals("O") || button.getText().equals("X"))
            return false;
        else {
            return true;
        }
    }

    /**
     * This is the main method for computer
     * to choose what move to make.
     * needs work**
     * @param btn1
     * @param btn2
     * @param btn3
     * @param btn4
     * @param btn5
     * @param btn6
     * @param btn7
     * @param btn8
     * @param btn9
     * @return
     */
    public static int cpuDoMove(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JButton btn5, JButton btn6, JButton btn7, JButton btn8, JButton btn9)	{
        if(btn1.getText().equals("O") && btn2.getText().equals("O") && btn3.getText().equals(""))
            return 3;
        else if(btn4.getText().equals("O") && btn5.getText().equals("O") && btn6.getText().equals(""))
            return 6;
        else if(btn7.getText().equals("O") && btn8.getText().equals("O") && btn9.getText().equals(""))
            return 9;

        else if(btn2.getText().equals("O") && btn3.getText().equals("O") && btn1.getText().equals(""))
            return 1;
        else if(btn5.getText().equals("O") && btn6.getText().equals("O") && btn4.getText().equals(""))
            return 4;
        else if(btn8.getText().equals("O") && btn9.getText().equals("O") && btn7.getText().equals(""))
            return 7;

        else if(btn1.getText().equals("O") && btn3.getText().equals("O") && btn2.getText().equals(""))
            return 2;
        else if(btn4.getText().equals("O") && btn6.getText().equals("O") && btn5.getText().equals(""))
            return 5;
        else if(btn7.getText().equals("O") && btn9.getText().equals("O") && btn8.getText().equals(""))
            return 8;

        else if(btn1.getText().equals("O") && btn4.getText().equals("O") && btn7.getText().equals(""))
            return 7;
        else if(btn2.getText().equals("O") && btn5.getText().equals("O") && btn8.getText().equals(""))
            return 8;
        else if(btn3.getText().equals("O") && btn6.getText().equals("O") && btn9.getText().equals(""))
            return 9;

        else if(btn4.getText().equals("O") && btn7.getText().equals("O") && btn1.getText().equals(""))
            return 1;
        else if(btn5.getText().equals("O") && btn8.getText().equals("O") && btn2.getText().equals(""))
            return 2;
        else if(btn6.getText().equals("O") && btn9.getText().equals("O") && btn3.getText().equals(""))
            return 3;

        else if(btn1.getText().equals("O") && btn7.getText().equals("O") && btn4.getText().equals(""))
            return 4;
        else if(btn2.getText().equals("O") && btn8.getText().equals("O") && btn5.getText().equals(""))
            return 5;
        else if(btn3.getText().equals("O") && btn9.getText().equals("O") && btn6.getText().equals(""))
            return 6;

        else if(btn1.getText().equals("O") && btn5.getText().equals("O") && btn9.getText().equals(""))
            return 9;
        else if(btn5.getText().equals("O") && btn9.getText().equals("O") && btn1.getText().equals(""))
            return 1;
        else if(btn1.getText().equals("O") && btn9.getText().equals("O") && btn5.getText().equals(""))
            return 5;

        else if(btn3.getText().equals("O") && btn5.getText().equals("O") && btn7.getText().equals(""))
            return 7;
        else if(btn7.getText().equals("O") && btn5.getText().equals("O") && btn3.getText().equals(""))
            return 3;
        else if(btn7.getText().equals("O") && btn3.getText().equals("O") && btn5.getText().equals(""))
            return 5;

        else if(btn1.getText().equals("X") && btn2.getText().equals("X") && btn3.getText().equals(""))
            return 3;
        else if(btn4.getText().equals("X") && btn5.getText().equals("X") && btn6.getText().equals(""))
            return 6;
        else if(btn7.getText().equals("X") && btn8.getText().equals("X") && btn9.getText().equals(""))
            return 9;

        else if(btn2.getText().equals("X") && btn3.getText().equals("X") && btn1.getText().equals(""))
            return 1;
        else if(btn5.getText().equals("X") && btn6.getText().equals("X") && btn4.getText().equals(""))
            return 4;
        else if(btn8.getText().equals("X") && btn9.getText().equals("X") && btn7.getText().equals(""))
            return 7;

        else if(btn1.getText().equals("X") && btn3.getText().equals("X") && btn2.getText().equals(""))
            return 2;
        else if(btn4.getText().equals("X") && btn6.getText().equals("X") && btn5.getText().equals(""))
            return 5;
        else if(btn7.getText().equals("X") && btn9.getText().equals("X") && btn8.getText().equals(""))
            return 8;

        else if(btn1.getText().equals("X") && btn4.getText().equals("X") && btn7.getText().equals(""))
            return 7;
        else if(btn2.getText().equals("X") && btn5.getText().equals("X") && btn8.getText().equals(""))
            return 8;
        else if(btn3.getText().equals("X") && btn6.getText().equals("X") && btn9.getText().equals(""))
            return 9;

        else if(btn4.getText().equals("X") && btn7.getText().equals("X") && btn1.getText().equals(""))
            return 1;
        else if(btn5.getText().equals("X") && btn8.getText().equals("X") && btn2.getText().equals(""))
            return 2;
        else if(btn6.getText().equals("X") && btn9.getText().equals("X") && btn3.getText().equals(""))
            return 3;

        else if(btn1.getText().equals("X") && btn7.getText().equals("X") && btn4.getText().equals(""))
            return 4;
        else if(btn2.getText().equals("X") && btn8.getText().equals("X") && btn5.getText().equals(""))
            return 5;
        else if(btn3.getText().equals("X") && btn9.getText().equals("X") && btn6.getText().equals(""))
            return 6;

        else if(btn1.getText().equals("X") && btn5.getText().equals("X") && btn9.getText().equals(""))
            return 9;
        else if(btn5.getText().equals("X") && btn9.getText().equals("X") && btn1.getText().equals(""))
            return 1;
        else if(btn1.getText().equals("X") && btn9.getText().equals("X") && btn5.getText().equals(""))
            return 5;

        else if(btn3.getText().equals("X") && btn5.getText().equals("X") && btn7.getText().equals(""))
            return 7;
        else if(btn7.getText().equals("X") && btn5.getText().equals("X") && btn3.getText().equals(""))
            return 3;
        else if(btn7.getText().equals("X") && btn3.getText().equals("X") && btn5.getText().equals(""))
            return 5;

        else if(btn1.getText().equals("X") && btn5.getText().equals("O") && btn9.getText().equals("X"))
            return 6;

        else if(btn3.getText().equals("X") && btn5.getText().equals("O") && btn7.getText().equals("X"))
            return 4;

        else if(btn5.getText().equals(""))
            return 5;

        else if(btn1.getText().equals(""))
            return 1;
        else
            return 0;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    public void checkTurn()	{
        String whoTurn;
        if(!(turn % 2 == 0))	{
            whoTurn = Player1 + " [X]";
        }	else	{
            whoTurn = Player2 + " [O]";
        }
        turnLabel.setText("Turn: " + whoTurn);
        turnLabel.setForeground(Color.white);
    }

    //This is if the user wants to change their nickname within Tic-Tac-Toe
    //Does not effect the actual username
    public void askUserForPlayerNames()	{
        String temp;
        boolean tempIsValid = false;
        temp = getInput("Enter player 1 name:", Player1);
        if(temp == null){/*Do Nothing*/}
        else if(temp.equals(""))
            showMessage("Invalid Name!");
        else if(temp.equals(Player2))	{
            option = askMessage("Player 1 name matches Player 2's" +
                    "\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                tempIsValid = true;
        } else if(temp != null)	{
            tempIsValid = true;
        }
        if(tempIsValid)	{
            Player1 = temp;
            tempIsValid = false;
        }

        temp = getInput("Enter player 2 name:", Player2);
        if(temp == null)	{/*Do Nothing*/}
        else if(temp.equals(""))
            showMessage("Invalid Name!");
        else if(temp.equals(Player1))	{
            option = askMessage("Player 2 name matches Player 1's" +
                    "\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                tempIsValid = true;
        } else if(temp != null)	{
            tempIsValid = true;
        }
        if(tempIsValid)	{
            Player2 = temp;
            tempPlayer2 = temp;
        }
    }
    //------------------------------------------------------------------------------
    public void setDefaultLayout()	{
        mainPanel.setLayout(new GridLayout(2, 1, 2, 5));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
    //----------------------------------------------------------------------------
    public int askMessage(String msg, String tle, int op)	{
        return JOptionPane.showConfirmDialog(null, msg, tle, op);
    }
    public String getInput(String msg, String setText)	{
        return JOptionPane.showInputDialog(null, msg, setText);
    }
    public void showMessage(String msg)	{
        JOptionPane.showMessageDialog(null, msg);
    }
    //----------------------------------------------------------------------------
    public void clearPanelSouth()	{
        //Removes all the possible panels
        //that mainPanel, topPanel, bottomPanel
        //could have.
        mainPanel.remove(topPanel);
        mainPanel.remove(bottomPanel);
        topPanel.remove(newGamePanel);
        topPanel.remove(txtMessage);
        topPanel.remove(playFieldPanel);
        bottomPanel.remove(turnLabel);
        bottomPanel.remove(quitAndTryAgainPanel);
    }
    //-----------------------------------------------------------------------------------

    /**
     * Check total number of coins for user.
     */
    public void checkCoins()	{
        statusLabel.setText(Player1 + "'s total coins: " + Login.getCoins());
        statusLabel.setForeground(Color.white);
    }

    /**
     * This method is used to update the current
     * users coin total within the whole console.
     * @return number of coins earned.
     */
    public static int coinsEarned(){
        //int numCoinsEarned = player1Points;
        int numCoinsEarned = 0;

        if(win || (!win && turn > 9))	{
            if(win)	{
                if(userWin)	{
                    numCoinsEarned = 5;
                    player1Points++;
                }
                else{
                    numCoinsEarned = 1;
                    player2Points++;
                }
            } else if(!win && turn > 9){
                numCoinsEarned = 3;
            }
        }
        return numCoinsEarned;
    }

    /**
     * Method to set the dark theme, if it's unlocked
     */
    public void darkTheme(){
        Font XOFont = new Font("Ravie", Font.BOLD, 36);
        clearPanelSouth();
        setDefaultLayout();
        newGamePanel.setBackground(Color.DARK_GRAY);
        menuPanel.setBackground(DarkRed);
        mainPanel.setBackground(Color.DARK_GRAY);
        topPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setBackground(DarkRed);
        quitAndTryAgainPanel.setBackground(DarkRed);
        txtMessage.setBackground(Color.DARK_GRAY);
        playFieldPanel.setBackground(DarkRed);
        txtMessage.setForeground(Color.WHITE);
        for(int i = 1; i <= 9; i++)	{
            buttons[i].setBackground(Color.DARK_GRAY);
            buttons[i].setFont(XOFont);
            UIManager.getDefaults().put("Button.disabledText",DarkRed);
        }
        topPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
        mainPanel.add(topPanel);
        //for debug
        System.out.println("Dark Theme");
    }

    /**
     * Light Theme
     */
    public void lightTheme(){
        Font XOFont = new Font("Ravie", Font.BOLD, 36);
        clearPanelSouth();
        setDefaultLayout();
        newGamePanel.setBackground(Color.lightGray);
        menuPanel.setBackground(lightBlue);
        mainPanel.setBackground(Color.lightGray);
        topPanel.setBackground(Color.lightGray);
        bottomPanel.setBackground(lightBlue);
        quitAndTryAgainPanel.setBackground(lightBlue);
        txtMessage.setBackground(Color.lightGray);
        playFieldPanel.setBackground(lightBlue);
        txtMessage.setForeground(Color.BLACK);
        for(int i = 1; i <= 9; i++)	{
            buttons[i].setBackground(Color.lightGray);
            UIManager.getDefaults().put("Button.disabledText",lightBlue);
            buttons[i].setFont(XOFont);
        }
        topPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
        mainPanel.add(topPanel);
        //for debug
        System.out.println("Light Theme");
    }

    /**
     * USA Theme aka:
     * "Secret theme"
     */
    public void USATheme(){
        Font XOFont = new Font("Ravie", Font.BOLD, 36);
        clearPanelSouth();
        setDefaultLayout();
        newGamePanel.setBackground(Color.blue);
        menuPanel.setBackground(DarkRed);
        mainPanel.setBackground(Color.white);
        topPanel.setBackground(Color.BLUE);
        bottomPanel.setBackground(DarkRed);
        quitAndTryAgainPanel.setBackground(DarkRed);
        txtMessage.setBackground(Color.blue);
        playFieldPanel.setBackground(Color.BLUE);
        txtMessage.setForeground(Color.white);
        for(int i = 1; i <= 9; i++)	{
            buttons[i].setBackground(Color.WHITE);
            UIManager.getDefaults().put("Button.disabledText", Color.BLUE);
            buttons[i].setFont(XOFont);
        }
        topPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
        mainPanel.add(topPanel);
        //for debug
        System.out.println("USA Theme");
    }

    //-----------------------------------------------------------------------------------
    /**
     * Method creating the actions
     * for each button clicked
     * @param click Action event
     */
    public void actionPerformed(ActionEvent click)	{
        Font XOFont = new Font("Ravie", Font.BOLD, 36);
        Font InstructionFont = new Font("Arial", Font.BOLD, 16);

        Object source = click.getSource();
        for(int i = 1; i <= 9; i++)	{
            if(source == buttons[i] && turn < 10)	{
                if(!(turn % 2 == 0)) {
                    buttons[i].setText("X");
                    buttons[i].setFont(XOFont);
                    buttons[i].setForeground(Color.black);
                }

                else {
                    buttons[i].setText("O");
                    buttons[i].setFont(XOFont);
                    buttons[i].setForeground(Color.black);
                }

                buttons[i].setEnabled(false);
                playFieldPanel.requestFocus();
                turn++;
                checkForWin();
                if(CPUGame && !win)
                    AI();
            }
        }
        if(source == mnuNewGame || source == mnuInstruction || source == mnuAbout) {
            clearPanelSouth();
            setDefaultLayout();

            if(source == mnuNewGame) {//NewGame
                topPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
            }
            else if(source == mnuInstruction || source == mnuAbout) {
                if(source == mnuInstruction) {  // Instructions
                    message = 	"Instructions:\n\n" +
                            "Your goal is to be the first player to get 3 X's or O's\n" +
                            "in a row. (horizontally, diagonally, or vertically)\n" +
                            Player1 + ": X\n" +
                            Player2 + ": O\n";
                } else	{//About
                    message = 	"About:\n\n" +
                            "Title: Tic-Tac-Toe\n" +
                            "Created by Gooey Games INC.";

                }
                txtMessage.setText(message);
                txtMessage.setFont(InstructionFont);
                topPanel.add(txtMessage);
            }
            mainPanel.add(topPanel);
        }
        else if(source == playerVsPlayerButton || source == playerVsComputerButton)	{
            if(inGame) {
                option = askMessage("If you start a new game," +
                                "your current game will be lost..." + "\n" +
                                "Are you sure you want to continue?",
                        "Quit Game?" ,JOptionPane.YES_NO_OPTION
                );
                if(option == JOptionPane.YES_OPTION)
                    inGame = false;
            }
            if(!inGame)	{
                continueButton.setEnabled(true);
                if(source == playerVsPlayerButton)	{   // 1 v 1 Game
                    Player2 = tempPlayer2;
                    player1Points = 0;
                    player2Points = 0;
                    modeLabel.setText(Login.getUsername() + " vs. " + Player2);
                    modeLabel.setForeground(Color.white);
                    CPUGame = false;
                    newGame();
                } else	{   // 1 v CPU Game
                    Player2 = "Computer";
                    player1Points = 0;
                    player2Points = 0;
                    modeLabel.setText(Login.getUsername() + " vs. CPU");
                    modeLabel.setForeground(Color.white);
                    CPUGame = true;
                    newGame();
                }
            }
        }
        else if(source == continueButton)	{
            checkTurn();
            showGame();
        }
        else if(source == setNameButton)	{
            askUserForPlayerNames();
        }
        else if(source == mnuExit)	{
            option = askMessage("Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION) {
                try {
                    Login.addCoins();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clearPanelSouth();
                setDefaultLayout();
                TTTframe.dispose();
            }
        }
        else if(source == tryAgainButton)	{
            newGame();
            tryAgainButton.setEnabled(false);
        }
        else if(source == quitButton)	{
            try {
                quit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(source == darkThemeButton){
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.isDarkThemeUnlocked().equals(rewardStatus.unlocked)) {
                        clearPanelSouth();
                        setDefaultLayout();
                        darkTheme();
                    } else {
                        showMessage("You need to unlock this reward.");
                        darkThemeButton.setEnabled(false);
                    }
                }
            }
        }
        else if(source == lightThemeButton){
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.isLightThemeUnlocked().equals(rewardStatus.unlocked)) {
                        clearPanelSouth();
                        setDefaultLayout();
                        lightTheme();
                    } else {
                        showMessage("You need to unlock this reward.");
                        lightThemeButton.setEnabled(false);
                    }
                }
            }
        }
        else if(source == USAThemeButton){
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.isUSAThemeUnlocked().equals(rewardStatus.unlocked)) {
                        clearPanelSouth();
                        setDefaultLayout();
                        USATheme();
                    } else {
                        showMessage("You need to unlock this reward.");
                        USAThemeButton.setEnabled(false);
                    }
                }
            }
        }
        mainPanel.setVisible(false);
        mainPanel.setVisible(true);
    }
//-------------------END OF ACTION PERFORMED METHOD-------------------------//
}