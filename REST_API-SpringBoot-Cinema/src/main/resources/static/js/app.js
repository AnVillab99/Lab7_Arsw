var funciones = [];
function actualizar() {
	$("#tb1").empty();
	for (var i = 0; i < funciones.length; i++) {
		var numFun = i + 1;
		var nombre = funciones[i].movie.name;
		var sillas = contar(funciones[i].seats);
		var fecha = funciones[i].date;
		window.console.log(numFun + "name=" + nombre + " sillas = " + sillas
				+ " fecha= " + fecha);
		var funcion = "<tr><td>" + numFun + "</td><td>" + nombre
				+ "</td><td>" + sillas + "</td><td>" + fecha + "</td></tr>";
		$("#tb1").append(funcion);

	}
}

function contar(seats) {
	var cont = 0;
	for (var i = 0; i < seats.length; i++) {
		for (var j = 0; j < seats[i].length; j++) {
			if (seats[i][j] == true) {
				cont++;
			}
		}
	}
	return cont;

}

app = (function() {
	var nombre;
	var functiones = [];

	return {
		obtenercinema : function() {

			nombre = document.getElementById("name").value;

			apimock.getCinemaByName(nombre, app.funciones)

		},
		funciones : function(cine) {
			var functionss = JSON.parse(cine);
			functiones = functionss.functions
			funciones = functiones;
			actualizar();

		},

		nombre : function() {
			return nombre;
		}

	}

})();