/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package MDI;

import DAO.ConnectionDB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author GreenDuy
 */
public class ThongKe extends javax.swing.JInternalFrame {

    /**
     * Creates new form ThongKe
     */
    public ThongKe() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        showPieChart();
        showBarChart(2023);
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

    /*========================================================================================*/
    
    public double thongKeDoanhThu1Thang(int thang, int nam)
    {
        ConnectionDB conn = new ConnectionDB();
        conn.getCn();
        
        
        String sql = "SELECT TONGTIENHD FROM HOADON WHERE Month(NGAYLAPHD) = '"+thang+"' AND YEAR(NGAYLAPHD) = '"+nam+"' GROUP BY TONGTIENHD ";
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
    
    public void showBarChart(int nam){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(thongKeDoanhThu1Thang(1, nam), "Amount", "Tháng 1");
        dataset.setValue(thongKeDoanhThu1Thang(2, nam), "Amount", "Tháng 2");
        dataset.setValue(thongKeDoanhThu1Thang(3, nam), "Amount", "Tháng 3");
        dataset.setValue(thongKeDoanhThu1Thang(4, nam), "Amount", "Tháng 4");
        dataset.setValue(thongKeDoanhThu1Thang(5, nam), "Amount", "Tháng 5");
        dataset.setValue(thongKeDoanhThu1Thang(6, nam), "Amount", "Tháng 6");
        dataset.setValue(thongKeDoanhThu1Thang(7, nam), "Amount", "Tháng 7");
        dataset.setValue(thongKeDoanhThu1Thang(8, nam), "Amount", "Tháng 8");
        dataset.setValue(thongKeDoanhThu1Thang(9, nam), "Amount", "Tháng 9");
        dataset.setValue(thongKeDoanhThu1Thang(10, nam), "Amount", "Tháng 10");
        dataset.setValue(thongKeDoanhThu1Thang(11, nam), "Amount", "Tháng 11");
        dataset.setValue(thongKeDoanhThu1Thang(12, nam), "Amount", "Tháng 12");
 
        JFreeChart chart = ChartFactory.createBarChart("Doanh thu các tháng trong năm " + String.valueOf(nam) ,"Tháng","Doanh thu", 
                dataset, PlotOrientation.VERTICAL, false,true,false);
        
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
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnSubmit = new javax.swing.JButton();
        txtNam = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        panelRound1 = new CUSTOM.PanelRound();
        panelPieChart = new javax.swing.JPanel();
        panelRound2 = new CUSTOM.PanelRound();
        panelBarChart = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THỐNG KÊ");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btnSubmit.setText("Tìm");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Năm:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1315, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit))
                .addGap(14, 14, 14))
        );

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        panelPieChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(panelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 1509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(panelPieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));
        panelRound2.setRoundBottomLeft(20);
        panelRound2.setRoundBottomRight(20);
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        panelBarChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelBarChart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(panelBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 1523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(panelBarChart, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 510, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        int nam = Integer.parseInt(txtNam.getText());
        showBarChart(nam);
        txtNam.setText("");
    }//GEN-LAST:event_btnSubmitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelBarChart;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelPieChart;
    private CUSTOM.PanelRound panelRound1;
    private CUSTOM.PanelRound panelRound2;
    private javax.swing.JTextField txtNam;
    // End of variables declaration//GEN-END:variables
}
