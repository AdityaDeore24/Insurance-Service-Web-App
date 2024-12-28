package com.project.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.project.dao.AgentDao;
import com.project.model.Agent;
import com.project.model.PolicyHolder;
import com.project.model.PolicyTable;
import com.project.dao.PolicyHolderDao;
import com.project.dao.PolicyTableDao;

@Controller
public class AgentController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
	Agent agent = (Agent) context.getBean("agent");
	AgentDao agentDao = (AgentDao) context.getBean("agentDao");
	
	ApplicationContext context1 = new ClassPathXmlApplicationContext("config1.xml");
	PolicyHolder policyHolder = (PolicyHolder) context1.getBean("policyHolder");
	PolicyHolderDao policyHolderDao = (PolicyHolderDao) context1.getBean("policyHolderDao");
	
	ApplicationContext context2 = new ClassPathXmlApplicationContext("config2.xml");
	PolicyTable policyTable = (PolicyTable) context2.getBean("policyTable");
	PolicyTableDao policyTableDao = (PolicyTableDao) context2.getBean("policyTableDao");

	@RequestMapping("/agentDashboard")
	public String agentDashboard()
	{
		System.out.println("Opening agent dashboard page");
		return "agentDashboard";
	}
	
	@RequestMapping("/agentRegister")
	public String agentRegister()
	{
		System.out.println("Opening agent register page");
		return "agentRegister";
	}
	
	@RequestMapping("/agentLogin")
	public String agentLogin()
	{
		System.out.println("Opening agent login page");
		return "agentLogin";
	}
	
	
	@RequestMapping("/successfulRegister")
	public String successfulRegister()
	{
		return "successfulRegister";
	}
	
	@RequestMapping(path="/afterAddNewAgent",method=RequestMethod.POST)
	public String addNewAgent(HttpServletRequest request)
	{
		

		long agentMob = Long.parseLong(request.getParameter("agentMob"));
		String agentName = request.getParameter("agentName");
		String agentDob = request.getParameter("agentDob");
		String agentEmail = request.getParameter("agentEmail");
		String agentPassword = request.getParameter("agentPassword");
		String agentAddress = request.getParameter("agentAddress");
		String agentStatus = "Pending";
		
		agent.setAgentMob(agentMob);
		agent.setAgentName(agentName);
		agent.setAgentDob(agentDob);
		agent.setAgentEmail(agentEmail);
		agent.setAgentPassword(agentPassword);
		agent.setAgentAddress(agentAddress);
		agent.setAgentStatus(agentStatus);
		

		try
		{
			agentDao.addAgent(agent);
			return "agentDashboard";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error";
		}
	}
	
	// for appoveAgent.jsp and mapping-approvedAgentTable
	
	
	
	@RequestMapping(path="/afterAgentLogin",method=RequestMethod.POST)
	public String afterAgentLogin(Model m,HttpServletRequest request)
	{
		long agentMob = Long.parseLong(request.getParameter("agentMob"));
		String agentPassword = request.getParameter("agentPassword");
		try{
		agent = agentDao.getOneAgent(agentMob);
		String password = agent.getAgentPassword();
		String status = agent.getAgentStatus();
		
		if(agent != null)
		{
			if(agentPassword.equals(password) && status.equals("Approved"))
			{
				m.addAttribute("agentInfo", agent);
				return "agentHome";
			}
			else
			{
				return "agentDashboard";
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
		
		return "agentHome";
	}
	

	@RequestMapping("/addPolicyHolder")
	public String addPolicyHolder(Model m)
	{
		
		
		long agentMob = agent.getAgentMob();
		agent.setAgentMob(agentMob);
		String agentName = agent.getAgentName();
		agent.setAgentName(agentName);
		m.addAttribute("agentInfo", agent);
		System.out.println("Open addNewPolicyHolder page");
		return "addPolicyHolder";
	}
	
	@RequestMapping("/agentHome")
	public String agentHome(Model m)
	{
		return "agentHome";
	}
	
	@RequestMapping(path="/afterAddPolicyHolder",method=RequestMethod.POST)
	public String addNewPolicyHolder(Model m,HttpServletRequest request)
	{
		long holderMob = Long.parseLong(request.getParameter("holderMob"));
		String holderName = request.getParameter("holderName");
		String holderDob = request.getParameter("holderDob");
		String holderEmail = request.getParameter("holderEmail");
		String holderPassword = request.getParameter("holderPassword");
		String holderAddress = request.getParameter("holderAddress");
		long agentMob = Long.parseLong(request.getParameter("agentMob"));
		
		
		policyHolder.setHolderMob(holderMob);
		policyHolder.setHolderName(holderName);
		policyHolder.setHolderDob(holderDob);
		policyHolder.setHolderEmail(holderEmail);
		policyHolder.setHolderPassword(holderPassword);
		policyHolder.setHolderAddress(holderAddress);
		policyHolder.setAgentMob(agentMob);
		
		try
		{	
			agent = agentDao.getOneAgent(agentMob);
			String agentName = agent.getAgentName();
			agent.setAgentName(agentName);
			m.addAttribute("agentInfo", agent);
			policyHolderDao.addPolicyHolder(policyHolder);
			return "agentHome";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping("/viewAllPolicyHolder")
	public String viewAllPolicyHolder(Model m)
	{

		long agentMob = agent.getAgentMob();
		agent.setAgentMob(agentMob);
		System.out.println("id"+agent.getAgentMob());
		String agentName = agent.getAgentName();
		agent.setAgentName(agentName);
		m.addAttribute("agentInfo", agent);
		 try
		 {
			 List<PolicyHolder> holders = policyHolderDao.getAllPolicyHoldersByAgentId(agentMob);
			 m.addAttribute("policyHolderList", holders);
			 
			 long agentMob1 = agent.getAgentMob();
				agent.setAgentMob(agentMob1);
				System.out.println("id"+agent.getAgentMob());
				String agentName1 = agent.getAgentName();
				agent.setAgentName(agentName1);
				m.addAttribute("agentInfo", agent);
				
			 return "viewAllPolicyHolder";
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 return "error";
		 }
	}
	

	@RequestMapping(path="/removal/{holderMob}",method=RequestMethod.GET)
	public RedirectView removal(@PathVariable("holderMob") long holderMob , HttpServletRequest request)
	{
		policyHolderDao.deletePolicyHolder(holderMob);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/viewAllPolicyHolder");
		return redirectView;
	}
	
	@RequestMapping("/updateEmailPassword")
	public String updateEmailPassword(Model m)
	{
		long agentMob = agent.getAgentMob();
		agent.setAgentMob(agentMob);
		String agentName = agent.getAgentName();
		agent.setAgentName(agentName);
		m.addAttribute("agentInfo", agent);
		System.out.println("Open Update email and password page");
		return "updateEmailPassword";
	}
	
	@RequestMapping(path="/afterUpdate",method=RequestMethod.POST)
	public String updateEmailAndPassword(Model m,HttpServletRequest request)
	{
		long holderMob = Long.parseLong(request.getParameter("holderMob"));
		String holderEmail = request.getParameter("holderEmail");
		String holderPassword = request.getParameter("holderPassword");
		
		try
		{
			policyHolder = policyHolderDao.getHolder(holderMob);
			policyHolder.setHolderEmail(holderEmail);
			policyHolder.setHolderPassword(holderPassword);
			policyHolderDao.updatePolicyHolder(policyHolder);
			
			long agentMob = agent.getAgentMob();
			agent.setAgentMob(agentMob);
			String agentName = agent.getAgentName();
			agent.setAgentName(agentName);
			m.addAttribute("agentInfo", agent);
			
			return "agentHome";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping("/addPolicy")
	public String addPolicy(Model m)
	{
		m.addAttribute("agentInfo", agent);
		System.out.println("Open add policy page");
		return "addPolicy";
	}
	
	@RequestMapping(path="/afterAddPolicy",method=RequestMethod.POST)
	public String afterAddPolicy(Model m,HttpServletRequest request)
	{
		long policyId = 0;
		String holderName = request.getParameter("holderName");
		String holderDob = request.getParameter("holderDob");
		long holderMob = Long.parseLong(request.getParameter("holderMob"));
		String holderEmail = request.getParameter("holderEmail");
		long agentMob = Long.parseLong(request.getParameter("agentMob"));
		String policyTitle = request.getParameter("policyTitle");
		String policyDetails = request.getParameter("policyDetails");
		String policyDueDate = request.getParameter("policyDueDate");
		System.out.println("entered due date : "+policyDueDate);
		LocalDate dueDate = LocalDate.parse(policyDueDate);
		System.out.println("due date : "+dueDate);
		try {	
		policyTable.setPolicyId(policyId);
		policyTable.setHolderName(holderName);
		policyTable.setHolderDob(holderDob);
		policyTable.setHolderMob(holderMob);
		policyTable.setHolderEmail(holderEmail);
		policyTable.setAgentMob(agentMob);
		policyTable.setPolicyTitle(policyTitle);
		policyTable.setPolicyDetails(policyDetails);
		policyTable.setPolicyDueDate(dueDate);
		
		
		policyHolder = policyHolderDao.getHolder(holderMob);
		long hMob = policyHolder.getHolderMob();
		if(policyHolder != null)
		{
			if(holderMob == hMob ) // we can take it for all attributes of policyholder
			{
				policyTableDao.addPolicy(policyTable);
				m.addAttribute("agentInfo", agent);
				return "agentHome";
			}
		}
		return "agentHome";
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping("/viewPolicy")
	public String viewPolicy(Model m)
	{
		m.addAttribute("agentInfo", agent);
		return "viewPolicy";
	}
	
	@RequestMapping("/policyDetails")
	public String policyDetails(Model m)
	{
		m.addAttribute("agentInfo", agent);
		System.out.println("Open policyDetail page");
		return "PolicyDetails";
	}
	
	@RequestMapping(path="/afterViewPolicy",method=RequestMethod.POST)
	public String afterViewPolicy(Model m,HttpServletRequest request)
	{
		long holderMob = Long.parseLong(request.getParameter("holderMob"));
		long agentMob = Long.parseLong(request.getParameter("agentMob"));
		
		List<PolicyTable> tables = policyTableDao.viewAllPolicies();
		List<PolicyTable> table = new ArrayList<PolicyTable>();
//		policyHolder = policyHolderDao.getHolder(holderMob);
		
		
		
		for(PolicyTable pt : tables)
		{
			if(pt.getHolderMob() == holderMob && pt.getAgentMob() == agentMob)
			{
				table.add(pt);
			}
		}
		
		m.addAttribute("agentInfo", agent);
		m.addAttribute("table", table);
		return "PolicyDetails";
	}
	
	@RequestMapping("/viewExpiredPolicy")
	public String viewExpiredPolicy(Model m)
	{

		List<PolicyTable> tables = policyTableDao.viewAllPolicies();
		List<PolicyTable> table = new ArrayList<PolicyTable>();
		LocalDate currentDate = LocalDate.now();
		System.out.println("Current Date : "+currentDate);
		
		for(PolicyTable pt : tables)
		{
			LocalDate dueDate = pt.getPolicyDueDate();
			int result = currentDate.compareTo(dueDate);
			System.out.println("result : "+result);
			if(result > 0)
			{
				table.add(pt);
			}		
		}
		m.addAttribute("agentInfo", agent);
		m.addAttribute("table" , table);
		return "viewExpiredPolicy";
	}
	 
	@RequestMapping("/viewNearbyExpiries")
	public String viewNearbyExpiries(Model m)
	{
		List<PolicyTable> tables = policyTableDao.viewAllPolicies();
		List<PolicyTable> table = new ArrayList<PolicyTable>();
		LocalDate currentDate = LocalDate.now();
		System.out.println("Current Date : "+currentDate);		
		LocalDate newDate = currentDate.plusMonths(1);
		System.out.println("Next Month : "+newDate);
		
		for(PolicyTable pt : tables)
		{
			LocalDate dueDate = pt.getPolicyDueDate();
			long daysDifference = ChronoUnit.DAYS.between(newDate,dueDate);
			System.out.println("difference : "+daysDifference);
			if(daysDifference >= -1 || daysDifference <= -30)
			{
				
			}
			else
			{
				table.add(pt);
			}
		}
		m.addAttribute("agentInfo", agent);
		m.addAttribute("table", table);
		return "viewNearbyExpiries";
	}
	
	@RequestMapping("/updateDueDate")
	public String updateDueDate(Model m)
	{
		
		m.addAttribute("agentInfo", agent);
		System.out.println("Open update Due Date page");
		return "updateDueDate";
	}
	
	@RequestMapping(path="/afterUpdateDueDate",method=RequestMethod.POST)
	public String afterUpdateDueDate(Model m ,HttpServletRequest request)
	{
		long policyId = Long.parseLong(request.getParameter("policyId"));
		String policyDueDate = request.getParameter("policyDueDate");
		
		try
		{
			policyTable = policyTableDao.getOnePolicy(policyId);
			LocalDate newDueDate = LocalDate.parse(policyDueDate);
			policyTable.setPolicyDueDate(newDueDate);
			policyTableDao.updatePolicyTable(policyTable);
			m.addAttribute("agentInfo", agent);
			return "agentHome";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
}
