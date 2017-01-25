package com.vansasolution.cambodianlivefinder.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 1/24/2017.
 */

public class ResD {
    @SerializedName("CODE")
    public String CODE;
    @SerializedName("MESSAGE")
    public String MESSAGE;
    @SerializedName("DATA")
    public DATA DATA;

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public ResD.DATA getDATA() {
        return DATA;
    }

    public void setDATA(ResD.DATA DATA) {
        this.DATA = DATA;
    }

    public class DATA{
        @SerializedName("rest_id")
        public String rest_id;
        @SerializedName("rest_name")
        public String rest_name;
        @SerializedName("about")
        public String about;
        @SerializedName("latitude")
        public String latitude;
        @SerializedName("longitude")
        public String longitude;

        public String getRest_id() {
            return rest_id;
        }

        public void setRest_id(String rest_id) {
            this.rest_id = rest_id;
        }

        public String getRest_name() {
            return rest_name;
        }

        public void setRest_name(String rest_name) {
            this.rest_name = rest_name;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
