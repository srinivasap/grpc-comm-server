package com.mobile.sensor.comm.service;

import com.google.common.collect.Lists;
import com.mobile.sensor.cloud.waterquality.WaterQualitySensorGrpc;
import com.mobile.sensor.cloud.waterquality.WaterSensorData;
import com.mobile.sensor.cloud.waterquality.WaterSensorDetails;
import com.mobile.sensor.cloud.waterquality.WaterSensorRequest;
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
public class WaterQualitySensorConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(WaterQualitySensorConsumer.class);

    public WaterSensorDetails getWaterSensorDetails(String ipaddress, int port, String authtoken) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ipaddress, port)
                                    .usePlaintext()
                                    .build();
        WaterQualitySensorGrpc.WaterQualitySensorBlockingStub stub = WaterQualitySensorGrpc.newBlockingStub(channel);
        WaterSensorRequest request = WaterSensorRequest.newBuilder().setAuthToken(authtoken).build();
        WaterSensorDetails sensorDetails = stub.getWaterSensorDetails(request);
        LOG.info("WQS provider {}", sensorDetails.getProvider());
        return sensorDetails;
    }

    public WaterSensorData getLatestWaterSensorData(String ipaddress, int port, String authtoken) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ipaddress, port)
                .usePlaintext()
                .build();
        WaterQualitySensorGrpc.WaterQualitySensorBlockingStub stub = WaterQualitySensorGrpc.newBlockingStub(channel);
        WaterSensorRequest request = WaterSensorRequest.newBuilder().setAuthToken(authtoken).build();
        WaterSensorData waterSensorData = stub.getWaterSensorData(request);
        LOG.info("WQS state {}", waterSensorData.getState());
        return waterSensorData;
    }

    public List<WaterSensorData> streamWaterSensorData(String ipaddress, int port, String authtoken, int timeSpan, WaterSensorRequest.TimeUnit unit) {
        List<WaterSensorData> data = Lists.newArrayList();
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ipaddress, port)
                .usePlaintext()
                .build();
        WaterQualitySensorGrpc.WaterQualitySensorBlockingStub stub = WaterQualitySensorGrpc.newBlockingStub(channel);
        WaterSensorRequest request = WaterSensorRequest.newBuilder()
                                        .setAuthToken(authtoken).setTimeSpan(timeSpan)
                                        .setTimeUnit(unit)
                                        .build();
        Iterator<WaterSensorData> dataItr = stub.streamWaterSensorData(request);
        while (dataItr.hasNext()) {
            data.add(dataItr.next());
        }
        LOG.info("WQS data size {}", data.size());
        return data;
    }

    @PostConstruct
    public void init() {

    }
}
