import java.io.*;
import java.util.ArrayList;


public class Inventory {
    private ArrayList<Product> productsList;

    public Inventory() {
        productsList = new ArrayList<>();
    }
    public void addProduct(Product p) {
        if (getProductById(p.getId()) != null) {
            System.out.println("Product ID already exists!");
            return;
        }
        productsList.add(p);
        saveInventory();
        System.out.println("Successfully Add!");
    }
    public void showInventory() {
        if (productsList.isEmpty()) {
            System.out.println("\n[!] Inventory is empty.");
            return;
        }

        productsList.sort((p1, p2) -> p1.getId().compareToIgnoreCase(p2.getId()));

        System.out.println("\n--------------------------------------------------");
        System.out.printf("| %-3s | %-5s | %-6s | %-2s |\n",
                "ID", "NAME", "PRICE", "QTY");
        System.out.println("--------------------------------------------------");
        for (Product p : productsList) {
            p.showDetail();
        }
    }
    public void saveInventory() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("StockInventory/stock.txt"))) {
            for (Product p : productsList) {

                String line = p.getType() + "," + p.getId() + "," + p.getName() + "," +
                        p.getPrice() + "," + p.getQuantity();

                if (p instanceof FoodProduct) {
                    FoodProduct fp = (FoodProduct) p;
                    line += "," + fp.getExpiryDate();
                }

                if (p instanceof DrinkProduct) {
                    DrinkProduct dp = (DrinkProduct) p;
                    line += "," + dp.getVolume();
                }

                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Fail to save : " + e.getMessage());
        }
    }
    public void readInventory() {
        productsList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("StockInventory/stock.txt"))) {
            String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    String[] data = line.split(",");
                    if (data.length < 6) continue;
                    String type = data[0];
                    String id = data[1];
                    String name = data[2];
                    double price = Double.parseDouble(data[3]);
                    int qty = Integer.parseInt(data[4]);

                    if (type.equalsIgnoreCase("FOOD")) {
                        productsList.add(new FoodProduct(id, name, price, qty, data[5]));
                    } else if (type.equalsIgnoreCase("DRINK")) {
                        productsList.add(new DrinkProduct(id, name, price, qty, Integer.parseInt(data[5])));
                    }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Find Your Stock");
        } catch (IOException e) {
            System.out.println("Fail to read : " + e.getMessage());
        }
    }
    public void removeProduct(String id) {
        boolean removed = productsList.removeIf(p->p.getId().equalsIgnoreCase(id));

        if (removed) {
            saveInventory();
            System.out.println("Remove Product ID : [" + id + "] Out Of Inventory");
        } else {
            System.out.println("Cannot Find Product ID : " + id);
        }
    }
    public Product getProductById(String id) {
        for (Product p : productsList) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
    public void searchProduct(String keyword) {
        for (Product p : productsList) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                p.showDetail();
            }
        }
    }
}
