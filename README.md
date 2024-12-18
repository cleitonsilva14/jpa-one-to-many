# Spring JPA @OneToMany @ManyToOne

### application.properties - Mysql
```markdown
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/itemcart_db
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true
server.port=8888
```

__Cart.java__
```java
@Entity
@AllArgsConstructor 
@NoArgsConstructor
@Getter @Setter
@Table(name = "tb_cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String customerName;
	private String customerAddress;
	
	@JsonIgnoreProperties(value="cart")
	@OneToMany(mappedBy = "cart")
	private Set<Item> items =  new HashSet<>();
	
}
```
__Item.java__
```java
@Entity
@AllArgsConstructor 
@NoArgsConstructor
@Getter @Setter
@Table(name = "tb_item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	@JsonIgnoreProperties(value="items")
	@ManyToOne
	@JoinColumn(name = "cardId")
	private Cart cart;
	
}
```

POST item
```json
{
    "name": "B550m Aorus Elite AM4",
    "description": "PLCA MÃE AMD GIGABYTE B550M AORUS ELITE"
}
```

POST course

```json
{
    "customerName": "customer03",
    "customerAddress": "MG"
}
```

PUT 

http://localhost:8888/add/item/to/cart?cartId=1&itemId=1



__ShoppingController.java__
```java
...
@PutMapping("/add/item/to/cart")
	public String addItemToCart(@RequestParam Integer cartId, @RequestParam Integer itemId) {
		Cart cart = cartRepository.findById(cartId).get();
		Item item = itemRepository.findById(itemId).get();
		item.setCart(cart);
		
		itemRepository.save(item);
		return "Item added to cart successfully!";
	}
	
...
```
