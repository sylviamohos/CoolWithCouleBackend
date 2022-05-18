package main.java.com.sequence.order;

import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.CustomerModel;
import main.java.com.obj.model.OrderModel;
import lombok.*;

@Getter
@Setter

public class POSTCheckoutResult {
    private OrderModel orderModel;
    private ResponseStatus responseStatus;
    private CustomerModel customerModel;

    public POSTCheckoutResult(Builder builder) {
        this.customerModel = builder.customerModel;
        this.orderModel = builder.orderModel;
        this.responseStatus = builder.responseStatus;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private OrderModel orderModel;
        private ResponseStatus responseStatus;
        private CustomerModel customerModel;

        public static Builder builder() { return new Builder(); }

        public Builder withOrderModel(OrderModel orderModel) {
            this.orderModel = orderModel;
            return this;
        }

        public Builder withCustomerModel(CustomerModel customerModel) {
            this.customerModel = customerModel;
            return this;
        }

        public Builder withResponseStatus(ResponseStatus responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public POSTCheckoutResult build() { return new POSTCheckoutResult(this);}
    }
}
