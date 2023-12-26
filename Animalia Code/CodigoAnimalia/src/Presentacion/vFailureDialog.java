package Presentacion;

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

import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.IGUI;
import Presentacion.Evento;

@SuppressWarnings("serial")
public class vFailureDialog  extends JFrame implements IGUI {
	// Errores Genericos
	private static final String error2 = "Error: Id perteneciente a una entidad inactiva";
	private static final String error3 = "Error: No se pueden dejar campos vacios";
	private static final String error4 = "Error: Caracter introducido invalido";
	private static final String error5 = "Error: ID no encontrado en la BD";

	// Errores Especie
	private static final String error10 = "Error: Ya existe una especie con el mismo nombre y activa";
	private static final String error11 = "Error: El id de la especie no existe";
	private static final String error12 = "Error: El id es el de una especie inactiva";
	private static final String error13 = "Error: No puedes dar de baja una especie con animales activos";
	private static final String error14 = "Error: ya existe una especie con el mismo nombre y inactivo";

	//Errores Habitat
	private static final String error20 = "Error: El ID del habitat no existe";
	private static final String error21 = "Error: El habitat no esta activo";
	private static final String error22 = "Error: Ya existe el habitat con el mismo nombre e inactivo";
	private static final String error23 = "Error: Ya existe el habitat con el mismo nombre e activo";
	private static final String error24 = "Error: No puedes dar de baja un habitat con pases o especies activos";
	private static final String error25 = "Error: Ya existe una vinculaci�n entre el empleado y el habitat";
	private static final String error26 = "Error: El empleado y el habitat ya estan desvinculados";
	
	// Errores Animal
	private static final String error30 = "Error: Ya existe un animal con el mismo nombre inactivo de otro tipo";
	private static final String error31 = "Error: Existe un animal con el mismo nombre y est� activo";
	private static final String error32 = "Error: El animal est� inactivo";
	private static final String error33 = "Error: El ID animal no existe";
	private static final String error34 = "Error: ya existe un Animal con el mismo nombre y inactivo";
	
	//Errores empleado
	private static final String error40 = "Error: ya existe un empleado con el mismo DNI y activo";
	private static final String error41 = "Error: No existe el empleado";
	private static final String error42 = "Error: Id es de un empleado inactivo";
	private static final String error43 = "Error: ya existe un empleado con el mismo DNI y inactivo";
	private static final String error44 = "Error: DNI formato incorrecto";
	private static final String error45 = "Error: Telefono formato incorrecto";
	private static final String error46 = "Error: Ya existe un empleado con el mismo DNI inactivo de otro tipo";
	
	//Errores Pase
	private static final String error50 = "Error: Ya existe un pase con las mismas fecha, hora y id habitat, y esta activo";
	private static final String error51 = "Error: El id del pase no existe";
	private static final String error52 = "Error: El id es el un pase inactivo";
	private static final String error53 = "Error: ya existe un pase con las mismas fecha, hora y id habitat, y esta inactivo";

	//Errores factura
	private static final String error60 = "No hay cantidad disponible del pase";
	private static final String error61 = "Este pase no esta activo";
	private static final String error62 = "Pase no existe";
	private static final String error63 = "Error al crear LineaFactura";
	private static final String error64 = "Error al crear factura";
	private static final String error65 = "El carrito esta vacio";
	private static final String error66 = "Factura no activa";
	private static final String error67 = "Error al modificar Factura";
	private static final String error68 = "Error al modificar pase";
	private static final String error69 = "Error al eliminarLineaFactura";
	private static final String error70 = "Error al modificar LineaFactura";
	private static final String error71 = "LineaFactura no existe";
	private static final String error72 = "Factura no existe";
	private static final String error73 = "La cantidad a eliminar del pase es superior a cantidad solicitada";
	private static final String error74 = "El pase ingresado no existe en la factura";
	private static final String error75 = "La cantidad a devolver es mayor a la cantidad original de la factura";
	
	
	//----------------------------- ERRORES JPA (Empezamos a partir del 100 y cada entidad le corresponden 10 errores -----------------------------------------------
	//Errores Proveedor 76-86
	private static final String error76 = "CIF ya existe en la base de datos y su proveedor ya esta activo";
	private static final String error77 = "Proveedor ya esta dado de baja o no existe en la base de datos";
	private static final String error78 = " El Proveedor esta dado de baja o el CIF introducido ya existe en la BD ";
	
