package Presentacion;

public class Evento {
    //VISTA GENERAL
    public static final int CREAR_MAIN_VIEW = 1 ;
    
    //VISTAS GENERALES DE CADA ENTIDAD
    public static final int CREAR_VANIMAL = 2;
    public static final int CREAR_VEMPLEADO = 3;
    public static final int CREAR_VESPECIE = 4;
    public static final int CREAR_VFACTURA = 5;
    public static final int CREAR_VHABITAT = 6;
    public static final int CREAR_VPASE = 7;
   
	
	//VISTAS GENERALES DE CADA ENTIDAD JPA
    public static final int CREAR_VTIENDA = 2000;
    public static final int CREAR_VPROVEEDOR = 2001;
    public static final int CREAR_VMARCA = 2002;
    public static final int CREAR_VPRODUCTO = 2003;
    public static final int CREAR_VTRABAJADOR = 2004;
    public static final int CREAR_VDEPARTAMENTO = 2005;
    public static final int CREAR_VVENTA = 2006;


    // Empleado
    public static final int ALTA_EMPLEADO = 8;
    public static final int ALTA_EMPLEADO_OK = 9;
    public static final int ALTA_EMPLEADO_KO = 10;
    public static final int BAJA_EMPLEADO = 11;
    public static final int BAJA_EMPLEADO_OK = 12;
    public static final int BAJA_EMPLEADO_KO = 13;
    public static final int MODIFICAR_EMPLEADO = 14;
    public static final int MODIFICAR_EMPLEADO_OK = 15;
    public static final int MODIFICAR_EMPLEADO_KO = 16;
    public static final int MOSTRAR_EMPLEADO = 17;
    public static final int MOSTRAR_EMPLEADO_OK = 18;
    public static final int MOSTRAR_EMPLEADO_KO = 19;
    public static final int LISTAR_EMPLEADO = 20;
    public static final int LISTAR_EMPLEADO_OK = 21;
    public static final int LISTAR_EMPLEADO_KO = 22;
    public static final int LISTAR_EMPLEADOS_POR_HABITAT = 23;
    public static final int LISTAR_EMPLEADOS_POR_HABITAT_OK = 24;
    public static final int LISTAR_EMPLEADOS_POR_HABITAT_KO = 25;
    public static final int LISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT = 26;
    public static final int LISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT_OK = 27;
    public static final int LISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT_KO = 28;

    // Factura
    public static final int ABRIR_FACTURA = 29;
    public static final int ABRIR_FACTURA_OK = 30;
    public static final int ABRIR_FACTURA_KO = 31;
    public static final int CERRAR_FACTURA = 32;
    public static final int CERRAR_FACTURA_OK = 33;
    public static final int CERRAR_FACTURA_KO = 34;
    public static final int MOSTRAR_FACTURA = 35;
    public static final int MOSTRAR_FACTURA_OK = 36;
    public static final int MOSTRAR_FACTURA_KO = 37;
    public static final int LISTAR_FACTURAS = 38;
    public static final int LISTAR_FACTURAS_OK = 39;
    public static final int LISTAR_FACTURAS_KO = 40;
    public static final int DEVOLVER_FACTURA = 41;
    public static final int DEVOLVER_FACTURA_OK = 42;
    public static final int DEVOLVER_FACTURA_KO = 43;
    public static final int ANADIR_PASE_A_FACTURA = 44;
    public static final int ANADIR_PASE_A_FACTURA_OK = 45;
    public static final int ANADIR_PASE_A_FACTURA_KO = 46;
    public static final int QUITAR_PASE_DE_FACTURA = 47;
    public static final int QUITAR_PASE_DE_FACTURA_OK = 48;
    public static final int QUITAR_PASE_DE_FACTURA_KO = 49;
    public static final int MODIFICAR_FACTURA = 50;
    public static final int MODIFICAR_FACTURA_OK = 51;
    public static final int MODIFICAR_FACTURA_KO = 52;

