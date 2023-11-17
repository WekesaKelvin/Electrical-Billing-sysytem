
package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MeterInfo extends JFrame implements ActionListener{
    JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9, label10, label11;
    Choice choice1, choice2, choice3,choice4;
    JButton button1,button2;
    MeterInfo(String meter){
        setLocation(600,200);
        setSize(700,500);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        
        JLabel title = new JLabel("Meter Information");
        title.setBounds(180, 10, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(title);
        
        label1 = new JLabel("Meter Number");
        label1.setBounds(100, 80, 100, 20);
        
        label11 = new JLabel(meter);
        label11.setBounds(240, 80, 200, 20);
        p.add(label1);
        p.add(label11);
        
        label2 = new JLabel("Meter Location");
        label2.setBounds(100, 120, 100, 20);
        choice1 = new Choice();
        choice1.add("Outside");
        choice1.add("Inside");
        choice1.setBounds(240, 120, 200, 20);
        p.add(label2);
        p.add(choice1);
        
        label3 = new JLabel("Meter Type");
        label3.setBounds(100, 160, 100, 20);
        choice2 = new Choice();
        choice2.add("Electric Meter");
        choice2.add("Solar Meter");
        choice2.add("Smart Meter");
        choice2.setBounds(240, 160, 200, 20);
        p.add(label3);
        p.add(choice2);
        
        label5 = new JLabel("Phase Code");
        label5.setBounds(100, 200, 100, 20);
        choice3 = new Choice();
        choice3.add("011");
        choice3.add("022");
        choice3.add("033");
        choice3.add("044");
        choice3.add("055");
        choice3.add("066");
        choice3.add("077");
        choice3.add("088");
        choice3.add("099");
        choice3.setBounds(240, 200, 200, 20);
        p.add(label5);
        p.add(choice3);
        
        label4 = new JLabel("Bill Type");
        label4.setBounds(100, 240, 100, 20);
        choice4 = new Choice();
        choice4.add("Normal");
        choice4.add("Industrial");
        choice4.setBounds(240, 240, 200, 20);
        p.add(label4);
        p.add(choice4);
        
        label6 = new JLabel("Days");
        label6.setBounds(100, 280, 100, 20);
        
        label9 = new JLabel("30 Days");
        label9.setBounds(240, 280, 200, 20);
        p.add(label6);
        p.add(label9);
        
        label7 = new JLabel("Note");
        label7.setBounds(100, 320, 100, 20);
        label10 = new JLabel("By Default Bill is calculated for 30 days only.");
        label10.setBounds(240, 320, 300, 20);
        p.add(label7);
        p.add(label10);
        
        button1 = new JButton("Submit");
        button1.setBounds(120, 390, 100, 25);
        button2 = new JButton("Cancel");
        button2.setBounds(250, 390, 100, 25);
        
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        
        p.add(button1);
        p.add(button2);
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon ichoice1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ichoice1.getImage().getScaledInstance(150, 300,Image.SCALE_DEFAULT);
        ImageIcon ichoice2 = new ImageIcon(i3);
        label8 = new JLabel(ichoice2);
        
        
        add(label8,"West");
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);
        
        button1.addActionListener(this);
        button2.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            String meter_number = label11.getText();
            String meter_location = choice1.getSelectedItem();
            String meter_type = choice2.getSelectedItem();
            String phase_code = choice3.getSelectedItem();
            String bill_type = choice4.getSelectedItem();
            String days = "30";

            String q1 = "Insert into meter_info values('"+meter_number+"','"+meter_location+"','"+meter_type+"','"+phase_code+"','"+bill_type+"','"+days+"')";
            try{
                Conn choice1 = new Conn();
                choice1.s.executeUpdate(q1);
                JOptionPane.showMessageDialog(null,"Meter Info Added Successfully");
                this.setVisible(false);

            }catch(Exception ex){
                 ex.printStackTrace();
            }
        }else if(ae.getSource() == button2){
            this.setVisible(false);
        }
    }
    
    
    public static void main(String[] args){
        new MeterInfo("").setVisible(true);
    }
}
