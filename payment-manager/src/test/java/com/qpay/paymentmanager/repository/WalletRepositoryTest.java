package com.qpay.paymentmanager.repository;

import com.qpay.paymentmanager.model.entity.WalletEntity;
import org.junit.jupiter.api.Test;
import com.qpay.libs.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "test")
class WalletRepositoryTest {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void should_returnWalletsByUserIdAndUsertype() {
        // given
        var userId = 1L;
        var userType = UserType.CUSTOMER;
        var expectedWallet = testEntityManager.persistAndFlush(
                WalletEntity.builder()
                        .balance(BigDecimal.valueOf(100))
                        .name("wallet1")
                        .userId(userId)
                        .userType(userType)
                        .build()
        );

        // when
        var result = walletRepository.findWalletEntityByUserIdAndUserType(userId, userType);

        // then
        assertThat(result).isEqualTo(expectedWallet);
    }
}