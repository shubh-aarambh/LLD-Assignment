package models;

import enums.Direction;

public class ExternalDisplay {
    private int currentFloor;
    private Direction direction;

    public ExternalDisplay() {
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
    }

    public void update(int currentFloor, Direction direction) {
        this.currentFloor = currentFloor;
        this.direction = direction;
    }

    public void show() {
        System.out.println("[External Display] Floor: " + currentFloor + " | Direction: " + direction);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
