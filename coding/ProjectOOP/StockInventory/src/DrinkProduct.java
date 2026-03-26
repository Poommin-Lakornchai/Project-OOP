public class DrinkProduct extends Product {
    private int volume;
    public DrinkProduct(String id,String name,double price,int quantity, int volume) {
        super(id,name,price,quantity);
        this.volume = volume;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    @Override
    public void showDetail() {
        System.out.printf("| %-3s | %-5s | %-5.2f | %-2d | Vol: %d ml |%n",
                getId(), getName(), getPrice(), getQuantity(), volume);
    }
    @Override
    public String getType() {
        return "Drink";
    }
}
