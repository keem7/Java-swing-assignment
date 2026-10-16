import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Java Swing First Individual Assignment
 * Author: Abdul Hakeem Gibril Kargbo
 */
public class JavaSwingAssignment extends JFrame {

    private JLabel welcomeLabel;
    private JPanel mainPanel;
    private boolean isOriginalText = true;
    private int colorIndex = 0;
    private ImageIcon appIcon;

    // Direct path to your image file
    private static final String IMAGE_PATH = "C:\\Users\\mapep\\Downloads\\images.jpg";

    // Array of light background colors for cycling
    private final Color[] bgColors = {
            new Color(0xF0, 0xF0, 0xF0), // Light Gray (Default)
            new Color(0xE3, 0xF2, 0xFD), // Soft Blue
            new Color(0xE8, 0xF5, 0xE9), // Soft Green
            new Color(0xFF, 0xF3, 0xE0)  // Soft Orange
    };

    public JavaSwingAssignment() {
        // Load the image directly from your Downloads folder
        appIcon = loadIconFromPath(IMAGE_PATH);

        // Set window properties
        setTitle("Java Swing Assignment");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        // Set top-left window icon and taskbar icon
        if (appIcon != null) {
            setIconImage(appIcon.getImage());
        }

        // Main panel setup
        mainPanel = new JPanel();
        mainPanel.setBackground(bgColors[0]);
        mainPanel.setLayout(new GridBagLayout());

        // Vertical container to keep components centered and neatly spaced
        Box verticalContainer = Box.createVerticalBox();

        // 0. Center Logo Image
        JLabel logoLabel = new JLabel();
        if (appIcon != null) {
            // Smoothly scale the image to 120x120 for optimal display
            Image scaledImg = appIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImg));
        } else {
            logoLabel.setText("[Image Not Found]");
            logoLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
        }
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 1. Scalable Text Label using HTML wrapping
        welcomeLabel = new JLabel(
                "<html><div style='text-align: center; width: 600px;'>" +
                        "Welcome to Java Swing First Individual Assignment<br>by Abdul Hakeem Gibril Kargbo" +
                        "</div></html>",
                SwingConstants.CENTER
        );
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(0x22, 0x22, 0x22));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. Buttons Setup
        JButton changeTextBtn = new JButton("Change Text");
        changeTextBtn.setPreferredSize(new Dimension(200, 40));
        changeTextBtn.setMaximumSize(new Dimension(200, 40));
        changeTextBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changeTextBtn.setFocusable(false);

        JButton changeBgBtn = new JButton("Change Background");
        changeBgBtn.setPreferredSize(new Dimension(200, 40));
        changeBgBtn.setMaximumSize(new Dimension(200, 40));
        changeBgBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changeBgBtn.setFocusable(false);

        // Horizontal panel for side-by-side buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(changeTextBtn);
        buttonPanel.add(changeBgBtn);

        // Add action listener to Change Text button
        changeTextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isOriginalText) {
                    welcomeLabel.setText(
                            "<html><div style='text-align: center; width: 600px;'>" +
                                    "Text Successfully Changed!<br>Java Swing Event Handling Active" +
                                    "</div></html>"
                    );
                } else {
                    welcomeLabel.setText(
                            "<html><div style='text-align: center; width: 600px;'>" +
                                    "Welcome to Java Swing First Individual Assignment<br>by Abdul Hakeem Gibril Kargbo" +
                                    "</div></html>"
                    );
                }
                isOriginalText = !isOriginalText;
            }
        });

        // Add action listener to Change Background button
        changeBgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorIndex = (colorIndex + 1) % bgColors.length;
                mainPanel.setBackground(bgColors[colorIndex]);
                mainPanel.repaint();
            }
        });

        // Assemble layout components in vertical sequence
        verticalContainer.add(logoLabel);
        verticalContainer.add(Box.createRigidArea(new Dimension(0, 20))); // Space below logo
        verticalContainer.add(welcomeLabel);
        verticalContainer.add(Box.createRigidArea(new Dimension(0, 30))); // Space below text
        verticalContainer.add(buttonPanel);

        // Center vertical container inside mainPanel
        mainPanel.add(verticalContainer);

        // Add main panel to top-level window frame
        add(mainPanel);
    }

    /**
     * Loads an image directly from a specified file path string.
     */
    private ImageIcon loadIconFromPath(String path) {
        File file = new File(path);
        if (file.exists()) {
            return new ImageIcon(file.getAbsolutePath());
        } else {
            System.err.println("File not found at path: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        // Ensure UI runs on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new JavaSwingAssignment().setVisible(true);
        });
    }