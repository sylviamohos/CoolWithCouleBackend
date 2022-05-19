package main.java.com.sequence.order;

import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.OrderModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class POSTCheckoutResult {
    private OrderModel orderModel;
    private ResponseStatus responseStatus;
    private String customerId;
    private List<String> productIds;

    public POSTCheckoutResult(Builder builder) {
        this.customerId = builder.customerId;
        this.orderModel = builder.orderModel;
        this.responseStatus = builder.responseStatus;
        this.productIds = builder.productNames;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private List<String> productNames;
        private OrderModel orderModel;
        private ResponseStatus responseStatus;
        private String customerId;

        public static Builder builder() { return new Builder(); }

        public Builder withProductNames(List<String> productIds) {
            this.productNames = productIds;
            return this;
        }

        public Builder withOrderModel(OrderModel orderModel) {
            this.orderModel = orderModel;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withResponseStatus(ResponseStatus responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public POSTCheckoutResult build() { return new POSTCheckoutResult(this);}
    }
}
