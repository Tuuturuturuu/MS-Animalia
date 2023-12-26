package Negocio;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.DepartamentoJPA.ASDepartamento;
import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.TrabajadorJPA.ASTrabajador;
import Negocio.TrabajadorJPA.TTrabajador;
import Negocio.TrabajadorJPA.TTrabajadorCompleto;

public class TrabajadorSATest {
	private static ASDepartamento asDepartamento;
	private static ASTrabajador asTrabajador;
	private static Random random;

	@BeforeClass
	public static void beforeClass() {
		asDepartamento = FactoriaSA.getInstance().getASDepartamento();
		asTrabajador = FactoriaSA.getInstance().getTrabajadorSA();
		random = new Random();
	}
	
	@Test
	public void altaTrabajador() {
	    // Damos de alta un departamento para asociar al trabajador
	    TDepartamento dep = creaTDepartamentoRandom();
	    Integer idDepartamento = asDepartamento.altaDepartamento(dep);

	    // Creamos un trabajador aleatorio
	    TTrabajador trabajador = creaTTrabajadorCompletoRandom();
	    
	    // Departamento no existe
	    trabajador.setIdDepartamento(-2);
	    // Intentamos dar de alta el trabajador
	    Integer res = asTrabajador.altaTrabajador(trabajador);

	    if (res != -115) {
	        fail("Error: Departamento no existe, debería devolver -1 " + res);
	    }
	    
	    // Departamento inactivo
	    asDepartamento.bajaDepartamento(idDepartamento);
	    trabajador.setIdDepartamento(idDepartamento);
	    // Intentamos dar de alta el trabajador
	    	res = asTrabajador.altaTrabajador(trabajador);

	    if (res != -116) {
	        fail("Error: Departamento inactivo, debería devolver -1 " + res);
	    }
	    asDepartamento.altaDepartamento(dep);
	    
	    //Alta trabajador correcta
	    trabajador.setIdDepartamento(idDepartamento);
	    // Intentamos dar de alta el trabajador
	    	res = asTrabajador.altaTrabajador(trabajador);

	    // Comprobamos que el resultado es un ID válido (mayor a 0)
	    if (res <= 0) {
	        fail("Error: Alta trabajador sin errores debería retornar ID > 0 y retorna " + res);
	    }

	    // Intentamos dar de alta el mismo trabajador nuevamente (debería devolver -1)
	    Integer resRepetido = asTrabajador.altaTrabajador(trabajador);
	    if (resRepetido != -120) {
	        fail("Error: Trabajador repetido activo, debería devolver -1 y devuelve: " + resRepetido);
	    }

	    // Damos de baja el trabajador creado para poder reactivarlo luego
	    asTrabajador.bajaTrabajador(res);

	    // Intentamos dar de alta nuevamente (debería reactivar y retornar ID > 0)
	    Integer resReactivado = asTrabajador.altaTrabajador(trabajador);
	    if (resReactivado <= 0) {
	        fail("Error: Reactivar una entidad debería devolver ID > 0 y retorna " + resReactivado);
	    }
	}
	
    @Test
    public void bajaTrabajador() {
        // Damos de alta un departamento para asociar al trabajador
        TDepartamento dep = creaTDepartamentoRandom();
        Integer idDepartamento = asDepartamento.altaDepartamento(dep);

        // Creamos un trabajador aleatorio
        TTrabajador trabajador = creaTTrabajadorCompletoRandom();

        // Damos de alta el trabajador
        trabajador.setIdDepartamento(idDepartamento);
        Integer idTrabajador = asTrabajador.altaTrabajador(trabajador);

        // Intentamos dar de baja al trabajador activo
        Integer res = asTrabajador.bajaTrabajador(idTrabajador);

        // Comprobamos que el resultado es un ID válido (mayor a 0)
        if (res <= 0) {
            fail("Error: Baja trabajador sin errores debería retornar ID > 0 y retorna " + res);
        }

        // Intentamos dar de baja al mismo trabajador nuevamente (debería devolver -1)
        Integer resRepetido = asTrabajador.bajaTrabajador(idTrabajador);
        if (resRepetido != -122) {
            fail("Error: Baja trabajador ya dado de baja, debería devolver -1 y devuelve: " + resRepetido);
        }

        // Intentamos dar de baja a un trabajador inexistente (debería devolver -1)
        Integer resInexistente = asTrabajador.bajaTrabajador(-1);
        if (resInexistente != -4) {
            fail("Error: Baja trabajador inexistente, debería devolver -1 y devuelve: " + resInexistente);
        }
    }
    
