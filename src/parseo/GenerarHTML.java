package parseo;

import java.util.ArrayList;

public class GenerarHTML {

	public static String crearHTML(ArrayList<Tiempo> predicciones) {
		String tabla = "<style>table{text-align: center;}th{color: blue;}</style>";
		tabla += "<h1>Ciudad: " + predicciones.get(0).getCiudad() + "</h1><h2>Provincia: "
				+ predicciones.get(0).getProvincia() + "</h2>";
		tabla += "<table border = 1 solid blue><tr><th>Fecha</th><th>Temperatura M�xima</th><th>Temperatuta M�nima</th></tr>";
		for (Tiempo tiempo : predicciones) {
			tabla += "<tr>";
			tabla += tiempo.toString();
			tabla += "</tr>";
		}
		tabla += "</table>";
		return tabla;
	}
}
