/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.filters.Filtro;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */

@Service
public class InMemoryCinemaPersistence implements CinemaPersitence {

	private final Map<String, Cinema> cinemas = new HashMap<>();

	@Autowired
	public Filtro filtro;

	public void setFiltro(Filtro fil) {
		filtro = fil;
	}

	public Filtro fil() {
		return filtro;
	}

	public InMemoryCinemaPersistence() {
		// load stub data
		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		functionDate = "2018-12-18 17:30";
		CinemaFunction funct12 = new CinemaFunction(new Movie("examen", "Horror"), functionDate);
		CinemaFunction funct22 = new CinemaFunction(new Movie("copia", "Action"), functionDate);
		functions.add(funct12);
		functions.add(funct22);

		Cinema c = new Cinema("cinemaX", functions);
		cinemas.put("cinemaX", c);

		List<CinemaFunction> functionsC2 = new ArrayList<>();
		CinemaFunction funct1C2 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
		CinemaFunction funct2C2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
		functionsC2.add(funct1C2);
		functionsC2.add(funct2C2);
		functionDate = "2018-12-18 17:30";
		CinemaFunction funct12C2 = new CinemaFunction(new Movie("examen", "Horror"), functionDate);
		CinemaFunction funct22C2 = new CinemaFunction(new Movie("copia", "Action"), functionDate);
		functionsC2.add(funct12C2);
		functionsC2.add(funct22C2);
		Cinema c2 = new Cinema("Procinal", functionsC2);
		cinemas.put("Cine Colombia", c2);

		List<CinemaFunction> functionsC3 = new ArrayList<>();
		CinemaFunction funct1C3 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
		CinemaFunction funct2C3 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
		functionsC3.add(funct1C3);
		functionsC3.add(funct2C3);
		functionDate = "2018-12-18 17:30";
		CinemaFunction funct12C3 = new CinemaFunction(new Movie("examen", "Horror"), functionDate);
		CinemaFunction funct22C3 = new CinemaFunction(new Movie("copia", "Action"), functionDate);
		functionsC3.add(funct12C3);
		functionsC3.add(funct22C3);

		Cinema c3 = new Cinema("Procinal", functionsC3);
		cinemas.put("Procinal", c3);
	}

	@Override
	public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
		Cinema c = null;

		try {
			c = getCinema(cinema);
		} catch (CinemaPersistenceException e) {

			e.printStackTrace();
			return;
		}
		for (CinemaFunction f : c.getFunctions()) {
			if (f.getMovie().getName().equals(movieName) && f.getDate().equals(date)) {
				f.buyTicket(row, col);
			}
		}

	}

	@Override
	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
		Cinema c = null;
		List<CinemaFunction> cf = new ArrayList<CinemaFunction>();

		try {
			c = getCinema(cinema);
		} catch (CinemaPersistenceException e) {

			e.printStackTrace();

			return null;
		}
		for (CinemaFunction f : c.getFunctions()) {

			if (f.getDate().equals(date) || f.getDate().substring(0, 10).equals(date)) {
				cf.add(f);
			}
		}

		return cf;
	}

	@Override
	public void saveCinema(Cinema c) throws CinemaPersistenceException {
		if (cinemas.containsKey(c.getName())) {
			throw new CinemaPersistenceException("The given cinema already exists: " + c.getName());
		} else {
			cinemas.put(c.getName(), c);
		}
	}

	@Override
	public Cinema getCinema(String name) throws CinemaPersistenceException {
		return cinemas.get(name);
	}

	public Map<String, Cinema> getCinemas() {
		return cinemas;
	}

	@Override
	public List<Movie> filter(String cinema, String date, String filter) throws CinemaPersistenceException {

		return filtro.filter(getCinema(cinema), date, filter);
	}

	@Override
	public void registerFunctionInCinema(String c, CinemaFunction cf) {
		cinemas.get(c).registerFunction(cf);

	}

	@Override
	public void updateFunction(String c, CinemaFunction cf) {
		if(existeFunction(c,cf)) {
			
			int i=0;
			for(CinemaFunction f:cinemas.get(c).getFunctions()) {
				System.out.println(f.getMovie().getName());
				if(f.getDate().equals(cf.getDate()) && f.getMovie().getName().equals(cf.getMovie().getName())) {
					cinemas.get(c).getFunctions().remove(i);					
				}	
				i++;
			}
			
			
			
		}
		cinemas.get(c).registerFunction(cf);
		
	}

	@Override
	public boolean existeFunction(String c, CinemaFunction cf) {
		boolean i = false;
		for (CinemaFunction f : cinemas.get(c).getFunctions()) {
			if (f.getDate().equals(cf.getDate()) && f.getMovie().getName().equals(cf.getMovie().getName())) {
				i = true;
			}
		}
		return i;
	}

	
}
