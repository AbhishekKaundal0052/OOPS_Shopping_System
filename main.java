import java.util.ArrayList;

public class main{
    public static abstract class Product{
        private String name;
        private double price;
        private int stock; 
        public Product(String n, double p, int s){
            this.name = n;
            this.price = p;
            this.stock = s;
        }
        public String getName(){
            return name;
        }
        public void setName(String n){
            this.name = n;
        }
        public double getPrice(){
            return price;
        }
        public void setPrice(double p){
            this.price = p;
        }
        public int getStock(){
            return stock;
        }
        public void setStock(int s){
            this.stock = s;
        }
        public abstract void displayDetails();
        public abstract boolean purchase(int quantity);
    };   
    public static class Electronics extends Product{
        private int warranty;
        public Electronics(String name, double price, int stock, int warranty){
            super(name, price, stock);
            this.warranty = warranty;
        }
        @Override
        public void displayDetails(){
            System.out.println("Electronics: " + getName() + ", Price: $" + getPrice() +
            ", Warranty: " + warranty + " months");
        }
        @Override
        public boolean purchase(int quantity) {
            if (quantity <= getStock()) {
                setStock(getStock() - quantity);
                System.out.println("Purchased " + quantity + " units of " + getName());
                return true;
            } else {
                System.out.println("Not enough stock for " + getName());
                return false;
            }
        }
    }  
    public static class Clothes extends Product {
        private String size;

        public Clothes(String name, double price, int stock, String size) {
            super(name, price, stock);
            this.size = size;
        }

        @Override
        public void displayDetails() {
            System.out.println("Clothing: " + getName() + ", Price: $" + getPrice() + ", Size: "+ size);
        }

        @Override
        public boolean purchase(int quantity) {
            if (quantity <= getStock()) {
                setStock(getStock() - quantity);
                System.out.println("Purchased " + quantity + " units of " + getName());
                return true;
            }
            System.out.println("Not enough stock for " + getName());
            return false;
        }
    }
    public static class ShoppingCart {
        private ArrayList<Product> cartItems = new ArrayList<>();

        public void addItem(Product product) {
            cartItems.add(product);
            System.out.println(product.getName() + " added to the cart.");
        }

        public void viewCart() {
            System.out.println("Cart Items:");
            for (Product product : cartItems) {
                product.displayDetails();
            }
        }

        public double getTotalPrice() {
            double total = 0;
            for (Product product : cartItems) {
                total += product.getPrice();
            }
            return total;
        }

        public void checkout() {
            System.out.println("Checking out with the following items:");
            viewCart();
            System.out.println("Total Price: $" + getTotalPrice());
            cartItems.clear();
        }
    }

    public static class Customer {
        private String name;
        private ShoppingCart cart;

        public Customer(String name) {
            this.name = name;
            this.cart = new ShoppingCart();
        }

        public String getName() {
            return name;
        }

        public ShoppingCart getCart() {
            return cart;
        }
    }
    public static void main(String[] args){
        Customer customer = new Customer("Akash");
        Electronics smartphone = new Electronics("Smartphone", 699.99, 50, 24);
        Clothes shirt = new Clothes("Shirt", 499.99, 100, "XL");
        customer.getCart().addItem(smartphone);
        customer.getCart().addItem(shirt);
        customer.getCart().viewCart();
        customer.getCart().checkout();
    }
}