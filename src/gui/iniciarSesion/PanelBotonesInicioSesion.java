package gui.iniciarSesion;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotonesInicioSesion extends JPanel implements ActionListener {
	
	private static final String CANCELAR = "cancelar";
	private static final String INICIAR = "iniciar sesion";
	
	private JButton butIniciarSesion;
	private JButton butCancelar;
	
	private VentanaInicioSesion ventanaIniciar;
	
	public PanelBotonesInicioSesion(VentanaInicioSesion ventanaIniciar) {
		this.ventanaIniciar = ventanaIniciar;
		setLayout(new FlowLayout());
		
		butCancelar = new JButton("Cancelar");
		butCancelar.addActionListener(this);
		butCancelar.setActionCommand(CANCELAR);
		butCancelar.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		butIniciarSesion = new JButton("Iniciar Sesion");
		butIniciarSesion.addActionListener(this);
		butIniciarSesion.setActionCommand(INICIAR);
		butIniciarSesion.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		add(butCancelar);
		add(butIniciarSesion);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand( );
		if (comando.equals(CANCELAR))
		{
			ventanaIniciar.volverInicio();
		}
		else if (comando.equals(INICIAR))
		{
			ventanaIniciar.iniciarSesion();
		}
	}

}
