package Presentacion.Factura;

import Negocio.Especie.TEspecie;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConPases;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import javax.swing.*;

import Integracion.Factura.TLineaFactura;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

@SuppressWarnings("serial")
public class VMostrarFactura extends JFrame implements IGUI {


    public VMostrarFactura() {
        super("Mostrar Factura");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 430;
        int alto = 330;
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
                .createLabel("Introduzca el ID de la Factura a mostrar ", 1, 10, 80, 20, Color.BLACK);
        msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(msgIntroIDCabecera);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel panelID = new JPanel();
        mainPanel.add(panelID);

        JLabel labelID = ComponentsBuilder.createLabel("ID Factura: ", 10, 100, 80, 20, Color.BLACK);
        panelID.add(labelID);

        JTextField id = new JTextField();
        id.setPreferredSize(new Dimension(250, 30));

        id.setEditable(true);
        panelID.add(id);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel panelBotones = new JPanel();
        mainPanel.add(panelBotones);

        // BOTON ACEPTAR
        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.setBounds(75, 50, 100, 100);
        botonAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VMostrarFactura.this.setVisible(false);
                try {
                	Integer id_Factura = Integer.parseInt(id.getText());
                    ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_FACTURA, 
                    		!id.getText().isEmpty()? id_Factura: 0));

                } catch (Exception ex) {
	    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
                }

            }
        });
        panelBotones.add(botonAceptar);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.setBounds(200, 50, 100, 100);
        botonCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VMostrarFactura.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VFACTURA, null));

            }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
        this.setResizable(true);
    }

    public void actualizar() {
        // Puedes implementar lógica adicional para la actualización de la interfaz si es necesario.
    }

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.MOSTRAR_FACTURA_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_MOSTRAR_FACTURA_COMPLETA, (TFacturaConPases) res.getDatos()));

		
		} else if (res.getEvento() == Evento.MOSTRAR_FACTURA_KO) {
			
			TFactura factura = (TFactura) res.getDatos();
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) factura.GetId()));
		}
		dispose(); //Liberamos recursos de la memoria
		
	}
}
