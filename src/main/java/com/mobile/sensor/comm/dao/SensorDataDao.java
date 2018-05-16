package com.mobile.sensor.comm.dao;

import com.mobile.sensor.comm.dto.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@Component
public class SensorDataDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SensorData insertSensorData(SensorData newRecord) {
        Object[] param = new Object[] {
                            newRecord.getSensorId(),
                            newRecord.getLatitude(),
                            newRecord.getLongitude(),
                            newRecord.getPressure(),
                            newRecord.getT_25(),
                            newRecord.getSalinity(),
                            newRecord.getConductivity(),
                            newRecord.getRecorded_on(),
                            newRecord.getFlag()
                        };
        String sqlQuery = "INSERT INTO SENSOR_DATA(SENSOR_ID,LATITUDE,LONGITUDE,PRESSURE,TEMPERATURE_C,SALINITY,CONDUCTIVITY,TIME_RECORDED,FLAG) VALUES (?,?,?,?,?,?,?,?,?)";
        int status = jdbcTemplate.update(sqlQuery, param);
        return newRecord;
    }


    public List<SensorData> findBySensorId(int sensorId) {
        String sqlQuery = "SELECT * FROM SENSOR_DATA WHERE SENSOR_ID = ?";

        List<SensorData> records = jdbcTemplate.query(sqlQuery, new Object[] {sensorId},
                (rs, rowNum)->  new SensorData(rs))
                .stream().collect(Collectors.toList());

        return records;
    }

}
