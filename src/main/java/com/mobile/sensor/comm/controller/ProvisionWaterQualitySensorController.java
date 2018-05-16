package com.mobile.sensor.comm.controller;

import com.mobile.sensor.comm.dto.SensorData;
import com.mobile.sensor.comm.dto.SensorDetails;
import com.mobile.sensor.comm.model.ErrorMessage;
import com.mobile.sensor.comm.service.WaterQualitySensorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@RestController
public class ProvisionWaterQualitySensorController {

    private static final Logger LOG = LoggerFactory.getLogger(ProvisionWaterQualitySensorController.class);

    @Autowired
    private WaterQualitySensorImpl sensorService;

    @RequestMapping(
            value = "/provision/waterquality/sensor/add",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSensor(
            @RequestParam(value="ipaddress", defaultValue="127.0.0.1") String ipaddress,
            @RequestParam(value="port", defaultValue="8130") String port,
            @RequestParam(value="authtoken", defaultValue="authtoken") String authtoken) {

        SensorDetails sensorDetails = null;
        ResponseEntity<?> response = null;
        try {
            sensorDetails = sensorService.getWaterSensorDetails(ipaddress, Integer.valueOf(port), authtoken);
            response = new ResponseEntity<>(sensorDetails, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Error", e);
            response = new ResponseEntity<>(new ErrorMessage("Error processing request, please check request and try again"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(
            value = "/provision/waterquality/sensor/data/latest",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchLatestSensorData(
            @RequestParam(value="ipaddress", defaultValue="127.0.0.1") String ipaddress,
            @RequestParam(value="port", defaultValue="8130") String port,
            @RequestParam(value="authtoken", defaultValue="authtoken") String authtoken) {

        SensorData sensorData = null;
        ResponseEntity<?> response = null;
        try {
            sensorData = sensorService.getLatestWaterSensorData(ipaddress, Integer.valueOf(port), authtoken);
            response = new ResponseEntity<>(sensorData, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Error", e);
            response = new ResponseEntity<>(new ErrorMessage("Error processing request, please check request and try again"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(
            value = "/provision/waterquality/sensor/data/historic",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetchSensorData(
            @RequestParam(value="ipaddress", defaultValue="127.0.0.1") String ipaddress,
            @RequestParam(value="port", defaultValue="8130") String port,
            @RequestParam(value="authtoken", defaultValue="authtoken") String authtoken,
            @RequestParam(value="timespan", defaultValue="10") String timeSpan,
            @RequestParam(value="timeunit", defaultValue="SECOND") String timeUnit) {

        List<SensorData> sensorData = null;
        ResponseEntity<?> response = null;
        try {
            sensorData = sensorService.streamWaterSensorData(ipaddress, Integer.valueOf(port), authtoken, Integer.valueOf(timeSpan), timeUnit);
            response = new ResponseEntity<>(sensorData, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Error", e);
            response = new ResponseEntity<>(new ErrorMessage("Error processing request, please check request and try again"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

}
