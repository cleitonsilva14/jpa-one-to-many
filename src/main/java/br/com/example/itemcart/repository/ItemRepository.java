package br.com.example.itemcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.example.itemcart.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
