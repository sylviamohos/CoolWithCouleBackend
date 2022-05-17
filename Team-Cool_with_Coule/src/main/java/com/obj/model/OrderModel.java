package main.java.com.obj.model;

import java.util.List;
import java.util.Date;
import lombok.*;
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

    //TODO
    // private CustomerModel customerModel;


    public OrderModel(Builder builder) {
        this.orderId = builder.orderId;
        this.orderDate = builder.orderDate;
        this.productNames = builder.productNames;

        // TODO
        //this.customerModel = builder.customerModel;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String orderId;
        private List<String> productNames;
        private Date orderDate;
        private ResponseStatus responseStatus;
        // TODO CustomerModel customerModel;

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

        //TODO
        /*
        public Builder withCustomerModel(CustomerModel customerModelToUse) {
            this.customerModel = customerModelToUse;
        }
        */

        public OrderModel build() {return new OrderModel(this);}
    }
}
