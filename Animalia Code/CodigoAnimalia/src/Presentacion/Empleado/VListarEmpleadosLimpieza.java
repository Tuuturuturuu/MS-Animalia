package Presentacion.Empleado;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VListarEmpleadosLimpieza extends JFrame implements IGUI {

	public VListarEmpleadosLimpieza(Set<TEmpleadoLimpieza> listaEmpleadosLimpieza) {
		super("Mostrar todos los Empleados");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI((Set<TEmpleadoLimpieza>) listaEmpleadosLimpieza);
	}
	
	private void initGUI(Set<TEmpleadoLimpieza> listaEmpleadosLimpieza) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VListarEmpleadosLimpieza.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VEMPLEADO, 
            			null));
			}
		});
		panelBotones.add(botonCancelar);

		String[] nombreColumnas = { "ID", "NombreEmpleado", "DniEmpleado", "SueldoBase", "Telefono", "Activa" , "Suplemento", "Zona"};
		JTable tabla = ComponentsBuilder.createTable(listaEmpleadosLimpieza.size(), 8, nombreColumnas);
		int i = 0;
		for (TEmpleadoLimpieza t : listaEmpleadosLimpieza) {
			tabla.setValueAt(t.getId(), i, 0);
			tabla.setValueAt(t.getNombre(), i, 1);
			tabla.setValueAt(t.getDni(), i, 2);
			tabla.setValueAt(t.getSueldoBase(), i, 3);
			tabla.setValueAt(t.getTelefono(), i, 4);
			tabla.setValueAt(t.getActivo(), i, 5);
			
			if(t.getTipo() == 0){ // LIMPIEZA
				TEmpleadoLimpieza aux = (TEmpleadoLimpieza) t;
				tabla.setValueAt(aux.getSuplemento(), i, 6);
				tabla.setValueAt(aux.getZona(), i, 7);
			}
			
			i++;
		}
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(50, 115, 900, 288);
		this.add(scroll);

		this.setVisible(true);
		this.setResizable(true);
	}
	
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}

}
