package com.mobile.sensor.comm.dao;

import com.mobile.sensor.comm.dto.SensorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@Component
public class SensorDetailsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SensorDetails insert(SensorDetails newRecord) {
        Object[] param = new Object[] {
                newRecord.getLatitude(),
                newRecord.getLongitude(),
                newRecord.getSensorId(),
                newRecord.getOwner(),
                newRecord.getStatus(),
                newRecord.getIpaddress(),
                newRecord.getType(),
                newRecord.getClusterId()
        };
        String sqlQuery = "INSERT INTO SENSOR_LIST(LATITUDE,LONGITUDE,SENSOR_ID,OWNER,STATUS,IP_ADDRESS,TYPE,CLUSTER_ID) VALUES (?,?,?,?,?,?,?,?)";
        int status = jdbcTemplate.update(sqlQuery, param);
        return newRecord;
    }

    public SensorDetails findBySensorId(int sensorId) {
        String sqlQuery = "SELECT * FROM SENSOR_LIST WHERE SENSOR_ID = ?";

        SensorDetails record = jdbcTemplate.query(sqlQuery, new Object[] {sensorId},
                (rs, rowNum)->  new SensorDetails(rs)).get(0);
        return record;
    }

    public List<SensorDetails> findAllSensor() {
        String sqlQuery = "SELECT * FROM SENSOR_LIST";

        List<SensorDetails> records = jdbcTemplate.query(sqlQuery, new Object[] {},
                (rs, rowNum)->  new SensorDetails(rs))
                .stream().collect(Collectors.toList());
        return records;
    }
}
