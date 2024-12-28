package com.project.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Agent;
import com.project.model.PolicyHolder;

@Component
public class PolicyHolderDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public PolicyHolderDao(HibernateTemplate hibernateTemplate) {
		super();
		this.hibernateTemplate = hibernateTemplate;
	}

	public PolicyHolderDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	public Long addPolicyHolder(PolicyHolder policyHolder)
	{
		Long i = (Long) hibernateTemplate.save(policyHolder);
		return i;
	}
		
	public List<PolicyHolder> getAllPolicyHolder()
	{
		List<PolicyHolder> policyHoldersList = hibernateTemplate.loadAll(PolicyHolder.class);
		return policyHoldersList;
	}
	
	@Transactional
	public void deletePolicyHolder(long holderMob)
	{
		PolicyHolder policyHolder = hibernateTemplate.get(PolicyHolder.class, holderMob);
		hibernateTemplate.delete(policyHolder);
	}
	
	public PolicyHolder getHolder(long holderMob )
	{
		PolicyHolder account = hibernateTemplate.get(PolicyHolder.class, holderMob);
		return account;
	}
	
	@Transactional
	public void updatePolicyHolder(PolicyHolder policyHolder)
	{
		hibernateTemplate.update(policyHolder);
	}
	
	@Transactional
	public List<PolicyHolder> getAllPolicyHoldersByAgentId(long agentMob) {
	    String hql = "FROM PolicyHolder ph WHERE ph.agentMob = :agentMob";
	    List<PolicyHolder> policyHolders = (List<PolicyHolder>) hibernateTemplate
	            .findByNamedParam(hql, "agentMob", agentMob);
	    return policyHolders;
	}
}
