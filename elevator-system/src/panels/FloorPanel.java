package panels;

import models.ExternalDisplay;
import java.util.ArrayList;
import java.util.List;

public class FloorPanel {
    private final int floorNumber;
    private final OutsidePanel outsidePanel;
    private final List<ExternalDisplay> elevatorDisplays;

    public FloorPanel(int floorNumber, int totalFloors, int totalElevators) {
        this.floorNumber = floorNumber;
        this.outsidePanel = new OutsidePanel(floorNumber, totalFloors);
        this.elevatorDisplays = new ArrayList<>();
        for (int i = 0; i < totalElevators; i++) {
            elevatorDisplays.add(new ExternalDisplay());
        }
    }

    public void pressUp() {
        outsidePanel.pressUp();
    }

    public void pressDown() {
        outsidePanel.pressDown();
    }

    public void updateElevatorDisplay(int elevatorIndex, int currentFloor, enums.Direction direction) {
        if (elevatorIndex >= 0 && elevatorIndex < elevatorDisplays.size()) {
            elevatorDisplays.get(elevatorIndex).update(currentFloor, direction);
        }
    }

    public void showAllDisplays() {
        System.out.println("=== Floor " + floorNumber + " Panel Displays ===");
        for (int i = 0; i < elevatorDisplays.size(); i++) {
            System.out.print("Elevator " + (i + 1) + ": ");
            elevatorDisplays.get(i).show();
        }
    }

    public OutsidePanel getOutsidePanel() {
        return outsidePanel;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ExternalDisplay> getElevatorDisplays() {
        return elevatorDisplays;
    }
}
