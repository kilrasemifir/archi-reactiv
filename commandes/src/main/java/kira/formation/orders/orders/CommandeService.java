package kira.formation.orders.orders;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    private final CommandeRepository oderRepository;
    private final CommandeProducer commandeProducer;


    public CommandeService(CommandeRepository oderRepository, CommandeProducer commandeProducer) {
        this.oderRepository = oderRepository;
        this.commandeProducer = commandeProducer;
    }

    public Commande createOrder(Commande commande) {
        System.out.println("OrderService.createOrder: "+ commande.getName());
        commande.setStatus("CREATED");
        commandeProducer.sendOrder(commande);
        return oderRepository.save(commande);
    }

    public Commande getOrder(String id) {
        return oderRepository.findById(id).orElse(null);
    }

    public List<Commande> getOrders() {
        return oderRepository.findAll();
    }

    public Commande updateOrder(Commande commande) {
        commande.setStatus("UPDATED");
        commandeProducer.sendOrder(commande);
        return oderRepository.save(commande);
    }

    public void deleteOrder(String id) {
        oderRepository.deleteById(id);
    }
}
