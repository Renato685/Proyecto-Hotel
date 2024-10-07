package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.TipoHabitacion;
import util.MySqlDBConexion;

public class ModelTipoHabitacion  {
	
	
	public List<TipoHabitacion> lista() {
		List<TipoHabitacion> salida = new ArrayList<TipoHabitacion>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from tipo_habitacion order by Nombre_Tipo asc";
			pstm = conn.prepareStatement(sql);
			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			TipoHabitacion obj = null;
			while(rs.next()) {
				obj = new TipoHabitacion();
				obj.setID_Tipo_Habitacion(rs.getInt(1));
				obj.setNombre_Tipo(rs.getString(2));
				salida.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
			}
		}
		return salida;
	}
}

