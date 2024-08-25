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
	<h4>CRUD Libro</h4>
		
		
		<div class="row" style="margin-top: 5%">
			<div class="col-md-3">
				<label class="control-label" for="id_filtro">Nombre</label> 
			</div>	
			<div class="col-md-6">
				<input	class="form-control" type="text" id="id_filtro" placeholder="Ingrese el nombre">
			</div>	
			<div class="col-md-1">
				<button type="button" class="btn btn-primary" id="id_btn_filtro">Filtra</button>
			</div>	
			<div class="col-md-1">
				<button type="button" class="btn btn-primary"  data-toggle='modal' data-target="#id_div_modal_registra" >Registra</button>
			</div>	
		</div>


		<div class="row" style="margin-top: 4%">
			<table id="id_table" class="table table-bordered table-hover table-condensed" >
				<thead style='background-color:#337ab7; color:white'>
					<tr>
						<th>ID libro</th>
						<th>Título</th>
						<th>Año</th>
						<th>Serie</th>
						<th>Tema</th>
						<th>Estado</th>
						<th>Categoría</th>
						<th></th>
						<th></th>
						<th></th>
						
					</tr>
				</thead>
				<tbody>


				</tbody>
			</table>
		</div>
		
		
		<!-- INICIO MODAL DE REGISTRO -->
		<div class="modal fade" id="id_div_modal_registra" >
			<div class="modal-dialog" style="width: 60%">
					<div class="modal-content">
					<div class="modal-header" >
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Libro</h4>
					</div>
					<div class="modal-body" >
						 <div class="panel-group" id="steps">
			                   <div class="panel panel-default">
			                   		<div id="stepOne" class="panel-collapse collapse in">
			                   			<form id="id_form_registra">
			                   			<input type="hidden" name="metodo" value="paramInserta">
			                   			<div class="panel-body">
			                                <div class = "row" style = "margin-top: 4%">
			
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_titulo">Título del libro</label>
									<input class="form-control" type="text" id="id_titulo" name="titulo" placeholder="Ingrese el título del libro">	
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
					
									<label class="control-label" for="id_categoria">ID Categoría</label>
									<select class = "form-control" id= "id_categoria" name="categoria">
											<option value = "">[SELECCIONE]</option>
									</select>
					
					</div>
			</div>
			
											<div class="row"  align="center" style="margin-top: 2%">
													<button type="button" style="width: 80px" id="id_btn_registra" class="btn btn-primary btn-sm">Registrar</button>
		                                        	<button type="button" style="width: 80px" id="id_btn_reg_cancelar" class="btn btn-primary btn-sm" data-dismiss="modal">Cancelar</button>
													
											</div>
			                             </div>
			                             </form>
			                        </div>
			                   </div>
			              </div>
					</div>
				</div>
			</div>
		</div>
 
		<!-- FIN MODAL DE REGISTRO -->
		
	
	
	
	
		<!-- INICIO MODAL DE ACTUALIZAR -->
