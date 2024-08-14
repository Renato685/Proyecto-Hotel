package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import java.util.List;

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


@WebServlet("/crudLibro")
public class CrudLibroServlet extends HttpServlet {


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

		ModelLibro model = new ModelLibro();
		List<Libro> lista = model.listaLibroPorNombres(filtro + "%");

		Gson gson = new Gson();
		String json = gson.toJson(lista);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.println(json);

		System.out.println("[fin] lista");
	}

	protected void inserta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] inserta");
		
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
					        
					        if (salida>0) {
								List<Libro>  lstDatos = modelLibro.listaLibroPorNombres("%");
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

		String idLibro = req.getParameter("idLibro");
		Respuesta objResupeta = new Respuesta();
		ModelLibro modelLibro = new ModelLibro();
		int salida =modelLibro.eliminarLibro(Integer.parseInt(idLibro));
		if (salida>0) {
			List<Libro>  lstDatos = modelLibro.listaLibroPorNombres("%");
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

		String idLibro = req.getParameter("idLibro");
		
		ModelLibro modelLibro = new ModelLibro();
		Libro objLibro = modelLibro.buscaLibroPorPK(Integer.parseInt(idLibro));
		int estadoNuevo = objLibro.getEstado() == 0 ? 1 : 0;
		objLibro.setEstado(estadoNuevo);
		modelLibro.actualizarLibro(objLibro);
		
		Respuesta objResupeta = new Respuesta();
		List<Libro>  lstDatos = modelLibro.listaLibroPorNombres("%");
		objResupeta.setDatos(lstDatos);
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] eliminacionLogica");

	}
	
	
	


}
