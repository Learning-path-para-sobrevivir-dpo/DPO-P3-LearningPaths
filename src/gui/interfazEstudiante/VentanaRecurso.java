package gui.interfazEstudiante;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modelo.Progreso;
import modelo.actividades.RecursoEducativo;

public class VentanaRecurso {

	public VentanaRecurso(RecursoEducativo recurso, Progreso progreso, JTextArea outputArea) {
		JFrame recursoFrame = new JFrame("Recurso Educativo: " + recurso.getTitulo());
        recursoFrame.setSize(400, 300);
        recursoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        recursoFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        recursoFrame.add(panel);

        JLabel label = new JLabel("Contenido del Recurso: " + recurso.getContenido());
        JButton completarButton = new JButton("Marcar como Completado");

        completarButton.addActionListener(e -> {
            try {
            	recurso.completarActividad();
                progreso.completarActividad(recurso);
          
                outputArea.append("Recurso completado: " + recurso.getTitulo() + "\n");
                recursoFrame.dispose();
                progreso.desempezarActividad();
            } catch (Exception ex) {
                outputArea.append("Error al completar el recurso.\n");
                progreso.desempezarActividad();
            }
        });

        panel.add(label);
        panel.add(completarButton);
        recursoFrame.setVisible(true);
	}

}
