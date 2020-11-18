package k12.base.mybatis.repository;

import k12.base.data.model.Paging;

import java.util.List;

/**
 * Created by kinginblue on 2017/5/15.
 */
public interface MybatisRepository<T extends Paging> {

    List<T> findAll(T t);

    List<T> findList(T t);

    T findOne(T t);

    T findLastOne(T t);

    T findById(T t);

    List<T> findPage(T t);

    long findTotal(T t);

    int save(T t);

    int update(T t);

    int delete(T t);

}
