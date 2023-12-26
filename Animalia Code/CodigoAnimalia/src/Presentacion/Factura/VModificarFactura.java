package Presentacion.Factura;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.Factura.TFactura;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

@SuppressWarnings("serial")
public class VModificarFactura extends JFrame implements IGUI {
    public VModificarFactura() {
        super("Modificar Factura");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 630;
        int alto = 430;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    public void initGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(mainPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // ID
        JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID de la Factura que quiere modificar ",
                1, 10, 80, 20, Color.BLACK);
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

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Fecha de Compra
        JPanel panelFechaCompra = new JPanel();
        mainPanel.add(panelFechaCompra);

        JLabel labelFechaCompra = ComponentsBuilder.createLabel("Fecha de Compra (AAAA-MM-DD): ", 10, 100, 200, 20, Color.BLACK);
        panelFechaCompra.add(labelFechaCompra);

        JTextField fechaCompra = new JTextField();
        fechaCompra.setPreferredSize(new Dimension(250, 30));
        fechaCompra.setEditable(true);
        panelFechaCompra.add(fechaCompra);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panelBotones = new JPanel();
        mainPanel.add(panelBotones);

        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.setBounds(75, 50, 100, 100);
        botonAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VModificarFactura.this.setVisible(false);

                try {
                    TFactura factura = new TFactura();
	            	Date fecha= Date.valueOf(fechaCompra.getText());
                    factura.SetId(Integer.parseInt(id.getText()));
                    factura.SetPrecioTotal(0.0);
                    factura.SetFechaCompra(fecha);
                    ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_FACTURA, factura));
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
                VModificarFactura.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VFACTURA, null));
            }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
        this.setResizable(true);
}

	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(Context res) {
		// TODO Auto-generated method stub
		if (res.getEvento() == Evento.MODIFICAR_FACTURA_OK) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_CORRECTO, (int) res.getDatos()));
		} else if (res.getEvento() == Evento.MODIFICAR_FACTURA_KO) {
			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, (int) res.getDatos()));
		}
		dispose(); //Liberamos recursos de la memoria
	}
}