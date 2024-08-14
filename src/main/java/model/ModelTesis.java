package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import entity.Alumno;
import entity.Tesis;
import util.MySqlDBConexion;

public class ModelTesis {

	public int insertarTesis(Tesis obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into tesis values(null,?,?,?,?,?,?,?)"; /// ojo a esta linea
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getTema());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setDate(4, obj.getFechaRegistro());
			pstm.setDate(5, obj.getFechaActualizacion());
			pstm.setInt(6, obj.getEstado());
			pstm.setInt(7,  obj.getAlumno().getIdAlumno());
		
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
	
	public int eliminarTesis(int idTesis) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "delete from tesis where idTesis = ?"; /// ojo 
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idTesis);
			
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

	public int actualizarTesis(Tesis obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = " update tesis set titulo = ?,tema = ?,fechaCreacion = ?, "  
					   + " fechaRegistro = ?,fechaActualizacion = ?, estado = ?, idAlumno = ? " 
						+ " where idTesis = ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getTema());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setDate(4, obj.getFechaRegistro());
			pstm.setDate(5, obj.getFechaActualizacion());
			pstm.setInt(6, obj.getEstado());
			pstm.setInt(7,  obj.getAlumno().getIdAlumno());
			pstm.setInt(8,  obj.getIdTesis());
			
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
	
	public List<Tesis> listarTesisPorTitulo(String filtro) {
	    Connection conn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    ArrayList<Tesis> lstSalida = new ArrayList<Tesis>();
	    try {
	        conn = MySqlDBConexion.getConexion();
	        
	        String sql = "select * from tesis";
	        if (filtro != null && !filtro.trim().isEmpty()) {
	            sql += " where titulo LIKE ?";
	            pstm = conn.prepareStatement(sql);
	            pstm.setString(1, "%" + filtro + "%");
	        } else {
	            pstm = conn.prepareStatement(sql);
	        }
	        
	        System.out.println("SQL completa => " + pstm.toString());
	        
	        rs = pstm.executeQuery();

	        Tesis objTesis;
	        Alumno objAlumno;
	        while (rs.next()) {
	            objTesis = new Tesis();
	            objTesis.setIdTesis(rs.getInt(1));  // Estaba hardcodeado en 1, cambiar a rs.getInt(1)
	            objTesis.setTitulo(rs.getString(2));
	            objTesis.setTema(rs.getString(3));
	            objTesis.setFechaCreacion(rs.getDate(4));
	            objTesis.setFechaRegistro(rs.getDate(5));
	            objTesis.setFechaActualizacion(rs.getDate(6));
	            objTesis.setEstado(rs.getInt(7));

	            objAlumno = new Alumno();
	            objAlumno.setIdAlumno(rs.getInt(8));
	            objTesis.setAlumno(objAlumno);

	            lstSalida.add(objTesis);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstm != null) pstm.close();
	            if (conn != null) conn.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    return lstSalida;
	}
	
	public Tesis listarTesisPorPk(int idTesis) {
	    Connection conn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    Tesis objTesis = null;
	    try {
	        conn = MySqlDBConexion.getConexion();
	        
	        String sql = "select * from tesis where idTesis = ?";
	        pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, idTesis);
	        
	        System.out.println("SQL completa => " + pstm.toString());
	        
	        rs = pstm.executeQuery();

	        Alumno objAlumno;
	        if (rs.next()) {
	            objTesis = new Tesis();
	            objTesis.setIdTesis(rs.getInt(1));
	            objTesis.setTitulo(rs.getString(2));
	            objTesis.setTema(rs.getString(3));
	            objTesis.setFechaCreacion(rs.getDate(4));
	            objTesis.setFechaRegistro(rs.getDate(5));
	            objTesis.setFechaActualizacion(rs.getDate(6));
	            objTesis.setEstado(rs.getInt(7));

	            objAlumno = new Alumno();
	            objAlumno.setIdAlumno(rs.getInt(8));
	            objTesis.setAlumno(objAlumno);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstm != null) pstm.close();
	            if (conn != null) conn.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    return objTesis;
	}
}

	