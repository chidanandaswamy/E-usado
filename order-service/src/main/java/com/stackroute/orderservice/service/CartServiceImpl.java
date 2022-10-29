package com.stackroute.orderservice.service;

import com.stackroute.orderservice.exception.CartNotFoundException;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService{

    @Autowired
    public CartRepository cartRepository;


    @Override
    public Cart createCart(Cart cart) {

        Cart cart1= cartRepository.save(cart);
        if(cart1 != null ){
//            rabbitTemplate.convertAndSend("Order_exchange","Order_routing key",cart);
            System.out.println("Cart is added successfully.");
        } else {


            System.out.println("Cart Creation terminated.");
        }
        return cart;
    }

    @Override
    public Cart  getCartById(String cartOwnerEmail) {
        Cart carts = cartRepository.findByCartId(cartOwnerEmail);
        if (carts != null ) {
            return carts;

        }
        else{
            throw new CartNotFoundException("user email doesn't exist");
        }
    }

    @Override
    public boolean deleteCartById(String cartOwnerEmail) {
        Cart carted = cartRepository.findByCartId(cartOwnerEmail);
        if(carted != null){
            cartRepository.deleteByCartId(cartOwnerEmail);

            return true;
        } else {
            throw new CartNotFoundException("Cart with id " + cartOwnerEmail + " is not found.");
        }
    }
}
