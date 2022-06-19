package Agent;

import Contenuer.InterfaceGraphique.InterfaceSelectUser;

import jade.core.behaviours.Behaviour;

import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;

public class User1 extends Agent{

    private InterfaceSelectUser interfaceSelectUser;
    @Override
    protected void setup() {
        List<String> users=new ArrayList<>();
        users.add("User2");
        users.add("User3");
        InterfaceSelectUser interfaceSelectUser = new InterfaceSelectUser(users);
        interfaceSelectUser.setAgentToInterface(this);
       System.out.println("demarage de l agent user1");

        addBehaviour(new Behaviour() {
            private int i;
            private String quitter;

            @Override
            public void action() {
                ++i;

                ACLMessage aclMessage=receive();
                if(aclMessage!=null) {
                     if(aclMessage.getContent().equals("start Discussion")){

                         interfaceSelectUser.setInterfaceMessage(aclMessage.getSender().getName().substring(0,5) + " : " + aclMessage.getContent(), false);
                        interfaceSelectUser.fromStartToSendmessage();

                    }

                   else if (aclMessage.getContent().equals("fin")) {
                        quitter="fin";
                        System.out.println(aclMessage.getContent());


                    }
                    else  interfaceSelectUser.setInterfaceMessage(aclMessage.getSender().getName().substring(0,5) + " : " + aclMessage.getContent(), true);

                }


            }

            @Override
            public boolean done() {
                if(quitter=="fin") {
                    interfaceSelectUser.setVisible(false);
                    return true;}
                else return false;



            }
        } );

    }
}
