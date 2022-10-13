
function leerCategoria() {
//FUNCION GET
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Category/all',
        type: 'GET',
        dataType: 'json',

        success: function (categoria) {
            let cb = categoria.items;
            $("#categoria").empty();
            for (i = 0; i < cb.length; i++) {
                $("#categoria").append(cb[i].id + " <b>" + cb[i].name + "</b> " + cb[i].description);
                $("#categoria").append("<button onclick='borrarCategory(" + cb[i].id + ")'>Borrar</button><br>");
            }

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema');
        }
    });
}

function guardarCategory() {
    let idCategory = $("#idCategory").val();
    let Name = $("#nombre").val();
    let Description = $("#Description").val();

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
            $("#nombre").val("");
            $("#Description").val("");
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
    let Name = $("#nombre").val();
    let Description = $("#Description").val();

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
                if ($("#nombre").val() === "") {
                    alert("El campo Nombre esta vacio");
                    $("#nombre").focus();
                    return false;
                } else {
                    if ($("#Description").val() === "") {
                        alert("El campo Descripción esta vacio");
                        $("#Description").focus();
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
    let data = {  
        id: idCategory
    };
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Category/{id}',
        type: 'DELETE',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (category) {
            $("#idCategory").val("");
            $("#nombre").val("");
            $("#Description").val("");
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
