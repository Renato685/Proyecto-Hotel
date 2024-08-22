package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.ModelLibro;

@WebServlet("/ValidaRemoteRevistaLibroSerieServlet")
public class ValidaRemoteRevistaLibroSerieServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String variable = req.getParameter("serie");
		System.out.println("[init] validaRemoteRevistaLibroSerieServlet => " + variable);
		
		ModelLibro modelLibro = new ModelLibro();
		boolean existe  = modelLibro.existeLibroPorSerie(variable);
		String msg = "";
		if (existe) {
			msg = "{\"valid\":false}";
		}else {
			msg = "{\"valid\":true}";
		}
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println(msg);
		
		System.out.println("[fin] validaRemoteRegistraAlumnoCampoDNIServlet");
	}
	
}