    // Pase
    public static final int ALTA_PASE = 53;
    public static final int ALTA_PASE_OK = 54;
    public static final int ALTA_PASE_KO = 55;
    public static final int BAJA_PASE = 56;
    public static final int BAJA_PASE_OK = 57;
    public static final int BAJA_PASE_KO = 58;
    public static final int MOSTRAR_PASE = 59;
    public static final int MOSTRAR_PASE_OK = 60;
    public static final int MOSTRAR_PASE_KO = 61;
    public static final int LISTAR_PASES = 62;
    public static final int LISTAR_PASES_OK = 63;
    public static final int LISTAR_PASES_KO = 64;
    public static final int MODIFICAR_PASE = 65;
    public static final int MODIFICAR_PASE_OK = 66;
    public static final int MODIFICAR_PASE_KO = 67;
    public static final int LISTAR_PASES_POR_HABITAT = 68;
    public static final int LISTAR_PASES_POR_HABITAT_OK = 69;
    public static final int LISTAR_PASES_POR_HABITAT_KO = 70;

    // Habitat
    public static final int ALTA_HABITAT = 71;
    public static final int ALTA_HABITAT_OK = 72;
    public static final int ALTA_HABITAT_KO = 73;
    public static final int BAJA_HABITAT = 74;
    public static final int BAJA_HABITAT_OK = 75;
    public static final int BAJA_HABITAT_KO = 76;
    public static final int MOSTRAR_HABITAT = 77;
    public static final int MOSTRAR_HABITAT_OK = 78;
    public static final int MOSTRAR_HABITAT_KO = 79;
    public static final int MODIFICAR_HABITAT = 80;
    public static final int MODIFICAR_HABITAT_OK = 81;
    public static final int MODIFICAR_HABITAT_KO = 82;
    public static final int LISTAR_HABITAT = 83;
    public static final int LISTAR_HABITAT_OK = 84;
    public static final int LISTAR_HABITAT_KO = 85;
    public static final int VINCULAR_EMPLEADO_A_HABITAT = 86;
    public static final int VINCULAR_EMPLEADO_A_HABITAT_OK = 87;
    public static final int VINCULAR_EMPLEADO_A_HABITAT_KO = 88;
    public static final int DESVINCULAR_EMPLEADO_DE_HABITAT = 89;
    public static final int DESVINCULAR_EMPLEADO_DE_HABITAT_OK = 90;
    public static final int DESVINCULAR_EMPLEADO_DE_HABITAT_KO = 91;
    public static final int LISTAR_HABITATS_POR_EMPLEADO = 92;
    public static final int LISTAR_HABITATS_POR_EMPLEADO_OK = 93;
    public static final int LISTAR_HABITATS_POR_EMPLEADO_KO = 94;
    public static final int CALCULAR_HABITAT_CON_MAS_INGRESOS = 95;
    public static final int CALCULAR_HABITAT_CON_MAS_INGRESOS_OK = 96;
    public static final int CALCULAR_HABITAT_CON_MAS_INGRESOS_KO = 97;

    // Especie
    public static final int ALTA_ESPECIE = 98;
    public static final int ALTA_ESPECIE_OK = 99;
    public static final int ALTA_ESPECIE_KO = 100;
    public static final int BAJA_ESPECIE = 101;
    public static final int BAJA_ESPECIE_OK = 102;
    public static final int BAJA_ESPECIE_KO = 103;
    public static final int MOSTRAR_ESPECIE = 104;
    public static final int MOSTRAR_ESPECIE_OK = 105;
    public static final int MOSTRAR_ESPECIE_KO = 106;
    public static final int MODIFICAR_ESPECIE = 107;
    public static final int MODIFICAR_ESPECIE_OK = 108;
    public static final int MODIFICAR_ESPECIE_KO = 109;
    public static final int LISTAR_ESPECIES = 110;
    public static final int LISTAR_ESPECIES_OK = 111;
    public static final int LISTAR_ESPECIES_KO = 112;
    public static final int LISTAR_ESPECIE_POR_HABITAT = 113;
    public static final int LISTAR_ESPECIE_POR_HABITAT_OK = 114;
    public static final int LISTAR_ESPECIE_POR_HABITAT_KO = 115;

