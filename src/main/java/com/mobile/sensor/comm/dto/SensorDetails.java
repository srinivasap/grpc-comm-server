package com.mobile.sensor.comm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mobile.sensor.cloud.airquality.AirSensorDetails;
import com.mobile.sensor.cloud.waterquality.WaterSensorDetails;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorDetails {

    @JsonProperty("sensor_identifier")
    private String sensorId;
    @JsonProperty("sensor_latitude")
    private String latitude;
    @JsonProperty("sensor_longitude")
    private String longitude;
    @JsonProperty("sensor_owner")
    private String owner;
    @JsonProperty("sensor_status")
    private String status;
    @JsonProperty("sensor_created_on")
    private Date createdOn;
    @JsonProperty("sensor_updated_on")
    private Date updatedOn;
    @JsonProperty("sensor_ipaddress")
    private String ipaddress;
    @JsonProperty("sensor_port")
    private String port;
    @JsonProperty("sensor_type")
    private String type;
    @JsonProperty("sensor_id")
    private int id;
    @JsonProperty("sensor_cluster_id")
    private String clusterId;

    DateTimeFormatter dtf;

    public SensorDetails() {
        dtf = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public SensorDetails(AirSensorDetails asd) {
        sensorId = asd.getIpaddress();
        latitude = String.valueOf(asd.getLatitude());
        longitude = String.valueOf(asd.getLongitue());
        owner = asd.getProvider();
        status = asd.getStatus().name();
        ipaddress = asd.getIpaddress();
        type = asd.getType().name();
    }

    public SensorDetails(WaterSensorDetails wsd) {
        sensorId = wsd.getIpaddress();
        latitude = String.valueOf(wsd.getLatitude());
        longitude = String.valueOf(wsd.getLongitue());
        owner = wsd.getProvider();
        status = wsd.getStatus().name();
        ipaddress = wsd.getIpaddress();
        type = wsd.getType().name();
    }

    public SensorDetails(ResultSet rs) throws SQLException {
        sensorId = rs.getString("SENSOR_ID");
        latitude = rs.getString("LATITUDE");
        longitude = rs.getString("LONGITUDE");
        owner = rs.getString("OWNER");
        status = rs.getString("STATUS");
        createdOn = rs.getDate("TIME_CREATED");
        updatedOn = rs.getDate("TIME_UPDATED");
        ipaddress = rs.getString("IP_ADDRESS");
        type = rs.getString("TYPE");
        //port = rs.getString("");
        id = rs.getInt("ID");
        clusterId = rs.getString("CLUSTER_ID");
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }
}
