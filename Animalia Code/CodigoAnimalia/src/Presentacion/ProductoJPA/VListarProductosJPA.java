package Presentacion.ProductoJPA;

import javax.swing.JFrame;

import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import java.util.List;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Negocio.ProductoJPA.TAlimentacion;
import Negocio.ProductoJPA.TProducto;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class VListarProductosJPA extends JFrame implements IGUI {

	private Set<JButton> jButton;
	private Set<JLabel> jLabel;
	private Set<JPanel> jPanel;
	private Set<JTable> jTable;

	public VListarProductosJPA(List<TProducto> listaProductos) {
		super("Mostrar todos los Productos");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI((List<TProducto>) listaProductos);
	}

	private void initGUI(List<TProducto> listaProductos) {
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
				VListarProductosJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPRODUCTO, null));
			}
		});
		panelBotones.add(botonCancelar);

		String[] nombreColumnas = { "ID", "Nombre", "Precio", "Stock", "Activo", "ID Marca Producto", "Tipo"};
		JTable tabla = ComponentsBuilder.createTable(listaProductos.size(), 7, nombreColumnas);
		
		int i = 0;
		for (TProducto p : listaProductos) {
			tabla.setValueAt(p.getID(), i, 0);
			tabla.setValueAt(p.getNombre(), i, 1);
			tabla.setValueAt(p.getPrecio(), i, 2);
			tabla.setValueAt(p.getStock(), i, 3);
			tabla.setValueAt(p.getActivo(), i, 4);
			tabla.setValueAt(p.getIdMarcaProducto(), i, 5);
			if (p instanceof TAlimentacion) {
				tabla.setValueAt("Alimentacion", i, 6);
			}
			else
				tabla.setValueAt(" Merchadising", i, 6);
			
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