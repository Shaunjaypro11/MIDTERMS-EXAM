import java.util.Scanner;

class Fish {
    private String name;
    private double price;

    public Fish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class FishShop {
    private Fish[] fishList = new Fish[50];
    private int fishCount = 0;

    private Fish[] boughtList = new Fish[50];
    private int boughtCount = 0;

    
    public void addFish(String name, double price) {
        fishList[fishCount] = new Fish(name, price);
        fishCount++;
        System.out.println(name + " added with price ₱" + price);
    }

    public void removeFish(String name) {
        boolean found = false;
        for (int i = 0; i < fishCount; i++) {
            if (fishList[i].getName().equalsIgnoreCase(name.trim())) {
                for (int j = i; j < fishCount - 1; j++) {
                    fishList[j] = fishList[j + 1];
                }
                fishCount--;
                found = true;
                System.out.println(name + " removed from shop.");
                break;
            }
        }
        if (!found) {
            System.out.println(name + " not found in shop.");
        }
    }

    
    public void buyFish(String name) {
        boolean found = false;
        for (int i = 0; i < fishCount; i++) {
            if (fishList[i].getName().equalsIgnoreCase(name.trim())) {
                Fish bought = fishList[i];

                
                boughtList[boughtCount] = bought;
                boughtCount++;

                
                for (int j = i; j < fishCount - 1; j++) {
                    fishList[j] = fishList[j + 1];
                }
                fishCount--;

                System.out.println("You bought " + bought.getName() + " for ₱" + bought.getPrice());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(name + " is not available.");
        }
    }

    
    public void returnFish(String name) {
        boolean found = false;
        for (int i = 0; i < boughtCount; i++) {
            if (boughtList[i].getName().equalsIgnoreCase(name.trim())) {
                Fish returned = boughtList[i];

                
                addFish(returned.getName(), returned.getPrice());

                
                for (int j = i; j < boughtCount - 1; j++) {
                    boughtList[j] = boughtList[j + 1];
                }
                boughtCount--;

                System.out.println(returned.getName() + " returned to shop.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(name + " was not bought, so it cannot be returned.");
        }
    }


    public void checkAllFish() {
        if (fishCount == 0) {
            System.out.println("No fish available.");
        } else {
            System.out.println("\nAvailable fish:\n");
            for (int i = 0; i < fishCount; i++) {
                System.out.println("- " + fishList[i].getName() + " : P" + fishList[i].getPrice());
            }
        }
    }

    public void showBoughtFish() {
        if (boughtCount == 0) {
            System.out.println("You haven't bought any fish yet.");
        } else {
            System.out.println("\nFish you bought:\n");
            for (int i = 0; i < boughtCount; i++) {
                System.out.println("- " + boughtList[i].getName() + " : P" + boughtList[i].getPrice());
            }
        }
    }

    
    public void checkout() {
        if (boughtCount == 0) {
            System.out.println("You have no fish to checkout.");
            return;
        }

        double total = 0;
        System.out.println("\nCheckout summary:\n");
        for (int i = 0; i < boughtCount; i++) {
            System.out.println("- " + boughtList[i].getName() + " : P" + boughtList[i].getPrice());
            total += boughtList[i].getPrice();
        }

        System.out.println("\nTotal amount: P" + total);

        
        boughtCount = 0;
        System.out.println("Thank you for your purchase! Your bought list is now cleared.");
    }
}

public class FishShopSystemEncaps {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FishShop shop = new FishShop();

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
                    scan.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