    // Animal
    public static final int ALTA_ANIMAL = 116;
    public static final int ALTA_ANIMAL_OK = 117;
    public static final int ALTA_ANIMAL_KO = 118;
    public static final int BAJA_ANIMAL = 119;
    public static final int BAJA_ANIMAL_OK = 120;
    public static final int BAJA_ANIMAL_KO = 121;
    public static final int MODIFICAR_ANIMAL = 122;
    public static final int MODIFICAR_ANIMAL_OK = 123;
    public static final int MODIFICAR_ANIMAL_KO = 124;
    public static final int MOSTRAR_ANIMAL = 125;
    public static final int MOSTRAR_ANIMAL_OK = 126;
    public static final int MOSTRAR_ANIMAL_KO = 127;
    public static final int LISTAR_ANIMALES = 128;
    public static final int LISTAR_ANIMALES_OK = 129;
    public static final int LISTAR_ANIMALES_KO = 130;
    public static final int LISTAR_ANIMALES_POR_ESPECIE = 131;
    public static final int LISTAR_ANIMALES_POR_ESPECIE_OK = 132;
    public static final int LISTAR_ANIMALES_POR_ESPECIE_KO = 133;
    public static final int LISTAR_ANIMALES_NO_ACUATICOS = 134;
    public static final int LISTAR_ANIMALES_NO_ACUATICOS_OK = 135;
    public static final int LISTAR_ANIMALES_NO_ACUATICOS_KO = 136;
    public static final int LISTAR_ANIMALES_ACUATICOS = 137;
    public static final int LISTAR_ANIMALES_ACUATICOS_OK = 138;
    public static final int LISTAR_ANIMALES_ACUATICOS_KO = 139;
    public static final int ALTA_ANIMAL_ACUATICO = 140;
    public static final int ALTA_ANIMAL_ACUATICO_OK = 141;
    public static final int ALTA_ANIMAL_ACUATICO_KO = 142;
    public static final int ALTA_ANIMAL_NO_ACUATICO = 143;
    public static final int ALTA_ANIMAL_NO_ACUATICO_OK = 144;
    public static final int ALTA_ANIMAL_NO_ACUATICO_KO = 145;
	public static final int MODIFICAR_ANIMAL_ACUATICO_OK = 146;
	public static final int MODIFICAR_ANIMAL_ACUATICO_KO = 147;
	public static final int MODIFICAR_ANIMAL_ACUATICO = 148;
	public static final int MODIFICAR_ANIMAL_NO_ACUATICO_OK = 149;
	public static final int MODIFICAR_ANIMAL_NO_ACUATICO_KO = 150;
	public static final int MODIFICAR_ANIMAL_NO_ACUATICO = 151;

    //VISTAS CASOS DE USO ANIMALES
    public static final int VALTA_ANIMAL = 200;
    public static final int VALTA_ANIMAL_ACUATICO = 201;
    public static final int VALTA_ANIMAL_NO_ACUATICO = 202;
    public static final int VBAJA_ANIMAL = 203;
    public static final int VMODIFICAR_ANIMAL = 204;
    public static final int VMOSTRAR_ANIMAL = 205;
    public static final int VLISTAR_ANIMALES = 206;
    public static final int VLISTAR_ANIMALES_NO_ACUATICOS = 207;
    public static final int VLISTAR_ANIMALES_ACUATICOS = 208;
    public static final int VLISTAR_ANIMALES_POR_ESPECIE = 209;
    public static final int VMODIFICAR_ANIMAL_SELECTOR = 210;
	public static final int VMODIFICAR_ANIMAL_NO_ACUATICO = 211;
	public static final int VMODIFICAR_ANIMAL_ACUATICO = 212;

    //VISTAS CASOS DE USO ESPECIE
    public static final int VALTA_ESPECIE = 500;;
    public static final int VBAJA_ESPECIE = 501;
    public static final int VMOSTRAR_ESPECIE = 502;
    public static final int VMODIFICAR_ESPECIE = 503;
    public static final int VLISTAR_ESPECIE_POR_HABITAT = 504;
    
