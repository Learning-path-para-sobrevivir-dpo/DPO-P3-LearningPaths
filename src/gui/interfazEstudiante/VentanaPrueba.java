package gui.interfazEstudiante;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modelo.Progreso;
import modelo.actividades.Encuesta;
import modelo.actividades.Examen;
import modelo.actividades.Prueba;
import modelo.actividades.QuizOpcionMultiple;
import modelo.actividades.QuizVerdaderoFalso;

public class VentanaPrueba {

	public VentanaPrueba(Prueba prueba, Progreso progreso, JTextArea outputArea) {
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
	
    private void mostrarVentanaQuizMultiple(QuizOpcionMultiple quiz, Progreso progreso, JTextArea outputArea) {
    	new VentanaQuizMultiple(quiz, progreso, outputArea);
    }
    
    private void mostrarVentanaQuizVF(QuizVerdaderoFalso quiz, Progreso progreso, JTextArea outputArea) {
    	new VentanaQuizVF(quiz, progreso, outputArea);
    }

    private void mostrarVentanaEncuesta(Encuesta encuesta, Progreso progreso, JTextArea outputArea) {
    	new VentanaEncuesta(encuesta, progreso, outputArea);
    }
    
    private void mostrarVentanaExamen(Examen examen, Progreso progreso, JTextArea outputArea) {
    	new VentanaExamen(examen, progreso, outputArea);
    }

}
