package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

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

@WebServlet("/crudAutor")
public class CrudAutorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String metodo = req.getParameter("metodo");	
		switch (metodo) {
			case "paramLista": 		{ lista(req, resp); break;}
			case "paramInserta": 	{ inserta(req, resp); break;}
			case "paramActualiza": 	{ actualiza(req, resp); break;}
			case "paramELogica": 	{ eliminacionLogica(req, resp); break;}
			case "paramEFisica": 	{ eliminacionFisica(req, resp);}
		}
	}

	protected void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] lista");
		String filtro = req.getParameter("filtro");

		ModelAutor model = new ModelAutor();
		List<Autor> lista = model.listaAutorPorNombres(filtro + "%");

		Gson gson = new Gson();
		String json = gson.toJson(lista);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.println(json);

		System.out.println("[fin] lista");
	}

	protected void inserta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] inserta");

		// 1 Recuperar datos de la GUI
		String nom = req.getParameter("nombres");
		String ape = req.getParameter("apellidos");
		String tel = req.getParameter("telefono");
		String fecNac = req.getParameter("fechaNacimiento");
		String gra = req.getParameter("grado");

		// 2 Llenar objAlumno
		Autor objAutor = new Autor();
		objAutor.setNombres(nom);
		objAutor.setApellidos(ape);
		objAutor.setTelefono(tel);
		objAutor.setFechaNacimiento(Date.valueOf(fecNac));
		objAutor.setFechaRegistro(new Date(System.currentTimeMillis()));
		objAutor.setFechaActualizacion(new Date(System.currentTimeMillis()));
		objAutor.setEstado(1);

		Grado objGrado = new Grado();
		objGrado.setIdGrado(Integer.parseInt(gra));
		objAutor.setGrado(objGrado);

		// 3 Se envía objAlumno registrar
		ModelAutor modelAutor = new ModelAutor();
		int salida = modelAutor.insertarAutor(objAutor);

		// 4 Se envía el mensaje
		Respuesta objRespuesta = new Respuesta();

		if (salida>0) {
			List<Autor>  lstDatos = modelAutor.listaAutorPorNombres("%");
			objRespuesta.setDatos(lstDatos);
			objRespuesta.setMensaje("Registro existoso");
		}

		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);

		resp.setContentType("application/json;charset=UTF-8");

		PrintWriter out = resp.getWriter();
		out.println(json);

		System.out.println("[fin] inserta");
	}

	protected void actualiza(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] actualiza");

		System.out.println("[fin] actualiza");
	}

	protected void eliminacionFisica(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("[ini] eliminacionFisica");

		String idAutor = req.getParameter("idAutor");
		Respuesta objResupeta = new Respuesta();
		ModelAutor modelAutor = new ModelAutor();
		int salida =modelAutor.eliminarAutor(Integer.parseInt(idAutor));
		if (salida>0) {
			List<Autor>  lstDatos = modelAutor.listaAutorPorNombres("%");
			objResupeta.setDatos(lstDatos);
			objResupeta.setMensaje("Eliminaci\u00f3n existosa");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] eliminacionFisica");

	}

	protected void eliminacionLogica(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("[ini] eliminacionLogica");

		String idAutor = req.getParameter("idAutor");
		
		ModelAutor modelAutor = new ModelAutor();
		Autor objAutor = modelAutor.buscaAutorPorPK(Integer.parseInt(idAutor));
		
		int estadoNuevo = objAutor.getEstado() == 0 ? 1 : 0;
		objAutor.setEstado(estadoNuevo);
		modelAutor.actualizarAutor(objAutor);
		
		Respuesta objResupeta = new Respuesta();
		List<Autor>  lstDatos = modelAutor.listaAutorPorNombres("%");
		objResupeta.setDatos(lstDatos);
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] eliminacionLogica");

	}

}
