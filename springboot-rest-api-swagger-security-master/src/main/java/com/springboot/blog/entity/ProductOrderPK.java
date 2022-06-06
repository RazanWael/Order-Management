package com.springboot.blog.entity;

import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@NoArgsConstructor
//@Embeddable
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "order")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "product")
public class ProductOrderPK implements Serializable {
    
        private static final long serialVersionUID = 476151177562655457L;

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id")
        private Order order;

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id")
        private Product product;

         public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

//        @Override
//        public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//
//            result = prime * result + ((order.getOrderId() == null)
//                    ? 0
//                    : order
//                    .getOrderId().hashCode());
//            result = prime * result + ((product.getProductId() == null)
//                    ? 0
//                    : product
//                    .getProductId().hashCode());
//
//            return result;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) {
//                return true;
//            }
//            if (obj == null) {
//                return false;
//            }
//            if (getClass() != obj.getClass()) {
//                return false;
//            }
//            ProductOrderPK other = (ProductOrderPK) obj;
//            if (order == null) {
//                if (other.order != null) {
//                    return false;
//                }
//            } else if (!order.equals(other.order)) {
//                return false;
//            }
//
//            if (product == null) {
//                if (other.product != null) {
//                    return false;
//                }
//            } else if (!product.equals(other.product)) {
//                return false;
//            }
//
//            return true;
//        }


}
