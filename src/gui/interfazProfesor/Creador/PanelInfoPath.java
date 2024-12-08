package gui.interfazProfesor.Creador;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.LearningPath;
import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelInfoPath extends JPanel implements ActionListener{
	
	private VentanaVerLPCreados ventanaCreados;
	
	private JLabel lTitulo;
	private JLabel lDescripcion;
	private JLabel lObjetivo;
	private JLabel lDificultad;
	private JLabel lRating;
	private JLabel lFechaCreacion;
	private JLabel lFechaModificacion;
	private JLabel lVersion;
	private JLabel lAutor;
	private JButton bCerrar;
    private static final String CERRAR = "cerrar";


	public PanelInfoPath(VentanaVerLPCreados ventanaCreados) {
		super();
		this.ventanaCreados=ventanaCreados;
    	this.setLayout(new GridLayout(10,1));
		
    	lTitulo = new JLabel("Titulo: ");
    	lTitulo.setHorizontalTextPosition(SwingConstants.LEFT);

    	lDescripcion = new JLabel("Descripción: ");
    	lDescripcion.setHorizontalTextPosition(SwingConstants.LEFT);
    	
       	lObjetivo = new JLabel("Objetivo: ");
       	lObjetivo.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	lDificultad = new JLabel("Nivel de Dificultad: ");
    	lDificultad.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	lRating = new JLabel("Rating: ");
    	lRating.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	lFechaCreacion = new JLabel("Fecha Creación: ");
    	lFechaCreacion.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	lFechaModificacion = new JLabel("Fecha Modificación: ");
    	lFechaModificacion.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	lVersion = new JLabel("Versión: ");
    	lVersion.setHorizontalTextPosition(SwingConstants.LEFT);

    	lAutor = new JLabel("Autor: ");
    	lAutor.setHorizontalTextPosition(SwingConstants.LEFT);

        bCerrar = new JButton("Cerrar");
        bCerrar.addActionListener(this);
        bCerrar.setVisible(true);
        bCerrar.setActionCommand(CERRAR);
		bCerrar.setFont(new Font("Calibri", Font.PLAIN, 18));
    	
    	this.add(lTitulo);
    	this.add(lDescripcion);
    	this.add(lObjetivo);
    	this.add(lDificultad);
    	this.add(lRating);
    	this.add(lFechaCreacion);
    	this.add(lFechaModificacion);
    	this.add(lVersion);
    	this.add(lAutor);
    	
        this.add(bCerrar);
    	
    	
	}

	
    protected void actualizarPath( LearningPath path )
    {
    	String titulo = path.getTitulo();
    	String descripcion = path.getDescripcion();
    	String objetivo = path.getObjetivo();
    	String dificultad = String.valueOf(path.getNivelDificultad());
    	String rating = String.valueOf(path.getRating());
    	String fechaCrea = path.getFechaCreacion();
    	String fechaMod = path.getFechaModificacion();
    	String version = String.valueOf(path.getVersion());
    	String autor = path.getAutor();
    	
    	    	
		lTitulo.setText("Titulo: "+ titulo);
		lDescripcion.setText("Descripción: "+descripcion);
		lObjetivo.setText("Objetivo: "+objetivo);
		lDificultad.setText("Nivel de Dificultad: "+dificultad);
		lRating.setText("Rating: "+rating);
		lFechaCreacion.setText("Fecha Creación: "+ fechaCrea);
		lFechaModificacion.setText("Fecha Modificación: "+fechaMod);
		lVersion.setText("Version: "+version);
		lAutor.setText("Autor: "+autor);

		this.repaint();
    	
    	
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        String comando = e.getActionCommand( );
        if( comando.equals( CERRAR ) )
        {
            ventanaCreados.cerrarVentana( );
        }
	}
}
