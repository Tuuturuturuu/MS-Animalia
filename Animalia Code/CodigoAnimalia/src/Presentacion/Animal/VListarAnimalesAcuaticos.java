/**
 * 
 */
package Presentacion.Animal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Negocio.Animal.TAnimal;
import Negocio.Animal.TAnimalAcuatico;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VListarAnimalesAcuaticos extends JFrame implements IGUI {

	private Set<JButton> jButton;
	private Set<JLabel> jLabel;
	private Set<JPanel> jPanel;
	private Set<JTable> jTable;

	public VListarAnimalesAcuaticos(Set<TAnimalAcuatico> listaAnimalesAcuaticos) {
		super("Mostrar todos los Animales");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI((Set<TAnimalAcuatico>) listaAnimalesAcuaticos);
	}

	private void initGUI(Set<TAnimalAcuatico> listaAnimalesAcuaticos) {
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
				VListarAnimalesAcuaticos.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VANIMAL, null));
			}
		});
		panelBotones.add(botonCancelar);

		String[] nombreColumnas = { "ID", "Nombre", "ID Especie", "Activo", "Temperatura", "Tipo de agua"};
		JTable tabla = ComponentsBuilder.createTable(listaAnimalesAcuaticos.size(), 6, nombreColumnas);
		
		int i = 0;
		for (TAnimal a : listaAnimalesAcuaticos) {
			tabla.setValueAt(a.getId(), i, 0);
			tabla.setValueAt(a.getNombre(), i, 1);
			tabla.setValueAt(a.getId_Especie(), i, 2);
			tabla.setValueAt(a.isActivo(), i, 3);
			if (a.getTipo() == 1) {
				TAnimalAcuatico aux = (TAnimalAcuatico) a;
				tabla.setValueAt(aux.getTemperatura(), i, 4);
				tabla.setValueAt(aux.getTipoAgua(), i, 5);
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