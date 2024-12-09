package gui.interfazEstudiante;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;

import javax.swing.JPanel;

import gui.GUIManejoDatos;
import gui.VentanaPrincipal;
import modelo.*;
import modelo.actividades.*;


public class VentanaEstudiante extends JFrame {
    
    private Estudiante est;
	private GUIManejoDatos datos;
    
    public VentanaEstudiante(Estudiante estudiante, GUIManejoDatos datos, VentanaPrincipal ventanaInicio) {
        this.est = estudiante;
        this.datos = datos;
		
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
        new VentanaInscribirLP(est, datos, this);
    }

    private void mostrarVentanaVerLP() {
    	new VentanaVerLP(est, datos, this); 
    }

    private void mostrarVentanaVerActividades() {
    	new VentanaVerActividades(est, datos, this); 
    }

    public void mostrarVentanaVerProgreso() {
    	new VentanaVerProgreso(est, datos, this); 
    }

    // Método para transformar el progreso en texto usando ImprimirConsola
    public  String obtenerTextoProgreso(Progreso progreso) {
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
    public  String obtenerTextoActividad(Actividad act, boolean imprimirActPrevias, boolean imprimirTipoAct, boolean imprimirInfoAdicional) {
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


    public  String obtenerTextoInfoTipoActividad(Actividad act, boolean imprimirInfoAdicional) {
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
    	new VentanaIniciarActividad(est, datos, this); 
    }
}