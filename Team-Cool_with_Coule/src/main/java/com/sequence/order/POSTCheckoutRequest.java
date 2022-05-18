package main.java.com.sequence.order;

import lombok.*;
import main.java.com.obj.model.CustomerModel;
import main.java.com.obj.model.OrderModel;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class POSTCheckoutRequest {
    private OrderModel orderModel;
    private Date date;
    private CustomerModel customerModel;

    public POSTCheckoutRequest(Builder builder) {
        this.customerModel = builder.customerModel;
        this.date = builder.date;
        this.orderModel = builder.orderModel;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private CustomerModel customerModel;
        private Date date;
        private OrderModel orderModel;
       // private ResponseStatus responseStatus;

        private Builder() {}

        public Builder withCustomerModel(CustomerModel customerModelToUse) {
            this.customerModel = customerModelToUse;
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

        public POSTCheckoutRequest build() { return new POSTCheckoutRequest(this);}

    }

}
