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
	<h4>CRUD Editorial</h4>

		<div class="row" style="margin-top: 5%">
			<div class="col-md-3">
				<label class="control-label" for="id_filtro">Razón Social o Dirección</label> 
			</div>	
			<div class="col-md-6">
				<input	class="form-control" type="text" id="id_filtro" placeholder="Ingrese la razón social o dirección">
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
						<th>Código</th>
						<th>Razón Social</th>
						<th>Dirección</th>
						<th>Teléfono</th>
						<th>RUC</th>
						<th>Fecha Creación</th>
						<th>Estado</th>
						<th>País</th>
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
						<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Editorial</h4>
					</div>
					<div class="modal-body" >
						 <div class="panel-group" id="steps">
			                   <div class="panel panel-default">
			                   		<div id="stepOne" class="panel-collapse collapse in">
			                   			<form id="id_form_registra">
			                   			<input type="hidden" name="metodo" value="paramInserta">
			                   			<div class="panel-body">
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
												<div class="form-group col-md-6">
													<label class="control-label" for="id_telefono">Teléfono</label>
													<input class="form-control" type="text" id="id_telefono" name="telefono" placeholder="Ingrese el teléfono">	
												</div>
												<div class="form-group col-md-6">
													<label class="control-label" for="id_ruc">RUC</label>
													<input class="form-control" type="text" id="id_ruc" name="ruc" placeholder="Ingrese el RUC">	
												</div>
											</div>
											<div class="row" style="margin-top: 2%">
												<div class="form-group col-md-6">
													<label class="control-label" for="id_fecha">Fecha Creacion</label>
													<input class="form-control" type="date" id="id_fecha" name="fechaCreacion">	
												</div>
												<div class="form-group col-md-6">
													<label class="control-label" for="id_pais"> País </label> 
													<select	class="form-control" id="id_pais" name="pais" >
														<option value=" ">[Seleccione]</option>
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
 		
	</div>
	
<script type="text/javascript">
	$.getJSON("cargaPais", {}, function (data){
		$.each(data, function(index, item){
			$("#id_pais").append("<option value=" +  item.idPais +" >" +  item.nombre + "</option>");
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
	
	$("#id_btn_registra").click(function() {
	    var validator = $('#id_form_registra').data('bootstrapValidator');
	    validator.validate();
	    
	    if (validator.isValid()) {
	        $.ajax({
	            type: "POST",
	            url: "crudEditorial",
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
		$.getJSON("crudEditorial", {"metodo":"paramLista","filtro":vfiltro}, function(data) {
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
					{data: "idEditorial",className:'text-center'},
					{data: "razonSocial",className:'text-center'},
					{data: "direccion",className:'text-center'},
					{data: "telefono", className:'text-center'},
					{data: "ruc", className:'text-center'},
					{data: "fechaCreacionFormateada", className:'text-center'},
					{data: function(row, type, val, meta){
						return row.estado == 1 ? "Activo" : "Inactivo";  
					},className:'text-center'},
					{data: "pais.nombre",className:'text-center'},
					{data: function(row, type, val, meta){
						return '<button type="button" class="btn btn-info btn-sm" onClick="verFormularioActualiza(\'' + row.idEditorial + '\',\'' +  row.razonSocial   + '\',\'' +  row.direccion   + '\',\'' +  row.telefono   + '\',\'' +  row.ruc   +  '\',\'' +  row.fechaCreacion  + '\',\'' +  row.estado   + '\',\'' +  row.pais.idPais +'\');">Editar</button>';  
					},className:'text-center'},
					{data: function(row, type, val, meta){
						return '<button type="button" class="btn btn-warning btn-sm" onClick="eliminacionLogica(\'' + row.idEditorial +'\');" >E.Lógica</button>';
					},className:'text-center'},
					{data: function(row, type, val, meta){
						return '<button type="button" class="btn btn-danger btn-sm"  onClick="eliminacionFisica(\'' + row.idEditorial +'\');" >E.Física</button>';
					},className:'text-center'},
				]                                     
		    });
	}
	
	function eliminacionFisica(idEditorial){	
		var array = [idEditorial];
		mostrarMensajeConfirmacion(MSG_ELIMINAR, accionEliminacionFisica,null,array);
	}
	
	function accionEliminacionFisica(array){
		 $.ajax({
	          type: "POST",
	          url: "crudEditorial", 
	          data: {"metodo":"paramEFisica", "idEditorial":array[0]},
	          success: function(data){
	        	  mostrarMensaje(data.mensaje);
	        	  agregarGrilla(data.datos);
	          },
	          error: function(){
	        	  mostrarMensaje(MSG_ERROR);
	          }
	    });
	}
	
	function eliminacionLogica(idEditorial){
		 $.ajax({
	          type: "POST",
	          url: "crudEditorial", 
	          data: {"metodo":"paramELogica", "idEditorial":idEditorial},
	          success: function(data){
	        	  agregarGrilla(data.datos);
	          },
	          error: function(){
	        	  mostrarMensaje(MSG_ERROR);
	          }
	    });
	}
	

    
</script>
</body>
</html>