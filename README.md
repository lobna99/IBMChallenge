# IBMChallenge
DC Tower Challenge

## Overview

This Java project implements a basic Elevator Control System for managing elevators in a building. The system includes an `ElevatorController` class responsible for handling elevator requests and managing multiple elevators. Each elevator is represented by the `Elevator` class, and elevator requests are represented by the `ElevatorRequest` class.

## Classes

### 1. ElevatorController Class

- Manages a set of elevators in a building.
- Initializes an array of elevators and an ExecutorService for handling elevator requests in separate threads.
- The `handleRequest` method assigns a request to the nearest available elevator and requests the elevator to stop at specified floors.
- The `findNextElevator` method determines the nearest available elevator based on the current floor and direction of the request.
- The `allElevatorsBusy` method checks if all elevators are currently busy.

### 2. Elevator Class

- Represents an individual elevator in the building.
- Attributes include elevator number, current floor, a list of stops, availability status, and current direction.
- The `requestStop` method adds a floor to the list of stops, and the elevator moves to those stops in a separate thread.
- The `moveElevator` method simulates the elevator's movement between floors.

### 3. ElevatorRequest Class

- Represents a request for an elevator, including the current floor, destination floor, and direction.

## Usage

1. Create an instance of `ElevatorController` with the desired number of elevators.

```java
ElevatorController elevatorController = new ElevatorController(7);
