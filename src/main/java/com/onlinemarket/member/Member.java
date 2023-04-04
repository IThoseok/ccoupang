package com.onlinemarket.member;

import com.onlinemarket.address.Address;
import com.onlinemarket.cart.Cart;
import com.onlinemarket.order.Order;
import com.onlinemarket.review.Review;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "member")
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @OneToMany(mappedBy = "member")
  private List<Address> address = new ArrayList<>();

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Cart> carts = new ArrayList<>();

  @OneToMany(mappedBy = "member")
  private List<Review> reviews = new ArrayList<>();
}
