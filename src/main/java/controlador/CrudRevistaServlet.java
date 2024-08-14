package controlador;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import entity.Modalidad;
import entity.Respuesta;
import entity.Revista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelRevista;

@WebServlet("/crudRevista")
public class CrudRevistaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		String metodo = req.getParameter("metodo");	
		switch (metodo) {
			case "paramLista": 		{ lista(req, resp); break;}
			case "paramInserta": 	{ inserta(req, resp); break;}
			case "paramELogica": 	{ eliminacionLogica(req, resp); break;}
			case "paramEFisica": 	{ eliminacionFisica(req, resp);}
		}
	}
	
	protected void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		System.out.println("[ini] lista");
		String filtro = req.getParameter("filtro");
		
		ModelRevista model =new ModelRevista();
		List<Revista> lista =model.listaRevistaPorNombre(filtro+"%");
		
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.println(json);

		System.out.println("[fin] lista");
	}
	protected void inserta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] inserta");
		String nom = req.getParameter("nombre");
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

		ModelRevista modelRevista = new ModelRevista();
		int salida = modelRevista.insertarRevista(objRevista);

		// 4 Se envía el mensaje
		Respuesta objRespuesta = new Respuesta();

		if (salida > 0) {
			List<Revista> lstDatos = modelRevista.listaRevistaPorNombre("%");
			objRespuesta.setDatos(lstDatos);
			objRespuesta.setMensaje("Registro exitoso");
		}

		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);

		resp.setContentType("application/json;charset=UTF-8");

		PrintWriter out = resp.getWriter();
		out.println(json);
		
		
		
		
		
		
		
		System.out.println("[fin] inserta");
	}
	
	protected void eliminacionFisica(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		System.out.println("[ini] eliminacionFisica");
		
		String idRevista = req.getParameter("idRevista");
		Respuesta objResupeta = new Respuesta();
		ModelRevista modelRevista = new ModelRevista();
		int salida =modelRevista.eliminaRevista(Integer.parseInt(idRevista));
		if (salida>0) {
			List<Revista>  lstDatos = modelRevista.listaRevistaPorNombre("%");
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
	protected void eliminacionLogica(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] eliminacionLogica");
		
		String idRevista = req.getParameter("idRevista");
		
		ModelRevista modelRevista = new ModelRevista();
		Revista objRevista = modelRevista.buscaRevistaPorPK(Integer.parseInt(idRevista));
		int estadoNuevo = objRevista.getEstado() == 0 ? 1 : 0;
		objRevista.setEstado(estadoNuevo);
		modelRevista.actualizarRevista(objRevista);
		
		Respuesta objResupeta = new Respuesta();
		List<Revista>  lstDatos = modelRevista.listaRevistaPorNombre("%");
		objResupeta.setDatos(lstDatos);
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] eliminacionLogica");
		
	}
	
	
	

	
}