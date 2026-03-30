# Elevator System — Low-Level Design

## Overview

A fully object-oriented Low-Level Design of an Elevator Control System built in Java. The system manages 3 elevator cars servicing a 15-floor building with smart dispatch, capacity enforcement, and full panel simulation.

## Design Highlights

- **Singleton** — `ElevatorSystem` is the single point of control
- **Strategy-style Dispatch** — `Dispatcher` scores each elevator by proximity and directional alignment
- **OOP Hierarchy** — Abstract `Button` base with `FloorButton`, `ElevatorButton`, `DoorButton` subclasses
- **Safety Constraint** — Doors only open when elevator state is `IDLE`
- **Capacity Enforcement** — Hard limits of 8 persons and 680 kg per elevator

## Package Structure

```
src/
├── Main.java
├── enums/
│   ├── Direction.java         # UP, DOWN, IDLE
│   ├── DoorState.java         # OPEN, CLOSED
│   ├── ElevatorState.java     # MOVING, IDLE, MAINTENANCE
│   └── ButtonType.java        # FLOOR_CALL_UP/DOWN, ELEVATOR_FLOOR, DOOR_OPEN/CLOSE
├── models/
│   ├── Button.java            # Abstract base class
│   ├── FloorButton.java       # Outside call buttons
│   ├── ElevatorButton.java    # Inside floor selection buttons
│   ├── DoorButton.java        # Door open/close buttons
│   ├── Door.java              # Door with safety guard
│   ├── ExternalDisplay.java   # Floor + direction display
│   ├── InternalDisplay.java   # Floor + direction + capacity display
│   ├── Floor.java             # Floor with directional buttons
│   ├── Passenger.java         # Passenger with weight and route
│   └── Request.java           # An elevator service request
├── panels/
│   ├── OutsidePanel.java      # External up/down call panel per floor
│   ├── InsidePanel.java       # Internal floor + door panel per elevator
│   └── FloorPanel.java        # Combines outside panel + elevator status displays
├── elevator/
│   ├── Elevator.java          # Core elevator car logic
│   └── ElevatorController.java # Controller for request processing and cycles
├── dispatch/
│   └── Dispatcher.java        # Smart elevator assignment algorithm
└── system/
    └── ElevatorSystem.java    # Singleton system controller
```

## Requirements Coverage

| Requirement | Implementation |
|---|---|
| 3 elevator cars | `ElevatorSystem` instantiates 3 `Elevator` objects |
| 15 floors | `TOTAL_FLOORS = 15` constant in `ElevatorSystem` |
| UP / DOWN / IDLE movement | `Direction` enum + `Elevator.moveToNextFloor()` |
| Doors open only when idle | `Door.open(ElevatorState)` safety check |
| All floors accessible | `TreeSet<Integer> destinationFloors` in `Elevator` |
| Outside control panel | `OutsidePanel` with UP/DOWN `FloorButton` |
| Inside control panel | `InsidePanel` with `ElevatorButton` + `DoorButton` |
| External display | `ExternalDisplay` — floor + direction |
| Internal display | `InternalDisplay` — floor + direction + capacity |
| Floor panels + displays | `FloorPanel` aggregates outside panel and per-elevator displays |
| Multiple passengers, multiple directions | `ElevatorSystem.requestElevator()` handles concurrent requests |
| Smart dispatch | `Dispatcher.computeScore()` — proximity + directional alignment |
| Capacity 8 persons / 680 kg | `Elevator.boardPassenger()` enforces both limits |
| Max 3 elevators | `TOTAL_ELEVATORS = 3` constant |

## How to Compile and Run

```bash
# Compile
javac -d out -sourcepath src src/Main.java src/enums/*.java src/models/*.java src/panels/*.java src/elevator/*.java src/dispatch/*.java src/system/*.java

# Run
java -cp out Main
```
