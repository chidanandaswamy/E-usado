package com.stackroute.orderservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.orderservice.exception.CartNotFoundException;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.Location;
import com.stackroute.orderservice.repository.CartRepository;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CartServiceImplTest {
    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private CartServiceImpl cartServiceImpl;



    @Test
    void testCreateCart() {
        Cart cart = new Cart();
        cart.setCartOwnerEmail("jane.doe@example.org");
        cart.setLocation(new Location());
        cart.setProductAddedTime(1L);
        cart.setProductAvailability(true);
        cart.setProductBrand("Product Brand");
        cart.setProductCategory("Product Category");
        cart.setProductDamageLevel(10.0f);
        cart.setProductDescription("Product Description");
        cart.setProductDiscount(10.0f);
        cart.setProductId("42");
        cart.setProductManufacturedYear("Product Manufactured Year");
        cart.setProductName("Product Name");
        cart.setProductOwnerEmail("jane.doe@example.org");
        cart.setProductPrice(10.0d);
        cart.setProductPurchasedDate(1L);
        cart.setProductSpecs(new HashMap<>());
        cart.setWarrantyStatus(true);
        when(cartRepository.save((Cart) any())).thenReturn(cart);

        Cart cart1 = new Cart();
        cart1.setCartOwnerEmail("jane.doe@example.org");
        cart1.setLocation(new Location());
        cart1.setProductAddedTime(1L);
        cart1.setProductAvailability(true);
        cart1.setProductBrand("Product Brand");
        cart1.setProductCategory("Product Category");
        cart1.setProductDamageLevel(10.0f);
        cart1.setProductDescription("Product Description");
        cart1.setProductDiscount(10.0f);
        cart1.setProductId("42");
        cart1.setProductManufacturedYear("Product Manufactured Year");
        cart1.setProductName("Product Name");
        cart1.setProductOwnerEmail("jane.doe@example.org");
        cart1.setProductPrice(10.0d);
        cart1.setProductPurchasedDate(1L);
        cart1.setProductSpecs(new HashMap<>());
        cart1.setWarrantyStatus(true);
        assertSame(cart1, cartServiceImpl.createCart(cart1));
        verify(cartRepository).save((Cart) any());
    }



    @Test
    void testCreateCart2() {
        when(cartRepository.save((Cart) any())).thenThrow(new CartNotFoundException("Cart Creation terminated."));

        Cart cart = new Cart();
        cart.setCartOwnerEmail("jane.doe@example.org");
        cart.setLocation(new Location());
        cart.setProductAddedTime(1L);
        cart.setProductAvailability(true);
        cart.setProductBrand("Product Brand");
        cart.setProductCategory("Product Category");
        cart.setProductDamageLevel(10.0f);
        cart.setProductDescription("Product Description");
        cart.setProductDiscount(10.0f);
        cart.setProductId("42");
        cart.setProductManufacturedYear("Product Manufactured Year");
        cart.setProductName("Product Name");
        cart.setProductOwnerEmail("jane.doe@example.org");
        cart.setProductPrice(10.0d);
        cart.setProductPurchasedDate(1L);
        cart.setProductSpecs(new HashMap<>());
        cart.setWarrantyStatus(true);
        assertThrows(CartNotFoundException.class, () -> cartServiceImpl.createCart(cart));
        verify(cartRepository).save((Cart) any());
    }



    @Test
    void testGetCartById() {
        Cart cart = new Cart();
        cart.setCartOwnerEmail("jane.doe@example.org");
        cart.setLocation(new Location());
        cart.setProductAddedTime(1L);
        cart.setProductAvailability(true);
        cart.setProductBrand("Product Brand");
        cart.setProductCategory("Product Category");
        cart.setProductDamageLevel(10.0f);
        cart.setProductDescription("Product Description");
        cart.setProductDiscount(10.0f);
        cart.setProductId("42");
        cart.setProductManufacturedYear("Product Manufactured Year");
        cart.setProductName("Product Name");
        cart.setProductOwnerEmail("jane.doe@example.org");
        cart.setProductPrice(10.0d);
        cart.setProductPurchasedDate(1L);
        cart.setProductSpecs(new HashMap<>());
        cart.setWarrantyStatus(true);
        when(cartRepository.findByCartId((String) any())).thenReturn(cart);
        assertSame(cart, cartServiceImpl.getCartById("jane.doe@example.org"));
        verify(cartRepository).findByCartId((String) any());
    }



    @Test
    void testGetCartById2() {
        when(cartRepository.findByCartId((String) any()))
                .thenThrow(new CartNotFoundException("user email doesn't exist"));
        assertThrows(CartNotFoundException.class, () -> cartServiceImpl.getCartById("jane.doe@example.org"));
        verify(cartRepository).findByCartId((String) any());
    }



    @Test
    void testDeleteCartById() {
        Cart cart = new Cart();
        cart.setCartOwnerEmail("jane.doe@example.org");
        cart.setLocation(new Location());
        cart.setProductAddedTime(1L);
        cart.setProductAvailability(true);
        cart.setProductBrand("Product Brand");
        cart.setProductCategory("Product Category");
        cart.setProductDamageLevel(10.0f);
        cart.setProductDescription("Product Description");
        cart.setProductDiscount(10.0f);
        cart.setProductId("42");
        cart.setProductManufacturedYear("Product Manufactured Year");
        cart.setProductName("Product Name");
        cart.setProductOwnerEmail("jane.doe@example.org");
        cart.setProductPrice(10.0d);
        cart.setProductPurchasedDate(1L);
        cart.setProductSpecs(new HashMap<>());
        cart.setWarrantyStatus(true);

        Cart cart1 = new Cart();
        cart1.setCartOwnerEmail("jane.doe@example.org");
        cart1.setLocation(new Location());
        cart1.setProductAddedTime(1L);
        cart1.setProductAvailability(true);
        cart1.setProductBrand("Product Brand");
        cart1.setProductCategory("Product Category");
        cart1.setProductDamageLevel(10.0f);
        cart1.setProductDescription("Product Description");
        cart1.setProductDiscount(10.0f);
        cart1.setProductId("42");
        cart1.setProductManufacturedYear("Product Manufactured Year");
        cart1.setProductName("Product Name");
        cart1.setProductOwnerEmail("jane.doe@example.org");
        cart1.setProductPrice(10.0d);
        cart1.setProductPurchasedDate(1L);
        cart1.setProductSpecs(new HashMap<>());
        cart1.setWarrantyStatus(true);
        when(cartRepository.deleteByCartId((String) any())).thenReturn(cart1);
        when(cartRepository.findByCartId((String) any())).thenReturn(cart);
        assertTrue(cartServiceImpl.deleteCartById("jane.doe@example.org"));
        verify(cartRepository).deleteByCartId((String) any());
        verify(cartRepository).findByCartId((String) any());
    }



    @Test
    void testDeleteCartById2() {
        Cart cart = new Cart();
        cart.setCartOwnerEmail("jane.doe@example.org");
        cart.setLocation(new Location());
        cart.setProductAddedTime(1L);
        cart.setProductAvailability(true);
        cart.setProductBrand("Product Brand");
        cart.setProductCategory("Product Category");
        cart.setProductDamageLevel(10.0f);
        cart.setProductDescription("Product Description");
        cart.setProductDiscount(10.0f);
        cart.setProductId("42");
        cart.setProductManufacturedYear("Product Manufactured Year");
        cart.setProductName("Product Name");
        cart.setProductOwnerEmail("jane.doe@example.org");
        cart.setProductPrice(10.0d);
        cart.setProductPurchasedDate(1L);
        cart.setProductSpecs(new HashMap<>());
        cart.setWarrantyStatus(true);
        when(cartRepository.deleteByCartId((String) any())).thenThrow(new CartNotFoundException("foo"));
        when(cartRepository.findByCartId((String) any())).thenReturn(cart);
        assertThrows(CartNotFoundException.class, () -> cartServiceImpl.deleteCartById("jane.doe@example.org"));
        verify(cartRepository).deleteByCartId((String) any());
        verify(cartRepository).findByCartId((String) any());
    }
}

