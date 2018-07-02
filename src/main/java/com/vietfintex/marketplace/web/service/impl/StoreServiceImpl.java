package com.vietfintex.marketplace.web.service.impl;

import com.vietfintex.marketplace.persistence.model.GroupClub;
import com.vietfintex.marketplace.persistence.model.Store;
import com.vietfintex.marketplace.persistence.repo.GroupClubRepo;
import com.vietfintex.marketplace.persistence.repo.StoreRepo;
import com.vietfintex.marketplace.util.BaseMapper;
import com.vietfintex.marketplace.web.dto.GroupClubDTO;
import com.vietfintex.marketplace.web.dto.StoreDTO;
import com.vietfintex.marketplace.web.service.GroupClubService;
import com.vietfintex.marketplace.web.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreServiceImpl  extends AbstractService<Store, StoreDTO> implements StoreService {
    private static final BaseMapper<Store, StoreDTO> mapper = new BaseMapper<>(Store.class,
            StoreDTO.class);
    @Autowired
    private StoreRepo repo;

    @Override
    public List<StoreDTO> getStoreList(Long storeId, Long ownerId, String storeName, String address, Integer page) {
        return getMapper().toDtoBean(repo.getStoreList(storeId,ownerId,storeName,address,page));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StoreDTO insertOrUpdateStore(StoreDTO storeDTO) {
        return getMapper().toDtoBean(repo.save(getMapper().toPersistenceBean(storeDTO)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStore(StoreDTO storeDTO) {
        repo.delete(getMapper().toPersistenceBean(storeDTO));
    }

    @Override
    protected PagingAndSortingRepository<Store, Long> getDao() {
        return repo;
    }

    @Override
    protected BaseMapper<Store, StoreDTO> getMapper() {
        return mapper;
    }
}
