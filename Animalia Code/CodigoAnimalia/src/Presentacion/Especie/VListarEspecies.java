package Presentacion.Especie;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import java.util.Set;
import javax.swing.JTable;

import Negocio.Especie.TEspecie;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VListarEspecies extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public VListarEspecies(Set<TEspecie> listaEspecies) {
		super("Mostrar todas las Especies");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI((Set<TEspecie>) listaEspecies);
	}

	private void initGUI(Set<TEspecie> listaEspecies) {
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
				VListarEspecies.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VESPECIE, 
            			null));
			}
		});
		panelBotones.add(botonCancelar);

		String[] nombreColumnas = { "ID", "NombreEspecie", "Id Habitat","Activa" };
		JTable tabla = ComponentsBuilder.createTable(listaEspecies.size(), 4, nombreColumnas);
		int i = 0;
		for (TEspecie t : listaEspecies) {
			tabla.setValueAt(t.getID(), i, 0);
			tabla.setValueAt(t.getNombreEspecie(), i, 1);
			tabla.setValueAt(t.getID_habitat(), i, 2);
			tabla.setValueAt(t.getActivo(), i, 3);
			i++;
		}
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(50, 115, 900, 288);
		this.add(scroll);

		this.setVisible(true);
		this.setResizable(true);
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	
}