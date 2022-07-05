package com.sibs.business.interfaces;


import java.util.List;

/**
 * @author geraldobarrosjr
 */
public interface BaseInterface<T, K> {


    T create( K request);

    T update(Long id, K request);

    void delete(Long id);

    K toModel(T request);

    T toEntity(K request);

    List<K> toModelList(List<T> request);
}
