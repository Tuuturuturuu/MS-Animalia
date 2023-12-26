/**
 * 
 */
package Presentacion.ProductoJPA;

import javax.swing.JFrame;
import Presentacion.FactoriaVistas.IGUI;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Negocio.ProductoJPA.TAlimentacion;
import Negocio.ProductoJPA.TMerchandising;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;

public class VAltaProductoJPA extends JFrame implements IGUI {

	private JDialog jDialog;
	private JLabel jLabel;
	private JPanel jPanel;
	private JButton jButton;
	private JTextField jTextField;

	public VAltaProductoJPA() {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder
				.createLabel("Seleccione el tipo de producto que desea dar de alta ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		// TIPO PRODUCTO
		JPanel panelTProducto = new JPanel();
		mainPanel.add(panelTProducto);

		JLabel labelTProducto = ComponentsBuilder.createLabel("                Tipo de Producto: ", 10, 100, 80, 20,
				Color.BLACK);
		panelTProducto.add(labelTProducto);

		JComboBox<String> tipoProducto = new JComboBox<String>();
		tipoProducto.addItem("Alimentacion");
		tipoProducto.addItem("Merchandising");
		tipoProducto.setPreferredSize(new Dimension(250, 25));
		panelTProducto.add(tipoProducto);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// NOMBRE
		JPanel panelNombre = new JPanel();
		mainPanel.add(panelNombre);

		JLabel labelNombre = ComponentsBuilder.createLabel("Nombre: ", 10, 100, 80, 20, Color.BLACK);
		panelNombre.add(labelNombre);

		JTextField nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(250, 30));
		nombre.setEditable(true);
		panelNombre.add(nombre);

		// ID MARCA
		JPanel panelIdMarca = new JPanel();
		mainPanel.add(panelIdMarca);

		JLabel labelIdMarca = ComponentsBuilder.createLabel("ID Marca: ", 10, 100, 80, 20, Color.BLACK);
		panelIdMarca.add(labelIdMarca);

		JTextField idMarca = new JTextField();
		idMarca.setPreferredSize(new Dimension(250, 30));
		idMarca.setEditable(true);
		panelIdMarca.add(idMarca);

		// PRECIO
		JPanel panelPrecio = new JPanel();
		mainPanel.add(panelPrecio);

		JLabel labelPrecio = ComponentsBuilder.createLabel("Precio: ", 10, 100, 80, 20, Color.BLACK);
		panelPrecio.add(labelPrecio);

		JTextField precio = new JTextField();
		precio.setPreferredSize(new Dimension(250, 30));
		precio.setEditable(true);
		panelPrecio.add(precio);

		// STOCK
		JPanel panelStock = new JPanel();
		mainPanel.add(panelStock);

		JLabel labelStock = ComponentsBuilder.createLabel("Stock: ", 10, 100, 80, 20, Color.BLACK);
		panelStock.add(labelStock);

		JTextField stock = new JTextField();
		stock.setPreferredSize(new Dimension(250, 30));
		stock.setEditable(true);
		panelStock.add(stock);

		// TIPO (SOLO PARA ALIMENTACIÓN)
		JPanel panelType = new JPanel();
		mainPanel.add(panelType);

		JLabel labelType = ComponentsBuilder.createLabel("Tipo producto alimentacion: ", 10, 100, 80, 20, Color.BLACK);
		panelType.add(labelType);

		JTextField type = new JTextField();
		type.setPreferredSize(new Dimension(250, 30));
		type.setEditable(true);
		panelType.add(type);

		panelType.setVisible(true);

		// CATEGORIA (SOLO PARA MERCHANDISING)
		JPanel panelCat = new JPanel();
		mainPanel.add(panelCat);

		JLabel labelCat = ComponentsBuilder.createLabel("Categoria: ", 10, 100, 80, 20, Color.BLACK);
		panelCat.add(labelCat);

		JTextField cat = new JTextField();
		cat.setPreferredSize(new Dimension(250, 30));
		cat.setEditable(true);
		panelCat.add(cat);

		panelCat.setVisible(false);

		// EDICION LIMITADA (SOLO PARA MERCHANDISING)
		JPanel panelEdicion = new JPanel();
		mainPanel.add(panelEdicion);

		JLabel labelEdicion = ComponentsBuilder.createLabel("Edicion limitada: ", 10, 100, 80, 20, Color.BLACK);
		panelEdicion.add(labelEdicion);

		JTextField edicion = new JTextField();
		edicion.setPreferredSize(new Dimension(250, 30));
		edicion.setEditable(true);
		panelEdicion.add(edicion);

		panelEdicion.setVisible(false);

		tipoProducto.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String option = (String) tipoProducto.getSelectedItem();
				if (option.equals("Alimentacion")) {
					panelType.setVisible(true);
					panelCat.setVisible(false);
					panelEdicion.setVisible(false);
				} else if (option.equals("Merchandising")) {
					panelType.setVisible(false);
					panelCat.setVisible(true);
					panelEdicion.setVisible(true);
				}
			}

		});

		// BOTONES ACEPTAR CANCELAR
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		// BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAltaProductoJPA.this.setVisible(false);
				int tipo = -47548;
				TProducto prod = null;
				try {
					if (tipoProducto.getSelectedItem() == "Alimentacion") {

						String nombreT = nombre.getText();
						Double precioT = Double.parseDouble(precio.getText());
						Integer stockT = Integer.parseInt(stock.getText());
						String tType = type.getText();
						Integer idMarcaT = Integer.parseInt(idMarca.getText());

						TAlimentacion alimentacion = new TAlimentacion(0, nombreT != null ? nombreT : "",
								precioT != null ? precioT : 0, !stock.getText().isEmpty() ? stockT : 0,
								tType != null ? tType : "", true, idMarcaT != null ? idMarcaT:0);

						prod = alimentacion;
						ApplicationController.getInstance()
								.manageRequest(new Context(Evento.ALTA_PRODUCTO, prod));

					} else if (tipoProducto.getSelectedItem() == "Merchandising") {

						String nombreT = nombre.getText();
						Double precioT = Double.parseDouble(precio.getText());
						Integer stockT = Integer.parseInt(stock.getText());
						String catT = cat.getText();
						String edicionT = edicion.getText();
						Integer idMarcaT = Integer.parseInt(idMarca.getText());
						
						TMerchandising merch = new TMerchandising(0, nombreT != null ? nombreT : "",
								precioT != null ? precioT : 0, !stock.getText().isEmpty() ? stockT : 0,
								catT != null ? catT : "", edicionT != null ? edicionT : "", true, idMarcaT != null ? idMarcaT: 0);

						prod = merch;
						ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_PRODUCTO, prod));

					}
				} catch (Exception ex) {
					ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, -4));
				}

			}
		});

		panelBotones.add(botonAceptar);

		// BOTON CANCELAR
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAltaProductoJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VPRODUCTO, null));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}

	public void actualizar() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void actualizar(Context res) {
		if (res.getEvento() == Evento.ALTA_PRODUCTO_OK) {
			ApplicationController.getInstance().manageRequest(new Context(Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.ALTA_PRODUCTO_KO) {
			ApplicationController.getInstance().manageRequest(new Context(Evento.V_ERRORES, (int) res.getDatos()));

		}
		dispose();
	}
}