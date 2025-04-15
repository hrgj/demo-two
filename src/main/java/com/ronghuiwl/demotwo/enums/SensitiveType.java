package com.ronghuiwl.demotwo.enums;

public enum SensitiveType {
    PHONE(3, 4),      // 手机号：保留前3位和后4位，如 138****5678
    ID_CARD(3, 4),    // 身份证：保留前3位和后4位，如 320***********1234
    DEFAULT(0, 0);    // 自定义规则（通过 left/right 指定）

    private final int left;
    private final int right;

    SensitiveType(int left, int right) {
        this.left = left;
        this.right = right;
    }

    // Getter 方法
    public int getLeft() { return left; }
    public int getRight() { return right; }
}
