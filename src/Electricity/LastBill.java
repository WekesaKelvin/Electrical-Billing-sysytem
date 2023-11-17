package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class LastBill extends JFrame implements ActionListener{
    JLabel label;
    JTextArea text;
    JButton button;
    Choice choice;
    JPanel panel;
    LastBill(){
        setSize(500,900);
        setLayout(new BorderLayout());
        
        panel = new JPanel();
        
        label = new JLabel("Generate Bill");
        
        choice = new Choice();

        choice.add("1001");
        choice.add("1002");
        choice.add("1003");
        choice.add("1004");
        choice.add("1005");
        choice.add("1006");
        choice.add("1007");
        choice.add("1008");
        choice.add("1009");
        choice.add("1010");

        
        text = new JTextArea(50,15);
        JScrollPane jsp = new JScrollPane(text);
        text.setFont(new Font("Senserif",Font.ITALIC,18));
        
        button = new JButton("Generate Bill");
        
        panel.add(label);
        panel.add(choice);
        add(panel,"North");
        
        add(jsp,"Center");
        add(button,"South");
        
        button.addActionListener(this);
        
        setLocation(350,40);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c = new Conn();

            ResultSet rs = c.s.executeQuery("select * from emp where meter_number="+choice.getSelectedItem());
            
            if(rs.next()){
                text.append("\n    Customer Name:"+rs.getString("name"));
                text.append("\n    Meter Number:  "+rs.getString("meter_number"));
                text.append("\n    Address:            "+rs.getString("address"));
                text.append("\n    State:                 "+rs.getString("state"));
                text.append("\n    City:                   "+rs.getString("city"));
                text.append("\n    Email:                "+rs.getString("email"));
                text.append("\n    Phone Number  "+rs.getString("phone"));
                text.append("\n-------------------------------------------------------------");
                text.append("\n");
            }

            text.append("Details from previous bills\n\n\n");
            
            rs = c.s.executeQuery("select * from bill where meter_number="+choice.getSelectedItem());
            
            while(rs.next()){
                text.append("       "+ rs.getString("month") + "           " +rs.getString("amount") + "\n");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new LastBill().setVisible(true);
    }
}

