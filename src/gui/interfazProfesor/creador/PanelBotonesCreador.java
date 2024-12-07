package gui.interfazProfesor.Creador;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelBotonesCreador extends JPanel implements ActionListener {
	
	private static final String VERPATHS = "ver paths creados";
	private static final String CREARPATH = "crear path";
	private static final String VERREVIEWS = "ver reviews";
	private static final String EDITARPATH = "editar path";
	private static final String CLONARACT = "clonar actividad";
	private static final String CREARACT = "crear actividad";
	private static final String EDITARACT = "editar actividad";
	private static final String ADDREVIEW = "añadir review";
	private static final String REGRESAR = "regresar";
	
	private VentanaProfCreadorLP ventanaCreador;
	
	private JButton bVerLPCreados;
	private JButton bCrearLP;
	private JButton bVerReviews;
	private JButton bEditarLP;
	private JButton bClonarAct;
	private JButton bCrearAct;
	private JButton bEditarAct;
	private JButton bAddReview;
	private JButton butRegresar;
	
	
	public PanelBotonesCreador(VentanaProfCreadorLP ventanaCreador) {
		super();
		this.ventanaCreador = ventanaCreador;

		setLayout(new GridLayout(4,1,30,30));
		setBorder(new EmptyBorder(50,100,50,100));
		
		bVerLPCreados = new JButton("Ver todos mis Learning Paths");
		bVerLPCreados.addActionListener(this);
		bVerLPCreados.setActionCommand(VERPATHS);
		
		bCrearLP = new JButton("Crear Learning Path");
		bCrearLP.addActionListener(this);
		bCrearLP.setActionCommand(CREARPATH);
		
		bVerReviews = new JButton("Ver reseñas");
		bEditarLP = new JButton("Editar Learning Path");
		bClonarAct = new JButton("Clonar actividad");
		bCrearAct = new JButton("Crear actividad");
		bEditarAct = new JButton("Editar Actividad");
		bAddReview = new JButton("Añadir reseña a Actividad");

		butRegresar = new JButton("Regresar al menu de opciones");
		butRegresar.addActionListener(this);
		butRegresar.setActionCommand(REGRESAR);
		
		add(bVerLPCreados);
		add(bCrearLP);
		add(bCrearAct);
		add(bClonarAct);
		add(bEditarLP);
		add(bEditarAct);
		add(bVerReviews);
		add(bAddReview);
		add(butRegresar);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		
		if (comando.equals(REGRESAR))
		{
			ventanaCreador.regresar();
		}
		else if (comando.equals(VERPATHS)) {
			
			ventanaCreador.mostrarVentanaLPCreados();
		}
		else if (comando.equals(CREARPATH)) {
			
			ventanaCreador.mostrarVentanaCrearLP();
		}
		
	}
	
	

	

}
