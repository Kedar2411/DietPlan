package com.example.lenovo.dietconsultant.model;

/**
 * Created by lenovo on 06-04-2018.
 */

public class DietInfo {


    /**
     * lunch : potato
     * created : 1523002689022
     * ownerId : null
     * snacks : cornflex
     * breakfast : apple
     * updated : null
     * dinner : ladyfinger
     * objectId : 65E19293-4371-868A-FFBC-E412E7485900
     * ___class : Diet
     * user_diet : null
     */

    private String lunch;
    private String snacks;
    private String breakfast;
    private String dinner;
    private Object user_diet;

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getSnacks() {
        return snacks;
    }

    public void setSnacks(String snacks) {
        this.snacks = snacks;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public Object getUser_diet() {
        return user_diet;
    }

    public void setUser_diet(Object user_diet) {
        this.user_diet = user_diet;
    }
}
