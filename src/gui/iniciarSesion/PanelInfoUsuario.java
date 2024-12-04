package gui.iniciarSesion;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelInfoUsuario extends JPanel {
	private JTextField login;
	private JPasswordField contrasenia;
	
	public PanelInfoUsuario() {
		setLayout(new GridLayout(2,2, 30, 30));
		setBorder(new EmptyBorder(30,30,30,30));
		
		JLabel l = new JLabel("Login:");
		JLabel c = new JLabel("Contraseña:");
		l.setFont(new Font("Calibri", Font.BOLD, 20));
		c.setFont(new Font("Calibri", Font.BOLD, 20));
		
		login = new JTextField();
		contrasenia = new JPasswordField();
		
		add(l);
		add(login);
		add(c);
		add(contrasenia);
	}
	
	public String getLogin() {
		return (String) login.getText();
	}
	
	public String getContrasenia() {
		return String.valueOf(contrasenia.getPassword());
	}
}
