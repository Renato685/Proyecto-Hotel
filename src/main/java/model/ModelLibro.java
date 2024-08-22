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
				
				
				
				
				

				System.out.println("SQL => " + pstm);
				rs = pstm.executeQuery();
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
	
	
	public List<Libro> listaLibroComplejo(String titulo,int anio,String serie,String tema) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Libro> lstSalida = new ArrayList<Libro>();
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = "select l.*, c.descripcion  from libro l inner join categoria_libro c on l.idCategoria = c.idCategoria "
		            + "where (? = '' or l.titulo like ? ) and "
		            + "( ? = -1 or l.anio = ?) and "
		            + "( ? = '' or l.serie = ?) and "
		            + "( ? = '' or l.tema = ?)";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, titulo);
			pstm.setString(2, titulo);
			pstm.setInt(3, anio);
			pstm.setInt(4, anio);
			pstm.setString(5, serie);
			pstm.setString(6, serie);
			pstm.setString(7, tema);
			pstm.setString(8, tema);



			

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
	
	
	public boolean existeLibroPorTitulo(String titulo) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean existe = false;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = " select titulo from libro where titulo =? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, titulo);
			

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			if(rs.next()) {
				
				existe = true;
				
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
		return existe;
	}
	
	public boolean existeLibroPorSerie(String serie) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean existe = false;
		try {

			conn = MySqlDBConexion.getConexion();
			String sql = " select serie from libro where serie =? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, serie);
			

			System.out.println("SQL => " + pstm);
			rs = pstm.executeQuery();
			if(rs.next()) {
				
				existe = true;
				
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
		return existe;
	}
	
	
	

}
