package com.genspark.spring.item.repository;

import com.genspark.spring.item.domain.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemDetailRepository extends JpaRepository<Detail, Long> {

}


