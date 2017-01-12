package com.logigear.tempedataservice.uuid;

import org.restexpress.plugin.hyperexpress.Linkable;
import com.logigear.tempedataservice.Constants;
import com.logigear.tempedataservice.serialization.UuidFormatter;

import com.strategicgains.hyperexpress.annotation.BindToken;
import com.strategicgains.hyperexpress.annotation.TokenBindings;
import com.strategicgains.repoexpress.mongodb.AbstractUuidMongodbEntity;

/**
 * This is a sample entity identified by a UUID (instead of a MongoDB ObjectID).
 * It also contains createdAt and updatedAt properties that are automatically
 * maintained by the persistence layer (SampleUuidEntityRepository).
 */
@TokenBindings({
    @BindToken(value = Constants.Url.SAMPLE_ID, field = "id", formatter = UuidFormatter.class)
})
public class SampleUuidEntity
        extends AbstractUuidMongodbEntity
        implements Linkable {

    public SampleUuidEntity() {
    }

    public SampleUuidEntity(float _tempo) {
        this._tempo = _tempo;
    }
    float _tempo;

    public float getTempo() {
        return _tempo;
    }

    public void setTempo(float _tempo) {
        this._tempo = _tempo;
    }
}
