package com.yj2025.sample.factory_method;

import java.util.Scanner;

/**
 * Demo class. Everything comes together here.
 */
public class Demo {
    private static Dialog dialog;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入类型(win、html中的一种): ");
        String dialogType = scanner.nextLine();
        configure(dialogType);
        runBusinessLogic();
    }

    /**
     * 根据输入的类型，选择使用哪个创建器
     */
    static void configure(String dialogType) {
        if (dialogType != null && dialogType.equals("win")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    /**
     * 所有客户端代码都应该通过抽象接口与工厂和产品一起工作。
     * 这样它就不会关心它与哪个工厂合作以及它返回什么样的产品。
     */
    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}