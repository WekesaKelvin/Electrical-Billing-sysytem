package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener{
    JLabel label1,label2,label3,label4,label5;
    JTextField text;
    Choice choice1,choice2;
    JButton button1,button2;
    JPanel panel;
    CalculateBill(){
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        
        label1 = new JLabel("Calculate Electricity Bill");
        label1.setBounds(30, 10, 400, 30);
        
        label2 = new JLabel("Meter No");
        label2.setBounds(60, 70, 100, 30);
        
        JLabel l6 = new JLabel("Name");
        l6.setBounds(60, 120, 100, 30);
        
        JLabel l7 = new JLabel("Address");
        l7.setBounds(60, 170, 100, 30);
        
        label3 = new JLabel("Units Cosumed");
        label3.setBounds(60, 220, 100, 30);
        
        label5 = new JLabel("Month");
        label5.setBounds(60, 270, 100, 30);
        
        choice1 = new Choice();
        choice1.setBounds(200, 70, 180, 20);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                choice1.add(rs.getString("meter"));
            }
        }catch(Exception e){}
        
        JLabel label11 = new JLabel();
        label11.setBounds(200, 120, 180, 20);
        panel.add(label11);
        
        JLabel label12 = new JLabel();
        label12.setBounds(200, 170, 180, 20);
        panel.add(label12);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '"+choice1.getSelectedItem()+"'");
            while(rs.next()){
                label11.setText(rs.getString("name"));
                label12.setText(rs.getString("address"));
            }
        }catch(Exception e){}
        
        choice1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter = '"+choice1.getSelectedItem()+"'");
                    while(rs.next()){
                        label11.setText(rs.getString("name"));
                        label12.setText(rs.getString("address"));
                    }
                }catch(Exception e){}
            }
        });
        
        text = new JTextField();
        text.setBounds(200, 220, 180, 20);
        
        
        choice2 = new Choice();
        choice2.setBounds(200, 270, 180, 20);
        choice2.add("January");
        choice2.add("February");
        choice2.add("March");
        choice2.add("April");
        choice2.add("May");
        choice2.add("June");
        choice2.add("July");
        choice2.add("August");
        choice2.add("September");
        choice2.add("October");
        choice2.add("November");
        choice2.add("December");
        
        button1 = new JButton("Submit");
        button1.setBounds(100, 350, 100, 25);
        button2 = new JButton("Cancel");
        button2.setBounds(230, 350, 100, 25);
        
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);

        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(180, 270,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        label4 = new JLabel(i3);
        
        
        
        label1.setFont(new Font("Senserif",Font.PLAIN,26));
        //Move the label to center
        label1.setHorizontalAlignment(JLabel.CENTER);
        
        
        panel.add(label1);
        panel.add(label2);
        panel.add(l6);
        panel.add(l7);
        panel.add(choice1);
        panel.add(label5);
        panel.add(choice2);
        panel.add(label3);
        panel.add(text);
        panel.add(button1);
        panel.add(button2);
        
        setLayout(new BorderLayout(30,30));
        
        
        add(panel,"Center");
        add(label4,"West");
        
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);        
        setSize(750,500);
        setLocation(550,220);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            String meter_no = choice1.getSelectedItem();
            String units = text.getText();
            String month = choice2.getSelectedItem();

            int units_consumed = Integer.parseInt(units);

            int total_bill = 0;
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from tax");
                while(rs.next()){
                    total_bill = units_consumed * Integer.parseInt(rs.getString("cost_per_unit")); // 120 * 7
                    total_bill += Integer.parseInt(rs.getString("meter_rent"));
                    total_bill += Integer.parseInt(rs.getString("service_charge"));
                    total_bill += Integer.parseInt(rs.getString("service_tax"));
                    total_bill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    total_bill += Integer.parseInt(rs.getString("fixed_tax"));
                }
            }catch(Exception e){}

            String q = "insert into bill values('"+meter_no+"','"+month+"','"+units+"','"+total_bill+"', 'Not Paid')";

            try{
                Conn choice1 = new Conn();
                choice1.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
                this.setVisible(false);
            }catch(Exception aee){
                aee.printStackTrace();
            }

        }else if(ae.getSource()== button2){
            this.setVisible(false);
        }        
    }
    
       
    public static void main(String[] args){
        new CalculateBill().setVisible(true);
    }
}
