package k12.edu.repo.subject.repository;

import k12.base.mybatis.lang.SimpleDeleteLangDriver;
import k12.base.mybatis.lang.SimpleInsertLangDriver;
import k12.base.mybatis.lang.SimpleSelectLangDriver;
import k12.base.mybatis.lang.SimpleUpdateLangDriver;
import k12.base.mybatis.repository.MybatisRepository;
import k12.edu.repo.subject.model.SubjectModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubjectRepository extends MybatisRepository<SubjectModel> {

    @Override
    @Lang(SimpleInsertLangDriver.class)
    @Insert("[#{model}]")
    int save(SubjectModel model);

    @Override
    @Lang(SimpleUpdateLangDriver.class)
    @Update("[#{model}]")
    int update(SubjectModel model);

    @Override
    @Lang(SimpleDeleteLangDriver.class)
    @Delete("[#{model}]")
    int delete(SubjectModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}]")
    SubjectModel findById(SubjectModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("[#{model}] ORDER BY id ASC LIMIT #{start}, #{size}")
    List<SubjectModel> findPage(SubjectModel model);

    @Override
    @Lang(SimpleSelectLangDriver.class)
    @Select("SELECT COUNT(1) [#{model}]")
    long findTotal(SubjectModel model);

}
