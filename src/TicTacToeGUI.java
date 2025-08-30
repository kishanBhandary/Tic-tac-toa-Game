
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameEnded = false;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe Gameeeeeeeeeerrfeeeee");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        Font font = new Font("Arial", Font.BOLD, 61);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded) return;

        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) return;

        clicked.setText(String.valueOf(currentPlayer));

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins! Congraultions;");
            gameEnded = true;
            askRestart();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw play again  play again pls !");
            gameEnded = true;
            askRestart();
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean checkWin() {
        String p = String.valueOf(currentPlayer);


        for (int i = 0; i < 3; i++) {
            if (p.equals(buttons[i][0].getText()) &&
                    p.equals(buttons[i][1].getText()) &&
                    p.equals(buttons[i][2].getText()))
                return true;
            if (p.equals(buttons[0][i].getText()) &&
                    p.equals(buttons[1][i].getText()) &&
                    p.equals(buttons[2][i].getText()))
                return true;
        }


        if (p.equals(buttons[0][0].getText()) &&
                p.equals(buttons[1][1].getText()) &&
                p.equals(buttons[2][2].getText()))
            return true;

        if (p.equals(buttons[0][2].getText()) &&
                p.equals(buttons[1][1].getText()) &&
                p.equals(buttons[2][0].getText()))
            return true;

        return false;
    }

    private boolean isBoardFull() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                if (btn.getText().equals(""))
                    return false;
        return true;
    }

    private void askRestart() {
        int choice = JOptionPane.showConfirmDialog(this, "Play again win again?", "Restart", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            resetBoard();
        } else {
            System.exit(0);
        }
    }

    private void resetBoard() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                btn.setText("");
        currentPlayer = 'X';
        gameEnded = false;
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
