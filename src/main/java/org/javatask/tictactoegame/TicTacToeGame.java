package org.javatask.tictactoegame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;

    public TicTacToeGame() {
        buttons = new JButton[3][3];
        currentPlayer = 'X';

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Tic-Tac-Toe Game");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            if (clickedButton.getText().equals("")) {
                clickedButton.setText(String.valueOf(currentPlayer));
                if (checkForWinner()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][j].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][j].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToeGame();
        });
    }
}
