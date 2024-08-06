package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.google.gson.Gson;

import entity.Autor;
import entity.Grado;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelAutor;
@WebServlet("/registraAutor")

public class RegistraAutorServlet extends HttpServlet{
	    private static final long serialVersionUID = 1L;
	    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	String nom = req.getParameter("nombres");
	        String ape = req.getParameter("apellidos");
	        String tel = req.getParameter("telefono");
	        String fecNac = req.getParameter("fechaNacimiento");
	        String grad = req.getParameter("grado");

	        Autor objAutor = new Autor();
	        objAutor.setNombres(nom);
	        objAutor.setApellidos(ape);
	        objAutor.setTelefono(tel);
	        objAutor.setFechaNacimiento(Date.valueOf(fecNac));
	        objAutor.setFechaRegistro(new Date(System.currentTimeMillis()));
	        objAutor.setFechaActualizacion(new Date(System.currentTimeMillis()));
	        objAutor.setEstado(1);
	        
	        Grado  objGrado = new Grado();
	        objGrado.setIdGrado(Integer.parseInt(grad));

	        objAutor.setGrado(objGrado);

	        ModelAutor modelAlumno = new ModelAutor();
	        int salida = modelAlumno.insertarAutor(objAutor);

	        Respuesta objRespuesta = new Respuesta();

	        if (salida > 0) {
	            objRespuesta.setMensaje("Registro exitoso");
	        }else {
	            objRespuesta.setMensaje("Error en el registro");
	        }

	        Gson gson = new Gson();
	        String json = gson.toJson(objRespuesta);

	        resp.setContentType("application/json;charset=UTF-8");

	        PrintWriter out = resp.getWriter();
	        out.println(json);
	    }
	}
	
