package kira.formation.orders.orders;

import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour les commandes.
 */
@RestController
@RequestMapping("api/orders")
public class CommandeController {

        private CommandeService orderService;

        public CommandeController(CommandeService orderService) {
            this.orderService = orderService;
        }

        @PostMapping
        public Commande createOrder(@RequestBody Commande commande) {
            return orderService.createOrder(commande);
        }

        @GetMapping("{id}")
        public Commande getOrder(@PathVariable String id) {
            return orderService.getOrder(id);
        }

        @GetMapping
        public Iterable<Commande> getOrders() {
            return orderService.getOrders();
        }

        @PutMapping
        public Commande updateOrder(@RequestBody Commande commande) {
            return orderService.updateOrder(commande);
        }

        @DeleteMapping("{id}")
        public void deleteOrder(@PathVariable String id) {
            orderService.deleteOrder(id);
        }
}
