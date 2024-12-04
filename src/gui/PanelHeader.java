package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelHeader extends JPanel {
	private String title;

	public PanelHeader(String title) {
		JLabel titulo = new JLabel(title);
		titulo.setFont(new Font("Calibri", Font.BOLD, 30));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(20, 0, 20, 0));
		setBackground(new Color(236, 145, 146));
		add(titulo);
	}
	
}
