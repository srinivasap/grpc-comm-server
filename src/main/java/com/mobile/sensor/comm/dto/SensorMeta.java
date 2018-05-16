package com.mobile.sensor.comm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorMeta {

    @JsonProperty("sensor_id")
    protected String sensorId;
    @JsonProperty("sensor_name")
    protected String sensorName;
    @JsonProperty("sensor_master_user")
    protected String masterUser;
    @JsonProperty("sensor_ipaddress")
    protected String ipAddress;
    @JsonProperty("sensor_latitude")
    protected String latitude;
    @JsonProperty("sensor_longitude")
    protected String longitude;
    @JsonProperty("sensor_created_on")
    protected String createdTime;
    @JsonProperty("sensor_updated_on")
    protected LocalDateTime timeUpdated;
    @JsonProperty("sensor_state")
    protected State state;
    @JsonProperty("sensor_updated_by")
    protected String lastUpdateBy;

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getMasterUser() {
        return masterUser;
    }

    public void setMasterUser(String masterUser) {
        this.masterUser = masterUser;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime localDateTime) {
        this.timeUpdated = localDateTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb = sb.append("Sensor [id=" + this.sensorId + ",");
        sb = sb.append("Name=" + this.sensorName + ",");
        sb = sb.append("UserID=" + this.masterUser + ",");
        sb = sb.append("IP=" + this.ipAddress + ",");
        sb = sb.append("Latituded=" + this.latitude + ",");
        sb = sb.append("Longitude=" + this.longitude + ",");
        sb = sb.append("Created On=" + this.createdTime + ",");
        sb = sb.append("Updtaed On=" + this.timeUpdated + ",");
        sb = sb.append("Updated By=" + this.lastUpdateBy + ",");
        sb = sb.append("Sate=" + this.state);

        return sb.toString();
    }
}
