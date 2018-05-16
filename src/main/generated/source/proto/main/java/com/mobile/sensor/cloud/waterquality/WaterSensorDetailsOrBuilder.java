// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: waterqualitysensor.proto

package com.mobile.sensor.cloud.waterquality;

public interface WaterSensorDetailsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:WaterSensorDetails)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string ipaddress = 1;</code>
   */
  java.lang.String getIpaddress();
  /**
   * <code>string ipaddress = 1;</code>
   */
  com.google.protobuf.ByteString
      getIpaddressBytes();

  /**
   * <code>float latitude = 2;</code>
   */
  float getLatitude();

  /**
   * <code>float longitue = 3;</code>
   */
  float getLongitue();

  /**
   * <code>string provider = 4;</code>
   */
  java.lang.String getProvider();
  /**
   * <code>string provider = 4;</code>
   */
  com.google.protobuf.ByteString
      getProviderBytes();

  /**
   * <code>.WaterSensorDetails.Type type = 6;</code>
   */
  int getTypeValue();
  /**
   * <code>.WaterSensorDetails.Type type = 6;</code>
   */
  com.mobile.sensor.cloud.waterquality.WaterSensorDetails.Type getType();

  /**
   * <code>.WaterSensorDetails.Status status = 5;</code>
   */
  int getStatusValue();
  /**
   * <code>.WaterSensorDetails.Status status = 5;</code>
   */
  com.mobile.sensor.cloud.waterquality.WaterSensorDetails.Status getStatus();
}