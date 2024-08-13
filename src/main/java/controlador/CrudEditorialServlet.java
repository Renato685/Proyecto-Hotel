package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import entity.Editorial;
import entity.Pais;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelEditorial;

@WebServlet("/crudEditorial")
public class CrudEditorialServlet extends HttpServlet{
	
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

		ModelEditorial model = new ModelEditorial();
		List<Editorial> lista = model.listaEditorialPorRazonSocialODireccion(filtro + "%");

		Gson gson = new Gson();
		String json = gson.toJson(lista);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		out.println(json);

		System.out.println("[fin] lista");
	}
	
	protected void inserta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] inserta");

		String razSoc = req.getParameter("razonSocial");
		String dir = req.getParameter("direccion");
		int tel = Integer.parseInt(req.getParameter("telefono"));
		String ruc = req.getParameter("ruc");
		String fecCre = req.getParameter("fechaCreacion");
		String pai = req.getParameter("pais");
		
		Editorial objEditorial = new Editorial();
		objEditorial.setRazonSocial(razSoc);
		objEditorial.setDireccion(dir);
		objEditorial.setTelefono(tel);
		objEditorial.setRuc(ruc);
		objEditorial.setFechaCreacion(Date.valueOf(fecCre));
		objEditorial.setFechaRegistro(new Date(System.currentTimeMillis()));
		objEditorial.setFechaActualizacion(new Date(System.currentTimeMillis()));
		objEditorial.setEstado(1);
		
        Pais  objPais = new Pais();
        objPais.setIdPais(Integer.parseInt(pai));
        objEditorial.setPais(objPais);
        
        ModelEditorial modelEditorial= new ModelEditorial();
        int salida = modelEditorial.insertarEditorial(objEditorial);
        
        Respuesta objRespuesta = new Respuesta();

		if (salida>0) {
			List<Editorial>  lstDatos = modelEditorial.listaEditorialPorRazonSocialODireccion("%");
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

		String idEditorial= req.getParameter("idEditorial");
		Respuesta objResupeta = new Respuesta();
		ModelEditorial modelEditorial = new ModelEditorial();
		int salida = modelEditorial.eliminarEditorial(Integer.parseInt(idEditorial));
		if (salida>0) {
			List<Editorial>  lstDatos = modelEditorial.listaEditorialPorRazonSocialODireccion("%");
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

		String idEditorial = req.getParameter("idEditorial");
		
		ModelEditorial modelEditorial = new ModelEditorial();
		Editorial objEditorial = modelEditorial.buscaEditorialPorPK(Integer.parseInt(idEditorial));
		int estadoNuevo = objEditorial.getEstado() == 0 ? 1 : 0;
		objEditorial.setEstado(estadoNuevo);
		modelEditorial.actualizarEditorial(objEditorial);
		
		Respuesta objResupeta = new Respuesta();
		List<Editorial>  lstDatos = modelEditorial.listaEditorialPorRazonSocialODireccion("%");
		objResupeta.setDatos(lstDatos);
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] eliminacionLogica");

	}

}
