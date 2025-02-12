package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import com.google.gson.Gson;

//Autor: Miriam Abad
import entity.Revista;
import entity.Modalidad;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLibro;
import model.ModelModalidad;
import model.ModelRevista;

@WebServlet("/registraRevista")
public class RegistraRevistaServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nom = req.getParameter("nombres");
		String fre = req.getParameter("frecuencia");
		String modal = req.getParameter("modalidad");

		Revista objRevista = new Revista();
		objRevista.setNombre(nom);
		objRevista.setFrecuencia(fre);
		objRevista.setFechaRegistro(new Date(System.currentTimeMillis()));
		objRevista.setFechaCreacion(new Date(System.currentTimeMillis()));
		objRevista.setEstado(1);

		Modalidad objModalidad = new Modalidad();
		objModalidad.setIdModalidad(Integer.parseInt(modal));
		objRevista.setModalidad(objModalidad);

		ModelRevista modelLibro = new ModelRevista();
		int salida = modelLibro.insertarRevista(objRevista);

		// 4 Se envía el mensaje
		Respuesta objRespuesta = new Respuesta();

		if (salida > 0) {
			objRespuesta.setMensaje("Registro exitoso");
		} else {
			objRespuesta.setMensaje("Error en el registro");
		}

		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);

		resp.setContentType("application/json;charset=UTF-8");

		PrintWriter out = resp.getWriter();
		out.println(json);
	}
}
