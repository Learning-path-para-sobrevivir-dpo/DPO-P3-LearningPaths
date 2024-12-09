package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excepciones.LearningPathIncorrectoProgresoException;
import modelo.LearningPath;
import modelo.Progreso;
import modelo.Usuario;
import modelo.actividades.Actividad;
import modelo.actividades.Tarea;
import persistencia.ManejoDatos;

public class GUIManejoDatos {
	ManejoDatos datos;

	public GUIManejoDatos() {
		datos = new ManejoDatos();
		datos.cargarDatos();
	}
	
	public void añadirUsuario(Usuario u)
	{
		if (u != null)
		{
			datos.addUsuario(u);
		}
	}
	
	/**
	 * Verifica si el login de un usuario ya esta en uso
	 * @return true si el login existe, false de lo contrario
	 */
	public boolean existeUsuario(String loginUsuario)
	{
		HashMap<List<String>, Usuario> usuarios = datos.getUsuarios();
		Set<List<String>> loginsContraseñas = usuarios.keySet();
		List<String> logins = new ArrayList<String>();
		
		for (List<String> lc: loginsContraseñas)
		{
			logins.add(lc.getFirst());
		}
		
		boolean existe = logins.contains(loginUsuario);
				
		return existe;
	}
	
	public Usuario getUsuario(String login, String contrasenia)
	{
		Usuario u = datos.getUsuario(login, contrasenia);
		return u;
	}
	
	public ManejoDatos getDatos()
	{
		return this.datos;
  
	}	
	public List<Actividad> getActividadesSistema() {
		List<Actividad> acts = new ArrayList<Actividad>();
		Map<String, Actividad>actsSistema = datos.getActividades();
		for (Actividad act: actsSistema.values()) {
			acts.add(act);
		}
		return acts;
	}
	
	public void addLearningPath(LearningPath path) {
		datos.addLearningPath(path);
	}
	
	public void actualizarUsuario(Usuario usuario) {
		datos.actualizarUsuario(usuario);
	}
	
	public void actualizarLearningPath(LearningPath path) {
		datos.actualizarLearningPath(path);
	}

	public void actualizarProgreso(Progreso progreso) {
		// TODO Auto-generated method stub
		try {
			datos.actualizarProgreso(progreso);
		} catch (LearningPathIncorrectoProgresoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarActividad(Actividad actividad)
	{
		datos.actualizarActividad(actividad);
	}

	public void addActividad(Actividad act) {
		// TODO Auto-generated method stub
		datos.addActividad(act);
	}
}

