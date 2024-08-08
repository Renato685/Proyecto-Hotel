package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Alumno;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;

@CommonsLog
public class ModelAlumno {

	public int insertarAlumno(Alumno obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into alumno values(null,?,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setString(3, obj.getTelefono());
			pstm.setString(4, obj.getDni());
			pstm.setString(5, obj.getCorreo());
			pstm.setDate(6, obj.getFechaNacimiento());
			pstm.setDate(7, obj.getFechaRegistro());
			pstm.setDate(8, obj.getFechaActualizacion());
			pstm.setInt(9, obj.getEstado());
			pstm.setInt(10, obj.getPais().getIdPais());

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

	public List<Alumno> listaXNombresIguales(String nombres, String apellidos) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Alumno> lstSalida = new ArrayList<Alumno>();
		try {
			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM alumno where nombres = ? and  apellidos  = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nombres);
			pstm.setString(2, apellidos);
			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Alumno objAlumno;
			while (rs.next()) {
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt(1));
				objAlumno.setNombres(rs.getString(2));
				lstSalida.add(objAlumno);

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

	public List<Alumno> listaXTelefonoIguales(String telefono) {
		Connection conn = null;

		PreparedStatement pstm = null;

		ResultSet rs = null;

		List<Alumno> lstSalida = new ArrayList<Alumno>();

		try {

			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM alumno where telefono = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, telefono);
			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Alumno objAlumno;
			while (rs.next()) {
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt(1));
				objAlumno.setTelefono(rs.getString(2));

				lstSalida.add(objAlumno);

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

	public List<Alumno> listaXDNIIguales(String dni) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Alumno> lstSalida = new ArrayList<Alumno>();

		try {

			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el sql
			String sql = "SELECT * FROM alumno where dni = ? ";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dni);

			System.out.println("SQL => " + pstm);

			// 3 Se ejcuta el SQL
			rs = pstm.executeQuery();

			Alumno objAlumno;
			while (rs.next()) {
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt(1));
				objAlumno.setDni(rs.getString(2));

				lstSalida.add(objAlumno);

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
	
	public List<Alumno> listaAlumno(){
		List<Alumno> salida = new ArrayList<Alumno>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "select * from alumno";
			psmt = conn.prepareStatement(sql);

			System.out.println("SQL => " +psmt);
			
			rs = psmt.executeQuery();
			
			Alumno obj = null;
			while (rs.next()) {
				obj = new Alumno();
				obj.setIdAlumno(rs.getInt(1));
				obj.setNombres(rs.getString(2));
				obj.setApellidos(rs.getString(3));
				obj.setTelefono(rs.getString(4));
				obj.setDni(rs.getString(5));
				obj.setCorreo(rs.getString(6));
				obj.setFechaNacimiento(rs.getDate(7));
				salida.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)psmt.close();
				if (conn != null)conn.close();
			} catch (Exception e2) {
				}
		}
		return salida;
		
	}


}
