package kira.formation.stocks.stockes;

import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock getStock(String id) {
        return stockRepository.findById(id).orElse(null);
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteStock(String id) {
        stockRepository.deleteById(id);
    }

    public void deleteAllStocks() {
        stockRepository.deleteAll();
    }

    public Iterable<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
}
