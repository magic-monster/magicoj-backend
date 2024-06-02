package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Question;
import generator.service.QuestionService;
import generator.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author magic
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-05-29 06:59:39
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




