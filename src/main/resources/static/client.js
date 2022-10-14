/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
function leerClientes() {
//FUNCION GET
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Client/all',
        type: 'GET',
        dataType: 'json',

        success: function (clientes) {
            let cs = clientes;
            $("#listaClientes").empty();
            for (i = 0; i < cs.length; i++) {
                $("#listaClientes").append(cs[i].idClient + " <b>" + cs[i].name + "</b> " + cs[i].email + " " + cs[i].password + " " + cs[i].age);
                $("#listaClientes").append("<button onclick='borrarCliente(" + cs[i].idClient + ")'>Borrar</button><br>");

            }
        },
        error: function (xhr, status) {
            alert('ha sucedido un problema');
        }
    });
}

function guardarCliente() {
    let idCliente = $("#idCliente").val();
    let nombre = $("#nombreCliente").val();
    let contrasena = $("#password").val();
    let mailCliente = $("#emailCliente").val();
    let edad = $("#edadCliente").val();

    let data = {
        idClient: idCliente,
        name: nombre,
        password: contrasena,
        email: mailCliente,
        age: edad
    };

    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Client/save',
        type: 'POST',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {
            $("#idCliente").val("");
            $("#nombreCliente").val("");
            $("#emailCliente").val("");
            $("#password").val("");
            $("#edadCliente").val("");
            alert("Operacion exitosa.");
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerClientes();
        }
    });

}

function editarCliente() {
    let idCliente = $("#idCliente").val();
    let nombre = $("#nombreCliente").val();
    let contrasena = $("#password").val();
    let mailCliente = $("#emailCliente").val();
    let edad = $("#edadCliente").val();

    let data = {
        idClient: idCliente,
        name: nombre,
        password: contrasena,
        email: mailCliente,
        age: edad
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Client/update',
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {
            if ($("#idCliente").val() === "") {
                alert("El campo Id de Cliente esta vacio");
                $("#idCliente").focus();
                return false;
            } else {
                if ($("#nombreCliente").val() === "") {
                    alert("El campo Nombre de Cliente esta vacio");
                    $("#nombreCliente").focus();
                    return false;
                } else {
                    if ($("#mailCliente").val() === "") {
                        alert("El campo Correo de Cliente esta vacio");
                        $("#mailCliente").focus();
                        return false;
                    } else {
                        if ($("#password").val() === "") {
                            alert("El campo Contraseña esta vacio");
                            $("#password").focus();
                            return false;
                        } else {
                            if ($("#edadCliente").val() === "") {
                                alert("El campo Edad de Cliente esta vacio");
                                $("#edadCliente").focus();
                                return false;
                            } else {
                                alert("Acutalizado exitosamente");
                            }
                        }
                    }
                }
            }
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerClientes();
        }
    });

}

function borrarCliente(idCliente) {
    let data = {
        idCliente: idCliente
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Client/{id}',
        type: 'DELETE',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {
            $("#idCliente").val("");
            $("#nombreCliente").val("");
            $("#emailCliente").val("");
            $("#password").val("");
            $("#edadCliente").val("");
            alert("Borrado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Ha ocurrido un problema');
        },
        complete: function () {
            leerClientes();
        }
    });

}

