//This is Rock Paper Scissors
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

public class RockPaperScissors implements ActionListener {
    private static final String VERSION = "1.0";

    private static JFrame RPSframe = new JFrame("Rock Paper Scissors v" + VERSION);

    private JMenuBar mainMenu = new JMenuBar();

    private JMenuItem   mnuNewGame = new JMenuItem("New Game"),
            mnuInstruction = new JMenuItem("Instructions"),
            mnuExit = new JMenuItem("Exit"),
            mnuAbout = new JMenuItem("About");

    private JButton PvC = new JButton("Player vs Computer"),
            PvP = new JButton("Player vs Player"),
            Continue = new JButton("Continue..."),
            TryAgain = new JButton("Try Again?"),
            Quit = new JButton("Quit"),
            SetName = new JButton("Set Player Names"),
            Rock = new JButton("Rock"),
            Paper = new JButton("Paper"),
            Scissors = new JButton("Scissors"),
            LightTheme = new JButton("Light Theme"),
            DarkTheme = new JButton("Dark Theme"),
            USAThemeButton = new JButton("Secret Theme");

    private JPanel  newGamePanel = new JPanel(),
            menuPanel = new JPanel(),
            mainPanel = new JPanel(),
            topPanel = new JPanel(),
            bottomPanel = new JPanel(),
            quitAndTryAgainPanel = new JPanel(),
            playFieldPanel = new JPanel();

    private JTextArea Message = new JTextArea();

    private JLabel  modeLabel = new JLabel(),
            statusLabel = new JLabel();
            //titleLabel = new JLabel();

    final int X = 600, Y = 500;

    private int     player2Points = 0,
            option;
    private static int player1Points = 0;

    private boolean inGame = false,
            CPUGame = false;

    private static boolean 	win = false,
            userWin = false;

    private String  message,
            Player1 = Login.getUsername(), Player2 = "Guest",
            tempPlayer2 = "Guest";

    private static Color DarkRed = new Color(255,0,0);
    //Background Color
    Color lightBlue = new Color(30, 144, 255);
    Color steelBlue = new Color(115, 216, 235);
    //Border Color
    Color navy = new Color(0, 0, 128);

    private String  player1Input = "",
            player2Input = "";

    //Creating the basic windows and menus of Rock Paper Scissors
    private RockPaperScissors() {
        //Setting game properties and layout

        //Setting panel layouts
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Adding color to loginFrame
        newGamePanel.setBackground(steelBlue);
        menuPanel.setBackground(navy);
        mainPanel.setBackground(steelBlue);
        topPanel.setBackground(steelBlue);
        bottomPanel.setBackground(navy);

        //Setting up the "Quit and Try Again" panel
        quitAndTryAgainPanel.setLayout(new GridLayout(1, 2, 2, 2));
        quitAndTryAgainPanel.setBackground(navy);
        quitAndTryAgainPanel.add(TryAgain);
        quitAndTryAgainPanel.add(Quit);

        //Adding menu items to menu bar
        mainMenu.add(mnuNewGame);
        mainMenu.add(mnuInstruction);
        mainMenu.add(mnuAbout);
        mainMenu.add(mnuExit);

        //Adding buttons to NewGame panel
        newGamePanel.setLayout(new GridLayout(6, 1, 2, 7));
        newGamePanel.add(Continue);
        //RPS DOES NOT CURRENTLY SUPPORT PLAYER VS PLAYER
        //newGamePanel.add(PvP);
        newGamePanel.add(PvC);
        newGamePanel.add(SetName);
        newGamePanel.add(DarkTheme);
        newGamePanel.add(LightTheme);
        newGamePanel.add(USAThemeButton);

        //Setting Button properties
        TryAgain.setEnabled(false);
        Continue.setEnabled(false);

        //Setting txtMessage Properties
        Message.setBackground(steelBlue);
        Message.setOpaque(true);
        Message.setForeground(Color.black);
        Message.setEditable(false);

        //Adding action listener to all the Buttons and Menu Items
        mnuNewGame.addActionListener(this);
        mnuExit.addActionListener(this);
        mnuInstruction.addActionListener(this);
        mnuAbout.addActionListener(this);
        PvP.addActionListener(this);
        PvC.addActionListener(this);
        Quit.addActionListener(this);
        SetName.addActionListener(this);
        Continue.addActionListener(this);
        TryAgain.addActionListener(this);
        Rock.addActionListener(this);
        Paper.addActionListener(this);
        Scissors.addActionListener(this);
        DarkTheme.addActionListener(this);
        LightTheme.addActionListener(this);
        USAThemeButton.addActionListener(this);

        //Setting up the playing field
        playFieldPanel.setLayout(new GridLayout(3, 1, 2, 2));
        playFieldPanel.setBackground(navy);

        //Adding buttons
        playFieldPanel.add(Rock);
        playFieldPanel.add(Paper);
        playFieldPanel.add(Scissors);

        modeLabel.setForeground(Color.white);
        menuPanel.add(modeLabel);
        menuPanel.add(mainMenu);
        menuPanel.setBackground(navy);

        //Adding to RPSframe and showing it
        RPSframe.setSize(X, Y);
        RPSframe.setLocation(350, 260);
        RPSframe.setLayout(new BorderLayout());
        RPSframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RPSframe.add(menuPanel, BorderLayout.NORTH);
        RPSframe.add(mainPanel, BorderLayout.CENTER);
        RPSframe.setVisible(true);


    }
/*    //Running RockPaperScissors
    public static void main (String[] args) {
        new RockPaperScissors();
    }*/

