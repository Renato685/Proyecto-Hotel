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
	<h4 >Registra Libro</h4>
	
	<form id = "id_form">
	
			<div class = "row" style = "margin-top: 4%">
			
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_titulo">Titulo del libro</label>
									<input class="form-control" type="text" id="id_titulo" name="titulo" placeholder="Ingrese el titulo del libro">	
					</div>
					
					
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_anio">Año del libro</label>
									<input class="form-control" type="text" id="id_anio" name="anio" placeholder="Ingrese el año del libro">	
					
					</div>
			</div>
			
			
			
			
			
			
			
			
			<div class = "row" style = "margin-top: 2%">
			
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_serie">Serie del libro</label>
									<input class="form-control" type="text" id="id_serie" name="serie" placeholder="Ingrese la serie del libro">	
					
					</div>
					
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_tema">Tema del libro</label>
									<input class="form-control" type="text" id="id_tema" name="tema" placeholder="Ingrese tema del libro">	
					
					</div>
			</div>
			
			
			
			
			
			
			<div class = "row" style = "margin-top: 2%" >
				
					
					
					
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_categoria">ID Categoria</label>
									<select class = "form-control" id= "id_categoria" name="categoria">
											<option value = "">[SELECCIONE]</option>
									</select>
					
					</div>
			</div>
			
			
			
			
			
			<div class = "row" align = "center" style = "margin-top: 2%">
					<div class = "form-group col-md-12">
					
						<button type = "button" class= "btn btn-primary" id = "id_btn_registro">Crea Libro</button>
					
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
	                    	titulo: {
	                        	selector: "#id_titulo",
	                            validators: {
	                                notEmpty: {
	                                    message: 'El titulo del libro es requerido cumpita'
	                                },
	                            }
	                        },
	                        anio: {
	                        	selector: "#id_anio",
	                            validators: {
	                                notEmpty: {
	                                    message: 'El año del libro es requerido cumpita'
	                                },
	                            }
	                        },
	                        serie: {
	                        	selector: "#id_serie",
	                            validators: {
	                                notEmpty: {
	                                    message: 'La serie del libro es requerido cumpita'
	                                },
	                            }
	                        },
	                        tema:{
	                            selector: "#id_tema",
	                            validators:{
	                                notEmpty: {
	                                    message: 'El tema del libro es obligatorio cumpita'
	                                },
	                                
	                            }
	                        },
	                        
	                        
	                        categoria: {
	                            validators: {
	                                notEmpty: {
	                                    message: 'Debe seleccionar una categoria cumpita'
	                                }
	                            }
	                        }
	                    }
	                });
	    });
	
	
	$("#id_btn_registro").click(function() {
	    var validator = $('#id_form').data('bootstrapValidator');
	    validator.validate();
	    
	    if (validator.isValid()) {
	        $.ajax({
	            type: "POST",
	            url: "registraLibro",
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
	
	
	
	
	
	
	
	
	
	$.getJSON("cargaCategoria", {}, function (data){
		$.each(data, function(index, item){
			$("#id_categoria").append("<option value=" +  item.idCategoria +" >" +  item.descripcion + "</option>");
		});	
	});		

	
	</script>
	
</body>
</html>