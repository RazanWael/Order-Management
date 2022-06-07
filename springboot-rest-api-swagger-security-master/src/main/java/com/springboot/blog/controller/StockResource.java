package com.springboot.blog.controller;


import com.springboot.blog.Dto.StockDto;
import com.springboot.blog.exception.BadRequestException;
import com.springboot.blog.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Razan Yassin
//1182226
@RestController
@RequestMapping("/api/stock")

public class StockResource {

        private final Logger log = LoggerFactory.getLogger(StockResource.class);

        //    @Autowired //@Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean. The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
        private StockService stockService; //the use of interface rather than class is important for loose coupling

        // Constructor based  injection


    public StockResource(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
        public ResponseEntity<List<StockDto>> getAllStocks() {
            return ResponseEntity.ok().body(stockService.getAllStocks()); //ResponseEntity represents an HTTP response, including headers, body, and status.
        }

        @GetMapping("/{id}")
        public ResponseEntity<StockDto> getStockById(
                @PathVariable(name = "id") int id) {
            return ResponseEntity.ok(stockService.getStockById(id));
        }

        @GetMapping("/product/{id}")
        public ResponseEntity<List<StockDto>> getStocksByProductID(
                @PathVariable(name = "id") int id) {
            return ResponseEntity.ok(stockService.getStocksByProductID(id));
        }

        @PostMapping
        public ResponseEntity<StockDto> createStock
        (@Valid @RequestBody StockDto stockDto) {

            if (stockDto.getStockId() != 0) {
                log.error("Cannot have an ID {}", stockDto);
                throw new BadRequestException(StockResource.class.getSimpleName(),
                        "Id");
            }
            return new ResponseEntity(stockService.createStock(stockDto), HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<StockDto> updateStock
                (@Valid @RequestBody StockDto stockDto
                        , @PathVariable(name = "id") int id) {
            return new ResponseEntity<>(stockService.updateStock(stockDto, id), HttpStatus.OK);
        }

        //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteStock(@PathVariable(name = "id") int id) {
            stockService.deleteStockById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        }


}
