package com.magic.magicoj.judge;

import com.magic.magicoj.judge.strategy.DefaultJudgeStrategy;
import com.magic.magicoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.magic.magicoj.judge.strategy.JudgeContext;
import com.magic.magicoj.judge.strategy.JudgeStrategy;
import com.magic.magicoj.judge.codesandbox.model.JudgeInfo;
import com.magic.magicoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
