package Presentacion.VentaJPA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import Negocio.VentaJPA.TCarritoJPA;
import Negocio.VentaJPA.TLineaVenta;
import Negocio.VentaJPA.TVenta;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class VCerrarVentaJPA extends JFrame implements IGUI {
	 
	 private TCarritoJPA tCarrito;

	 public VCerrarVentaJPA(TCarritoJPA Carrito){
		 super("Cerrar Venta");
	        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	        int ancho = 800;
	        int alto = 800;
	        int x = (pantalla.width - ancho) / 2;
	        int y = (pantalla.height - alto) / 2;
	        this.setBounds(x, y, ancho, alto);
	        this.setLayout(new BorderLayout());
	        this.setResizable(false);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.tCarrito = Carrito;
	        initGUI();
	 }
	private void initGUI() {
		TVenta venta = tCarrito.getVenta();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		
		//Añadir el ID del Producto
		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
				"Introduzca el ID de el producto que desea agregar a la venta ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);
		
		JPanel panelID = new JPanel();
		panelID.setBorder(new EmptyBorder(10, 10, 10, 10)); // agregado
		panelID.setAlignmentX(CENTER_ALIGNMENT); // agregado
		mainPanel.add(panelID);

		JLabel idProducto = new JLabel("Id Producto");
		idProducto.setPreferredSize(new Dimension(100, 30));
		panelID.add(idProducto);

		JTextField id = new JTextField();
		id.setPreferredSize(new Dimension(250, 30));
		id.setEditable(true);
		panelID.add(id);

		JPanel panelCantidad = new JPanel();
		panelCantidad.setBorder(new EmptyBorder(10, 10, 10, 10)); // agregado
		panelCantidad.setAlignmentX(CENTER_ALIGNMENT); // agregado
		mainPanel.add(panelCantidad);
		
		JLabel cantidad = new JLabel("Cantidad a Añadir:");
		cantidad.setPreferredSize(new Dimension(100, 60));
		panelCantidad.add(cantidad);

		JTextField cantidadtxt = new JTextField();
		cantidadtxt.setPreferredSize(new Dimension(250, 30));
		cantidadtxt.setEditable(true);
		panelCantidad.add(cantidadtxt);

		JPanel panelAniadirButton = new JPanel();
		panelAniadirButton.setBorder(new EmptyBorder(10, 10, 10, 10)); // agregado
		panelAniadirButton.setAlignmentX(CENTER_ALIGNMENT); // agregado
		mainPanel.add(panelAniadirButton);

		JButton botonAniadirProducto = new JButton("Aniadir");
		botonAniadirProducto.setBounds(75, 50, 100, 100);
		botonAniadirProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VCerrarVentaJPA.this.setVisible(false);
				try {
					//Comprobamos que los campos no sean vacios
					if(id.getText().isEmpty() || cantidadtxt.getText().isEmpty()){
						claseContenedorErroresVenta erroresVenta = new claseContenedorErroresVenta(-3,tCarrito, venta);
		    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_VENTA_VISTA, erroresVenta));
					}
					else{
						//Creamos un linea venta
						int idProducto = Integer.parseInt(id.getText());
						//Quiero pillar el precio del producto
						int precioProducto = 0;
	                    int cantidad = Integer.parseInt(cantidadtxt.getText());
	                    TLineaVenta lineaVenta = new TLineaVenta(0, idProducto, cantidad, precioProducto);
	                    boolean modificaciónLineaVenta= false;
	                    
	                    //Comprobamos si la linea venta ya existe en el carrito en caso de ser asi la modificamos
	                    Set<TLineaVenta> lineaVentas = tCarrito.getLineasVenta();
	                    for(TLineaVenta linea: lineaVentas){
	                    	if(linea.getIdProducto() == idProducto){
	                    		linea.setUnidades(linea.getUnidades() + cantidad);
	                    		modificaciónLineaVenta = true;
	                    	}
	                    }
	                    //Si no hemos modificado ninguna linea venta entonces la agregamos al carrito directamente
	                    if(!modificaciónLineaVenta)
	                    	//Agregamos la linea Venta a las lineas de venta del carrito
	                    	lineaVentas.add(lineaVenta);
	                    tCarrito.setLineasVentas(lineaVentas);
	                    ApplicationController.getInstance().manageRequest(new Context(Evento.VCERRAR_VENTA, tCarrito));
					}
					
				} catch (Exception ex) {// Excepcion para campos Integer
					claseContenedorErroresVenta erroresVenta = new claseContenedorErroresVenta(-4,tCarrito, venta);
	    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_VENTA_VISTA, erroresVenta));
				}

			}
		});
		panelAniadirButton.add(botonAniadirProducto);
		
		// Panel Quitar Producto
				JLabel msg = ComponentsBuilder.createLabel("Introduzca el ID de el producto que desea eliminar de la venta ",
						1, 150, 80, 20, Color.BLACK);
				msg.setAlignmentX(CENTER_ALIGNMENT);
				mainPanel.add(msg);

				JPanel panelQuitar = new JPanel();
				panelQuitar.setBorder(new EmptyBorder(10, 10, 10, 10));
				panelQuitar.setAlignmentX(CENTER_ALIGNMENT);
				mainPanel.add(panelQuitar);

				JLabel idProductoOut = new JLabel("Id Producto");
				idProductoOut.setPreferredSize(new Dimension(100, 30));
				panelQuitar.add(idProductoOut);

				JTextField idOut = new JTextField();
				idOut.setPreferredSize(new Dimension(250, 30));
				idOut.setEditable(true);
				panelQuitar.add(idOut);

				JPanel panelCantidadOut = new JPanel();
				panelCantidadOut.setBorder(new EmptyBorder(10, 10, 10, 10)); // agregado
				panelCantidadOut.setAlignmentX(CENTER_ALIGNMENT); // agregado
				mainPanel.add(panelCantidadOut);

				JLabel cantidadOut = new JLabel("Cantidad a Quitar:");
				cantidadOut.setPreferredSize(new Dimension(100, 60));
				panelCantidadOut.add(cantidadOut);

				JTextField cantidadOuttxt = new JTextField();
				cantidadOuttxt.setPreferredSize(new Dimension(250, 30));
				cantidadOuttxt.setEditable(true);
				panelCantidadOut.add(cantidadOuttxt);
				
				JPanel panelQuitarButton = new JPanel();
				panelQuitarButton.setBorder(new EmptyBorder(10, 10, 10, 10));
				panelQuitarButton.setAlignmentX(CENTER_ALIGNMENT);
				mainPanel.add(panelQuitarButton);

				JButton botonQuitarProducto = new JButton("Quitar");
				botonQuitarProducto.setBounds(75, 50, 100, 100);
				botonQuitarProducto.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						VCerrarVentaJPA.this.setVisible(false);
						try {
							
							//Comprobamos que los campos no sean vacios
							if(idOut.getText().isEmpty() || cantidadOuttxt.getText().isEmpty()){
								claseContenedorErroresVenta erroresVenta = new claseContenedorErroresVenta(-3,tCarrito, venta);
				    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_VENTA_VISTA, erroresVenta));
							}
							else{
								int idProducto = Integer.parseInt(idOut.getText());
								int cantidad = Integer.parseInt(cantidadOuttxt.getText());
								boolean correct = true;
								boolean encontrado = false;
								//Comprobamos que el numero de unidades a eliminar no es superior al numero de unidades solicitadas anteriormente
			                    Set<TLineaVenta> lineaVentas = tCarrito.getLineasVenta();
			                    for(TLineaVenta linea: lineaVentas){
			                    	if(linea.getIdProducto() == idProducto){
			                    		encontrado = true;
										if(cantidad > linea.getUnidades())
							        		correct = false; 
										else{
											int cantidadTotal = linea.getUnidades() - cantidad;
											if(cantidadTotal == 0) //Si la cantidad es 0, entonces eliminamos la linea
												lineaVentas.remove(linea);
											else //Si no es 0, actualizamos la linea 
												linea.setUnidades(cantidadTotal);
										}
									}
										
			                    }
			                    if(!encontrado) //Si el producto no esta en la venta, debemos mostrar un error
			                    {
			                    	claseContenedorErroresVenta erroresVenta = new claseContenedorErroresVenta(-2,tCarrito, venta);
					    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_VENTA_VISTA, erroresVenta));
			                    }
			                    else if(!correct){
			                    	claseContenedorErroresVenta erroresVenta = new claseContenedorErroresVenta(-1,tCarrito, venta);
					    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_VENTA_VISTA, erroresVenta));
			                    }
			                    else{ //si no hay error entonces eliminamos la linea
			                        tCarrito.setLineasVentas(lineaVentas);
			                        ApplicationController.getInstance().manageRequest(new Context(Evento.VCERRAR_VENTA, tCarrito));
			                    }

							}

						} catch (Exception ex) {// Excepcion para campos Integer
							claseContenedorErroresVenta erroresVenta = new claseContenedorErroresVenta(-4,tCarrito, venta);
			    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_VENTA_VISTA, erroresVenta));
						}

					}
				});
			panelQuitarButton.add(botonQuitarProducto);
			//Panel para seleccionar el tipo de pago a realizar
			JPanel panelModoPago = new JPanel();
			mainPanel.add(panelModoPago);

			JLabel labelModoPago = ComponentsBuilder.createLabel("                Modo de Pago: ", 10, 100, 80, 20,
					Color.BLACK);
			panelModoPago.add(labelModoPago);

			JComboBox<String> tipoPago = new JComboBox<String>();
			tipoPago.addItem("Tarjeta de Crédito/Débito");
			tipoPago.addItem("Pago en Efectivo");
			tipoPago.addItem("Transferencia Bancaria");
			tipoPago.setPreferredSize(new Dimension(250, 25));
			panelModoPago.add(tipoPago);

			mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
			// Panel para Tabla

			Set<TLineaVenta> LineasVenta = tCarrito.getLineasVenta();
			String[] nombreColumnas = {"Id Venta", "Id Producto", "Cantidad" };
			JTable tabla = ComponentsBuilder.createTable(LineasVenta.size(), 3, nombreColumnas);
			int i = 0;
			for (TLineaVenta t : LineasVenta) {
				tabla.setValueAt(t.getIdVenta(), i, 0);
				tabla.setValueAt(t.getIdProducto(), i, 1);
				tabla.setValueAt(t.getUnidades(), i, 2);
				i++;
			}
			JScrollPane scroll = new JScrollPane(tabla);
			scroll.setBounds(50, 115, 900, 288);
			this.add(scroll);

			// Panel de botones
			JPanel panelBotones = new JPanel();
			mainPanel.add(panelBotones);

			JButton botonCancelar = new JButton("Cancelar");
			botonCancelar.setBounds(200, 50, 100, 100);
			botonCancelar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VCerrarVentaJPA.this.setVisible(false);
		            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VVENTA, null));

				}
			});
			panelBotones.add(botonCancelar);

			JButton botonCerrar = new JButton("Cerrar Venta");
			botonCerrar.setBounds(75, 50, 100, 100);
			botonCerrar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					VCerrarVentaJPA.this.setVisible(false);
				    String metodoPagoSeleccionado = (String) tipoPago.getSelectedItem();

					venta.setMetodo_pago(metodoPagoSeleccionado);
					//Devolvemos la clase contenedora para poder enviar el TVenta y TCarrito al command
					claseContenedorErroresVenta correctVenta = new claseContenedorErroresVenta(0,tCarrito, venta);
		            ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_VENTA, correctVenta));
				}
			});
			panelBotones.add(botonCerrar);
			this.setVisible(true);
			this.setResizable(true);
	}
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.CERRAR_VENTA_OK) {	
			TCarritoJPA aux = (TCarritoJPA) res.getDatos();
			int respuesta = aux.getVenta().getId();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, respuesta));
		} else if (res.getEvento() == Evento.CERRAR_VENTA_KO) {
			TCarritoJPA aux = (TCarritoJPA) res.getDatos();
			int respuesta = aux.getVenta().getId();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, respuesta));
		}else if(res.getEvento() == Evento.VCERRAR_VENTA){
			this.tCarrito = (TCarritoJPA) res.getDatos();
			initGUI();
			
		}
		//ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_ESPECIES)); //Mostramos especies para que se vea que se ha modificado correctamente
//		dispose(); //Liberamos recursos de la memoria
		
	}
}