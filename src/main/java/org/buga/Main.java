package org.buga;


import org.buga.data.GenericDAO;
import org.buga.data.GenericDAOImpl;
import org.buga.entity.Customer;
import org.buga.entity.Order;
import org.buga.entity.Product;
import org.buga.enums.Status;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program initiated");

        GenericDAO<Customer> customerDAO = new GenericDAOImpl<>(Customer.class);
        GenericDAO<Order> orderDAO = new GenericDAOImpl<>(Order.class);
        GenericDAO<Product> productDAO = new GenericDAOImpl<>(Product.class);

        Customer customer = new Customer();
        customer.setUsername("artiombuga");
        customer.setEmail("artiombuga@gmail.com");
        customer.setOrders(new ArrayList<>());

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setStatus(Status.PENDING);

        order.setCustomer(customer);
        customer.addOrder(order);

        Product product = new Product();
        product.setName("Iphone 15 Pro");
        product.setBrand("Apple");
        product.setPrice(15);
        product.setQuantity(50);

        order.addProducts(product);

        System.out.println(order.getProducts().toString());

        customerDAO.create(customer);
        orderDAO.create(order);

        System.out.println("Program terminated.");
    }
}