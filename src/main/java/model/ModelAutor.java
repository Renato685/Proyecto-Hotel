package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Autor;
import entity.Grado;
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

	public List<Autor> listaAutorPorNombres(String filtro) {
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    ArrayList<Autor> lstSalida = new ArrayList<Autor>();
    try {

        conn = MySqlDBConexion.getConexion();
        String sql = "select a.*, p.descripcion from autor a inner join grado_autor p on a.idGrado = p.idGrado where a.nombres like ? or a.apellidos like ?";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, "%" + filtro + "%");
        pstm.setString(2, "%" + filtro + "%");

        System.out.println("SQL => " + pstm);
        rs = pstm.executeQuery();
        
        while(rs.next()) {
            Autor objAutor = new Autor();
            objAutor.setIdAutor(rs.getInt("idAutor"));
            objAutor.setNombres(rs.getString("nombres"));
            objAutor.setApellidos(rs.getString("apellidos"));
            objAutor.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            objAutor.setFechaActualizacion(rs.getDate("fechaActualizacion"));
            objAutor.setTelefono(rs.getString("telefono"));
            objAutor.setFechaRegistro(rs.getDate("fechaRegistro"));
            objAutor.setEstado(rs.getInt("estado"));

            Grado objGrado = new Grado();
            objGrado.setIdGrado(rs.getInt("idGrado"));
            objGrado.setDescripcion(rs.getString("descripcion"));
            
            objAutor.setGrado(objGrado);
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

        } catch (Exception e2) {}
    }
    return lstSalida;
}

	public int eliminarAutor(int idAutor) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "delete from autor where idAutor = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idAutor);

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
	public Autor buscaAutorPorPK(int idAutor) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Autor objAutor =null;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select a.*, p.descripcion from autor a inner join grado_autor p on a.idGrado = p.idGrado where a.idAutor = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idAutor);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Grado objGrado;
			if(rs.next()) {
				objAutor.setIdAutor(rs.getInt(1));
				objAutor.setNombres(rs.getString(2));
				objAutor.setApellidos(rs.getString(3));
				objAutor.setFechaNacimiento(rs.getDate(4));
				objAutor.setFechaActualizacion(rs.getDate(5));
				objAutor.setTelefono(rs.getString(6));
				objAutor.setFechaRegistro(rs.getDate(7));
				objAutor.setEstado(rs.getInt(8));
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(9));
				objGrado.setDescripcion(rs.getString(10));
				objAutor.setGrado(objGrado);
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
		return objAutor;
	}
	public int actualizarAutor(Autor obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update autor set nombres = ?, apellidos = ?, fechaNacimiento = ?, telefono = ?"
									 + "  fechaActualizacion = ?, estado = ?, idGrado = ? "
									 + " where idAutor=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setDate(3, obj.getFechaNacimiento());
			pstm.setString(4, obj.getTelefono());
			pstm.setDate(5, obj.getFechaActualizacion());
			pstm.setInt(6, obj.getEstado());
			pstm.setInt(7, obj.getGrado().getIdGrado());
			pstm.setInt(8, obj.getIdAutor());

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
