package org.genspark.inventory.domain;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="inventory")
@DynamicUpdate
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    int quantity;

    @Column
    float cost;  // purchase cost

    @Column
    float price;  // selling price

    @Column
    float weight;

    public Inventory() { }

    public Inventory(int quantity, float cost, float price, float weight) {
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
        this.weight = weight;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory inventory = (Inventory) o;

        return id != null ? id.equals(inventory.id) : inventory.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
