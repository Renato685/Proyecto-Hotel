package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Tesis;
import util.MySqlDBConexion;

public class ModelTesis {

	public int insertarTesis(Tesis obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into tesis values(null,?,?,?,?)"; /// ojo a esta linea
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getTema());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setInt(4,  obj.getAlumno().getIdAlumno());
			/*
			pstm.setString(4, obj.getDni());
			pstm.setString(5, obj.getCorreo());
			pstm.setDate(6, obj.getFechaNacimiento());
			pstm.setDate(7, obj.getFechaRegistro());
			pstm.setDate(8, obj.getFechaActualizacion());
			pstm.setInt(9, obj.getEstado());
			*/
			
			
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
	
}