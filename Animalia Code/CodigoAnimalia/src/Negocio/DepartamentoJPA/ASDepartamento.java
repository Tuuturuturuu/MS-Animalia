package Negocio.DepartamentoJPA;

import java.util.List;

public interface ASDepartamento {
    public Integer altaDepartamento(TDepartamento departamento);

    public Integer bajaDepartamento(Integer id);

    public TDepartamento mostrarDepartamento(Integer id);

    public Integer modificarDepartamento(TDepartamento departamento);

    public List<TDepartamento> listarDepartamento();

    public double calcularNominaDepartamento(Integer id_departamento);
}
