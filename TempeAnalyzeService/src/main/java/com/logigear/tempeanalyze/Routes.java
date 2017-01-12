package com.logigear.tempeanalyze;

import io.netty.handler.codec.http.HttpMethod;

import org.restexpress.RestExpress;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
    {
		//TODO: Your routes here...
		server.uri("/your/route/here/{sampleId}.{format}", config.getSampleController())
			.method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
			.name(Constants.Routes.SINGLE_SAMPLE);

		server.uri("/your/route/here.{format}", config.getSampleController())
			.action("readAll", HttpMethod.GET)
			.method(HttpMethod.POST)
			.name(Constants.Routes.SAMPLE_COLLECTION);
                
                
		server.uri("/analyze/temperature/average", config.getSampleController())
                    .action("average", HttpMethod.GET)
		    .name(Constants.Routes.GET_AVR_TEMPERATURE);

		server.uri("/analyze/temperature/max", config.getSampleController())
                    .action("max", HttpMethod.GET)
		    .name(Constants.Routes.GET_MAX_TEMPERATURE);
                
		server.uri("/analyze/temperature/min", config.getSampleController())
                    .action("min", HttpMethod.GET)
		    .name(Constants.Routes.GET_MIN_TEMPERATURE);
// or...
//		server.regex("/some.regex", config.getRouteController());
    }
}