    //VISTAS CASOS DE USO EMPELADOS (Y OTROS)
    public static final int ALTA_EMPLEADO_LIMPIEZA = 600;
    public static final int ALTA_EMPLEADO_LIMPIEZA_OK = 601;
    public static final int ALTA_EMPLEADO_LIMPIEZA_KO = 602;
    public static final int ALTA_EMPLEADO_ZOOLOGICO = 603;
    public static final int ALTA_EMPLEADO_ZOOLOGICO_OK = 604;
    public static final int ALTA_EMPLEADO_ZOOLOGICO_KO = 605;
    public static final int LISTAR_EMPLEADOS_LIMPIEZA = 606;
    public static final int LISTAR_EMPLEADOS_LIMPIEZA_OK = 607;
    public static final int LISTAR_EMPLEADOS_LIMPIEZA_KO = 608;
    public static final int LISTAR_EMPLEADOS_ZOOLOGICO = 609;
    public static final int LISTAR_EMPLEADOS_ZOOLOGICO_OK = 610;
    public static final int LISTAR_EMPLEADOS_ZOOLOGICO_KO = 611;
    
    public static final int VALTA_EMPLEADO = 612;
    public static final int VBAJA_EMPLEADO = 613;
    public static final int VMOSTRAR_EMPLEADO = 614;
    public static final int VMODIFICAR_EMPLEADO = 615;
    public static final int VLISTAR_EMPLEADO = 616;
    public static final int VLISTAR_EMPLEADO_POR_HABITAT = 617;
    public static final int VLISTAR_EMPLEADO_POR_ESPECIE_EN_HABITAT = 618;
    
    public static final int VLISTAR_EMPLEADOS_LIMPIEZA = 619;
    public static final int VLISTAR_EMPLEADOS_ZOOLOGICO = 620;
    public static final int VALTA_EMPLEADO_LIMPIEZA = 621;
    public static final int VALTA_EMPLEADO_ZOOLOGICO = 622;
    
    //VISTAS CASOS DE USO HABITAT
    public static final int VALTA_HABITAT = 700;
    public static final int VBAJA_HABITAT = 701;
    public static final int VMOSTRAR_HABITAT = 702;
    public static final int VMODIFICAR_HABITAT = 703;
    public static final int VLISTAR_HABITAT = 704;
    public static final int VLISTAR_HABITATS_POR_EMPLEADO = 705;
    public static final int VVINCULAR_EMPLEADO_A_HABITAT = 706;
    public static final int VDESVINCULAR_EMPLEADO_DE_HABITAT = 707;
    public static final int VCALCULAR_HABITAT_CON_MAS_INGRESOS = 708;
      
    //VISTAS CASOS DE USO PASE
    public static final int VALTA_PASE = 505;
    public static final int VBAJA_PASE = 506;
    public static final int VMOSTRAR_PASE = 507;
    public static final int VMODIFICAR_PASE = 508;
    public static final int VLISTAR_PASE = 509;
    public static final int VLISTAR_PASE_POR_HABITAT = 510;
    
    //VISTAS CASOS DE USO FACTURA
    public static final int VCERRAR_FACTURA= 720;
    public static final int VMOSTRAR_FACTURA= 721;
    public static final int VDEVOLVER_FACTURA= 722;
    public static final int VMODIFICAR_FACTURA = 723;
    
    //JPA
    
