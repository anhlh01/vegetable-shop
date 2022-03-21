/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.filter;

import anhlh.tblUsers.UserDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class NewFilter implements Filter {

    private final List<String> USER;
    private final List<String> ADMIN;
    private final String US = "US";
    private final String AD = "AD";
    private final String GG = "GG";
    private final String LOGIN = "MainController";

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public NewFilter() {
        USER = new ArrayList<>();
        USER.add("about.jsp");
        USER.add("blog-single.jsp");
        USER.add("blog.jsp");
        USER.add("cart.jsp");
        USER.add("checkout.jsp");
        USER.add("contact.jsp");
        USER.add("index.jsp");
        USER.add("login.jsp");
        USER.add("product-single.jsp");
        USER.add("vnpay_index.jsp");
        USER.add("vnpay_ipn.jsp");
        USER.add("vnpay_querydr.jsp");
        USER.add("vnpay_refund.jsp");
        USER.add("vnpay_return.jsp");
        USER.add("vnpay_wishlist.jsp");
//        USER.add("Load a type");
//        USER.add("Load All Product");
//        USER.add("Show Product");
//        USER.add("Add To Cart");
//        USER.add("Delete From Cart");
//        USER.add("Proccess Check Out");
//        USER.add("Check Out");
//        USER.add("Change quantity");
//        USER.add("Search Product");
//        USER.add("VNPay");
//        USER.add("Sign Up");

        ADMIN = new ArrayList<>();
        ADMIN.add("admin.jsp");
        ADMIN.add("UserManager.jsp");
//        ADMIN.add("Add Product");
//        ADMIN.add("Edit Product");
//        ADMIN.add("Delete Product");
//        ADMIN.add("Edit User");
//        ADMIN.add("Delete User");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("NewFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("NewFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request;

            String uri = req.getRequestURI();
            int index = uri.lastIndexOf("/");

            String resource = uri.substring(index + 1);

            String queryString = req.getQueryString();
            if(queryString == null){
                queryString = "";
            }

            HttpSession session = req.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {
                if ("login.jsp".equals(resource)) {
                    if (AD.equals(user.getRoleID())) {
                        res.sendRedirect("admin.jsp");
                    } else if (US.equals(user.getRoleID()) || GG.equals(user.getRoleID())) {
                        res.sendRedirect("MainController");
                    } else {
                        chain.doFilter(request, response);
                    }
                }
            }

            if (ADMIN.contains(resource) || queryString.contains("Add%20Product") || queryString.contains("Delete%20Product")
                    || queryString.contains("Edit%20Product") || queryString.contains("Edit%20User")
                    || queryString.contains("Delete%20User")) {
                if (user != null) {
                    if (user.getRoleID().equals(US) || user.getRoleID().equals(GG)) {
                        session.removeAttribute("LOGIN_USER");
                        res.sendRedirect("login.jsp");
                    } else {
                        chain.doFilter(request, response);
                    }
                } else {
                    res.sendRedirect("login.jsp");
                }

            } else if (USER.contains(resource) || queryString.contains("Load%20a%20type") || queryString.contains("Load%20All%20Product")
                    || queryString.contains("Show%20Product") || queryString.contains("Add%20To%20Cart")
                    || queryString.contains("Delete%20From%20Cart") || queryString.contains("Proccess%20Check%20Out")
                    || queryString.contains("Check%20Out") || queryString.contains("Change%20quantity")
                    || queryString.contains("Search%20Product") || queryString.contains("VNPay")
                    || queryString.contains("Sign%20Up")) {
                if (user != null) {
                    if (user.getRoleID().equals(AD)) {
                        session.removeAttribute("LOGIN_USER");
                        res.sendRedirect("login.jsp");
                    } else {
                        chain.doFilter(request, response);
                    }
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("NewFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("NewFilter()");
        }
        StringBuffer sb = new StringBuffer("NewFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
