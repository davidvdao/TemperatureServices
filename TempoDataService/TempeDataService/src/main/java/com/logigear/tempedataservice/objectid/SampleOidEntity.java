package com.logigear.tempedataservice.objectid;

import org.restexpress.plugin.hyperexpress.Linkable;
import com.logigear.tempedataservice.Constants;

import com.strategicgains.hyperexpress.annotation.BindToken;
import com.strategicgains.hyperexpress.annotation.TokenBindings;
import com.strategicgains.repoexpress.mongodb.AbstractMongodbEntity;
import java.util.Date;

/**
 * This is a sample entity identified by a MongoDB ObjectID (instead of a UUID).
 * It also contains createdAt and updatedAt properties that are automatically
 * maintained by the persistence layer (SampleOidEntityRepository).
 */
@TokenBindings({
    @BindToken(value = Constants.Url.SAMPLE_ID, field = "id")
})
public class SampleOidEntity
        extends AbstractMongodbEntity
        implements Linkable {

    public SampleOidEntity() {
    }

    public SampleOidEntity(float _temperature, Date _time) {
        this.temperature = _temperature;
        this.time = _time;
    }
    float temperature;
    Date time;

    public float getTempe() {
        return temperature;
    }

    public void setTempe(float _temperature) {
        this.temperature = _temperature;
    }
    
    public Date getTime() {
        return time;
    }

    public void setTime(Date _time) {
        this.time = _time;
    }
}
