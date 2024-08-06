package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

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

//Autor: Oscar Morales

@WebServlet("/registraEditorial")
public class RegistraEditorialServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
