package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.project.dao.AgentDao;
import com.project.model.Agent;

@Controller
public class HomeController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
	Agent agent = (Agent) context.getBean("agent");
	AgentDao agentDao = (AgentDao) context.getBean("agentDao");
	
	@RequestMapping("/")
	public String homePage()
	{
		System.out.println("Opening index page");
		return "index";
	}
	
	@RequestMapping("/adminDashBoard")
	public String adminDash()
	{
		System.out.println("Opening admin dashboard page");
		return "adminDashboard";
	}
	
	@RequestMapping("/userDashBoard")
	public String userDash()
	{
		System.out.println("Opening user dashboard page");
		return "userDashboard";
	}
	
	@RequestMapping("/adminLogin")
	public String adminLogin()
	{
		System.out.println("Opening admin Login page");
		return "adminLogin";
	}
	
	@RequestMapping("/adminHome")
	public String adminHome()
	{
		System.out.println("Opening Admin Home page");
		return "adminHome";
	}
	
	@RequestMapping("/approveAgent")
	public String approveAgent()
	{
		System.out.println("Opening Approve Agent page");
		return "approveAgent";
	}
	
	
	@RequestMapping("/disapprovedAgent")
	public String disapprovedAgent()
	{
		System.out.println("Opening Disapproved Agent page");
		return "disapprovedAgent";
	}
	
	@RequestMapping("/pendingAgent")
	public String pendingAgent()
	{
		System.out.println("Opening Pending Agent page");
		return "pendingAgent";
	}
	
	@RequestMapping(path="/afterAdminLogin",method=RequestMethod.POST)
	public String adminLogin(HttpServletRequest request)
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email.equals("admin@gmail.com") && password.equals("admin"))
		{
			return "adminHome";
		}
		else{
			return "adminDashboard";
		}
	}
	
	@RequestMapping("/viewApprovationTable")
	public String getAllAgent(Model m)
	{
		try
		{
			List<Agent> agentList = agentDao.getPendingAgent();
			m.addAttribute("agentList", agentList);
			return "approveAgent";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error";
		}
	}
	
	@RequestMapping(path="/approve/{agentMob}",method=RequestMethod.GET)
	public RedirectView approve(@PathVariable("agentMob") long agentMob , HttpServletRequest request)
	{
		agent = agentDao.getOneAgent(agentMob);
		String agentStatus = agent.getAgentStatus();
		agentStatus = "Approved";
		agent.setAgentStatus(agentStatus);
		agentDao.updateAgent(agent);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/viewApprovationTable");
		return redirectView;
	}
	
	@RequestMapping(path="/disapprove/{agentMob}",method=RequestMethod.GET)
	public RedirectView disapprove(@PathVariable("agentMob") long agentMob , HttpServletRequest request)
	{
		agent = agentDao.getOneAgent(agentMob);
		String agentStatus = agent.getAgentStatus();
		agentStatus = "Disapproved";
		agent.setAgentStatus(agentStatus);
		agentDao.updateAgent(agent);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/viewApprovationTable");
		return redirectView;
	}
	
	// for approvedAgent.jsp and mapping- approvedAgentTable

	@RequestMapping("/approvedAgentTable")
	public String approvedAgentTable(Model m)
	{
		try
		{
			List<Agent> agentList = agentDao.getApprovedAgent();
			m.addAttribute("agentList", agentList);
			return "approvedAgent";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error";
		}
	}
	
	@RequestMapping(path="/disapproval/{agentMob}",method=RequestMethod.GET)
	public RedirectView disapproval(@PathVariable("agentMob") long agentMob , HttpServletRequest request)
	{
		agent = agentDao.getOneAgent(agentMob);
		String agentStatus = agent.getAgentStatus();
		agentStatus = "Disapproved";
		agent.setAgentStatus(agentStatus);
		agentDao.updateAgent(agent);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/approvedAgentTable");
		return redirectView;
	}
	
	// for disApprovedAgent.jsp and mapping- disapprovedAgentTable
	@RequestMapping("/disapprovedAgentTable")
	public String disapprovedAgentTable(Model m)
	{
		try
		{
			List<Agent> agentList = agentDao.getDisapprovedAgent();
			m.addAttribute("agentList", agentList);
			return "disapprovedAgent";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error";
		}
	} 
	
	@RequestMapping(path="/approval/{agentMob}",method=RequestMethod.GET)
	public RedirectView approval(@PathVariable("agentMob") long agentMob , HttpServletRequest request)
	{
		agent = agentDao.getOneAgent(agentMob);
		String agentStatus = agent.getAgentStatus();
		agentStatus = "Approved";
		agent.setAgentStatus(agentStatus);
		agentDao.updateAgent(agent);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/disapprovedAgentTable");
		return redirectView;
	}
	
	@RequestMapping(path="/deletion/{agentMob}",method=RequestMethod.GET)
	public RedirectView deletion(@PathVariable("agentMob") long agentMob , HttpServletRequest request)
	{
		agentDao.deleteAgent(agentMob);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/disapprovedAgentTable");
		return redirectView;
	}
	
	// for pendingAgent.jsp and mapping- disapprovedAgentTable
	@RequestMapping("/pendingAgentTable")
	public String pendingAgentTable(Model m)
	{
		try
		{
			List<Agent> agentList = agentDao.getPendingAgent();
			m.addAttribute("agentList", agentList);
			return "pendingAgent";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error";
		}
	} 
	
	@RequestMapping(path="/approvation/{agentMob}",method=RequestMethod.GET)
	public RedirectView approvation(@PathVariable("agentMob") long agentMob , HttpServletRequest request)
	{
		agent = agentDao.getOneAgent(agentMob);
		String agentStatus = agent.getAgentStatus();
		agentStatus = "Approved";
		agent.setAgentStatus(agentStatus);
		agentDao.updateAgent(agent);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/pendingAgentTable");
		return redirectView;
	}
	
	@RequestMapping(path="/remove/{agentMob}",method=RequestMethod.GET)
	public RedirectView remove(@PathVariable("agentMob") long agentMob , HttpServletRequest request)
	{
		agentDao.deleteAgent(agentMob);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/pendingAgentTable");
		return redirectView;
	}
}
