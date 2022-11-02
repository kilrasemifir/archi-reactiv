package kira.formation.orders;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OderController {

        private OrderService orderService;

        public OderController(OrderService orderService) {
            this.orderService = orderService;
        }

        @PostMapping
        public Order createOrder(@RequestBody Order order) {
            return orderService.createOrder(order);
        }

        @GetMapping("{id}")
        public Order getOrder(@PathVariable String id) {
            return orderService.getOrder(id);
        }

        @GetMapping
        public Iterable<Order> getOrders() {
            return orderService.getOrders();
        }

        @PutMapping
        public Order updateOrder(@RequestBody Order order) {
            return orderService.updateOrder(order);
        }

        @DeleteMapping("{id}")
        public void deleteOrder(@PathVariable String id) {
            orderService.deleteOrder(id);
        }
}
