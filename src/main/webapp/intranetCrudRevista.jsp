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
	<h4>CRUD Revista</h4>
		
		
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
						<th>Código</th>
						<th>Nombre</th>
						<th>Frecuencia</th>
						<th>FechaCreacion</th>
						<th>FechaRegistro</th>
						<th>Estado</th>
						<th>Modalidad</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>


				</tbody>
			</table>
		</div>
		
	
	</div>
	
	<div class="modal fade" id="id_div_modal_registra" >
			<div class="modal-dialog" style="width: 60%">
					<div class="modal-content">
					<div class="modal-header" >
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4><span class="glyphicon glyphicon-ok-sign"></span> Registro de Revista</h4>
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
													<label class="control-label" for="id_nombre">Nombre</label>
													<input class="form-control" type="text" id="id_nombre" name="nombre" placeholder="Ingrese el nombre">	
												</div>
												<div class="form-group col-md-6">
													<label class="control-label" for="id_frecuencia">Frecuencia</label>
													<input class="form-control" type="text" id="id_frecuencia" name="frecuencia" placeholder="Ingrese la frecuencia">	
												</div>
												
												
												
												<div class="form-group col-md-6">
													<label class="control-label" for="id_modalidad"> Modalidad </label> 
													<select	class="form-control" id="id_modalidad" name="modalidad" >
														<option value=" ">[Seleccione]</option>
													</select>
												</div>
												<div class="row" style="margin-top: 4%">
												<div class="form-group col-md-12">
											<div class="row"  align="center" style="margin-top: 2%">
													<button type="button" style="width: 80px" id="id_btn_registra" class="btn btn-primary btn-sm">Registrar</button>
		                                        	<button type="button" style="width: 80px" id="id_btn_reg_cancelar" class="btn btn-primary btn-sm" data-dismiss="modal">Cancelar</button>
													
											</div>
											</div>
											</div>
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
	
	
	
	
	<script type="text/javascript">
	
	 $.getJSON("cargaModalidad", {}, function (data){
			$.each(data, function(index, item){
				$("#id_modalidad").append("<option value=" +  item.idModalidad +" >" +  item.descripcion + "</option>");
			});
	    });
	 
	 
	 $(document)
     .ready(
         function() {
             $('#id_form_registra')
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

 $("#id_btn_registra").click(function() {
     var validator = $('#id_form_registra').data('bootstrapValidator');
     validator.validate();

     if (validator.isValid()) {
         $.ajax({
             type: "POST",
             url: "crudRevista",
             data: $('#id_form_registra').serialize(),
             success: function(data){
                 mostrarMensaje(data.mensaje);
                 agregarGrilla(data.datos);
                 limpiarFormulario();
                 validator.resetForm();
                 $('#id_div_modal_registra').modal('hide');
             },
             error: function(){
                 mostrarMensaje(MSG_ERROR);
             }
         });
     }
 });

 function limpiarFormulario() {
     $('#id_nombre').val('');
     $('#id_frecuencia').val('');
 }
 
		$("#id_btn_filtro").click(function() {
			var vfiltro = $("#id_filtro").val();
			$.getJSON("crudRevista", {"metodo":"paramLista","filtro":vfiltro}, function(data) {
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
						{data: "idRevista",className:'text-center'},
						{data: "nombre",className:'text-center'},
						{data: "frecuencia",className:'text-center'},
						{data: "fechaCreacion", className:'text-center'},
						{data: "fechaRegistro", className:'text-center'},
						{data: function(row, type, val, meta){
							return row.estado == 1 ? "Activo" : "Inactivo";  
						},className:'text-center'},
						{data: "modalidad.descripcion",className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-info btn-sm" onClick="verFormularioActualiza(\'' + row.idRevista + '\',\'' +  row.nombre   + '\',\'' +  row.frecuencia   + '\',\'' +  row.fechaCreacion   + '\',\'' +  row.fechaRegistro   + '\',\'' + row.estado   + '\',\'' +  row.modalidad.idModalidad +'\');">Editar</button>';  
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-warning btn-sm" onClick="eliminacionLogica(\'' + row.idRevista +'\');" >E.Lógica</button>';
						},className:'text-center'},
						{data: function(row, type, val, meta){
							return '<button type="button" class="btn btn-danger btn-sm"  onClick="eliminacionFisica(\'' + row.idRevista +'\');" >E.Física</button>';
						},className:'text-center'},
					]                                     
			    });
		}
		
		function eliminacionFisica(idRevista){	
			var array = [idRevista];
			mostrarMensajeConfirmacion(MSG_ELIMINAR, accionEliminacionFisica,null,array);
		}
		
		function accionEliminacionFisica(array){
			 $.ajax({
		          type: "POST",
		          url: "crudRevista", 
		          data: {"metodo":"paramEFisica", "idRevista":array[0]},
		          success: function(data){
		        	  mostrarMensaje(data.mensaje);
		        	  agregarGrilla(data.datos);
		          },
		          error: function(){
		        	  mostrarMensaje(MSG_ERROR);
		          }
		    });
		}
		
		function eliminacionLogica(idRevista){
			 $.ajax({
		          type: "POST",
		          url: "crudRevista", 
		          data: {"metodo":"paramELogica", "idRevista":idRevista},
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

