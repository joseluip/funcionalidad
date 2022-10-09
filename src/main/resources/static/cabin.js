function leerCabana() {
//FUNCION GET
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Cabin/all',
        type: 'GET',
        dataType: 'json',

        success: function (cabana) {
            let cb = cabana.items;
            $("#cabana").empty();
            for (i = 0; i < cb.length; i++) {
                $("#cabana").append(cb[i].id + " <b>" + cb[i].brand + "</b> " + cb[i].rooms + " " + cb[i].category_id + " " + cb[i].name);
                $("#cabana").append("<button onclick='borrarCabana(" + cb[i].id + ")'>Borrar</button><br>");
            }

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema');
        }
    });
}

function guardarCabana() {
    let idCabana = $("#idCabana").val();
    let Tipo = $("#Marca").val();
    let Marcas = $("#Habitaciones").val();
    let numero = $("#id_category").val();
    let persona = $("#nombre").val();

    let data = {
        id: idCabana,
        brand: Tipo,
        rooms: Marcas,
        category_id: numero,
        name: persona
    };

    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Cabin/save',
        type: 'POST',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (cabina) {
            $("#idCabana").val("");
            $("#Marca").val("");
            $("#Habitaciones").val("");
            $("#id_category").val("");
            $("#nombre").val("");
            alert("Guardado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerCabana();
        }
    });


}

function editarCabana() {
    let idCabana = $("#idCabana").val();
    let Tipo = $("#Marca").val();
    let Marcas = $("#Habitaciones").val();
    let numero = $("#id_category").val();
    let persona = $("#nombre").val();

    let data = {
        id: idCabana,
        brand: Tipo,
        rooms: Marcas,
        category_id: numero,
        name: persona
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: '',
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (cabina) {
            if ($("#idCabana").val() === "") {
                alert("El campo Id Cabaña esta vacio");
                $("#idCabana").focus();
                return false;
            } else {
                if ($("#Marca").val() === "") {
                    alert("El campo Brand esta vacio");
                    $("#Marca").focus();
                    return false;
                } else {
                    if ($("#Habitaciones").val() === "") {
                        alert("El campo Rooms esta vacio");
                        $("#Habitaciones").focus();
                        return false;
                    } else {
                        if ($("#id_category").val() === "") {
                            alert("El campo Category_id esta vacio");
                            return false;
                        } else {
                            if ($("#nombre").val() === "") {
                                alert("El campo Name esta vacio");
                                $("#nombre").focus();
                                return false;
                            } else {
                                alert("Actualizado exitosamente.");
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
            leerCabana();
        }
    });

}

function borrarCabana(idCabana) {
    let data = {
        id: idCabana
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: '',
        type: 'DELETE',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (cabina) {
            $("#idCabana").val("");
            $("#Marca").val("");
            $("#Habitaciones").val("");
            $("#id_category").val("");
            $("#nombre").val("");
            alert("Borrado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Ha ocurrido un problema');
        },
        complete: function () {
            leerCabana();
        }
    });
}


    