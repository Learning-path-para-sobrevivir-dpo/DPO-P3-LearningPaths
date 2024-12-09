package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ProgresoPorMes extends JFrame {

    public ProgresoPorMes() {
        // Título de la ventana
        setTitle("Actividad Mensual - Actividades hechas");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 12)); 
        panel.setBackground(Color.BLACK); 

        Random random = new Random();

        for (int mes = 0; mes < 12; mes++) {
            for (int dia = 0; dia < 7; dia++) {
                
                int commits = random.nextInt(10);  
                JButton boton = new JButton();
                boton.setBackground(obtenerColorSegunCommits(commits));
                boton.setPreferredSize(new Dimension(50, 50)); 
                boton.setFocusable(false); 
                boton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                panel.add(boton);
            }
        }

        // Añadir el panel a la ventana
        add(panel, BorderLayout.CENTER);

        // Ajustar el tamaño de la ventana y hacerla visible
        setSize(800, 400); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centra la ventana
        setVisible(true);
    }

    // Método para determinar el color en función del número de commits
    private Color obtenerColorSegunCommits(int commits) {
        if (commits == 0) {
            return Color.WHITE; // Sin actividad
        } else if (commits <= 3) {
            return Color.RED; // Poca actividad
        } else if (commits <= 6) {
            return Color.ORANGE; // Actividad moderada
        } else {
            return Color.GREEN; // Alta actividad
        }
    }

    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica
        SwingUtilities.invokeLater(() -> new ProgresoPorMes());
    }
}
