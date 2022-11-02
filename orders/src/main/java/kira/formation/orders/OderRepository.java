package kira.formation.orders;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OderRepository extends MongoRepository<Order, String> {
}
