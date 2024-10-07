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
    <h4>Registra Cliente</h4>

    <div class="container">
        <form action=" "  id="id_form">
            <div class="row" style="margin-top: 5%">
                <!-- Primera columna -->
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="id_nombres" class="control-label">Nombres</label> <input
                            class="form-control" type="text" id="id_nombre" name="Nombre"
                            placeholder="Ingrese el Nombre" maxlength="30">
                    </div>
                    <div class="form-group">
                        <label for="id_apellidos" class="control-label">Apellidos</label> <input
                            class="form-control" type="text" id="id_apellido" name="Apellido"
                            placeholder="Ingrese los apellidos" maxlength="30">
                    </div>
                    <div class="form-group">
                        <label for="id_telefono" class="control-label">Tel&eacute;fono</label> <input
                            class="form-control" type="text" id="id_telefono" name="Telefono"
                            placeholder="Ingrese el tel&eacute;fono" maxlength="9">
                    </div>
                    <div class="form-group">
                        <label for="id_telefono" class="control-label">Direccion</label> <input
                            class="form-control" type="text" id="id_direccion" name="Direccion"
                            placeholder="Ingrese el tel&eacute;fono" maxlength="9">
                    </div>
                    <div class="form-group">
                        <label for="id_tipo_habitacion" class="control-label">Tipo Habitacion</label>
                        <select class="form-control" id="id_tipo_habitacion" name="ID_Tipo_Habitacion">
                            <option value="">[Seleccione]</option>
                        </select>
                    </div>
                </div>

                <!-- Segunda columna -->
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="id_dni" class="control-label">DNI</label> <input
                            class="form-control" type="text" id="id_dni" name="Documento_Identidad"
                            placeholder="Ingrese el DNI" maxlength="8">
                    </div>

                    <div class="form-group">
                        <label for="id_correo" class="control-label">Correo</label> <input
                            class="form-control" type="text" id="id_correo" name="Correo_Electronico"
                            placeholder="Ingrese el correo" maxlength="50">
                    </div>
                    

                </div>
            </div>

            <div class="row" style="margin-top: 2%" align="center">
                <button id="id_btn_registrar" type="button" class="btn btn-primary">Registrar Cliente</button>
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
                                },
                                apellidos: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Los Apellidos son requeridos'
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
                                    selector: "#id_dni",
                                    validators:{
                                        notEmpty: {
                                            message: 'El DNI es obligatorio'
                                        },
                                        regexp: {
                                            regexp: /^[0-9]{8}$/,
                                            message: 'El DNI es 8 d&iacute;gitos'
                                        }
                                    }
                                },
                                correo: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Debe ingresar un correo v&aacute;lido'
                                        },
                                        emailAddress: {
                                			message: 'Debe tener formato de correo'
                               			 },
                                    }
                                },
                                fechaNacimiento: {
                                    selector : "#id_fechaNacimiento",
                                    validators : {
                                        notEmpty : {
                                            message : 'La fecha de nacimiento es requerida'
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
                url: "registraCliente",
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

    $.getJSON("cargaTipoHabitacion", {}, function(data) {
        $.each(data, function(index, item) {
            $("#id_tipo_habitacion").append("<option value=" +  item.ID_Tipo_Habitacion +" >" + item.Nombre_Tipo + "</option>");
        });
    });

   function limpiarFormulario() {
        $('#id_nombres').val('');
        $('#id_apellidos').val('');
        $('#id_telefono').val('');
        $('#id_dni').val('');
        $('#id_correo').val('');
        $('#id_fechaNacimiento').val('');
        $('#id_pais').val('');
    }
</script>
</body>
</html>