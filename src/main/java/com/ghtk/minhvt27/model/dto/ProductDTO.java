package com.ghtk.minhvt27.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter

public class ProductDTO implements Serializable {

    @Length(min = 1 , max = 100 , message = "Tên sản phẩm không quá 100 ký tự")
    @NotEmpty(message = "Tên sản phẩm không được để trống")
    private String name;

    @Min(value = 0,message = "Giá sản phẩm phải lớn hơn 0")
    private Float price;

    private String sku;

    private String description;

    @NotNull(message = "Thiếu mã loại sản phẩm")
    private Integer categoryId;


}
