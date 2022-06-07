package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

//Razan Yassin
//1182226
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "order")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "product")
public class ProductOrderPK implements Serializable {
    
        private static final long serialVersionUID = 476151177562655457L;
        //Defining the many-to-one relationship between the productorder table and the order table

        //Setter and Getters

         Integer orderId;
         Integer productId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
        public int hashCode() {
            return Objects.hash(orderId,productId);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ProductOrderPK other = (ProductOrderPK) obj;
            return Objects.equals(getOrderId(), other.getOrderId())&& Objects.equals(getProductId(),other.getProductId());
        }


}
