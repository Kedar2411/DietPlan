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
    private String food_type;
    private String food_time;

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getFood_time() {
        return food_time;
    }

    public void setFood_time(String food_time) {
        this.food_time = food_time;
    }
}
