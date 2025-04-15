package com.ronghuiwl.demotwo.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ronghuiwl.demotwo.enums.SensitiveType;
import com.ronghuiwl.demotwo.serializers.SensitiveSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerializer.class)
public @interface Sensitive {
    /**
     * 预定义脱敏类型（优先级高于 left/right）
     */
    SensitiveType type() default SensitiveType.DEFAULT;

    /**
     * 左侧保留位数（当 type=DEFAULT 时生效）
     */
    int left() default 0;

    /**
     * 右侧保留位数（当 type=DEFAULT 时生效）
     */
    int right() default 0;
}
