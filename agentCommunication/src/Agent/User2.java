package Agent;

import Contenuer.InterfaceGraphique.InterfaceSelectUser;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;

public class User2 extends Agent {
    private InterfaceSelectUser interfaceSelectUser;


    @Override
    protected void setup() {

        List<String> users=new ArrayList<>();
        users.add("User1");
        users.add("User3");
        InterfaceSelectUser interfaceSelectUser = new InterfaceSelectUser(users);
        interfaceSelectUser.setAgentToInterface(this);
        System.out.println("demarage de l agent user2");
        ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
        addBehaviour(parallelBehaviour);



        parallelBehaviour.addSubBehaviour(new Behaviour() {
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
