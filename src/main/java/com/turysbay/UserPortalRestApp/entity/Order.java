package com.turysbay.UserPortalRestApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity{
    @NotBlank(message = "Quantity is required")
    private Integer number;

    @NotBlank(message = "Description number is required")
    private String description;

    @NotBlank(message = "Address number is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @JoinColumn(name = "user_id")
    private Long createdBy;
}
