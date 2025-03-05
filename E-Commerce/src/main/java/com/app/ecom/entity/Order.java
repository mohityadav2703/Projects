package com.app.ecom.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    private String orderTrackingNumber;
    private Integer totalQty;
    private Double totalPrice;
    private String orderStatus;

    @CreationTimestamp
    @Column(name="created_date", updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name="updated_date", insertable = false)
    private Date updatedDate;

    @Temporal(TemporalType.DATE)
    @Column(name="delivery_date", nullable = true)
    private Date deliveryDate;

    private String paymentStatus;
    private String razorPayOrderId;
    private String razorPayPaymentId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "addr_id", nullable = false)
    private Address address;
}
