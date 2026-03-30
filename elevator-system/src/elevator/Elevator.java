package elevator;

import enums.Direction;
import enums.DoorState;
import enums.ElevatorState;
import models.Door;
import models.ExternalDisplay;
import models.InternalDisplay;
import models.Passenger;
import panels.InsidePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Elevator {
    private static final int MAX_CAPACITY_PERSONS = 8;
    private static final double MAX_CAPACITY_KG = 680.0;

    private final int elevatorId;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private final Door door;
    private final ExternalDisplay externalDisplay;
    private final InternalDisplay internalDisplay;
    private final InsidePanel insidePanel;
    private final List<Passenger> passengers;
    private final TreeSet<Integer> destinationFloors;
    private double currentWeight;

    public Elevator(int elevatorId, int totalFloors) {
        this.elevatorId = elevatorId;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.door = new Door();
        this.externalDisplay = new ExternalDisplay();
        this.internalDisplay = new InternalDisplay();
        this.insidePanel = new InsidePanel(elevatorId, totalFloors);
        this.passengers = new ArrayList<>();
        this.destinationFloors = new TreeSet<>();
        this.currentWeight = 0.0;
    }

    public boolean boardPassenger(Passenger passenger) {
        if (passengers.size() >= MAX_CAPACITY_PERSONS) {
            System.out.println("[Elevator " + elevatorId + "] At full person capacity. Cannot board passenger.");
            return false;
        }
        if (currentWeight + passenger.getWeight() > MAX_CAPACITY_KG) {
            System.out.println("[Elevator " + elevatorId + "] Weight limit exceeded. Cannot board passenger.");
            return false;
        }
        passengers.add(passenger);
        currentWeight += passenger.getWeight();
        destinationFloors.add(passenger.getDestinationFloor());
        insidePanel.pressFloorButton(passenger.getDestinationFloor());
        updateDisplays();
        System.out.println("[Elevator " + elevatorId + "] Passenger " + passenger.getPassengerId()
                + " boarded. Destination: Floor " + passenger.getDestinationFloor());
        return true;
    }

    public void disembarkPassengersAtCurrentFloor() {
        List<Passenger> toRemove = new ArrayList<>();
        for (Passenger p : passengers) {
            if (p.getDestinationFloor() == currentFloor) {
                toRemove.add(p);
                currentWeight -= p.getWeight();
                System.out.println("[Elevator " + elevatorId + "] Passenger " + p.getPassengerId()
                        + " disembarked at Floor " + currentFloor + ".");
            }
        }
        passengers.removeAll(toRemove);
        destinationFloors.remove(currentFloor);
        updateDisplays();
    }

    public void openDoor() {
        door.open(state);
        if (door.getDoorState() == DoorState.OPEN) {
            System.out.println("[Elevator " + elevatorId + "] Door OPENED at Floor " + currentFloor + ".");
        } else {
            System.out.println("[Elevator " + elevatorId + "] Cannot open door while moving.");
        }
    }

    public void closeDoor() {
        door.close();
        System.out.println("[Elevator " + elevatorId + "] Door CLOSED.");
    }

    public void moveToNextFloor() {
        if (destinationFloors.isEmpty()) {
            state = ElevatorState.IDLE;
            direction = Direction.IDLE;
            updateDisplays();
            return;
        }

        if (direction == Direction.UP) {
            currentFloor++;
        } else if (direction == Direction.DOWN) {
            currentFloor--;
        }

        System.out
                .println("[Elevator " + elevatorId + "] Moved to Floor " + currentFloor + " | Direction: " + direction);
        updateDisplays();

        if (destinationFloors.contains(currentFloor)) {
            state = ElevatorState.IDLE;
            System.out.println("[Elevator " + elevatorId + "] Stopping at Floor " + currentFloor + ".");
            openDoor();
            disembarkPassengersAtCurrentFloor();
            closeDoor();

            if (!destinationFloors.isEmpty()) {
                determineAndSetDirection();
                state = ElevatorState.MOVING;
            } else {
                state = ElevatorState.IDLE;
                direction = Direction.IDLE;
            }
        }
        updateDisplays();
    }

    private void determineAndSetDirection() {
        if (!destinationFloors.isEmpty()) {
            int nextDestination = destinationFloors.first();
            if (nextDestination > currentFloor) {
                direction = Direction.UP;
            } else if (nextDestination < currentFloor) {
                direction = Direction.DOWN;
            }
        }
    }

    public void addDestinationFloor(int floor) {
        destinationFloors.add(floor);
        if (state == ElevatorState.IDLE) {
            determineAndSetDirection();
            state = ElevatorState.MOVING;
        }
        updateDisplays();
    }

    private void updateDisplays() {
        externalDisplay.update(currentFloor, direction);
        internalDisplay.update(currentFloor, direction, passengers.size(), currentWeight);
    }

    public void showStatus() {
        System.out.println("--- Elevator " + elevatorId + " Status ---");
        externalDisplay.show();
        internalDisplay.show();
        System.out.println("Door: " + door.getDoorState()
                + " | State: " + state
                + " | Pending Floors: " + destinationFloors);
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public boolean isAtCapacity() {
        return passengers.size() >= MAX_CAPACITY_PERSONS || currentWeight >= MAX_CAPACITY_KG;
    }

    public TreeSet<Integer> getDestinationFloors() {
        return destinationFloors;
    }
}
