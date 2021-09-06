package com.yj2025.sample.factory_method;

/**
 * 基础工厂类，所谓工厂只是这个类的一种角色，它应该有一些需要创建不同产品的核心业务逻辑。
 */
public abstract class Dialog {

    public void renderWindow() {
        // ... other code ...

        Button okButton = createButton();
        okButton.render();
    }

    /**
     * 子类将覆盖此方法用来创建特定的按钮对象。
     */
    public abstract Button createButton();
}