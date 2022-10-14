function leerReservation() {
//FUNCION GET
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Reservation/all',
        type: 'GET',
        dataType: 'json',

        success: function (reservacion) {
            let cp = reservacion;
            $("#reservacion").empty();
            for (i = 0; i < cp.length; i++) {
                $("#reservacion").append(cp[i].idReservation + " <b>" + cp[i].startDate + "</b> " + cp[i].devolutionDate + " " + cp[i].status);
                $("#reservacion").append("<button onclick='borrarReservation(" + cp[i].idReservation + ")'>Borrar</button><br>");
            }

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema');
        }
    });
}

function guardarReservation() {
    let idReservation = $("#idReservation").val();
    let inicio = $("#iniciofecha").val();
    let final = $("#finfecha").val();
    let estado = $("#estado").val();

    let data = {
        idReservation: idReservation,
        startDate: inicio,
        devolutionDate: final,
        status: estado

    };

    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Reservation/save',
        type: 'POST',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (reserva) {
            $("#idReservation").val("");
            $("#iniciofecha").val("");
            $("#finfecha").val("");
            $("#estado").val("");
            alert("Guardado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerReservation();
        }
    });


}

function editarReservation() {
    let idReservation = $("#idReservation").val();
    let inicio = $("#iniciofecha").val();
    let final = $("#finfecha").val();
    let estado = $("#estado").val();

    let data = {
        idReservation: idReservation,
        startDate: inicio,
        devolutionDate: final,
        status: estado
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Reservation/update',
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (reser) {
            if ($("#idReservation").val() === "") {
                alert("El campo Id Reservation esta vacio");
                $("#idReservation").focus();
                return false;
            } else {
                if ($("#iniciofecha").val() === "") {
                    alert("El campo Fecha de alquilamiento esta vacia");
                    $("#iniciofecha").focus();
                    return false;
                } else {
                    if ($("#finfecha").val() === "") {
                        alert("El campo Fin de alquilamiento esta vacio");
                        $("#finfecha").focus();
                        return false;
                    } else {
                        if ($("#estado").val() === "") {
                            alert("El campo Estado esta vacio");
                            $("#estado").focus();
                            return false;
                        } else {
                            alert("Actualizado exitosamente.");
                        }
                    }
                }
            }
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerReservation();
        }
    });

}

function borrarCategory(idReservation) {
    let data = {  
        idReservation: idReservation
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Reservation/{id}',
        type: 'DELETE',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (adios) {
            $("#idReservation").val("");
            $("#iniciofecha").val("");
            $("#finfecha").val("");
            $("#estado").val("");
            alert("Borrado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Ha ocurrido un problema');
        },
        complete: function () {
            leerReservation();
        }
    });
}
