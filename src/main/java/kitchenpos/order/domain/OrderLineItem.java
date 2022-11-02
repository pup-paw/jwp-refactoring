package kitchenpos.order.domain;

import java.math.BigDecimal;

public class OrderLineItem {

    private final Long seq;
    private final Long orderId;
    private final Long menuId;
    private final BigDecimal menuPrice;
    private final String menuName;
    private final long quantity;

    public OrderLineItem(final Long orderId, final OrderLineItem orderLineItem) {
        this(null,
                orderId,
                orderLineItem.menuId,
                orderLineItem.getMenuName(),
                orderLineItem.getMenuPrice(),
                orderLineItem.quantity);
    }

    public OrderLineItem(final Long menuId, final long quantity, final BigDecimal menuPrice, final String menuName) {
        this(null, null, menuId, menuName, menuPrice, quantity);
    }

    public OrderLineItem(final Long seq, final Long orderId, final Long menuId, final String menuName,
                         final BigDecimal menuPrice, final long quantity) {
        this.seq = seq;
        this.orderId = orderId;
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.quantity = quantity;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public BigDecimal getMenuPrice() {
        return menuPrice;
    }

    public long getQuantity() {
        return quantity;
    }
}
