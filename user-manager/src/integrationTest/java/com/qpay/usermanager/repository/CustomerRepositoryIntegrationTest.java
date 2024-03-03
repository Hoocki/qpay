package com.qpay.usermanager.repository;

import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = "test")
class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void should_returnTrue_when_customerExistsWithGivenEmail() {
        // given
        var email = "admin@gmail.com";
        testEntityManager.persistAndFlush(
                CustomerEntity.builder()
                        .name("Roman")
                        .email("admin@gmail.com")
                        .password("password")
                        .build()
        );

        // when
        var result = customerRepository.existsByEmail(email);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void should_returnFalse_when_customerDoesNotExistWithGivenEmail() {
        // given
        var email = "qwerty@gmail.com";

        // when
        var result = customerRepository.existsByEmail(email);

        // then
        assertThat(result).isFalse();
    }
}