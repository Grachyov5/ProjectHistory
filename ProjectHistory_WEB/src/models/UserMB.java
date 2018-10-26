package models;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.*;
import javax.servlet.http.HttpServletRequest;

import com.sun.jmx.snmp.ThreadContext;




@SuppressWarnings("deprecation")
@ManagedBean(name = "userMB")
@SessionScoped
public class UserMB implements Serializable {
		
	

	private static final long serialVersionUID = -4118144519094007627L;
	
	//private User user;

	private String userName;
		
	/*@EJB
	private UserServiceBeanLocal userService;*/
		
	@PostConstruct
    public void init() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		this.userName = context.getUserPrincipal().getName();
	//	this.user = userService.find(this.userName);
	}

	/*public User getUser() 
	{
		return this.user;
	}*/

	public boolean isUserAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}

	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

}
