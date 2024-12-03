package gui;

import modelo.Usuario;
import persistencia.ManejoDatos;

public class GUIManejoDatos {
	ManejoDatos datos;

	public GUIManejoDatos() {
		datos = new ManejoDatos();
	}
	
	public void añadirUsuario(Usuario u)
	{
		if (u != null)
		{
			datos.addUsuario(u);
		}
	}
	
	public Usuario autenticarUsuario()
	{
		Usuario u = null;
		return u;
	}
}
