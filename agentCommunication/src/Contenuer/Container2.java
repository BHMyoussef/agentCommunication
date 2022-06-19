package Contenuer;

import Agent.User1;
import Agent.User2;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Container2 {
    public static void main(String []args) throws StaleProxyException {
        Runtime runtime=Runtime.instance();
        Profile profile = new ProfileImpl(false);
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController agentController =  agentContainer.createNewAgent("user2", User2.class.getName(),new Object[]{});
        agentController.start();

    }

}
