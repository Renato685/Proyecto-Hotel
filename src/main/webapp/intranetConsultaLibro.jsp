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
	<h4>Consulta Libro</h4>
	
		<div class = "row" style = "margin-top: 5%">
			
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_titulo">Título del libro</label>
									<input class="form-control" type="text" id="id_titulo" name="titulo" >	
					</div>
					
					
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_anio">Año del libro</label>
									<input class="form-control" type="text" id="id_anio" name="anio" >	
					
					</div>
		</div>
		
		<div class = "row" style = "margin-top: 2%">
			
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_serie">Serie del libro</label>
									<input class="form-control" type="text" id="id_serie" name="serie" >	
					
					</div>
					
					<div class = "form-group col-md-6">
					
									<label class="control-label" for="id_tema">Tema del libro</label>
									<input class="form-control" type="text" id="id_tema" name="tema" >	
					
					</div>
		</div>
		
		<div class = "row" align = "center" style = "margin-top: 2%">
					<div class = "form-group col-md-12">
					
						<button type = "button" class= "btn btn-primary" id = "id_btn_filtrar">Filtrar</button>
					
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
						
						
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	
	</div>
	
	<script type="text/javascript">
	
	$("#id_btn_filtrar").click(function() {
		var vftitulo = $("#id_titulo").val();
		var vfanio = $("#id_anio").val();
		var vfserie = $("#id_serie").val();
		var vftema = $("#id_tema").val();
		
		console.log("vftitulo >>> " + vftitulo )
	    console.log("vfanio >>> " + vfanio )
		console.log("vfserie >>> " + vfserie )
		console.log("vftema >>> " + vftema )


		$.getJSON("consultaLibro", {"titulo": vftitulo,"anio": vfanio,"serie": vfserie,"tema": vftema}, function(data) {
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
					
				]                                     
		    });
	}
	
	
	
	
	
	
	
	
	</script>
	
	
</body>
</html>