    //PROVEEDOR
    public static final int VALTA_PROVEEDOR = 800;
    public static final int VBAJA_PROVEEDOR = 801;
    public static final int VMODIFICAR_PROVEEDOR = 802;
    public static final int VMOSTRAR_PROVEEDOR = 803;
    public static final int VLISTAR_PROVEEDORES = 804;
    public static final int VVINCULAR_PROVEEDOR_MARCA = 805;
    public static final int VDESVINCULAR_PROVEEDOR_MARCA = 806;
    public static final int VLISTAR_PROVEEDORES_POR_MARCA = 807;
    public static final int V_LISTAR_PROVEEDOR_POR_ID_MARCA = 811;
    public static final int ALTA_PROVEEDOR_OK = 808;
	public static final int ALTA_PROVEEDOR_KO = 809;
	public static final int ALTA_PROVEEDOR = 810;
	public static final int BAJA_PROVEEDOR_OK = 812;
	public static final int BAJA_PROVEEDOR_KO = 813;
	public static final int BAJA_PROVEEDOR = 814;
	public static final int MODIFICAR_PROVEEDOR_OK = 815;
	public static final int MODIFICAR_PROVEEDOR_KO = 816;
	public static final int MODIFICAR_PROVEEDOR = 817;
	public static final int MOSTRAR_PROVEEDOR_OK = 818;
	public static final int MOSTRAR_PROVEEDOR_KO = 819;
	public static final int MOSTRAR_PROVEEDOR = 820;
	public static final int LISTAR_PROVEEDOR_POR_MARCA_OK = 821;
	public static final int LISTAR_PROVEEDOR_POR_MARCA_KO = 822;
	public static final int LISTAR_PROVEEDOR_POR_MARCA = 823;
	public static final int VINCULAR_PROVEEDOR_A_MARCA_OK = 824;
	public static final int VINCULAR_PROVEEDOR_A_MARCA_KO = 825;
	public static final int VINCULAR_PROVEEDOR_A_MARCA = 826;
	public static final int DESVINCULAR_PROVEEDOR_DE_MARCA_OK = 827;
	public static final int DESVINCULAR_PROVEEDOR_DE_MARCA_KO = 828;
	public static final int DESVINCULAR_PROVEEDOR_DE_MARCA = 829;
	public static final int LISTAR_PROVEEDORES = 830;


	
	//PRODUCTO 
	public static final int VALTA_PRODUCTO = 900;
	public static final int VBAJA_PRODUCTO = 901;
	public static final int VMODIFICAR_PRODUCTO = 902;
	public static final int VMOSTRAR_PRODUCTO = 903;
	public static final int VLISTAR_PRODUCTO = 904;
	public static final int VLISTAR_PRODUCTOS_POR_MARCA = 905;
	public static final int ALTA_PRODUCTO_OK = 906;
	public static final int ALTA_PRODUCTO_KO = 907;
	public static final int ALTA_PRODUCTO = 908;
	public static final int BAJA_PRODUCTO_OK = 909;
	public static final int BAJA_PRODUCTO_KO = 910;
	public static final int BAJA_PRODUCTO = 911;
	public static final int MODIFICAR_PRODUCTO_OK = 912;
	public static final int MODIFICAR_PRODUCTO_KO = 913;
	public static final int MODIFICAR_PRODUCTO = 914;
	public static final int MOSTRAR_PRODUCTO= 915;
	public static final int LISTAR_PRODUCTOS=916;
	public static final int LISTAR_PRODUCTOS_POR_MARCA=917;
	public static final int LISTAR_PRODUCTOS_POR_MARCA_OK=918;
	public static final int LISTAR_PRODUCTOS_POR_MARCA_KO=919;
	public static final int MOSTRAR_PRODUCTO_KO= 920;
	public static final int MOSTRAR_PRODUCTO_OK= 921;
    public static final int VLISTAR_PRODUCTO_POR_ID_MARCA = 922;
	
	//Departamento
	public static final int ALTA_Departamento_OK = 1100;
	public static final int ALTA_Departamento_KO = 1101;
	public static final int ALTA_DEPARTAMENTO = 1102;
	public static final int VALTA_DEPARTAMENTO = 1115;
	public static final int BAJA_DEPARTAMENTO_OK = 1103;
	public static final int BAJA_DEPARTAMENTO_KO = 1104;
	public static final int BAJA_DEPARTAMENTO = 1105;
	public static final int VBAJA_DEPARTAMENTO = 1116;
	public static final int MOSTRAR_DEPARTAMENTO_OK = 1106;
	public static final int MOSTRAR_DEPARTAMENTO_KO = 1107;
	public static final int MOSTRAR_DEPARTAMENTO = 1108;
	public static final int VMOSTRAR_DEPARTAMENTO = 1117;
	public static final int MODIFICAR_DEPARTAMENTO_OK = 1109;
	public static final int MODIFICAR_DEPARTAMENTO_KO = 1110;
	public static final int MODIFICAR_DEPARTAMENTO = 1111;
	public static final int VMODIFICAR_DEPARTAMENTO = 1118;
	public static final int CALCULAR_NOMINA_DEPARTAMENTO_OK = 1112;
	public static final int CALCULAR_NOMINA_DEPARTAMENTO_KO = 1113;
	public static final int CALCULAR_NOMINA_DEPARTAMENTO = 1114;
	public static final int VCALCULAR_NOMINA_DEPARTAMENTO = 1119;
	public static final int VLISTAR_DEPARTAMENTO = 1120;
	public static final int LISTAR_DEPARTAMENTO_OK = 1121;
	public static final int LISTAR_DEPARTAMENTO_KO = 1122;
	public static final int LISTAR_DEPARTAMENTO = 1123;
	
	
    //ERRORES
    public static final int V_ERRORES= 1000;
	public static final int V_CORRECTO = 1001;
	public static final int V_MOSTRAR_UNO = 1002;
	
