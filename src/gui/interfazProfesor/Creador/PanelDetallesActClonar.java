package gui.interfazProfesor.Creador;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.actividades.Actividad;

@SuppressWarnings("serial")
public class PanelDetallesActClonar  extends JPanel implements ActionListener{
	
	private VentanaClonarAct ventanaClonar;
    private JLabel lTitulo;
    private JLabel lDescripcion;
    private JLabel lDificultad;
    private JLabel lDuracion;
    private JCheckBox chkObligatorio;
    private JLabel lTiempoSug;
    private JLabel lTipo;
    
    private JButton addAct;
    private static final String CLONAR = "clonar actividad";
    
    private JButton bCerrar;
    private static final String CERRAR = "cerrar";
       
       
	public PanelDetallesActClonar(VentanaClonarAct ventanaClonar) {
		super();
		this.ventanaClonar = ventanaClonar;
		
    	this.setLayout(new GridLayout(9,1));
		
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
    	
        addAct = new JButton("Clonar Actividad");
        addAct.addActionListener(this);
        addAct.setVisible(true);
        addAct.setActionCommand(CLONAR);
        addAct.setFont(new Font("Calibri", Font.PLAIN, 16));
        
        bCerrar = new JButton("Cerrar");
        bCerrar.addActionListener(this);
        bCerrar.setVisible(true);
        bCerrar.setActionCommand(CERRAR);
        bCerrar.setFont(new Font("Calibri", Font.PLAIN, 16));
        
    	this.add(lTitulo);
    	this.add(lDescripcion);
    	this.add(lDificultad);
    	this.add(lDuracion);
    	this.add(chkObligatorio);
    	this.add(lTiempoSug);
    	this.add(lTipo);
    	this.add(addAct);
    	this.add(bCerrar);

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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        String comando = e.getActionCommand( );
        if( comando.equals( CLONAR ) )
        {
			ventanaClonar.clonarActividad();
			ventanaClonar.cerrarVentana();
        }else if( comando.equals( CERRAR ) )
        {
			ventanaClonar.cerrarVentana();
        }
		
	}
    
	
}
