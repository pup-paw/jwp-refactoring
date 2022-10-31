package kitchenpos.dao;

import java.util.List;
import java.util.Optional;
import kitchenpos.domain.Order;

public interface OrderDao {

    OrderDto save(Order entity);

    Optional<OrderDto> findById(Long id);

    List<OrderDto> findAll();

    Optional<OrderDto> findByOrderTableId(Long id);

    List<OrderDto> findAllByOrderTableIdIn(List<Long> tableGroupIds);
}
