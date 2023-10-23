import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorApp {
    private final JPanel panel;
    private final JTextField textField;
    private BigDecimal currentValue = BigDecimal.ZERO;
    private String selectedOperator = "=";
    public JPanel getPanel() {
        return panel;
    }
    public CalculatorApp(GUIFactory factory){
        panel = factory.createPanel(); // 创建面板
        panel.setLayout(new GridBagLayout()); // 设置布局管理器

        textField = factory.createTextField(4); // 设置文本字段在顶部且宽度为4个网格
        textField.setText("0"); // 设置文本框的初始值
        textField.setBorder(null); // 隐藏文本框的边框
        textField.setHorizontalAlignment(JTextField.RIGHT); // 设置文本框中的文本右对齐
        textField.setEditable(false); // 设置文本框不可编辑
        // 设置文本框的字体大小
        Font font = new Font("Arial", Font.PLAIN, 40);
        textField.setFont(font);
        // 文本框设置填充和组件位置
        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 0;
        textFieldConstraints.gridwidth = 4;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstraints.insets = new Insets(0, 10, 0, 10); // 控制左右填充
        textFieldConstraints.anchor = GridBagConstraints.LINE_END; // 控制文本框中的文本右对齐
        panel.add(textField, textFieldConstraints);

        // 设置按钮的填充和组件位置
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 0;
        buttonConstraints.gridwidth = 4;
        buttonConstraints.fill = GridBagConstraints.HORIZONTAL;

        // 将按钮添加到面板中
        String[] buttonLabels = {
                "AC", "Change Theme", "/",
                "7",     "8",    "9", "*",
                "4",     "5",    "6", "-",
                "1",     "2",    "3", "+",
                "0",             ".", "="
        };
        int gridx = 0; // 从第一列开始
        int gridy = 1; // 从第二行开始
        for (String label : buttonLabels) {
            JButton button = factory.createButton(label);
            buttonConstraints.gridx = gridx;
            buttonConstraints.gridy = gridy;
            buttonConstraints.insets = new Insets(10, 10, 0, 10); // 添加一些填充
            buttonConstraints.ipadx = 34; // 设置水平方向的内部填充
            buttonConstraints.ipady = 48; // 设置垂直方向的内部填充
            if (label.equals("0") || label.equals("Change Theme")) {
                buttonConstraints.gridwidth = 2; // 设置占据两列
                gridx += 2; // 跳过下一个列
            } else {
                buttonConstraints.gridwidth = 1; // 设置默认占据一列
                gridx++; // 移到下一个列
            }
            if (gridx > 3) { // 如果超过四列，移至下一行
                gridx = 0;
                gridy++;
            }
            panel.add(button, buttonConstraints);

            // 添加对应的按钮事件
            switch (label) {
                case "AC":
                    button.addActionListener(e -> {
                        currentValue = BigDecimal.ZERO;
                        textField.setText("0");
                    });
                    break;
                case "Change Theme":
                    button.addActionListener(e -> {
                        Color currentColor = panel.getBackground();
                        Color newColor = (currentColor == Color.LIGHT_GRAY) ? Color.DARK_GRAY : Color.LIGHT_GRAY;
                        factory.setTheme(panel, newColor); // 设置面板背景颜色
                        panel.revalidate(); // 重新验证面板
                        panel.repaint(); // 重绘面板
                    });
                    break;
                case "/":
                    button.addActionListener(e -> {
                        if (!textField.getText().isEmpty()) {
                            currentValue = new BigDecimal(textField.getText());
                        }
                        textField.setText("");
                        selectedOperator = "/";
                    });
                    break;
                case "*":
                    button.addActionListener(e -> {
                        if (!textField.getText().isEmpty()) {
                            // 更新当前值
                            currentValue = new BigDecimal(textField.getText());
                            // 更新运算符
                            selectedOperator = "*";
                            textField.setText("");
                        }
                    });
                    break;
                case "-":
                    button.addActionListener(e -> {
                        if (!textField.getText().isEmpty()) {
                            currentValue = new BigDecimal(textField.getText());
                        }
                        textField.setText("");
                        selectedOperator = "-";
                    });
                    break;
                case "+":
                    button.addActionListener(e -> {
                        if (!textField.getText().isEmpty()) {
                            currentValue = new BigDecimal(textField.getText());
                        }
                        textField.setText("");
                        selectedOperator = "+";
                    });
                    break;
                case "=":
                    button.addActionListener(e -> {
                        if (!textField.getText().isEmpty()) {
                            BigDecimal operand = new BigDecimal(textField.getText());
                            switch (selectedOperator) {
                                case "+":
                                    currentValue = currentValue.add(operand);
                                    break;
                                case "-":
                                    currentValue = currentValue.subtract(operand);
                                    break;
                                case "*":
                                    currentValue = currentValue.multiply(operand);
                                    break;
                                case "/":
                                    if (operand.equals(BigDecimal.ZERO)) {
                                        textField.setText("Error");
                                        return;
                                    }
                                    currentValue = currentValue.divide(operand, 2, RoundingMode.HALF_UP);
                                    break;
                                default:
                                    currentValue = operand;
                                    break;
                            }
                        }
                        textField.setText(String.valueOf(currentValue));
                        selectedOperator = "=";
                    });
                    break;
                case ".":
                    button.addActionListener(e -> {
                        if (!textField.getText().contains(".")) {
                            textField.setText(textField.getText() + ".");
                        }
                    });
                    break;
                default:
                    int digit = Integer.parseInt(label);
                    button.addActionListener(e -> {
                        if (textField.getText().equals("0")) {
                            textField.setText(String.valueOf(digit));
                        } else {
                            textField.setText(textField.getText() + digit);
                        }
                    });
                    break;
            }
        }
    }

    public static void main(String[] args) {
        GUIFactory darkFactory = new DarkThemeFactory(); // 默认使用黑色主题
        CalculatorApp darkCalculator = new CalculatorApp(darkFactory);

        // 设置窗口大小
        int width = 400;
        int height = (int) (width * 1.618);
        // 使用darkCalculator获取计算器外观，添加到窗口中
        JFrame frame = new JFrame("Calculator App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口位置为屏幕合适位置
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        frame.setSize(width, height);
        frame.getContentPane().add(darkCalculator.getPanel()); // 添加面板
        frame.setVisible(true);
    }
}