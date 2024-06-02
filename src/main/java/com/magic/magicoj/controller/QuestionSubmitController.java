package com.magic.magicoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.magic.magicoj.annotation.AuthCheck;
import com.magic.magicoj.common.BaseResponse;
import com.magic.magicoj.common.ErrorCode;
import com.magic.magicoj.common.ResultUtils;
import com.magic.magicoj.constant.UserConstant;
import com.magic.magicoj.exception.BusinessException;
import com.magic.magicoj.model.dto.question.QuestionQueryRequest;
import com.magic.magicoj.model.dto.questionsubmit.QuestionSubmitAddRequest ;
import com.magic.magicoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.magic.magicoj.model.entity.Question;
import com.magic.magicoj.model.entity.QuestionSubmit;
import com.magic.magicoj.model.entity.User;
import com.magic.magicoj.model.vo.QuestionSubmitVO;
import com.magic.magicoj.service.QuestionSubmitService;
import com.magic.magicoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录的 id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }


    /**
     * 分页获取题目提交列表（除管理员外，普通用户只能看到非答案、提交代码等公开信息）
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
                                                                         HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        //从数据库中查询原始的题目提交分类信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        //返回脱敏信息
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage,loginUser));
    }

}
