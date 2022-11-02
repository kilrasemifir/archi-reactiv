package kira.formation.orders;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OderRepository oderRepository;

    public OrderService(OderRepository oderRepository) {
        this.oderRepository = oderRepository;
    }

    public Order createOrder(Order order) {
        System.out.println("OrderService.createOrder: "+order.getName());
        return oderRepository.save(order);
    }

    public Order getOrder(String id) {
        return oderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrders() {
        return oderRepository.findAll();
    }

    public Order updateOrder(Order order) {
        return oderRepository.save(order);
    }

    public void deleteOrder(String id) {
        oderRepository.deleteById(id);
    }
}
