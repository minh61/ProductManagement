package com.ghtk.minhvt27.service;

import com.ghtk.minhvt27.constant.StatusConstants;
import com.ghtk.minhvt27.model.dto.ProductDTO;
import com.ghtk.minhvt27.model.dto.WarehouseDTO;
import com.ghtk.minhvt27.model.entity.ProductEntity;
import com.ghtk.minhvt27.model.entity.WarehouseEntity;
import com.ghtk.minhvt27.model.exception.NotFoundException;
import com.ghtk.minhvt27.model.response.Pagination;
import com.ghtk.minhvt27.model.response.ResponseData;
import com.ghtk.minhvt27.model.response.ResponseDataPage;
import com.ghtk.minhvt27.repository.DistrictRepository;
import com.ghtk.minhvt27.repository.ProvinceRepository;
import com.ghtk.minhvt27.repository.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl  implements WarehouseService{
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ModelMapper modelMapper;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public ResponseData create(WarehouseDTO newWarehouse) {
        ArrayList<WarehouseDTO> list = new ArrayList<>();

        WarehouseEntity warehouseEntity = modelMapper.map(newWarehouse,WarehouseEntity.class);
        warehouseEntity.setAddress(newWarehouse.getAddress() + ", " +
                                    districtRepository.findDistrictById(newWarehouse.getDistrictId()).getName() + ", " +
                                    provinceRepository.findProvinceById(newWarehouse.getProvinceId()).getName());
        warehouseEntity.setDistrictId(newWarehouse.getDistrictId());
        warehouseEntity.setProvinceId(newWarehouse.getProvinceId());
        System.out.println(warehouseEntity.toString());

        // khởi tạo status
        warehouseEntity.setStatus(StatusConstants.ACTIVE.getStatus());
        warehouseRepository.save(warehouseEntity);

        newWarehouse.setAddress(warehouseEntity.getAddress());
        list.add(newWarehouse);

        return ResponseData.builder()
                .sucess(true)
                .message("Thêm kho mới thành công")
                .data(list)
                .build();
    }

    @Override
    public ResponseData update(Integer warehouseId, WarehouseDTO newData) {

        WarehouseEntity warehouseEntity = this.getWarehouseById(warehouseId);
        if(warehouseEntity == null)
            throw new NotFoundException("Kho không tồn tại");
        if(warehouseEntity.getStatus() == StatusConstants.INACTIVE.getStatus()) {
            throw new NotFoundException("Kho đã bị xóa trước đó");
        }

        // cập nhật thông tin mới
        warehouseEntity.setName(newData.getName());
        warehouseEntity.setStatus(StatusConstants.ACTIVE.getStatus());
        warehouseEntity.setAddress(newData.getAddress() + ", " +
                districtRepository.findDistrictById(newData.getDistrictId()).getName() + ", " +
                provinceRepository.findProvinceById(newData.getProvinceId()).getName());
        warehouseEntity.setDistrictId(newData.getDistrictId());
        warehouseEntity.setProvinceId(newData.getProvinceId());


        warehouseRepository.save(warehouseEntity);
        ArrayList<WarehouseDTO> list = new ArrayList<>();
        list.add(newData);

        return ResponseData.builder()
                .sucess(true)
                .message("Cập nhật kho thành công")
                .data(list)
                .build();
    }


    @Override
    public ResponseData delete(Integer warehouseId) {
        WarehouseEntity warehouseEntity = this.getWarehouseById(warehouseId);

        if( warehouseEntity == null) {
            throw new NotFoundException("Kho không tồn tại");
        }
        if( warehouseEntity.getStatus() == StatusConstants.INACTIVE.getStatus()) {
            throw new NotFoundException("Kho đã bị xóa trước đó");
        }

        // cập nhật status
        warehouseEntity.setStatus(StatusConstants.INACTIVE.getStatus());

        warehouseRepository.save(warehouseEntity);
        ArrayList<WarehouseDTO> list = new ArrayList<>();
        WarehouseDTO warehouseDTO = modelMapper.map( warehouseEntity,WarehouseDTO.class);
        list.add(warehouseDTO);

        return ResponseData.builder()
                .sucess(true)
                .message("Xóa kho thành công")
                .data(list)
                .build();

    }


    @Override
    public ResponseDataPage getWarehouse(Pageable pageable) {
        Page<WarehouseEntity> warehouseEntities = warehouseRepository.findAllByStatus(StatusConstants.ACTIVE.getStatus(), pageable);
        List<WarehouseDTO> warehouseDTOS = warehouseEntities.getContent().stream().map(e -> modelMapper.map(e,WarehouseDTO.class)).collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .page(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .total(warehouseRepository.countWarehouseByStatus(StatusConstants.ACTIVE.getStatus()))
                .build();

        return ResponseDataPage.builder()
                .sucess(true)
                .message("Thành công")
                .data(warehouseDTOS)
                .pagination(pagination).build();

    }

    public WarehouseEntity getWarehouseById(Integer warehouseId) {
        Optional<WarehouseEntity> warehouse = warehouseRepository.findById(warehouseId);
        if(warehouse.isPresent()) {
            WarehouseEntity WarehouseEntity = warehouse.get();
            return WarehouseEntity;
        }
        return null;
    }
}
