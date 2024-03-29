package com.quangta.service;

import com.quangta.entity.Cart;
import com.quangta.entity.CartItem;
import com.quangta.payload.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest request, String jwtToken) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwtToken) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;
}
