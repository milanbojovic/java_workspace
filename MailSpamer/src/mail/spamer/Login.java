package mail.spamer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mail.spamer.User;;

@WebServlet(description = "Login servlet", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public User[] users;
	
	public void init(){
		
		users = new User[7];
		
		users[0] = new User("bojomil", "Performance1!");
		users[1] = new User("vuckale", "Performance1!");
		users[2] = new User("stojmil", "Performance1!");
		users[3] = new User("horvbra", "Performance1!");
		users[4] = new User("majssrd", "Performance1!");
		users[5] = new User("ilicjov", "Performance1!");
		users[6] = new User("nikonen", "Performance1!");
	}
    

	public boolean userLogin(String username, String password){
		boolean result = false;
		
		for ( User usr : users) {
			if (usr.getUsername().equals(username) && usr.getPassword().equals(password)){
				result = true;
				break;
			}		
		}
		return result;
	}
	
	
	public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		if(userLogin(name, pass)){
			
			response.sendRedirect("spamer.html");		
		}
		else{
			response.sendRedirect("loginFailed.html");
		}
	}

}
