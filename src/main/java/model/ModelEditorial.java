package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entity.Editorial;
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
}
