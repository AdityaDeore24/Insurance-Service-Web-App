package com.project.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.PolicyHolder;
import com.project.model.PolicyTable;

@Component
public class PolicyTableDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public PolicyTableDao(HibernateTemplate hibernateTemplate) {
		super();
		this.hibernateTemplate = hibernateTemplate;
	}

	public PolicyTableDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	public Long addPolicy(PolicyTable policyTable)
	{
		Long i = (Long) hibernateTemplate.save(policyTable);
		return i;
	}
	
	
	public List<PolicyTable> viewAllPolicies()
	{
		List<PolicyTable> policyList = hibernateTemplate.loadAll(PolicyTable.class);
		return policyList;
	}
	
	@Transactional
	public List<PolicyTable> getPoliciesByHolderMob(long holderMob) {
	    String hql = "FROM PolicyTable pt WHERE pt.holderMob = :holderMob";
	    List<PolicyTable> policyDetails = (List<PolicyTable>) hibernateTemplate
	            .findByNamedParam(hql, "holderMob", holderMob);
	    return policyDetails;
	}
	
	public PolicyTable getOnePolicy(long policyId)
	{
		PolicyTable policyTable = hibernateTemplate.get(PolicyTable.class, policyId);
		return policyTable;
	}
	
	@Transactional
	public void updatePolicyTable(PolicyTable policyTable)
	{
		hibernateTemplate.update(policyTable);
	}
}