    @Test
    public void mostrarTrabajador() {
        // Damos de alta un departamento para asociar al trabajador
        TDepartamento dep = creaTDepartamentoRandom();
        Integer idDepartamento = asDepartamento.altaDepartamento(dep);
        dep.setId(idDepartamento);

        // Creamos un trabajador aleatorio
        TTrabajadorCompleto trabajador = creaTTrabajadorCompletoRandom();

        // Damos de alta el trabajador
        trabajador.setIdDepartamento(idDepartamento);
        Integer idTrabajador = asTrabajador.altaTrabajador(trabajador);
        trabajador.setId(idTrabajador);

        // Mostrar trabajador existente (debería devolver un objeto TTrabajador)
        TTrabajadorCompleto trabajadorMostrado = (TTrabajadorCompleto) asTrabajador.mostrarTrabajador(idTrabajador);
        if(!equals(trabajadorMostrado, trabajador))
        	fail("Error: Mostrar trabajador existente debería devolver un objeto TTrabajador");

        // Mostrar trabajador inexistente (debería devolver null)
        Integer idTrabajadorInexistente = -1;
        TTrabajador trabajadorInexistente = asTrabajador.mostrarTrabajador(idTrabajadorInexistente);
        if (trabajadorInexistente != null) {
            fail("Error: Mostrar trabajador inexistente debería devolver null");
        }
    }
    
    @Test
    public void listarTrabajadoresTest() {

        TDepartamento dep = creaTDepartamentoRandom();
        Integer idDepartamento = asDepartamento.altaDepartamento(dep);
        if (idDepartamento <= 0)
            fail("Error: Alta departamento es un prerequisito de este test");

        TTrabajadorCompleto trabajador1 = creaTTrabajadorCompletoRandom();
        TTrabajadorCompleto trabajador2 = creaTTrabajadorCompletoRandom();
        trabajador1.setIdDepartamento(idDepartamento);
        trabajador2.setIdDepartamento(idDepartamento);
        
        Integer idTrabajador1 = asTrabajador.altaTrabajador(trabajador1);
        if (idTrabajador1 <= 0)
            fail("Error: Alta trabajador 1 es un prerequisito de este test");

        Integer idTrabajador2 = asTrabajador.altaTrabajador(trabajador2);
        if (idTrabajador2 <= 0)
            fail("Error: Alta trabajador 2 es un prerequisito de este test");

        List<TTrabajador> lista = asTrabajador.listarTrabajadores();
        boolean trabajador1Found = false, trabajador2Found = false;
        for (TTrabajador trabajador : lista) {
            if (trabajador.getId().equals(idTrabajador1)) {
                trabajador1Found = true;
            }
            if (trabajador.getId().equals(idTrabajador2)) {
                trabajador2Found = true;
            }
        }

        if (!trabajador1Found || !trabajador2Found)
            fail("Error: Listar trabajadores debería contener los trabajadores creados. Trabajador 1: " + trabajador1Found + ", Trabajador 2: " + trabajador2Found);
    }

    @Test
    public void listarTrabajadoresPorDepartamentoTest() {

        TDepartamento dep = creaTDepartamentoRandom();
        Integer idDepartamento = asDepartamento.altaDepartamento(dep);
        if (idDepartamento <= 0)
            fail("Error: Alta departamento es un prerequisito de este test");
        
        TDepartamento dep2 = creaTDepartamentoRandom();
        Integer idDepartamento2 = asDepartamento.altaDepartamento(dep2);
        if (idDepartamento2 <= 0)
            fail("Error: Alta departamento es un prerequisito de este test");

        TTrabajadorCompleto trabajador1 = creaTTrabajadorCompletoRandom();
        TTrabajadorCompleto trabajador2 = creaTTrabajadorCompletoRandom();
        TTrabajadorCompleto trabajador3 = creaTTrabajadorCompletoRandom();
        trabajador1.setIdDepartamento(idDepartamento);
        trabajador2.setIdDepartamento(idDepartamento);
        trabajador3.setIdDepartamento(idDepartamento2);
        
        Integer idTrabajador1 = asTrabajador.altaTrabajador(trabajador1);
        if (idTrabajador1 <= 0)
            fail("Error: Alta trabajador 1 es un prerequisito de este test");

        Integer idTrabajador2 = asTrabajador.altaTrabajador(trabajador2);
        if (idTrabajador2 <= 0)
            fail("Error: Alta trabajador 2 es un prerequisito de este test");
        
        Integer idTrabajador3 = asTrabajador.altaTrabajador(trabajador3);
        if (idTrabajador3 <= 0)
            fail("Error: Alta trabajador 3 es un prerequisito de este test");

        List<TTrabajador> lista = asTrabajador.listarTrabajadoresPorDepartamento(idDepartamento);
        boolean trabajador1Found = false, trabajador2Found = false, trabajador3Found = false;
        for (TTrabajador trabajador : lista) {
            if (trabajador.getId().equals(idTrabajador1)) {
                trabajador1Found = true;
            }
            if (trabajador.getId().equals(idTrabajador2)) {
                trabajador2Found = true;
            }
            if (trabajador.getId().equals(idTrabajador3)) {
                trabajador3Found = true;
            }
        }
        
        if(trabajador3Found)
        	fail("Error: Listar trabajadores no deberia contener al trabajador 3");

        if (!trabajador1Found || !trabajador2Found)
            fail("Error: Listar trabajadores debería contener los trabajadores creados. Trabajador 1: " + trabajador1Found + ", Trabajador 2: " + trabajador2Found);
    }


