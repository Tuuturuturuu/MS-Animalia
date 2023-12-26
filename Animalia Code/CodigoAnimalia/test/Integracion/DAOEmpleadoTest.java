package Integracion;

import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoLimpieza;
import Negocio.Empleado.TEmpleadoZoologico;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;
import Integracion.Empleado.DAOEmpleado;
import Integracion.FactoriaDAO.FactoriaDAO;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class DAOEmpleadoTest {

    private static DAOEmpleado daoEmpleado;

    private boolean equals(TEmpleado a, TEmpleado b) {
    	boolean igualComunes = a.getId().equals(b.getId())
    			&& a.getDni().equals(b.getDni())
    			&& a.getNombre().equals(b.getNombre())
    			&& a.getSueldoBase().equals(b.getSueldoBase())
    			&& a.getTelefono().equals(b.getTelefono())
    			&& a.getActivo().equals(b.getActivo());
    	boolean igualHerencia = false;
    	if(a instanceof TEmpleadoLimpieza && b instanceof TEmpleadoLimpieza)
    	{
    		TEmpleadoLimpieza la = (TEmpleadoLimpieza) a;
    		TEmpleadoLimpieza lb = (TEmpleadoLimpieza) b;
    		igualHerencia = la.getSuplemento().equals(lb.getSuplemento())
    				&& la.getZona().equals(lb.getZona());
    	}
    	else if(a instanceof TEmpleadoZoologico && b instanceof TEmpleadoZoologico)
    	{
    		TEmpleadoZoologico za = (TEmpleadoZoologico) a;
    		TEmpleadoZoologico zb = (TEmpleadoZoologico) b;
    		igualHerencia = za.getEspecialidad().equals(za.getEspecialidad())
    				&& za.getExperiencia().equals(zb.getExperiencia())
    				&& za.getTasa().equals(zb.getTasa());
    	}
    	
    	return igualComunes && igualHerencia;
    }

    @BeforeClass
    public static void beforeClass() {
        daoEmpleado = FactoriaDAO.getInstance().getDAOEmpleado();
    }

    @Test
    public void testAltaEmpleado() {
        try {
            Transaction t = crearTransaccion();
            t.start();
            TEmpleado empleado1 = crearTEmpleadoLimpieza();
            Integer idEmpleado1 = daoEmpleado.alta(empleado1);
            if (idEmpleado1 < 0) {
                t.rollback();
                fail("Error: altaEmpleado() debería retornar ID > 0");
            }
            TEmpleado empleado2 = crearTEmpleadoZoologico();
            Integer idEmpleado2 = daoEmpleado.alta(empleado2);
            if (idEmpleado2 < 0) {
                t.rollback();
                fail("Error: altaEmpleado() debería retornar ID > 0");
            }
            t.commit();
        } catch (Exception e) {
            fail("Excepción");
            e.printStackTrace();
        }
    }


    @Test
    public void testBajaEmpleado() {
        try {
            Transaction t = crearTransaccion();
            t.start();
            TEmpleado empleado1 = crearTEmpleadoLimpieza();
            Integer idEmpleado1 = daoEmpleado.alta(empleado1);
            Integer result1 = daoEmpleado.baja(idEmpleado1);
            if (result1 < 0) {
                t.rollback();
                fail("Error: bajaEmpleado() debería retornar un número positivo");
            }
            
            TEmpleado empleado2 = crearTEmpleadoZoologico();
            Integer idEmpleado2 = daoEmpleado.alta(empleado2);
            Integer result2 = daoEmpleado.baja(idEmpleado2);
            if (result2 < 0) {
                t.rollback();
                fail("Error: bajaEmpleado() debería retornar un número positivo");
            }
            t.commit();
        } catch (Exception e) {
            fail("Excepción");
            e.printStackTrace();
        }
    }

    @Test
    public void testMostrarEmpleado() {
        try {
            Transaction t = crearTransaccion();
            t.start();
            TEmpleado empleado1 = crearTEmpleadoZoologico();
            Integer id1 = daoEmpleado.alta(empleado1);
            empleado1.setId(id1);
            if (!equals(empleado1, daoEmpleado.mostrar(id1))) {
                t.rollback();
                fail("Error: mostrarEmpleado() debería devolver un empleado idéntico");
            }
            
            TEmpleado empleado2 = crearTEmpleadoZoologico();
            Integer id2 = daoEmpleado.alta(empleado2);
            empleado2.setId(id2);
            if (!equals(empleado2, daoEmpleado.mostrar(id2))) {
                t.rollback();
                fail("Error: mostrarEmpleado() debería devolver un empleado idéntico");
            }

            t.commit();
        } catch (Exception e) {
            fail("Excepción");
            e.printStackTrace();
        }
    }

    
    @Test
    public void testModificarEmpleado() {
        try {
            Transaction t = crearTransaccion();
            t.start();

            TEmpleado empleado1 = crearTEmpleadoLimpieza();
            Integer id1 = daoEmpleado.alta(empleado1);
            empleado1.setId(id1);
            empleado1.setNombre(generarNombreAleatorio());
            Integer resultado1 = daoEmpleado.modificar(empleado1);
            if (resultado1 < 0) {
                t.rollback();
                fail("Error: modificarEmpleado() debería retornar un numero mayor positivo");
            }
            TEmpleado empleadoModificado1 = daoEmpleado.mostrar(id1);
            if (!equals(empleado1, empleadoModificado1)) {
                t.rollback();
                fail("Error: Los atributos del empleado no coinciden después de la modificación");
            }
            
            TEmpleado empleado2 = crearTEmpleadoZoologico();
            Integer id2 = daoEmpleado.alta(empleado2);
            empleado2.setId(id2);
            empleado2.setNombre(generarNombreAleatorio());
            Integer resultado2 = daoEmpleado.modificar(empleado2);
            if (resultado2 < 0) {
                t.rollback();
                fail("Error: modificarEmpleado() debería retornar un numero mayor positivo");
            }
            TEmpleado empleadoModificado2 = daoEmpleado.mostrar(id2);
            if (!equals(empleado2, empleadoModificado2)) {
                t.rollback();
                fail("Error: Los atributos del empleado no coinciden después de la modificación");
            }

            t.commit();
        } catch (Exception e) {
            fail("Excepción");
            e.printStackTrace();
        }
    }
    

    @Test
    public void testListarEmpleados() {
        try {
            Transaction t = crearTransaccion();
            t.start();

            TEmpleado empleado1 = crearTEmpleadoLimpieza();
            TEmpleado empleado2 = crearTEmpleadoZoologico();

            Integer idEmpleado1 = daoEmpleado.alta(empleado1);
            Integer idEmpleado2 = daoEmpleado.alta(empleado2);

            empleado1.setId(idEmpleado1);
            empleado2.setId(idEmpleado2);

            Set<TEmpleado> empleados = daoEmpleado.listarEmpleados();

            boolean foundEmpleado1 = false;
            boolean foundEmpleado2 = false;

            for (TEmpleado empleado : empleados) {
                if (empleado.getId().equals(empleado1.getId())) {
                    foundEmpleado1 = true;
                } else if (empleado.getId().equals(empleado2.getId())) {
                    foundEmpleado2 = true;
                }
            }

            if (!foundEmpleado1 || !foundEmpleado2) {
                t.rollback();
                fail("Error: La lista de empleados no contiene todos los empleados creados");
            }

            t.commit();
        } catch (Exception e) {
            fail("Excepción");
            e.printStackTrace();
        }
    }

    @Test
    public void testLeerPorDniUnico() {
        try {
            Transaction t = crearTransaccion();
            t.start();

            TEmpleado empleado1 = crearTEmpleadoLimpieza();
            Integer id1 = daoEmpleado.alta(empleado1);
            empleado1.setId(id1);
            TEmpleado empleadoLeido1 = daoEmpleado.leerPorDniUnico(empleado1.getDni());
            if (!equals(empleado1, empleadoLeido1)) {
                t.rollback();
                fail("Error: El empleado leído por DNI único no coincide con el empleado creado");
            }
            
            TEmpleado empleado2 = crearTEmpleadoZoologico();
            Integer id2 = daoEmpleado.alta(empleado2);
            empleado2.setId(id2);
            TEmpleado empleadoLeido2 = daoEmpleado.leerPorDniUnico(empleado2.getDni());
            if (!equals(empleado2, empleadoLeido2)) {
                t.rollback();
                fail("Error: El empleado leído por DNI único no coincide con el empleado creado");
            }

            t.commit();
        } catch (Exception e) {
            fail("Excepción");
            e.printStackTrace();
        }
    }

    private TEmpleado crearTEmpleadoLimpieza() {
        TEmpleadoLimpieza empleado = new TEmpleadoLimpieza();
        empleado.setId(generateRandom());
        empleado.setNombre(generarNombreAleatorio());
        empleado.setDni(generarDniAleatorio());
        empleado.setSueldoBase((generateRandomDouble()));
        empleado.setTelefono(generateRandom());
        empleado.setActivo(true);
        empleado.setTipo(0);
        empleado.setSuplemento((double) generateRandom());
        empleado.setZona(generarNombreAleatorio());
        return empleado;
    }
    
    private TEmpleado crearTEmpleadoZoologico() {
        TEmpleadoZoologico empleado = new TEmpleadoZoologico();
        empleado.setId(generateRandom());
        empleado.setNombre(generarNombreAleatorio());
        empleado.setDni(generarDniAleatorio());
        empleado.setSueldoBase((generateRandomDouble()));
        empleado.setTelefono(generateRandom());
        empleado.setActivo(true);
        empleado.setTipo(1);
        empleado.setEspecialidad(generarNombreAleatorio());
        empleado.setExperiencia(generateRandom());
        empleado.setTasa((double) generateRandom());
        return empleado;
    }

    private String generarNombreAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder nombreAleatorio = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(caracteres.length());
            nombreAleatorio.append(caracteres.charAt(index));
        }
        return nombreAleatorio.toString();
    }

    private String generarDniAleatorio() {
        String caracteres = "0123456789";
        StringBuilder dniAleatorio = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            dniAleatorio.append(caracteres.charAt(index));
        }
        return dniAleatorio.toString();
    }

    private int generateRandom() {
        return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
    }
    
    private double generateRandomDouble()
	{
		return ThreadLocalRandom.current().nextDouble(0, 10000 + 1);
	}

    private Transaction crearTransaccion() {
        try {
            TransactionManager tm = TransactionManager.getInstance();
            tm.newTransaction();
            return tm.getTransaction();
        } catch (Exception e) {
            fail("Error transaccional");
            return null;
        }
    }
}
