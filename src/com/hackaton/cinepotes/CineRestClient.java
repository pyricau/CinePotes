package com.hackaton.cinepotes;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.hackaton.cinepotes.model.Cinemas;
import com.hackaton.cinepotes.model.Sessions;

@Rest(rootUrl = "http://192.168.0.19:8080", converters = { GsonHttpMessageConverter.class })
public interface CineRestClient {

	@Get("/kinolist")
	Cinemas getCinemas();

	@Get("/sessions")
	Sessions getSessions();

}
