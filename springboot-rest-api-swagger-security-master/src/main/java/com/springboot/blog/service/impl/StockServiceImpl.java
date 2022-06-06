package com.springboot.blog.service.impl;

import com.springboot.blog.Dto.StockDto;
import com.springboot.blog.entity.Product;
import com.springboot.blog.entity.Stock;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.StockRepository;
import com.springboot.blog.service.StockService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

        private StockRepository stockRepository;

         public StockServiceImpl(StockRepository stockRepository) {
          this.stockRepository = stockRepository;
         }


        @Override
        public StockDto createStock(StockDto stockDto) {
            // convert DTO to entity
            Stock stock = mapToEntity(stockDto);
            Stock newStock = stockRepository.save(stock);

            // convert entity to DTO
            StockDto stockResponse = mapToDTO(newStock);
            return stockResponse;
        }

        @Override
        public List<StockDto> getAllStocks() {
            List<Stock> stocks = stockRepository.findAll();
            return stocks.stream().map(stock -> mapToDTO(stock))
                    .collect(Collectors.toList());
        }

        @Override
        public int quantityOfProductID(int product_id){
             //This function aims to find the total quantity for a specific product, by checking all available stocks having this product id.
             List<Stock> allStocks = stockRepository.findAll();
             int totalQuantity = 0;
             for(int i=0;i<allStocks.size();i++){
                 if(allStocks.get(i).getProduct().getProductId() == product_id) {
                     totalQuantity += allStocks.get(i).getQuantity();
                 }
             }
             return totalQuantity;
        }

        @Override
        public List<StockDto> getStocksByProductID(int product_id){
            List<Stock> stocks = stockRepository.findAll();
            List<StockDto> output= stocks.stream().map(stock -> mapToDTO(stock))
                    .collect(Collectors.toList());
            for (int i=0;i<output.size();i++){
                if(output.get(i).getProduct_id()!=product_id)
                    output.remove(i);
            }
            return output;
        }
    @Override
        public StockDto getStockById(int id) {
            Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
            return mapToDTO(stock);
        }

        @Override
        public StockDto updateStock(StockDto stockDto, int id) {
            // get category by id from the database
            Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
//            stock.setUpdateAt(new Date());
            stock.setQuantity(stockDto.getQuantity());
            Stock updatedStock = stockRepository.save(stock);
            return mapToDTO(updatedStock);
        }

        @Override
        public void deleteStockById(int id) {
            // get category by id from the database
            Stock stock = stockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock", "id", id));
            stockRepository.delete(stock);
        }

        // convert Entity into DTO
        private StockDto mapToDTO(Stock stock) {
            StockDto stockDto = new StockDto();
            stockDto.setStockId(stock.getStockId());
            stockDto.setQuantity(stock.getQuantity());
            stockDto.setProduct_id(stock.getProduct().getProductId());
            stockDto.setUpdateAt(new Date());
            return stockDto;
        }

        // convert DTO to entity
        private Stock mapToEntity(StockDto stockDto) {
            Stock stock = new Stock();
            stock.setQuantity(stockDto.getQuantity());
            stock.setUpdateAt(new Date());
            stock.setProduct(new Product(stockDto.getProduct_id()));
            return stock;
        }


}
