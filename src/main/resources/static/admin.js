function leerAdmin() {
//FUNCION GET
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Admin/all',
        type: 'GET',
        dataType: 'json',

        success: function (administradores) {
            let ad = administradores;
            $("#administradores").empty();
            for (i = 0; i < ad.length; i++) {                             
                $("#administradores").append(ad[i].name + "</b> " + ad[i].email+ " ");
                $("#administradores").append("<button onclick='borrarAdmin background-color:#FF0000;(" + ad[i].idAdmin + ")'>Borrar</button><br>");
            }

        },
        error: function (xhr, status) {
            alert('ha sucedido un problema');
        }
    });
}

function guardarAdmin() {
    let idAdmin = $("#idAdmin").val();
    
    let nombre = $("#nombre").val();
     
    let correo= $ ("#email").val();
     
    let contrasena = $("#password").val();
   
    
    let data = {
        idAdmin: idAdmin,
        name: nombre,
        email: correo,
        passsword: contrasena
        
    };

    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: 'http://132.145.243.225:8080/api/Admin/save',
        type: 'POST',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (admin) {
            $("#idAdmin").val("");          
            $("#nombre").val("");            
            $("#email").val("");           
            $("#password").val("");               
            alert("Guardado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Llena todos los espacios');
        },
        complete: function () {
            leerAdmin();
        }
    });


}

function borrarAdmin(idAdmin) {
    
    $.ajax({
        url: 'http://132.145.243.225:8080/api/Admin/'+ idAdmin,
        type: 'DELETE',
        //   dataType : 'json',        
        contentType: 'application/json',
        success: function (admin) {
            $("#idAdmin").val("");
            $("#nombre").val("");
            $("#email").val("");
            $("#password").val("");  
            alert("Borrado exitosamente.");
        },
        error: function (xhr, status) {
            alert('Ha ocurrido un problema');
        },
        complete: function () {
            leerAdmin();
        }
    });
}



