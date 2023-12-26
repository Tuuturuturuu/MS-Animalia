package Presentacion.Pase;

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


import Negocio.Pase.TPase;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VMostrarPasePorIdHabitat extends JFrame implements IGUI {

	private Set<TPase> listaPases;
	
	public VMostrarPasePorIdHabitat (Set<TPase> Pases){
		super("Mostrar todos los Pases");
		this.listaPases = Pases;
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}
	
	private void initGUI(){
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
				VMostrarPasePorIdHabitat.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPASE, 
            			null));
			}
		});
		panelBotones.add(botonCancelar);
		
		String[] nombreColumnas = { "ID", "ID_habitat", "Fecha","Hora","Precio","Cantidad_Disponible","Activo" };
		JTable tabla = ComponentsBuilder.createTable(listaPases.size(), 7, nombreColumnas);
		int i = 0;
		for (TPase t : listaPases) {
			tabla.setValueAt(t.getID(), i, 0);
			tabla.setValueAt(t.getIDHabitat(), i, 1);
			tabla.setValueAt(t.getFecha(), i, 2);
			tabla.setValueAt(t.getHora(), i, 3);
			tabla.setValueAt(t.getPrecio(), i, 4);
			tabla.setValueAt(t.getCantidadDisponible(), i, 5);
			tabla.setValueAt(t.getActivo(), i, 6);

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
		if (res.getDatos() != null) {
			this.listaPases = (Set<TPase>) res.getDatos();
			initGUI();
		}	
		
	}

}
