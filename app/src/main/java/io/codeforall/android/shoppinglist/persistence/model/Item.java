package io.codeforall.android.shoppinglist.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private long id;
    private final String name;
    private final Integer quantity;

    public Item(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Item(int id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return String.valueOf(quantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static List<Item> list() {
        return new ArrayList<>(List.of(
                new Item(1, "Notebook", 1),
                new Item(2, "Graphing calculator", 2),
                new Item(3, "Monitor stand", 10),
                new Item(4, "Ethernet cable", 1),
                new Item(5, "Sticky notes", 9)
        ));
    }
}
