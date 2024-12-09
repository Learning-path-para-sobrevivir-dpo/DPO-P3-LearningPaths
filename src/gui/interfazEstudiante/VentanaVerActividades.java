package gui.interfazEstudiante;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.BoxLayout;
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
import modelo.actividades.Actividad;

public class VentanaVerActividades {

	public VentanaVerActividades(Estudiante est, GUIManejoDatos datos, VentanaEstudiante ventanaEstudiante) {
		JFrame actividadesFrame = new JFrame("Actividades del Learning Path");
        actividadesFrame.setSize(500, 400);
        actividadesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        actividadesFrame.setLocationRelativeTo(ventanaEstudiante);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        actividadesFrame.add(mainPanel);

        JLabel label = new JLabel("Selecciona un Learning Path para ver sus actividades:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Obtener los Learning Paths inscritos del estudiante
        Map<String, LearningPath> learningPathsInscritos = est.getLearningPaths();
        if (learningPathsInscritos.isEmpty()) {
            JOptionPane.showMessageDialog(ventanaEstudiante, "No tienes Learning Paths inscritos", "Información", JOptionPane.INFORMATION_MESSAGE);
            actividadesFrame.dispose();
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

        // Panel para mostrar las actividades seleccionadas
        JPanel actividadesPanel = new JPanel();
        actividadesPanel.setLayout(new BoxLayout(actividadesPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneActividades = new JScrollPane(actividadesPanel);

        // Acción al seleccionar un Learning Path de la lista
        listaLearningPaths.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Evitar ejecutar múltiples veces por un solo clic
                String nombreLPSeleccionado = listaLearningPaths.getSelectedValue();
                if (nombreLPSeleccionado != null) {
                    LearningPath lpSeleccionado = learningPathsInscritos.get(nombreLPSeleccionado);
                    actividadesPanel.removeAll(); // Limpiar actividades previas

                    Map<Integer, Actividad> actividades = lpSeleccionado.getActividades();
                    if (!actividades.isEmpty()) {
                        for (Actividad actividad : actividades.values()) {
                            // Obtener la información formateada de la actividad
                            String actividadInfo = ventanaEstudiante.obtenerTextoActividad(actividad, false, true, true);

                            // Crear un JTextArea para mostrar la información de la actividad
                            JTextArea actividadTextArea = new JTextArea("- " + actividadInfo);
                            actividadTextArea.setLineWrap(true);
                            actividadTextArea.setWrapStyleWord(true);
                            actividadTextArea.setEditable(false);
                            actividadTextArea.setBackground(actividadesPanel.getBackground()); // Para que coincida con el panel

                            // Añadir el JTextArea al panel
                            actividadesPanel.add(actividadTextArea);
                        }
                    } else {
                        JLabel noActividadesLabel = new JLabel("No hay actividades en este Learning Path.");
                        noActividadesLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                        actividadesPanel.add(noActividadesLabel);
                    }

                    actividadesPanel.revalidate();
                    actividadesPanel.repaint();
                }
            }
        });

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(e -> actividadesFrame.dispose());

        // Agregar los componentes al panel principal
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(scrollPaneLP, BorderLayout.WEST);
        mainPanel.add(scrollPaneActividades, BorderLayout.CENTER);
        mainPanel.add(cerrarButton, BorderLayout.SOUTH);

        actividadesFrame.setVisible(true);
	}

}
