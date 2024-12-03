package gui.crearUsuario;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelRecibirInfoUsuario extends JPanel {
	private JTextField login;
	private JTextField correo;
	private JPasswordField contraseña;
	private JPasswordField confirmacionContraseña;
	private JComboBox<String> tipo;
	public PanelRecibirInfoUsuario() {
		setLayout(new GridLayout(5, 2));
		
		JLabel l = new JLabel("Login:");
		JLabel c = new JLabel("Correo: ");
		JLabel co = new JLabel("Contraseña:");
		JLabel co2 = new JLabel("Confirmación contraseña:");
		JLabel t = new JLabel("Tipo:");
		l.setFont(new Font("Calibri", Font.PLAIN, 15));
		c.setFont(new Font("Calibri", Font.PLAIN, 15));
		co.setFont(new Font("Calibri", Font.PLAIN, 15));
		co2.setFont(new Font("Calibri", Font.PLAIN, 15));
		t.setFont(new Font("Calibri", Font.PLAIN, 15));
		
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
		return (String) login.getText().trim();
	}
	
	public String getCorreo()
	{
		return (String) correo.getText().trim();
	}
	
	public String getContrasenia()
	{
		return (String) contraseña.getSelectedText().trim();
	}
	
	public String getConfirmacionContrasenia()
	{
		return (String) confirmacionContraseña.getSelectedText().trim();
	}
	
	public String getTipo()
	{
		return (String) tipo.getSelectedItem();
	}
	
}
