package com.ronghuiwl.demotwo.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.ronghuiwl.demotwo.annotation.Sensitive;
import com.ronghuiwl.demotwo.enums.SensitiveType;

import java.io.IOException;

public class SensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private final int left;
    private final int right;
    private final boolean shouldDesensitize; // 新增标志位

    // 默认构造函数（无脱敏）
    public SensitiveSerializer() {
        this(0, 0, false);
    }

    // 全参构造函数
    public SensitiveSerializer(int left, int right, boolean shouldDesensitize) {
        this.left = Math.max(0, left);
        this.right = Math.max(0, right);
        // 设置是否脱敏
        this.shouldDesensitize = shouldDesensitize;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        // 关键判断：无注解时直接返回原始值
        if (!shouldDesensitize) {
            gen.writeString(value);
            return;
        }

        // 执行脱敏逻辑
        gen.writeString(desensitize(value));
    }

    private String desensitize(String value) {
        int totalVisible = left + right;
        if (totalVisible == 0 || value.length() < totalVisible) {
            // 全掩码
            return "******";
        }

        StringBuilder sb = new StringBuilder(value.length());
        sb.append(value, 0, left);
        for (int i = 0; i < value.length() - totalVisible; i++) {
            sb.append('*');
        }
        sb.append(value, value.length() - right, value.length());
        return sb.toString();
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property == null) {
            return this; // 处理空属性
        }

        // 获取字段上的 @Sensitive 注解
        Sensitive ann = property.getAnnotation(Sensitive.class);
        if (ann == null) {
            // 无注解：返回不脱敏的序列化器
            return new SensitiveSerializer(0, 0, false);
        }

        // 解析注解参数
        int left = ann.type() != SensitiveType.DEFAULT
                ? ann.type().getLeft()
                : ann.left();
        int right = ann.type() != SensitiveType.DEFAULT
                ? ann.type().getRight()
                : ann.right();

        return new SensitiveSerializer(left, right, true);
    }
}