	//LISTAR ENTIDADES
	public static final int V_LISTAR_ESPECIE_POR_ID_HABITAT = 1003;
	public static final int V_LISTAR_PASE_POR_ID_HABITAT = 1004;
	public static final int V_LISTAR_ANIMAL_POR_ID_ESPECIE = 1005;
	public static final int V_LISTAR_EMPLEADO_POR_ID = 1006;
	public static final int V_LISTAR_HABITAT_POR_ID_EMPLEADO = 1007;
	
	public static final int V_ERRORES_FACTURA_VISTA= 1010;
	public static final int V_MOSTRAR_FACTURA_COMPLETA= 1011;
	public static final int V_DEVOLVER_FACTURA_CORRECT= 1012;
	public static final int V_ERRORES_VENTA_VISTA= 1013;
	public static final int V_DEVOLVER_VENTA_CORRECT= 1014;

	
	 // Venta
    public static final int ABRIR_VENTA = 1200;
    public static final int ABRIR_VENTA_OK = 1201;
    public static final int ABRIR_VENTA_KO = 1202;
    public static final int CERRAR_VENTA = 1203;
    public static final int CERRAR_VENTA_OK = 1204;
    public static final int CERRAR_VENTA_KO = 1205;
    public static final int MOSTRAR_VENTA = 1206;
    public static final int MOSTRAR_VENTA_COMPLETA = 1450;
    public static final int MOSTRAR_VENTA_OK = 1207;
    public static final int MOSTRAR_VENTA_KO = 1208;
    public static final int LISTAR_VENTAS = 1209;
    public static final int LISTAR_VENTA_OK = 1210;
    public static final int LISTAR_VENTA_KO = 1211;
    public static final int DEVOLVER_VENTA = 1212;
    public static final int DEVOLVER_VENTA_OK = 1213;
    public static final int DEVOLVER_VENTA_KO = 1214;
    public static final int ANADIR_PRODUCTO_A_VENTA = 1215;
    public static final int ANADIR_PRODUCTO_A_VENTA_OK = 1216;
    public static final int ANADIR_PRODUCTO_A_VENTA_KO = 1217;
    public static final int QUITAR_PRODUCTO_DE_VENTA = 1218;
    public static final int QUITAR_PRODUCTO_DE_VENTA_OK = 1219;
    public static final int QUITAR_PRODUCTO_DE_VENTA_KO = 1220;
    public static final int MODIFICAR_VENTA = 1221;
    public static final int MODIFICAR_VENTA_OK = 1222;
    public static final int MODIFICAR_VENTA_KO = 1223;
    public static final int V_LISTAR_VENTA_POR_IDTRABAJADOR = 1234;
    public static final int LISTAR_VENTAS_POR_TRABAJADOR = 1224;
    public static final int V_LISTAR_VENTAS_POR_TRABAJADOR = 1235;
    public static final int LISTAR_VENTA_POR_TRABAJADOR_OK = 1225;
    public static final int LISTAR_VENTA_POR_TRABAJADOR_KO = 1226;
    public static final int CERRAR_VENTA_IDTRABAJADOR= 1227;
    public static final int CERRAR_VENTA_IDTRABAJADOR_KO = 1228;
    
