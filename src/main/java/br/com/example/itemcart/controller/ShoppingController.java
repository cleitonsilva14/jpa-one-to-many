package br.com.example.itemcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.itemcart.entity.Cart;
import br.com.example.itemcart.entity.Item;
import br.com.example.itemcart.repository.CartRepository;
import br.com.example.itemcart.repository.ItemRepository;

@RestController
public class ShoppingController {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	@GetMapping("/carts")
	public List<Cart> getAllCarts() {
		return cartRepository.findAll();
	}
	
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	@PostMapping("/cart")
	public Cart addCart(@RequestBody Cart cart) {
		
		return cartRepository.save(cart);
	}
	
	@PostMapping("/item")
	public Item addItem(@RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	
	@PutMapping("/add/item/to/cart")
	public String addItemToCart(@RequestParam Integer cartId, @RequestParam Integer itemId) {
		Cart cart = cartRepository.findById(cartId).get();
		Item item = itemRepository.findById(itemId).get();
		item.setCart(cart);
		
		itemRepository.save(item);
		return "Item added to cart successfully!";
	}
	
	
}
