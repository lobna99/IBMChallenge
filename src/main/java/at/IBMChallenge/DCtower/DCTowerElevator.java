package at.IBMChallenge.DCtower;

import at.IBMChallenge.DCtower.controller.ElevatorController;
import at.IBMChallenge.DCtower.model.Direction;
import at.IBMChallenge.DCtower.model.ElevatorRequest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DCTowerElevator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start the elevator controller
        ElevatorController elevatorController = new ElevatorController(7);

        System.out.println("Elevator System started. Enter requests in the format: currentFloor,destinationFloor,direction");

        while (true) {
            try {
                String inputLine = scanner.nextLine();
                String[] requestInfo = inputLine.split(",");
                if (requestInfo.length == 3) {
                    int currentFloor = Integer.parseInt(requestInfo[0].trim());
                    int destinationFloor = Integer.parseInt(requestInfo[1].trim());
                    Direction direction = requestInfo[2].trim().equalsIgnoreCase("UP") ? Direction.UP : Direction.DOWN;
                    elevatorController.handleRequest(new ElevatorRequest(currentFloor, destinationFloor, direction));
                    System.out.println("Request processed successfully.");
                } else {
                    System.out.println("Invalid input format. Please provide input in the format: currentFloor,destinationFloor,direction");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please provide valid floor numbers and direction.");
            }
        }
    }
}