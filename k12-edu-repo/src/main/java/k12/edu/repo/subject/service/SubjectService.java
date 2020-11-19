package k12.edu.repo.subject.service;

import k12.base.web.service.BaseService;
import k12.edu.repo.subject.model.SubjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService extends BaseService<SubjectModel> {
    Page<SubjectModel> findPage(SubjectModel model, Pageable pageable);
    List<SubjectModel> findList(SubjectModel model);
}
