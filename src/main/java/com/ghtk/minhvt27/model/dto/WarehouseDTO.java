package com.ghtk.minhvt27.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter

public class WarehouseDTO implements Serializable {

    @Length(min = 1 , max = 100 , message = "Tên kho không quá 100 ký tự")
    @NotEmpty(message = "Tên kho không được để trống")
    private String name;
    @NotNull(message = "Thiếu địa chỉ kho")
    private String address;
    @NotNull(message = "Thiếu mã quận")
    private Integer districtId;
    @NotNull(message = "Thiếu mã tỉnh")
    private Integer provinceId;

}
