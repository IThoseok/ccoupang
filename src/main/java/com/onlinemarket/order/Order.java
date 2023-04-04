package com.onlinemarket.order;

import com.onlinemarket.address.Address;
import com.onlinemarket.delivery.Delivery;
import com.onlinemarket.member.Member;
import com.onlinemarket.orderproduct.OrderProduct;
import com.onlinemarket.payment.Payment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Table(name = "orders")
@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @Column(nullable = false)
  private LocalDateTime orderDate;

  @OneToMany(mappedBy = "order")
  private List<Address> address = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus; // ENUM [ORDER(주문), CANCEL(취소)]

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payment_id", nullable = false)
  private Payment payment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "delivery_id")
  private Delivery delivery; // 배송정보

  @OneToMany(mappedBy = "order")
  private List<OrderProduct> orderProducts = new ArrayList<>();
}
