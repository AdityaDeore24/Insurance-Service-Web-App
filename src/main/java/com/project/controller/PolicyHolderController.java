package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.dao.AgentDao;
import com.project.dao.PolicyHolderDao;
import com.project.dao.PolicyTableDao;
import com.project.model.Agent;
import com.project.model.PolicyHolder;
import com.project.model.PolicyTable;

@Controller
public class PolicyHolderController {
	
//	ApplicationContext context1 = new ClassPathXmlApplicationContext("config.xml");
//	Agent agent = (Agent) context1.getBean("agent");
//	AgentDao agentDao = (AgentDao) context1.getBean("agentDao");
	
	ApplicationContext context = new ClassPathXmlApplicationContext("config1.xml");
	PolicyHolder policyHolder = (PolicyHolder) context.getBean("policyHolder");
	PolicyHolderDao policyHolderDao = (PolicyHolderDao) context.getBean("policyHolderDao");
	
	ApplicationContext context2 = new ClassPathXmlApplicationContext("config2.xml");
	PolicyTable policyTable = (PolicyTable) context2.getBean("policyTable");
	PolicyTableDao policyTableDao = (PolicyTableDao) context2.getBean("policyTableDao");
	
	@RequestMapping("/userLogin")
	public String userLogin()
	{
		return "userLogin";
	}
	
	@RequestMapping("/UserHome")
	public String userHome(Model model)
	{
		model.addAttribute("holderInfo", policyHolder);
		return "UserHome";
	}
	
//	@RequestMapping("/userDetails")
//	public String userDetails()
//	{
//		return "userDetails";
//	}
//	
	@RequestMapping(path="afterUserLogin",method=RequestMethod.POST)
	public String afterUserLogin(Model model,HttpServletRequest request)
	{
		long holderMob = Long.parseLong(request.getParameter("holderMob"));
		String holderPassword = request.getParameter("holderPassword");
		
		try
		{
			policyHolder = policyHolderDao.getHolder(holderMob);
			String password = policyHolder.getHolderPassword();
			
			System.out.println(policyHolder.getHolderName());
			
			System.out.println(policyHolder.getHolderMob());
			
			System.out.println(policyHolder.getHolderPassword());
			if(policyHolder != null)
			{
				if(policyHolder.getHolderPassword().equals(holderPassword))
				{
					System.out.println(policyHolder.getHolderName());
					model.addAttribute("holderInfo", policyHolder);
					return "UserHome"; // PolicyHolderHome
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
		return "error";
	}
	
	@RequestMapping("/userDetails")
	public String userDetails(Model model)
	{
		long holderMob = policyHolder.getHolderMob();
		
		try
		{
			policyHolder = policyHolderDao.getHolder(holderMob);
			List<PolicyTable> policyDetails = policyTableDao.getPoliciesByHolderMob(holderMob);
			System.out.println(policyDetails);
			model.addAttribute("policyDetails", policyDetails);
			model.addAttribute("holderInfo", policyHolder);
			return "userDetails";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
	}
}
