package Presentacion.ProveedorJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Animal.TAnimal;
import Negocio.ProveedorJPA.TProveedor;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

@SuppressWarnings("serial")
public class VProveedorJPA extends JFrame implements IGUI{
	private JButton bAltaProveedor;
	private JButton bBajaProveedor;
	private JButton bModificarProveedor;
	private JButton bMostrarProveedor;
	private JButton bListarProveedores;
	private JButton bVincularProveedorConMarca;
	private JButton bDesvincularProveedorConMarca;
	private JButton bListarProveedoresPorMarca;


	private JButton backButton;
	private JPanel j;

	private TProveedor tProveedor;
	private Set<TProveedor> hsProveedor;

	public VProveedorJPA(){
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
	private void initGUI() {
		tProveedor = new TProveedor();
		hsProveedor = new HashSet<TProveedor> ();
		JLabel label = ComponentsBuilder.createLabel("Proveedor", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ALTA PROVEEDOR
		bAltaProveedor = ComponentsBuilder.createButton("Alta Proveedor", 100, 100, 185, 100);
		bAltaProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_PROVEEDOR, tProveedor));
			}

		});
		bAltaProveedor.setVisible(true);
		this.add(bAltaProveedor);
		
		//BAJA PROVEEDOR
		bBajaProveedor = ComponentsBuilder.createButton("Baja Proveedor", 300, 100, 185, 100);
		bBajaProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_PROVEEDOR, tProveedor));
			}

		});
		bBajaProveedor.setVisible(true);
		this.add(bBajaProveedor);
		
		//MODIFICAR PROVEEDOR
		bModificarProveedor = ComponentsBuilder.createButton("Modificar Proveedor", 500, 100, 185, 100);
		bModificarProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_PROVEEDOR, tProveedor));
			}

		});
		bModificarProveedor.setVisible(true);
		this.add(bModificarProveedor);
		
		//MOSTRAR PROVEEDOR
		bMostrarProveedor = ComponentsBuilder.createButton("Mostrar Proveedor", 700, 100, 185, 100);
		bMostrarProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_PROVEEDOR, tProveedor));
			}

		});
		bMostrarProveedor.setVisible(true);
		this.add(bMostrarProveedor);
		
		//LISTAR PROVEEDOR
		bListarProveedores = ComponentsBuilder.createButton("Listar Proveedores", 100, 250, 185, 100);
		bListarProveedores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_PROVEEDORES, hsProveedor));
			}

		});
		bListarProveedores.setVisible(true);
		this.add(bListarProveedores);
		
		//LISTAR PROVEEDOR POR MARCA DE PRODUCTO
				bListarProveedoresPorMarca = ComponentsBuilder.createButton("Listar Proveedor por Marca de Producto", 300, 250, 185, 100);
				bListarProveedoresPorMarca.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						VProveedorJPA.this.setVisible(false);
						ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_PROVEEDORES_POR_MARCA, tProveedor));
					}

				});
				bListarProveedoresPorMarca.setVisible(true);
				this.add(bListarProveedoresPorMarca);
				
		
		//VINCULAR PROVEEDOR CON MARCA DE PRODUCTO
		bVincularProveedorConMarca = ComponentsBuilder.createButton("Vincular Proveedor con Marca de Producto", 500, 250, 185, 100);
		bVincularProveedorConMarca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VVINCULAR_PROVEEDOR_MARCA, tProveedor));
			}

		});
		bVincularProveedorConMarca.setVisible(true);
		this.add(bVincularProveedorConMarca);
		
		//DESVINCULAR PROVEEDOR CON MARCA DE PRODUCTO
		bDesvincularProveedorConMarca = ComponentsBuilder.createButton("Desvincular Proveedor con Marca de Producto", 700, 250, 185, 100);
		bDesvincularProveedorConMarca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VDESVINCULAR_PROVEEDOR_MARCA, tProveedor));
			}

		});
		bDesvincularProveedorConMarca.setVisible(true);
		this.add(bDesvincularProveedorConMarca);
		
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VProveedorJPA.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_MAIN_VIEW,null));
				dispose();
			}
		});
		backButton.setVisible(true);
		this.add(backButton);
		
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
