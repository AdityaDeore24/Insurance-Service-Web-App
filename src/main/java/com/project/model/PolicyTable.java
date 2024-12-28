package com.project.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class PolicyTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long policyId;
	private String holderName;
	private String holderDob;
	private  long holderMob;
	private String holderEmail;
	private long agentMob;
	private String policyTitle;
	private String policyDetails;
	private LocalDate policyDueDate;
	
	@Override
	public String toString() {
		return "PolicyTable [policyId=" + policyId + ", holderName=" + holderName + ", holderDob=" + holderDob
				+ ", holderMob=" + holderMob + ", holderEmail=" + holderEmail + ", agentMob=" + agentMob
				+ ", policyTitle=" + policyTitle + ", policyDetails=" + policyDetails + ", policyDueDate="
				+ policyDueDate + "]";
	}
	public PolicyTable(long policyId, String holderName, String holderDob, long holderMob, String holderEmail,
			long agentMob, String policyTitle, String policyDetails, LocalDate policyDueDate) {
		super();
		this.policyId = policyId;
		this.holderName = holderName;
		this.holderDob = holderDob;
		this.holderMob = holderMob;
		this.holderEmail = holderEmail;
		this.agentMob = agentMob;
		this.policyTitle = policyTitle;
		this.policyDetails = policyDetails;
		this.policyDueDate = policyDueDate;
	}
	public LocalDate getPolicyDueDate() {
		return policyDueDate;
	}
	public void setPolicyDueDate(LocalDate policyDueDate) {
		this.policyDueDate = policyDueDate;
	}

	public long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(long policyId) {
		this.policyId = policyId;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getHolderDob() {
		return holderDob;
	}
	public void setHolderDob(String holderDob) {
		this.holderDob = holderDob;
	}
	public long getHolderMob() {
		return holderMob;
	}
	public void setHolderMob(long holderMob) {
		this.holderMob = holderMob;
	}
	public String getHolderEmail() {
		return holderEmail;
	}
	public void setHolderEmail(String holderEmail) {
		this.holderEmail = holderEmail;
	}
	public long getAgentMob() {
		return agentMob;
	}
	public void setAgentMob(long agentMob) {
		this.agentMob = agentMob;
	}
	public String getPolicyTitle() {
		return policyTitle;
	}
	public void setPolicyTitle(String policyTitle) {
		this.policyTitle = policyTitle;
	}
	public String getPolicyDetails() {
		return policyDetails;
	}
	public void setPolicyDetails(String policyDetails) {
		this.policyDetails = policyDetails;
	}
	
	public PolicyTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