    static void createFrame() {
        EventQueue.invokeLater(RockPaperScissors::new);
    }

    //-----------------------------------------------------------------------
    //Beginning of Gameplay methods
    private void showGame() {
        clearPanelSouth();
        mainPanel.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new BorderLayout());
        topPanel.add(playFieldPanel);
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(quitAndTryAgainPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        playFieldPanel.requestFocus();
        inGame = true;
        checkPoints();
    }

    private void newGame() {
        win = false;
        showGame();
    }

    private void quit() throws IOException {
        inGame = false;
        modeLabel.setText("");
        Continue.setEnabled(false);
        clearPanelSouth();
        setDefaultLayout();
        topPanel.add(newGamePanel);
        mainPanel.add(topPanel);
        Login.addCoins();
    }

    //-----------------------------------------------------------------------
    private void checkWin() {
        Random random = new Random();
        int AIchoice = random.nextInt(3);
        if (AIchoice == 0) {
            player2Input = "Rock";
        } else if (AIchoice == 1) {
            player2Input = "Paper";
        } else {
            player2Input = "Scissors";
        }
        if ((player1Input.equals("Rock") && player2Input.equals("Rock")) ||
                (player1Input.equals("Paper") && player2Input.equals("Paper")) ||
                (player1Input.equals("Scissors") && player2Input.equals("Scissors"))) {
            message = Player1 + " chose " + player1Input +
                    "\n" + Player2 + " chose " + player2Input +
                    "\nBoth players have tied! Better luck next time!";
                checkPoints();

        } else if ((player1Input.equals("Rock") && player2Input.equals("Scissors")) ||
                (player1Input.equals("Paper") && player2Input.equals("Rock")) ||
                (player1Input.equals("Scissors") && player2Input.equals("Paper"))) {
            message = Player1 + " chose " + player1Input +
                    "\n" + Player2 + " chose " + player2Input +
                    "\n" +Player1 + " has won!";
            userWin = true;
            win = true;
            player1Points++;
                checkPoints();
        } else {
            message = Player1 + " chose " + player1Input +
                    "\n" + Player2 + " chose " + player2Input +
                    "\n" + Player2 + " has won!";
            win = true;
            player2Points++;
                checkPoints();
        }
        showMessage(message);
        TryAgain.setEnabled(true);
    }

    //End of Gameplay methods
    //-----------------------------------------------------------------------
    //Beginning of background methods

