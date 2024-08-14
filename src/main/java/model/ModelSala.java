package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Sala;
import entity.Sede;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class ModelSala {
	
	public int insertarSala(Sala obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into sala values(null,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNumero());
			pstm.setInt(2, obj.getPiso());
			pstm.setInt(3, obj.getNumAlumnos());
			pstm.setString(4, obj.getRecursos());
			pstm.setDate(5, obj.getFechaRegistro());
			pstm.setInt(6, obj.getEstado());
			pstm.setDate(7, obj.getFechaActualizacion());
			pstm.setInt(8, obj.getSede().getIdSede());

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
	
	public int eliminarSala(int idSala) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "delete from sala where idSala = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSala);

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
	
	public int actualizarSala(Sala obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "update sala set numero = ?, piso = ?, numAlumnos = ?, recursos = ?, "+ " estado = ?, fechaActualizacion = ?, idSede = ? " + " where idSala=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNumero());
			pstm.setInt(2, obj.getPiso());
			pstm.setInt(3, obj.getNumAlumnos());
			pstm.setString(4, obj.getRecursos());
			pstm.setInt(5, obj.getEstado());
			pstm.setDate(6, obj.getFechaActualizacion());
			pstm.setInt(7, obj.getSede().getIdSede());
			pstm.setInt(8, obj.getIdSala());
			

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
	
	public List<Sala> listaSalaPorNumeroORecursos(String filtro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Sala> lstSalida = new ArrayList<Sala>();
	
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select s.*, se.nombre from sala s inner join sede se on s.idSede = se.idSede where s.numero like ? or s.recursos like ?";
			
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);
			pstm.setString(2, filtro);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			
			Sala objSala;
			Sede objSede;
			
			while(rs.next()) {
				objSala = new Sala();
				objSala.setIdSala(rs.getInt(1));
				objSala.setNumero(rs.getString(2));
				objSala.setPiso(rs.getInt(3));
				objSala.setNumAlumnos(rs.getInt(4));
				objSala.setRecursos(rs.getString(5));
				objSala.setFechaRegistro(rs.getDate(6));
				objSala.setEstado(rs.getInt(7));
				objSala.setFechaActualizacion(rs.getDate(8));
				
				objSede = new Sede();
				objSede.setIdSede(rs.getInt(9));
				objSede.setNombre(rs.getString(10));
				objSala.setSede(objSede);
				lstSalida.add(objSala);
				
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
	
	public Sala buscaSalaPorPK(int idSala) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Sala objSala =null;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select s.*, se.nombre from sala s inner join sede se on s.idSede = se.idSede where s.idSala = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSala);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Sede objSede;
			if (rs.next()) {
				objSala = new Sala();
				objSala.setIdSala(rs.getInt(1));
				objSala.setNumero(rs.getString(2));
				objSala.setPiso(rs.getInt(3));
				objSala.setNumAlumnos(rs.getInt(4));
				objSala.setRecursos(rs.getString(5));
				objSala.setFechaRegistro(rs.getDate(6));
				objSala.setEstado(rs.getInt(7));
				objSala.setFechaActualizacion(rs.getDate(8));
				
				objSede = new Sede();
				objSede.setIdSede(rs.getInt(9));
				objSede.setNombre(rs.getString(10));
				objSala.setSede(objSede);
				
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
		return objSala;
	}
}
