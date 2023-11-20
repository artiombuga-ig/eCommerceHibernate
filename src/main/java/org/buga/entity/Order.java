package org.buga.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.buga.enums.Status;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    Date orderDate;

    Status status;

    @ManyToOne
    @JoinColumn(name = "customers_id")
    private Customer customer;

    @ManyToMany (cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable (name = "orders_products",
            joinColumns = {@JoinColumn(name = "orders_id") },
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private Set<Product> products = new HashSet<>();

    public void addProducts(Product product) {
        this.products.add(product);
        product.getOrders().add(this);
    }
}