    private void askUserForPlayerNames() {
        String temp;
        boolean tempIsValid = false;
        temp = getInput("Enter player 1 name:", Player1);
        if(temp == null)    {/*Do Nothing*/}
        else if(temp.equals(""))
            showMessage("Invalid Name!");
        else if(temp.equals(Player2))   {
            option = askMessage("Player 1 name matches Player 2's" +
                    "\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                tempIsValid = true;
        } else if(temp != null) {
            tempIsValid = true;
        }
        if(tempIsValid) {
            Player1 = temp;
            tempIsValid = false;
        }
        temp = getInput("Enter player 2 name:", Player2);
        if(temp == null)    {/*Do Nothing*/}
        else if(temp.equals(""))
            showMessage("Invalid Name!");
        else if(temp.equals(Player1))   {
            option = askMessage("Player 2 name matches Player 1's" +
                    "\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                tempIsValid = true;
        } else if(temp != null) {
            tempIsValid = true;
        }
        if(tempIsValid) {
            Player2 = temp;
            tempPlayer2 = temp;
        }
    }

    private void setDefaultLayout() {
        mainPanel.setLayout(new GridLayout(2, 1, 2, 5));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void checkPoints() {
        statusLabel.setText(Player1 + ": " + player1Points + " | " + Player2 + ": " + player2Points);
        //statusLabel.setText(Player1 + "'s total coins: " + Login.getCoins());
        statusLabel.setForeground(Color.white);
    }

    //-----------------------------------------------------------------------
    private int askMessage(String msg, String tle, int op) {
        return JOptionPane.showConfirmDialog(null, msg, tle, op);
    }

    private String getInput(String msg, String setText) {
        return JOptionPane.showInputDialog(null, msg, setText);
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    //-----------------------------------------------------------------------

    private void clearPanelSouth() {
        //mainPanel.remove(titleLabel);
        mainPanel.remove(topPanel);
        mainPanel.remove(bottomPanel);
        topPanel.remove(newGamePanel);
        topPanel.remove(Message);
        topPanel.remove(playFieldPanel);
        bottomPanel.remove(quitAndTryAgainPanel);
    }

    static int coinsEarned() {
        int numCoinsEarned;
        if ((player1Points / 5) < 1) {
            numCoinsEarned = 1;
        } else {
            numCoinsEarned = player1Points / 5;
        }
        return numCoinsEarned;
    }

    private void darkTheme(){
        clearPanelSouth();
        setDefaultLayout();
        newGamePanel.setBackground(Color.DARK_GRAY);
        menuPanel.setBackground(DarkRed);
        mainPanel.setBackground(Color.DARK_GRAY);
        topPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setBackground(DarkRed);
        quitAndTryAgainPanel.setBackground(DarkRed);
        Message.setBackground(Color.DARK_GRAY);
        playFieldPanel.setBackground(DarkRed);
        Message.setForeground(Color.WHITE);
        Rock.setBackground(Color.DARK_GRAY);
        Paper.setBackground(Color.DARK_GRAY);
        Scissors.setBackground(Color.DARK_GRAY);
        Rock.setForeground(Color.WHITE);
        Paper.setForeground(Color.WHITE);
        Scissors.setForeground(Color.WHITE);
        topPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
        UIManager.getDefaults().put("Button.disabledText",DarkRed);
        mainPanel.add(topPanel);
        System.out.println("Dark Theme");
    }

    /**
     * Light Theme
     */
    private void lightTheme(){
        clearPanelSouth();
        setDefaultLayout();
        newGamePanel.setBackground(Color.lightGray);
        menuPanel.setBackground(lightBlue);
        mainPanel.setBackground(Color.lightGray);
        topPanel.setBackground(Color.lightGray);
        bottomPanel.setBackground(lightBlue);
        quitAndTryAgainPanel.setBackground(lightBlue);
        Message.setBackground(Color.lightGray);
        playFieldPanel.setBackground(lightBlue);
        Message.setForeground(Color.BLACK);
        Rock.setBackground(Color.lightGray);
        Paper.setBackground(Color.lightGray);
        Scissors.setBackground(Color.lightGray);
        Rock.setForeground(lightBlue);
        Paper.setForeground(lightBlue);
        Scissors.setForeground(lightBlue);
        topPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
        UIManager.getDefaults().put("Button.disabledText",lightBlue);
        mainPanel.add(topPanel);
        System.out.println("Light Theme");
    }

    private void USATheme(){
        clearPanelSouth();
        setDefaultLayout();
        newGamePanel.setBackground(Color.blue);
        menuPanel.setBackground(DarkRed);
        mainPanel.setBackground(Color.white);
        topPanel.setBackground(Color.BLUE);
        bottomPanel.setBackground(DarkRed);
        quitAndTryAgainPanel.setBackground(DarkRed);
        Message.setBackground(Color.blue);
        playFieldPanel.setBackground(Color.BLUE);
        Message.setForeground(Color.white);
        UIManager.getDefaults().put("Button.disabledText", Color.BLUE);
        Rock.setBackground(Color.WHITE);
        Rock.setForeground(Color.BLUE);
        Paper.setForeground(Color.BLUE);
        Scissors.setForeground(Color.BLUE);
        Paper.setBackground(Color.WHITE);
        Scissors.setBackground(Color.WHITE);
        topPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
        mainPanel.add(topPanel);
        System.out.println("USA Theme");
    }
    //End of Background methods
    //-----------------------------------------------------------------------
    //Action Performed method

    public void actionPerformed(ActionEvent click) {
        Font InstructionFont = new Font("Arial", Font.BOLD, 16);

        Object source = click.getSource();
        if (source == Rock) {
            player1Input = "Rock";
            checkWin();
        } else if (source == Paper) {
            player1Input = "Paper";
            checkWin();
        } else if (source == Scissors) {
            player1Input = "Scissors";
            checkWin();
        }
        if (source == mnuNewGame || source == mnuInstruction || source == mnuAbout) {
            clearPanelSouth();
            setDefaultLayout();
            if (source == mnuNewGame) {
                topPanel.add(newGamePanel, JPanel.TOP_ALIGNMENT);
            }
            else if (source == mnuInstruction || source == mnuAbout) {
                if (source == mnuInstruction) {
                    message = "Instructions:\n\n" +
                            "Your goal is to defeat the other player " +
                            "\nby choosing the correct tool to win." +
                            "\nRock beats Scissors" + "\nScissors beats Paper" + "\nPaper beats Rock"
                            + "\n\n\nTake a chance, and defeat your opponent with luck.";

                } else {
                    message = "About:\n\n" +
                            "Title: Rock Paper Scissors\n" +
                            "Created by Gooey Games INC.";
                }
                Message.setText(message);
                Message.setFont(InstructionFont);
                topPanel.add(Message);
            }
            mainPanel.add(topPanel);
        }
        else if (source == PvP || source == PvC) {
            if (inGame) {
                option = askMessage("If you start a new game, your current game will be lost...\n" +
                                "Are you sure you want to continue?", "Quit Game?",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    inGame = false;
                }
            }
            if (!inGame) {
                Continue.setEnabled(true);
                if (source == PvP) {
                    Player2 = tempPlayer2;
                    player1Points = 0;
                    player2Points = 0;
                    modeLabel.setText("1 v 1");
                    modeLabel.setForeground(Color.white);
                    CPUGame = false;
                    newGame();
                } else {
                    Player2 = "Computer";
                    player1Points = 0;
                    player2Points = 0;
                    modeLabel.setText("1 v CPU");
                    modeLabel.setForeground(Color.white);
                    CPUGame = true;
                    newGame();
                }
            }
        }
        else if (source == Continue) {
            showGame();
        }
        else if (source == SetName) {
            askUserForPlayerNames();
        }
        else if (source == LightTheme) {
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.isLightThemeUnlocked().equals(rewardStatus.unlocked)) {
                        clearPanelSouth();
                        setDefaultLayout();
                        lightTheme();
                    } else {
                        showMessage("You need to unlock this reward.");
                        LightTheme.setEnabled(false);
                    }
                }
            }
        }
        else if (source == DarkTheme) {
            for (Users user : Login.userList) {
                if (user.getUsername().equalsIgnoreCase(Login.getUsername())) {
                    if (user.isDarkThemeUnlocked().equals(rewardStatus.unlocked)) {
                        clearPanelSouth();
                        setDefaultLayout();
                        darkTheme();
                    } else {
                        showMessage("You need to unlock this reward.");
                        DarkTheme.setEnabled(false);
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
        else if (source == mnuExit) {
            option = askMessage("Are you sure you want to exit?", "Exit Game",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    Login.addCoins();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clearPanelSouth();
                setDefaultLayout();
                RPSframe.dispose();
            }
        }
        else if (source == TryAgain) {
            newGame();
            TryAgain.setEnabled(false);
        }
        else if (source == Quit) {
            try {
                quit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mainPanel.setVisible(false);
        mainPanel.setVisible(true);
    }

}