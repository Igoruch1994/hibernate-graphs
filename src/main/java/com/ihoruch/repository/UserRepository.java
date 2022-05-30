package com.ihoruch.repository;

import com.ihoruch.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        CommonRepository<User>,
        JpaSpecificationExecutor<User> {

}
