import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GG_Settings extends JPanel implements ActionListener, ChangeListener, ItemListener {

    static JFrame settingsFrame = new JFrame("Settings - Gooey Games console");

    private JLabel difficultyLabel;
    private JLabel backgroundLabel;
    private JLabel soundLabel;
    private JLabel volumeLabel;
    private JRadioButton soundOnOff;
    static private String soundStatus;
    private JSlider volumeSlider;
    private JComboBox<String> difficulty;
    private JMenuBar menuBar;
    private JComboBox<String> color;

    Color darkGreen = new Color(0, 100, 0);

    public GG_Settings() {


        //construct preComponents

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem contact_usItem = new JMenuItem("Contact Us");
        helpMenu.add(contact_usItem);
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        // background color options
        String[] colorItems = {"Blue", "Red", "Green"};
        //  difficulty options
        String[] difficultyOptions = {"Easy", "Medium", "Difficult"};
        Font labelFont = new Font("Arial Rounded MT Bold",Font.BOLD, 15);

        //construct components
        // labels
        backgroundLabel = new JLabel("Change background color");
        soundLabel = new JLabel("Turn sound on/off");
        volumeLabel = new JLabel("Adjust volume");
        difficultyLabel = new JLabel("Change difficulty level");
        //  add difficulty options to pull down menu
        difficulty = new JComboBox<String>(difficultyOptions);
        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        //  add background color options to pull down menu
        color = new JComboBox<String>(colorItems);
        //  volume slider goes from 0 (off) to 10 (max)
        volumeSlider = new JSlider (0, 10);
        //  sound on off button
        soundOnOff = new JRadioButton("blank is off");

        //set components properties like color and font
        backgroundLabel.setFont(labelFont);
        backgroundLabel.setForeground(Color.WHITE);
        soundLabel.setFont(labelFont);
        soundLabel.setForeground(Color.WHITE);
        volumeLabel.setFont(labelFont);
        volumeLabel.setForeground(Color.WHITE);
        difficultyLabel.setFont(labelFont);
        difficultyLabel.setForeground(Color.WHITE);
        volumeSlider.setOrientation (JSlider.HORIZONTAL);
        volumeSlider.setMinorTickSpacing (1);
        volumeSlider.setMajorTickSpacing (5);
        volumeSlider.setPaintTicks (true);
        volumeSlider.setPaintLabels (true);

        //adjust size and set layout
        settingsFrame.setPreferredSize(new Dimension (544, 375));
        settingsFrame.setLayout(null);

        //add components
        settingsFrame.add(backgroundLabel);
        settingsFrame.add(difficultyLabel);
        settingsFrame.add(soundLabel);
        settingsFrame.add(volumeLabel);
        settingsFrame.add(soundOnOff);
        settingsFrame.add(volumeSlider);
        settingsFrame.add(difficulty);
        settingsFrame.add(menuBar);
        settingsFrame.add(color);

        //set component bounds (only needed by Absolute Positioning)
        soundLabel.setBounds(25, 120, 175, 50);
        volumeLabel.setBounds(25, 190, 175, 50);
        difficultyLabel.setBounds(25, 260, 175, 50);
        backgroundLabel.setBounds(25, 45, 195, 50);
        soundOnOff.setBounds(270, 130, 100, 25);
        volumeSlider.setBounds(270, 190, 115, 45);
        difficulty.setBounds(275, 270, 110, 25);
        menuBar.setBounds(0, 0, 200, 25);
        color.setBounds(270, 50, 115, 30);


        //  add action listeners, item listeners for sound on/off, & change listener for volume

        fileMenu.addActionListener(this);
        exitItem.addActionListener(this);
        difficulty.addActionListener(this);
        color.addActionListener(this);
        soundOnOff.addItemListener(this);
        volumeSlider.addChangeListener(this);


        //Overriding action listener for menu items
        //Exit
        exitItem.addActionListener(e ->
                settingsFrame.dispose());

        //Contact Us
        contact_usItem.addActionListener(e ->
                showMessageDialog(GG_Settings.this,
                        "Gooey Games Inc.\n" +
                                "(636) 922 - 8000\n" +
                                "help@gooeygames.com",
                        "Contact Us",
                        INFORMATION_MESSAGE));

        //About / How to change settings
        aboutItem.addActionListener(e ->
                showMessageDialog(GG_Settings.this,
                        "To change the background color, select from the dropdown menu.\n" +
                                "To turn the sound on/off, select/deselect the button.\n" +
                                "To adjust the volume, use the slider.\n" +
                                "To change the difficulty level, select from the dropdown menu.",
                        "About",
                        INFORMATION_MESSAGE));

    }

    public static void createFrame() { //used to instantiate settings screen
        EventQueue.invokeLater(() -> new GG_Settings());{
            settingsFrame.setPreferredSize(new Dimension(750,450));
            settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            settingsFrame.getContentPane().setBackground(Color.BLUE);
            settingsFrame.pack();
            settingsFrame.setVisible(true);
        };
    }

    //  ask if the user is sure that they want to quit
    public int askMessage(String msg, String tle, int op)	{
        return JOptionPane.showConfirmDialog(null, msg, tle, op);
    }

    //  get the sound status (on or off) to know whether or not to play sound
    static String getSoundStatus() {
        return soundStatus;
    }

    //  set sound status on or off
    static void setSoundStatus(String newStatus) {
        soundStatus = newStatus;
    }

/*    public static void main (String[] args) {

        //  set basic setting for settings screen & set visibility to true
        settingsFrame.setPreferredSize(new Dimension(750,450));
        settingsFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        settingsFrame.getContentPane().add(new GG_Settings());
        settingsFrame.getContentPane().setBackground(Color.BLUE);
        settingsFrame.pack();
        settingsFrame.setVisible(true);
    }*/

    @Override
    public void actionPerformed(ActionEvent event)  {

        //  get action which sent us to this method
        Object source = event.getSource();

        //  check for changing difficulty level
        //  actually changing the difficulty level is not part of this project
        if (source == difficulty) {
            if (difficulty.getSelectedItem().equals("Easy")) {
                System.out.println("Easy difficulty");
            }
            else if (difficulty.getSelectedItem().equals("Medium")) {
                System.out.println("Medium difficulty");
            } else {
                System.out.println("Difficult difficulty");
            }
        }
        //  check for changing background color
        else if (source == color) {
            if (color.getSelectedItem().equals("Red")) {
                settingsFrame.getContentPane().setBackground(Color.RED);
                RockTickFrame.frame.getContentPane().setBackground(Color.red);
            }
            else if (color.getSelectedItem().equals("Blue")) {
                settingsFrame.getContentPane().setBackground(Color.BLUE);
                RockTickFrame.frame.getContentPane().setBackground(Color.BLUE);
            }
            else if (color.getSelectedItem().equals("Green")) {
                settingsFrame.getContentPane().setBackground(darkGreen);
                RockTickFrame.frame.getContentPane().setBackground(darkGreen);
            }
        }
    }

    public void itemStateChanged(ItemEvent event) {

        // check for sound turned on or off
        int state = event.getStateChange();
        if (state == ItemEvent.SELECTED) {		// turning sound on
            setSoundStatus("On");
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src//tada.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                //  need this delay to actually hear the sound
                Thread.sleep(clip.getMicrosecondLength()/1000);

            }catch(Exception x) { x.printStackTrace(); }
        }
        else if (state == ItemEvent.DESELECTED) {		// turning sound off
            setSoundStatus("Off");
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int volume = source.getValue();
            //  this part was not implemented yet, but the slider does slide
/*           if (volume == 0) {
                System.out.println("Turn off sound");
            } else {
                System.out.println("Turn up/down sound");
            }*/

        }
    }
}