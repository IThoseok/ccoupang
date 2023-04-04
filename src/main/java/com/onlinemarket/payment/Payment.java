package com.onlinemarket.payment;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "payment")
@Entity
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_id")
  private Long id;

  @Column(nullable = false)
  private LocalDateTime paymentDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentMethod PaymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentStatus PaymentStatus;
}
