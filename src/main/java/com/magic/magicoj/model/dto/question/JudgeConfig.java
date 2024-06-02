package com.magic.magicoj.model.dto.question;

/**
 * 题目配置
 */

import lombok.Data;

@Data
public class JudgeConfig {

    /**
     * 时间限制(ms)
     */
    private long timeLimit;

    /**
     * 内存限制(KB)
     */
    private long memoryLimit;
    /**
     * 堆栈限制(KB)
     */
    private long stackLimit;
}
