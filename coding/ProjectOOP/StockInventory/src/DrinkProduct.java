public class DrinkProduct extends Product {
    private String expiryDate;
    public DrinkProduct(String id,String name,double price,int quantity, String expiryDate) {
        super(id,name,price,quantity);
        this.expiryDate = expiryDate;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    @Override
    public void showDetail() {
        System.out.printf("| %-3s | %-5s | %-5.2f | %-2d | Exp: %-5s |%n",
                getId(), getName(), getPrice(), getQuantity(), expiryDate);
    }
    @Override
    public String getType() {
        return "Drink";
    }
}
