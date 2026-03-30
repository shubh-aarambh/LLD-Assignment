package models;

import enums.Direction;

public class Request {
    private static int idCounter = 1;
    private final int requestId;
    private final int sourceFloor;
    private final int destinationFloor;
    private final Direction direction;
    private boolean isServiced;

    public Request(int sourceFloor, int destinationFloor) {
        this.requestId = idCounter++;
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.direction = destinationFloor > sourceFloor ? Direction.UP : Direction.DOWN;
        this.isServiced = false;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isServiced() {
        return isServiced;
    }

    public void markServiced() {
        this.isServiced = true;
    }
}
