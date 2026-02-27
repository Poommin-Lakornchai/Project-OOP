import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Inventory {
    private ArrayList<Product> productsList;

    public Inventory() {
        productsList = new ArrayList<>();
    }
    public void addProduct(Product p) {
        productsList.add(p);
        saveInventory();
        System.out.println("Successfully Add!");
    }
    public void showInventory() {
        if (productsList.isEmpty()) {
            System.out.println("\n[!] Inventory is empty.");
            return;
        }
        productsList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                int id1 = Integer.parseInt(p1.getId());
                int id2 = Integer.parseInt(p2.getId());
                return Integer.compare(id1, id2);
            }
        });
        System.out.println("\n--- ProductList in Inventory ---");
        for (Product p : productsList) {
            p.showDetail();
        }
    }
    public void saveInventory() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("D:\\Nickky\\coding\\ProjectOOP\\stock.txt"))) {
            for (Product p : productsList) {
                FoodProduct fp = (FoodProduct) p;
                String line = "FOOD," + fp.getId() + "," + fp.getName() + "," +
                        fp.getPrice() + "," + fp.getQuantity() + "," + fp.getExpiryDate();
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Fail to save : " + e.getMessage());
        }
    }
    public void readInventory() {
        productsList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Nickky\\coding\\ProjectOOP\\stock.txt"))) {
            String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    String type = data[0];
                    String id = data[1];
                    String name = data[2];
                    double price = Double.parseDouble(data[3]);
                    int qty = Integer.parseInt(data[4]);

                    if (type.equals("FOOD")) {
                    productsList.add(new FoodProduct(id, name, price, qty, data[5]));
                    }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot Find Your Stock");
        } catch (IOException e) {
            System.out.println("Fail to read : " + e.getMessage());
        }
    }
    public void removeProduct(String id) {
        boolean removed = productsList.removeIf(p -> p.getId().equalsIgnoreCase(id));

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
}
