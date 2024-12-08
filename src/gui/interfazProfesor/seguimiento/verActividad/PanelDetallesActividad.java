package gui.interfazProfesor.seguimiento.verActividad;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.actividades.Actividad;
import modelo.actividades.Prueba;
import modelo.actividades.RecursoEducativo;
import modelo.actividades.Tarea;

@SuppressWarnings("serial")
public class PanelDetallesActividad extends JPanel {
	
	private Actividad actividad;

	public PanelDetallesActividad(Actividad actividad) {
		this.actividad = actividad;
		setLayout(new GridLayout(9, 2, 10, 10));
		setBorder(new EmptyBorder(30, 40, 30, 40));
		setAutoscrolls(true);
		
		JLabel titulo = new JLabel("Titulo: " + actividad.getTitulo());
		titulo.setFont(new Font("Calibri", Font.BOLD, 20));
		
		JLabel estudiante = new JLabel("Estudiante: " + actividad.getEstudiante());
		estudiante.setFont(new Font("Calibri", Font.BOLD, 20));
		
		JLabel labTipo = new JLabel("Tipo de Actividad: ");
		JLabel tipo = new JLabel(actividad.getTipoActividad());
		
		JLabel labObjetivo = new JLabel("Objetivo");
		JLabel objetivo = new JLabel(actividad.getObjetivo());
		
		JLabel labNivel = new JLabel("Nivel de dificultad");
		JLabel nivelDificultad = new JLabel(actividad.getNivelDificultadString());
		
		JLabel labObligatorio = new JLabel("Obligatorio");
		JLabel obligatorio = new JLabel();
		if (actividad.isObligatorio())
			obligatorio.setText("Sí");
		else
			obligatorio.setText("No");
		
		JLabel labEstado = new JLabel("Estado:");
		JLabel estado = new JLabel(actividad.getEstado());
		
		add(titulo);
		add(estudiante);
		add(labTipo);
		add(tipo);
		add(labObjetivo);
		add(objetivo);
		add(labNivel);
		add(nivelDificultad);
		add(labObligatorio);
		add(obligatorio);
		add(labEstado);
		add(estado);
		addDetallesUnicos();
	}
	
	private void addDetallesUnicos()
	{
		if (this.actividad instanceof RecursoEducativo)
		{
			RecursoEducativo r = (RecursoEducativo) actividad;
			
			JLabel labContenido = new JLabel("Contenido: ");
			JLabel contenido = new JLabel(r.getContenido());
			
			add(labContenido);
			add(contenido);
			
			if (!r.getEnlace().equals(""))
			{
				JLabel labEnlace = new JLabel("Enlace: ");
				JLabel enlace = new JLabel(r.getEnlace());
				add(labEnlace);
				add(enlace);
			}
			
		}
		else if (this.actividad instanceof Tarea)
		{
			Tarea t = (Tarea) actividad;
			
			JLabel labContenido = new JLabel("Contenido: ");
			JLabel contenido = new JLabel(t.getContenido());
			
			add(labContenido);
			add(contenido);
			
			JLabel labEnviado = new JLabel("Enviado: ");
			JLabel enviado = new JLabel();
			if (t.isEnviado())
			{
				enviado.setText("Si");
				add(labEnviado);
				add(enviado);
				
				JLabel labMedio = new JLabel("Medio de entrega:");
				JLabel medio = new JLabel();
				if (t.getMedioEntrega().strip().equals(""))
					medio.setText("Ninguno");
				else
					medio.setText(t.getMedioEntrega());
				add(labMedio);
				add(medio);
			}
			else
			{
				enviado.setText("No");
				add(labEnviado);
				add(enviado);
			}
			
		}
		else if (this.actividad instanceof Prueba)
		{
			
		}
	}
	
}
