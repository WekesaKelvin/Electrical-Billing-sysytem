package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener{
    JLabel label1,label2,label3,label4, label5;
    Choice choice;
    JButton button1,button2;
    String meter;
    PayBill(String meter){
        this.meter = meter;
        setLayout(null);
        
        setBounds(550, 220, 900, 600);
        
        JLabel title = new JLabel("Electricity Bill");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setBounds(120, 5, 400, 30);
        add(title);
        
        label1 = new JLabel("Meter No.");
        label1.setBounds(35, 80, 200, 20);
        add(label1);
        
        JLabel label11 = new JLabel();
        label11.setBounds(300, 80, 200, 20);
        add(label11);
        
        JLabel l2 = new JLabel("Name");
        l2.setBounds(35, 140, 200, 20);
        add(l2);
        
        JLabel label12 = new JLabel();
        label12.setBounds(300, 140, 200, 20);
        add(label12);
        
        label2 = new JLabel("Month");
        label2.setBounds(35, 200, 200, 20);
        add(label2);
        
        choice = new Choice();
        choice.setBounds(300, 200, 200, 20);
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
        add(choice);
        
        
        label3 = new JLabel("Units");
        label3.setBounds(35, 260, 200, 20);
        add(label3);
        
        JLabel label13 = new JLabel();
        label13.setBounds(300, 260, 200, 20);
        add(label13);
        
        label4 = new JLabel("Total Bill");
        label4.setBounds(35, 320, 200, 20);
        add(label4);
        
        JLabel label14 = new JLabel();
        label14.setBounds(300, 320, 200, 20);
        add(label14);
        
        label5 = new JLabel("Status");
        label5.setBounds(35, 380, 200, 20);
        add(label5);
        
        JLabel label15 = new JLabel();
        label15.setBounds(300, 380, 200, 20);
        label15.setForeground(Color.RED);
        add(label15);
        
        
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '"+meter+"'");
            while(rs.next()){
                label11.setText(rs.getString("meter"));
                label12.setText(rs.getString("name"));
            }
            rs = c.s.executeQuery("select * from bill where meter = '"+meter+"' AND month = 'January' ");
            while(rs.next()){
                label13.setText(rs.getString("units"));
                label14.setText(rs.getString("total_bill"));
                label15.setText(rs.getString("status"));
            }
        }catch(Exception e){}
        
        choice.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter = '"+meter+"' AND month = '"+choice.getSelectedItem()+"'");
                    while(rs.next()){
                        label13.setText(rs.getString("units"));
                        label14.setText(rs.getString("total_bill"));
                        label15.setText(rs.getString("status"));
                    }
                }catch(Exception e){}
            }
        });
        
        button1 = new JButton("Pay");
        button1.setBounds(100, 460, 100, 25);
        add(button1);
        button2 = new JButton("Back");
        button2.setBounds(230, 460, 100, 25);
        add(button2);
        
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);

        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l21 = new JLabel(i3);
        l21.setBounds(400, 120, 600, 300);
        add(l21);
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            try{
                Conn c = new Conn();
                c.s.executeQuery("update bill status = 'Paid' where meter = '"+meter+"' AND month = '"+choice.getSelectedItem()+"'");
                
            } catch(Exception e){}
            this.setVisible(false);
            new Paytm(meter).setVisible(true);

        }else if(ae.getSource()== button2){
            this.setVisible(false);
        }        
    }
    
       
    public static void main(String[] args){
        new PayBill("").setVisible(true);
    }
}
