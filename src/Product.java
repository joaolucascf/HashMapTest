public class Product {
    private String name;
    private double price;

    public Product(String _name, double _price){
        this.name = _name;
        this.price = _price;
    }

    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
}
