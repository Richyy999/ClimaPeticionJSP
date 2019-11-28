package parseo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Parsear {

	public static ArrayList<Tiempo> parseo(URL web) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document docum = builder.build(web);
			Element root = docum.getRootElement();
			Element pred = root.getChild("prediccion");
			String ciudad = root.getChildText("nombre");
			String provincia = root.getChildText("provincia");
			List<Element> listaDias = pred.getChildren();
			ArrayList<Tiempo> predicciones = new ArrayList<Tiempo>();
			for (Element element : listaDias) {
				Element temp = element.getChild("temperatura");

				String fecha = element.getAttributeValue("fecha");
				String tMax = temp.getChildText("maxima");
				String tMin = temp.getChildText("minima");

				predicciones.add(new Tiempo(fecha, tMax, tMin, ciudad, provincia));
			}
			return predicciones;
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static URL conectar(String url) {
		try {
			URL web = new URL(url);
			return web;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
