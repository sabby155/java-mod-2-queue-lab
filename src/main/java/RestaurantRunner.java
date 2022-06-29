import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Map.Entry;

public class RestaurantRunner {
    private static void log(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        // your code here

        // Create a restaurant tables map
        HashMap<Integer, Boolean> restaurantTables = new HashMap<Integer, Boolean>();

        // Create a waitlist queue list
        Queue<Integer> waitlist = new LinkedList<Integer>();

        // Set up 'empty' or 'true' tables - false indicates that this table isn't free.
        restaurantTables.put(1, true);
        restaurantTables.put(2, true);

        boolean restaurantOpen = true;

        while (restaurantOpen) {
            Scanner inputScanner = new Scanner(System.in);

            log("Would you like to check someone in or out of my restaurant? Select 1 to check someone in. Select 2 to check someone out.");
            int userInput = inputScanner.nextInt();

            if (userInput == 1) {
                log("You want to check someone in. Awesome.");

                boolean tableFree = restaurantTables.containsValue(true);

                if (tableFree) {
                    log("There's a free table!");
                    checkIn(restaurantTables);
                } else {
                    log("Oops! Sorry - all booked. I will add them on a waitlist.");
                    waitlist.add(1);
                    log("waitlist now has " + waitlist.size() + " person(s).");
                }

            } else if (userInput == 2) {
                log("You want to check someone out. Sure.");
                checkOut(restaurantTables);
                if (waitlist.isEmpty()) {
                    log("No one on waitlist.");
                } else {
                    waitlist.remove(1);
                    log("Took the next person off waitlist. I'll check them in.");
                    checkIn(restaurantTables);
                    log("waitlist now has " + waitlist.size() + " person(s).");
                }
            }
        }

    }

    private static void checkIn(HashMap tables) {

        for (Entry<Integer, Boolean> table : ((HashMap<Integer, Boolean>) tables).entrySet()) {
            if (table.getValue()) {
                log("They can sit here.");
                table.setValue(false);
                log("The tables now: " + tables);
                return;
            }
        }

    }

    private static void checkOut(HashMap tables) {

        for (Entry<Integer, Boolean> table : ((HashMap<Integer, Boolean>) tables).entrySet()) {
            if (!table.getValue()) {
                log("They have left.");
                table.setValue(true);
                log("The tables now: " + tables);
                return;
            }
        }

    }
}
