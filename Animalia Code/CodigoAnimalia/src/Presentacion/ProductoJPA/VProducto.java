package Presentacion.ProductoJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.ProductoJPA.TAlimentacion;
import Negocio.ProductoJPA.TMerchandising;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

public class VProducto extends JFrame implements IGUI {

	private JButton bAltaProducto;
	private JButton bBajaProducto;
	private JButton bModificarProducto;
	private JButton bMostrarProducto;
	private JButton bListarProductos;
	private JButton bListarProductosPorMarca;
	private JButton bBack;
	private JPanel j;

	private TProducto tProducto;
	private List<TProducto> hsProducto;
	private Set<TAlimentacion> hsAlimentacion;
	private Set<TMerchandising> hsMerchandising;

	public VProducto() {
		super("Animalia");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		j = new JPanel();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		this.setVisible(true);
	}

	public void initGUI() {

		tProducto = new TProducto();
		hsProducto = new ArrayList<TProducto>();
		hsAlimentacion = new HashSet<TAlimentacion>();
		hsMerchandising = new HashSet<TMerchandising>();
		JLabel label = ComponentsBuilder.createLabel("Producto", 250, 30, 500, 50, Color.BLACK);
		this.add(label);

		// ALTA PRODUCTO
		bAltaProducto = ComponentsBuilder.createButton("Alta Producto", 100, 100, 185, 100);
		bAltaProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_PRODUCTO, tProducto));
			}

		});
		bAltaProducto.setVisible(true);
		this.add(bAltaProducto);

		// BAJA PRODUCTO
		bBajaProducto = ComponentsBuilder.createButton("Baja Producto", 300, 100, 185, 100);
		bBajaProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_PRODUCTO, tProducto));
			}

		});
		bBajaProducto.setVisible(true);
		this.add(bBajaProducto);

		// MODIFICAR PRODUCTO
		bModificarProducto = ComponentsBuilder.createButton("Modificar Producto", 500, 100, 185, 100);
		bModificarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_PRODUCTO, tProducto));
			}

		});
		bModificarProducto.setVisible(true);
		this.add(bModificarProducto);

		// MOSTRAR PRODUCTO
		bMostrarProducto = ComponentsBuilder.createButton("Mostrar Producto", 700, 100, 185, 100);
		bMostrarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_PRODUCTO, tProducto));
			}

		});
		bMostrarProducto.setVisible(true);
		this.add(bMostrarProducto);

		// LISTAR PRODUCTOS
		bListarProductos = ComponentsBuilder.createButton("Listar Productos", 100, 250, 185, 100);
		bListarProductos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_PRODUCTOS, hsProducto));
			}

		});
		bListarProductos.setVisible(true);
		this.add(bListarProductos);

		// LISTAR PRODUCTOS POR MARCA
		bListarProductosPorMarca = ComponentsBuilder.createButton("Listar Productos Por Marca", 700, 250, 185, 100);
		bListarProductosPorMarca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProducto.this.setVisible(false);
				ApplicationController.getInstance()
						.manageRequest(new Context(Evento.VLISTAR_PRODUCTOS_POR_MARCA, tProducto));
			}

		});
		bListarProductosPorMarca.setVisible(true);
		this.add(bListarProductosPorMarca);

		// BACK BUTTON
		bBack = ComponentsBuilder.createBackButton();
		bBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProducto.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_MAIN_VIEW, null));
				dispose();
			}
		});
		bBack.setVisible(true);
		this.add(bBack);

		getContentPane().add(j);

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
