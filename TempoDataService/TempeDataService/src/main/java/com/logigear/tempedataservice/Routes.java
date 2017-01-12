package com.logigear.tempedataservice;

import io.netty.handler.codec.http.HttpMethod;

import org.restexpress.RestExpress;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
	{
		// TODO: Your routes here...
		server.uri("/samples/uuid/{uuid}.{format}", config.getSampleUuidEntityController())
		    .method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
		    .name(Constants.Routes.SINGLE_UUID_SAMPLE);

		server.uri("/data/temperaturelist/{date}", config.getSampleOidEntityController())
		    .action("readTemperatureInDate", HttpMethod.GET)
		    .method(HttpMethod.POST)
		    .name(Constants.Routes.SAMPLE_UUID_COLLECTION);

		server.uri("/samples/oid/{uuid}.{format}", config.getSampleOidEntityController())
		    .method(HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
		    .name(Constants.Routes.SINGLE_OID_SAMPLE);

		server.uri("/data/temperature", config.getSampleOidEntityController())
		    .action("create", HttpMethod.PUT)
		    .method(HttpMethod.POST)
		    .name(Constants.Routes.SAMPLE_OID_COLLECTION);

		server.uri("/data/temperature/date", config.getSampleOidEntityController())
                    .action("read", HttpMethod.GET)
		    .name(Constants.Routes.GET_AVR_TEMPERATURE);

//		server.uri("/data/temperaturelist/{date}", config.getSampleOidEntityController())
//                    .action("getTempList", HttpMethod.GET)
//		    .name(Constants.Routes.GET_LIST_TEMPERATURE);
                
		server.uri("/data/temperature/latest", config.getSampleOidEntityController())
                    .action("getLatest", HttpMethod.GET)
		    .method(HttpMethod.GET)
		    .name(Constants.Routes.GET_LAST_TEMPERATURE);
                
		// or REGEX matching routes...
		// server.regex("/some.regex", config.getRouteController());
	}
}
