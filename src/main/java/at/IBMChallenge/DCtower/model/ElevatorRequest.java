package at.IBMChallenge.DCtower.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ElevatorRequest {
    private int currentFloor;
    private int destination;
    private Direction direction;

    public ElevatorRequest(int currentFloor,int destination,Direction direction){
        this.currentFloor = currentFloor;
        this.destination = destination;
        this.direction = direction;
    }

}
