package gui.interfazEstudiante;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import consola.ImprimirConsola;
import excepciones.CompletarActividadQueNoEstaEnProgresoException;
import excepciones.RespuestasInconsistentesPruebaException;
import excepciones.YaExisteActividadEnProgresoException;
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
                            // Obtener la información formateada de la actividad
                            String actividadInfo = obtenerTextoActividad(actividad, false, true, true);

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
        // Crear la ventana principal
        JFrame actividadFrame = new JFrame("Iniciar o Continuar Actividad");
        actividadFrame.setSize(400, 400);
        actividadFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        actividadFrame.setLocationRelativeTo(this);

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
        JFrame tareaFrame = new JFrame("Tarea: " + tarea.getTitulo());
        tareaFrame.setSize(400, 300);
        tareaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tareaFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        tareaFrame.add(panel);

        // Etiqueta con el contenido de la tarea
        JLabel label = new JLabel("Contenido de la Tarea: " + tarea.getContenido());
        panel.add(label);

        // Cuadro de texto para que el usuario ingrese el medio de entrega
        JLabel medioEntregaLabel = new JLabel("Especifique el medio de entrega:");
        JTextField medioEntregaField = new JTextField(20);  // Cuadro de texto para el medio de entrega
        panel.add(medioEntregaLabel);
        panel.add(medioEntregaField);

        // Botón para enviar la tarea
        JButton enviarButton = new JButton("Enviar Tarea");

        enviarButton.addActionListener(e -> {
            // Obtener el valor ingresado en el cuadro de texto
            String medioEntrega = medioEntregaField.getText();

            if (medioEntrega.isEmpty()) {
                outputArea.append("El medio de entrega no puede estar vacío.\n");
                return;
            }

            try {
                // Establecer el medio de entrega y marcar la tarea como enviada
                tarea.setMedioEntrega(medioEntrega);
                tarea.setEnviado(true);

                // Actualizar el progreso de la actividad
                progreso.completarActividad(tarea);
                progreso.desempezarActividad();

                // Mostrar mensaje en el área de salida
                outputArea.append("Tarea enviada: " + tarea.getTitulo() + "\n");

                // Cerrar la ventana de la tarea
                tareaFrame.dispose();
            } catch (Exception ex) {
                outputArea.append("Error al enviar la tarea.\n");
            }
        });

        panel.add(enviarButton);
        tareaFrame.setVisible(true);
    }


    private void mostrarVentanaRecurso(RecursoEducativo recurso, Progreso progreso, JTextArea outputArea) {
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

    private void mostrarVentanaQuizMultiple(QuizOpcionMultiple quiz, Progreso progreso, JTextArea outputArea) {
        JFrame quizFrame = new JFrame("Quiz Opción Múltiple: " + quiz.getTitulo());
        quizFrame.setSize(500, 500);
        quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        quizFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        quizFrame.add(new JScrollPane(mainPanel));

        JLabel tituloLabel = new JLabel("Quiz: " + quiz.getTitulo());
        tituloLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel objetivoLabel = new JLabel("Objetivo: " + quiz.getObjetivo());
        objetivoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        mainPanel.add(tituloLabel);
        mainPanel.add(objetivoLabel);

        // Crear un grupo de botones de radio para cada pregunta
        List<ButtonGroup> grupos = new ArrayList<>();
        for (PreguntaMultiple pregunta : quiz.getPreguntas()) {
            JPanel preguntaPanel = new JPanel();
            preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));
            preguntaPanel.setBorder(BorderFactory.createTitledBorder("Pregunta " + pregunta.getNumero()));

            JLabel preguntaLabel = new JLabel(pregunta.getEnunciado());
            preguntaPanel.add(preguntaLabel);

            ButtonGroup group = new ButtonGroup();
            for (int i = 0; i < pregunta.getOpciones().size(); i++) {
                JRadioButton opcionButton = new JRadioButton(pregunta.getOpciones().get(i));
                group.add(opcionButton);
                preguntaPanel.add(opcionButton);
            }
            grupos.add(group);
            mainPanel.add(preguntaPanel);
        }

        // Botón para enviar respuestas
        JButton enviarButton = new JButton("Enviar Respuestas");
        enviarButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        enviarButton.addActionListener(e -> {
            List<Integer> respuestas = new ArrayList<>();
            boolean todasRespondidas = true;

            // Recoger las respuestas seleccionadas
            for (int i = 0; i < grupos.size(); i++) {
                ButtonGroup group = grupos.get(i);
                int respuestaSeleccionada = -1;

                Enumeration<AbstractButton> buttons = group.getElements();
                int index = 0;
                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        respuestaSeleccionada = index;
                        break;
                    }
                    index++;
                }

                if (respuestaSeleccionada == -1) {
                    todasRespondidas = false;
                    break;
                }
                respuestas.add(respuestaSeleccionada);
            }

            // Validar si todas las preguntas fueron respondidas
            if (!todasRespondidas) {
                JOptionPane.showMessageDialog(quizFrame, "Por favor responde todas las preguntas antes de enviar.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Intentar calificar el quiz
            try {
                quiz.responderQuiz(respuestas);
                outputArea.append("Quiz completado.\n");
                outputArea.append("Calificación: " + quiz.getCalificacion() + "\n");
                outputArea.append("Estado: " + quiz.getEstado() + "\n");
                progreso.completarActividad(quiz);
                progreso.desempezarActividad();
                quizFrame.dispose();
            } catch (RespuestasInconsistentesPruebaException ex) {
                outputArea.append("Número de respuestas incorrecto. Inténtalo de nuevo.\n");
            } catch (CompletarActividadQueNoEstaEnProgresoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        mainPanel.add(enviarButton);

        quizFrame.setVisible(true);
    }
    
    private void mostrarVentanaQuizVF(QuizVerdaderoFalso quiz, Progreso progreso, JTextArea outputArea) {
        JFrame quizFrame = new JFrame("Quiz Verdadero/Falso: " + quiz.getTitulo());
        quizFrame.setSize(500, 500);
        quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        quizFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        quizFrame.add(new JScrollPane(mainPanel));

        JLabel tituloLabel = new JLabel("Quiz: " + quiz.getTitulo());
        tituloLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel objetivoLabel = new JLabel("Objetivo: " + quiz.getObjetivo());
        objetivoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        mainPanel.add(tituloLabel);
        mainPanel.add(objetivoLabel);

        // Crear los botones de radio para cada pregunta
        List<ButtonGroup> grupos = new ArrayList<>();
        for (PreguntaVerdaderoFalso pregunta : quiz.getPreguntas()) {
            JPanel preguntaPanel = new JPanel();
            preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));
            preguntaPanel.setBorder(BorderFactory.createTitledBorder("Pregunta " + pregunta.getNumero()));

            JLabel preguntaLabel = new JLabel(pregunta.getEnunciado());
            preguntaPanel.add(preguntaLabel);

            ButtonGroup group = new ButtonGroup();
            JRadioButton verdaderoButton = new JRadioButton("V (Verdadero)");
            JRadioButton falsoButton = new JRadioButton("F (Falso)");

            group.add(verdaderoButton);
            group.add(falsoButton);

            preguntaPanel.add(verdaderoButton);
            preguntaPanel.add(falsoButton);

            grupos.add(group);
            mainPanel.add(preguntaPanel);
        }

        // Botón para enviar respuestas
        JButton enviarButton = new JButton("Enviar Respuestas");
        enviarButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        enviarButton.addActionListener(e -> {
            List<Boolean> respuestas = new ArrayList<>();
            boolean todasRespondidas = true;

            // Recoger las respuestas seleccionadas
            for (ButtonGroup group : grupos) {
                ButtonModel seleccion = group.getSelection();
                if (seleccion == null) {
                    todasRespondidas = false;
                    break;
                }
                // Determinar si es Verdadero o Falso
                respuestas.add(seleccion.getActionCommand().equals("V"));
            }

            // Validar si todas las preguntas fueron respondidas
            if (!todasRespondidas) {
                JOptionPane.showMessageDialog(quizFrame, "Por favor responde todas las preguntas antes de enviar.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Intentar calificar el quiz
            try {
                quiz.responderQuiz(respuestas);
                outputArea.append("Quiz completado.\n");
                outputArea.append("Calificación: " + quiz.getCalificacion() + "\n");
                outputArea.append("Estado: " + quiz.getEstado() + "\n");
                progreso.completarActividad(quiz);
                progreso.desempezarActividad();
                quizFrame.dispose();
            } catch (RespuestasInconsistentesPruebaException ex) {
                outputArea.append("Número de respuestas incorrecto. Inténtalo de nuevo.\n");
            } catch (CompletarActividadQueNoEstaEnProgresoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });

        mainPanel.add(enviarButton);

        quizFrame.setVisible(true);
    }

    private void mostrarVentanaEncuesta(Encuesta encuesta, Progreso progreso, JTextArea outputArea) {
        JFrame encuestaFrame = new JFrame("Encuesta: " + encuesta.getTitulo());
        encuestaFrame.setSize(500, 500);
        encuestaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        encuestaFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        encuestaFrame.add(new JScrollPane(mainPanel)); // Scroll en caso de preguntas extensas

        JLabel tituloLabel = new JLabel("Encuesta: " + encuesta.getTitulo());
        tituloLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel objetivoLabel = new JLabel("Descripción: " + encuesta.getObjetivo());
        objetivoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        mainPanel.add(tituloLabel);
        mainPanel.add(objetivoLabel);

        // Crear campos de texto para cada pregunta
        List<JTextField> camposRespuestas = new ArrayList<>();
        for (PreguntaAbierta pregunta : encuesta.getPreguntas()) {
            JPanel preguntaPanel = new JPanel();
            preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));
            preguntaPanel.setBorder(BorderFactory.createTitledBorder("Pregunta " + pregunta.getNumero()));

            JLabel preguntaLabel = new JLabel(pregunta.getEnunciado());
            JTextField respuestaField = new JTextField(20);
            camposRespuestas.add(respuestaField);

            preguntaPanel.add(preguntaLabel);
            preguntaPanel.add(respuestaField);
            mainPanel.add(preguntaPanel);
        }

        // Botón para enviar respuestas
        JButton enviarButton = new JButton("Enviar Respuestas");
        enviarButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        enviarButton.addActionListener(e -> {
            List<String> respuestas = new ArrayList<>();
            boolean todasRespondidas = true;

            // Recoger y validar las respuestas
            for (JTextField campo : camposRespuestas) {
                String respuesta = campo.getText().trim();
                if (respuesta.isEmpty()) {
                    todasRespondidas = false;
                    break;
                }
                respuestas.add(respuesta);
            }

            // Mostrar error si hay preguntas sin responder
            if (!todasRespondidas) {
                JOptionPane.showMessageDialog(encuestaFrame, "Por favor responde todas las preguntas antes de enviar.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Intentar procesar la encuesta
            try {
                encuesta.responderEncuesta(respuestas);
                outputArea.append("Encuesta completada.\n");
                outputArea.append("Estado: " + encuesta.getEstado() + "\n");
                progreso.completarActividad(encuesta);
                progreso.desempezarActividad();
                encuestaFrame.dispose();
            } catch (RespuestasInconsistentesPruebaException ex) {
                JOptionPane.showMessageDialog(encuestaFrame, "Número de respuestas incorrecto. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                outputArea.append("Error: Respuestas inconsistentes.\n");
            } catch (CompletarActividadQueNoEstaEnProgresoException ex) {
                ex.printStackTrace();
            }
        });

        mainPanel.add(enviarButton);
        encuestaFrame.setVisible(true);
    }

    private void mostrarVentanaPrueba(Prueba prueba, Progreso progreso, JTextArea outputArea) {
        JFrame pruebaFrame = new JFrame("Prueba: " + prueba.getTitulo());
        pruebaFrame.setSize(400, 300);
        pruebaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pruebaFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        pruebaFrame.add(panel);

        JLabel label = new JLabel("Tipo de Prueba: " + prueba.getTipoPrueba());
        JButton completarButton = new JButton("Completar Prueba");

        // Lógica para determinar el tipo de prueba y mostrar la ventana correspondiente
        if (prueba instanceof QuizOpcionMultiple) {
            completarButton.addActionListener(e -> {
                // Llamar a la ventana para el quiz de opción múltiple
                mostrarVentanaQuizMultiple((QuizOpcionMultiple) prueba, progreso, outputArea);
                pruebaFrame.dispose();
            });
        } else if (prueba instanceof QuizVerdaderoFalso) {
            completarButton.addActionListener(e -> {
                // Llamar a la ventana para el quiz verdadero/falso
                mostrarVentanaQuizVF((QuizVerdaderoFalso) prueba, progreso, outputArea);
                pruebaFrame.dispose();
            });
        } else if (prueba instanceof Encuesta) {
            completarButton.addActionListener(e -> {
                // Llamar a la ventana para la encuesta
                mostrarVentanaEncuesta((Encuesta) prueba, progreso, outputArea);
                pruebaFrame.dispose();
            });
        } else if (prueba instanceof Examen) {
            completarButton.addActionListener(e -> {
                // Llamar a la ventana para la encuesta
                mostrarVentanaExamen((Examen) prueba, progreso, outputArea);
                pruebaFrame.dispose();
            });
        } else {
            completarButton.addActionListener(e -> {
                try {
                    progreso.completarActividad(prueba);
                    progreso.desempezarActividad();
                    outputArea.append("Prueba completada: " + prueba.getTitulo() + "\n");
                    pruebaFrame.dispose();
                } catch (Exception ex) {
                    outputArea.append("Error al completar la prueba.\n");
                }
            });
        }

        panel.add(label);
        panel.add(completarButton);
        pruebaFrame.setVisible(true);
    }

    private void mostrarVentanaExamen(Examen examen, Progreso progreso, JTextArea outputArea) {
        JFrame examenFrame = new JFrame("Examen: " + examen.getTitulo());
        examenFrame.setSize(500, 500);
        examenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        examenFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        examenFrame.add(new JScrollPane(mainPanel)); // Scroll en caso de muchas preguntas

        JLabel tituloLabel = new JLabel("Examen: " + examen.getTitulo());
        tituloLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel objetivoLabel = new JLabel("Descripción: " + examen.getObjetivo());
        objetivoLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        mainPanel.add(tituloLabel);
        mainPanel.add(objetivoLabel);

        // Crear campos de texto para cada pregunta
        List<JTextField> camposRespuestas = new ArrayList<>();
        for (PreguntaAbierta pregunta : examen.getPreguntas()) {
            JPanel preguntaPanel = new JPanel();
            preguntaPanel.setLayout(new BoxLayout(preguntaPanel, BoxLayout.Y_AXIS));
            preguntaPanel.setBorder(BorderFactory.createTitledBorder("Pregunta " + pregunta.getNumero()));

            JLabel preguntaLabel = new JLabel(pregunta.getEnunciado());
            JTextField respuestaField = new JTextField(20);
            camposRespuestas.add(respuestaField);

            preguntaPanel.add(preguntaLabel);
            preguntaPanel.add(respuestaField);
            mainPanel.add(preguntaPanel);
        }

        // Botón para enviar respuestas
        JButton enviarButton = new JButton("Enviar Respuestas");
        enviarButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        enviarButton.addActionListener(e -> {
            List<String> respuestas = new ArrayList<>();
            boolean todasRespondidas = true;

            // Recoger y validar las respuestas
            for (JTextField campo : camposRespuestas) {
                String respuesta = campo.getText().trim();
                if (respuesta.isEmpty()) {
                    todasRespondidas = false;
                    break;
                }
                respuestas.add(respuesta);
            }

            // Mostrar error si hay preguntas sin responder
            if (!todasRespondidas) {
                JOptionPane.showMessageDialog(examenFrame, "Por favor responde todas las preguntas antes de enviar.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Intentar procesar el examen
            try {
                examen.responderExamen(respuestas);
                outputArea.append("Examen respondido exitosamente.\n");
                outputArea.append("Calificación: " + examen.getCalificacion() + "\n");
                outputArea.append("Estado: " + examen.getEstado() + "\n");
                progreso.completarActividad(examen);
                progreso.desempezarActividad();
                examenFrame.dispose();
            } catch (RespuestasInconsistentesPruebaException ex) {
                JOptionPane.showMessageDialog(examenFrame, "Número de respuestas incorrecto. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                outputArea.append("Error: Respuestas inconsistentes.\n");
            } catch (CompletarActividadQueNoEstaEnProgresoException ex) {
                ex.printStackTrace();
            }
        });

        mainPanel.add(enviarButton);
        examenFrame.setVisible(true);
    }


}

