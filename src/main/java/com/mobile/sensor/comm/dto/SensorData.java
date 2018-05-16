package com.mobile.sensor.comm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mobile.sensor.cloud.airquality.AirParameter;
import com.mobile.sensor.cloud.airquality.AirSensorData;
import com.mobile.sensor.cloud.waterquality.WaterParameter;
import com.mobile.sensor.cloud.waterquality.WaterSensorData;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Map;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorData extends SensorMeta {

    private String coordinates;
    @JsonProperty("sensor_temperature")
    private double t_25;
    @JsonProperty("sensor_pressure")
    private double pressure;
    @JsonProperty("sensor_salinity")
    private double salinity;
    @JsonProperty("sensor_conductivity")
    private double conductivity;
    @JsonProperty("sensor_recorded_on")
    private LocalDateTime recorded_on;
    @JsonProperty("sensor_flag")
    private String flag;

    DateTimeFormatter dtf;

    public SensorData() {
        dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    public SensorData(AirSensorData asd) {
        this();
        latitude = String.valueOf(asd.getSensorDetails().getLatitude());
        longitude = String.valueOf(asd.getSensorDetails().getLongitue());
        sensorId = asd.getSensorDetails().getIpaddress();
        pressure = asd.getPressure();
        t_25 = asd.getTemparature();
        salinity = 0.0d;
        conductivity = 0.0d;
        recorded_on = LocalDateTime.from(dtf.parse(asd.getRecordedTime()));
        flag = asd.getState().name();
    }

    public SensorData(WaterSensorData wsd) {
        this();
        latitude = String.valueOf(wsd.getSensorDetails().getLatitude());
        longitude = String.valueOf(wsd.getSensorDetails().getLongitue());
        sensorId = wsd.getSensorDetails().getIpaddress();
        pressure = wsd.getPressure();
        t_25 = wsd.getTemparature();
        for (Map.Entry<String, WaterParameter> entry : wsd.getParametersMap().entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getAttribute() == WaterParameter.Attribute.PH) {
                    pressure = Double.valueOf(entry.getKey());
                } else if (entry.getValue().getAttribute() == WaterParameter.Attribute.TEMPERATURE) {
                    t_25 = Double.valueOf(entry.getKey());
                } else if (entry.getValue().getAttribute() == WaterParameter.Attribute.TURBIDITY) {
                    salinity = Double.valueOf(entry.getKey());
                } else if (entry.getValue().getAttribute() == WaterParameter.Attribute.CONDUCTIVITY) {
                    conductivity = Double.valueOf(entry.getKey());
                }
            }
        }
        recorded_on = LocalDateTime.from(dtf.parse(wsd.getRecordedTime()));
        flag = wsd.getState().name();
    }

    public SensorData(ResultSet rs) throws SQLException {
        this();
        latitude = rs.getString("LATITUDE");
        longitude = rs.getString("LONGITUDE");
        sensorId = rs.getString("SENSOR_ID");
        pressure = rs.getDouble("PRESSURE");
        salinity = rs.getDouble("SALINITY");
        conductivity = rs.getDouble("CONDUCTIVITY");
        t_25 = rs.getDouble("TEMPERATURE_C");
        Date d = rs.getDate("TIME_RECORDED");
        if (d != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            recorded_on = LocalDateTime.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR), c.get(Calendar.MINUTE));
        }
        d = rs.getDate("UPDATED_TIME");
        if (d != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            timeUpdated = LocalDateTime.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR), c.get(Calendar.MINUTE));
        }
        flag = rs.getString("FLAG");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb = sb.append("Ocean Sensor [id=" + this.sensorId + ",");
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

    public LocalDateTime getRecorded_on() {
        return recorded_on;
    }

    public void setRecorded_on(LocalDateTime recorded_on) {
        this.recorded_on = recorded_on;
    }

    public double getPressure() {
        return pressure;
    }


    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /*	public double getTemperature() {
            return temperature;
        }
        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
    */
    public double getSalinity() {
        return salinity;
    }

    public void setSalinity(double salinity) {
        this.salinity = salinity;
    }

    public double getConductivity() {
        return conductivity;
    }

    public void setConductivity(double conductivity) {
        this.conductivity = conductivity;
    }

    public double getT_25() {
        return t_25;
    }

    public void setT_25(double t_25) {
        this.t_25 = t_25;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
