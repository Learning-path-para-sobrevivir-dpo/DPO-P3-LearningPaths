package gui.interfazEstudiante;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import gui.GUIManejoDatos;
import modelo.Estudiante;
import modelo.LearningPath;
import modelo.Progreso;

public class VentanaVerProgreso {

	public VentanaVerProgreso(Estudiante est, GUIManejoDatos datos, VentanaEstudiante ventanaEstudiante) {
		JFrame progresoFrame = new JFrame("Progreso de Learning Path");
        progresoFrame.setSize(600, 500);
        progresoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        progresoFrame.setLocationRelativeTo(ventanaEstudiante);

        JPanel mainPanel = new JPanel(new BorderLayout());
        progresoFrame.add(mainPanel);

        JLabel label = new JLabel("Selecciona un Learning Path para ver tu progreso:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Obtener los Learning Paths inscritos del estudiante
        Map<String, LearningPath> learningPathsInscritos = est.getLearningPaths();
        if (learningPathsInscritos.isEmpty()) {
            JOptionPane.showMessageDialog(ventanaEstudiante, "No tienes Learning Paths inscritos", "Información", JOptionPane.INFORMATION_MESSAGE);
            progresoFrame.dispose();
            return;
        }

        // Crear un JList para mostrar los nombres de los Learning Paths
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String lpNombre : learningPathsInscritos.keySet()) {
            listModel.addElement(lpNombre);
        }
        JList<String> listaLearningPaths = new JList<>(listModel);
        listaLearningPaths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPaneLP = new JScrollPane(listaLearningPaths);

        // Área de texto para mostrar el progreso
        JTextArea progresoTextArea = new JTextArea();
        progresoTextArea.setEditable(false);
        progresoTextArea.setLineWrap(true);
        progresoTextArea.setWrapStyleWord(true);
        JScrollPane scrollPaneProgreso = new JScrollPane(progresoTextArea);

        // Acción al seleccionar un Learning Path
        listaLearningPaths.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Evitar múltiples ejecuciones por un solo clic
                String nombreLPSeleccionado = listaLearningPaths.getSelectedValue();
                if (nombreLPSeleccionado != null) {
                    LearningPath lpSeleccionado = learningPathsInscritos.get(nombreLPSeleccionado);

                    // Obtener el progreso del estudiante en el Learning Path seleccionado
                    Map<String, Progreso> progresos = lpSeleccionado.getProgresosEstudiantiles();
                    Progreso progreso = progresos.get(est.getLogin());

                    if (progreso != null) {
                        // Reutilizar ImprimirConsola para formatear el texto
                        progresoTextArea.setText(ventanaEstudiante.obtenerTextoProgreso(progreso));
                    } else {
                        progresoTextArea.setText("No se encontró progreso registrado para este Learning Path.");
                    }
                }
            }
        });

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(e -> progresoFrame.dispose());

        // Agregar componentes al panel principal
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(scrollPaneLP, BorderLayout.WEST);
        mainPanel.add(scrollPaneProgreso, BorderLayout.CENTER);
        mainPanel.add(cerrarButton, BorderLayout.SOUTH);

        progresoFrame.setVisible(true);

	}

}
