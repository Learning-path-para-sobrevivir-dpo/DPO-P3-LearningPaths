package gui.crearUsuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import gui.VentanaPrincipal;
import modelo.Estudiante;
import modelo.Profesor;
import modelo.Usuario;

@SuppressWarnings("serial")
public class VentanaCrearUsuario extends JFrame {
	
	private VentanaPrincipal ventanaInicio;
	private GUIManejoDatos datos;
	
	private PanelBotononesCrearUsuario panelBotones;
	private PanelRecibirInfoUsuario detallesUsuario;
	
	public VentanaCrearUsuario(VentanaPrincipal ventanaInicio, GUIManejoDatos datos) throws HeadlessException {
		
		this.ventanaInicio = ventanaInicio;
		this.datos = datos;
		setLayout(new BorderLayout());
		
		panelBotones = new PanelBotononesCrearUsuario(this);
		detallesUsuario = new PanelRecibirInfoUsuario();
		
		PanelHeader header = new PanelHeader("Crear Usuario");
		
		add(header, BorderLayout.NORTH);
		add(detallesUsuario, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		setTitle( "Crear Usuario" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 600, 500 );
        setLocationRelativeTo( null );
        setVisible( true );
	}
	
	public void crearNuevoUsuario()
	{
		String login = detallesUsuario.getLogin(); 
		String correo = detallesUsuario.getCorreo();
		String contraseña = detallesUsuario.getContrasenia(); 
		String tipo = detallesUsuario.getTipo();
		Usuario u = null;
		if (login == null || login.equals("") || correo == null|| correo.equals("") ||
				contraseña == null || contraseña.equals("") || tipo == null || tipo.equals(""))
		{
			JOptionPane.showMessageDialog(this, "Asegurese de llenar todos los campos");
		}
		else {
			boolean existe = existeLogin();
			boolean coinciden = coincidenContrasenia();
			if (existe)
			{
				JOptionPane.showMessageDialog(this, "El login ingresado ya esta en uso");
			}
			else if (!coinciden)
			{
				JOptionPane.showMessageDialog(this, "Las contraseñas ingresadas no coinciden");
			}
			else 
			{
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
					JOptionPane.showMessageDialog(this, "Usuario creado exitosamente");
					volverInicio();
				}
				else {
					JOptionPane.showMessageDialog(this, "Hubo un error creando su perfil. Intentelo de nuevo");
				}
			}
		}
	}
	
	public void volverInicio()
	{
		ventanaInicio.setVisible(true);
		dispose();
	}

	public boolean existeLogin() {
		String login = detallesUsuario.getLogin();
		return datos.existeUsuario(login);
	}

	public boolean coincidenContrasenia() {
		boolean coinciden = true;
		String contrasenia = detallesUsuario.getContrasenia();
		String confirmacion = detallesUsuario.getConfirmacionContrasenia();
		if (confirmacion != null)
		{
			if (!contrasenia.equals(confirmacion))
			{
				coinciden = false;
			}
		}

		return coinciden;
	}
	
}
