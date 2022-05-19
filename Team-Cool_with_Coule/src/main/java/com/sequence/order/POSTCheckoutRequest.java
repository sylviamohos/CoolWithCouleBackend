package main.java.com.sequence.order;

import lombok.*;
import main.java.com.obj.model.OrderModel;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class POSTCheckoutRequest {
    private OrderModel orderModel;
    private Date date;
    private String customerId;
    private List<String> productNames;

    public POSTCheckoutRequest(Builder builder) {
        this.customerId = builder.customerId;
        this.date = builder.date;
        this.orderModel = builder.orderModel;
        this.productNames = builder.productNames;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String customerId;
        private Date date;
        private OrderModel orderModel;
        private List<String> productNames;

        private Builder() {}

        public Builder withCustomerModel(String customerIdToUse) {
            this.customerId = customerIdToUse;
            return this;
        }

        public Builder withDate(Date dateToUse) {
            this.date = dateToUse;
            return this;
        }

        public Builder withOrderModel(OrderModel orderModelToUse) {
            this.orderModel = orderModelToUse;
            return this;
        }

        public Builder withProductNames(List<String> productIds) {
            this.productNames = productIds;
            return this;
        }

        public POSTCheckoutRequest build() { return new POSTCheckoutRequest(this);}

    }

}
