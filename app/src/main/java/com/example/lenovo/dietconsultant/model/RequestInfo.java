package com.example.lenovo.dietconsultant.model;

/**
 * Created by lenovo on 05-04-2018.
 */

public class RequestInfo {


    /**
     * request : change my dinner
     * created : 1523375023363
     * name : pratul
     * ownerId : null
     * updated : null
     * objectId : 65B0C959-4B0D-1330-FFB8-9C9876467900
     * email : sawantpratul1012@gmail.com
     * ___class : request
     */

    private String request;
    private long created;
    private String name;
    private Object ownerId;
    private Object updated;
    private String objectId;
    private String email;
    private String ___class;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String get___class() {
        return ___class;
    }

    public void set___class(String ___class) {
        this.___class = ___class;
    }
}
