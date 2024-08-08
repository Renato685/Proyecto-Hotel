package entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tesis {

	private int idTesis;
	private String titulo; 
	private String tema;
	private Date fechaCreacion;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private int estado;
	private Alumno alumno;    ///  foranea 
	

	
}
