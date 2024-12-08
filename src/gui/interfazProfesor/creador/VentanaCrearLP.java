package gui.interfazProfesor.Creador;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import gui.PanelHeader;



@SuppressWarnings("serial")
public class VentanaCrearLP extends JFrame{
	
	private VentanaProfCreadorLP ventanaCreador;
	
	private PanelBotonesCrearLP pBotones;
	private PanelDetallesCrearLP pDetalles;
	
	
	public VentanaCrearLP(VentanaProfCreadorLP ventanaCreador) {
		super();
		this.ventanaCreador = ventanaCreador;

		setLayout(new BorderLayout());
		
		PanelHeader header = new PanelHeader("Crear Learning Path");
		add(header, BorderLayout.NORTH);
		
        pDetalles = new PanelDetallesCrearLP();
        add(pDetalles);
        
        pBotones = new PanelBotonesCrearLP(this);
        add(pBotones, BorderLayout.SOUTH);

		setVisible(true);
		
		pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( true );
        setSize( 500, 400 );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

		
	}

	   public void agregarLearningPath( )
	    {

	    }

	    /**
	     * Cierra la ventana sin crear un nuevo restaurante
	     */
	    public void cerrarVentana( )
	    {
	    	ventanaCreador.setVisible(true);
	        dispose( );
	    }

	
}
