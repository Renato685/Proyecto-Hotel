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
	<h4>Registra Sala</h4>
	
	<form id="id_form">
		<div class="row" style="margin-top: 4%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_numero">Número</label>
				<input class="form-control" type="text" id="id_numero" name="numero" placeholder="Ingrese el numero de sala">	
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="id_piso">Piso</label>
				<input class="form-control" type="text" id="id_piso" name="piso" placeholder="Ingrese el piso de la sala">	
			</div>
		</div>
		<div class="row"  style="margin-top: 2%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_numalumnos">Número de alumnos</label>
				<input class="form-control" type="text" id="id_numalumnos" name="numalumnos" placeholder="Ingrese el número de alumnos">	
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="id_recursos">Recursos</label>
				<input class="form-control" type="text" id="id_recursos" name="recursos" placeholder="Ingrese los recursos">	
			</div>
		</div>
		<div class="row"  style="margin-top: 2%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_sede"> Sede </label> 
				<select	class="form-control" id="id_sede" name="sede" >
					<option value=" ">[Seleccione]</option>
				</select>
			</div>
		</div>
		<div class="row"  align="center" style="margin-top: 2%">
			<div class="form-group col-md-12">
					<button type="button" class="btn btn-primary" id="id_btn_registra">Crea Sala</button>
			</div>
		</div>
	</form>
	</div>
	

<script type="text/javascript">

$(document).ready(function() {
        $('#id_form').bootstrapValidator(
                {
                    message : 'This value is not valid',
                    feedbackIcons : {
                        valid : 'glyphicon glyphicon-ok',
                        invalid : 'glyphicon glyphicon-remove',
                        validating : 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        numero: {
                        	selector: "#id_numero",
                            validators: {
                                notEmpty: {
                                    message: 'El numero de sala es requerido'
                                },
                            }
                        },
                        piso: {
                        	selector: "#id_piso",
                            validators: {
                                notEmpty: {
                                    message: 'El numero de piso de la sala es requerido'
                                },
                            }
                        },
                        
                        numalumnos:{
                            selector: "#id_numalumnos",
                            validators:{
                                notEmpty: {
                                    message: 'El número de alumnos es requerido'
                                }, 
                            }
                        },
                        recursos:{
                            selector: "#id_recursos",
                            validators:{
                                notEmpty: {
                                    message: 'El nombre de recursos es requerido'
                                },
                            }
                        },

                        sede: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe seleccionar una sede'
                                }
                            }
                        }
                    }
                });
    });
    
    
$("#id_btn_registra").click(function() {
    var validator = $('#id_form').data('bootstrapValidator');
    validator.validate();

    if (validator.isValid()) {
        $.ajax({
            type: "POST",
            url: "registraSala",
            data: $('#id_form').serialize(),
            success: function(data){
                mostrarMensaje(data.mensaje);
                validator.resetForm();
            },
            error: function(){
                mostrarMensaje(MSG_ERROR);
            }
        });
    }
});

$.getJSON("cargaSede", {}, function (data){
	$.each(data, function(index, item){
		$("#id_sede").append("<option value=" +  item.idSede +" >" +  item.nombre + "</option>");
	});	
});		


</script>
</body>
</html>