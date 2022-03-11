package com.example.webpos.biz;

import com.example.webpos.db.PosDB;
import com.example.webpos.model.Cart;
import com.example.webpos.model.Item;
import com.example.webpos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {

        Cart cart = posDB.getCart();
        if (cart == null) {
            cart = this.newCart();
        }
        return cart;
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public void checkout(Cart cart) {
        double total = cart.total();
        this.newCart();
    }

    @Override
    public double total(Cart cart) {
        return cart.total();
    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {
        if (this.getCart().getQuantity(productId) != 0) {
            return this.modify(productId, amount + this.getCart().getQuantity(productId));
        } else {
            Product product = posDB.getProduct(productId);
            if (product == null) return false;

            return this.getCart().addItem(new Item(product, amount));
        }
    }

    @Override
    public boolean modify(String productId, int amount) {
        if (amount < 0) return false;
        Product product = posDB.getProduct(productId);
        if (product == null || this.getCart() == null) return false;
        return this.getCart().modifyItem(new Item(product, amount));
    }

    public int getCartItemQuantity(String productId) {
        if (this.posDB.getCart() == null)
            return 0;
        return this.posDB.getCart().getQuantity(productId);
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
