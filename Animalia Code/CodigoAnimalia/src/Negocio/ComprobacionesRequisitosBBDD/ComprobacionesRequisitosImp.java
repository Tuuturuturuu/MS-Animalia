package Negocio.ComprobacionesRequisitosBBDD;

public class ComprobacionesRequisitosImp extends ComprobacionesRequisitos{
	public boolean nombreValido(String nombre) {
		if (nombre.trim().length() > 100 || nombre.trim().length() < 1)
			return false;
		else
			return true;
	}

	public boolean checkString(String s) {
		return s.matches("[a-zA-Z\\s]*");
	}

	public boolean tlValido(String tlf) {
		return tlf.matches("\\d{9}");
	}

	public boolean dniValido(String dni) {

		return dni.matches("\\d{8}[a-zA-Z]");
	}

	public boolean isNumeric(String num) {
		try {
			Double.parseDouble(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
