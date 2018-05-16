package com.mobile.sensor.comm.service;

import com.google.common.collect.Lists;
import com.mobile.sensor.cloud.airquality.AirQualitySensorGrpc;
import com.mobile.sensor.cloud.airquality.AirSensorData;
import com.mobile.sensor.cloud.airquality.AirSensorDetails;
import com.mobile.sensor.cloud.airquality.AirSensorRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;

/**
 * @author Srinivasa Prasad Sunnapu
 */
@Component
public class AirQualitySensorConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(AirQualitySensorConsumer.class);

    public AirSensorDetails getAirSensorDetails(String ipaddress, int port, String authtoken) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ipaddress, port)
                                .usePlaintext()
                                .build();
        AirQualitySensorGrpc.AirQualitySensorBlockingStub stub = AirQualitySensorGrpc.newBlockingStub(channel);
        AirSensorRequest request = AirSensorRequest.newBuilder().setAuthToken(authtoken).build();
        AirSensorDetails sensorDetails = stub.getAirSensorDetails(request);
        LOG.info("AQS provider {}", sensorDetails.getProvider());
        return sensorDetails;
    }

    public AirSensorData getLatestAirSensorData(String ipaddress, int port, String authtoken) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ipaddress, port)
                .usePlaintext()
                .build();
        AirQualitySensorGrpc.AirQualitySensorBlockingStub stub = AirQualitySensorGrpc.newBlockingStub(channel);
        AirSensorRequest request = AirSensorRequest.newBuilder().setAuthToken(authtoken).build();
        AirSensorData airSensorData = stub.getAirSensorData(request);
        LOG.info("AQS state {}", airSensorData.getState());
        return airSensorData;
    }

    public List<AirSensorData> streamAirSensorData(String ipaddress, int port, String authtoken, int timeSpan, AirSensorRequest.TimeUnit unit) {
        List<AirSensorData> data = Lists.newArrayList();
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ipaddress, port)
                .usePlaintext()
                .build();
        AirQualitySensorGrpc.AirQualitySensorBlockingStub stub = AirQualitySensorGrpc.newBlockingStub(channel);
        AirSensorRequest request = AirSensorRequest.newBuilder()
                .setAuthToken(authtoken).setTimeSpan(timeSpan)
                .setTimeUnit(unit)
                .build();
        Iterator<AirSensorData> dataItr = stub.streamAirSensorData(request);
        while (dataItr.hasNext()) {
            data.add(dataItr.next());
        }
        LOG.info("AQS data size {}", data.size());
        return data;
    }

    @PostConstruct
    public void init() {

    }
}
