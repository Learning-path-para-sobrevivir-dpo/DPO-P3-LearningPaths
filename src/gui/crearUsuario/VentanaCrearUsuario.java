package gui.crearUsuario;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.GUIManejoDatos;
import gui.VentanaPrincipal;
import modelo.Estudiante;
import modelo.Profesor;
import modelo.Usuario;

@SuppressWarnings("serial")
public class VentanaCrearUsuario extends JFrame {
	
	private PanelBotononesCrearUsuario panelBotones;
	private VentanaPrincipal ventanaInicio;
	private GUIManejoDatos datos;
	
	public VentanaCrearUsuario(VentanaPrincipal ventanaInicio, GUIManejoDatos datos) throws HeadlessException {
		
		this.ventanaInicio = ventanaInicio;
		this.datos = datos;
	}
	
	public void crearNuevoUsuario(String login, String correo, String contraseña, String tipo)
	{
		Usuario u = null;
		if (tipo.equals("Profesor"))
		{
			u = new Profesor(login, correo, contraseña, tipo);
		}
		else if (tipo.equals("Estudiante"))
		{
			u = new Estudiante(login, correo, contraseña, tipo);
		}
		
		if (u != null)
		{
			datos.añadirUsuario(u);
			JOptionPane.showMessageDialog(this, "Hubo un error creando su perfil. Intentelo de nuevo");
		}
		else {
			JOptionPane.showMessageDialog(this, "Hubo un error creando su perfil. Intentelo de nuevo");
		}
	}
	
	
	
}
