package parseo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			TreeMap<String, Integer> provincias = AccesoBBDD.getProvincias();
			request.setAttribute("combo", provincias);
			request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("idProvincia") == null) {
			int idProvincia = Integer.parseInt(request.getParameter("combo"));
			TreeMap<String, Integer> municipios = AccesoBBDD.getMunicipioPorId(idProvincia);
			session.setAttribute("idProvincia", idProvincia);
			request.setAttribute("comboMun", municipios);
		} else {
			String idProvincia = String.valueOf(session.getAttribute("idProvincia"));
			String idMunicipio = request.getParameter("comboMun");
			String url = codificarId(idProvincia, idMunicipio);
			URL web = Parsear.conectar(url);
			ArrayList<Tiempo> predicciones = Parsear.parseo(web);
			String html = GenerarHTML.crearHTML(predicciones);
			request.setAttribute("tabla", html);
			session.setAttribute("idProvincia", null);
		}
		TreeMap<String, Integer> provincias = AccesoBBDD.getProvincias();
		request.setAttribute("combo", provincias);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private String codificarId(String idProvincia, String idMunicipio) {
		while (idProvincia.length() < 2) {
			idProvincia = "0" + idProvincia;
		}
		while (idMunicipio.length() < 3) {
			idMunicipio = "0" + idMunicipio;
		}
		return "http://www.aemet.es/xml/municipios/localidad_" + idProvincia + idMunicipio + ".xml";
	}
}
