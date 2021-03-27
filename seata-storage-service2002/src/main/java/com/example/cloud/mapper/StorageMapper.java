package com.example.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cloud.entity.Order;
import com.example.cloud.entity.Storage;
import org.apache.ibatis.annotations.Param;

public interface StorageMapper extends BaseMapper<Storage> {
    void updateCountByProductId(@Param("productId") Long productId, @Param("count") Integer count);
}
