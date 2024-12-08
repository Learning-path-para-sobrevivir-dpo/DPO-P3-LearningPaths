package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelo.Usuario;
import modelo.actividades.Actividad;
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

	public List<Actividad> getActividadesSistema() {
		List<Actividad> acts = new ArrayList<Actividad>();
		Map<String, Actividad>actsSistema = datos.getActividades();
		for (Actividad act: actsSistema.values()) {
			acts.add(act);
		}
		return acts;
	}
	
}
