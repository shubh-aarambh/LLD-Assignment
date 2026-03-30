package elevator;

import enums.Direction;
import enums.ElevatorState;
import models.Passenger;
import models.Request;

public class ElevatorController {
    private final Elevator elevator;

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
    }

    public void processRequest(Request request) {
        System.out.println("[Controller - Elevator " + elevator.getElevatorId()
                + "] Processing request from Floor " + request.getSourceFloor()
                + " to Floor " + request.getDestinationFloor()
                + " | Direction: " + request.getDirection());
        elevator.addDestinationFloor(request.getSourceFloor());
        elevator.addDestinationFloor(request.getDestinationFloor());
        request.markServiced();
    }

    public void boardPassenger(Passenger passenger) {
        elevator.boardPassenger(passenger);
    }

    public void runElevatorCycle() {
        if (elevator.getState() == ElevatorState.MOVING || !elevator.getDestinationFloors().isEmpty()) {
            elevator.moveToNextFloor();
        } else {
            System.out.println("[Elevator " + elevator.getElevatorId() + "] Is currently IDLE.");
        }
    }

    public void runUntilIdle() {
        int maxIterations = 30;
        int iterations = 0;
        while ((elevator.getState() == ElevatorState.MOVING || !elevator.getDestinationFloors().isEmpty())
                && iterations < maxIterations) {
            elevator.moveToNextFloor();
            iterations++;
        }
    }

    public void openDoor() {
        elevator.openDoor();
    }

    public void closeDoor() {
        elevator.closeDoor();
    }

    public void showStatus() {
        elevator.showStatus();
    }

    public Elevator getElevator() {
        return elevator;
    }

    public int getCurrentFloor() {
        return elevator.getCurrentFloor();
    }

    public Direction getDirection() {
        return elevator.getDirection();
    }

    public ElevatorState getState() {
        return elevator.getState();
    }

    public boolean isAtCapacity() {
        return elevator.isAtCapacity();
    }
}
