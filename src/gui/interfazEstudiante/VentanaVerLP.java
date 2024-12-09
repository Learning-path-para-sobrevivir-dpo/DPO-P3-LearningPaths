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
import modelo.Estudiante;
import modelo.LearningPath;

public class VentanaVerLP {

	public VentanaVerLP(Estudiante est, GUIManejoDatos datos, VentanaEstudiante ventanaEstudiante) {
		JFrame verLPFrame = new JFrame("Learning Paths Inscritos");
        verLPFrame.setSize(400, 300);
        verLPFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verLPFrame.setLocationRelativeTo(ventanaEstudiante);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        verLPFrame.add(panel);

        JLabel label = new JLabel("Learning Paths Inscritos:");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Obtener los Learning Paths inscritos del estudiante
        Map<String, LearningPath> learningPathsInscritos = est.getLearningPaths();
        if (learningPathsInscritos.isEmpty()) {
            // Si no hay LPs inscritos, mostrar un mensaje
            JOptionPane.showMessageDialog(ventanaEstudiante, "No tienes Learning Paths inscritos", "Información", JOptionPane.INFORMATION_MESSAGE);
            verLPFrame.dispose();
            return;
        }

        // Crear un JList para mostrar los nombres de los Learning Paths
        JList<String> listaLearningPaths = new JList<>(learningPathsInscritos.keySet().toArray(new String[0]));
        listaLearningPaths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección individual
        JScrollPane scrollPane = new JScrollPane(listaLearningPaths); // Añadir scroll para listas largas
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // Acción para cerrar la ventana
        cerrarButton.addActionListener(e -> verLPFrame.dispose());

        // Agregar los componentes al panel
        panel.add(Box.createVerticalStrut(20)); // Espaciado
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(20));
        panel.add(cerrarButton);

        verLPFrame.setVisible(true);
	}

}
