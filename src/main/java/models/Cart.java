package models;

import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<Item> contents =new LinkedList<Item>();
    
    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }

    public void addItemToCart(Item item){
        this.contents.add(item);
    }
}
