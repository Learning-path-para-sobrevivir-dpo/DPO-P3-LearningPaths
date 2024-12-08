package gui.interfazEstudiante;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import consola.ImprimirConsola;
import gui.GUIManejoDatos;
import gui.VentanaPrincipal;
import modelo.*;
import modelo.actividades.*;


public class VentanaEstudiante extends JFrame {
    
    private Estudiante est;
    private VentanaPrincipal ventanaInicio;
	private GUIManejoDatos datos;
    
    public VentanaEstudiante(Estudiante estudiante, GUIManejoDatos datos, VentanaPrincipal ventanaInicio) {
        this.est = estudiante;
        this.datos = datos;
		this.ventanaInicio = ventanaInicio;
		
        // Configuración básica de la ventana
        setTitle("Menu Estudiante");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con botones
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(null);

        // Crear botones para las opciones
        JButton inscribirButton = new JButton("Inscribir Learning Path");
        inscribirButton.setBounds(100, 30, 200, 30); // Posición y tamaño del botón
        panel.add(inscribirButton);

        JButton verLPButton = new JButton("Ver Learning Paths inscritos");
        verLPButton.setBounds(100, 70, 200, 30);
        panel.add(verLPButton);

        JButton verActividadesButton = new JButton("Ver actividades de Learning Path");
        verActividadesButton.setBounds(100, 110, 200, 30);
        panel.add(verActividadesButton);

        JButton progresoButton = new JButton("Ver progreso de Learning Path");
        progresoButton.setBounds(100, 150, 200, 30);
        panel.add(progresoButton);

        JButton iniciarActividadButton = new JButton("Iniciar/Continuar actividad");
        iniciarActividadButton.setBounds(100, 190, 200, 30);
        panel.add(iniciarActividadButton);

        // Acciones para cada botón
        inscribirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaInscribirLP(); // Cambia a la ventana correspondiente
            }
        });

        verLPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaVerLP(); 
            }
        });

        verActividadesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaVerActividades(); 
            }
        });

        progresoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaVerProgreso(); 
            }
        });

        iniciarActividadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaIniciarActividad();
            }
        });

        // Mostrar la ventana
        setVisible(true);
    }

    private void mostrarVentanaInscribirLP() {
        // Crear una nueva ventana para Inscribir Learning Path
        JFrame inscribirLPFrame = new JFrame("Inscribir Learning Path");
        inscribirLPFrame.setSize(400, 300);
        inscribirLPFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inscribirLPFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        inscribirLPFrame.add(panel);

        JLabel label = new JLabel("Selecciona un Learning Path para inscribir:");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Obtener la lista de Learning Paths disponibles
        Map<String, LearningPath> learningPathsDisponibles = datos.getDatos().getLearningPaths();
        if (learningPathsDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay Learning Paths disponibles para inscribir", "Error", JOptionPane.ERROR_MESSAGE);
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


    private void mostrarVentanaVerLP() {
        // Crear la ventana para mostrar los Learning Paths inscritos
        JFrame verLPFrame = new JFrame("Learning Paths Inscritos");
        verLPFrame.setSize(400, 300);
        verLPFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verLPFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        verLPFrame.add(panel);

        JLabel label = new JLabel("Learning Paths Inscritos:");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Obtener los Learning Paths inscritos del estudiante
        Map<String, LearningPath> learningPathsInscritos = est.getLearningPaths();
        if (learningPathsInscritos.isEmpty()) {
            // Si no hay LPs inscritos, mostrar un mensaje
            JOptionPane.showMessageDialog(this, "No tienes Learning Paths inscritos", "Información", JOptionPane.INFORMATION_MESSAGE);
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


    private void mostrarVentanaVerActividades() {
        // Crear la ventana para mostrar las actividades
        JFrame actividadesFrame = new JFrame("Actividades del Learning Path");
        actividadesFrame.setSize(500, 400);
        actividadesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        actividadesFrame.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        actividadesFrame.add(mainPanel);

        JLabel label = new JLabel("Selecciona un Learning Path para ver sus actividades:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Obtener los Learning Paths inscritos del estudiante
        Map<String, LearningPath> learningPathsInscritos = est.getLearningPaths();
        if (learningPathsInscritos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes Learning Paths inscritos", "Información", JOptionPane.INFORMATION_MESSAGE);
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
                            JLabel actividadLabel = new JLabel("- " + actividad.getTitulo() + ": " + actividad.getObjetivo());
                            actividadLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                            actividadesPanel.add(actividadLabel);
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

    
    private void mostrarVentanaVerProgreso() {
        JFrame progresoFrame = new JFrame("Progreso de Learning Path");
        progresoFrame.setSize(600, 500);
        progresoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        progresoFrame.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout());
        progresoFrame.add(mainPanel);

        JLabel label = new JLabel("Selecciona un Learning Path para ver tu progreso:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Obtener los Learning Paths inscritos del estudiante
        Map<String, LearningPath> learningPathsInscritos = est.getLearningPaths();
        if (learningPathsInscritos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes Learning Paths inscritos", "Información", JOptionPane.INFORMATION_MESSAGE);
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
                        progresoTextArea.setText(obtenerTextoProgreso(progreso));
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

    // Método para transformar el progreso en texto usando ImprimirConsola
    private String obtenerTextoProgreso(Progreso progreso) {
        StringBuilder sb = new StringBuilder();

        // Redirigir la lógica de impresión de `ImprimirConsola` a un StringBuilder
        sb.append(progreso.getEstudiante()).append("\n");
        sb.append("- Progreso en el Learning Path: ").append(progreso.getLearningPath()).append("\n");
        sb.append("    • Progreso Total: ").append(progreso.getProgresoTotal()).append("% de actividades completadas\n");
        sb.append("    • Progreso Actividades Obligatorias: ").append(progreso.getProgresoObligatorio()).append("% de actividades completadas\n");
        sb.append("-----------------------------------------------------\n");

        // Actividades completadas
        sb.append("Actividades Completadas:\n");
        List<Actividad> completadas = progreso.getActCompletadas();
        if (!completadas.isEmpty()) {
            for (Actividad act : completadas) {
                sb.append(obtenerTextoActividad(act, false, true, true)).append("\n");
            }
        } else {
            sb.append("No hay actividades completadas\n");
        }
        sb.append("-----------------------------------------------------\n");

        // Actividades pendientes
        sb.append("Actividades Pendientes:\n");
        List<Actividad> pendientes = progreso.getActPendientes();
        if (!pendientes.isEmpty()) {
            for (Actividad act : pendientes) {
                sb.append(obtenerTextoActividad(act, false, true, true)).append("\n");
            }
        } else {
            sb.append("No tiene actividades pendientes\n");
        }
        sb.append("-----------------------------------------------------\n");

        // Actividad en progreso
        sb.append("Actividad en progreso:\n");
        Actividad enProgreso = progreso.getActividadEnProgreso();
        if (enProgreso != null) {
            sb.append(obtenerTextoActividad(enProgreso, false, true, true)).append("\n");
        } else {
            sb.append("No tiene ninguna actividad en progreso\n");
        }

        return sb.toString();
    }

    // Método para formatear texto de actividades usando lógica de ImprimirConsola
    private String obtenerTextoActividad(Actividad act, boolean imprimirActPrevias, boolean imprimirTipoAct, boolean imprimirInfoAdicional) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n- ").append(act.getTitulo()).append(":\n");
        sb.append("• Tipo: ").append(act.getTipoActividad()).append("\n");
        sb.append("• Objetivo: ").append(act.getObjetivo()).append("\n");
        sb.append("• Nivel de dificultad: ").append(act.getNivelDificultad()).append("\n");
        sb.append("• Tiempo Estimado: ").append(act.getDuracionMin()).append(" minutos\n");
        sb.append("• Es obligatoria: ").append(act.isObligatorio() ? "Sí" : "No").append("\n");

        // Información adicional de actividades
        if (imprimirTipoAct) {
            sb.append("• Información Adicional:\n");
            sb.append(obtenerTextoInfoTipoActividad(act, imprimirInfoAdicional));
        }

        return sb.toString();
    }


    public static String obtenerTextoInfoTipoActividad(Actividad act, boolean imprimirInfoAdicional) {
        StringBuilder sb = new StringBuilder();
        String tipoActividad = act.getTipoActividad();

        switch (tipoActividad) {
            case "Recurso Educativo":
                RecursoEducativo rec = (RecursoEducativo) act;
                sb.append("• Tipo de Recurso: ").append(rec.getTipoRecurso()).append("\n");
                sb.append("• Contenido: ").append(rec.getContenido()).append("\n");
                sb.append("• Enlace: ").append(rec.getEnlace()).append("\n");
                break;

            case "Tarea":
                Tarea t = (Tarea) act;
                sb.append("• Contenido: ").append(t.getContenido()).append("\n");
                if (imprimirInfoAdicional) {
                    sb.append("• Enviada: ").append(t.isEnviado() ? "Sí" : "No").append("\n");
                    sb.append("• Medio de entrega: ").append(t.getMedioEntrega() != null ? t.getMedioEntrega() : "No entregado").append("\n");
                }
                break;

            case "Prueba":
                Prueba p = (Prueba) act;
                sb.append("• Tipo de Prueba: ").append(p.getTipoPrueba()).append("\n");
                if (imprimirInfoAdicional) {
                    sb.append("• Calificación: ").append(p.getCalificacion()).append("\n");
                    sb.append("• Enviada: ").append(p.isRespondida() ? "Sí" : "No").append("\n");
                    if (p instanceof Examen) {
                        Examen e = (Examen) p;
                        sb.append("• Calificado: ").append(e.isCalificado() ? "Sí" : "No").append("\n");
                    }
                }
                if (p instanceof Quiz) {
                    Quiz q = (Quiz) p;
                    sb.append("• Calificación Mínima: ").append(q.getCalificacionMinima()).append("\n");
                }
                break;

            default:
                sb.append("• Tipo de actividad desconocido.").append("\n");
                break;
        }

        if (imprimirInfoAdicional) {
            sb.append("• Completada: ").append(act.isCompletada() ? "Sí" : "No").append("\n");
            sb.append("• Estado: ").append(act.getEstado()).append("\n");
        }

        return sb.toString();
    }


    private void mostrarVentanaIniciarActividad() {
        JFrame actividadFrame = new JFrame("Iniciar o Continuar Actividad");
        actividadFrame.setSize(300, 200);
        actividadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        actividadFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        actividadFrame.add(panel);

        actividadFrame.setVisible(true);
    }
}

