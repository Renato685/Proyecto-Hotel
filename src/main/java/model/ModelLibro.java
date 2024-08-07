package model;

import java.sql.Connection;
import java.sql.PreparedStatement;


import entity.Libro;
import util.MySqlDBConexion;

public class ModelLibro {
	
	public int insertarLibro(Libro obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into libro values(null,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setInt(2, obj.getAnio());
			pstm.setString(3, obj.getSerie());
			pstm.setString(4, obj.getTema());
			pstm.setDate(5, obj.getFechaRegistro());
			pstm.setDate(6, obj.getFechaActualizacion());
			pstm.setInt(7, obj.getEstado());
			pstm.setInt(8, obj.getCategoria().getIdCategoria());
			

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
