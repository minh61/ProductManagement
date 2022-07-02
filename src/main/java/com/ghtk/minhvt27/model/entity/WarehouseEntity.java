package com.ghtk.minhvt27.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "warehouse")
@Getter
@Setter

public class WarehouseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer warehouseId;
    private String name;
    private String address;
    private Integer status;
    private Integer districtId;
    private Integer provinceId;



    @Override
    public String toString() {
        return this.warehouseId + " " + this.name + " " + this.address + " " + this.status + " " + this.districtId + " " + this.provinceId;
    }
}
