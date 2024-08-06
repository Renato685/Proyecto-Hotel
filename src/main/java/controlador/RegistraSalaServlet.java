package controlador;

import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.google.gson.Gson;

import entity.Sala;
import entity.Sede;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelSala;

@WebServlet("/registraSala")
//Autor: Espinoza Talavera Juan
public class RegistraSalaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String num = req.getParameter("numero");
        int pis = Integer.parseInt(req.getParameter("piso"));
        int nal = Integer.parseInt(req.getParameter("numalumnos"));
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
