package com.genspark.spring.item.service;

import com.genspark.spring.item.domain.Detail;

import java.util.List;


public interface IItemDetailService {

    List<Detail> getItemDetails();
    Detail getItemDetailBy(Long theId);
    Detail addItemDetail(Detail theDetail);
    Detail updateItemDetail(Detail theDetail);
    Long deleteItemDetailBy(Long theId);

}
