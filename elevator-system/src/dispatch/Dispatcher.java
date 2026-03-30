package dispatch;

import elevator.Elevator;
import elevator.ElevatorController;
import enums.Direction;
import enums.ElevatorState;
import models.Request;

import java.util.List;

public class Dispatcher {

    public ElevatorController dispatch(List<ElevatorController> controllers, Request request) {
        ElevatorController best = null;
        int bestScore = Integer.MAX_VALUE;

        for (ElevatorController controller : controllers) {
            if (controller.isAtCapacity()) {
                continue;
            }

            int score = computeScore(controller, request);
            if (score < bestScore) {
                bestScore = score;
                best = controller;
            }
        }

        if (best == null) {
            System.out
                    .println("[Dispatcher] All elevators at capacity. Request " + request.getRequestId() + " queued.");
            return null;
        }

        System.out.println("[Dispatcher] Assigned Elevator " + best.getElevator().getElevatorId()
                + " to Request " + request.getRequestId()
                + " (Floor " + request.getSourceFloor() + " -> Floor " + request.getDestinationFloor() + ")");
        return best;
    }

    private int computeScore(ElevatorController controller, Request request) {
        Elevator elevator = controller.getElevator();
        int floorDiff = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());

        if (elevator.getState() == ElevatorState.IDLE) {
            return floorDiff;
        }

        Direction elevatorDir = elevator.getDirection();
        Direction requestDir = request.getDirection();

        if (elevatorDir == requestDir) {
            if (elevatorDir == Direction.UP && elevator.getCurrentFloor() <= request.getSourceFloor()) {
                return floorDiff;
            }
            if (elevatorDir == Direction.DOWN && elevator.getCurrentFloor() >= request.getSourceFloor()) {
                return floorDiff;
            }
        }

        return floorDiff + 20;
    }
}
