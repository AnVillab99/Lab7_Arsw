//@author dsaavedra

import * as mock from 'apimock.js';
app=(function(){
    var nombre;
    var funciones =[];

    




  

	return {
		getCinemaByName:function(name,callback){
			callback(
				mockdata[name]
			);
		}
	}	

})();