import javax.swing.*;
import java.awt.*;

// 具体工厂类 - 创建黑色主题的UI组件
class DarkThemeFactory implements GUIFactory {
    private JPanel panel; // 添加一个面板成员变量
    @Override
    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        return button;
    }
    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new GridLayout());
        return panel;
    }

    @Override
    public JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.WHITE);
        return textField;
    }

    @Override
    public void setTheme(JPanel panel, Color color) {
        if (panel != null) {
            panel.setBackground(color); // 更新面板的背景颜色
        } else {
            panel = createPanel(); // 如果面板为空，则初始化面板
            panel.setBackground(color); // 设置背景颜色
        }
    }
    // 其他UI组件的具体实现
}