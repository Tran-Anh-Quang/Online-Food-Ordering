package com.quangta.service.impl;

import com.quangta.entity.Cart;
import com.quangta.entity.CartItem;
import com.quangta.entity.Food;
import com.quangta.entity.User;
import com.quangta.payload.request.AddCartItemRequest;
import com.quangta.repository.CartItemRepository;
import com.quangta.repository.CartRepository;
import com.quangta.service.CartService;
import com.quangta.service.FoodService;
import com.quangta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final UserService userService;

    private final FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwtToken) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Food food = foodService.getFoodById(request.getFoodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        if (cart == null) {
            // Handle the case where the cart is null
            cart = new Cart();
            cart.setCustomer(user);
            cart = cartRepository.save(cart);
        }

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        CartItem newCartItem = new CartItem();

        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity() * food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * item.getFood().getPrice());

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwtToken) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItem item = cartItem.get();
        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) {
        long total = 0L;

        for (CartItem cartItem : cart.getItems()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isEmpty()){
            throw new Exception("cart not found with id: " + id);
        }
        return cartOptional.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) {
        Cart cart = findCartByUserId(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
