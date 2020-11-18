package k12.base.web.service;

import k12.base.data.model.Paging;

/**
 * Created by kinginblue on 2017/7/17.
 */
public interface BaseService<T extends Paging> {

    T save(T model);

    T update(T model);

    void delete(int id);

    T findById(int id);

}
