package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
    private VentanaPrincipal ventanaInicio;
    
	public PanelBotonesInicio(VentanaPrincipal ventanaInicio) {
		this.ventanaInicio = ventanaInicio;
		setLayout(new GridLayout(3,1,20,20));
		setBorder(new EmptyBorder(50,200,50,200));
		//setBackground(new Color(236, 145, 146));
		
		butNuevo = new JButton("Crear perfil");
		butNuevo.addActionListener(this);
		butNuevo.setActionCommand(NUEVO);
		butNuevo.setFont(new Font("Calibri", Font.PLAIN, 18));
		
		butIniciarSesion = new JButton("Iniciar Sesion");
		butIniciarSesion.addActionListener(this);
		butIniciarSesion.setActionCommand(INICIAR_SESION);
		butIniciarSesion.setFont(new Font("Calibri", Font.PLAIN, 18));
		
		butSalir = new JButton("Salir");
		butSalir.addActionListener(this);
		butSalir.setActionCommand(SALIR);
		butSalir.setFont(new Font("Calibri", Font.PLAIN, 18));
		
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
