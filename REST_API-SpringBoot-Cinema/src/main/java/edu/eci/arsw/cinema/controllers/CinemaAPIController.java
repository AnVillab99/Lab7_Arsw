/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;

/**
 *
 * @author cristian
 */
@RestController
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {
	@Autowired
	public CinemaServices cs;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> manejadorGetRecurso() {

		// try {
		// obtener datos que se enviarán a través del API

		return new ResponseEntity<>(cs.getAllCinemas(), HttpStatus.ACCEPTED);
		/*
		 * } catch (ResourceNotFoundException ex) {
		 * Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null,
		 * ex); return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND); }
		 */
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> getByName(@PathVariable String name) {
		ResponseEntity<?> re = null;
		try {
			re = new ResponseEntity<>(cs.getCinemaByName(name), HttpStatus.ACCEPTED);
		} catch (CinemaException e) {
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (CinemaPersistenceException e) {
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return re;

		// ...
	}

	@GetMapping("/{name}/{date}")
	public ResponseEntity<?> getByDate(@PathVariable String name, @PathVariable String date) {
		ResponseEntity<?> re = null;

		re = new ResponseEntity<>(cs.getFunctionsbyCinemaAndDate(name, date), HttpStatus.ACCEPTED);
		if (re.equals(null)) {
			re = new ResponseEntity<>("No hay funcciones en ese cinema en esa fecha", HttpStatus.NOT_FOUND);

		}
		return re;

		// ...
	}

	@GetMapping("/{name}/{date}/{moviename}")
	public ResponseEntity<?> getMovieName(@PathVariable String name, @PathVariable String date,
			@PathVariable String moviename) {
		ResponseEntity<?> re = null;

		List<CinemaFunction> cf = cs.getFunctionsbyCinemaAndDate(name, date);
		for (CinemaFunction f : cf) {
			if (f.getMovie().getName().equals(moviename)) {
				re = new ResponseEntity<>(f.getMovie(), HttpStatus.ACCEPTED);
			}
		}
		if (re.equals(null)) {
			re = new ResponseEntity<>("No hay funcciones en ese cinema en esa fecha", HttpStatus.NOT_FOUND);

		}
		return re;

	}

	@PostMapping("/{name}")
	public ResponseEntity<?> registerFucntion(@PathVariable String name, @RequestBody CinemaFunction c) {
		ResponseEntity<?> re = null;

		try {
			if (!cs.getCinemaByName(name).getFunctions().contains(c)) {
				cs.registerFunctionInCinema(name, c);
				re = new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				re = new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);

			}
		} catch (CinemaException e) {

			re = new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
			e.printStackTrace();
			return re;
		} catch (CinemaPersistenceException e) {

			re = new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
			e.printStackTrace();
			return re;
		}
		return re;

	}
	
	@PutMapping("/{name}")
	public ResponseEntity<?> updateFunction(@PathVariable String name, @RequestBody CinemaFunction c) {
		ResponseEntity<?> re = null;

		try {
		
				cs.updateFunction(name, c);
				re = new ResponseEntity<>(HttpStatus.CREATED);
			
		
		} catch (CinemaPersistenceException e) {

			re = new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
			e.printStackTrace();
			return re;
		}
		return re;

	}

}
