package gui.iniciarSesion;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.GUIManejoDatos;
import gui.PanelHeader;
import gui.VentanaPrincipal;
import gui.interfazEstudiante.VentanaEstudiante;
import gui.interfazProfesor.VentanaProfesor;
import modelo.Estudiante;
import modelo.Profesor;
import modelo.Usuario;

@SuppressWarnings("serial")
public class VentanaInicioSesion extends JFrame {
	
	private VentanaPrincipal ventanaInicio;
	private GUIManejoDatos datos;
	
	private VentanaProfesor ventanaProf;
	private VentanaEstudiante ventanaEstudiante;
	
	private PanelHeader header;
	private PanelInfoUsuario detallesUsuario;
	private PanelBotonesInicioSesion panelBotones;
	
	public VentanaInicioSesion(VentanaPrincipal ventanaInicio, GUIManejoDatos datos) throws HeadlessException {
		
		this.ventanaInicio = ventanaInicio;
		this.datos = datos;
		setLayout(new BorderLayout());
		
		header = new PanelHeader("Iniciar Sesion");
		detallesUsuario = new PanelInfoUsuario();
		panelBotones = new PanelBotonesInicioSesion(this);
		
		add(header, BorderLayout.NORTH);
		add(detallesUsuario, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		setTitle( "Iniciar Sesion" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 300 );
        setLocationRelativeTo( null );
        setVisible( true );
	}

	public void volverInicio() {
		ventanaInicio.setVisible(true);
		dispose( );
	}

	public void iniciarSesion() {
		String loginUsuario = detallesUsuario.getLogin();
		String contraseniaUsuario = detallesUsuario.getContrasenia();
		if (loginUsuario == null || loginUsuario.equals("") || contraseniaUsuario == null || contraseniaUsuario.equals(""))
		{
			JOptionPane.showMessageDialog(this,"Asegurese de llenar todos los espacios en blanco");
		}
		else
		{
			Usuario u = datos.getUsuario(loginUsuario, contraseniaUsuario);
			if (u == null)
			{
				JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
			}
			else
			{
				if (u instanceof Profesor)
				{
					Profesor p = (Profesor) u;
					JOptionPane.showMessageDialog(this, "Inicio de Sesión Exitosa");
					mostrarVentanaProfesor(p);
					dispose();
				}
				else if (u instanceof Estudiante)
				{
					Estudiante e = (Estudiante) u;
					JOptionPane.showMessageDialog(this, "Inicio de Sesión Exitosa");
					mostrarVentanaEstudiante(e);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Ocurrio un error en el inicio de sesion. Vuelva a intentarlo más tarde");
				}
			}
		}
	}
	
	public void mostrarVentanaProfesor(Profesor p)
	{
		if (ventanaProf == null || !ventanaProf.isVisible())
		{
			ventanaProf = new VentanaProfesor(p, datos, ventanaInicio);
			ventanaProf.setVisible(true);
		}
	}
	
	public void mostrarVentanaEstudiante(Estudiante e)
	{
		if (ventanaEstudiante == null || !ventanaEstudiante.isVisible())
		{
			ventanaEstudiante = new VentanaEstudiante(e, datos, ventanaInicio);
			ventanaEstudiante.setVisible(true);
		}
	}
	
}
