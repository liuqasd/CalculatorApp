import javax.swing.*;
import java.awt.*;

// 抽象工厂模式接口
public interface GUIFactory {
    JButton createButton(String text);
    JPanel createPanel();
    JTextField createTextField(int columns);
    void setTheme(JPanel panel, Color color);
    // 其他UI组件的抽象方法
}