package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        return items.add(item);
    }

    @Override
    public String toString() {
        if (items.size() == 0) {
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n");

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n");

        stringBuilder.append("Total...\t\t\t" + total);

        return stringBuilder.toString();
    }

    public boolean modifyItem(Item item) {
        items.removeIf(i -> Objects.equals(i.getProduct().getId(), item.getProduct().getId()));
        if (item.getQuantity() != 0) return this.addItem(item);
        else return true;
    }

    public int getQuantity(String id) {
        for (Item i : items) {
            if (i.getProduct().getId().equals(id))
                return i.getQuantity();
        }
        return 0;
    }

    public double total() {
        double total = 0;
        for (Item item : items) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }

}
