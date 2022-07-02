package com.ghtk.minhvt27.service;

import com.ghtk.minhvt27.model.dto.WarehouseDTO;
import com.ghtk.minhvt27.model.entity.WarehouseEntity;
import com.ghtk.minhvt27.model.response.ResponseData;
import com.ghtk.minhvt27.model.response.ResponseDataPage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface WarehouseService {

    ResponseData create(WarehouseDTO newWarehouse);

    ResponseData update(Integer warehouseId, WarehouseDTO newData);

    ResponseData delete(Integer warehouseId);

    ResponseDataPage getWarehouse(Pageable pageable);
}
