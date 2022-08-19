package org.genspark.bootstrap;

import org.genspark.inventory.repository.InventoryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class MySQLIntegrationTest {

    @Autowired
    InventoryDao inventoryDAO;

    @Test
    void testMySQL() {
        long countBefore = inventoryDAO.getTheInventoryList().size();
        assertThat(countBefore).isGreaterThan(0);
    }

}
