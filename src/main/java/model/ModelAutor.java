package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.Autor;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;


@CommonsLog
public class ModelAutor {

	public int insertarAutor(Autor obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into autor values(null,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setDate(3, obj.getFechaNacimiento());
			pstm.setDate(4, obj.getFechaActualizacion());
			pstm.setString(5, obj.getTelefono());
			pstm.setDate(6, obj.getFechaRegistro());			
			pstm.setInt(7, obj.getEstado());
			pstm.setInt(8, obj.getGrado().getIdGrado());

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

	public List<Autor> listaXNombresIguales(String nombres, String apellidos) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Autor> lstSalida = new ArrayList<Autor>();
		try {
			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM autor where nombres = ? and  apellidos  = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Autor objAutor;
			while (rs.next()) {
				objAutor = new Autor();
				objAutor.setIdAutor(rs.getInt(1));
				objAutor.setNombres(rs.getString(2));
				lstSalida.add(objAutor);

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
			} catch (Exception e2) {
			}
		}
		return lstSalida;
	}

	public List<Autor> listaXTelefonoIguales(String telefono) {
		Connection conn = null;

		PreparedStatement pstm = null;

		ResultSet rs = null;

		List<Autor> lstSalida = new ArrayList<Autor>();

		try {

			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM autor where telefono = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, telefono);
			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Autor objAutor;
			while (rs.next()) {
				objAutor = new Autor();
				objAutor.setIdAutor(rs.getInt(1));
				objAutor.setTelefono(rs.getString(2));

				lstSalida.add(objAutor);

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
			} catch (Exception e2) {
			}
		}
		return lstSalida;

	}

}
