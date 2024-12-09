package gui.interfazEstudiante;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import excepciones.CompletarActividadQueNoEstaEnProgresoException;
import excepciones.RespuestasInconsistentesPruebaException;
import modelo.Progreso;
import modelo.actividades.Examen;
import modelo.actividades.PreguntaAbierta;

public class VentanaExamen {

	public VentanaExamen(Examen examen, Progreso progreso, JTextArea outputArea) {
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
