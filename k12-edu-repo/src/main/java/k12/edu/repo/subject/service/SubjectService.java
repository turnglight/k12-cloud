package k12.edu.repo.subject.service;

import k12.base.web.service.BaseService;
import k12.edu.repo.subject.model.SubjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectService extends BaseService<SubjectModel> {
    /**
     * 分页
     * @param model 查询Model
     * @param pageable 分页信息
     * @return
     */
    Page<SubjectModel> findPage(SubjectModel model, Pageable pageable);

}
