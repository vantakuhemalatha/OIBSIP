import java.util.*;

public class task1 {
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();
    private static int reservationCounter = 1;
    private static Map<String, String> trainNames = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a default admin user
        users.put("admin", "admin123");

        // Populate train names (train number -> train name)
        trainNames.put("123", "Express Train");
        trainNames.put("456", "Super Fast Train");
        trainNames.put("789", "Local Train");

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("1. Make a reservation");
                System.out.println("2. Cancel a reservation");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        makeReservation(scanner, username);
                        break;
                    case 2:
                        cancelReservation(scanner, username);
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter a new username: ");
        String username = scanner.next();
        System.out.print("Enter a new password: ");
        String password = scanner.next();

        users.put(username, password);
        System.out.println("Registration successful! You can now log in.");
    }

    private static void makeReservation(Scanner scanner, String username) {
        System.out.print("Enter train number: ");
        String trainNumber = scanner.next();
        
        if (!trainNames.containsKey(trainNumber)) {
            System.out.println("Invalid train number. Please try again.");
            return;
        }

        String trainName = trainNames.get(trainNumber);

        System.out.print("Enter class type: ");
        String classType = scanner.next();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.next();
        System.out.print("Enter from (place): ");
        String fromPlace = scanner.next();
        System.out.print("Enter to (destination): ");
        String toDestination = scanner.next();

        Reservation reservation = new Reservation(trainNumber, trainName, classType, dateOfJourney, fromPlace, toDestination);
        reservations.put(username + "_" + reservationCounter++, reservation);
        System.out.println("Reservation successful!");
    }

    private static void cancelReservation(Scanner scanner, String username) {
        System.out.print("Enter reservation number: ");
        int reservationNumber = scanner.nextInt();

        String keyToCancel = username + "_" + reservationNumber;

        if (reservations.containsKey(keyToCancel)) {
            reservations.remove(keyToCancel);
            System.out.println("Cancellation successful!");
        } else {
            System.out.println("Invalid reservation number. Please try again.");
        }
    }

    static class Reservation {
        private String trainNumber;
        private String trainName;
        private String classType;
        private String dateOfJourney;
        private String fromPlace;
        private String toDestination;

        public Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String fromPlace, String toDestination) {
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.fromPlace = fromPlace;
            this.toDestination = toDestination;
        }

        public String getTrainNumber() {
            return trainNumber;
        }

        public String getTrainName() {
            return trainName;
        }

        public String getClassType() {
            return classType;
        }

        public String getDateOfJourney() {
            return dateOfJourney;
        }

        public String getFromPlace() {
            return fromPlace;
        }

        public String getToDestination() {
            return toDestination;
        }
    }
}