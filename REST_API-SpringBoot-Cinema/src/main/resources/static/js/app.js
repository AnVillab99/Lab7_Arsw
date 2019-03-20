app=(function(){
    var nombre;
    var funciones =[];

    




  

	return {
		obtenercinema:function(){
			
			nombre=document.getElementById("name").value;
			alert(nombre);
			apimock.getCinemaByName(nombre,app.funciones)

		},
		funciones:function(cine){
			funciones=cine;
			alert(funciones);

		}
		
	}	

})();