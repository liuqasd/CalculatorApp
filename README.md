# CalculatorApp
Java实现计算器，并用抽象工厂方法更改外观（实现后）

在进行使用抽象工厂方法改写计算器程序时，要求更改计算器外观，我先创建了一个接口GUIFactory，在其中定义了包括createButton、createPanel、createTextField和setTheme的抽象方法，用于创建按钮、创建面板、创建文本框和更改主题。

接着使用LightThemeGUIFactory和DarkThemeGUIFantory两个工厂类来实现不同的抽象方法，以黑色主题工厂类为例，我新创建了一个私有变量panel，用于更改主题时使用；在实现创建按钮方法时，将按钮背景设置为DARK_GRAY，返回按钮类型；在实现创建面板方法时，我将面板背景设置为DARK_GRAY,并且设置面板布局为Grid布局，返回面板类型；在实现创建文本框方法时，我将文本框背景设置为DARK_GRAY，并且设置字体颜色为白色，返回文本框；在实现设置主题方法时，我传入panel和color两个参数，并且在更改颜色时判断面板是否存在。

最后，在具体计算器类中，我使用黑色主题创建接口，再使用该接口创建计算器应用，其中使用switch语句识别运算符，使用BigDecimal进行精确计算，成功实现完整的计算器功能，并且深刻贯彻抽象工厂方法。
