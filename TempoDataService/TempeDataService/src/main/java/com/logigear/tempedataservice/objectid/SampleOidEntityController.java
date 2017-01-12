package com.logigear.tempedataservice.objectid;

import java.util.List;

import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.common.query.QueryFilter;
import org.restexpress.common.query.QueryOrder;
import org.restexpress.common.query.QueryRange;
import org.restexpress.query.QueryFilters;
import org.restexpress.query.QueryOrders;
import org.restexpress.query.QueryRanges;
import com.logigear.tempedataservice.Constants;
import com.logigear.tempedataservice.TempeResponse;

import com.strategicgains.hyperexpress.builder.DefaultTokenResolver;
import com.strategicgains.hyperexpress.builder.DefaultUrlBuilder;
import com.strategicgains.hyperexpress.builder.UrlBuilder;
import com.strategicgains.repoexpress.mongodb.Identifiers;

import io.netty.handler.codec.http.HttpMethod;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 * This is the 'controller' layer, where HTTP details are converted to domain
 * concepts and passed to the service layer. Then service layer response
 * information is enhanced with HTTP details, if applicable, for the response.
 * <p/>
 * This controller demonstrates how to process an entity that is identified by a
 * MongoDB ObjectId.
 */
public class SampleOidEntityController {

    private static final UrlBuilder LOCATION_BUILDER = new DefaultUrlBuilder();
    private SampleOidEntityService service;

    public SampleOidEntityController(SampleOidEntityService sampleService) {
        super();
        this.service = sampleService;
    }

    public TempeResponse create(Request request, Response response) {
        SampleOidEntity entity = request.getBodyAs(SampleOidEntity.class, "Resource details not provided");
        if(entity.time == null)
            return new TempeResponse(null, "The time field is required");
        SampleOidEntity saved = service.create(entity);

        // Construct the response for create...
        response.setResponseCreated();

        // Include the Location header...
        String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_OID_SAMPLE);
        response.addLocationHeader(LOCATION_BUILDER.build(locationPattern, new DefaultTokenResolver()));

        // Return the newly-created resource...
        
        return new TempeResponse(saved, "Add temperature data successfull");
    }

    public float read(Request request, Response response) throws ParseException {
        String id = request.getHeader("date", "No resource ID supplied");
        //SampleOidEntity entity = service.read(Identifiers.MONGOID.parse(id));

        DateFormat df = new SimpleDateFormat("mmddyyyy");
        Date startDate = null;
        try {
            startDate = df.parse(id);
        } catch (ParseException ex) {
            Logger.getLogger(SampleOidEntityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        float avr = service.getAvrTempe(startDate);
        return avr;
    }

//    public List<Float> getTempList(Request request, Response response) throws ParseException {
//        String id = request.getHeader("date", "No resource ID supplied");
//        //SampleOidEntity entity = service.read(Identifiers.MONGOID.parse(id));
//
//        DateFormat df = new SimpleDateFormat("mmddyyyy");
//        Date startDate = null;
//        try {
//            startDate = df.parse(id);
//        } catch (ParseException ex) {
//            Logger.getLogger(SampleOidEntityController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //float avr = service.getAvrTempe(startDate);
//        return service.getTempeData(startDate);
//    }

    public TempeResponse getLatest(Request request, Response response) {
        QueryFilter filter = QueryFilters.parseFrom(request);
        QueryOrder order = QueryOrders.parseFrom(request);
        QueryRange range = QueryRanges.parseFrom(request, 20);
        List<SampleOidEntity> entities = service.readAll(filter, range, order);
        long count = service.count(filter);
        response.setCollectionResponse(range, entities.size(), count);
        if (entities.isEmpty()) {
            return null;
        } else {
            return new TempeResponse(entities.get(entities.size() - 1), "Get latest temperature");
        }
    }

    public List<TempeResponse> readAll(Request request, Response response) {
        QueryFilter filter = QueryFilters.parseFrom(request);
        QueryOrder order = QueryOrders.parseFrom(request);
        QueryRange range = QueryRanges.parseFrom(request, 20);
        List<SampleOidEntity> entities = service.readAll(filter, range, order);
        long count = service.count(filter);
        ArrayList<TempeResponse> result = new ArrayList<>();
        entities.forEach((item) -> {
            result.add(new TempeResponse(item));
        });
        response.setCollectionResponse(range, result.size(), count);
        return result;
    }

    public List<TempeResponse> readTemperatureInDate(Request request, Response response) throws ParseException {
        String id = request.getHeader("date", "No resource ID supplied");
        //SampleOidEntity entity = service.read(Identifiers.MONGOID.parse(id));

        DateFormat df = new SimpleDateFormat("mmddyyyy");
        Date startDate = null;
        try {
            startDate = df.parse(id);
        } catch (ParseException ex) {
            Logger.getLogger(SampleOidEntityController.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<SampleOidEntity> entities = service.getTemperatureInDate(startDate);
        ArrayList<TempeResponse> result = new ArrayList<>();
        entities.forEach((item) -> {
            result.add(new TempeResponse(item));
        });
        return result;
    }

    public void update(Request request, Response response) {
        String id = request.getHeader(Constants.Url.SAMPLE_ID, "No resource ID supplied");
        SampleOidEntity entity = request.getBodyAs(SampleOidEntity.class, "Resource details not provided");
        entity.setId(Identifiers.MONGOID.parse(id));
        service.update(entity);
        response.setResponseNoContent();
    }

    public void delete(Request request, Response response) {
        String id = request.getHeader(Constants.Url.SAMPLE_ID, "No resource ID supplied");
        service.delete(Identifiers.MONGOID.parse(id));
        response.setResponseNoContent();
    }
}