    @Test
    public void calcularSueldoTrabajadorTest() {
        // Damos de alta un departamento para asociar al trabajador
        TDepartamento dep = creaTDepartamentoRandom();
        Integer idDepartamento = asDepartamento.altaDepartamento(dep);

        // Creamos un trabajador aleatorio
        TTrabajadorCompleto trabajador = creaTTrabajadorCompletoRandom();
        trabajador.setIdDepartamento(idDepartamento);
        trabajador.setSueldo(1000.0);
        trabajador.setH_extra(3.0);
        trabajador.setEuros_h_extra(20.0);
        
        Integer idTrabajador = asTrabajador.altaTrabajador(trabajador);

        // Calcular sueldo del trabajador
        Double sueldoCalculado = asTrabajador.calcularSueldoTrabajador(idTrabajador);

        // Comprobamos que el sueldo es correcto
        if (sueldoCalculado != 1060.0) {
            fail("Error: El sueldo calculado debería ser igual que 1060");
        }

    }
    
    @Test
    public void modificarTrabajadorTest() {
        // Damos de alta un departamento para asociar al trabajador
        TDepartamento dep = creaTDepartamentoRandom();
        Integer idDepartamento = asDepartamento.altaDepartamento(dep);

        // Creamos un trabajador aleatorio
        TTrabajadorCompleto trabajadorOriginal = creaTTrabajadorCompletoRandom();
        trabajadorOriginal.setIdDepartamento(idDepartamento);
        Integer idTrabajador = asTrabajador.altaTrabajador(trabajadorOriginal);

        
        // Damos de alta un departamento para asociar al trabajador
        TDepartamento dep2 = creaTDepartamentoRandom();
        Integer idDepartamento2 = asDepartamento.altaDepartamento(dep2);

        // Modificamos el trabajador
        TTrabajadorCompleto trabajadorModificado = creaTTrabajadorCompletoRandom();
        trabajadorModificado.setId(idTrabajador);
        trabajadorModificado.setIdDepartamento(idDepartamento2);

        Integer resultado = asTrabajador.modificarTrabajador(trabajadorModificado);

        // Verificamos que el resultado es el ID del trabajador modificado
        if (!idTrabajador.equals(resultado)) {
            fail("Error: El resultado debería ser el ID del trabajador modificado y es " + resultado);
        }
        
        TTrabajadorCompleto trabajadorResultado = (TTrabajadorCompleto) asTrabajador.mostrarTrabajador(idTrabajador);
        
        //Verificamos que se haya modificado
        if(!equals(trabajadorResultado, trabajadorModificado)){
        	fail("Error: No se ha modificado");
        }

        
    }
    
    
    private boolean equals(TTrabajadorCompleto a, TTrabajadorCompleto b) {
        boolean igualComunes = a.getId().equals(b.getId()) && a.getDni().equals(b.getDni())
                && a.getNombre().equals(b.getNombre()) && a.getActivo() == b.getActivo()
                && a.getSueldo().equals(b.getSueldo()) && a.getTelefono().equals(b.getTelefono())
                && a.getIdDepartamento().equals(b.getIdDepartamento())
                && a.getH_extra().equals(b.getH_extra()) && a.getEuros_h_extra().equals(b.getEuros_h_extra());

        return igualComunes;
    }


	public TDepartamento creaTDepartamentoRandom() {
		TDepartamento departamento = new TDepartamento();
		departamento.setNombre("ASDepartamento departamento " + random.nextInt());
		departamento.setActivo(true);
		return departamento;
	}
	
	private TTrabajadorCompleto creaTTrabajadorCompletoRandom() {
	    Random random = new Random();

	    // Generar un DNI aleatorio (puedes ajustar según tus necesidades)
	    String dni = UUID.randomUUID().toString().substring(0, 8);

	    // Generar un nombre aleatorio
	    String nombre = "Trabajador" + UUID.randomUUID().toString().substring(0, 8);

	    // Generar un salario aleatorio (puedes ajustar según tus necesidades)
	    Double sueldo = 1500.0 + random.nextDouble() * 2000.0;

	    // Generar un teléfono aleatorio (puedes ajustar según tus necesidades)
	    Integer telefono = 600000000 + random.nextInt(100000000);

	    // Generar horas extras aleatorias (puedes ajustar según tus necesidades)
	    Double horasExtra = random.nextDouble() * 10.0;

	    // Generar euros por hora extra aleatorios (puedes ajustar según tus necesidades)
	    Double eurosHoraExtra = 5.0 + random.nextDouble() * 5.0;

	    return new TTrabajadorCompleto(null, dni, nombre, sueldo, telefono, null, true, horasExtra, eurosHoraExtra);
	}

}
