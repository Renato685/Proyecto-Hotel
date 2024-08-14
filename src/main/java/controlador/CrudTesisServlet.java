package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import entity.Alumno;
import entity.Respuesta;
import entity.Tesis;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelTesis;

@WebServlet("/crudTesis")
public class CrudTesisServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		String metodo = req.getParameter("metodo");
		switch(metodo) {
			case "paramLista":     { lista(req, resp); break;}
			case "paramInserta":   { inserta(req, resp); break;}
			case "paramActualiza": { actualiza(req, resp); break;}
			case "paramEFisica":   { eliminacionFisica(req, resp); break;}
			case "paramELogica":   { eliminacionLogica(req, resp);}
		}
	}
	protected void lista(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		System.out.println("[ini] lista  ");
		String filtro = req.getParameter("filtro");
		
		ModelTesis model = new ModelTesis();
		List<Tesis> lista = model.listarTesisPorTitulo(filtro+"%");
		
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		
		System.out.println("[fin] lista  ");
	}
	protected void inserta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[ini] inserta  ");
		
		//1 RECUPERAR DATOS DEL GUI
		String tit= req.getParameter("titulo");
		String tem= req.getParameter("tema");
		String fec= req.getParameter("fechaCreacion");
		String id= req.getParameter("alumno");

				
		//LLENAR OBJTESIS
		Tesis objTesis = new Tesis();
		objTesis.setTitulo(tit);
		objTesis.setTema(tem);
		objTesis.setFechaCreacion(Date.valueOf(fec));
		objTesis.setFechaRegistro(new Date(System.currentTimeMillis()));
		objTesis.setFechaActualizacion(new Date(System.currentTimeMillis()));
		objTesis.setEstado(1);
		
		Alumno objAlumno = new Alumno();
	    objAlumno.setIdAlumno(Integer.parseInt(id));
	       
	    objTesis.setAlumno(objAlumno);
	    
	    //se envia el obj a tesis
	    ModelTesis modelTesis = new ModelTesis();
	    int salida = modelTesis.insertarTesis(objTesis);
	    
	    Respuesta objRespuesta = new Respuesta();
	    
	    if (salida > 0) {
	           List<Tesis> lstDatos = modelTesis.listarTesisPorTitulo("%");
	           objRespuesta.setDatos(lstDatos);
	           objRespuesta.setMensaje("Registro exitoso");
	           
	        }
	    
	    Gson gson = new Gson();
		String json = gson.toJson(objRespuesta);
		
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] inserta  ");
	}
	protected void actualiza(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		System.out.println("[ini] actualiza  ");
		
		System.out.println("[fin] actualiza  ");
	}
	protected void eliminacionFisica(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		System.out.println("[ini] eliminacionFisica  ");
		
		String idTesis = req.getParameter("idTesis");
		Respuesta objRespuesta = new Respuesta();
		ModelTesis modelTesis = new ModelTesis();
		int salida = modelTesis.eliminarTesis(Integer.parseInt(idTesis));
		
		 if (salida > 0) {
	           List<Tesis> lstDatos = modelTesis.listarTesisPorTitulo("%");
	           objRespuesta.setDatos(lstDatos);
	           objRespuesta.setMensaje("Eliminacion exitosa");
	           
	        }
		  Gson gson = new Gson();
		  String json = gson.toJson(objRespuesta);
		  resp.setContentType("application/json;charset=UTF-8");
		  PrintWriter out = resp.getWriter();
		  out.println(json);
			
		System.out.println("[fin] eliminacionFisica  ");
	}
	protected void eliminacionLogica(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		System.out.println("[ini] eliminacionLogica  ");
		
		String idTesis = req.getParameter("idTesis");
		
		ModelTesis modelTesis = new ModelTesis();
		
		Tesis objTesis = modelTesis.listarTesisPorPk(Integer.parseInt(idTesis));
		
		int estadoNuevo = objTesis.getEstado() == 0 ? 1 : 0;
		objTesis.setEstado(estadoNuevo);
		
		modelTesis.actualizarTesis(objTesis);
		
		Respuesta objResupeta = new Respuesta();
		List<Tesis>  lstDatos = modelTesis.listarTesisPorTitulo("%");
		objResupeta.setDatos(lstDatos);
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] eliminacionLogica ");
	}
	
}

