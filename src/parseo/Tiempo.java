package parseo;

public class Tiempo {

	private String fecha;
	private String tMax;
	private String tMin;
	private String ciudad;
	private String provincia;

	public Tiempo(String fecha, String tMax, String tMin, String ciudad, String provincia) {
		String[] fechaCortada = fecha.split("-");
		this.fecha = fechaCortada[2] + " / " + fechaCortada[1] + " / " + fechaCortada[0];
		this.tMax = tMax;
		this.tMin = tMin;
		this.ciudad = ciudad;
		this.provincia = provincia;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public String toHTML() {
		return "<tr><td>" + this.fecha + "</td><td>" + this.tMax + "</td><td>" + this.tMin + "</td></tr>";
	}
}
