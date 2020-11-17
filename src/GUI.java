import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.XChartPanel;


public class GUI implements ActionListener {
    private static JLabel vLabel, cLabel, lLabel, rLabel, t_step_Label, t_end_Label;
    private static JTextField vText, cText, lText, rText, t_step_Text, t_end_Text;
    private static JLabel vLabel_unit,cLabel_unit,lLabel_unit,rLabel_unit;
    private static JLabel t_step_Label_unit,t_end_Label_unit;
    private static JButton submit_button;
    private static ImageIcon img1;
    private static JLabel label1, info_msg;
    private static JLabel filename_Label, filedir_Label, extLabel;
    private static JTextField filename_Text, filedir_Text;
    private static JLabel file_error, v_error, c_error, l_error, r_error, t_step_error, t_end_error;

    public static void window(){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(700, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        //create label, text field, unit label for V,C,L,R, t_step, t_end,
        // file name and file directory
        vLabel = new JLabel("Voltage (V)");
        vLabel.setBounds(20,20,100,25);
        panel.add(vLabel);
        vText = new JTextField(30);
        vText.setBounds(120, 20, 165, 25);
        panel.add(vText);
        vLabel_unit = new JLabel("volts");
        vLabel_unit.setBounds(300,20,100,25);
        panel.add(vLabel_unit);
        v_error = new JLabel("");
        v_error.setBounds(400, 20, 300, 25);
        v_error.setForeground(Color.RED);
        panel.add(v_error);

        cLabel = new JLabel("Capacitance (C)");
        cLabel.setBounds(20,60,100,25);
        panel.add(cLabel);
        cText = new JTextField(30);
        cText.setBounds(120, 60, 165, 25);
        panel.add(cText);
        cLabel_unit = new JLabel("farads");
        cLabel_unit.setBounds(300,60,100,25);
        panel.add(cLabel_unit);
        c_error = new JLabel("");
        c_error.setBounds(400, 60, 300, 25);
        c_error.setForeground(Color.RED);
        panel.add(c_error);

        lLabel = new JLabel("Inductance (L)");
        lLabel.setBounds(20,100,100,25);
        panel.add(lLabel);
        lText = new JTextField(30);
        lText.setBounds(120, 100, 165, 25);
        panel.add(lText);
        lLabel_unit = new JLabel("henrys");
        lLabel_unit.setBounds(300,100,100,25);
        panel.add(lLabel_unit);
        l_error = new JLabel("");
        l_error.setBounds(400, 100, 300, 25);
        l_error.setForeground(Color.RED);
        panel.add(l_error);

        rLabel = new JLabel("Resistance (R)");
        rLabel.setBounds(20,140,100,25);
        panel.add(rLabel);
        rText = new JTextField(30);
        rText.setBounds(120, 140, 165, 25);
        panel.add(rText);
        rLabel_unit = new JLabel("ohms");
        rLabel_unit.setBounds(300,140,100,25);
        panel.add(rLabel_unit);
        r_error = new JLabel("");
        r_error.setBounds(400, 140, 300, 25);
        r_error.setForeground(Color.RED);
        panel.add(r_error);

        t_step_Label = new JLabel("Time_step");
        t_step_Label.setBounds(20,220,100,25);
        panel.add(t_step_Label);
        t_step_Text = new JTextField(30);
        t_step_Text.setBounds(120, 220, 165, 25);
        panel.add(t_step_Text);
        t_step_Label_unit = new JLabel("seconds");
        t_step_Label_unit.setBounds(300,220,100,25);
        panel.add(t_step_Label_unit);
        t_step_error = new JLabel("");
        t_step_error.setBounds(400, 220, 300, 25);
        t_step_error.setForeground(Color.RED);
        panel.add(t_step_error);

        t_end_Label = new JLabel("Time_end");
        t_end_Label.setBounds(20,260,100,25);
        panel.add(t_end_Label);
        t_end_Text = new JTextField(30);
        t_end_Text.setBounds(120, 260, 165, 25);
        panel.add(t_end_Text);
        t_end_Label_unit = new JLabel("seconds");
        t_end_Label_unit.setBounds(300,260,100,25);
        panel.add(t_end_Label_unit);
        t_end_error = new JLabel("");
        t_end_error.setBounds(400, 260, 300, 25);
        t_end_error.setForeground(Color.RED);
        panel.add(t_end_error);

        filename_Label = new JLabel("Output file name:");
        filename_Label.setBounds(20,340,100,25);
        panel.add(filename_Label);
        filename_Text = new JTextField(30);
        filename_Text.setBounds(20, 360, 165, 25);
        filename_Text.setText("output");
        panel.add(filename_Text);
        extLabel= new JLabel(".log");
        extLabel.setBounds(190,360,100,25);
        panel.add(extLabel);

        filedir_Label = new JLabel("File Directory (Default: same as project directory):");
        filedir_Label.setBounds(300,340,300,25);
        panel.add(filedir_Label);
        filedir_Text = new JTextField(200);
        filedir_Text.setBounds(300, 360, 300, 25);
        panel.add(filedir_Text);

        file_error = new JLabel("");
        file_error.setBounds(20, 380, 700, 25);
        panel.add(file_error);

        info_msg = new JLabel("");
        info_msg.setBounds(20, 400, 500, 25);
        panel.add(info_msg);

        submit_button = new JButton("Submit");
        submit_button.setBounds(20, 440, 80, 25);
        submit_button.addActionListener(new GUI());
        panel.add(submit_button);

        img1 = new ImageIcon("range.jpg");
        label1 = new JLabel(img1);
        label1.setBounds(10,450,650,380);
        panel.add(label1);

        frame.setVisible(true);

        }

    //Function for actionPerformed (Submit button)
    public void actionPerformed(ActionEvent e) {
        // the flag to see if all input pass the validation
        //Reset all the error message
        file_error.setText("");
        v_error.setText("");
        c_error.setText("");
        l_error.setText("");
        r_error.setText("");
        t_step_error.setText("");
        t_end_error.setText("");

        //Get the value from test field after the user pressed the submit button
        //Then, validate every inputs
        boolean pass_flag = check_value();

        //A list for all outputs from calculation
        List<Double> out_list = new ArrayList<>();
        List<Double> time_list = new ArrayList<>();
        //convert String to double if all inputs passed the validation
        if (pass_flag) {
            String v = vText.getText();
            double v_value = Double.parseDouble(v);
            String c = cText.getText();
            double c_value = Double.parseDouble(c);
            String l = lText.getText();
            double l_value = Double.parseDouble(l);
            String r = rText.getText();
            double r_value = Double.parseDouble(r);
            String t_step = t_step_Text.getText();
            double t_step_value = Double.parseDouble(t_step);
            String t_end = t_end_Text.getText();
            double t_end_value = Double.parseDouble(t_end);

            System.out.println("Voltage = " + v);
            System.out.println("capacitance = " + c);
            System.out.println("Inductance= " + l);
            System.out.println("Resistance = " + r);
            System.out.println("Time_step = " + t_step);
            System.out.println("Time_end = " + t_end);

            // calculate the result for each time step and add the value to the list
            System.out.println("Time\t\t\tq(t)");
            double t_current = 0;
            while (t_current <= t_end_value) {
                double result = calculate(v_value, c_value, l_value, r_value, t_current);
                out_list.add(result);
                time_list.add(t_current);
                System.out.format("%.2e", t_current);
                System.out.print("\t\t");
                System.out.format("%.5e%n", result);
                t_current = t_current + t_step_value;
            }

            //File Section -----------------------------------------------------
            //create a log file with user inputs if pass the input validation
            if (pass_flag) {
                //validation for file section,
                // including the file name and directory entered by user
                try {
                    String filename = filename_Text.getText();
                    String filedir = filedir_Text.getText();
                    File file = new File(filedir + filename + ".log");
                    file.createNewFile();

                    //Create a writer to write the output to the log file
                    PrintWriter writer = new PrintWriter(file);
                    writer.println("Voltage = " + v + " volts");
                    writer.println("capacitance = " + c + " farads");
                    writer.println("Inductance= " + l + " henrys");
                    writer.println("Resistance = " + r + " ohms");
                    writer.println("Time_step = " + t_step + " (s)");
                    writer.println("Time_end = " + t_end + " (s)");
                    writer.println("Total result = " + time_list.size());
                    writer.println("\n\nTime\t\t\tq(t)");
                    for (int i = 0; i < out_list.size(); i++) {
                        writer.format("%.2e", time_list.get(i));
                        writer.print("\t\t");
                        writer.format("%.5e%n", out_list.get(i));
                    }
                    writer.close();

                    file_error.setForeground(Color.BLACK);
                    file_error.setText("Submit Successfully!!!");
                    System.out.println("Submit Successfully!!!");
                    //create graph
                    createGraph(out_list, time_list, filedir);


                } catch (IOException error) {
                    file_error.setForeground(Color.RED);
                    file_error.setText("ERROR: " + error.toString());
                    System.out.println("Submit Failed!!!");
                }
            }
            else{
                System.out.println("Submit Failed!!!");
            }
        }
    }

    // Function for input validations (all inputs)
    public static boolean check_value(){
        boolean pass_flag = true;
        //Validation for v_value
        String v = vText.getText();
        double v_value;
        try {
            v_value = Double.parseDouble(v);
            //check if the input is out of range
            if (v_value > 15 || v_value < 4) {
                v_error.setText("Error: Input out of range!");
                pass_flag = false;
            }
        }
        catch (Exception error){
            if (v.length() == 0){
                v_error.setText("Empty input!");
            }
            else{
                v_error.setText("Error: Invalid Input!");
            }
            pass_flag = false;
        }

        //Validation for c_value
        String c = cText.getText();
        double c_value;
        try {
            c_value = Double.parseDouble(c);
            //check if the input is out of range
            if (c_value > Math.pow(10,-7) || c_value < Math.pow(10,-9)) {
                c_error.setText("Error: Input out of range!");
                pass_flag = false;
            }
        }
        catch (Exception error){
            if (c.length() == 0){
                c_error.setText("Empty input!");
            }
            else{
                c_error.setText("Error: Invalid Input!");
            }
            pass_flag = false;
        }

        //Validation for l_value
        String l = lText.getText();
        double l_value;
        try {
            l_value = Double.parseDouble(l);
            //check if the input is out of range
            if (l_value > Math.pow(10,-1) || l_value < Math.pow(10,-3)) {
                l_error.setText("Error: Input out of range!");
                pass_flag = false;
            }
        }
        catch (Exception error){
            if (l.length() == 0){
                l_error.setText("Empty input!");
            }
            else{
                l_error.setText("Error: Invalid Input!");
            }
            pass_flag = false;
        }

        //Validation for r_value
        String r = rText.getText();
        double r_value;
        try {
            r_value = Double.parseDouble(r);
            //check if the input is out of range
            if (r_value > 10 || r_value < 5) {
                r_error.setText("Error: Input out of range!");
                pass_flag = false;
            }
        }
        catch (Exception error){
            if (r.length() == 0){
                r_error.setText("Empty input!");
            }
            else{
                r_error.setText("Error: Invalid Input!");
            }
            pass_flag = false;
        }

        //Validation for t_step_value
        String t_step = t_step_Text.getText();
        double t_step_value;
        try {
            t_step_value = Double.parseDouble(t_step);
            //check if the input is out of range
            if (t_step_value <= 0) {
                t_step_error.setText("Error: Input out of range!");
                pass_flag = false;
            }
        }
        catch (Exception error){
            if (t_step.length() == 0){
                t_step_error.setText("Empty input!");
            }
            else{
                t_step_error.setText("Error: Invalid Input!");
            }
            pass_flag = false;
        }

        //Validation for t_end_value
        String t_end = t_end_Text.getText();
        double t_end_value;
        try {
            t_end_value = Double.parseDouble(t_end);
            //check if the input is out of range
            if (t_end_value < 0 ) {
                t_end_error.setText("Error: Input out of range!");
                pass_flag = false;
            }
        }
        catch (Exception error){
            if (t_end.length() == 0){
                t_end_error.setText("Empty input!");
            }
            else{
                t_end_error.setText("Error: Invalid Input!");
            }
            pass_flag = false;
        }
        return pass_flag;
    }

    // Function for calculation (formula)
    public static double calculate(double V, double C, double L, double R, double t){
        double q1 = V * C * Math.exp(-1 * (R * t)/(2 * L));
        double q2 = Math.cos(t * Math.sqrt( 1/(L * C) - Math.pow(R/(2 * L), 2) ));
        double q = q1 * q2;
        return q;
    }

    // Function for creating the plot
    public static void createGraph(List<Double> xData, List<Double> yData, String filedir) {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(500).height(400).title("q vs t").xAxisTitle("Time (sec)").yAxisTitle("q(t)").build();

        // Customize Chart
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);

        // Add Series
        XYSeries series = chart.addSeries("q(t)", yData, xData);
        series.setSmooth(true);
        series.setMarker(SeriesMarkers.CIRCLE);
        chart.getStyler().setMarkerSize(5);

        // Show the chart
        JFrame frame2 = new JFrame("q vs t");
        frame2.setSize(600, 850);
        JPanel panel = new XChartPanel<>(chart);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.add(panel);
        frame2.pack();
        frame2.setVisible(true);

        //save the chart
        try {
            BitmapEncoder.saveBitmap(chart, filedir + "qt_graph", BitmapEncoder.BitmapFormat.PNG);
            info_msg.setText("Output file and qt_graph has been saved.");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

}

