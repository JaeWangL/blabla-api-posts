package com.blabla.posts.api.domain.exceptions;

import com.blabla.posts.api.domain.seedwork.BusinessRule;

public class BusinessRuleBrokenException extends RuntimeException {
    public BusinessRuleBrokenException(BusinessRule rule) {
        super(rule.message());
    }
}
