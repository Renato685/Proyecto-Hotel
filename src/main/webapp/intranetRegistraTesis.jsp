<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>

<title>Sistemas - Jorge Jacinto Gutarra</title>
</head>
<body>
<jsp:include page="intranetCabecera.jsp" />
	<div class="container" style="margin-top: 4%">
	<h4>Registra Tesis</h4>
	
	<form id="id_form"> 
		<div class="row" style="margin-top: 5%">
			<div class="form-group col-md-12">
				<label class="control-label" for="id_titulo">Título</label>
				<input class="form-control" type="text" id="id_titulo" name="titulo" placeholder="Ingrese el título">	
			</div>
		</div>
		<div class="row" style="margin-top:2%">
			<div class="form-group col-md-12">
				<label class="control-label" for="id_tema">Tema</label>
				<input class="form-control" type="text" id="id_tema" name="tema" placeholder="Ingrese el tema">	
		
			</div>
		</div>
		<div class="row" style="margin-top:2%">
			<div class="form-group col-md-12">
				<label class="control-label" for="id_fechaCreacion">Fecha de creación</label>
				<input class="form-control" type="date" id="id_fechaCreacion" name="fechaCreacion" >	
		
			</div>
		</div>
		
		<div class="row" style="margin-top:2%">
			<div class="form-group col-md-12">
				 <label for="id_alumno" class="control-label">Alumno</label>
                        <select class="form-control" id="id_alumno" name="alumno">
                            <option value="">[Seleccione]</option>
                        </select>
		
			</div>
		</div>
		
		
		<div class="row">
			<div class="form-group col-md-12" align="center" style="margin-top:2%">
				<button type="button" class="btn btn-primary" id="id_btn_registrar">Registrar</button>	
		
			</div>
		</div>
	
	</form>
	
	</div>
	<script type="text/javascript">
	  $.getJSON("cargaAlumno", {}, function(data) {
	        $.each(data, function(index, item) {
	            $("#id_alumno").append("<option value=" +  item.idAlumno +" >" + item.nombres + item.apellidos + "</option>");
	        });
	    });
	  
$(document)
.ready(function(){
	$('#id_form').bootstrapValidator(
                {
                    message : 'This value is not valid',
                    feedbackIcons : {
                        valid : 'glyphicon glyphicon-ok',
                        invalid : 'glyphicon glyphicon-remove',
                        validating : 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        nombres: {
                        	selector: "#id_titulo",
                            validators: {
                                notEmpty: {
                                    message: 'El titulo es requerido'
                                },
                            }
                        },
                        apellidos: {
                        	selector: "#id_tema",
                            validators: {
                                notEmpty: {
                                    message: 'El tema es requerido'
                                },
                            }
                        },
                        fechaCreacion: {
                            selector : "#id_fechaCreacion",
                            validators : {
                                notEmpty : {
                                    message : 'La fecha de creacion es requerida'
                                },
                            }
                        },
                        idAlumno: {
                        	selector: "#id_idAlumno",
                            validators: {
                                notEmpty: {
                                    message: 'El ID es requerido'
                                },
                            }
                        },
                    }
                });
    });
    
$("#id_btn_registrar").click(function() {
    var validator = $('#id_form').data('bootstrapValidator');
    validator.validate();
    
    if (validator.isValid()) {
        $.ajax({
            type: "POST",
            url: "registraTesis",
            data: $('#id_form').serialize(),
            success: function(data){
                mostrarMensaje(data.mensaje);
                limpiarFormulario();
                validator.resetForm();
            },
            error: function(){
                mostrarMensaje(MSG_ERROR);
            }
        });
    }
   
});





function limpiarFormulario() {
	$('#id_titulo').val('');
	$('#id_tema').val('');
	$('#id_fechaCreacion').val('');
    $('#id_nombres').val('');
    $('#id_apellidos').val('');
    $('#id_telefono').val('');
    $('#id_dni').val('');
    $('#id_correo').val('');
    $('#id_fechaNacimiento').val('');
    $('#id_pais').val('');
    $('#id_idAlumno').val('');
    
}

</script>
	
</body>
</html>