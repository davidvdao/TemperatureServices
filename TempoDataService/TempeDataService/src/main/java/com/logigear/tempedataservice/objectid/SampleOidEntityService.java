package com.logigear.tempedataservice.objectid;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import java.util.List;

import org.restexpress.common.query.QueryFilter;
import org.restexpress.common.query.QueryOrder;
import org.restexpress.common.query.QueryRange;

import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.syntaxe.ValidationEngine;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.TimeZone;
import jdk.nashorn.internal.objects.NativeArray;
import org.restexpress.common.query.FilterOperator;

/**
 * This is the 'service' or 'business logic' layer, where business logic,
 * syntactic and semantic domain validation occurs, along with calls to the
 * persistence layer.
 */
public class SampleOidEntityService {

    private SampleOidEntityRepository samples;

    public SampleOidEntityService(SampleOidEntityRepository samplesRepository) {
        super();
        this.samples = samplesRepository;
    }

    public SampleOidEntity create(SampleOidEntity entity) {
        ValidationEngine.validateAndThrow(entity);
        return samples.create(entity);
    }

    public SampleOidEntity read(Identifier id) {
        return samples.read(id);
    }

    public void update(SampleOidEntity entity) {
        ValidationEngine.validateAndThrow(entity);
        samples.update(entity);
    }

    public void delete(Identifier id) {
        samples.delete(id);
    }

    public List<SampleOidEntity> readAll(QueryFilter filter, QueryRange range, QueryOrder order) {
        return samples.readAll(filter, range, order);
    }

    public long count(QueryFilter filter) {
        return samples.count(filter);
    }

    public List<SampleOidEntity> getEntities(float tempe) {
        QueryFilter inQuery = new QueryFilter();
        inQuery.addCriteria("temperature", FilterOperator.EQUALS, tempe);
        return samples.find(inQuery);
    }
    
    public List<Float> getTempeData(Date date) throws ParseException{
    QueryFilter inQuery = new QueryFilter();
        
        //TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        //df.setTimeZone(tz);
        
        String strFromDate = df.format(date);
        Date toDate = new Date();
        toDate.setTime(date.getTime() + 1 * 24 * 60 * 60 * 1000);
        String strToDate = df.format(toDate);

        inQuery.addCriteria("time", FilterOperator.GREATER_THAN_OR_EQUAL_TO, df.parse(strFromDate));
        inQuery.addCriteria("time", FilterOperator.LESS_THAN, df.parse(strToDate));
        List<SampleOidEntity> entities = samples.find(inQuery);
        ArrayList<Float> result = new ArrayList<Float>();
        for (SampleOidEntity item : entities) {
            result.add(item.temperature);
        }
        return result;
    }
    
    public List<SampleOidEntity> getTemperatureInDate(Date date) throws ParseException{
        QueryFilter inQuery = new QueryFilter();
        
        //TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        //df.setTimeZone(tz);
        
        String strFromDate = df.format(date);
        Date toDate = new Date();
        toDate.setTime(date.getTime() + 1 * 24 * 60 * 60 * 1000);
        String strToDate = df.format(toDate);

        inQuery.addCriteria("time", FilterOperator.GREATER_THAN_OR_EQUAL_TO, df.parse(strFromDate));
        inQuery.addCriteria("time", FilterOperator.LESS_THAN, df.parse(strToDate));
        return samples.find(inQuery);
    }
    
    public float getAvrTempe(Date date) throws ParseException {
        QueryFilter inQuery = new QueryFilter();
        
        //TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        //df.setTimeZone(tz);
        
        String strFromDate = df.format(date);
        Date toDate = new Date();
        toDate.setTime(date.getTime() + 1 * 24 * 60 * 60 * 1000);
        String strToDate = df.format(toDate);

        inQuery.addCriteria("time", FilterOperator.GREATER_THAN_OR_EQUAL_TO, df.parse(strFromDate));
        inQuery.addCriteria("time", FilterOperator.LESS_THAN, df.parse(strToDate));
        List<SampleOidEntity> entities = samples.find(inQuery);
        float result = 0;
        for (SampleOidEntity item : entities) {
            result += item.temperature;
        }
        return result/entities.size();
    }
}
