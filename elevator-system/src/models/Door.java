package models;

import enums.DoorState;
import enums.ElevatorState;

public class Door {
    private DoorState doorState;

    public Door() {
        this.doorState = DoorState.CLOSED;
    }

    public void open(ElevatorState elevatorState) {
        if (elevatorState == ElevatorState.IDLE) {
            this.doorState = DoorState.OPEN;
        }
    }

    public void close() {
        this.doorState = DoorState.CLOSED;
    }

    public DoorState getDoorState() {
        return doorState;
    }
}
