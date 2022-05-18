package main.java.com.sequence.order;

import main.java.com.obj.ResponseStatus;
import main.java.com.obj.model.OrderModel;

public class GETOrderResult {

    private OrderModel order;
    private ResponseStatus responseStatus;

    public GETOrderResult(Builder builder) {
        this.order = builder.order;
        this.responseStatus = builder.responseStatus;
    }

    public OrderModel getOrder() { return this.order; }

    public void setOrder(OrderModel order) { this.order = order; }

    public ResponseStatus getResponseStatus() {
        return this.responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private OrderModel order;
        private ResponseStatus responseStatus;

        public Builder withOrder(OrderModel order) {
            this.order = order;
            return this;
        }

        public Builder withResponseStatus(ResponseStatus responseStatus) {
            this.responseStatus = responseStatus;
            return this;
        }

        public GETOrderResult build() { return new GETOrderResult(this); }
    }

}
