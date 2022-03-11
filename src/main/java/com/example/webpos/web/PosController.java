package com.example.webpos.web;

import com.example.webpos.biz.PosService;
import com.example.webpos.model.Item;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.logging.Logger;

@Controller
public class PosController {

    private PosService posService;

    private static final Log logger = LogFactory.getLog(PosController.class);

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
        posService.add("PD1", 2);
    }

    @GetMapping("/")
    public String pos(Model model) {
        model.addAttribute("products", posService.products());
        model.addAttribute("cart", posService.getCart());
        model.addAttribute("totalPrice", posService.total(posService.getCart()));
        return "index";
    }

    @GetMapping("addCartItem/{id}")
    public String addCartItem(@PathVariable String id, Model model) {
        int quantity = posService.getCartItemQuantity(id);
        posService.modify(id, quantity + 1);
        logger.info("addCartItem" + id);
        return "redirect:/";
    }

    @GetMapping("decCartItem/{id}")
    public String decCartItem(@PathVariable String id, Model model) {
        int quantity = posService.getCartItemQuantity(id);
        posService.modify(id, quantity - 1);
        logger.info("decCartItem" + id);
        return "redirect:/";
    }

    @GetMapping("deleteCartItem/{id}")
    public String deleteCartItem(@PathVariable String id, Model model) {
        posService.modify(id, 0);
        logger.info("deleteCartItem" + id);
        return "redirect:/";
    }

    @GetMapping("addItem/{id}")
    public String addItem(@PathVariable String id, Model model) {
        posService.add(id, 1);
        logger.info("addItem" + id);
        return "redirect:/";
    }

    @GetMapping("/cancel")
    public String cancel(Model model) {
        logger.info("cancel");
        posService.newCart();
        return "redirect:/";
    }

    @GetMapping("/charge")
    public String charge(Model model) {
        logger.info("charge");
        posService.checkout(posService.getCart());
        return "redirect:/";
    }
}
