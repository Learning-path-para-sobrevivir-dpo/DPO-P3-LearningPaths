package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotonesInicio extends JPanel implements ActionListener {
	/**
     * El comando para el botón para crear un nuevo perfil
     */
    private static final String NUEVO = "nuevo perfil";

    /**
     * El comando para el botón para iniciar sesión
     */
    private static final String INICIAR_SESION = "iniciar sesion";
    
    /**
     * El comando para salir de la aplicación
     */
    private static final String SALIR = "salir";
    
    private JButton butNuevo;
    private JButton butIniciarSesion;
    private JButton butSalir;
    private VentanaInicio ventanaInicio;
    
	public PanelBotonesInicio(VentanaInicio ventanaInicio) {
		this.ventanaInicio = ventanaInicio;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		butNuevo = new JButton("Crear perfil");
		butNuevo.addActionListener(this);
		butNuevo.setActionCommand(NUEVO);
		
		butIniciarSesion = new JButton("Iniciar Sesion");
		butIniciarSesion.addActionListener(this);
		butIniciarSesion.setActionCommand(INICIAR_SESION);
		
		butSalir = new JButton("Salir");
		butSalir.addActionListener(this);
		butSalir.setActionCommand(SALIR);
		
		add(butNuevo);
		add(butIniciarSesion);
		add(butSalir);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(NUEVO))
		{
			ventanaInicio.crearUsuario();
		}
		else if (comando.equals(INICIAR_SESION))
		{
			ventanaInicio.iniciarSesion();
		}
		else if (comando.equals(SALIR))
		{
			ventanaInicio.salir();
		}
	}

}