<div class="modal fade" id="id_div_modal_actualiza">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4><span class="glyphicon glyphicon-ok-sign"></span> Actualiza Libro</h4>
            </div>
            <div class="modal-body">
                <div class="panel-group" id="steps">
                    <div class="panel panel-default">
                        <div id="stepOne" class="panel-collapse collapse in">
                            <form id="id_form_actualiza">
                                <input type="hidden" name="metodo" value="paramActualiza">
                                <input type="hidden" name="idLibro" id="idLibro">
                                
                                <div class="panel-body">
                                    <!-- Título del libro -->
                                    <div class="row" style="margin-top: 4%">
                                        <div class="form-group col-md-6">
                                            <label class="control-label" for="id_titulo_actualiza">Título del libro</label>
                                            <input class="form-control" type="text" id="id_titulo_actualiza" name="titulo" placeholder="Ingrese el título del libro">
                                        </div>
                                        <!-- Año del libro -->
                                        <div class="form-group col-md-6">
                                            <label class="control-label" for="id_anio_actualiza">Año del libro</label>
                                            <input class="form-control" type="text" id="id_anio_actualiza" name="anio" placeholder="Ingrese el año del libro">
                                        </div>
                                    </div>
                                    
                                    <!-- Serie del libro -->
                                    <div class="row" style="margin-top: 2%">
                                        <div class="form-group col-md-6">
                                            <label class="control-label" for="id_serie_actualiza">Serie del libro</label>
                                            <input class="form-control" type="text" id="id_serie_actualiza" name="serie" placeholder="Ingrese la serie del libro">
                                        </div>
                                        <!-- Tema del libro -->
                                        <div class="form-group col-md-6">
                                            <label class="control-label" for="id_tema_actualiza">Tema del libro</label>
                                            <input class="form-control" type="text" id="id_tema_actualiza" name="tema" placeholder="Ingrese el tema del libro">
                                        </div>
                                    </div>
                                    
                                    <!-- Categoría y Estado -->
                                    <div class="row" style="margin-top: 2%">
                                        <!-- ID Categoría -->
                                        <div class="form-group col-md-6">
                                            <label class="control-label" for="id_categoria_actualiza">ID Categoría</label>
                                            <select class="form-control" id="id_categoria_actualiza" name="categoria">
                                                <option value="">[SELECCIONE]</option>
                                            </select>
                                        </div>
                                        <!-- Estado -->
                                        <div class="form-group col-md-4">
                                            <label class="control-label" for="id_estado_actualiza">Estado</label>
                                            <select class="form-control" id="id_estado_actualiza" name="estado">
                                                <option value="">[SELECCIONE]</option>
                                                <option value="1">Activo</option>
                                                <option value="0">Inactivo</option>
                                            </select>
                                        </div>
                                    </div>
                                    
                                    <!-- Botones -->
                                    <div class="row" align="center" style="margin-top: 2%">
                                        <button type="button" style="width: 80px" id="id_btn_actualiza" class="btn btn-primary btn-sm">Actualizar</button>
                                        <button type="button" style="width: 80px" id="id_btn_act_cancelar" class="btn btn-primary btn-sm" data-dismiss="modal">Cancelar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- FIN MODAL DE ACTUALIZAR -->

	</div>
	
	
	<script type="text/javascript">
	$.getJSON("cargaCategoria", {}, function (data){
		$.each(data, function(index, item){
			$("#id_categoria").append("<option value=" +  item.idCategoria +" >" +  item.descripcion + "</option>");
			$("#id_categoria_actualiza").append("<option value=" +  item.idCategoria +" >" +  item.descripcion + "</option>");

		});	
	});		
	
	$(document)
	.ready(function(){
		$('#id_form_registra').bootstrapValidator(
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
	                                    message: 'El título del libro es requerido cumpita'
	                                },
	                                remote :{
	                                	delay   : 1000,
	                                	url     : 'ValidaRemoteRevistaLibroTituloServlet',
	                                	message : 'El título ya existe'
	                                }
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
	                                remote :{
	                                	delay   : 1000,
	                                	url     : 'ValidaRemoteRevistaLibroSerieServlet',
	                                	message : 'La serie ya existe'
	                                }
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
	                                    message: 'Debe seleccionar una categoría cumpita'
	                                }
	                            }
	                        }
	                        
	                    }
	                });
	    });
	
	
	$("#id_btn_registra").click(function() {
	    var validator = $('#id_form_registra').data('bootstrapValidator');
	    validator.validate();
	    
	    if (validator.isValid()) {
	        $.ajax({
	            type: "POST",
	            url: "crudLibro",
	            data: $('#id_form_registra').serialize(),
	            success: function(data){
	                mostrarMensaje(data.mensaje);
	                agregarGrilla(data.datos);
	                validator.resetForm();
	                $('#id_div_modal_registra').modal("hide");
	            },
	            error: function(){
	                mostrarMensaje(MSG_ERROR);
	            }
	        });
	    }
	   
	    
	});
	
	
	
	
	
		$("#id_btn_filtro").click(function() {
			var vfiltro = $("#id_filtro").val();
			$.getJSON("crudLibro", {"metodo":"paramLista","filtro":vfiltro}, function(data) {
				agregarGrilla(data);
			});
		});
		
		function agregarGrilla(lista){
			 $('#id_table').DataTable().clear();
			 $('#id_table').DataTable().destroy();
			 $('#id_table').DataTable({
					data: lista,
					language: IDIOMA,
					searching: true,
					ordering: true,
					processing: true,
					pageLength: 10,
					lengthChange: false,
					info:true,
					scrollY: 305,
			        scroller: {
			            loadingIndicator: true
			        },
					columns:[
						{data: "idLibro",className:'text-center'},
						{data: "titulo",className:'text-center'},
						{data: "anio",className:'text-center'},
						{data: "serie", className:'text-center'},
						{data: "tema", className:'text-center'},
						{data: function(row, type, val, meta){
							return row.estado == 1 ? "Activo" : "Inactivo";  
						},className:'text-center'},
						{data: "categoria.descripcion",className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-info btn-sm" onClick="verFormularioActualiza(\'' + row.idLibro + '\',\'' +  row.titulo   + '\',\'' +  row.anio   + '\',\'' +  row.serie   + '\',\'' +  row.tema   + '\',\'' +   row.estado   + '\',\''+  + row.categoria.idCategoria +'\');">Editar</button>';  
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-warning btn-sm" onClick="eliminacionLogica(\'' + row.idLibro +'\');" >E.Lógica</button>';
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-danger btn-sm"  onClick="eliminacionFisica(\'' + row.idLibro +'\');" >E.Física</button>';
						},className:'text-center'},
					]                                     
			    });
		}
		
		function eliminacionFisica(idLibro){	
			var array = [idLibro];
			mostrarMensajeConfirmacion(MSG_ELIMINAR, accionEliminacionFisica,null,array);
		}
		
		function accionEliminacionFisica(array){
			 $.ajax({
		          type: "POST",
		          url: "crudLibro", 
		          data: {"metodo":"paramEFisica", "idLibro":array[0]},
		          success: function(data){
		        	  mostrarMensaje(data.mensaje);
		        	  agregarGrilla(data.datos);
		          },
		          error: function(){
		        	  mostrarMensaje(MSG_ERROR);
		          }
		    });
		}
		
		function eliminacionLogica(idLibro){
			 $.ajax({
		          type: "POST",
		          url: "crudLibro", 
		          data: {"metodo":"paramELogica", "idLibro":idLibro},
		          success: function(data){
		        	  agregarGrilla(data.datos);
		          },
		          error: function(){
		        	  mostrarMensaje(MSG_ERROR);
		          }
		    });
		}
		
		
		
		function verFormularioActualiza(idLibro,titulo,anio,serie,tema,estado,idCategoria ){
			console.log(">> verFormularioActualiza >> " + idLibro);
			$("#id_div_modal_actualiza").modal("show");
			$("#idLibro").val(idLibro);
			$("#id_titulo_actualiza").val(titulo);
			$("#id_anio_actualiza").val(anio);
			$("#id_serie_actualiza").val(serie);
			$("#id_tema_actualiza").val(tema);
			$("#id_estado_actualiza").val(estado);
			$("#id_categoria_actualiza").val(idCategoria);
			
			

		}
		
		$("#id_btn_actualiza").click(function() {
			var validator = $('#id_form_actualiza').data('bootstrapValidator');
		    validator.validate();
		    

			
		    if (validator.isValid()) {
		        $.ajax({
			          type: "POST",
			          url: "crudLibro", 
			          data: $('#id_form_actualiza').serialize(),
			          success: function(data){
			        	  mostrarMensaje(data.mensaje);
			        	  agregarGrilla(data.datos);
			        	  validator.resetForm();
			        	  $('#id_div_modal_actualiza').modal("hide");
			          },
			          error: function(){
			        	  mostrarMensaje(MSG_ERROR);
			          }
			    });
		    }
		});
		
		$(document).ready(function() {
		    $('#id_form_actualiza').bootstrapValidator({
		        message: 'Este valor no es válido',
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		            titulo: {
		                selector: "#id_titulo_actualiza",
		                validators: {
		                    notEmpty: {
		                        message: 'El título del libro es requerido'
		                    }
		                }
		            },
		            anio: {
		                selector: "#id_anio_actualiza",
		                validators: {
		                    notEmpty: {
		                        message: 'El año del libro es requerido'
		                    }
		                }
		            },
		            serie: {
		                selector: "#id_serie_actualiza",
		                validators: {
		                    notEmpty: {
		                        message: 'La serie del libro es requerida'
		                    }
		                }
		            },
		            tema: {
		                selector: "#id_tema_actualiza",
		                validators: {
		                    notEmpty: {
		                        message: 'El tema del libro es obligatorio'
		                    }
		                }
		            },
		            categoria: {
		                selector: "#id_categoria_actualiza",
		                validators: {
		                    notEmpty: {
		                        message: 'Debe seleccionar una categoría'
		                    }
		                }
		            },
		            estado: {
		                selector: "#id_estado_actualiza",
		                validators: {
		                    notEmpty: {
		                        message: 'Debe seleccionar un estado'
		                    }
		                }
		            }
		        }
		    });
		});

		
		
		
		
		
		
		
</script>
</body>
</html>