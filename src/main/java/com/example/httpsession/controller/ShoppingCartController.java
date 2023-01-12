package com.example.httpsession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import models.Cart;
import models.Item;

@Controller
@RequestMapping(path="/cart")
public class ShoppingCartController {
    
    @GetMapping
    public String getCart(Model model, HttpSession session){
        // create a instance of a cart object
        // getAttribute() - get the cart object from the sessions will return null if object not in session 
        Cart cart = (Cart)session.getAttribute("cart");
        // if return null we bind cart to the duration of the sessions
        if(null == cart){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        model.addAttribute("item", new Item());
        model.addAttribute("cart", cart);
        
        return "cart";
    }

    @PostMapping()
    public String postData(Model model, HttpSession session,
        @Validated Item item, BindingResult bindResult) {
        Cart cart = (Cart)session.getAttribute("cart");
        
        if(bindResult.hasErrors()){
            model.addAttribute("item", item);
            model.addAttribute("cart", cart);
            return "cart";
        }

        cart.addItemToCart(item);
        model.addAttribute("item", item);
        model.addAttribute("cart", cart);
        return "cart";
    }
    
}
