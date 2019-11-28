<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clima España</title>
</head>
<%
	@SuppressWarnings("unchecked")
	TreeMap<String, Integer> combo = (TreeMap<String, Integer>) request.getAttribute("combo");
%>
<body>
	<form action="Servlet" method="post">
		<select name="combo">
			<%
				for (Map.Entry<String, Integer> estring : combo.entrySet()) {
					String html;
					if (estring.getValue() == session.getAttribute("idProvincia")) {
						html = "<option selected value='" + estring.getValue() + "'>" + estring.getKey() + "</option>";
					} else {
						html = "<option value='" + estring.getValue() + "'>" + estring.getKey() + "</option>";
					}
					out.print(html);
				}
			%>
		</select>
		<input type="submit" value="Siguiente">
	</form>
	<p></p>
	<%
		@SuppressWarnings("unchecked")
		TreeMap<String, Integer> mun = (TreeMap<String, Integer>) request.getAttribute("comboMun");
		if (mun != null) {
			out.print("<form action=\"Servlet\" method=\"post\">");
			out.print("<select name=\"comboMun\">");
			for (Map.Entry<String, Integer> estring : mun.entrySet()) {
				String html = "<option value='" + estring.getValue() + "'>" + estring.getKey() + "</option>";
				out.print(html);
			}
			out.print("</select>");
			out.print("<input type=\"submit\" value=\"Enviar\"></form>");
		}
	%>
	<%
		String tabla = (String) request.getAttribute("tabla");
		if (tabla != null)
			out.print(tabla);
	%>
</body>
</html>