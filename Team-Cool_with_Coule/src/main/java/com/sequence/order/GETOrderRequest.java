package main.java.com.sequence.order;

import lombok.*;
import main.java.com.obj.model.CustomerModel;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class GETOrderRequest {
    private String orderId;
    private List<String> productNames;
    private Date orderDate;
    private CustomerModel customerModel;


    public GETOrderRequest(Builder builder) {
        this.orderId = builder.orderId;
        this.productNames = builder.productNames;
        this.orderDate = builder.orderDate;
        this.customerModel = builder.customerModel;
    }

    public static Builder build() { return new Builder();}

    public static final class Builder {
        private String orderId;
        private Date orderDate;
        private List<String> productNames;
        private CustomerModel customerModel;

        private Builder() {}

        public Builder withOrderId(String orderIdToUse) {
            this.orderId = orderIdToUse;
            return this;
        }

        public Builder withOrderDate(Date orderDateToUse) {
            this.orderDate = orderDateToUse;
            return this;
        }

        public Builder withProductNames(List<String> productNamesToUse) {
            this.productNames = productNamesToUse;
            return this;
        }

        public Builder withCustomerModel(CustomerModel customerModelToUse) {
            this.customerModel = customerModelToUse;
            return this;
        }

        public GETOrderRequest build() { return new GETOrderRequest(this); }
    }

}
