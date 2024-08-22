package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import entity.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLibro;

@WebServlet("/consultaLibro")

public class ConsultaLibroServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String tit = req.getParameter("titulo");
		String ani = req.getParameter("anio");
		String ser = req.getParameter("serie");
		String tem = req.getParameter("tema");
		
		int anio = -1; // Valor predeterminado para indicar que no se debe filtrar por año

	    try {
	        if (ani != null && !ani.trim().isEmpty()) {
	            anio = Integer.parseInt(ani);
	        }
	    } catch (NumberFormatException e) {
	        // Manejar la excepción si el parámetro no es un número válido
	        e.printStackTrace();
	        // Puedes enviar una respuesta de error al cliente aquí si es necesario
	    }


		ModelLibro model = new ModelLibro();
		List<Libro> lista = model.listaLibroComplejo( "%"+tit+"%",anio,ser,tem);
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.println(json);
		
		
	}

}
