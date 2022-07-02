package com.ghtk.minhvt27.service;

import com.ghtk.minhvt27.model.dto.ProductDTO;
import com.ghtk.minhvt27.model.entity.ProductEntity;
import com.ghtk.minhvt27.model.response.ResponseData;
import com.ghtk.minhvt27.model.response.ResponseDataPage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface ProductService {


    ResponseData create(ProductDTO newProduct);

    ResponseData update(Integer id , ProductDTO newProduct);

    ResponseData delete(Integer productId);

    ResponseDataPage getProduct(Pageable pageable);
}
