package com.mobile.sensor.comm.dto;

public enum State {

    STOPPED(0), RUNNING(1), CLOSED(2);

    private int sensorState;

    State (int sensorState) {
        this.sensorState = sensorState;
    }

    public int getSensorState() {
        return this.sensorState;
    }

}
