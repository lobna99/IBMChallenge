package at.IBMChallenge.DCtower.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElevatorTest {

    @Test
    void testElevatorMovement() throws InterruptedException {
        // Create an elevator
        Elevator elevator = Elevator.builder()
                .elevatorNum(1)
                .currentFloor(0)
                .stops(new ArrayList<>())
                .available(true)
                .direction(Direction.UP)
                .build();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        elevator.requestStop(5, executor);
        elevator.requestStop(2, executor);
        elevator.requestStop(8, executor);

        executor.shutdown();
        executor.awaitTermination(15, TimeUnit.SECONDS);  // Adjust the timeout if needed

        assertEquals(8, elevator.getCurrentFloor());
        assertEquals(Direction.UP, elevator.getDirection());
        assertEquals(true, elevator.isAvailable());
    }

    @Test
    void testElevatorRequestStop() throws InterruptedException {
        // Create an elevator
        Elevator elevator = Elevator.builder()
                .elevatorNum(2)
                .currentFloor(3)
                .stops(new ArrayList<>())
                .available(true)
                .direction(Direction.DOWN)
                .build();

        // Create an executor service for testing
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Request a stop and simulate elevator movement
        elevator.requestStop(0, executor);

        executor.shutdown();
        executor.awaitTermination(15, TimeUnit.SECONDS);  // Adjust the timeout if needed

        // Verify the final state of the elevator
        assertEquals(0, elevator.getCurrentFloor());
        assertEquals(Direction.DOWN, elevator.getDirection());
        assertEquals(true, elevator.isAvailable());
    }
}
