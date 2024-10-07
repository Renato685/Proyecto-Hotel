package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Client;

import entity.Cliente;
import lombok.extern.apachecommons.CommonsLog;
import util.MySqlDBConexion;


@CommonsLog
public class ModelCliente {

	public int insertarCliente(Cliente obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "insert into Cliente values(null,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getApellido());
			pstm.setString(3, obj.getTelefono());
			pstm.setString(4, obj.getDocumento_Identidad());
			pstm.setString(5, obj.getCorreo_Electronico());
			pstm.setInt(6, obj.getTipoHabitacion().getID_Tipo_Habitacion());

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