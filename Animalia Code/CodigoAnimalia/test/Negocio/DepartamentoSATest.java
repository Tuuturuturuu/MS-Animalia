package Negocio;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import Negocio.DepartamentoJPA.ASDepartamento;
import Negocio.DepartamentoJPA.TDepartamento;
import Negocio.FactoriaSA.FactoriaSA;
import Negocio.TrabajadorJPA.ASTrabajador;
import Negocio.TrabajadorJPA.TTrabajadorCompleto;


public class DepartamentoSATest {

	private static ASDepartamento asDepartamento;
	private static ASTrabajador asTrabajador;
	private static Random random;

	@BeforeClass
	public static void beforeClass() {
		asDepartamento = FactoriaSA.getInstance().getASDepartamento();
		asTrabajador = FactoriaSA.getInstance().getTrabajadorSA();
		random = new Random();
	}

	public TDepartamento creaTDepartamentoRandom() {
		TDepartamento departamento = new TDepartamento();
		departamento.setNombre("ASDepartamento departamento " + random.nextInt());
		departamento.setActivo(true);
		return departamento;
	}

	@Test
	public void altaDepartamento() {
		TDepartamento dep = creaTDepartamentoRandom();
		Integer res = asDepartamento.altaDepartamento(dep);
		dep.setId(res);
		if (res <= 0) //Alta departamento correcto
			fail("Error: Alta departamento sin errores deberia retornar ID > 0 y retorna " + res);
		res = asDepartamento.altaDepartamento(dep);
		if (res != -110) //Alta departamento entidad repetida activa
			fail("Error: Entidad repetida activa, deberia devolver -110 y devuelve: " + res);
		asDepartamento.bajaDepartamento(dep.getId());
		res = asDepartamento.altaDepartamento(dep);
		if (res <= 0) //Alta departamento reactivación
			fail("Error: Reactivar una entidad debería devolver ID > 0 y retorna " + res);
	}
	
	
	@Test
	public void bajaDepartamento() {
		TDepartamento dep = creaTDepartamentoRandom();
		Integer res = asDepartamento.altaDepartamento(dep);
		Integer sol = asDepartamento.bajaDepartamento(res);
		if (sol < 0)
			fail("Error: Baja departamento sin errores deberia retornar ID >= 0 y retorna " + res);
		sol = asDepartamento.bajaDepartamento(res);
		if (sol != -112)
			fail("Error: Entidad ya dada de baja deberia devolver -112 y devuelve: " + res);
		sol = asDepartamento.bajaDepartamento(-2154);
		if (sol > 0)
			fail("Error: entidad inexistente deberia devolver un numero negativo y devuelve: " + res);
	}

	@Test
	public void mostrarTest() {
		// Mostrar - departamento inexistente
		TDepartamento res = asDepartamento.mostrarDepartamento(-1);
		if (res.getId() > 0)
			fail("Error: Mostrar departamento inexsitente deberia retornar null y retorna " + res);

		// Mostrar - sin error
		TDepartamento dep = creaTDepartamentoRandom();
		Integer rdo = asDepartamento.altaDepartamento(dep);
		res = asDepartamento.mostrarDepartamento(rdo);
		if (res.getId() < 0)
			fail("Error: Mostrar departamento deberia retornar una entidad sin errores.");

	}

	@Test
	public void listarTest() {
		// Listar - sin errores
		TDepartamento dep1 = creaTDepartamentoRandom();
		TDepartamento dep2 = creaTDepartamentoRandom();
		Integer dep1ID = asDepartamento.altaDepartamento(dep1);
		if (dep1ID <= 0)
			fail("Error: Alta es un prerequisito de este test");
		Integer dep2ID = asDepartamento.altaDepartamento(dep2);
		if (dep2ID <= 0)
			fail("Error: Alta es un prerequisito de este test");
		List<TDepartamento> lista = asDepartamento.listarDepartamento();
		boolean dep1Found = false, dep2Found = false;
		for (TDepartamento deps : lista) {
			if (deps.getId().equals(dep1ID)) {
				dep1Found = true;
			}
			if (deps.getId().equals(dep2ID)) {
				dep2Found = true;
			}
		}

		if (!dep1Found || !dep2Found)
			fail("Error: Listar deberia contener las entidades creadas. DEP1: " + dep1Found + ", DEP2: " + dep2Found);
	}
	
    @Test
    public void modificarDepartamento() {
        TDepartamento dep = creaTDepartamentoRandom();
        Integer id = asDepartamento.altaDepartamento(dep);

        TDepartamento mdep = creaTDepartamentoRandom();
        mdep.setId(id);

        Integer result = asDepartamento.modificarDepartamento(mdep);

        if (result <= 0)
            fail("Error: Modificar departamento debería devolver un ID válido y devuelve: " + result);
	
    }
    
    @Test
    public void calcularNominaDepartamentoTest() {
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
        
        if(asDepartamento.calcularNominaDepartamento(idDepartamento) != sueldoCalculado)
        	fail("Error en el calculo de la nomina");
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
