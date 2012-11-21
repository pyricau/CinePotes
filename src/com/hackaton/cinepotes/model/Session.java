package com.hackaton.cinepotes.model;

import java.util.Date;

public class Session {

	public Film film;

	public Cinema kino;

	public Date datetime;

	@Override
	public String toString() {
		return "Session [film=" + film + ", kino=" + kino + ", datetime=" + datetime + "]";
	}

}
