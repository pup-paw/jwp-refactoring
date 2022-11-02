package kitchenpos.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kitchenpos.product.domain.OrderStatus;
import org.springframework.util.CollectionUtils;

public class Order {

    private final Long id;
    private final Long orderTableId;
    private OrderStatus orderStatus;
    private final LocalDateTime orderedTime;
    private final List<OrderLineItem> orderLineItems;

    public Order(final Long id, final Long orderTableId, final String orderStatus,
                 final LocalDateTime orderedTime) {
        this.id = id;
        this.orderTableId = orderTableId;
        this.orderStatus = OrderStatus.valueOf(orderStatus);
        this.orderedTime = orderedTime;
        this.orderLineItems = new ArrayList<>();
    }

    public Order(final Order order, final List<OrderLineItem> orderLineItems) {
        this(order.getId(), order.getOrderTableId(), order.orderStatus, order.orderedTime, orderLineItems);
    }

    public Order(final Long orderTableId, final List<OrderLineItem> orderLineItems) {
        this(null, orderTableId, null, null, orderLineItems);
    }

    public Order(final Long orderTableId, final OrderStatus orderStatus, final LocalDateTime orderedTime,
                 final List<OrderLineItem> orderLineItems) {
        this(null, orderTableId, orderStatus, orderedTime, orderLineItems);
    }

    public Order(final Long id, final Long orderTableId, final OrderStatus orderStatus, final LocalDateTime orderedTime,
                 final List<OrderLineItem> orderLineItems) {
        validateOrderLineItems(orderLineItems);
        this.id = id;
        this.orderTableId = orderTableId;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
        this.orderLineItems = orderLineItems;
    }

    private void validateOrderLineItems(final List<OrderLineItem> orderLineItems) {
        if (CollectionUtils.isEmpty(orderLineItems)) {
            throw new IllegalArgumentException();
        }
    }

    public void changeOrderStatus(final OrderStatus orderStatus) {
        if (isStatusCompletion()) {
            throw new IllegalArgumentException();
        }
        this.orderStatus = orderStatus;
    }

    private boolean isStatusCompletion() {
        return Objects.equals(OrderStatus.COMPLETION, orderStatus);
    }

    public boolean isStatusCooking() {
        return Objects.equals(OrderStatus.COOKING, orderStatus);
    }

    public boolean isStatusMeal() {
        return Objects.equals(OrderStatus.MEAL, orderStatus);
    }

    public Long getId() {
        return id;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public String getOrderStatus() {
        return orderStatus.name();
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void addOrderLineItems(final List<OrderLineItem> orderLineItems) {
        this.orderLineItems.addAll(orderLineItems);
    }
}