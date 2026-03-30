package system;

import dispatch.Dispatcher;
import elevator.Elevator;
import elevator.ElevatorController;
import enums.Direction;
import models.Passenger;
import models.Request;
import panels.FloorPanel;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private static final int TOTAL_FLOORS = 15;
    private static final int TOTAL_ELEVATORS = 3;

    private static ElevatorSystem instance;

    private final List<ElevatorController> controllers;
    private final List<FloorPanel> floorPanels;
    private final Dispatcher dispatcher;

    private ElevatorSystem() {
        controllers = new ArrayList<>();
        for (int i = 1; i <= TOTAL_ELEVATORS; i++) {
            Elevator elevator = new Elevator(i, TOTAL_FLOORS);
            controllers.add(new ElevatorController(elevator));
        }

        floorPanels = new ArrayList<>();
        for (int i = 1; i <= TOTAL_FLOORS; i++) {
            floorPanels.add(new FloorPanel(i, TOTAL_FLOORS, TOTAL_ELEVATORS));
        }

        dispatcher = new Dispatcher();
        System.out.println("[ElevatorSystem] Initialized with " + TOTAL_ELEVATORS
                + " elevators and " + TOTAL_FLOORS + " floors.");
    }

    public static ElevatorSystem getInstance() {
        if (instance == null) {
            instance = new ElevatorSystem();
        }
        return instance;
    }

    public void requestElevator(int sourceFloor, int destinationFloor) {
        if (!isValidFloor(sourceFloor) || !isValidFloor(destinationFloor)) {
            System.out.println("[ElevatorSystem] Invalid floor number. Valid range: 1 to " + TOTAL_FLOORS);
            return;
        }
        if (sourceFloor == destinationFloor) {
            System.out.println("[ElevatorSystem] Source and destination are the same floor.");
            return;
        }

        Request request = new Request(sourceFloor, destinationFloor);
        Direction callDirection = request.getDirection();

        FloorPanel panel = floorPanels.get(sourceFloor - 1);
        if (callDirection == Direction.UP) {
            panel.pressUp();
        } else {
            panel.pressDown();
        }

        ElevatorController assigned = dispatcher.dispatch(controllers, request);
        if (assigned != null) {
            assigned.processRequest(request);
        }
    }

    public void boardPassengerToElevator(int elevatorId, Passenger passenger) {
        ElevatorController controller = getControllerById(elevatorId);
        if (controller != null) {
            controller.boardPassenger(passenger);
        }
    }

    public void runCycleForElevator(int elevatorId) {
        ElevatorController controller = getControllerById(elevatorId);
        if (controller != null) {
            controller.runElevatorCycle();
        }
    }

    public void runAllElevatorsUntilIdle() {
        for (ElevatorController controller : controllers) {
            controller.runUntilIdle();
        }
    }

    public void showAllElevatorStatuses() {
        System.out.println("\n========== ELEVATOR SYSTEM STATUS ==========");
        for (ElevatorController controller : controllers) {
            controller.showStatus();
        }
        System.out.println("============================================\n");
    }

    public void syncFloorDisplays() {
        for (FloorPanel panel : floorPanels) {
            for (int i = 0; i < controllers.size(); i++) {
                ElevatorController ctrl = controllers.get(i);
                panel.updateElevatorDisplay(i, ctrl.getCurrentFloor(), ctrl.getDirection());
            }
        }
    }

    public void showFloorPanel(int floorNumber) {
        if (!isValidFloor(floorNumber)) {
            System.out.println("[ElevatorSystem] Invalid floor number.");
            return;
        }
        floorPanels.get(floorNumber - 1).showAllDisplays();
    }

    private ElevatorController getControllerById(int elevatorId) {
        for (ElevatorController ctrl : controllers) {
            if (ctrl.getElevator().getElevatorId() == elevatorId) {
                return ctrl;
            }
        }
        System.out.println("[ElevatorSystem] Elevator ID " + elevatorId + " not found.");
        return null;
    }

    private boolean isValidFloor(int floor) {
        return floor >= 1 && floor <= TOTAL_FLOORS;
    }

    public List<ElevatorController> getControllers() {
        return controllers;
    }

    public List<FloorPanel> getFloorPanels() {
        return floorPanels;
    }

    public int getTotalFloors() {
        return TOTAL_FLOORS;
    }

    public int getTotalElevators() {
        return TOTAL_ELEVATORS;
    }
}
