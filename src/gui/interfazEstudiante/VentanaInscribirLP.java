package gui.interfazEstudiante;

import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import gui.GUIManejoDatos;
import modelo.*;
import modelo.actividades.*;

public class VentanaInscribirLP {
	private Estudiante est;
    private GUIManejoDatos datos;

	public VentanaInscribirLP(Estudiante estudiante, GUIManejoDatos datos, VentanaEstudiante ventanaE) {
        this.est = estudiante;
        this.datos = datos;
        
        JFrame inscribirLPFrame = new JFrame("Inscribir Learning Path");
        inscribirLPFrame.setSize(400, 300);
        inscribirLPFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inscribirLPFrame.setLocationRelativeTo(ventanaE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        inscribirLPFrame.add(panel);

        JLabel label = new JLabel("Selecciona un Learning Path para inscribir:");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Obtener la lista de Learning Paths disponibles
        Map<String, LearningPath> learningPathsDisponibles = datos.getDatos().getLearningPaths();
        if (learningPathsDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(ventanaE, "No hay Learning Paths disponibles para inscribir", "Error", JOptionPane.ERROR_MESSAGE);
            inscribirLPFrame.dispose();
            return;
        }

        // Crear un JList para mostrar los Learning Paths
        JList<String> listaLearningPaths = new JList<>(learningPathsDisponibles.keySet().toArray(new String[0]));
        listaLearningPaths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección individual
        JScrollPane scrollPane = new JScrollPane(listaLearningPaths); // Scroll para listas largas
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);

        JButton inscribirButton = new JButton("Inscribir");
        inscribirButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // Añadir el ActionListener al botón de inscribir
        inscribirButton.addActionListener(e -> {
            String nombreLPSeleccionado = listaLearningPaths.getSelectedValue(); // Obtener el elemento seleccionado
            if (nombreLPSeleccionado != null) {
                LearningPath lpSeleccionado = learningPathsDisponibles.get(nombreLPSeleccionado);
                try {
                    est.inscribirLearningPath(lpSeleccionado); // Inscribir el Learning Path
                    JOptionPane.showMessageDialog(inscribirLPFrame, 
                        "El Learning Path '" + nombreLPSeleccionado + "' fue inscrito exitosamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    inscribirLPFrame.dispose(); // Cerrar la ventana tras el éxito
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(inscribirLPFrame, 
                        "Error: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(inscribirLPFrame, "Selecciona un Learning Path primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Agregar los componentes al panel
        panel.add(Box.createVerticalStrut(20)); // Espaciado
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20));
        panel.add(inscribirButton);

        inscribirLPFrame.setVisible(true);
    }
}
