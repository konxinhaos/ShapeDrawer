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
                // 获取⽤户选择的项
                JComboBox cb = (JComboBox) e.getSource();
                String shapeName = (String) cb.getSelectedItem();
                // 根据选择更新绘图区域的当前图形，并重绘
                drawArea.setShape(shapeName);
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
        // 默认绘制的图形是正⽅形
        private String shape = "正⽅形";
        // setShape⽅法⽤于更新当前要绘制的图形
        public void setShape(String shape) {
            this.shape = shape;
            this.repaint(); // 调⽤repaint⽅法来重绘⾯板
        }
        @Override
        // 重写paintComponent⽅法以实现⾃定义绘图逻辑
        protected void paintComponent(Graphics g) {
            // 调⽤⽗类的paintComponent⽅法进⾏默认的绘图操作
            super.paintComponent(g);
            // 根据当前选择的图形，使⽤switch语句绘制相应的图形
            switch (shape) {
                case "正⽅形":
                    // 绘制正⽅形
                    g.drawRect(50, 50, 100, 100);
                    break;
                case "⻓⽅形":
                    // 绘制⻓⽅形
                    g.drawRect(50, 50, 150, 100);
                    break;
                case "圆形":
                    // 绘制圆形
                    g.drawOval(50, 50, 100, 100);
                    break;
                case "三⻆形":
                    // 绘制三⻆形，需要定义顶点坐标
                    int[] xPoints = {100, 50, 150};
                    int[] yPoints = {50, 150, 150};
                    // 使⽤drawPolygon⽅法绘制多边形，这⾥是三⻆形
                    g.drawPolygon(xPoints, yPoints, 3);
                    break;
                default:
                    break;
            }
        }
    }
}