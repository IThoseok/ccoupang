package com.onlinemarket.delivery;

import com.onlinemarket.order.Order;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "delivery")
@Entity
public class Delivery {
  @Id
  @GeneratedValue
  @Column(name = "delivery_id")
  private Long id;

  @Column(nullable = false)
  private LocalDateTime deliveryDate;

  private String memo;

  @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
  private Order order;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status; // ENUM [READY(준비), COMP(배송)]
}
