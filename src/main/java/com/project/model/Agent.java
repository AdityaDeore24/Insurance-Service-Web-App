package com.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Agent {
	@Id
	private long agentMob;
	private String agentName;
	private String agentDob;
	private String agentEmail;
	private String agentPassword;
	private String agentAddress;
	private String agentStatus;
	public long getAgentMob() {
		return agentMob;
	}
	public void setAgentMob(long agentMob) {
		this.agentMob = agentMob;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentDob() {
		return agentDob;
	}
	public void setAgentDob(String agentDob) {
		this.agentDob = agentDob;
	}
	public String getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	public String getAgentPassword() {
		return agentPassword;
	}
	public void setAgentPassword(String agentPassword) {
		this.agentPassword = agentPassword;
	}
	public String getAgentAddress() {
		return agentAddress;
	}
	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}
	public String getAgentStatus() {
		return agentStatus;
	}
	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}
	public Agent(long agentMob, String agentName, String agentDob, String agentEmail, String agentPassword,
			String agentAddress, String agentStatus) {
		super();
		this.agentMob = agentMob;
		this.agentName = agentName;
		this.agentDob = agentDob;
		this.agentEmail = agentEmail;
		this.agentPassword = agentPassword;
		this.agentAddress = agentAddress;
		this.agentStatus = agentStatus;
	}
	public Agent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
