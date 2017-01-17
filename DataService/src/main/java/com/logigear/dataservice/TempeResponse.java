/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.dataservice;

import com.logigear.dataservice.objectid.SampleOidEntity;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

public class TempeResponse {

    @SuppressWarnings("unused")
    private ObjectId objectId;
    @SuppressWarnings("unused")
    private float temperature;
    @SuppressWarnings("unused")
    private Date time;
    @SuppressWarnings("unused")
    private String message;

    public TempeResponse(SampleOidEntity entity, String message) {
        super();
        this.message = message;
        if (entity != null) {
            this.objectId = entity.getObjectId();
            this.temperature = entity.getTempe();
            this.time = entity.getTime();
        }
    }

    public TempeResponse(SampleOidEntity entity) {
        super();
        if (entity != null) {
            this.objectId = entity.getObjectId();
            this.temperature = entity.getTempe();
            this.time = entity.getTime();
        }
    }
}
