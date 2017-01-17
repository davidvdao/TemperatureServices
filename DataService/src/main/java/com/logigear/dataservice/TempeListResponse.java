/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.dataservice;

import com.logigear.dataservice.objectid.SampleOidEntity;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duy.dao
 */
public class TempeListResponse {


    public TempeListResponse(List<SampleOidEntity> entities) {
        entities.forEach((item) -> {
            this.tempList.add(new TempeResponse(item));
        });
    }

    ArrayList<TempeResponse> tempList = new ArrayList<>();

    public List<TempeResponse> getTempList() {
        return tempList;
    }

    public void setTempList(List<SampleOidEntity> entities) {
        this.tempList = new ArrayList<>();
        entities.forEach((item) -> {
            this.tempList.add(new TempeResponse(item));
        });
    }
}
