package com.biz.gbck.exceptions.evaluation;

import com.biz.gbck.exceptions.ExceptionType;

/**
 * @author yangzichun
 * @date 2017/2/21
 */
public interface EvaluationException {
    enum Evaluation implements ExceptionType {
        EVALUATION_NOT_FOUND(2501, "找不到评价id对应的评价"),;
        private int code;
        private String description;

        Evaluation(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}
