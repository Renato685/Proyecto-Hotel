package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Editorial;
import entity.Pais;
import util.FechaUtil;
import util.MySqlDBConexion;

public class ModelEditorial {

	public int insertarEditorial(Editorial obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into editorial values(null,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getRazonSocial());
			pstm.setString(2, obj.getDireccion());
			pstm.setInt(3, obj.getTelefono());
			pstm.setString(4, obj.getRuc());
			pstm.setDate(5, obj.getFechaCreacion());
			pstm.setDate(6, obj.getFechaRegistro());
			pstm.setDate(7, obj.getFechaActualizacion());
			pstm.setInt(8, obj.getEstado());
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
	
	public int eliminarEditorial(int idEditorial) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "delete from editorial where idEditorial = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idEditorial);

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
	
	public int actualizarEditorial(Editorial obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update editorial set razonsocial = ?, direccion = ?, telefono = ?, ruc = ?, fechaCreacion = ?, "
									 + " fechaRegistro = ?, fechaActualizacion = ?, estado = ?, idPais = ? "
									 + " where idEditorial=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getRazonSocial());
			pstm.setString(2, obj.getDireccion());
			pstm.setInt(3, obj.getTelefono());
			pstm.setString(4, obj.getRuc());
			pstm.setDate(5, obj.getFechaCreacion());
			pstm.setDate(6, obj.getFechaRegistro());
			pstm.setDate(7, obj.getFechaActualizacion());
			pstm.setInt(8, obj.getEstado());
			pstm.setInt(9, obj.getPais().getIdPais());
			pstm.setInt(10, obj.getIdEditorial());

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
	
	public List<Editorial> listaEditorialPorRazonSocialODireccion(String filtro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Editorial> lstSalida = new ArrayList<Editorial>();
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select e.*, p.nombre from editorial e inner join pais p on e.idEditorial = p.idPais where e.razonSocial like ? or e.direccion like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);
			pstm.setString(2, filtro);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Editorial objEditorial;
			Pais objPais;
			while(rs.next()) {
				objEditorial = new Editorial();
				objEditorial.setIdEditorial(rs.getInt(1));
				objEditorial.setRazonSocial(rs.getString(2));
				objEditorial.setDireccion(rs.getString(3));
				objEditorial.setTelefono(rs.getInt(4));
				objEditorial.setRuc(rs.getString(5));
				objEditorial.setFechaCreacion(rs.getDate(6));
				objEditorial.setFechaCreacionFormateada(FechaUtil.getFechaFormateadaYYYYMMdd(rs.getDate(6)));
				objEditorial.setFechaRegistro(rs.getDate(7));
				objEditorial.setFechaActualizacion(rs.getDate(8));
				objEditorial.setEstado(rs.getInt(9));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objEditorial.setPais(objPais);
				lstSalida.add(objEditorial);
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
	
	public Editorial buscaEditorialPorPK(int idEditorial) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Editorial objEditorial = null;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select e.*, p.nombre from editorial e inner join pais p on e.idEditorial = p.idPais where e.idEditorial = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idEditorial);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Pais objPais;
			if(rs.next()) {
				objEditorial = new Editorial();
				objEditorial.setIdEditorial(rs.getInt(1));
				objEditorial.setRazonSocial(rs.getString(2));
				objEditorial.setDireccion(rs.getString(3));
				objEditorial.setTelefono(rs.getInt(4));
				objEditorial.setRuc(rs.getString(5));
				objEditorial.setFechaCreacion(rs.getDate(6));
				objEditorial.setFechaCreacionFormateada(FechaUtil.getFechaFormateadaYYYYMMdd(rs.getDate(6)));
				objEditorial.setFechaRegistro(rs.getDate(7));
				objEditorial.setFechaActualizacion(rs.getDate(8));
				objEditorial.setEstado(rs.getInt(9));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objEditorial.setPais(objPais);
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
		return objEditorial;
	}
	
}
