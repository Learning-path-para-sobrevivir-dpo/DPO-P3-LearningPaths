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
import modelo.actividades.*;

public class VentanaEncuesta {

	public VentanaEncuesta(Encuesta encuesta, Progreso progreso, JTextArea outputArea) {

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
}
