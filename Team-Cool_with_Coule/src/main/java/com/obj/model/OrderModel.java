package main.java.com.obj.model;

import java.util.List;
import java.util.Date;
import lombok.*;
import main.java.com.obj.Order;
import main.java.com.obj.ResponseStatus;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class OrderModel {

    private String orderId;
    private List<String> productNames;
    private Date orderDate;
    private String customerId;

    public OrderModel(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.productNames = builder.productNames;
        this.customerId = builder.customerId;
    }

    public OrderModel(Order order) {
        this.orderId = order.getOrderId();
        this.orderDate = order.getOrderDate();
        this.productNames = order.getProductNames();
        this.customerId = order.getCustomerId();
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String orderId;
        private List<String> productNames;
        private Date orderDate;
        private String customerId;
        //private CustomerModel customerModel;

        public Builder withOrderId(String orderIdToUse) {
            this.orderId = orderIdToUse;
            return this;
        }

        public Builder withProductNames(List<String> productNamesToUse) {
            this.productNames = productNamesToUse;
            return this;
        }

        public Builder withDate(Date orderDateToUse) {
            this.orderDate = orderDateToUse;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

//        public Builder withCustomerModel(CustomerModel customerModelToUse) {
//            this.customerModel = customerModelToUse;
//            return this;
//        }

        public OrderModel build() {return new OrderModel(this);}
    }
}