	//Errores Venta 100-110
	private static final String error100 = " El trabajador para la venta esta inactivo o inexistente";
	private static final String error101 = " No hay stock suficiente para un producto de la venta";
	private static final String error102 = " Un producto de la venta no esta activo";
	private static final String error103 = " Un producto de la venta no existe";
	private static final String error104 = " No puedes cerrar una venta con un carrito vac�o";
	private static final String error105 = " No puedes cerrar una venta con un trabajador inactivo";
	private static final String error106 = " No existe una venta con el id especificado";
	private static final String error107 = " No existe la linea de venta a devolver";
	private static final String error108 = " No existe el producto a devolver";
	private static final String error109 = " La venta se encuentra inactiva";
	
	
	//Errores Departamento 110-120
	private static final String error110 = " Error alta: Departamento que ya existe";
	private static final String error111 = " Error al calcular la n�mina del departamento";
	private static final String error112 = " Departamento no existe, est� inactivo o tiene trabajadores activos";
	private static final String error113 = " Departamento ya tiene el mismo nombre";
	private static final String error114 = " Error modificar: Departamento no existe o no est� activo";
	private static final String error115 = " Departamento no existe";
	private static final String error116 = " Departamento esta inactivo";

	
	//Errores Trabajador 120 -130
	private static final String error120 = " Ya existe un trabajador activo con el mismo dni";
	private static final String error121 = " No existe el trabajador";
	private static final String error122 = " Trabajador no existe o esta inactivo";
	private static final String error123 = " Ya existe un trabajador con el mismo dni";


	//Errores Producto 130-140
	private static final String error130 = " El ID del producto no existe";
	private static final String error131 = " El producto no esta activo";
	private static final String error132 = " No puedes dar de baja un producto con ventas activas";
	private static final String error133 = " Error al dar de Alta un Producto";
	private static final String error134 = " El producto o no existe o no esta activo";
	private static final String error135 = " ";
	private static final String error136 = " ";
	private static final String error137 = " ";
	private static final String error138 = " ";
	private static final String error139 = " ";
	
	//Errores Marca de Producto 140 -150
	private static final String error140 = " El ID de la marca de producto no existe";
	private static final String error141 = " La Marca de producto no esta activa";
	private static final String error142 = " Marca no existe, est� inactiva o tiene proveedores activos";
	private static final String error143 = " Ya existe una marca activa con mismo nombre";
	private static final String error144 = " Marca no existe o esta inactiva";
	private static final String error145 = " Ya existe una marca con mismo nombre";
	


	


	private int id;

