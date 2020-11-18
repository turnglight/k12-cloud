package k12.edu.repo.subject.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import k12.base.web.response.CloudApiResponse;
import k12.edu.repo.subject.model.SubjectModel;
import k12.edu.repo.subject.projection.SubjectProjection;
import k12.edu.repo.subject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "科目")
@RequestMapping("repo:subject")
@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(httpMethod = "GET", value = "获取所有页", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页.page", paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "分页.size", paramType = "query", dataType = "int", defaultValue = "20")
    })
    @GetMapping(value = "/findPage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    CloudApiResponse findPage(@ApiIgnore Pageable pageable){
        CloudApiResponse<Page<SubjectProjection>> apiResponse = new CloudApiResponse<>();
        Page<SubjectModel> page = subjectService.findPage(new SubjectModel(), pageable);
        apiResponse.setData(page, SubjectProjection.class);
        return apiResponse;
    }

}
