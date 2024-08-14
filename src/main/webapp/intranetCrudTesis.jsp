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

<title>Pc2 David espinoza pardave</title>
</head>
<body>
<jsp:include page="intranetCabecera.jsp" />
	<div class="container" style="margin-top: 4%">
	<h4>Crud Tesis</h4>
		
		
		<div class="row" style="margin-top: 5%">
			<div class="col-md-3">
				<label class="control-label" for="id_filtro">Titulo</label> 
			</div>	
			<div class="col-md-6">
				<input	class="form-control" type="text" id="id_filtro" placeholder="Ingrese el titulo">
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
						<th>idTesis</th>
						<th>Titulo</th>
						<th>Tema</th>
						<th>FechaCreacion</th>
						<th>FechaRegistro</th>
						<th>FechaActualizacion</th>
						<th>Estado</th>
						<th>idAlumno</th>
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
						<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Tesis</h4>
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
													<label class="control-label" for="id_titulo">Titulo</label>
													<input class="form-control" type="text" id="id_titulo" name="titulo" placeholder="Ingrese el titulo">	
												</div>
												<div class="form-group col-md-6">
													<label class="control-label" for="id_tema">Tema</label>
													<input class="form-control" type="text" id="id_tema" name="tema" placeholder="Ingrese el tema">	
												</div>
											</div>
											<div class="row" style="margin-top: 2%">
												<div class="form-group col-md-6">
													<label class="control-label" for="id_fechaCreacion">Fecha Creacion</label>
													<input class="form-control" type="date" id="id_fechaCreacion" name="fechaCreacion">	
												</div>
												<div class="form-group col-md-6">
													<label class="control-label" for="id_fechaRegistro">Fecha Registro</label>
													<input class="form-control" type="date" id="id_fechaRegistro" name="fechaRegistro">	
												</div>
												<div class="form-group col-md-6">
													<label class="control-label" for="id_fechaActualizacion">Fecha Actualizacion</label>
													<input class="form-control" type="date" id="id_fechaActualizacion" name="fechaActualizacion">	
												</div>
											</div>
											<div class="row" style="margin-top: 2%">
												
												<div class="form-group col-md-6">
													<label class="control-label" for="id_alumno"> Alumno </label> 
													<select	class="form-control" id="id_alumno" name="alumno" >
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
	//cb alumno xd
		 $.getJSON("cargaAlumno", {}, function(data) {
	        $.each(data, function(index, item) {
	            $("#id_alumno").append("<option value=" +  item.idAlumno + " >" + item.nombres + item.apellidos + "</option>");
	        });
	     });
	//-----
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
			                                    message: 'El titulo es requerido'
			                                },
			                            }
			                        },
			                        tema: {
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
			                                    message : 'La fecha de Creacion es requerida'
			                                },
			                            }
			                        },
			                        
			                        fechaRegistro: {
			                            selector : "#id_fechaRegistro",
			                            validators : {
			                                notEmpty : {
			                                    message : 'La fecha de registro es requerida'
			                                },
			                            }
			                        },
			                        fechaActualizacion: {
			                            selector : "#id_fechaActualizacion",
			                            validators : {
			                                notEmpty : {
			                                    message : 'La fecha de Actualizacion es requerida'
			                                },
			                            }
			                        },
			                        alumno: {
			                            validators: {
			                                notEmpty: {
			                                    message: 'Debe seleccionar un alumno'
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
			            url: "crudTesis",
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
	
	//------------
	  
		$("#id_btn_filtro").click(function() {
			var vfiltro = $("#id_filtro").val();
			$.getJSON("crudTesis", {"metodo":"paramLista","filtro":vfiltro}, function(data) {
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
						{data: "idTesis",className:'text-center'},
						{data: "titulo",className:'text-center'},
						{data: "tema",className:'text-center'},
						{data: "fechaCreacion", className:'text-center'},
						{data: "fechaRegistro", className:'text-center'},
						{data: "fechaActualizacion", className:'text-center'},
						{data: function(row, type, val, meta){
							return row.estado == 1 ? "Activo" : "Inactivo";  
						},className:'text-center'},
						{data: "alumno.idAlumno",className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-info btn-sm" onClick="verFormularioActualiza(\'' + row.idTesis + '\',\'' +  row.titulo   + '\',\'' +  row.tema   + '\',\'' +  row.fechaCreacion   + '\',\'' +  row.fechaRegistro   + '\',\'' +  row.fechaActualizacion + '\',\'' +    row.estado   + '\',\'' +  row.alumno.idAlumno +'\');">Editar</button>';  
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-warning btn-sm" onClick="eliminacionLogica(\'' + row.idTesis +'\');" >E.Lógica</button>';
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-danger btn-sm"  onClick="eliminacionFisica(\'' + row.idTesis +'\');" >E.Física</button>';
						},className:'text-center'},
					]                                     
			    });
		}
		function eliminacionFisica(idTesis){	
			var array = [idTesis];
			mostrarMensajeConfirmacion(MSG_ELIMINAR, accionEliminacionFisica,null,array);
		}
		
		function accionEliminacionFisica(array){
			 $.ajax({
		          type: "POST",
		          url: "crudTesis", 
		          data: {"metodo":"paramEFisica", "idTesis":array[0]},
		          success: function(data){
		        	  mostrarMensaje(data.mensaje);
		        	  agregarGrilla(data.datos);
		          },
		          error: function(){
		        	  mostrarMensaje(MSG_ERROR);
		          }
		    });
		}
		
		function eliminacionLogica(idTesis){
			 $.ajax({
		          type: "POST",
		          url: "crudTesis", 
		          data: {"metodo":"paramELogica", "idTesis":idTesis},
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