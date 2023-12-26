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
import Negocio.Animal.TAnimalNoAcuatico;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VListarAnimalesNoAcuaticos extends JFrame implements IGUI {

	private Set<JButton> jButton;
	private Set<JLabel> jLabel;
	private Set<JPanel> jPanel;
	private Set<JTable> jTable;

	public VListarAnimalesNoAcuaticos(Set<TAnimalNoAcuatico> listaAnimalesNoAcuaticos) {
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
		initGUI((Set<TAnimalNoAcuatico>) listaAnimalesNoAcuaticos);
	}

	private void initGUI(Set<TAnimalNoAcuatico> listaAnimalesNoAcuaticos) {
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
				VListarAnimalesNoAcuaticos.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VANIMAL, null));
			}
		});
		panelBotones.add(botonCancelar);

		String[] nombreColumnas = { "ID", "Nombre", "ID Especie", "Activo", "Numero de patas"};
		JTable tabla = ComponentsBuilder.createTable(listaAnimalesNoAcuaticos.size(), 5, nombreColumnas);
		
		int i = 0;
		for (TAnimal a : listaAnimalesNoAcuaticos) {
			tabla.setValueAt(a.getId(), i, 0);
			tabla.setValueAt(a.getNombre(), i, 1);
			tabla.setValueAt(a.getId_Especie(), i, 2);
			tabla.setValueAt(a.isActivo(), i, 3);
			if (a.getTipo() == 0) {
				TAnimalNoAcuatico aux = (TAnimalNoAcuatico) a;
				tabla.setValueAt(aux.getNumPatas(), i, 4);
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