package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Modalidad;
import entity.Revista;
import util.MySqlDBConexion;

public class ModelRevista {
	public int insertarRevista(Revista obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into revista values(null,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getFrecuencia());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setDate(4, obj.getFechaRegistro());
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getModalidad().getIdModalidad());

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
	
	public int eliminaRevista(int idRevista) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "delete from revista where idRevista = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idRevista);

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
	
	public int actualizarRevista(Revista obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update revista set nombre = ?, frecuencia = ?,  "
									 + " fechaCreacion = ?, fechaRegistro = ?, estado = ?, idModalidad = ? "
									 + " where idRevista=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getFrecuencia());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setDate(4, obj.getFechaRegistro());
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getModalidad().getIdModalidad());
			pstm.setInt(7, obj.getIdRevista());

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
	
	public List<Revista> listaRevistaPorNombre(String filtro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Revista> lstSalida = new ArrayList<Revista>();
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select a.*, p.descripcion from revista a inner join modalidad p on a.idModalidad = p.idModalidad where a.nombre like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Revista objRevista;
			Modalidad objModalidad;
			while(rs.next()) {
				objRevista = new Revista();
				objRevista.setIdRevista(rs.getInt(1));
				objRevista.setNombre(rs.getString(2));
				objRevista.setFrecuencia(rs.getString(3));
				objRevista.setFechaCreacion(rs.getDate(4));
				objRevista.setFechaRegistro(rs.getDate(5));
				objRevista.setEstado(rs.getInt(6));
				
				
				objModalidad = new Modalidad();
				objModalidad.setIdModalidad(rs.getInt(7));
				objModalidad.setDescripcion(rs.getString(8));
				objRevista.setModalidad(objModalidad);
				lstSalida.add(objRevista);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();

			} catch (Exception e2) {}
		}
		return lstSalida;
	}
	
	public Revista buscaRevistaPorPK(int idRevista) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Revista objRevista =null;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select a.*, p.descripcion from revista a inner join modalidad p on a.idModalidad = p.idModalidad where a.idRevista = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idRevista);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Modalidad objModalidad;
			if(rs.next()) {
				objRevista = new Revista();
				objRevista.setIdRevista(rs.getInt(1));
				objRevista.setNombre(rs.getString(2));
				objRevista.setFrecuencia(rs.getString(3));
				objRevista.setFechaCreacion(rs.getDate(4));
				objRevista.setFechaRegistro(rs.getDate(5));
				objRevista.setEstado(rs.getInt(6));
				
				
				objModalidad = new Modalidad();
				objModalidad.setIdModalidad(rs.getInt(7));
				objModalidad.setDescripcion(rs.getString(8));
				objRevista.setModalidad(objModalidad);
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
		return objRevista;
	}
	
}
