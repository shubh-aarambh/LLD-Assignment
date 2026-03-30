package models;

import enums.Direction;

public class InternalDisplay {
    private int currentFloor;
    private Direction direction;
    private int currentPassengerCount;
    private double currentWeight;
    private static final int MAX_CAPACITY_PERSONS = 8;
    private static final double MAX_CAPACITY_KG = 680.0;

    public InternalDisplay() {
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.currentPassengerCount = 0;
        this.currentWeight = 0.0;
    }

    public void update(int currentFloor, Direction direction, int passengerCount, double weight) {
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.currentPassengerCount = passengerCount;
        this.currentWeight = weight;
    }

    public void show() {
        System.out.println("[Internal Display] Floor: " + currentFloor
                + " | Direction: " + direction
                + " | Occupancy: " + currentPassengerCount + "/" + MAX_CAPACITY_PERSONS
                + " | Weight: " + currentWeight + "/" + MAX_CAPACITY_KG + " kg");
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCurrentPassengerCount() {
        return currentPassengerCount;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }
}
