package kitchenpos.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @DisplayName("가격이 null이면 예외를 반환한다.")
    @Test
    void create_exception_priceIsNull() {
        assertThatThrownBy(() -> new Product("마이쮸", null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가격이 0보다 작으면 예외를 반환한다.")
    @Test
    void create_exception_priceIsLessThanZero() {
        assertThatThrownBy(() -> new Product("마이쮸", BigDecimal.valueOf(-1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("개수가 주어지면 총 가격을 반환한다.")
    @Test
    void getTotalPrice() {
        final Product product = new Product("마이쮸", BigDecimal.valueOf(800));

        final BigDecimal totalPrice = product.getTotalPrice(5);

        assertThat(totalPrice).isEqualByComparingTo(BigDecimal.valueOf(4000));
    }
}
