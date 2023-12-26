package Negocio.ComprobacionesRequisitosBBDD;

public abstract class ComprobacionesRequisitos {
	static ComprobacionesRequisitos comprobacionesR = null;

	public static synchronized ComprobacionesRequisitos getComprobacionesRequisitosBBDD() {

		if (comprobacionesR == null)
			comprobacionesR = new ComprobacionesRequisitosImp();
		return comprobacionesR;
	}

	public abstract boolean nombreValido(String dni);

	public abstract boolean tlValido(String dni);

	public abstract boolean dniValido(String dni);

	public abstract boolean isNumeric(String dni);
}
