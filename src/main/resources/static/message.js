/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
function leerMensajes() {
//FUNCION GET
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Message/all',
        type: 'GET',
        dataType: 'json',

        success: function (mensaje) {
            let ms = mensaje;
            $("#mensaje").empty();
            for (i = 0; i < ms.length; i++) {
                $("#mensaje").append(ms[i].idMessage + "<b>" + ms[i].messageText + "</b>");
                $("#mensaje").append("<button onclick='borrarMensaje(" + ms[i].idMessage + ")'>Borrar</button><br>");

            }
        },
        error: function (xhr, status) {
            alert('Ha ocurrido un problema');
        }
    });
}


function guardarMensaje() {
    let idMensaje = $("#idMensaje").val();
    let nombre = $("#comentarios").val();

    let data = {
        idMeessage: idMensaje,
        messageText: nombre
    };

    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Message/save',
        type: 'POST',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (loky) {
            $("#idMensaje").val("");
            $("#comentarios").val("");
            alert("Operacion exitosa.");
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerMensajes();
        }
    });
}

function editarMensaje() {
    let idMensaje = $("#idMensaje").val();
    let nombre = $("#comentarios").val();

    let data = {
        idMessage: idMensaje,
        messageText: nombre
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Message/update',
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (loky) {
            if ($("#idMensaje").val() === "") {
                alert("El campo esta Id Mensaje esta vacio");
                $("#idMensaje").focus();
                return false;
            }
            ;
            if ($("#comentarios").val() === "") {
                alert("El campo Mensaje de texto esta vacio");
                $("#comentarios").focus();
                return false;
            }
            $("#comentarios").val("");
            alert("Actualizado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerMensajes();
        }
    });
}

function borrarMensaje(idMensaje) {

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Message/'+ idMensaje,
        type: 'DELETE',
        //   dataType : 'json',       
        contentType: 'application/json',
        success: function (loky) {
            $("#idMensaje").val("");
            $("#comentarios").val("");
            alert("Borrado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Ha ocurrido un problema.');
        },
        complete: function () {
            leerMensajes();
        }
    });
}

