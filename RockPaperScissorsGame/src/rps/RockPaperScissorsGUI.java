package rps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsGUI extends JFrame implements ActionListener {
    
    // Game components
    private JButton rockButton, paperButton, scissorsButton, resetButton;
    private JLabel playerChoiceLabel, computerChoiceLabel, resultLabel;
    private JLabel playerScoreLabel, computerScoreLabel, roundLabel;
    private JPanel mainPanel, buttonPanel, scorePanel, choicePanel;
    
    // Game state
    private int playerScore = 0;
    private int computerScore = 0;
    private int roundNumber = 1;
    private Random random = new Random();
    
    // Choice constants
    private static final String ROCK = "Rock";
    private static final String PAPER = "Paper";
    private static final String SCISSORS = "Scissors";
    
    public RockPaperScissorsGUI() {
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Rock-Paper-Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        
        // Create main panel
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Create title
        JLabel titleLabel = new JLabel("Rock-Paper-Scissors", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 25, 112));
        
        // Create score panel
        createScorePanel();
        
        // Create choice display panel
        createChoicePanel();
        
        // Create button panel
        createButtonPanel();
        
        // Create result label
        resultLabel = new JLabel("Choose your move!", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setForeground(new Color(220, 20, 60));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Add components to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scorePanel, BorderLayout.CENTER);
        mainPanel.add(choicePanel, BorderLayout.EAST);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);
        
        // Add main panel and button panel to frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Set window properties
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void createScorePanel() {
        scorePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        scorePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), "Score Board"));
        scorePanel.setBackground(new Color(240, 248, 255));
        
        // Round number
        JLabel roundTitleLabel = new JLabel("Round:", JLabel.CENTER);
        roundTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        roundLabel = new JLabel(String.valueOf(roundNumber), JLabel.CENTER);
        roundLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Player score
        JLabel playerTitleLabel = new JLabel("Player Score:", JLabel.CENTER);
        playerTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerTitleLabel.setForeground(new Color(34, 139, 34));
        playerScoreLabel = new JLabel(String.valueOf(playerScore), JLabel.CENTER);
        playerScoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        playerScoreLabel.setForeground(new Color(34, 139, 34));
        
        // Computer score
        JLabel computerTitleLabel = new JLabel("Computer Score:", JLabel.CENTER);
        computerTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        computerTitleLabel.setForeground(new Color(178, 34, 34));
        computerScoreLabel = new JLabel(String.valueOf(computerScore), JLabel.CENTER);
        computerScoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        computerScoreLabel.setForeground(new Color(178, 34, 34));
        
        scorePanel.add(roundTitleLabel);
        scorePanel.add(roundLabel);
        scorePanel.add(playerTitleLabel);
        scorePanel.add(playerScoreLabel);
        scorePanel.add(computerTitleLabel);
        scorePanel.add(computerScoreLabel);
    }
    
    private void createChoicePanel() {
        choicePanel = new JPanel(new GridLayout(2, 1, 5, 10));
        choicePanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), "Choices"));
        choicePanel.setBackground(new Color(240, 248, 255));
        
        // Player choice
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBackground(new Color(240, 248, 255));
        JLabel playerTitle = new JLabel("Your Choice:", JLabel.CENTER);
        playerTitle.setFont(new Font("Arial", Font.BOLD, 12));
        playerTitle.setForeground(new Color(34, 139, 34));
        playerChoiceLabel = new JLabel("?", JLabel.CENTER);
        playerChoiceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        playerChoiceLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        playerChoiceLabel.setOpaque(true);
        playerChoiceLabel.setBackground(Color.WHITE);
        playerPanel.add(playerTitle, BorderLayout.NORTH);
        playerPanel.add(playerChoiceLabel, BorderLayout.CENTER);
        
        // Computer choice
        JPanel computerPanel = new JPanel(new BorderLayout());
        computerPanel.setBackground(new Color(240, 248, 255));
        JLabel computerTitle = new JLabel("Computer Choice:", JLabel.CENTER);
        computerTitle.setFont(new Font("Arial", Font.BOLD, 12));
        computerTitle.setForeground(new Color(178, 34, 34));
        computerChoiceLabel = new JLabel("?", JLabel.CENTER);
        computerChoiceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        computerChoiceLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        computerChoiceLabel.setOpaque(true);
        computerChoiceLabel.setBackground(Color.WHITE);
        computerPanel.add(computerTitle, BorderLayout.NORTH);
        computerPanel.add(computerChoiceLabel, BorderLayout.CENTER);
        
        choicePanel.add(playerPanel);
        choicePanel.add(computerPanel);
    }
    
    private void createButtonPanel() {
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Create game buttons
        rockButton = createGameButton("ROCK", new Color(139, 69, 19));
        paperButton = createGameButton("PAPER", new Color(70, 130, 180));
        scissorsButton = createGameButton("SCISSORS", new Color(220, 20, 60));
        
        // Create reset button
        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setPreferredSize(new Dimension(120, 40));
        resetButton.setBackground(new Color(255, 165, 0));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(this);
        
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(resetButton);
    }
    
    private JButton createGameButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            resetGame();
            return;
        }
        
        String playerChoice = "";
        String playerDisplay = "";
        
        if (e.getSource() == rockButton) {
            playerChoice = ROCK;
            playerDisplay = "ROCK";
        } else if (e.getSource() == paperButton) {
            playerChoice = PAPER;
            playerDisplay = "PAPER";
        } else if (e.getSource() == scissorsButton) {
            playerChoice = SCISSORS;
            playerDisplay = "SCISSORS";
        }
        
        // Generate computer choice
        String[] choices = {ROCK, PAPER, SCISSORS};
        String[] displays = {"ROCK", "PAPER", "SCISSORS"};
        int computerIndex = random.nextInt(3);
        String computerChoice = choices[computerIndex];
        String computerDisplay = displays[computerIndex];
        
        // Update choice displays
        playerChoiceLabel.setText(playerDisplay);
        playerChoiceLabel.setForeground(new Color(34, 139, 34));
        computerChoiceLabel.setText(computerDisplay);
        computerChoiceLabel.setForeground(new Color(178, 34, 34));
        
        // Determine winner
        String result = determineWinner(playerChoice, computerChoice);
        
        // Update score
        if (result.equals("You win!")) {
            playerScore++;
            resultLabel.setText(result + " " + playerChoice + " beats " + computerChoice);
            resultLabel.setForeground(new Color(34, 139, 34));
        } else if (result.equals("Computer wins!")) {
            computerScore++;
            resultLabel.setText(result + " " + computerChoice + " beats " + playerChoice);
            resultLabel.setForeground(new Color(178, 34, 34));
        } else {
            resultLabel.setText(result + " Both chose " + playerChoice);
            resultLabel.setForeground(new Color(255, 165, 0));
        }
        
        // Update displays
        roundNumber++;
        updateScoreDisplay();
        
        // Check for game end (optional: end at 10 points)
        if (playerScore >= 10 || computerScore >= 10) {
            String winner = playerScore >= 10 ? "Player" : "Computer";
            int option = JOptionPane.showConfirmDialog(this,
                winner + " wins the game!\nFinal Score - Player: " + playerScore + 
                ", Computer: " + computerScore + "\n\nPlay again?",
                "Game Over", JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }
    
    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a tie!";
        }
        
        if ((playerChoice.equals(ROCK) && computerChoice.equals(SCISSORS)) ||
            (playerChoice.equals(SCISSORS) && computerChoice.equals(PAPER)) ||
            (playerChoice.equals(PAPER) && computerChoice.equals(ROCK))) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }
    
    private void updateScoreDisplay() {
        playerScoreLabel.setText(String.valueOf(playerScore));
        computerScoreLabel.setText(String.valueOf(computerScore));
        roundLabel.setText(String.valueOf(roundNumber));
    }
    
    private void resetGame() {
        playerScore = 0;
        computerScore = 0;
        roundNumber = 1;
        playerChoiceLabel.setText("?");
        playerChoiceLabel.setForeground(Color.BLACK);
        computerChoiceLabel.setText("?");
        computerChoiceLabel.setForeground(Color.BLACK);
        resultLabel.setText("Choose your move!");
        resultLabel.setForeground(new Color(220, 20, 60));
        updateScoreDisplay();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RockPaperScissorsGUI();
        });
    }
}