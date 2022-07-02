package com.ghtk.minhvt27.service;

import com.ghtk.minhvt27.constant.StatusConstants;
import com.ghtk.minhvt27.model.dto.ProductDTO;
import com.ghtk.minhvt27.model.entity.ProductEntity;

import com.ghtk.minhvt27.model.exception.NotFoundException;
import com.ghtk.minhvt27.model.response.Pagination;
import com.ghtk.minhvt27.model.response.ResponseData;
import com.ghtk.minhvt27.model.response.ResponseDataPage;
import com.ghtk.minhvt27.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseData create(ProductDTO newProduct) {
        ArrayList<ProductDTO> list = new ArrayList<>();
        list.add(newProduct);

        ProductEntity productEntity = modelMapper.map(newProduct,ProductEntity.class);
        productEntity.setId(null);

        // khởi tạo status, thời gian create , modified và code
        productEntity.setStatus(StatusConstants.ACTIVE.getStatus());
        LocalDateTime date = LocalDateTime.now();
        date.truncatedTo(ChronoUnit.SECONDS);
        productEntity.setModifiedAt(date);
        productEntity.setCreatedAt(date);
        productEntity.setCode(productEntity.getCategoryId() + "." + productEntity.getSku() + "." + productEntity.getCreatedAt());

        productRepository.save(productEntity);

        return ResponseData.builder()
                .sucess(true)
                .message("Thêm sản phẩm mới thành công")
                .data(list)
                .build();
    }

    @Override
    public ResponseData update(Integer productId ,ProductDTO newData) {

        ProductEntity productEntity = this.getProductById(productId);
        if(productEntity == null)
            throw new NotFoundException("Sản phẩm không tồn tại");
        if(productEntity.getStatus() == StatusConstants.INACTIVE.getStatus()) {
            throw new NotFoundException("Sản phẩm đã bị xóa trước đó");
        }

        // cập nhật thông tin mới
        productEntity.setName(newData.getName());
        productEntity.setPrice(newData.getPrice());
        productEntity.setSku(newData.getSku());
        productEntity.setStatus(StatusConstants.ACTIVE.getStatus());
        productEntity.setDescription(newData.getDescription());
        productEntity.setCategoryId(newData.getCategoryId());

        // cập nhật thời gian modified
        LocalDateTime date = LocalDateTime.now();
        date.truncatedTo(ChronoUnit.SECONDS);
        productEntity.setModifiedAt(date);

        productRepository.save(productEntity);
        ArrayList<ProductDTO> list = new ArrayList<>();
        list.add(newData);

        return ResponseData.builder()
                .sucess(true)
                .message("Cập nhật sản phẩm thành công")
                .data(list)
                .build();
    }


    @Override
    public ResponseData delete(Integer productId) {
        ProductEntity product = this.getProductById(productId);

        if(product == null) {
            throw new NotFoundException("Sản phẩm không tồn tại");
        }
        if(product.getStatus() == StatusConstants.INACTIVE.getStatus()) {
            throw new NotFoundException("Sản phẩm đã bị xóa trước đó");
        }

        ProductDTO productDTO = modelMapper.map(product,ProductDTO.class);

        ArrayList<ProductDTO> list = new ArrayList<>();
        // cập nhật status và thời gian modified
        product.setStatus(StatusConstants.INACTIVE.getStatus());
        LocalDateTime date = LocalDateTime.now();
        date.truncatedTo(ChronoUnit.SECONDS);
        product.setModifiedAt(date);

        productRepository.save(product);
        list.add(productDTO);

        return ResponseData.builder()
                            .sucess(true)
                            .message("Xóa sản phẩm thành công")
                            .data(list)
                            .build();

    }


    @Override
    public ResponseDataPage getProduct(Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAllByStatus(StatusConstants.ACTIVE.getStatus(), pageable);
        List<ProductDTO> productDTOS = productEntities.getContent().stream().map(e -> modelMapper.map(e,ProductDTO.class)).collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .page(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .total(productRepository.countProductByStatus(StatusConstants.ACTIVE.getStatus()))
                .build();

        return ResponseDataPage.builder()
                .sucess(true)
                .message("Thanh cong")
                .data(productDTOS)
                .pagination(pagination).build();

    }

    public ProductEntity getProductById(Integer productId) {
        Optional<ProductEntity> product = productRepository.findById(productId);
        if(product.isPresent()) {
            ProductEntity productEntity = product.get();
            return productEntity;
        }
        return null;
    }
}
