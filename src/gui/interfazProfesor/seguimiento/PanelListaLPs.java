package gui.interfazProfesor.seguimiento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.LearningPath;

@SuppressWarnings("serial")
public class PanelListaLPs extends JPanel implements ActionListener{
	
	private static final String VER = "ver";
	
	private JList<LearningPath> listaLearningPaths;
	private PanelListaSeguimiento panelListaPrincipal;
	private JButton butSeleccionar;
	private JScrollPane scroll;
	
	public PanelListaLPs(JList<LearningPath> listaLearningPaths, PanelListaSeguimiento panelListaPrincipal) {
		this.listaLearningPaths = listaLearningPaths;
		this.panelListaPrincipal = panelListaPrincipal;
		setLayout(new BorderLayout());
		
		scroll = new JScrollPane();
		scroll.setBounds(20, 120,500, 500);
		scroll.setViewportView(listaLearningPaths);
		
		butSeleccionar = new JButton("Ver estudiantes");
		butSeleccionar.addActionListener(this);
		butSeleccionar.setActionCommand(VER);
		
		JPanel panelBoton = new JPanel();
		panelBoton.setLayout(new FlowLayout());
		panelBoton.add(butSeleccionar);
		
		add(scroll, BorderLayout.CENTER);
		add(panelBoton, BorderLayout.SOUTH);
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(VER))
		{
			panelListaPrincipal.mostrarProgresosLP();
		}
	}
		
	

}
