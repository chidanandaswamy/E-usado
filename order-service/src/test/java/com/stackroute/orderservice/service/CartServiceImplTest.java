package com.stackroute.orderservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.orderservice.exception.CartNotFoundException;
import com.stackroute.orderservice.model.Cart;
import com.stackroute.orderservice.model.DbSequence;
import com.stackroute.orderservice.model.Location;
import com.stackroute.orderservice.repository.CartRepository;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CartServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CartServiceImplTest {
    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @MockBean
    private MongoOperations mongoOperations;


    @Test
    void testGetSequenceNumber() {
        DbSequence dbSequence = new DbSequence();
        dbSequence.setId("42");
        dbSequence.setSeq("Seq");
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any())).thenReturn(dbSequence);
        assertEquals("Seq", cartServiceImpl.getSequenceNumber("Seq Name"));
        verify(mongoOperations).findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any());
    }

    @Test
    void testGetSequenceNumber2() {
        DbSequence dbSequence = mock(DbSequence.class);
        when(dbSequence.getSeq()).thenReturn("Seq");
        doNothing().when(dbSequence).setId((String) any());
        doNothing().when(dbSequence).setSeq((String) any());
        dbSequence.setId("42");
        dbSequence.setSeq("Seq");
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any())).thenReturn(dbSequence);
        assertEquals("Seq", cartServiceImpl.getSequenceNumber("Seq Name"));
        verify(mongoOperations).findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DbSequence>) any());
        verify(dbSequence).getSeq();
        verify(dbSequence).setId((String) any());
        verify(dbSequence).setSeq((String) any());
    }


    @Test
    void testCreateCart() {
        Cart cart = new Cart();
        cart.setCartId("42");
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
        cart1.setCartId("42");
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
        cart.setCartId("42");
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
        ArrayList<Cart> cartList = new ArrayList<>();
        when(cartRepository.findByCartId((String) any())).thenReturn(cartList);
        List<Cart> actualCartById = cartServiceImpl.getCartById("jane.doe@example.org");
        assertSame(cartList, actualCartById);
        assertTrue(actualCartById.isEmpty());
        verify(cartRepository).findByCartId((String) any());
    }


    @Test
    void testGetCartById2() {
        when(cartRepository.findByCartId((String) any())).thenThrow(new CartNotFoundException("foo"));
        assertThrows(CartNotFoundException.class, () -> cartServiceImpl.getCartById("jane.doe@example.org"));
        verify(cartRepository).findByCartId((String) any());
    }


    @Test
    void testDeleteCartById() {
        when(cartRepository.findByCartId((String) any())).thenReturn(new ArrayList<>());
        assertThrows(CartNotFoundException.class, () -> cartServiceImpl.deleteCartById("jane.doe@example.org"));
        verify(cartRepository).findByCartId((String) any());
    }


    @Test
    void testDeleteCartById2() {
        Cart cart = new Cart();
        cart.setCartId("42");
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

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(cart);
        when(cartRepository.deleteByCartId((String) any())).thenReturn(new ArrayList<>());
        when(cartRepository.findByCartId((String) any())).thenReturn(cartList);
        assertEquals("Deleted Successfully", cartServiceImpl.deleteCartById("jane.doe@example.org"));
        verify(cartRepository).deleteByCartId((String) any());
        verify(cartRepository).findByCartId((String) any());
    }


    @Test
    void testDeleteCartById3() {
        Cart cart = new Cart();
        cart.setCartId("42");
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

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(cart);
        when(cartRepository.deleteByCartId((String) any())).thenThrow(new CartNotFoundException("Deleted Successfully"));
        when(cartRepository.findByCartId((String) any())).thenReturn(cartList);
        assertThrows(CartNotFoundException.class, () -> cartServiceImpl.deleteCartById("jane.doe@example.org"));
        verify(cartRepository).deleteByCartId((String) any());
        verify(cartRepository).findByCartId((String) any());
    }
}