	public vFailureDialog(int idError) {
		super("Mensaje de fracaso");
		this.id = idError;
		initGUI();
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 400;
		int alto = 200;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initGUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.setContentPane(panel);

		JPanel aux = new JPanel();
		panel.add(aux);
		JLabel info;
		switch (id) {
		case -2:
			info = new JLabel(error2);
			break;
		case -3:
			info = new JLabel(error3);
			break;
		case -4:
			info = new JLabel(error4);
			break;
		case -5:
			info = new JLabel(error5);
			break;
		case -10:
			info = new JLabel(error10);
			break;
		case -11:
			info = new JLabel(error11);
			break;
		case -12:
			info = new JLabel(error12);
			break;
		case -13:
			info = new JLabel(error13);
			break;
		case -14:
			info = new JLabel(error14);
			break;
		case -20:
			info = new JLabel(error20);
			break;
		case -21:
			info = new JLabel(error21);
			break;
		case -22:
			info = new JLabel(error22);
			break;
		case -23:
			info = new JLabel(error23);
			break;
		case -24:
			info = new JLabel(error24);
			break;
		case -25:
			info = new JLabel(error25);
			break;
		case -26:
			info = new JLabel(error26);
			break;
		case -30:
			info = new JLabel(error30);
			break;
		case -31:
			info = new JLabel(error31);
			break;
		case -32:
			info = new JLabel(error32);
			break;
		case -33:
			info = new JLabel(error33);
			break;
		case -34:
			info = new JLabel(error34);
			break;
		case -40:
			info = new JLabel(error40);
			break;
		case -41:
			info = new JLabel(error41);
			break;
		case -42:
			info = new JLabel(error42);
			break;
		case -43:
			info = new JLabel(error43);
			break;
		case -44:
			info = new JLabel(error44);
			break;
		case -45:
			info = new JLabel(error45);
			break;
		case -46:
			info = new JLabel(error46);
			break;
		case -50:
			info = new JLabel(error50);
			break;
		case -51:
			info = new JLabel(error51);
			break;
		case -52:
			info = new JLabel(error52);
			break;
		case -53:
			info = new JLabel(error53);
			break;
		case -60:
			info = new JLabel(error60);
			break;
		case -61:
			info = new JLabel(error61);
			break;
		case -62:
			info = new JLabel(error62);
			break;
		case -63:
			info = new JLabel(error63);
			break;
		case -64:
			info = new JLabel(error64);
			break;
		case -65:
			info = new JLabel(error65);
			break;
		case -66:
			info = new JLabel(error66);
			break;
		case -67:
			info = new JLabel(error67);
			break;
		case -68:
			info = new JLabel(error68);
			break;
		case -69:
			info = new JLabel(error69);
			break;
		case -70:
			info = new JLabel(error70);
			break;
		case -71:
			info = new JLabel(error71);
			break;
		case -72:
			info = new JLabel(error72);
			break;
		case -73:
			info = new JLabel(error73);
			break;
		case -74:
			info = new JLabel(error74);
			break;
		case -75:
			info = new JLabel(error75);
			break;
		case -76:
			info = new JLabel(error76);
			break;
		case -77:
			info = new JLabel(error77);
			break;
		case -78:
			info = new JLabel(error78);
			break;
		case -100:
			info = new JLabel(error100);
			break;
		case -101:
			info = new JLabel(error101);
			break;
		case -102:
			info = new JLabel(error102);
			break;
		case -103:
			info = new JLabel(error103);
			break;
		case -104:
			info = new JLabel(error104);
			break;
		case -105:
			info = new JLabel(error105);
			break;
		case -106:
			info = new JLabel(error106);
			break;
		case -107:
			info = new JLabel(error107);
			break;
		case -108:
			info = new JLabel(error108);
			break;
		case -109:
			info = new JLabel(error109);
			break;
		case -110:
	        info = new JLabel(error110);
	        break;
	    case -111:
	        info = new JLabel(error111);
	        break;
	    case -112:
	        info = new JLabel(error112);
	        break;
	    case -113:
	        info = new JLabel(error113);
	        break;
	    case -114:
	        info = new JLabel(error114);
	        break;
	    case -115:
	        info = new JLabel(error115);
	        break;
	        
	    case -116:
	        info = new JLabel(error116);
	        break;
	    case -120:
	        info = new JLabel(error120);
	        break;
	    case -121:
	        info = new JLabel(error121);
	        break;
	    case -122:
	        info = new JLabel(error122);
	        break;
	    case -123:
	        info = new JLabel(error123);
	        break;   
	        
	    case -140:
	        info = new JLabel(error140);
	        break;
	    case -141:
	        info = new JLabel(error141);
	        break;
	    case -142:
	        info = new JLabel(error142);
	        break;
	    case -143:
	        info = new JLabel(error143);
	        break;
	    case -144:
	        info = new JLabel(error144);
	        break;
	    case -145:
	        info = new JLabel(error145);
	        break;
		case -133:
			info = new JLabel(error133);
			break;
		case -134:
			info = new JLabel(error134);
			break;
		case -130:
			info = new JLabel(error130);
			break;
		case -131:
			info = new JLabel(error131);
			break;
		case -132:
			info = new JLabel(error132);
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
				vFailureDialog.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.CREAR_VTIENDA, null));
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
			Integer respuesta = (Integer) res.getDatos();
			this.id = respuesta;
			initGUI();
		}		
	}


}
