package entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Editorial {

	private int idEditorial;
	private String razonSocial;
	private String direccion;
	private int telefono;
	private String ruc;
	private Date fechaCreacion;
	private Date fechaRegistro;
	private Date fechaActualizacion;
	private int estado;
	private Pais pais;

}
