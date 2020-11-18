package k12.edu.repo.subject.model;


import k12.base.data.model.Paging;
import k12.base.mybatis.annotation.Column;
import k12.base.mybatis.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "k12_tk_subject")
public class SubjectModel extends Paging {

    @Column(id = true)
    private Integer id;

    @Column
    private String name;

    @Column
    private String href;

    @Column
    private Integer level;
}
