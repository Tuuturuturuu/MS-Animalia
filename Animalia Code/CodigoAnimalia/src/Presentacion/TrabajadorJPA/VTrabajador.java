package Presentacion.TrabajadorJPA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.TrabajadorJPA.TTrabajador;
import Presentacion.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;


public class VTrabajador extends JFrame implements IGUI{
	
	private JButton bAltaTrabajador;
    private JButton bBajaTrabajador;
    private JButton bModificarTrabajador;
    private JButton bMostrarTrabajador;
    private JButton bListarTrabajadores;
    private JButton bListarTrabajadoresPorDepartamento;
    private JButton bCalcularSueldoTrabajador;
	private JButton backButton;
	private JPanel j;

	private TTrabajador tTrabajador;
	
	public VTrabajador(){
		super("Trabajador");
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
		JLabel label = ComponentsBuilder.createLabel("Trabajador", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
        bAltaTrabajador = ComponentsBuilder.createButton("Alta Trabajador", 100, 100, 185, 100);
        bAltaTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VTrabajador.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VALTA_TRABAJADOR, null));
            }
        });
        this.add(bAltaTrabajador);
		
        // BAJA Trabajador
        bBajaTrabajador = ComponentsBuilder.createButton("Baja Trabajador", 300, 100, 185, 100);
        bBajaTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VTrabajador.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VBAJA_TRABAJADOR, null));
            }
        });
        this.add(bBajaTrabajador);

        // MODIFICAR Trabajador
        bModificarTrabajador = ComponentsBuilder.createButton("Modificar Trabajador", 500, 100, 185, 100);
        bModificarTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VTrabajador.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VMODIFICAR_TRABAJADOR, null));
            }
        });
        this.add(bModificarTrabajador);

        // MOSTRAR Trabajador
        bMostrarTrabajador = ComponentsBuilder.createButton("Mostrar Trabajador", 700, 100, 185, 100);
        bMostrarTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VTrabajador.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VMOSTRAR_TRABAJADOR, null));
            }
        });
        this.add(bMostrarTrabajador);

        // LISTAR Trabajadores
        bListarTrabajadores = ComponentsBuilder.createButton("Listar Trabajadores", 100, 250, 185, 100);
        bListarTrabajadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VTrabajador.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_TRABAJADORES, null));
            }
        });
        this.add(bListarTrabajadores);

        // LISTAR Trabajadores Por Departamento
        bListarTrabajadoresPorDepartamento = ComponentsBuilder.createButton("Listar Trabajadores por Departamento", 300, 250, 285, 100);
        bListarTrabajadoresPorDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VTrabajador.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VLISTAR_TRABAJADORES_POR_DEPARTAMENTO, null));
            }
        });
        this.add(bListarTrabajadoresPorDepartamento);
			
        // CALCULAR SUELDO
        bCalcularSueldoTrabajador = ComponentsBuilder.createButton("Calcular Sueldo Trabajador", 600, 250, 285, 100);
        bCalcularSueldoTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VTrabajador.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VCALCULAR_SUELDO_TRABAJADOR, null));
            }
        });
        this.add(bCalcularSueldoTrabajador);
        
        // BACK BUTTON
        backButton = ComponentsBuilder.createBackButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VTrabajador.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_MAIN_VIEW, null));
                dispose();
            }
        });
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
