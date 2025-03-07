
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    String Username;
    String StreetName;
    String StreetNumber;
    String PostalCode;


    //constructor
    public User(String Username, String StreetName, String StreetNumber, String PostalCode) {
        this.Username = Username;
        this.StreetName = StreetName;
        this.StreetNumber = StreetNumber;
        this.PostalCode = PostalCode;
    }
}

class PizzaOrder {
    String pizzaType;
    String size;
    double pizzaPrice;
    double deliveryFee;
    double totalPrice;

    public PizzaOrder(String pizzaType, String size, double pizzaPrice, double deliveryFee) {
        this.pizzaType = pizzaType;
        this.size = size;
        this.pizzaPrice = pizzaPrice;
        this.deliveryFee = deliveryFee;
        this.totalPrice = pizzaPrice + deliveryFee;
    }
}

public class PizzaTime {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args){

        ClearConsole();
        
        String PizzaTimeWelcomeMessage = 
                        "   ▄███████▄  ▄█   ▄███████▄   ▄███████▄     ▄████████          ███      ▄█    ▄▄▄▄███▄▄▄▄      ▄████████\r\n" + //
                        "  ███    ███ ███  ██▀     ▄██ ██▀     ▄██   ███    ███      ▀█████████▄ ███  ▄██▀▀▀███▀▀▀██▄   ███    ███\r\n" + //
                        "  ███    ███ ███▌       ▄███▀       ▄███▀   ███    ███         ▀███▀▀██ ███▌ ███   ███   ███   ███    █▀ \r\n" + //
                        "  ███    ███ ███▌  ▀█▀▄███▀▄▄  ▀█▀▄███▀▄▄   ███    ███          ███   ▀ ███▌ ███   ███   ███  ▄███▄▄▄    \r\n" + //
                        "▀█████████▀  ███▌   ▄███▀   ▀   ▄███▀   ▀ ▀███████████          ███     ███▌ ███   ███   ███ ▀▀███▀▀▀    \r\n" + //
                        "  ███        ███  ▄███▀       ▄███▀         ███    ███          ███     ███  ███   ███   ███   ███    █▄ \r\n" + //
                        "  ███        ███  ███▄     ▄█ ███▄     ▄█   ███    ███          ███     ███  ███   ███   ███   ███    ███\r\n" + //
                        " ▄████▀      █▀    ▀████████▀  ▀████████▀   ███    █▀          ▄████▀   █▀    ▀█   ███   █▀    ██████████";
        

        System.out.print(PizzaTimeWelcomeMessage + "\nPress Enter To Continue...");
        scanner.nextLine();

