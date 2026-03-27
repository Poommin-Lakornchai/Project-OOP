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
            System.out.println("\n=================================");
            System.out.println("        STOCK MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add Product");
            System.out.println("2. Show Inventory");
            System.out.println("3. Remove Product");
            System.out.println("4. Edit Product Info");
            System.out.println("5. Search Product");
            System.out.println("0. Exit");
            System.out.println("---------------------------------");
            System.out.print("Select Menu : ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.println("\n-------- Product Type --------");
                    System.out.println("1. Food Product");
                    System.out.println("2. Drink Product");
                    System.out.print("Select Type : ");

                    int choice2 = input.nextInt();
                    input.nextLine();

                    System.out.print("Product ID : "); String id = input.nextLine();
                    System.out.print("Product Name : "); String name = input.nextLine().toUpperCase();
                    System.out.print("Product Price (Baht): "); double price = input.nextDouble();
                    System.out.print("Quantity (Pack): "); int qty = input.nextInt();
                    input.nextLine();


                    if (choice2 == 1) {
                        System.out.print("Expired (YYYY-MM-DD) : "); String exp = input.nextLine();
                        myStock.addProduct(new FoodProduct(id,name,price,qty,exp));
                    } else if (choice2 == 2) {
                        System.out.print("Volume : "); int volume = input.nextInt();
                        myStock.addProduct(new DrinkProduct(id,name,price,qty,volume));
                    }
                    break;

                case 2:
                    myStock.showInventory();
                    break;
                case 3:
                    System.out.println("\n------ Remove Product ------");
                    System.out.print("Enter Product ID : ");
                    String fid = input.nextLine();
                    myStock.removeProduct(fid);
                    break;
                case 4:
                    System.out.print("Edit Product ID : ");
                    String targetId = input.nextLine();
                    Product p = myStock.getProductById(targetId);

                    if (p != null) {
                        System.out.println("\n------ Edit Product ------");
                        System.out.println("Product : " + p.getName());
                        System.out.println("--------------------------");
                        System.out.println("1. Edit Name");
                        System.out.println("2. Edit Price");
                        System.out.println("3. Edit Quantity");
                        if (p instanceof FoodProduct) {
                            System.out.println("4. Edit Expiry Date");
                        } else if (p instanceof DrinkProduct) {
                            System.out.println("4. Edit Volume");
                        }
                        System.out.print("Select Option : ");
                        int editChoice = input.nextInt();
                        input.nextLine();

                        switch (editChoice) {
                            case 1:
                                System.out.print("New Name : ");
                                p.setName(input.nextLine().toUpperCase());
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
                                } else if (p instanceof DrinkProduct) {
                                    System.out.print("New Volume: ");
                                    ((DrinkProduct) p).setVolume(input.nextInt());
                                    input.nextLine();
                                }
                                break;
                        }
                        myStock.saveInventory();
                        System.out.println("Edit Successfully!");
                    } else {
                        System.out.println("Cannot Find Product Id: " + targetId);
                    }
                    break;
                case 5 :
                    System.out.println("\n------ Search Product ------");
                    System.out.print("Enter Product Name : ");
                    String sp = input.nextLine();
                    myStock.searchProduct(sp);
                    break;
                default:
                    System.out.println("Please try again!");
            }
        }
        System.out.println("Close The Program");
    }
}
