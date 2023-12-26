package Presentacion.Factura;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Integracion.Factura.TLineaFactura;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

@SuppressWarnings("serial")
public class VCerrarFactura extends JFrame implements IGUI {
    
    private TCarrito tCarrito;

    public VCerrarFactura(TCarrito Carrito) {
        super("Cerrar Factura");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 600;
        int alto = 600;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.tCarrito = Carrito;
        initGUI();
    }

    public void initGUI() {
    	
    	TFactura factura = new TFactura();
    	JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
				"Introduzca el ID de el pase que desea agregar a la factura ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		// Panel para agregar Pase
		JPanel panelID = new JPanel();
		panelID.setBorder(new EmptyBorder(10, 10, 10, 10)); // agregado
		panelID.setAlignmentX(CENTER_ALIGNMENT); // agregado
		mainPanel.add(panelID);

		JLabel idPase = new JLabel("Id Pase");
		idPase.setPreferredSize(new Dimension(100, 30));
		panelID.add(idPase);

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

		JButton botonAniadirPase = new JButton("Aniadir");
		botonAniadirPase.setBounds(75, 50, 100, 100);
		botonAniadirPase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VCerrarFactura.this.setVisible(false);
				try {
					//Comprobamos que los campos no sean vacios
					if(id.getText().isEmpty() || cantidadtxt.getText().isEmpty()){
						claseContenedorErroresFactura erroresFactura = new claseContenedorErroresFactura(-3,tCarrito, factura);
		    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_FACTURA_VISTA, erroresFactura));
					}
					else{
						//Creamos un linea factura
						int idPase = Integer.parseInt(id.getText());
	                    int cantidad = Integer.parseInt(cantidadtxt.getText());
//	                    int idFactura = tCarrito.getFactura().GetId();
	                    TLineaFactura lineaFactura = new TLineaFactura(idPase, cantidad);
	                    boolean modificaciónLineaFactura = false;
	                    
	                    //Comprobamos si la linea Factura ya existe en el carrito en caso de ser asi la modificamos
	                    Set<TLineaFactura> lineaFacturas = tCarrito.getLineasFactura();
	                    for(TLineaFactura linea: lineaFacturas){
	                    	if(linea.GetIdPase() == idPase){
	                    		linea.SetCantidad(linea.GetCantidad()+ cantidad);
	                    		modificaciónLineaFactura = true;
	                    	}
	                    }
	                    //Si no hemos modificado ninguna linea factura entonces la agregamos al carrito directamente
	                    if(!modificaciónLineaFactura)
	                    	//Agregamos la linea Factura a las lineas de factura del carrito
	                        lineaFacturas.add(lineaFactura);
	                    tCarrito.setLineasFactura(lineaFacturas);
	                    ApplicationController.getInstance().manageRequest(new Context(Evento.VCERRAR_FACTURA, tCarrito));
					}
					
				} catch (Exception ex) {// Excepcion para campos Integer
					claseContenedorErroresFactura erroresFactura = new claseContenedorErroresFactura(-4,tCarrito,factura);
	    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_FACTURA_VISTA, erroresFactura));
				}

			}
		});
		panelAniadirButton.add(botonAniadirPase);

		// Panel Quitar Pase
		JLabel msg = ComponentsBuilder.createLabel("Introduzca el ID de el pase que desea eliminar de la factura ",
				1, 150, 80, 20, Color.BLACK);
		msg.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msg);

		JPanel panelQuitar = new JPanel();
		panelQuitar.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelQuitar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelQuitar);

		JLabel idPaseOut = new JLabel("Id Pase");
		idPaseOut.setPreferredSize(new Dimension(100, 30));
		panelQuitar.add(idPaseOut);

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

		JButton botonQuitarPase = new JButton("Quitar");
		botonQuitarPase.setBounds(75, 50, 100, 100);
		botonQuitarPase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VCerrarFactura.this.setVisible(false);
				try {
					
					//Comprobamos que los campos no sean vacios
					if(idOut.getText().isEmpty() || cantidadOuttxt.getText().isEmpty()){
						claseContenedorErroresFactura erroresFactura = new claseContenedorErroresFactura(-3,tCarrito, factura);
		    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_FACTURA_VISTA, erroresFactura));
					}
					else{
						int idPase = Integer.parseInt(idOut.getText());
						int cantidad = Integer.parseInt(cantidadOuttxt.getText());
						boolean correct = true;
						boolean encontrado = false;
						//Comprobamos que el numero de unidades a eliminar no es superior al numero de unidades solicitadas anteriormente
	                    Set<TLineaFactura> lineaFacturas = tCarrito.getLineasFactura();
	                    for(TLineaFactura linea: lineaFacturas){
	                    	if(linea.GetIdPase() == idPase){
	                    		encontrado = true;
								if(cantidad > linea.GetCantidad())
					        		correct = false; 
								else{
									int cantidadTotal = linea.GetCantidad() - cantidad;
									if(cantidadTotal == 0) //Si la cantidad es 0, entonces eliminamos la linea
										lineaFacturas.remove(linea);
									else //Si no es 0, actualizamos la linea
										linea.SetCantidad(cantidadTotal);
								}
							}
								
	                    }
	                    if(!encontrado) //Si el pase no esta en la factura, debemos mostrar un error
	                    {
	                    	claseContenedorErroresFactura erroresFactura = new claseContenedorErroresFactura(-2,tCarrito, factura);
			    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_FACTURA_VISTA, erroresFactura));
	                    }
	                    else if(!correct){
	                    	claseContenedorErroresFactura erroresFactura = new claseContenedorErroresFactura(-1,tCarrito, factura);
			    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_FACTURA_VISTA, erroresFactura));
	                    }
	                    else{ //si no hay error entonces eliminamos la linea
	                        tCarrito.setLineasFactura(lineaFacturas);
	                        ApplicationController.getInstance().manageRequest(new Context(Evento.VCERRAR_FACTURA, tCarrito));
	                    }

					}

				} catch (Exception ex) {// Excepcion para campos Integer
					claseContenedorErroresFactura erroresFactura = new claseContenedorErroresFactura(-4,tCarrito, factura);
	    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES_FACTURA_VISTA, erroresFactura));
				}

			}
		});

		panelQuitarButton.add(botonQuitarPase);

		// Panel para Tabla

		Set<TLineaFactura> LineasFactura = tCarrito.getLineasFactura();
		String[] nombreColumnas = { "Id Pase", "Cantidad" };
		JTable tabla = ComponentsBuilder.createTable(LineasFactura.size(), 2, nombreColumnas);
		int i = 0;
		for (TLineaFactura t : LineasFactura) {
			tabla.setValueAt(t.GetIdPase(), i, 0);
			tabla.setValueAt(t.GetCantidad(), i, 1);
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
				VCerrarFactura.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VFACTURA, null));

			}
		});
		panelBotones.add(botonCancelar);

		JButton botonCerrar = new JButton("Cerrar Factura");
		botonCerrar.setBounds(75, 50, 100, 100);
		botonCerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VCerrarFactura.this.setVisible(false);
				//Devolvemos la clase contenedora para poder enviar el TFactura y TCarrito al command
				claseContenedorErroresFactura correctFactura = new claseContenedorErroresFactura(0,tCarrito, factura);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_FACTURA, correctFactura));
			}
		});
		panelBotones.add(botonCerrar);
		this.setVisible(true);
		this.setResizable(true);
    }

    public void actualizar() {
    }

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.CERRAR_FACTURA_OK) {			
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.CERRAR_FACTURA_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		//ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_ESPECIES)); //Mostramos especies para que se vea que se ha modificado correctamente
		dispose(); //Liberamos recursos de la memoria
		// end-user-code
		
	}

}
