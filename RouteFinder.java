/*
 * Ryan Taylor
 * December 6, 2019
 */
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
public class RouteFinder {
 
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.out.println("Wrong number of arguments! Please pass in the file name, and only the file name.");
            System.exit(-1);
        }
 
        // Setup input scanner
        File inputFile = new File(args[0]);
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find \"" + args[0] + "\", please try a different file name.");
            System.exit(-1);
        }
 
        // Create input array
        int targetDistance = Integer.parseInt(scanner.nextLine());
        String stops[] = scanner.nextLine().split("\\t");

        scanner.close();

        findRoute(targetDistance, stops);

        System.exit(0);
    }

    // Finds and prints the route with the least penalty.
    private static void findRoute(int targetDistance, String[] stops) {

        // Set up variables.
        int stopsLength = stops.length, currentStop, previousStop;
        int penalties[] = new int[stopsLength];
        int routes[] = new int[stopsLength];

        // Starting point.
        penalties[0] = 0;
        routes[0] = 0;

        for (currentStop = 1; currentStop < stopsLength; currentStop++) { // For each stop,
            for (previousStop = 0; previousStop < currentStop; previousStop++) { // For each previous stop,

                int currentStopDistance = Integer.parseInt(stops[currentStop]);
                int previousStopDistance = Integer.parseInt(stops[previousStop]);
                int distanceBetweenStops = currentStopDistance - previousStopDistance;

                // Calculate the penalty to travel from the previous stop to the current stop.
                int newPenalty = penalties[previousStop] + (int)(Math.pow((distanceBetweenStops - targetDistance), 2));

                // The new penalty is less than any saved penalty,
                if (previousStop == 0 || newPenalty < penalties[currentStop]) {
                    // Save the new penalty.
                    penalties[currentStop] = newPenalty;
                    routes[currentStop] = previousStop;
                }
            }
        }

        // Print information.
        String route = routes[stopsLength - 1] + ", " + (stopsLength - 1);
        int i = routes[stopsLength - 1];
        while (i != 0) {
            route = routes[i] + ", " + route;
            i = routes[i];
        }

        System.out.println(route);
        System.out.println(penalties[stopsLength - 1]);
    }
}