        while(true){
            ClearConsole();
            System.out.print("---------------------- \nWelcome To Pizza Time\n----------------------");
            System.out.println("\n1. Create User\n2. Login\n3. Exit");

            int UserInput = 0;

            while(UserInput > 3 || UserInput < 1){
                UserInput = getValidIntInput("", "Response Must Be A Number.");

                if(UserInput > 3 || UserInput < 1){
                    System.out.println("Please Type Valid Response!");
                }
            }
            
            switch (UserInput) {
                case 1:
                    CreateUser();
                    break;
                case 2:
                    Login();
                    break;
                case 3:

                    System.out.println("Thank you for using Pizza Time! See you again soon.");
                    return;

                default:
                    break;
            }
        }
        
        
    }

    public static String[] LoadUsers(){
        List<String> userList = new ArrayList<>();
    
        try (Scanner scanner = new Scanner(new File("users.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" \\| ");
                userList.add(data[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return userList.toArray(new String[0]);
    }

    public static String[] GetUserInfo(String Username) {
        try (Scanner scanner = new Scanner(new File("users.txt"))) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" \\| ");
                if (data[0].equalsIgnoreCase(Username)) {
                    return data;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return new String[]{"User not found"};
    }

    public static void Login(){
        ClearConsole();
        String[] LoadedUsers = LoadUsers();

        System.out.print("\nUsername: ");
        String Username = scanner.nextLine();

        for(String user: LoadedUsers){
            if(user.equalsIgnoreCase(Username)){
                String[] UsersInfo = GetUserInfo(Username);
                UserSession(Username.toUpperCase(), UsersInfo[1]);
                return;
            }
        }
        System.out.print("The user " + Username + " does not exist. Type enter to continue...");
        scanner.nextLine();

    }
    public static void CreateUser(){
        ClearConsole();
        
        String[] LoadedUsers = LoadUsers();

        Boolean isTaken = true;

        System.out.print("\nEnter A Username: ");
        String NewUsername = scanner.nextLine();
        while(isTaken == true){
            

            isTaken = false;

            for(String user: LoadedUsers){
                if(NewUsername.equalsIgnoreCase(user)){

                    System.out.print("Username is already taken! Please select a new username: ");
                    NewUsername = scanner.nextLine();
                    isTaken = true;
                    
                }
            }
        }

        System.out.print("Enter your city: ");
        String city = scanner.nextLine();

        while(!city.equalsIgnoreCase("winnipeg")){
            System.out.print("Sorry but we do not deliever in that city. Please enter an approved city: ");
            city = scanner.nextLine();
        }

        System.out.print("Enter your street name: ");
        String StreetName = scanner.nextLine().toLowerCase();

        while(!(StreetName.equals("burrows") || StreetName.equals("main") || StreetName.equals("henderson") || StreetName.equals("magnus") || StreetName.equals("mountain"))){
            System.out.print("Sorry but we do not deliever there. Please enter an approved street (Burrows, Main, Henderson, Magnus, Mountain): ");
            StreetName = scanner.nextLine().toLowerCase();
        }

        int StreetNumber = getValidIntInput("Enter your street number: ", "Street number must be an integer.");

        while(StreetNumber == 0){
            StreetNumber = getValidIntInput("Please enter a valid street number: ", "Street number must be an integer.");
        }

        
        System.out.print("Enter your Postal Code (A1A 1A1): ");
        String PostalCode = scanner.nextLine().toUpperCase();

        while(!isValidPostalCode(PostalCode)){
            System.out.print("The postal code you entered is in a invalid format. Please enter a valid postal code (A1A 1A1):");
            PostalCode = scanner.nextLine().toUpperCase();
        }

        User user = new User(NewUsername, StreetName, Integer.toString(StreetNumber), PostalCode);     

        try(BufferedWriter write = new BufferedWriter(new FileWriter("users.txt", true))){

                 write.write("\n" + capitalizeUsername(user.Username) + " | " + user.StreetName + " | " + user.StreetNumber + " | " + user.PostalCode);
    
             }catch(IOException e){
         System.err.println("An error occurred while creating the file: " + e.getMessage());
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print("Your User has successfully been created! Press enter to login...");
        scanner.nextLine();

        UserSession(NewUsername.toUpperCase(), StreetName);
    }

    public static void UserSession(String Username, String StreetName){
        Username = Username.toUpperCase();

        boolean Session = true;
        while(Session == true){
            ClearConsole();
    
            System.out.print("Welcome " + Username);
            System.out.println("\n1. Order Pizza\n2. View Order History\n3. View account details\n4. Logout");
    
            int UserInput = 0;
    
            while(UserInput > 4|| UserInput < 1){
                UserInput = getValidIntInput("", "Response Must Be A Number.");
    
                if(UserInput > 4 || UserInput < 1){
                    System.out.println("Please Type Valid Response!");
                }
            }
    
            switch (UserInput) {
                case 1:
                        ClearConsole();
                        OrderPizza(Username, StreetName);
                        System.out.print("Click enter to go back...");
                        scanner.nextLine();
                    break;
                case 2:
                        ClearConsole();
                        ViewOrderHistory(Username);
                        System.out.print("Click enter to go back...");
                        scanner.nextLine();
                    break;
                case 3:
                    ClearConsole();
                    ViewAccountDetails(Username);
                    System.out.print("Click enter to go back...");
                    scanner.nextLine();

                    break;
                case 4:

                    Session = false;

                break;
            
                default:
                break;
            }
        }
        
    }
    private static void ViewAccountDetails(String username){
        String[] UserData = GetUserInfo(username);

        System.out.println(UserData[0] + "\n" + UserData[2] + " "+ UserData[1] + "\nWinnipeg, MB " + UserData[3]);
    }

    private static void ViewOrderHistory(String username){

        System.out.println("Order History for " + username + ":");
        System.out.println("------------------------------------------------------------");
        System.out.println("Pizza Type     Size      Pizza Price   Delivery Fee      Total Price");
        System.out.println("------------------------------------------------------------");
    
        try (Scanner scanner = new Scanner(new File("order_history.txt"))) {
            while (scanner.hasNextLine()) {
                String order = scanner.nextLine();
                if (order.contains(username)) {
                    String[] orderDetails = order.split(" \\| ");
                    if (orderDetails.length == 6) {
                        System.out.println(orderDetails[1] + "     " + orderDetails[2] + "     " + orderDetails[3] + "    " 
                                           + orderDetails[4] + "    " + orderDetails[5]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No order history found.");
        }
    
        System.out.println("------------------------------------------------------------");
    }
    

    public static void OrderPizza(String Username, String streetName){
        System.out.println("What type of pizza would you like to order?");
        System.out.println("1. Margherita - $10");
        System.out.println("2. Pepperoni - $12");
        System.out.println("3. Veggie - $11");

        int pizzaChoice = getValidIntInput("", "Please select a valid option.");
        
        double pizzaPrice = 0.0;
        String pizzaType = "";

        switch (pizzaChoice) {
            case 1:
                pizzaPrice = 10;
                pizzaType = "Margherita";
                break;
            case 2:
                pizzaPrice = 12;
                pizzaType = "Pepperoni";
                break;
            case 3:
                pizzaPrice = 11;
                pizzaType = "Veggie";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("What size would you like?");
        System.out.println("1. Small - $0");
        System.out.println("2. Medium - $2");
        System.out.println("3. Large - $5");

        int sizeChoice = getValidIntInput("", "Please select a valid size.");

        double sizePrice = 0.0;

        switch (sizeChoice) {
            case 1:
                sizePrice = 0;
                break;
            case 2:
                sizePrice = 2;
                break;
            case 3:
                sizePrice = 5;
                break;
            default:
                System.out.println("Invalid size choice.");
                return;
        }

        double deliveryFee = getDeliveryFee(streetName);
        PizzaOrder order = new PizzaOrder(pizzaType, getPizzaSize(sizeChoice), pizzaPrice + sizePrice, deliveryFee);

        System.out.println("Order Summary:");
        System.out.println("Pizza: " + pizzaType + " (" + order.size + ")");
        System.out.println("Pizza Price: $" + pizzaPrice);
        System.out.println("Size Price: $" + sizePrice);
        System.out.println("Delivery Fee: $" + deliveryFee);
        System.out.println("Total Price: $" + order.totalPrice);

        // Save order to history
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("order_history.txt", true))) {
            writer.write(Username + " | " + pizzaType + " | " + order.size + " | $" + pizzaPrice + " | Delivery Fee: $" + deliveryFee + " | Total: $" + order.totalPrice);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving order history: " + e.getMessage());
        }
    }

    private static double getDeliveryFee(String streetName){
        switch (streetName.toLowerCase()) {
            case "burrows":
                return 2.0;
            case "main":
                return 3.0;
            case "henderson":
                return 2.5;
            case "magnus":
                return 1.5;
            case "mountain":
                return 4.0;
            default:
                return 0;
        }
    }

    private static String getPizzaSize(int sizeChoice){
        switch (sizeChoice) {
            case 1:
                return "Small";
            case 2:
                return "Medium";
            case 3:
                return "Large";
            default:
                return "";
        }
    }

    private static int getValidIntInput(String prompt, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } else {
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }

    private static void ClearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String capitalizeUsername(String username) {
        return username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
    }

    public static boolean isValidPostalCode(String postalCode) {
        if (postalCode.length() != 7 || postalCode.charAt(3) != ' ') {
            return false;
        }

        for (int i = 0; i < postalCode.length(); i++) {
            char c = postalCode.charAt(i);

            if (i == 3) {
                continue;
            }

            if (i <= 2 && i % 2 == 0 && !Character.isLetter(c) ) {
                return false;
            }

            if (i <= 2 && i % 2 != 0 && !Character.isDigit(c)) {
                return false;
            }

            if (i > 2 && i % 2 == 0 && !Character.isDigit(c) ) {
                return false;
            }

            if (i > 2 && i % 2 != 0 && !Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

}