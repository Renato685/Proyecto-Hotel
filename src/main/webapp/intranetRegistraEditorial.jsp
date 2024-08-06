<!DOCTYPE html>
<html lang="esS">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrapValidator.css" />

<title>Sistemas - Jorge Jacinto Gutarra</title>
</head>
<body>
<jsp:include page="intranetCabecera.jsp" />
	<div class="container" style="margin-top: 4%">
	<h4>Registra Editorial</h4>
	
	<form id="id_form" action=" ">
		<div class="row" style="margin-top: 4%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_razonSocial">Razón Social</label> 
				<input class="form-control" type="text" id="id_razonSocial" name="razonSocial" placeholder="Ingrese la razón social">
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="id_direccion">Dirección</label> 
				<input class="form-control" type="text" id="id_direccion" name="direccion" placeholder="Ingrese la dirección">
			</div>
		</div>
		<div class="row" style="margin-top: 2%">
			<div class="form-group col-md-3">
				<label class="control-label" for="id_telefono">Teléfono</label> 
				<input class="form-control" type="text" id="id_telefono" name="telefono" placeholder="Ingrese el teléfono">
			</div>
			<div class="form-group col-md-3">
				<label class="control-label" for="id_ruc">RUC</label> 
				<input class="form-control" type="text" id="id_ruc" name="ruc" placeholder="Ingrese el RUC">
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="id_fecha">Fecha Creación</label>
				<input class="form-control" type="date" id="id_fecha" name="fechaCreacion">
			</div>
		</div>
		<div class="row" style="margin-top: 2%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_pais"> País </label> 
				<select	class="form-control" id="id_pais" name="pais">
					<option value=" ">[Seleccione]</option>
				</select>
			</div>
		</div>
		<div class="row" align="center" style="margin-top: 2%">
			<div class="form-group col-md-12">
				<button type="button" class="btn btn-primary" id="id_btn_registrar">Crear Editorial</button>
			</div>
		</div>
	</form>

</div>
	
<script type="text/javascript">

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
                        	selector: "#id_razonSocial",
                            validators: {
                                notEmpty: {
                                    message: 'La raz&oacute;n social es requerida'
                                },
                            }
                        },
                        apellidos: {
                        	selector: "#id_direccion",
                            validators: {
                                notEmpty: {
                                    message: 'La direcci&oacute;n es requerida'
                                },
                            }
                        },
                        telefono:{
                            selector: "#id_telefono",
                            validators:{
                                notEmpty: {
                                    message: 'El tel&eacute;fono es obligatorio'
                                },
                                regexp: {
                                    regexp: /^9[0-9]{8}$/,
                                    message: 'El tel&eacute;fono es de 9 d&iacute;gitos y empieza con 9'
                                }
                            }
                        },
                        dni:{
                            selector: "#id_ruc",
                            validators:{
                                notEmpty: {
                                    message: 'El RUC es obligatorio'
                                },
                                regexp: {
                                    regexp: /^[0-9]{11}$/,
                                    message: 'El RUC es 11 d&iacute;gitos'
                                }
                            }
                        },

                        fechaNacimiento: {
                            selector : "#id_fecha",
                            validators : {
                                notEmpty : {
                                    message : 'La fecha de creaci&oacute;n es requerida'
                                },
                            }
                        },
                        pais: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe seleccionar un pa&iacute;s'
                                }
                            }
                        }
                    }
                });
    });
    
$("#id_btn_registrar").click(function() {
    var validator = $('#id_form').data('bootstrapValidator');
    validator.validate();
    
    if (validator.isValid()) {
        $.ajax({
            type: "POST",
            url: "registraEditorial",
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


$.getJSON("cargaPais", {}, function (data){
	$.each(data, function(index, item){
		$("#id_pais").append("<option value=" +  item.idPais +" >" +  item.nombre + "</option>");
	});	
});		

</script>
</body>
</html>