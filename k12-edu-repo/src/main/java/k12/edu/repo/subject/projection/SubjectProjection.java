package k12.edu.repo.subject.projection;

import io.swagger.annotations.ApiModelProperty;

public interface SubjectProjection {

    @ApiModelProperty(value = "ID", required = true)
    Integer getId();

    @ApiModelProperty(value = "科目名称", required = true)
    String getName();

    @ApiModelProperty(value = "科目阶级", required = true)
    Integer getLevel();
}
