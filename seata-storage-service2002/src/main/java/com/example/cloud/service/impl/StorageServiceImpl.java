package com.example.cloud.service.impl;

import com.example.cloud.entity.Storage;
import com.example.cloud.mapper.StorageMapper;
import com.example.cloud.service.StorageService;
import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

@Slf4j
@Service("storageService")
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;


    @Override
    public int save(Storage storage) {
        int insert = storageMapper.insert(storage);
        return insert;
    }

    @Override
    public void update(Storage storage) {
        storageMapper.updateById(storage);
    }

    @Override
    public Storage findByid(Long id) {

        Storage storage = storageMapper.selectById(id);
        return storage;
    }

    @Override
    public void decrease(Long productId, Integer count) {
        storageMapper.updateCountByProductId(productId, count);
    }


    @Override
    public void createStorage(Storage storage) {


    }
}
