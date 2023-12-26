package Presentacion.VentaJPA;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Factura.TCarrito;
import Negocio.VentaJPA.TCarritoJPA;
import Presentacion.Evento;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import Presentacion.Factura.VFailureDialogFactura;
import Presentacion.Factura.claseContenedorErroresFactura;

public class VFailureDialogVenta extends JFrame implements IGUI{
	// Errores Vista Venta
			private static final String error1 = "La cantidad a eliminar del producto es superior a cantidad solicitada";
			private static final String error2 = "El producto ingresado no existe en la venta";
			private static final String error3 = "Error: No se pueden dejar campos vacios";
			private static final String error4 = "Error: Caracter introducido invalido";

			private int id;

			public VFailureDialogVenta(claseContenedorErroresVenta ContenedorError) {
				super("Mensaje de fracaso");
				Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
				int ancho = 400;
				int alto = 200;
				int x = (pantalla.width - ancho) / 2;
				int y = (pantalla.height - alto) / 2;
				this.setBounds(x, y, ancho, alto);
				this.setVisible(true);
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				this.id = ContenedorError.getIdError();
				TCarritoJPA carrito = ContenedorError.getCarrito();
				initGUI(carrito);
			}

			public void initGUI(TCarritoJPA carrito) {
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				this.setContentPane(panel);

				JPanel aux = new JPanel();
				panel.add(aux);
				JLabel info;
				switch (id) {
				case -1:
					info = new JLabel(error1);
					break;
				case -2:
					info = new JLabel(error2);
					break;
				case -3:
					info = new JLabel(error3);
					break;
				case -4:
					info = new JLabel(error4);
					break;
				
				default:
					info = new JLabel("Error en la operacion");
				}
				aux.add(info);

				panel.add(Box.createRigidArea(new Dimension(0, 20)));

				JPanel botonPanel = new JPanel();
				panel.add(botonPanel);
				JButton confirmar = new JButton("OK");
				confirmar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						VFailureDialogVenta.this.setVisible(false);
			            ApplicationController.getInstance().manageRequest(new Context(Evento.VCERRAR_VENTA, carrito));
					}

				});
				botonPanel.add(confirmar);
			}

			@Override
			public void actualizar() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void actualizar(Context res) {
				if (res.getDatos() != null) {
					claseContenedorErroresVenta ContenedorError = (claseContenedorErroresVenta) res.getDatos();
					TCarritoJPA carrito = ContenedorError.getCarrito();
					this.id = ContenedorError.getIdError();
					initGUI(carrito);
				}		
			}
}
