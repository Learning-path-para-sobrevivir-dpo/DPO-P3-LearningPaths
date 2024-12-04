package gui.crearUsuario;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelRecibirInfoUsuario extends JPanel {
	private JTextField login;
	private JTextField correo;
	private JPasswordField contraseña;
	private JPasswordField confirmacionContraseña;
	private JComboBox<String> tipo;
	
	public PanelRecibirInfoUsuario() {
		setLayout(new GridLayout(5, 2, 30, 30));
		setBorder(new EmptyBorder(30,30,30,30));
		
		JLabel l = new JLabel("Login:");
		JLabel c = new JLabel("Correo: ");
		JLabel co = new JLabel("Contraseña:");
		JLabel co2 = new JLabel("Confirmación contraseña:");
		JLabel t = new JLabel("Tipo:");
		l.setFont(new Font("Calibri", Font.BOLD, 20));
		c.setFont(new Font("Calibri", Font.BOLD, 20));
		co.setFont(new Font("Calibri", Font.BOLD, 20));
		co2.setFont(new Font("Calibri", Font.BOLD, 20));
		t.setFont(new Font("Calibri", Font.BOLD, 20));
		
		login = new JTextField();
		correo = new JTextField();
		contraseña = new JPasswordField();
		confirmacionContraseña = new JPasswordField();
		tipo = new JComboBox<String>(new String[] {"Profesor", "Estudiante"});
		
		add(l);
		add(login);
		add(c);
		add(correo);
		add(co);
		add(contraseña);
		add(co2);
		add(confirmacionContraseña);
		add(t);
		add(tipo);
	}
	
	public String getLogin()
	{
		return (String) login.getText();
	}
	
	public String getCorreo()
	{
		return (String) correo.getText();
	}
	
	public String getContrasenia()
	{
		return String.valueOf(contraseña.getPassword());
	}
	
	public String getConfirmacionContrasenia()
	{
		return String.valueOf(confirmacionContraseña.getPassword());
	}
	
	public String getTipo()
	{
		return (String) tipo.getSelectedItem();
	}
	
}
