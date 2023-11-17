package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener{
    JLabel label1, label2;
    JTextArea text;
    JButton button;
    Choice choice;
    JPanel panel;
    String meter;
    GenerateBill(String meter){
        this.meter = meter;
        setSize(500,900);
        setLayout(new BorderLayout());
        
        panel = new JPanel();
        
        label1 = new JLabel("Generate Bill");
        
        label2 = new JLabel(meter);
        choice = new Choice();
        
        choice.add("January");
        choice.add("February");
        choice.add("March");
        choice.add("April");
        choice.add("May");
        choice.add("June");
        choice.add("July");
        choice.add("August");
        choice.add("September");
        choice.add("October");
        choice.add("November");
        choice.add("December");

        
        text = new JTextArea(50,15);
        text.setText("\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
        JScrollPane jsp = new JScrollPane(text);
        text.setFont(new Font("Senserif",Font.ITALIC,18));
        
        button = new JButton("Generate Bill");
        
        panel.add(label1);
        panel.add(label2);
        panel.add(choice);
        add(panel,"North");
        
        add(jsp,"Center");
        add(button,"South");
        
        button.addActionListener(this);
        
        setLocation(750,100);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c = new Conn();
   
            String month = choice.getSelectedItem();
            text.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF "+month+" ,2021\n\n\n");
            
            ResultSet rs = c.s.executeQuery("select * from customer where meter="+meter);
            
            if(rs.next()){
                text.append("\n    Customer Name:"+rs.getString("name"));
                text.append("\n    Meter Number:  "+rs.getString("meter"));
                text.append("\n    Address:            "+rs.getString("address"));
                text.append("\n    State:                 "+rs.getString("state"));
                text.append("\n    City:                   "+rs.getString("city"));
                text.append("\n    Email:                "+rs.getString("email"));
                text.append("\n    Phone Number  "+rs.getString("phone"));
                text.append("\n-------------------------------------------------------------");
                text.append("\n");
            }
            
            rs = c.s.executeQuery("select * from meter_info where meter_number = " + meter);
            
            if(rs.next()){
                text.append("\n    Meter Location:"+rs.getString("meter_location"));
                text.append("\n    Meter Type:      "+rs.getString("meter_type"));
                text.append("\n    Phase Code:    "+rs.getString("phase_code"));
                text.append("\n    Bill Type:         "+rs.getString("bill_type"));
                text.append("\n    Days:               "+rs.getString("days"));
                text.append("\n");
            }
            rs = c.s.executeQuery("select * from tax");
            if(rs.next()){
                text.append("---------------------------------------------------------------");
                text.append("\n\n");
                text.append("\n Cost per Unit:             Rs "+rs.getString("cost_per_unit"));
                text.append("\n Meter Rent:                Rs "+rs.getString("meter_rent"));
                text.append("\n Service Charge:            Rs "+rs.getString("service_charge"));
                text.append("\n Service Tax:               Rs "+rs.getString("service_tax"));
                text.append("\n Swacch Bharat Cess:        Rs "+rs.getString("swacch_bharat_cess"));
                text.append("\n Fixed Tax:                 Rs "+rs.getString("fixed_tax"));
                text.append("\n");
                
            }
            
            rs = c.s.executeQuery("select * from bill where meter="+meter+" AND month = '"+choice.getSelectedItem()+"'");
            
            if(rs.next()){
                text.append("\n    Current Month :\t"+rs.getString("month"));
                text.append("\n    Units Consumed:\t"+rs.getString("units"));
                text.append("\n    Total Charges :\t"+rs.getString("total_bill"));
                text.append("\n---------------------------------------------------------------");
                text.append("\n    TOTAL PAYABLE :\t"+rs.getString("total_bill"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new GenerateBill("").setVisible(true);
    }
}

