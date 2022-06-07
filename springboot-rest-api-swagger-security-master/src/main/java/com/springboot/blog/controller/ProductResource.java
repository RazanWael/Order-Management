package com.springboot.blog.controller;

import com.springboot.blog.exception.BadRequestException;
import com.springboot.blog.Dto.ProductDto;
import com.springboot.blog.service.ProductService;
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
@RequestMapping("/api/product")
public class ProductResource {


        private final Logger log = LoggerFactory.getLogger(ProductResource.class);

        //    @Autowired //@Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean. The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
        private ProductService productService; //the use of interface rather than class is important for loose coupling

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

         @GetMapping
        public ResponseEntity<List<ProductDto>> getAllProducts() {
            return ResponseEntity.ok().body(productService.getAllProducts()); //ResponseEntity represents an HTTP response, including headers, body, and status.
        }

        @GetMapping("/{id}")
        public ResponseEntity<ProductDto> getProductById(
                @PathVariable(name = "id") int id) {
            return ResponseEntity.ok(productService.getProductById(id));
        }


        @PostMapping
        public ResponseEntity<ProductDto> createProduct
        (@Valid @RequestBody ProductDto productDto) {
            if (productDto.getProductId() != 0) {
                log.error("Cannot have an ID {}", productDto);
                throw new BadRequestException(ProductResource.class.getSimpleName(),
                        "Id");
            }
            return new ResponseEntity(productService.createProduct(productDto), HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<ProductDto> updateProduct
                (@Valid @RequestBody ProductDto productDto
                        , @PathVariable(name = "id") int id) {
            return new ResponseEntity<>(productService.updateProduct(productDto, id), HttpStatus.OK);
        }

        //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {
            productService.deleteProductById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
            return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
        }


}
