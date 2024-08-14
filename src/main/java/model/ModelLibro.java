package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import entity.Categoria;
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
	
	public int eliminarLibro(int idLibro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "delete from libro where idLibro = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idLibro);

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
	
	
	public int actualizarLibro(Libro obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = " update libro set titulo = ?, anio = ?, serie = ?, tema = ?, "
									 + " fechaRegistro = ?, fechaActualizacion = ?, estado = ?, idCategoria = ? "
									 + " where idLibro = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setInt(2, obj.getAnio());
			pstm.setString(3, obj.getSerie());
			pstm.setString(4, obj.getTema());
			pstm.setDate(5, obj.getFechaRegistro());
			pstm.setDate(6, obj.getFechaActualizacion());
			pstm.setInt(7, obj.getEstado());
			pstm.setInt(8, obj.getCategoria().getIdCategoria());
			pstm.setInt(9, obj.getIdLibro());

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
	
	public List<Libro> listaLibroPorNombres(String filtro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Libro> lstSalida = new ArrayList<Libro>();
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = " select c.*, p.descripcion from libro c inner join categoria_libro p on c.idCategoria = p.idCategoria where c.titulo like ? or c.anio like ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);
			pstm.setString(2, filtro);

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			Libro objLibro;
			Categoria objCategoria;
			while(rs.next()) {
				objLibro = new Libro();
				objLibro.setIdLibro(rs.getInt(1));
				objLibro.setTitulo(rs.getString(2));
				objLibro.setAnio(rs.getInt(3));
				objLibro.setSerie(rs.getString(4));
				objLibro.setTema(rs.getString(5));				
				objLibro.setFechaRegistro(rs.getDate(6));
				objLibro.setFechaActualizacion(rs.getDate(7));
				objLibro.setEstado(rs.getInt(8));
				
				objCategoria = new Categoria();
				objCategoria.setIdCategoria(rs.getInt(9));
				objCategoria.setDescripcion(rs.getString(10));
				objLibro.setCategoria(objCategoria);
				lstSalida.add(objLibro);
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
	
	
	
	public Libro buscaLibroPorPK(int idLibro) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Libro objLibro = null;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = " select c.*, p.descripcion from libro c inner join categoria_libro p on c.idCategoria = p.idCategoria where c.idLibro =  ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idLibro);
			

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			
			Categoria objCategoria;
			if(rs.next()) {
				objLibro = new Libro();
				objLibro.setIdLibro(rs.getInt(1));
				objLibro.setTitulo(rs.getString(2));
				objLibro.setAnio(rs.getInt(3));
				objLibro.setSerie(rs.getString(4));
				objLibro.setTema(rs.getString(5));				
				objLibro.setFechaRegistro(rs.getDate(6));
				objLibro.setFechaActualizacion(rs.getDate(7));
				objLibro.setEstado(rs.getInt(8));
				
				objCategoria = new Categoria();
				objCategoria.setIdCategoria(rs.getInt(9));
				objCategoria.setDescripcion(rs.getString(10));
				objLibro.setCategoria(objCategoria);
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
		return objLibro;
	}

}
