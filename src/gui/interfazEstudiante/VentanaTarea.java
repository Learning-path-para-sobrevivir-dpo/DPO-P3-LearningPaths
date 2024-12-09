package gui.interfazEstudiante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Progreso;
import modelo.actividades.Tarea;

public class VentanaTarea {

	public VentanaTarea(Tarea tarea, Progreso progreso, JTextArea outputArea) {
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

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });


        panel.add(enviarButton);
        tareaFrame.setVisible(true);
	}

}
