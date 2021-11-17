
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthenticationFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // the code before the FilterChain call is executed before the servlet, during the request
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String  email = (String)session.getAttribute("email");
        
        if(email == null){
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendRedirect("login");
            return; //very important to stop the execution of code
        }
        
        //Will call upon either the next filter or load the requested servlet
        chain.doFilter(request, response);
        
        // the code after the FilterChain call is executed after the servlet, during the response
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
 
    @Override
    public void destroy() {}
    
}
