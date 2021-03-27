package com.example.cloud.service;

import com.example.cloud.entity.Storage;

public interface StorageService {

    int save(Storage storage);

    void update(Storage storage);

    Storage findByid(Long id);

    void decrease(Long productId, Integer count);


    void createStorage(Storage storage);
}
