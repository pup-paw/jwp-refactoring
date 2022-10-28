package kitchenpos.ui.request;

public class OrderStatusChangeRequest {

    private String orderStatus;

    public OrderStatusChangeRequest() {
    }

    public OrderStatusChangeRequest(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
