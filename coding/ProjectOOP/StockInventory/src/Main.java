import java.util.Scanner;

public class Main {
    static Scanner input;
    public static void main(String[] args) {
        input = new Scanner(System.in);
        Inventory myStock = new Inventory();

        myStock.readInventory();

        showSystem(input,myStock);
    }
    public static void showSystem(Scanner input,Inventory myStock) {
        while (true) {
            System.out.println("\n===== Stock System =====");
            System.out.println("1. Add Food Stock");
            System.out.println("2. Show Inventory");
            System.out.println("3. Remove Product");
            System.out.println("4. Edit Product Info");
            System.out.println("0. Exit");
            System.out.print("Chose choice : ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("What Type of Product ");
                    System.out.println("1. Food");
                    System.out.println("2. Drink");

                    int choice2 = input.nextInt();
                    input.nextLine();

                    System.out.print("Product ID : "); String fid = input.nextLine();
                    System.out.print("Product Name : "); String fname = input.nextLine().toUpperCase();
                    System.out.print("Product Price (Baht): "); double fprice = input.nextDouble();
                    System.out.print("Quantity (Pack): "); int fqty = input.nextInt();
                    input.nextLine();
                    System.out.print("Expired (YYYY-MM-DD) : "); String exp = input.nextLine();

                    if (choice2 == 1) {
                        myStock.addProduct(new FoodProduct(fid, fname, fprice, fqty, exp));
                    } else if (choice2 == 2) {
                        myStock.addProduct(new DrinkProduct(fid, fname, fprice, fqty, exp));
                    }
                    break;

                case 2:
                    myStock.showInventory();
                    break;
                case 3:
                    System.out.print("Remove Product ID : ");
                    String id = input.nextLine();
                    myStock.removeProduct(id);
                    break;
                case 4:
                    System.out.print("Edit Product ID : ");
                    String targetId = input.nextLine();
                    Product p = myStock.getProductById(targetId);


                    if (p != null) {
                        System.out.println("Found Product: " + p.getName());
                        System.out.println("1.EditName  \n2.EditPrice  \n3.EditQuantity  \n4.EditExpiryDate");
                        System.out.print("Select What To Edit : ");
                        int editChoice = input.nextInt();
                        input.nextLine();

                        switch (editChoice) {
                            case 1:
                                System.out.print("New Name : ");
                                p.setName(input.nextLine());
                                break;
                            case 2:
                                System.out.print("New Price : ");
                                p.setPrice(input.nextDouble());
                                input.nextLine();
                                break;
                            case 3:
                                System.out.print("New Quantity: ");
                                p.setQuantity(input.nextInt());
                                input.nextLine();
                                break;
                            case 4:
                                if (p instanceof FoodProduct) {
                                    System.out.print("New ExpiryDate: ");
                                    ((FoodProduct) p).setExpiryDate(input.nextLine());
                                }
                                break;
                        }
                        myStock.saveInventory();
                        System.out.println("Edit Successfully!");
                    } else {
                        System.out.println("Cannot Find This Product");
                    }
                case 5:

                    break;
                default:
                    System.out.println("Please try again!");
            }
        }
        System.out.println("Close The Program");
    }
}
