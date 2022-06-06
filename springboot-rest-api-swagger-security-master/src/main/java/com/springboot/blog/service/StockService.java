package com.springboot.blog.service;



import com.springboot.blog.Dto.StockDto;


import java.util.List;

public interface StockService {
    StockDto createStock(StockDto StockDto);

    List<StockDto> getAllStocks();

    List<StockDto> getStocksByProductID(int product_id);

    StockDto getStockById(int id);

    StockDto updateStock(StockDto StockDto, int id);

    void deleteStockById(int id);

    int quantityOfProductID(int product_id);
}
