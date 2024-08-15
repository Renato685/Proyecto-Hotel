package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import entity.Pais;
import entity.Proveedor;
import util.FechaUtil;
import util.MySqlDBConexion;

public class ModelProveedor {

	public int insertarProveedor(Proveedor obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into proveedor values(null,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getRazonsocial());
			pstm.setString(2, obj.getRuc());
			pstm.setString(3, obj.getDireccion());
			pstm.setString(4, obj.getCelular());
			pstm.setString(5, obj.getContacto());
			pstm.setInt(6, obj.getEstado());
			pstm.setDate(7, obj.getFechaRegistro());
			pstm.setDate(8, obj.getFechaActualizacion());
			pstm.setInt(9, obj.getPais().getIdPais());
			
			System.out.println("SQL => " + pstm);

			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public int eliminarProveedor(int idProveedor) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "delete from proveedor  where idProveedor= ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idProveedor);

			System.out.println("SQL => " + pstm);
			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public int actualizarProveedor(Proveedor obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update proveedor set razonsocial = ?,ruc = ?, direccion = ?, celular = ?, contacto=?,  "
									 + "estado=?, fechaRegistro = ?, fechaActualizacion = ?,  idPais = ? "
									 + " where idProveedor=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, obj.getIdProveedor());
			pstm.setString(1, obj.getRazonsocial());
			pstm.setString(2, obj.getRuc());
			pstm.setString(3, obj.getDireccion());
			pstm.setString(4, obj.getCelular());
			pstm.setString(5, obj.getContacto());
			pstm.setInt(6, obj.getEstado());
			pstm.setDate(7, obj.getFechaRegistro());
			pstm.setDate(8, obj.getFechaActualizacion());
			pstm.setInt(9, obj.getPais().getIdPais());
			pstm.setInt(10, obj.getIdProveedor());
			

			System.out.println("SQL => " + pstm);

			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public List<Proveedor> listaProveedorPorRazonSocialODireccion(String filtro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Proveedor > lstSalida = new ArrayList<Proveedor>();
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select e.*, p.nombre from proveedor e inner join pais p on e.idPais = p.idPais where e.razonSocial like ? or e.direccion like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);
			pstm.setString(2, filtro);
		

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Proveedor  objProveedor ;
			Pais objPais;
			while(rs.next()) {
				objProveedor = new Proveedor ();
				objProveedor.setIdProveedor (rs.getInt(1));
				objProveedor.setRazonsocial(rs.getString(2));
				objProveedor.setRuc(rs.getString(3));
				objProveedor.setDireccion(rs.getString(4));
				objProveedor.setCelular(rs.getString(5));
				objProveedor.setContacto(rs.getString(6));
				objProveedor.setEstado(rs.getInt(7));
				objProveedor.setFechaRegistro(rs.getDate(8));
				objProveedor.setFechaActualizacion(rs.getDate(9));
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objProveedor.setPais(objPais);
				lstSalida.add(objProveedor );
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {}
		}
		return lstSalida;
	}
	
	public Proveedor buscaProveedorPorPK(int idProveedor ) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Proveedor objProveedor  = null;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select e.*, p.nombre from proveedor e inner join pais p on e.idPais = p.idPais where e.idProveedor = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idProveedor );

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Pais objPais;
			if(rs.next()) {
				objProveedor = new Proveedor ();
				objProveedor.setIdProveedor (rs.getInt(1));
				objProveedor.setRazonsocial(rs.getString(2));
				objProveedor.setRuc(rs.getString(3));
				objProveedor.setDireccion(rs.getString(4));
				objProveedor.setCelular(rs.getString(5));
				objProveedor.setContacto(rs.getString(6));
				objProveedor.setEstado(rs.getInt(7));
				objProveedor.setFechaRegistro(rs.getDate(8));
				objProveedor.setFechaActualizacion(rs.getDate(9));
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objProveedor.setPais(objPais);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {}
		}
		return objProveedor ;
	}
	
}