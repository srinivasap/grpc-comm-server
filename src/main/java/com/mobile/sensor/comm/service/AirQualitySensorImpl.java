package com.mobile.sensor.comm.service;

import com.google.common.collect.Lists;
import com.mobile.sensor.cloud.airquality.AirSensorData;
import com.mobile.sensor.cloud.airquality.AirSensorDetails;
import com.mobile.sensor.cloud.airquality.AirSensorRequest;
import com.mobile.sensor.comm.dao.SensorDataDao;
import com.mobile.sensor.comm.dao.SensorDetailsDao;
import com.mobile.sensor.comm.dto.SensorData;
import com.mobile.sensor.comm.dto.SensorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@Service
public class AirQualitySensorImpl {

    @Autowired
    private AirQualitySensorConsumer airQualitySensorConsumer;

    @Autowired
    private SensorDetailsDao sensorDetailsDao;

    @Autowired
    private SensorDataDao sensorDataDao;

    public SensorDetails getAirSensorDetails(String ipaddress, int port, String authtoken) {
        AirSensorDetails wsd = airQualitySensorConsumer.getAirSensorDetails(ipaddress, port, authtoken);
        SensorDetails sensorData = new SensorDetails(wsd);
        sensorData.setClusterId("cluster-1");
        sensorDetailsDao.insert(sensorData);
        return sensorData;
    }

    public SensorData getLatestAirSensorData(String ipaddress, int port, String authtoken) {
        AirSensorData wsd = airQualitySensorConsumer.getLatestAirSensorData(ipaddress, port, authtoken);
        SensorData sensorData = new SensorData(wsd);
        sensorData = sensorDataDao.insertSensorData(sensorData);
        return sensorData;
    }

    public List<SensorData> streamAirSensorData(String ipaddress, int port, String authtoken, int timeSpan, String timeUnit) {
        AirSensorRequest.TimeUnit unit = AirSensorRequest.TimeUnit.SECOND;
        if (!StringUtils.isEmpty(timeUnit)) {
            try {
                unit = AirSensorRequest.TimeUnit.valueOf(timeUnit.toUpperCase());
            } catch (Exception e) {}
        }
        List<AirSensorData> wsd = airQualitySensorConsumer.streamAirSensorData(ipaddress, port, authtoken, timeSpan, unit);
        List<SensorData> data = Lists.newArrayList();
        for (AirSensorData d : wsd) {
            SensorData sd = new SensorData(d);
            sd = sensorDataDao.insertSensorData(sd);
            data.add(sd);
        }
        return data;
    }
}
