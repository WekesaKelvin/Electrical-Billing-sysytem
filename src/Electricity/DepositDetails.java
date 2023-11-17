package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; //Requires external library

public class DepositDetails extends JFrame implements ActionListener{
 
    JTable table;
    JButton button1, button2;
    JLabel label1, label2;
    Choice choice1, choice2;
    String x[] = {"Meter Number","Month","Units","Total Bill","Status"};
    String y[][] = new String[40][8];
    int i=0, j=0;
    DepositDetails(){
        super("Deposit Details");
        setSize(700,750);
        setLocation(600,150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        label1 = new JLabel("Sort by Meter Number");
        label1.setBounds(20, 20, 150, 20);
        add(label1);
        
        choice1 = new Choice();
        
        label2 = new JLabel("Sort By Month");
        label2.setBounds(400, 20, 100, 20);
        add(label2);
        
        choice2 = new Choice();
        
        table = new JTable(y,x);
        
        try{
            Conn c  = new Conn();
            String s1 = "select * from bill";
            ResultSet rs  = c.s.executeQuery(s1);
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
            String str2 = "select * from customer";
            rs = c.s.executeQuery(str2);
            while(rs.next()){
                choice1.add(rs.getString("meter"));
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        choice1.setBounds(180,20, 150, 20);
        add(choice1);
        
        
        choice2.setBounds(520, 20, 150, 20);
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
        add(choice2);
        
        
        button1 = new JButton("Search");
        button1.setBounds(20, 70, 80, 20);
        button1.addActionListener(this);
        add(button1);
        
        button2 = new JButton("Print");
        button2.setBounds(120, 70, 80, 20);
        button2.addActionListener(this);
        add(button2);
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 650);
        add(sp);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            String str = "select * from bill where meter = '"+choice1.getSelectedItem()+"' AND month = '"+choice2.getSelectedItem()+"'";
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(str);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){}
        }else if(ae.getSource() == button2){
            try{
                table.print();
            }catch(Exception e){}
        }
    }
    
    public static void main(String[] args){
        new DepositDetails().setVisible(true);
    }
    
}
