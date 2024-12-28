package com.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class PolicyHolder {
		@Id
		private long holderMob;
		private String holderName;
		private String holderDob;
		private String holderEmail;
		private String holderPassword;
		private String holderAddress;
		private Long agentMob;
		public long getHolderMob() {
			return holderMob;
		}
		public void setHolderMob(long holderMob) {
			this.holderMob = holderMob;
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
		public String getHolderEmail() {
			return holderEmail;
		}
		public void setHolderEmail(String holderEmail) {
			this.holderEmail = holderEmail;
		}
		public String getHolderPassword() {
			return holderPassword;
		}
		public void setHolderPassword(String holderPassword) {
			this.holderPassword = holderPassword;
		}
		public String getHolderAddress() {
			return holderAddress;
		}
		public void setHolderAddress(String holderAddress) {
			this.holderAddress = holderAddress;
		}
		public Long getAgentMob() {
			return agentMob;
		}
		public void setAgentMob(Long agentMob) {
			this.agentMob = agentMob;
		}
		public PolicyHolder(long holderMob, String holderName, String holderDob, String holderEmail,
				String holderPassword, String holderAddress, Long agentMob) {
			super();
			this.holderMob = holderMob;
			this.holderName = holderName;
			this.holderDob = holderDob;
			this.holderEmail = holderEmail;
			this.holderPassword = holderPassword;
			this.holderAddress = holderAddress;
			this.agentMob = agentMob;
		}
		public PolicyHolder() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
}
