// mi_script.js
function mostrarAlerta() {
    alert("¡Hola desde JavaScript!");
}

//Funcion que me permite validar las patentes	
document.addEventListener("DOMContentLoaded", function () {
    var patenteInput = document.querySelector('input[name="patente"]');
    
    patenteInput.addEventListener("input", function () {
        var patenteValue = patenteInput.value;
        var pattern = /^[A-Z]{1}[0-9]{3}[A-Z]{3}$/;
        
        if (!pattern.test(patenteValue)) {
            patenteInput.setCustomValidity("El formato de la patente no es válido, formato A000AAA.");
        } else {
            patenteInput.setCustomValidity(""); // Restablecer el mensaje de error personalizado.
        }
    });
});


document.addEventListener("DOMContentLoaded", function () {
    var eliminarBotones = document.querySelectorAll(".btn-danger"); // Selecciona todos los botones de eliminar

    eliminarBotones.forEach(function (boton) {
        boton.addEventListener("click", function (event) {
            var confirmacion1 = confirm("¿Estás seguro de que deseas eliminar este vehículo? (Primera confirmación)");

            if (confirmacion1) {
                var contraseña = prompt("Por favor, ingresa tu contraseña para confirmar la eliminación:");

                // Verifica si la contraseña ingresada es correcta (cambia "tu_contraseña" por la contraseña real)
                if (contraseña === "tu_contraseña") {
                    var confirmacion2 = confirm("¡Esta acción no se puede deshacer! ¿Estás seguro? (Segunda confirmación)");

                    if (!confirmacion2) {
                        event.preventDefault(); // Cancela la acción de eliminar si el usuario no confirma la segunda vez
                    }
                } else {
                    alert("Contraseña incorrecta. La eliminación no se ha confirmado.");
                    event.preventDefault(); // Cancela la acción de eliminar si la contraseña es incorrecta
                }
            } else {
                event.preventDefault(); // Cancela la acción de eliminar si el usuario no confirma la primera vez
            }
        });
    });
});