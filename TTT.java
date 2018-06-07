package lecture26;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
	public static final int board_size = 3;

	public static enum gamestatus {
		Xwins, Zwins, Incomplete, Tie;
	}

	JButton[][] buttons = new JButton[board_size][board_size];
	boolean crossturn = true;

	TTT() {
		super.setTitle("tic tac toe");
		super.setSize(800, 800);
		GridLayout grid = new GridLayout(board_size, board_size);
		super.setLayout(grid);
		Font font = new Font("Comic sans", 1, 150);

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				JButton button = new JButton("");
				buttons[i][j] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(true);
		super.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		makeMove(button);

		gamestatus gs = getgamestatus();
		if (gs == gamestatus.Incomplete) {
			return;
		}

		declarewinner(gs);
		int choice = JOptionPane.showConfirmDialog(this, "Do you want to play another game ?");
		if (choice == JOptionPane.YES_OPTION) {
			for (int i = 0; i < board_size; i++) {
				for (int j = 0; j < board_size; j++) {
					buttons[i][j].setText("");
				}
			}
			crossturn = true;
		} else {
			super.dispose();
		}
	}

	private void makeMove(JButton button) {
		String text = button.getText();
		if (text.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");
			return;
		}

		if (crossturn) {
			button.setText("X");
		} else {
			button.setText("0");
		}
		crossturn = !crossturn;
	}

	private void declarewinner(gamestatus gs) {
		if (gs == gamestatus.Xwins) {
			JOptionPane.showMessageDialog(this, "Xwins");
		} else if (gs == gamestatus.Zwins) {
			JOptionPane.showMessageDialog(this, "Zwins");
		} else {
			JOptionPane.showMessageDialog(this, "Tie");
		}
	}

	private gamestatus getgamestatus() {
		String text1 = "", text2 = "";
		int row, col;

		// CASE-1
		row = 0;
		col = 0;
		while (row < board_size) {
			col = 0;
			while (col < board_size - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0 || text2.length() == 0) {
					break;
				}
				col++;
			}
			if (col == board_size - 1) {
				if (text1.equals("X")) {
					return gamestatus.Xwins;
				} else {
					return gamestatus.Zwins;
				}
			}
			row++;
		}

		// CASE-2
		row = 0;
		col = 0;
		while (col < board_size) {
			row = 0;
			while (row < board_size - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0 || text2.length() == 0) {
					break;
				}
				row++;
			}
			if (row == board_size - 1) {
				if (text1.equals("X")) {
					return gamestatus.Xwins;
				} else {
					return gamestatus.Zwins;
				}
			}
			col++;
		}

		// CASE-3
		row = 0;
		col = 0;
		while (row < board_size - 1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0 || text2.length() == 0) {
				break;
			}
			row++;
			col++;
		}
		if (row == board_size - 1) {
			if (text1.equals("X")) {
				return gamestatus.Xwins;
			} else {
				return gamestatus.Zwins;
			}
		}

		// CASE-4
		row = board_size - 1;
		col = 0;
		while (row > 0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row - 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0 || text2.length() == 0) {
				break;
			}
			row--;
			col++;
		}
		if (row == 0) {
			if (text1.equals("X")) {
				return gamestatus.Xwins;
			} else {
				return gamestatus.Zwins;
			}
		}

		// CASE-5
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				String text = buttons[i][j].getText();
				if (text.length() == 0) {
					return gamestatus.Incomplete;
				}
			}
		}

		// CASE-6
		return gamestatus.Tie;

	}

}
