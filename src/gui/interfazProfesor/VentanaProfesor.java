package gui.interfazProfesor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GUIManejoDatos;
import gui.VentanaPrincipal;
import gui.crearUsuario.VentanaCrearUsuario;
import gui.interfazProfesor.Creador.VentanaProfCreadorLP;
import modelo.Profesor;


public class VentanaProfesor extends JFrame implements ActionListener{
	
	private VentanaPrincipal ventanaInicio;
	private VentanaProfCreadorLP ventanaCreador;
	
	private Profesor prof;
	private GUIManejoDatos datos;
	
    private static final String CREADOR = "creador";
	
	public VentanaProfesor(Profesor p, GUIManejoDatos datos, VentanaPrincipal ventanaInicio) {
		// TODO Auto-generated constructor stub
		this.prof = p;
		this.datos = datos;
		this.ventanaInicio = ventanaInicio;
		setLayout(new BorderLayout());
		
		JLabel intro = new JLabel ("Profesor(a), que desea hacer hoy: ");
		
		JPanel panelBotones = new JPanel();
		JButton butCreadorLP = new JButton("Gestionar Learning Paths");
		butCreadorLP.addActionListener(this);
		butCreadorLP.setActionCommand(CREADOR);
		panelBotones.add(butCreadorLP);
		
		add(intro, BorderLayout.NORTH);
		add(panelBotones, BorderLayout.CENTER);
		

		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
        setSize( 600, 400 );
        setTitle( "Profesor" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(CREADOR))
		{
			this.mostrarVentanaCreador();
		}
		
	}
	
	public void mostrarVentanaCreador() {
		// TODO Auto-generated method stub
		if (ventanaCreador == null || !ventanaCreador.isVisible())
		{
			ventanaCreador = new VentanaProfCreadorLP(this, prof, datos);
			ventanaCreador.setVisible(true);
			setVisible(false);
		}
	}

}
