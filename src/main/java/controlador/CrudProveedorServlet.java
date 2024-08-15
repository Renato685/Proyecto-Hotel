package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;


import entity.Pais;
import entity.Proveedor;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelProveedor;

@WebServlet("/crudProveedor")
public class CrudProveedorServlet extends HttpServlet{
	
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

		ModelProveedor model = new ModelProveedor();
		List<Proveedor> lista = model.listaProveedorPorRazonSocialODireccion(filtro + "%");

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
		String ruc = req.getParameter("ruc");
		String dir = req.getParameter("direccion");
		String cel = req.getParameter("celular");
		String cont = req.getParameter("contacto");
		String estado = req.getParameter("estado");
		String fecReg = req.getParameter("fechaRegistro");
		String fecAc = req.getParameter("fechaActualizacion");
		String pai = req.getParameter("pais");
		
		Proveedor objProveedor= new Proveedor();
		objProveedor.setRazonsocial(razSoc);
		objProveedor.setRuc(ruc);
		objProveedor.setDireccion(dir);
		objProveedor.setCelular(cel);
		objProveedor.setContacto(cont);
		objProveedor.setEstado(1);
		objProveedor.setFechaRegistro(new Date(System.currentTimeMillis()));
		objProveedor.setFechaActualizacion(new Date(System.currentTimeMillis()));
		
		
        Pais  objPais = new Pais();
        objPais.setIdPais(Integer.parseInt(pai));
        objProveedor.setPais(objPais);
        
        ModelProveedor modelProveedor= new ModelProveedor();
        int salida = modelProveedor.insertarProveedor(objProveedor);
        
        Respuesta objRespuesta = new Respuesta();

		if (salida>0) {
			List<Proveedor>  lstDatos = modelProveedor.listaProveedorPorRazonSocialODireccion("%");
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

		String idProveedor= req.getParameter("idProveedor");
		Respuesta objResupeta = new Respuesta();
		ModelProveedor modelProveedor= new ModelProveedor();
		int salida = modelProveedor.eliminarProveedor(Integer.parseInt(idProveedor));
		if (salida>0) {
			List<Proveedor>  lstDatos = modelProveedor.listaProveedorPorRazonSocialODireccion("%");
			objResupeta.setDatos(lstDatos);
			objResupeta.setMensaje("Eliminación existosa.");
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

		String idProveedor = req.getParameter("idProveedor");
		
		ModelProveedor modelProveedor = new ModelProveedor();
		Proveedor objProveedor = modelProveedor.buscaProveedorPorPK(Integer.parseInt(idProveedor));
		int estadoNuevo = objProveedor.getEstado() == 0 ? 1 : 0;
		objProveedor.setEstado(estadoNuevo);
		modelProveedor.actualizarProveedor(objProveedor);
		
		Respuesta objResupeta = new Respuesta();
		List<Proveedor>  lstDatos = modelProveedor.listaProveedorPorRazonSocialODireccion("%");
		objResupeta.setDatos(lstDatos);
		
		Gson gson = new Gson();
		String json = gson.toJson(objResupeta);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(json);
		
		System.out.println("[fin] eliminacionLogica");

	}

}