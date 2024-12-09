package gui.interfazEstudiante;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import excepciones.CompletarActividadQueNoEstaEnProgresoException;
import excepciones.RespuestasInconsistentesPruebaException;
import modelo.Progreso;
import modelo.actividades.PreguntaVerdaderoFalso;
import modelo.actividades.QuizVerdaderoFalso;

public class VentanaQuizVF {

	public VentanaQuizVF(QuizVerdaderoFalso quiz, Progreso progreso, JTextArea outputArea) {
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

}
