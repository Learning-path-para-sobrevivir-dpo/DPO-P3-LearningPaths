package gui.interfazProfesor.Creador;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelDetallesActSistema extends JPanel{
	
    private JLabel lTitulo;
    private JLabel lDescripcion;
    private JLabel lDificultad;
    private JLabel lDuracion;
    private JCheckBox chkObligatorio;
    private JLabel lTiempoSug;
    private JLabel lTipo;
    
    
	public PanelDetallesActSistema() {
		super();
		
    	this.setLayout(new GridLayout(7,1));
		
    	lTitulo = new JLabel("Titulo: ");
    	lTitulo.setHorizontalTextPosition(SwingConstants.LEFT);

    	lDescripcion = new JLabel("Descripción: ");
    	lDescripcion.setHorizontalTextPosition(SwingConstants.LEFT);

    	lDificultad = new JLabel("Nivel de Dificultad: ");
    	lDificultad.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	lDuracion = new JLabel("Duración: ");
    	lDuracion.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	chkObligatorio = new JCheckBox("Obligatorio: ");
    	chkObligatorio.setHorizontalTextPosition(SwingConstants.LEFT);
    	chkObligatorio.setEnabled(false);
    	
    	lTiempoSug = new JLabel("Tiempo Sugerido Completar: ");
    	lTiempoSug.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	lTipo = new JLabel("Tipo de Actividad: ");
    	lTipo.setHorizontalTextPosition(SwingConstants.LEFT);

    	this.add(lTitulo);
    	this.add(lDescripcion);
    	this.add(lDificultad);
    	this.add(lDuracion);
    	this.add(chkObligatorio);
    	this.add(lTiempoSug);
    	this.add(lTipo);
	}
	
	
    protected void actualizarActividad( Actividad act )
    {
    	String titulo = act.getTitulo();
    	String descripcion = act.getObjetivo();
    	String dificultad = String.valueOf(act.getNivelDificultad());
    	String duracion = String.valueOf(act.getDuracionMin());
    	boolean obligatorio = act.isObligatorio();
    	String tiempoSug = String.valueOf(act.getTiempoCompletarSugerido());
    	String tipo = act.getTipoActividad();
    	    	
		lTitulo.setText("Titulo: "+ titulo);
		lDescripcion.setText("Descripción: "+descripcion);
		lDificultad.setText("Nivel de Dificultad: "+dificultad);
		lDuracion.setText("Duración: "+duracion);
		chkObligatorio.setSelected(obligatorio);
		lTiempoSug.setText("Tiempo Sugerido Completar: "+tiempoSug);
		lTipo.setText("Tipo de Actividad: "+tipo);

		this.repaint();
    	
    	
    }
    
	
}
