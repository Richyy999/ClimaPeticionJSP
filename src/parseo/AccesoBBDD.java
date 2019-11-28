package parseo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

public class AccesoBBDD {

	private static final String URL = "jdbc:mysql://localhost/municipios?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String PWD = "02091999";
	private static final String USR = "ricardo";

	private static final String SELECT_PROVINCIAS = "SELECT * FROM PROVINCIAS ORDER BY 2";
	private static final String SELECT_PROVINCIAS_POR_ID = "SELECT provincia FROM PROVINCIAS WHERE id_provincia = ?";
	private static final String SELECT_MUNICIPIOPORID = "SELECT nombre, cod_municipio FROM municipios WHERE id_provincia = ? ORDER BY 1";
	private static final String SELECT_PROVINCIAS_NOMBRE = "SELECT * FROM PROVINCIAS ORDER BY 1";

	private static Connection conection;
	private static PreparedStatement pstm;

	private static void conectarBBDD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conection = DriverManager.getConnection(URL, USR, PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static TreeMap<String, Integer> getProvincias() {
		conectarBBDD();
		TreeMap<String, Integer> provincia = new TreeMap<String, Integer>();
		try {
			pstm = conection.prepareStatement(SELECT_PROVINCIAS);
			ResultSet res = pstm.executeQuery();
			while (res.next()) {
				provincia.put(res.getString(2), res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provincia;
	}
	
	public static ArrayList<String> getProvinciasNombre(){
		conectarBBDD();
		ArrayList<String> prov = new ArrayList<String>();
		try {
			pstm = conection.prepareStatement(SELECT_PROVINCIAS_NOMBRE);
			ResultSet res = pstm.executeQuery();
			while (res.next()) {
				prov.add(res.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prov;
	}

	public static TreeMap<String, Integer> getMunicipioPorId(int i) {
		conectarBBDD();
		TreeMap<String, Integer> municipios = new TreeMap<String, Integer>();
		try {
			pstm = conection.prepareStatement(SELECT_MUNICIPIOPORID);
			pstm.setInt(1, i);
			ResultSet res = pstm.executeQuery();
			while (res.next()) {
				municipios.put(res.getString(1), res.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return municipios;
	}

	public static String getProvinciaPorId(int idMunicipio) {
		conectarBBDD();
		try {
			pstm = conection.prepareStatement(SELECT_PROVINCIAS_POR_ID);
			pstm.setInt(1, idMunicipio);
			ResultSet res = pstm.executeQuery();
			res.next();
			return res.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
