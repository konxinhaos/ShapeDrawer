// 导⼊必要的Java Swing和AWT库
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// ShapeDrawerGUI类继承⾃JFrame，⽤于创建图形⽤户界⾯
public class ShapeDrawer extends JFrame {
    // 定义⼀个DrawArea对象，⽤作绘图的主要区域
    private DrawArea drawArea = new DrawArea();
    // ShapeDrawerGUI的构造函数
    public ShapeDrawer() {
        this.setSize(400, 400); // 设置窗⼝的初始⼤⼩
        // 设置默认关闭操作，退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("图形绘制器"); // 设置窗⼝标题
        // 将绘图区域添加到窗⼝的中央
        this.add(drawArea, BorderLayout.CENTER);
        // 创建⼀个下拉列表（JComboBox），⽤于选择要绘制的图形类型
        JComboBox<String> shapeSelector = new JComboBox<>(new String[]
                {"正⽅形", "⻓⽅形", "圆形", "三⻆形"});
        // 将下拉列表添加到窗⼝的北部（上⽅）
        this.add(shapeSelector, BorderLayout.NORTH);
        // 为下拉列表添加事件监听器，以便在⽤户选择不同选项时做出响应
        shapeSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String shapeName = (String) cb.getSelectedItem();
                String input = JOptionPane.showInputDialog("请输⼊" +
                        shapeName + "的尺⼨:");
                int size;
                size = Integer.parseInt(input); // 将输⼊转换为整数
                drawArea.setShape(shapeName, size); // 更新图形和尺⼨
            }
        });
    }
    // 主⽅法，程序的⼊⼝点
    public static void main(String[] args) {
        // 使⽤SwingUtilities.invokeLater
        // 来确保GUI的创建和更新在事件派发线程上执⾏
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建ShapeDrawer对象，并使窗⼝可⻅
                new ShapeDrawer().setVisible(true);}
        });
    }
    // DrawArea类继承⾃JPanel，⽤于⾃定义绘图逻辑
    private static class DrawArea extends JPanel {
        private String shape = "正⽅形";
        private int size = 100; // 添加尺⼨属性，默认为100
        public void setShape(String shape, int size) {
            this.shape = shape;
            this.size = size;
            this.repaint(); // 请求重新绘制⾯板
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            switch (shape) {
                case "正⽅形":
                    g.drawRect(50, 50, size, size);
                    break;
                case "⻓⽅形":
                    g.drawRect(50, 50, size * 2, size);
                    break;
                case "圆形":
                    g.drawOval(50, 50, size, size);
                    break;
                case "三⻆形":
                    int[] xPoints = {50 + size / 2, 50, 50 + size};
                    int[] yPoints = {50, 50 + size, 50 + size};
                    g.drawPolygon(xPoints, yPoints, 3);
                    break;
            }
        }
    }
}