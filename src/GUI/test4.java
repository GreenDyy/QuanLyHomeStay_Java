/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import DAO.ConnectionDB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.time.SimpleTimePeriod;
/**
 *
 * @author GreenDuy
 */
public class test4 extends javax.swing.JFrame {

    /**
     * Creates new form test4
     */

  
    
    public test4() {
        initComponents();
        showPieChart();
        showHistogram();
        showBarChart();
        showLineChart();

    }
    
   
    public void showPieChart(){
        DefaultPieDataset barDataset = new DefaultPieDataset( );
     
        
        String sql = "select TENDV, COUNT(PHUCVU.MADV) AS SOLUONG from PHUCVU, DICHVU  WHERE PHUCVU.MADV = DICHVU.MADV " 
                    + "GROUP BY TENDV";
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) 
            {
                String key = rs.getString(1);
                double value = rs.getDouble(2);
                barDataset.setValue( key , value);  
            }
        }  
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        //create dataset
      
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Biểu đồ dịch vụ được sử dụng",barDataset, true,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
    }

    /*=============================================================================*/
    
    public void showLineChart(){
        //create dataset for the graph
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Tiền", "january");
        dataset.setValue(150, "Tiền", "february");
        dataset.setValue(18, "Tiền", "march");
        dataset.setValue(100, "Tiền", "april");
        dataset.setValue(80, "Tiền", "may");
        dataset.setValue(250, "Tiền", "june");
        
        //create chart
        JFreeChart linechart = ChartFactory.createLineChart("Doanh thu các tháng","Tháng","Doanh thu", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
        //create plot object
         CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
       // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        
         //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        panelLineChart.removeAll();
        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
    }

    /*========================================================================================*/
    
    public void showHistogram(){
        
         double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
                            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
                            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
                            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
                            77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
                          };
 
 
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);
        
         JFreeChart chart = ChartFactory.createHistogram("JFreeChart Histogram","Data", "Frequency", dataset,PlotOrientation.VERTICAL, false,true,false);
            XYPlot plot= chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        
        
        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        panelHistogram.removeAll();
        panelHistogram.add(barpChartPanel2, BorderLayout.CENTER);
        panelHistogram.validate();
    }

    /*========================================================================================*/
    
    public double thongKeDoanhThu1Thang(int thang)
    {
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        
        
        String sql = "SELECT TONGTIENHD FROM HOADON WHERE Month(NGAYLAPHD) = '"+thang+"' GROUP BY TONGTIENHD ";
        double tongtien1thang = 0;
        try{
        ResultSet rs = conn.executeQuery(sql);
            while (rs.next()) 
            {
                tongtien1thang = tongtien1thang + rs.getDouble(1);
                //dstiencacthang[thang] = tongtien1thang;
                
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return tongtien1thang;
    }
    
    public void showBarChart(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(thongKeDoanhThu1Thang(1), "Tiền", "Tháng 1");
        dataset.setValue(thongKeDoanhThu1Thang(2), "Tiền", "Tháng 2");
        dataset.setValue(thongKeDoanhThu1Thang(3), "Tiền", "Tháng 3");
        dataset.setValue(thongKeDoanhThu1Thang(4), "Tiền", "Tháng 4");
        dataset.setValue(thongKeDoanhThu1Thang(5), "Tiền", "Tháng 5");
        dataset.setValue(thongKeDoanhThu1Thang(6), "Tiền", "Tháng 6");
        dataset.setValue(thongKeDoanhThu1Thang(7), "Tiền", "Tháng 7");
        dataset.setValue(thongKeDoanhThu1Thang(8), "Tiền", "Tháng 8");
        dataset.setValue(thongKeDoanhThu1Thang(9), "Tiền", "Tháng 9");
        dataset.setValue(thongKeDoanhThu1Thang(10), "Tiền", "Tháng 10");
        dataset.setValue(thongKeDoanhThu1Thang(11), "Tiền", "Tháng 11");
        dataset.setValue(thongKeDoanhThu1Thang(12), "Tiền", "Tháng 12");
        
        
        
//        dataset.setValue(200, "Tiền", "january");
//        dataset.setValue(150, "Tiền", "february");
//        dataset.setValue(18, "Tiền", "march");
//        dataset.setValue(100, "Tiền", "april");
//        dataset.setValue(80, "Tiền", "may");
//        dataset.setValue(250, "Tiền", "june");
        
        
        
        JFreeChart chart = ChartFactory.createBarChart("Doanh thu các tháng","Tháng","Doanh thu", 
                dataset, PlotOrientation.VERTICAL, true,true,false);
        
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204,0,51);
        renderer.setSeriesPaint(0, clr3);
        
        ChartPanel barpChartPanel = new ChartPanel(chart);
        panelBarChart.removeAll();
        panelBarChart.add(barpChartPanel, BorderLayout.CENTER);
        panelBarChart.validate();
        
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        panelBarChart = new javax.swing.JPanel();
        panelHistogram = new javax.swing.JPanel();
        panelPieChart = new javax.swing.JPanel();
        panelLineChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMain.setBackground(new java.awt.Color(255, 255, 255));

        panelBarChart.setLayout(new java.awt.BorderLayout());

        panelHistogram.setLayout(new java.awt.BorderLayout());

        panelPieChart.setLayout(new java.awt.BorderLayout());

        panelLineChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelHistogram, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(357, 357, 357))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(panelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(panelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(panelBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelHistogram, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(test4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelBarChart;
    private javax.swing.JPanel panelHistogram;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelPieChart;
    // End of variables declaration//GEN-END:variables
}
