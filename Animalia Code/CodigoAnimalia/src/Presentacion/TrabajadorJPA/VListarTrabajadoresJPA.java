/**
 * 
 */
package Presentacion.TrabajadorJPA;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.Empleado.VListarEmpleado;
import Presentacion.FactoriaVistas.IGUI;
import javax.swing.JTextField;

import Negocio.TrabajadorJPA.TTrabajador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class VListarTrabajadoresJPA extends JFrame implements IGUI {
	
	public VListarTrabajadoresJPA(List<TTrabajador> listaTrabajadores) {
		super("Mostrar todos los Trabajadores");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI((List<TTrabajador>) listaTrabajadores);
	}
	
	private void initGUI(List<TTrabajador> listaTrabajadores) {
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
				VListarTrabajadoresJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VTRABAJADOR, 
            			null));
			}
		});
		panelBotones.add(botonCancelar);

		String[] nombreColumnas = { "ID", "DNI", "Nombre", "Sueldo", "Telefono", "Activa", "Tipo"};
		JTable tabla = ComponentsBuilder.createTable(listaTrabajadores.size(), 7, nombreColumnas);
		int i = 0;
		for (TTrabajador t : listaTrabajadores) {
			tabla.setValueAt(t.getId(), i, 0);
			tabla.setValueAt(t.getDni(), i, 1);
			tabla.setValueAt(t.getNombre(), i, 2);
			tabla.setValueAt(t.getSueldo(), i, 3);
			tabla.setValueAt(t.getTelefono(), i, 4);
			tabla.setValueAt(t.getActivo(), i, 5);
			if(t.getTipo() == 0){ // COMPLETO
				tabla.setValueAt("Completo", i, 6);
				
			}
			else if(t.getTipo() == 1) { // PARCIAL
				tabla.setValueAt("Parcial", i, 6);
			}
			
			i++;
		}
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(50, 115, 900, 288);
		this.add(scroll);

		this.setVisible(true);
		this.setResizable(true);
	}
	
	
	public void actualizar() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}
}