  //VISTAS CASOS DE USO VENTA
    public static final int VCERRAR_VENTA= 1229;
    public static final int VMOSTRAR_VENTA= 1230;
    public static final int VDEVOLVER_VENTA= 1231;
    public static final int VMODIFICAR_VENTA = 1232;
    public static final int VCERRAR_VENTA_IDTRABAJADOR= 1233;
    
    
 // TRABAJADOR
    public static final int VALTA_TRABAJADOR = 1300;
    public static final int ALTA_TRABAJADOR = 1301;
    public static final int ALTA_TRABAJADOR_OK = 1302;
    public static final int ALTA_TRABAJADOR_KO = 1303;
    public static final int VBAJA_TRABAJADOR = 1304;
    public static final int BAJA_TRABAJADOR = 1305;
    public static final int BAJA_TRABAJADOR_OK = 1306;
    public static final int BAJA_TRABAJADOR_KO = 1307;
    public static final int VMODIFICAR_TRABAJADOR = 1308;
    public static final int MODIFICAR_TRABAJADOR = 1309;
    public static final int MODIFICAR_TRABAJADOR_OK = 1310;
    public static final int MODIFICAR_TRABAJADOR_KO = 1311;
    public static final int VMOSTRAR_TRABAJADOR = 1312;
    public static final int MOSTRAR_TRABAJADOR = 1313;
    public static final int MOSTRAR_TRABAJADOR_OK = 1314;
    public static final int MOSTRAR_TRABAJADOR_KO = 1315;
    public static final int VLISTAR_TRABAJADOR = 1316;
    public static final int LISTAR_TRABAJADORES = 1317;
    public static final int LISTAR_TRABAJADORES_OK = 1318;
    public static final int LISTAR_TRABAJADORES_KO = 1319;
    public static final int VLISTAR_TRABAJADORES_POR_DEPARTAMENTO = 1320;
    public static final int LISTAR_TRABAJADORES_POR_DEPARTAMENTO = 1321;
    public static final int LISTAR_TRABAJADORES_POR_DEPARTAMENTO_OK = 1322;
    public static final int LISTAR_TRABAJADORES_POR_DEPARTAMENTO_KO = 1323;
    public static final int VCALCULAR_SUELDO_TRABAJADOR = 1324;
    public static final int CALCULAR_SUELDO_TRABAJADOR = 1325;
    public static final int CALCULAR_SUELDO_TRABAJADOR_OK = 1326;
    public static final int CALCULAR_SUELDO_TRABAJADOR_KO = 1327;

  //MarcaProducto 
  	public static final int VALTA_MARCA = 1400;
  	public static final int VBAJA_MARCA = 1401;
  	public static final int VMODIFICAR_MARCA = 1402;
  	public static final int VMOSTRAR_MARCA = 1403;
  	public static final int VLISTAR_MARCA = 1404;
  	public static final int VLISTAR_MARCAS_POR_PROVEEDOR = 1405;
  	public static final int ALTA_MARCA_OK = 1406;
  	public static final int ALTA_MARCA_KO = 1407;
  	public static final int ALTA_MARCA = 1408;
  	public static final int BAJA_MARCA_OK = 1409;
  	public static final int BAJA_MARCA_KO = 1410;
  	public static final int BAJA_MARCA = 1411;
  	public static final int MODIFICAR_MARCA_OK = 1412;
  	public static final int MODIFICAR_MARCA_KO = 1413;
  	public static final int MODIFICAR_MARCA = 1414;
  	public static final int MOSTRAR_MARCA= 1415;
  	public static final int LISTAR_MARCA= 1416;
  	public static final int LISTAR_MARCAS_POR_PROVEEDOR= 1417;
  	public static final int MOSTRAR_MARCA_KO= 1418;
  	public static final int MOSTRAR_MARCA_OK= 1419;
  	public static final int LISTAR_MARCAS_POR_PROVEEDOR_OK = 1420;
  	public static final int LISTAR_MARCAS_POR_PROVEEDOR_KO = 1421;
  	public static final int V_CORRECT_RESULTADO = 1422;
	

	
}
