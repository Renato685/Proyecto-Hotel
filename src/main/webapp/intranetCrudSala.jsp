<!DOCTYPE html>
<html lang="es" >
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
	<h4>CRUD Sala</h4>
		
		
		<div class="row" style="margin-top: 5%">
			<div class="col-md-3">
				<label class="control-label" for="id_filtro">Número o Recurso</label> 
			</div>	
			<div class="col-md-6">
				<input	class="form-control" type="text" id="id_filtro" placeholder="Ingrese el número de sala o el nombre del recurso">
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
						<th>Número</th>
						<th>Piso</th>
						<th>Número de Alumnos</th>
						<th>Recursos</th>
						<th>Estado</th>
						<th>Sede</th>
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
						<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Sala</h4>
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
													<label class="control-label" for="id_numero">Número</label>
													<input class="form-control" type="text" id="id_numero" name="numero" placeholder="Ingrese el número de sala">	
												</div>
												<div class="form-group col-md-6">
													<label class="control-label" for="id_piso">Piso</label>
													<input class="form-control" type="text" id="id_piso" name="piso" placeholder="Ingrese el piso de la sala">	
												</div>
											</div>
											<div class="row"  style="margin-top: 2%">
												<div class="form-group col-md-6">
													<label class="control-label" for="id_numalumnos">Número de alumnos</label>
													<input class="form-control" type="text" id="id_numalumnos" name="numAlumnos" placeholder="Ingrese el número de alumnos">	
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
		
		$.getJSON("cargaSede", {}, function (data){
			$.each(data, function(index, item){
				$("#id_sede").append("<option value=" +  item.idSede +" >" +  item.nombre + "</option>");
			});	
		});		
		
		$(document).ready(function() {
	        $('#id_form_registra').bootstrapValidator(
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
	                                    message: 'El número de sala es requerido'
	                                },
	                            }
	                        },
	                        piso: {
	                        	selector: "#id_piso",
	                            validators: {
	                                notEmpty: {
	                                    message: 'El número de piso de la sala es requerido'
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
		    var validator = $('#id_form_registra').data('bootstrapValidator');
		    validator.validate();
	
		    if (validator.isValid()) {
		        $.ajax({
		            type: "POST",
		            url: "crudSala",
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
			$.getJSON("crudSala", {"metodo":"paramLista","filtro":vfiltro}, function(data) {
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
						{data: "idSala",className:'text-center'},
						{data: "numero",className:'text-center'},
						{data: "piso",className:'text-center'},
						{data: "numAlumnos", className:'text-center'},
						{data: "recursos", className:'text-center'},
						{data: function(row, type, val, meta){
							return row.estado == 1 ? "Activo" : "Inactivo";  
						},className:'text-center'},
						{data: "sede.nombre",className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-info btn-sm" onClick="verFormularioActualiza(\'' + row.idSala + '\',\'' +  row.numero   + '\',\'' +  row.piso   + '\',\'' +  row.numAlumnos   + '\',\'' +  row.recursos   + '\',\''  +  row.estado   + '\',\'' +  row.sede.idSede +'\');">Editar</button>';  
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-warning btn-sm" onClick="eliminacionLogica(\'' + row.idSala +'\');" >E.Lógica</button>';
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-danger btn-sm"  onClick="eliminacionFisica(\'' + row.idSala +'\');" >E.Física</button>';
						},className:'text-center'},
					]                                     
			    });
		}
		
		function eliminacionFisica(idSala){	
			var array = [idSala];
			mostrarMensajeConfirmacion(MSG_ELIMINAR, accionEliminacionFisica,null,array);
		}
		
		
		function accionEliminacionFisica(array){
			 $.ajax({
		          type: "POST",
		          url: "crudSala", 
		          data: {"metodo":"paramEFisica", "idSala":array[0]},
		          success: function(data){
		        	  mostrarMensaje(data.mensaje);
		        	  agregarGrilla(data.datos);
		          },
		          error: function(){
		        	  mostrarMensaje(MSG_ERROR);
		          }
		    });
		}
		
		function eliminacionLogica(idSala){
			 $.ajax({
		          type: "POST",
		          url: "crudSala", 
		          data: {"metodo":"paramELogica", "idSala":idSala},
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