package Presentacion.ComponentsBuilder;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ComponentsBuilder {

	public static JPanel createPanel(int x, int y, int width, int height) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, width, height);
		return panel;

	}

	public static JLabel createLabel(String text, int x, int y, int width, int height, Color color) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, width, height);
		label.setForeground(color);
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}

	public static JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		return button;

	}

	public static JButton createBackButton() {
		JButton button = new JButton();

		ImageIcon icon = new ImageIcon("imagen/back.png");
		Image newImg = icon.getImage().getScaledInstance(80, 60, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		button.setIcon(icon);

		button.setBounds(40, 25, 80, 60);
		button.setToolTipText("Atras");
		return button;
	}

	public JPanel createConfirmMsg(int x, int y, int width, int height) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public static JTable createTable(int filas, int columnas, String[] columns) {
		JTable table = new JTable();
		table.setModel(new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			@Override
			public String getColumnName(int index) {
				return columns[index];
			}

			@Override
			public int getColumnCount() {
				return columnas;
			}

			@Override
			public int getRowCount() {
				return filas;
			}
		});
		return table;
	}
}
