//@author dsaavedra

apimock = (function() {

	var mockdata = [];
	var func;

	mockdata["Cine80"] = '{"name":"Cine80","functions":[{"movie":{"name":"Titanic Movie","genre":"Action"},"seats":[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]],"date":"2018-12-18 15:30"},{"movie":{"name":"The Purge","genre":"Horror"},"seats":[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]],"date":"2018-12-18 15:30"}]}';

	
	mockdata["Cine112"] = '{"name":"Cine112","functions":[{"movie":{"name":"mision imposible","genre":"Fiction"},"seats":[[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true],[true,true,true,true,true,true,true,true,true,true,true,true]],"date":"2018-12-18 15:31"}]}';
	mockdata["CinePork"] = '{ "name": "CinePork", "functions": [{ "movie": { "name": "examen", "genre": "Drama" }, "seats": [[true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true]], "date": "2019-02-19 15:30" }] }';
    mockdata["Cine Colombia"] = '{ "name": "Cine Colombia", "functions": [{ "movie": { "name": "capitana feminazi", "genre": "PC" }, "seats": [[true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, false, true, true, true, true, true, true, true, false], [true, true, false, true, true, true, false, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, false, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true]], "date": "2019-04-15 01:30" }] }';
    
	return {
		getCinemaByName : function(name, callback) {

			callback(mockdata[name]);

		}

	}

})();
