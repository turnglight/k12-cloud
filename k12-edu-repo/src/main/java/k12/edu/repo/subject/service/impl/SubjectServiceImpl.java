package k12.edu.repo.subject.service.impl;

import k12.edu.repo.subject.model.SubjectModel;
import k12.edu.repo.subject.repository.SubjectRepository;
import k12.edu.repo.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Page<SubjectModel> findPage(SubjectModel model, Pageable pageable) {
        model.pageable(pageable);
        List<SubjectModel> modelList = subjectRepository.findPage(model);
        long total = subjectRepository.findTotal(model);
        return new PageImpl<>(modelList, pageable, total);
    }

    @Override
    public List<SubjectModel> findList(SubjectModel model) {
        return subjectRepository.findList(model);
    }

    @Override
    public SubjectModel save(SubjectModel model) {
        subjectRepository.save(model);
        return this.findById(model.getId());
    }

    @Override
    public SubjectModel update(SubjectModel model) {
        subjectRepository.update(model);
        return this.findById(model.getId());
    }

    @Override
    public void delete(int id) {
        SubjectModel subjectModel = this.findById(id);
        subjectRepository.delete(subjectModel);
    }

    @Override
    public SubjectModel findById(int id) {
        SubjectModel subjectModel =new SubjectModel();
        subjectModel.setId(id);
        return subjectRepository.findById(subjectModel);
    }
}
