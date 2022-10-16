
function leerCategoria() {
//FUNCION GET
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Category/all',
        type: 'GET',
        dataType: 'json',

        success: function (categoria) {
            let cl = categoria;
            $("#categoria").empty();
            for (i = 0; i < cl.length; i++) {
                $("#categoria").append(cl[i].id + " <b>" + cl[i].name + "</b> " + cl[i].description);
                $("#categoria").append("<button onclick='borrarCategory(" + cl[i].id + ")'>Borrar</button><br>");
            }

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema');
        }
    });
}

function guardarCategory() {
    let idCategory = $("#idCategory").val();
    let Name = $("#Nombre").val();
    let Description = $("#descripcion").val();

    let data = {
        id: idCategory,
        name: Name,
        description: Description

    };

    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Category/save',
        type: 'POST',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (category) {
            $("#idCategory").val("");
            $("#Nombre").val("");
            $("#descripcion").val("");
            alert("Guardado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerCategoria();
        }
    });


}

function editarCategory() {
    let idCategory = $("#idCategory").val();
    let Name = $("#Nombre").val();
    let Description = $("#descripcion").val();

    let data = {
        id: idCategory,
        name: Name,
        description: Description
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Category/update',
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (category) {
            if ($("#idCategory").val() === "") {
                alert("El campo Id Category esta vacio");
                $("#idCategory").focus();
                return false;
            } else {
                if ($("#Nombre").val() === "") {
                    alert("El campo Nombre esta vacio");
                    $("#Nombre").focus();
                    return false;
                } else {
                    if ($("#descripcion").val() === "") {
                        alert("El campo Descripción esta vacio");
                        $("#descripcion").focus();
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
            leerCategoria();
        }
    });

}

function borrarCategory(idCategory) {
    
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Category/'+ idCategory,
        type: 'DELETE',
        //   dataType : 'json',        
        contentType: 'application/json',
        success: function (category) {
            $("#idCategory").val("");
            $("#Nombre").val("");
            $("#descripcion").val("");
            alert("Borrado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Ha ocurrido un problema');
        },
        complete: function () {
            leerCategoria();
        }
    });
}
