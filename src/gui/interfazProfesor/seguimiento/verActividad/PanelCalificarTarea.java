package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class PanelCalificarTarea extends JPanel {
	
	private JComboBox<String> marcar;

	public PanelCalificarTarea() {
		setLayout(new GridLayout(2, 1, 40, 10));
		setBorder(new EmptyBorder(10,10,10,10));
		JLabel info = new JLabel("Como desea marcar esta Tarea");
		info.setFont(new Font("Calibri", Font.BOLD, 20));
		
		marcar = new JComboBox<String>(new String[] {"Exitosa", "No Exitosa"});
		JPanel miniPanel = new JPanel();
		miniPanel.add(marcar);
		miniPanel.setBorder(new EmptyBorder(20,30,20,30));
		
		add(info);
		add(miniPanel);
	}
	
	public String getMarcar()
	{
		return (String) marcar.getSelectedItem();
	}
}
