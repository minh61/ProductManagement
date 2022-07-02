package com.ghtk.minhvt27.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;
    private Float price;
    private String sku;
    private Integer status;
    private String description;
    private Integer categoryId;
    private String code;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.price + this.sku + " " + this.status + this.description + " " + this.categoryId + " " + this.code + " " + this.createdAt;
    }
}
