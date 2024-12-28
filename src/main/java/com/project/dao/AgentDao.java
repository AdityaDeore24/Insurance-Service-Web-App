 package com.project.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Agent;

@Component
public class AgentDao {
	
	private HibernateTemplate hibernateTemplate;

	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public AgentDao(HibernateTemplate hibernateTemplate) {
		super();
		this.hibernateTemplate = hibernateTemplate;
	}

	

	public AgentDao() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}



	@Transactional
	public Long addAgent(Agent agent)
	{
		Long i = (Long) hibernateTemplate.save(agent);
		return i;
	}
	
	public Agent getOneAgent(long agentMob)
	{
		Agent agent = hibernateTemplate.get(Agent.class, agentMob);
		return agent;
	}
	
	public List<Agent> viewAllAgent()
	{
		List<Agent> agentList = hibernateTemplate.loadAll(Agent.class);
		
		return agentList;
	}
	
	@Transactional
    public List<Agent> getApprovedAgent() {
        String hql = "FROM Agent a WHERE a.agentStatus = 'Approved'";
        List<Agent> approvedAgents = (List<Agent>) hibernateTemplate.find(hql);
        return approvedAgents;
    }
	
	@Transactional
    public List<Agent> getDisapprovedAgent() {
        String hql = "FROM Agent a WHERE a.agentStatus = 'Disapproved'";
        List<Agent> disapprovedAgents = (List<Agent>) hibernateTemplate.find(hql);
        return disapprovedAgents;
    }
	
	@Transactional
    public List<Agent> getPendingAgent() {
        String hql = "FROM Agent a WHERE a.agentStatus = 'Pending'";
        List<Agent> pendingAgents = (List<Agent>) hibernateTemplate.find(hql);
        return pendingAgents;
    }
	
	@Transactional
	public void deleteAgent(long agentMob)
	{
		Agent agent = hibernateTemplate.get(Agent.class, agentMob);
		hibernateTemplate.delete(agent);
	}
	
	@Transactional
	public void updateAgent(Agent agent)
	{
		hibernateTemplate.update(agent);
	}
}
