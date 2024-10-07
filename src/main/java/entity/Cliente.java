package entity;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Cliente {

	private int ID_Usuario ;
	private String Nombre ;
	private String Apellido ;
    private String Documento_Identidad ;
    private String Correo_Electronico ;
    private Date Fecha_Registro ;
    private String Telefono ;
    private String Direccion ;
    private TipoHabitacion tipoHabitacion;


}

/* public class Alumno {

private int idAlumno;
private String nombres;
private String apellidos;
private String telefono;
private String dni;
private String correo;
private Date fechaNacimiento;
private Date fechaRegistro;
private Date fechaActualizacion;
private int estado;
private Pais pais;

}
*/