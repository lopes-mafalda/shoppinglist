package io.codeforall.android.shoppinglist.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private int id;
    private final String name;
    private final String note;

    public Item(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public Item(int id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public static List<Item> list() {
        return new ArrayList<>(List.of(
                new Item(1, "Notebook", "A lined notebook"),
                new Item(2, "Graphing calculator", "Need it for Math"),
                new Item(3, "Monitor stand", "Don't forget!!"),
                new Item(4, "Ethernet cable", "Need it for online meetings"),
                new Item(5, "Sticky notes", "Almost out!!")
        ));
    }
}
