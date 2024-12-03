package gui;

import javax.swing.JFrame;

import persistencia.ManejoDatos;

public class Principal{
	
	private VentanaInicio ventanaInicio;
	
	public static void main(String[] args) {
		ManejoDatos datos = new ManejoDatos();
		Principal ventana = new Principal(datos);
	}
	
	public Principal(ManejoDatos datos) {
		ventanaInicio = new VentanaInicio(datos);
		ventanaInicio.setVisible(true);
        
	}
	
	
}
