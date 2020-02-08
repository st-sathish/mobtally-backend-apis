package com.mobtally.customer.repo;

import com.mobtally.domain.JpaCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepo extends JpaRepository<JpaCustomer, Long> {
}
