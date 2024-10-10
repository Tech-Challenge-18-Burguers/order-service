package br.com.eighteenburguers.order.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.order.infra.repository.entity.OrderEntity;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}
