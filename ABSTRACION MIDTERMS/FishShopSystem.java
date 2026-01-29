import java.util.Scanner;

interface FishShop {
    void addFish(String name, double price);
    void removeFish(String name);
    void buyFish(String name);
    void returnFish(String name);
    void checkAllFish();
    void showBoughtFish();
    void checkout(); 
}

class MyFishShop implements FishShop {

    String[] fishNames = new String[50];
    double[] fishPrices = new double[50];
    int fishCount = 0;

    String[] boughtFishNames = new String[50];
    double[] boughtFishPrices = new double[50];
    int boughtCount = 0;

    public void addFish(String name, double price) {
        fishNames[fishCount] = name;
        fishPrices[fishCount] = price;
        fishCount++;
        System.out.println(name + " added with price $" + price);
    }

    public void removeFish(String name) {
        boolean found = false;
        for (int i = 0; i < fishCount; i++) {
            if (fishNames[i].equalsIgnoreCase(name)) {
                for (int j = i; j < fishCount - 1; j++) {
                    fishNames[j] = fishNames[j + 1];
                    fishPrices[j] = fishPrices[j + 1];
                }
                fishCount--;
                found = true;
                System.out.println(name + " removed from shop.");
                break;
            }
        }
        if (!found) System.out.println(name + " not found in shop.");
    }

    public void buyFish(String name) {
        boolean found = false;
        for (int i = 0; i < fishCount; i++) {
            if (fishNames[i].equalsIgnoreCase(name)) {
                double price = fishPrices[i];

                boughtFishNames[boughtCount] = name;
                boughtFishPrices[boughtCount] = price;
                boughtCount++;

                for (int j = i; j < fishCount - 1; j++) {
                    fishNames[j] = fishNames[j + 1];
                    fishPrices[j] = fishPrices[j + 1];
                }
                fishCount--;

                System.out.println("You bought " + name + " for $" + price);
                found = true;
                break;
            }
        }
        if (!found) System.out.println(name + " is not available.");
    }

    public void returnFish(String name) {
        boolean found = false;
        for (int i = 0; i < boughtCount; i++) {
            if (boughtFishNames[i].equalsIgnoreCase(name)) {
                double price = boughtFishPrices[i];
                addFish(name, price);

                for (int j = i; j < boughtCount - 1; j++) {
                    boughtFishNames[j] = boughtFishNames[j + 1];
                    boughtFishPrices[j] = boughtFishPrices[j + 1];
                }
                boughtCount--;

                System.out.println(name + " returned to shop.");
                found = true;
                break;
            }
        }
        if (!found) System.out.println(name + " was not bought, so it cannot be returned.");
    }

    public void checkAllFish() {
        if (fishCount == 0) System.out.println("No fish available.");
        else {
            System.out.println("Available fish:");
            for (int i = 0; i < fishCount; i++) {
                System.out.println("- " + fishNames[i] + " : $" + fishPrices[i]);
            }
        }
    }

    public void showBoughtFish() {
        if (boughtCount == 0) System.out.println("No fish bought yet.");
        else {
            System.out.println("Bought fish:");
            for (int i = 0; i < boughtCount; i++) {
                System.out.println("- " + boughtFishNames[i] + " : $" + boughtFishPrices[i]);
            }
        }
    }

  
    public void checkout() {
        if (boughtCount == 0) {
            System.out.println("No fish bought yet.");
            return;
        }

        double total = 0;
        System.out.println("Checkout:");
        for (int i = 0; i < boughtCount; i++) {
            System.out.println("- " + boughtFishNames[i] + " : $" + boughtFishPrices[i]);
            total += boughtFishPrices[i];
        }
        System.out.println("Total price: $" + total);

       
        boughtCount = 0;
        System.out.println("Thank you for your purchase!");
    }
}

public class FishShopSystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FishShop shop = new MyFishShop();

        while (true) {
            System.out.println("\nFISH SHOP MENU");
            System.out.println("1. Add Fish");
            System.out.println("2. Remove Fish");
            System.out.println("3. Buy Fish");
            System.out.println("4. Return Fish");
            System.out.println("5. Check All Fish Available");
            System.out.println("6. Show Bought Fish");
            System.out.println("7. Checkout"); 
            System.out.println("8. EXIT");

            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter fish name: ");
                    String addName = scan.nextLine();
                    System.out.print("Enter fish price: ");
                    double price = scan.nextDouble();
                    shop.addFish(addName, price);
                    break;
                case 2:
                    System.out.print("Enter fish name to remove: ");
                    String removeName = scan.nextLine();
                    shop.removeFish(removeName);
                    break;
                case 3:
                    System.out.print("Enter fish name to buy: ");
                    String buyName = scan.nextLine();
                    shop.buyFish(buyName);
                    break;
                case 4:
                    System.out.print("Enter fish name to return: ");
                    String returnName = scan.nextLine();
                    shop.returnFish(returnName);
                    break;
                case 5:
                    shop.checkAllFish();
                    break;
                case 6:
                    shop.showBoughtFish();
                    break;
                case 7:
                    shop.checkout();
                    break;
                case 8:
                    System.out.println("Thank you for visiting the Fish Shop!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

