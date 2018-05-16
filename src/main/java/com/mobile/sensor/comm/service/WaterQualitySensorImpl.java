package com.mobile.sensor.comm.service;

import com.google.common.collect.Lists;
import com.mobile.sensor.cloud.waterquality.WaterSensorData;
import com.mobile.sensor.cloud.waterquality.WaterSensorDetails;
import com.mobile.sensor.cloud.waterquality.WaterSensorRequest;
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
public class WaterQualitySensorImpl {

    @Autowired
    private WaterQualitySensorConsumer waterQualitySensorConsumer;

    @Autowired
    private SensorDetailsDao sensorDetailsDao;

    @Autowired
    private SensorDataDao sensorDataDao;

    public SensorDetails getWaterSensorDetails(String ipaddress, int port, String authtoken) {
        WaterSensorDetails wsd = waterQualitySensorConsumer.getWaterSensorDetails(ipaddress, port, authtoken);
        SensorDetails sensorData = new SensorDetails(wsd);
        sensorData.setClusterId("cluster-1");
        sensorDetailsDao.insert(sensorData);
        return sensorData;
    }

    public SensorData getLatestWaterSensorData(String ipaddress, int port, String authtoken) {
        WaterSensorData wsd = waterQualitySensorConsumer.getLatestWaterSensorData(ipaddress, port, authtoken);
        SensorData sensorData = new SensorData(wsd);
        sensorData = sensorDataDao.insertSensorData(sensorData);
        return sensorData;
    }

    public List<SensorData> streamWaterSensorData(String ipaddress, int port, String authtoken, int timeSpan, String timeUnit) {
        WaterSensorRequest.TimeUnit unit = WaterSensorRequest.TimeUnit.SECOND;
        if (!StringUtils.isEmpty(timeUnit)) {
            try {
                unit = WaterSensorRequest.TimeUnit.valueOf(timeUnit.toUpperCase());
            } catch (Exception e) {}
        }
        List<WaterSensorData> wsd = waterQualitySensorConsumer.streamWaterSensorData(ipaddress, port, authtoken, timeSpan, unit);
        List<SensorData> data = Lists.newArrayList();
        for (WaterSensorData d : wsd) {
            SensorData sd = new SensorData(d);
            sd = sensorDataDao.insertSensorData(sd);
            data.add(sd);
        }
        return data;
    }
}
