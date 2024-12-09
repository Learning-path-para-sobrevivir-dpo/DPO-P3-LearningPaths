package gui.interfazEstudiante;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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
import modelo.actividades.PreguntaMultiple;
import modelo.actividades.QuizOpcionMultiple;

public class VentanaQuizMultiple {

	public VentanaQuizMultiple(QuizOpcionMultiple quiz, Progreso progreso, JTextArea outputArea) {
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

}
