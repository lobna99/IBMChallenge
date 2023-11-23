package at.IBMChallenge.DCtower.controller;

import at.IBMChallenge.DCtower.controller.ElevatorController;
import at.IBMChallenge.DCtower.model.Direction;
import at.IBMChallenge.DCtower.model.Elevator;
import at.IBMChallenge.DCtower.model.ElevatorRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElevatorControllerTest {

    private ElevatorController elevatorController;

    @BeforeEach
    void setUp() {
        elevatorController = new ElevatorController(5);
    }

    @Test
    void testHandleRequest() throws InterruptedException {
        ElevatorRequest request = new ElevatorRequest(0, 5, Direction.UP);

        elevatorController.handleRequest(request);

        elevatorController.getExecutorService().shutdown();
        elevatorController.getExecutorService().awaitTermination(15, TimeUnit.SECONDS);

        assertEquals(5, elevatorController.getElevators()[0].getCurrentFloor());
        assertTrue(elevatorController.getElevators()[0].isAvailable());

        assertEquals(Direction.UP, elevatorController.getElevators()[0].getDirection());
    }

    @Test
    void testHandleRequestMultipleElevators() throws InterruptedException {
        // Create requests for both elevators
        ElevatorRequest request1 = new ElevatorRequest(0, 5, Direction.UP);
        ElevatorRequest request2 = new ElevatorRequest(1, 0, Direction.DOWN);
        CountDownLatch latch = new CountDownLatch(2);

        elevatorController.handleRequest(request1);
        elevatorController.handleRequest(request2);

        latch.await(20, TimeUnit.SECONDS);  // Adjust the timeout if needed

        assertEquals(5, elevatorController.getElevators()[0].getCurrentFloor());
        assertTrue(elevatorController.getElevators()[0].isAvailable());
        assertEquals(Direction.UP, elevatorController.getElevators()[0].getDirection());

        assertEquals(0, elevatorController.getElevators()[1].getCurrentFloor());
        assertTrue(elevatorController.getElevators()[1].isAvailable());
        assertEquals(Direction.DOWN, elevatorController.getElevators()[1].getDirection());
    }
}