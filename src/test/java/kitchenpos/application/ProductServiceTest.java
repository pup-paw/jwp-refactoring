package kitchenpos.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.util.List;
import kitchenpos.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @DisplayName("상품을 등록한다.")
    @Test
    void create() {
        final String name = "마이쮸";
        final BigDecimal price = BigDecimal.valueOf(800);

        final Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        final Product savedProduct = productService.create(product);

        assertAll(
                () -> assertThat(savedProduct.getId()).isNotNull(),
                () -> assertThat(savedProduct.getName()).isEqualTo(name),
                () -> assertThat(savedProduct.getPrice()).isEqualByComparingTo(price)
        );
    }

    @DisplayName("상품을 등록한다. - 가격이 null이면 예외를 반환한다.")
    @Test
    void create_exception_priceIsNull() {
        final Product product = new Product();
        product.setName("마이쮸");

        assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품을 등록한다. - 가격이 0보다 작으면 예외를 반환한다.")
    @Test
    void create_exception_priceIsLessThanZero() {
        final Product product = new Product();
        product.setName("마이쮸");
        product.setPrice(BigDecimal.valueOf(-1));

        assertThatThrownBy(() -> productService.create(product))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품 목록을 조회한다.")
    @Test
    void list() {
        List<Product> products = productService.list();

        assertThat(products).hasSize(6);
    }
}
