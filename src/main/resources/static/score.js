function leerScore() {
//FUNCION GET
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Score/all',
        type: 'GET',
        dataType: 'json',

        success: function (score) {
            let cw = score;
            $("#score").empty();
            for (i = 0; i < cw.length; i++) {
                $("#score").append(cw[i].idScore + " <b>" + cw[i].messageText + "</b> " + cw[i].stars);
                $("#score").append("<button onclick='borrarScore(" + cw[i].idScore + ")'>Borrar</button><br>");
            }

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema');
        }
    });
}

function guardarScore() {
    let idScore = $("#idScore").val();
    let message = $("#comentarios").val();
    let estrellas = $("#stars").val();

    let data = {
        idScore: idScore,
        messageText: message,
        stars: estrellas

    };

    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Score/save',
        type: 'POST',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (iroman) {
            $("#idScore").val("");
            $("#comentarios").val("");
            $("#stars").val("");
            alert("Guardado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerScore();
        }
    });


}

function editarScore() {
    let idScore = $("#idScore").val();
    let message = $("#comentarios").val();
    let estrellas = $("#stars").val();

    let data = {
        idScore: idScore,
        messageText: message,
        stars: estrellas
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Score/update',
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (blackg) {
            if ($("#idScore").val() === "") {
                alert("El campo Id Score esta vacio");
                $("#idScore").focus();
                return false;
            } else {
                if ($("#comentarios").val() === "") {
                    alert("El campo Cuentanos tu experiencia esta vacio");
                    $("#comentarios").focus();
                    return false;
                } else {
                    if ($("#stars").val() === "") {
                        alert("El campo Rstrellas esta vacio");
                        $("#stars").focus();
                        return false;
                    } else {
                        alert("Actualizado exitosamente.");
                    }
                }
            }
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerScore();
        }
    });

}

function borrarScore(idScore) {
    
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Score/'+ idScore,
        type: 'DELETE',
        //   dataType : 'json',        
        contentType: 'application/json',
        success: function (hola) {
            $("#idScore").val("");
            $("#comentarios").val("");
            $("#stars").val("");            
            alert("Borrado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Ha ocurrido un problema');
        },
        complete: function () {
            leerScore();
        }
    });
}
