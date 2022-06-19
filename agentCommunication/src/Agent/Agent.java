package Agent;

import jade.core.AID;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import java.util.Map;

public class Agent extends GuiAgent {

    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
        switch (guiEvent.getType()){
            case 1:
                Map<String,Object> params= (Map<String, Object>) guiEvent.getParameter(0);

                String message= (String) params.get("message");
                String agent= (String) params.get("agent");
                ACLMessage aclMessage=new ACLMessage(ACLMessage.REQUEST);
                aclMessage.addReceiver(new AID(agent,AID.ISLOCALNAME));
                aclMessage.setContent(message);
                send(aclMessage);
        }

    }
}
