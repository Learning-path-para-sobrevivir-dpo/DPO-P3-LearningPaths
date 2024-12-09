package gui.interfazEstudiante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import excepciones.CompletarActividadQueNoEstaEnProgresoException;
import excepciones.RespuestasInconsistentesPruebaException;
import excepciones.YaExisteActividadEnProgresoException;
import gui.GUIManejoDatos;
import modelo.Estudiante;
import modelo.LearningPath;
import modelo.Progreso;
import modelo.actividades.Actividad;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.PreguntaAbierta;
import modelo.actividades.PreguntaMultiple;
import modelo.actividades.PreguntaVerdaderoFalso;
import modelo.actividades.Prueba;
import modelo.actividades.QuizOpcionMultiple;
import modelo.actividades.QuizVerdaderoFalso;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;

public class VentanaIniciarActividad {

	public VentanaIniciarActividad(Estudiante est, GUIManejoDatos datos, VentanaEstudiante ventanaEstudiante) {
		
        JFrame actividadFrame = new JFrame("Iniciar o Continuar Actividad");
        actividadFrame.setSize(400, 400);
        actividadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        actividadFrame.setLocationRelativeTo(ventanaEstudiante);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        actividadFrame.add(panel);

        // Selección de Learning Path
        JLabel lpLabel = new JLabel("Seleccione un Learning Path:");
        DefaultListModel<String> lpListModel = new DefaultListModel<>();
        est.getLearningPaths().forEach((titulo, lp) -> lpListModel.addElement(titulo));

        JList<String> lpList = new JList<>(lpListModel);
        lpList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane lpScrollPane = new JScrollPane(lpList);

        // Botón para cargar actividades del Learning Path seleccionado
        JButton cargarActividadesButton = new JButton("Cargar Actividades");

        // Área para mostrar actividades
        JLabel actividadLabel = new JLabel("Seleccione una Actividad:");
        JList<String> actividadList = new JList<>();
        actividadList.setEnabled(false); // Deshabilitado hasta que se carguen actividades
        JScrollPane actividadScrollPane = new JScrollPane(actividadList);

        // Botón para iniciar la actividad
        JButton iniciarButton = new JButton("Iniciar Actividad");
        iniciarButton.setEnabled(false);

        // Texto para mostrar mensajes
        JTextArea outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Agregar los componentes al panel
        panel.add(lpLabel);
        panel.add(lpScrollPane);
        panel.add(cargarActividadesButton);
        panel.add(actividadLabel);
        panel.add(actividadScrollPane);
        panel.add(iniciarButton);
        panel.add(new JScrollPane(outputArea));

        // Lógica para cargar actividades del LP seleccionado
        cargarActividadesButton.addActionListener(e -> {
            String lpSeleccionado = lpList.getSelectedValue();
            if (lpSeleccionado != null) {
                LearningPath lp = est.getLearningPaths().get(lpSeleccionado);
                DefaultListModel<String> actividadListModel = new DefaultListModel<>();
                lp.getActividades().forEach((id, actividad) -> actividadListModel.addElement(actividad.getTitulo()));
                actividadList.setModel(actividadListModel);
                actividadList.setEnabled(true);
                iniciarButton.setEnabled(true);
            }
        });

        // Lógica para iniciar actividad
        iniciarButton.addActionListener(e -> {
            String actividadSeleccionada = actividadList.getSelectedValue();
            if (actividadSeleccionada != null) {
                String lpSeleccionado = lpList.getSelectedValue();
                LearningPath lp = est.getLearningPaths().get(lpSeleccionado);
                Progreso progreso = est.getProgresosLearningPaths().get(lpSeleccionado);
                Actividad actividad = lp.getActividades().values().stream()
                        .filter(a -> a.getTitulo().equals(actividadSeleccionada))
                        .findFirst()
                        .orElse(null);

                if (actividad == null) {
                    outputArea.append("No se seleccionó una actividad válida.\n");
                    return;
                }

                // Intentar iniciar la actividad
                try {
                    progreso.empezarActividad(actividad);
                    outputArea.append("Actividad iniciada: " + actividad.getTitulo() + "\n");
                } catch (YaExisteActividadEnProgresoException ex) {
                    outputArea.append("Ya hay una actividad en progreso.\n");
                    return;
                }

                // Llamar a una función específica para cada tipo de actividad
                switch (actividad.getTipoActividad()) {
                    case "Prueba":
                        mostrarVentanaPrueba((Prueba) actividad, progreso, outputArea);
                        break;
                    case "Tarea":
                        mostrarVentanaTarea((Tarea) actividad, progreso, outputArea);
                        break;
                    case "Recurso Educativo":
                        mostrarVentanaRecurso((RecursoEducativo) actividad, progreso, outputArea);
                        break;
                    default:
                        outputArea.append("Tipo de actividad desconocido.\n");
                        break;
                }
            }
        });

        // Mostrar la ventana
        actividadFrame.setVisible(true);
    }

    private void mostrarVentanaTarea(Tarea tarea, Progreso progreso, JTextArea outputArea) {
    	new VentanaTarea(tarea, progreso, outputArea);
    }


    private void mostrarVentanaRecurso(RecursoEducativo recurso, Progreso progreso, JTextArea outputArea) {
    	new VentanaRecurso(recurso, progreso, outputArea);
    }

    private void mostrarVentanaPrueba(Prueba prueba, Progreso progreso, JTextArea outputArea) {
    	new VentanaPrueba(prueba, progreso, outputArea);
    }


}


