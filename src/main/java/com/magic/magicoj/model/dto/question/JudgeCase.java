package com.magic.magicoj.model.dto.question;

/**
 * 题目用例
 */

import lombok.Data;

@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
