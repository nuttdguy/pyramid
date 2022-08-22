package com.genspark.spring.item.service;

import com.genspark.spring.item.domain.Detail;
import com.genspark.spring.item.repository.IItemDetailRepository;
import com.genspark.spring.item.utils.AppContextUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class ItemItemDetailService implements IItemDetailService {

    private final IItemDetailRepository IItemDetailRepository;

    @Autowired
    public ItemItemDetailService(IItemDetailRepository IItemDetailRepository) {
        this.IItemDetailRepository = IItemDetailRepository;
    }

    // get all the items
    public List<Detail> getItemDetails() {
        return IItemDetailRepository.findAll();
    }

    // get one item
    public Detail getItemDetailBy(Long theId) {
        return IItemDetailRepository.findById(theId)
                .orElse(AppContextUtil.getAppContext().getBean(Detail.class));
    }

    // save the item
    @Transactional
    public Detail addItemDetail(Detail theDetail) {
        return IItemDetailRepository.save(theDetail);
    }

    // update an item
    @Transactional(rollbackOn = {SQLException.class} )
    public Detail updateItemDetail(Detail theDetail) {
        return IItemDetailRepository.save(theDetail);
    }

    // delete an item
    public Long deleteItemDetailBy(Long theId) {
        if (IItemDetailRepository.existsById(theId)) {
            IItemDetailRepository.deleteById(theId);
            return 1L;
        }
        return 0L;
    }

}
