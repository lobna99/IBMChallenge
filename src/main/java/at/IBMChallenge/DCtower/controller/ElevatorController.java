package at.IBMChallenge.DCtower.controller;

import at.IBMChallenge.DCtower.model.Direction;
import at.IBMChallenge.DCtower.model.Elevator;
import at.IBMChallenge.DCtower.model.ElevatorRequest;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class ElevatorController implements IElevatorController {

    private final Elevator[] elevators;
    private final ExecutorService executorService;

    public ElevatorController(int numElevators){
        elevators = new Elevator[numElevators];
        for (int i = 0; i < numElevators; i++) {
            elevators[i] = new Elevator(i+1,0, new ArrayList<>(),true,Direction.UP);
        }
        executorService = Executors.newFixedThreadPool(numElevators);

    }
    public void handleRequest(ElevatorRequest request){
            Elevator freeElevator = findNextElevator(request.getCurrentFloor(), request.getDirection());
            // Handle the request in a separate thread
            freeElevator.requestStop(request.getCurrentFloor(), executorService);
            freeElevator.requestStop(request.getDestination(), executorService);
    }

    private Elevator findNextElevator(int currentFloor,Direction direction ){
        int minDistance = Integer.MAX_VALUE;
        int elevIndex = 0;

        for (int i = 0; i < elevators.length; i++){
            if (elevators[i].isAvailable() || (allElevatorsBusy() && elevators[i].getDirection() == direction)) {
                int distance = Math.abs(currentFloor - elevators[i].getCurrentFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    elevIndex = i;
                }
            }
        }
        elevators[elevIndex].setAvailable(false);
        return elevators[elevIndex];

    }

    private boolean allElevatorsBusy(){
        return Arrays.stream(elevators).anyMatch(Elevator::isAvailable);
    }



}
