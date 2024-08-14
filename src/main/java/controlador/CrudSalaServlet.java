package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import entity.Respuesta;
import entity.Sala;
import entity.Sede;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelSala;

@WebServlet("/crudSala")
public class CrudSalaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String metodo = req.getParameter("metodo");
		switch (metodo) {
			case "paramLista": { lista(req,resp); break;} 
			case "paramInserta": { inserta(req,resp); break;} 
			case "paramActualiza": { actualiza(req,resp); break;} 
			case "paramELogica": { eliminacionLogica(req,resp); break;} 
			case "paramEFisica": { eliminacionFisica(req,resp);} 
		}
	}
	
	
	protected void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] lista");
		
		String filtro = req.getParameter("filtro");
		
		ModelSala model = new ModelSala();
		List<Sala> lista = model.listaSalaPorNumeroORecursos(filtro + "%");
		
		
		Gson gson = new Gson();
        String json = gson.toJson(lista);

        resp.setContentType("application/json;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.println(json);
		
		System.out.println("[fin] lista");
		
	}
	
	protected void inserta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("[ini] inserta");
		
		String num = req.getParameter("numero");
        int pis = Integer.parseInt(req.getParameter("piso"));
        int nal = Integer.parseInt(req.getParameter("numAlumnos"));
        String rec = req.getParameter("recursos");
        String se = req.getParameter("sede");
        
        Sala objSala = new Sala();
        objSala.setNumero(num);
        objSala.setPiso(pis);
        objSala.setNumAlumnos(nal);
        objSala.setRecursos(rec);
        objSala.setFechaRegistro(new Date(System.currentTimeMillis()));
        objSala.setEstado(1);
        objSala.setFechaActualizacion(new Date(System.currentTimeMillis()));
        
        Sede  objSede = new Sede();
        objSede.setIdSede(Integer.parseInt(se));

        objSala.setSede(objSede);
        
        ModelSala modelSala = new ModelSala();
        int salida = modelSala.insertarSala(objSala);
        
        Respuesta objRespuesta = new Respuesta();

	        if (salida>0) {
				List<Sala>  lstDatos = modelSala.listaSalaPorNumeroORecursos("%");
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
		
		System.out.println("[int] actualiza");
		
		System.out.println("[fin] actualiza");
	}
	protected void eliminacionFisica(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[int] eliminacionFisica");
		
		
		String idSala = req.getParameter("idSala");
		Respuesta objRespuesta = new Respuesta();
		ModelSala modelSala = new ModelSala();
		int salida =modelSala.eliminarSala(Integer.parseInt(idSala));
		if (salida>0) {
			List<Sala>  lstDatos = modelSala.listaSalaPorNumeroORecursos("%");
			objRespuesta.setDatos(lstDatos);
			objRespuesta.setMensaje("Eliminaci\u00f3n existosa");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		
		System.out.println("[fin] eliminacionFisica");
	}
	protected void eliminacionLogica(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[int] eliminacionLogica");
		
		String idSala = req.getParameter("idSala");
		
		ModelSala modelSala = new ModelSala();
		Sala objSala = modelSala.buscaSalaPorPK(Integer.parseInt(idSala));
		int estadoNuevo = objSala.getEstado() == 0 ? 1 : 0;
		objSala.setEstado(estadoNuevo);
		modelSala.actualizarSala(objSala);
		
		Respuesta objRespuesta = new Respuesta();
		List<Sala>  lstDatos = modelSala.listaSalaPorNumeroORecursos("%");
		objRespuesta.setDatos(lstDatos);
		
		Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] eliminacionLogica");
	}
	
	
}
