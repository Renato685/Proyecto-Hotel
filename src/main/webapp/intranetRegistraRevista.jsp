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
		<h4>Registrar Revista</h4>
		<div class="container">
			<form action=" "  id="id_form">
				<div class="row" style="margin-top: 5%">
					<div class="form-group col-md-12">
						<label class="control-label" for="id_nombre">Nombres</label>
						<input class="form-control" type="text" id="id_nombre" name="nombres" placeholder="Ingrese el nombre">	
					</div>
					<div class="form-group col-md-12">
						<label class="control-label" for="id_frecuencia">Frecuencia</label>
						<input class="form-control" type="text" id="id_frecuencia" name="frecuencia" placeholder="Ingrese frecuencia">	
					</div>
					<div class="form-group col-md-12">
						<label class="control-label" for="id_modalidad"> Modalidad </label> 
						<select	class="form-control" id="id_modalidad" name="modalidad" >
							<option value=" ">[Seleccione]</option>
						</select>
						
					</div>
				</div>
				<div class="row" style="margin-top: 2%" align="center">
					<button id="id_btn_registrar" type="button" class="btn btn-primary">Registrar Revista</button>
		        </div>
			</form>
		</div>
	</div>

<script type="text/javascript">
    $(document)
        .ready(
            function() {
                $('#id_form')
                    .bootstrapValidator(
                        {
                            message : 'This value is not valid',
                            feedbackIcons : {
                                valid : 'glyphicon glyphicon-ok',
                                invalid : 'glyphicon glyphicon-remove',
                                validating : 'glyphicon glyphicon-refresh'
                            },
                            fields: {
                                nombres: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Los Nombres son requeridos'
                                        },
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
                url: "registraRevista",
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

    $.getJSON("cargaModalidad", {}, function (data){
		$.each(data, function(index, item){
			$("#id_modalidad").append("<option value=" +  item.idModalidad +" >" +  item.descripcion + "</option>");
		});
    });

   function limpiarFormulario() {
        $('#id_nombre').val('');
    }

   </script>
</body>
</html>