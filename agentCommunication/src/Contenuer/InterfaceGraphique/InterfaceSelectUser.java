package Contenuer.InterfaceGraphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import  Agent.Agent;

import Contenuer.Container2;
import Contenuer.Container3;
import Contenuer.Container1;
import jade.gui.GuiEvent;

import jade.wrapper.StaleProxyException;

public class InterfaceSelectUser extends JFrame {

    private JTextField jTextField=new JTextField("nom Agent",12);
    private JPanel verticalpanel=new JPanel();
    private JTextField jTextFieldmessage=new JTextField("(taper fin pour fermer la discusssion)",30);
    private JButton jButtonenvoyer=new JButton("envoyer message");
    private JTextArea jTextArea=new JTextArea();
    private JButton discuterUser2;
    private JButton discuterUser3;
    private  JButton Start=new JButton("start Discussion");

    private JPanel jPanel=new JPanel();

    private Agent agents=new Agent();


     public InterfaceSelectUser(List<String> users){

         discuterUser2=new JButton(users.get(0));
         discuterUser3=new JButton(users.get(1));
        jPanel.setLayout(new FlowLayout());
        jPanel.add(jTextField);
        jPanel.add(jTextFieldmessage);
        jPanel.add(jButtonenvoyer);
        jPanel.add(Start);

        verticalpanel.add(discuterUser2);
        verticalpanel.add(discuterUser3);

        this.setLayout(new BorderLayout());
        this.add(verticalpanel,BorderLayout.WEST);

        this.add(jPanel,BorderLayout.SOUTH);
        this.add(new JScrollPane(jTextArea),BorderLayout.CENTER);
        this.setSize(800,400);
        jTextFieldmessage.setVisible(false);
        jButtonenvoyer.setVisible(false);


        this.setVisible(true);
        jButtonenvoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String agent=jTextField.getText();
                String message=jTextFieldmessage.getText();
                GuiEvent gev=new GuiEvent(this,1);
                Map<String,String> params=new HashMap<>();
                if(message.equals("fin")){
                    verticalpanel.setVisible(true);
                    discuterUser3.setVisible(true);
                    discuterUser2.setVisible(true);
                    jTextArea.setText("");
                }
                else{
                jTextArea.append("me : "+message +"\n");}
               params.put("agent",agent);
                params.put("message",message);
                gev.addParameter(params);
                agents.onGuiEvent(gev);



            }
        });
       discuterUser2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    jTextField.setText(discuterUser2.getText());
                    System.out.println(discuterUser2.getText());
                    if(discuterUser2.getText().equals("User1")){
                        discuterUser2.setVisible(false);
                        Container1.main(null);


                    }
                    else if(discuterUser2.getText().equals("User2")){
                        discuterUser2.setVisible(false);
                        Container2.main(null);

                    }
                    else if(discuterUser2.getText().equals("User2")){
                        discuterUser2.setVisible(false);
                        Container3.main(null);

                    }


                } catch (StaleProxyException ex) {
                    ex.printStackTrace();
                }


            }
        });
         discuterUser3.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     jTextField.setText(discuterUser3.getText());

                    if(discuterUser3.getText().equals("User1")){
                        discuterUser3.setVisible(false);

                        Container1.main(null);


                     }
                     else if(discuterUser3.getText().equals("User2")){
                        discuterUser3.setVisible(false);
                         Container2.main(null);

                     }
                    else if(discuterUser3.getText().equals("User3")){
                        discuterUser3.setVisible(false);
                         Container3.main(null);

                     }

             } catch (StaleProxyException ex) {
                     ex.printStackTrace();
                 }


             }
         });
         Start.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String agent=jTextField.getText();
                 String message=Start.getText();
                 GuiEvent gev=new GuiEvent(this,1);
                 Map<String,String> params=new HashMap<>();

                 params.put("agent",agent);
                 params.put("message",message);
                 gev.addParameter(params);
                 agents.onGuiEvent(gev);
                 Start.setVisible(false);
                 jTextFieldmessage.setVisible(true);
                 jButtonenvoyer.setVisible(true);


             }
         });


     }
     public void setAgentToInterface(Agent agent){
         this.agents=agent;
     }
     public void setInterfaceMessage(String message,boolean append){
         if(append==true){
             jTextArea.append(message+"\n");
             verticalpanel.setVisible(false);
             jTextField.setText(message.substring(0,5));
         }
         else{

             verticalpanel.setVisible(false);
             jTextField.setText(message.substring(0,5));

         }
     }
     public void fromStartToSendmessage(){
         verticalpanel.setVisible(false);
         Start.setVisible(false);
         jTextFieldmessage.setVisible(true);
         jButtonenvoyer.setVisible(true);
     }

}
