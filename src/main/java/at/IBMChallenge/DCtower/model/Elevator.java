package at.IBMChallenge.DCtower.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

@Builder
@Getter
@Setter
public class Elevator{
    private int elevatorNum;
    private int currentFloor;
    private List<Integer> stops;
    private boolean available;
    private Direction direction;
    private static final Logger LOGGER = Logger.getLogger(Elevator.class.getName());



    public Elevator(int id,int currentFloor, List<Integer> stops,boolean available, Direction direction){
        this.elevatorNum = id;
        this.currentFloor = currentFloor;
        this.available = available;
        this.stops = stops;
        this.direction = direction;
    }

    public void requestStop(int floor, ExecutorService executor) {
        executor.submit(() -> {
            synchronized (this) {
                this.stops.add(floor);
                Collections.sort(stops);
                int nextStop = stops.remove(0);
                moveElevator(nextStop);
            }
        });
    }


    private void moveElevator(int destination) {
        // Simulate moving the elevator
        LOGGER.info("Elevator "+this.elevatorNum +" moving from floor " + currentFloor + " to floor " + destination);
        Direction direction = currentFloor < destination ? Direction.UP : Direction.DOWN;
        while(currentFloor != destination ){
            try {// Simulate the time it takes for the elevator to move
                Thread.sleep(1000);
                if (direction == Direction.UP) currentFloor++;
                else currentFloor--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.direction = direction;
        this.available = true;
    }

}
