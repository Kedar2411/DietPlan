package com.example.lenovo.dietconsultant.model;

/**
 * Created by lenovo on 05-04-2018.
 */

public class RequestInfo {

    /**
     * sub_type : apple
     * created : 1522951867587
     * food_type : fruit
     * ownerId : null
     * updated : null
     * objectId : 07358E31-5EA2-2110-FF53-E58AAEC80F00
     * food_time : lunch
     * ___class : request
     */

    private String sub_type;
    private long created;
    private String food_type;
    private Object ownerId;
    private Object updated;
    private String objectId;
    private String food_time;
    private String ___class;

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public Object getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Object ownerId) {
        this.ownerId = ownerId;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getFood_time() {
        return food_time;
    }

    public void setFood_time(String food_time) {
        this.food_time = food_time;
    }

    public String get___class() {
        return ___class;
    }

    public void set___class(String ___class) {
        this.___class = ___class;
    }
}
