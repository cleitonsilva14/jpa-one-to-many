package br.com.example.itemcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.itemcart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
