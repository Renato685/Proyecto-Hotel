package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.google.gson.Gson;


import entity.Categoria;
import entity.Libro;

import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLibro;

//AUTOR : JOSEP ATAUCUSI

@WebServlet("/registraLibro")
public class RegistraLibroServlet  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1 Recuperar datos de la GUI
		String tit = req.getParameter("titulo");
		String ani = req.getParameter("anio");
		String ser = req.getParameter("serie");
		String tem = req.getParameter("tema");
		String cat = req.getParameter("categoria");
		
		
		
		//2 Llenar objLibro
		
				Libro objLibro = new Libro();
				objLibro.setTitulo(tit);
				objLibro.setAnio(Integer.parseInt(ani));
				objLibro.setSerie(ser);
				objLibro.setTema(tem);
				objLibro.setFechaRegistro(new Date(System.currentTimeMillis()));
				objLibro.setFechaActualizacion(new Date(System.currentTimeMillis()));
				objLibro.setEstado(1);
				
				
				
				 Categoria  objCategoria = new Categoria();
				 objCategoria.setIdCategoria(Integer.parseInt(cat));
				 objLibro.setCategoria(objCategoria);
				 
				 //3 SE ENVIA objAlumno a registrar
				 
				 ModelLibro modelLibro = new ModelLibro();
				 int salida = modelLibro.insertarLibro(objLibro);
				 
				//4 Se envía el mensaje
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
