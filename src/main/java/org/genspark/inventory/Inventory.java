package org.genspark.inventory;

public class Inventory {

    Long id;
    int quantity;
    float cost;  // purchase cost
    float price;  // selling price
    float weight;

    public Inventory() { }

    public Inventory(Long id, int quantity, float cost, float price, float weight) {
        this.id = id;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", qty=" + quantity +
                ", cost=" + cost +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
