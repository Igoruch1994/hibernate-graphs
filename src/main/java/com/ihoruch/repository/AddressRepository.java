package com.ihoruch.repository;

import com.ihoruch.model.Address;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long>,
        CommonRepository<Address>,
        JpaSpecificationExecutor<Address> {

}
