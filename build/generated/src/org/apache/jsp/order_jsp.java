package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class order_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/navbar.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/bookList.css\"/>\t\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/navBar.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("<nav class=\"navbar navbar-inverse\">\n");
      out.write("    <div class=\"container-fluid\">\n");
      out.write("        <div class=\"navbar-header\">               \n");
      out.write("            <li>\n");
      out.write("                <a class=\"navbar-brand\" href=\"index.jsp\">ISD Movies</a>\n");
      out.write("            </li>         \n");
      out.write("        </div>\n");
      out.write("        <ul class=\"nav navbar-nav\">                 \n");
      out.write("            <li class=\"categories\">\n");
      out.write("                <a id=\"hometext\" href=\"index.jsp\">Categories</a>\n");
      out.write("            </li>   \n");
      out.write("            <li class=\"search\">\n");
      out.write("                <a id=\"hometext\" href=\"index.jsp\">&#x1F50D;</a>\n");
      out.write("            </li>   \n");
      out.write("        </ul>\n");
      out.write("        <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("            <li class=\"register\">\n");
      out.write("                <a id=\"registertext\" href=\"register.jsp\">\n");
      out.write("                    <span class=\"glyphicon glyphicon-user\"> Register</span>\n");
      out.write("                </a>\n");
      out.write("            </li>\n");
      out.write("            <li class=\"login\">\n");
      out.write("                <a id=\"logintext\" href=\"login.jsp\">\n");
      out.write("                    <span class=\"glyphicon glyphicon-log-in\"> Login</span>\n");
      out.write("                </a>\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("</nav>");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Order</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div style=\"display: flex; flex-direction: column; padding-left: 4rem; padding-right: 4rem\">\n");
      out.write("            <h1 style=\"margin-bottom: 4rem\">Your Order</h1>\n");
      out.write("            <div style=\"display: flex\">\n");
      out.write("                <div class=\"card\" style=\"width: 50%; margin-right: 4rem\">\n");
      out.write("                    <ul class=\"list-group list-group-flush\">\n");
      out.write("                      <li class=\"list-group-item\" style=\"display: flex;\">\n");
      out.write("                          <div style=\"flex-grow: 1\">\n");
      out.write("                              <h4>Movie Title</h4>\n");
      out.write("                              <p>$20.50</p>\n");
      out.write("                          </div>\n");
      out.write("                          <div style=\"display: flex; align-items: center\">\n");
      out.write("                              <button type=\"button\" class=\"btn btn-danger\">\n");
      out.write("                                  Remove\n");
      out.write("                              </button>\n");
      out.write("                          </div>\n");
      out.write("                      </li>\n");
      out.write("                      <li class=\"list-group-item\" style=\"display: flex;\">\n");
      out.write("                          <div style=\"flex-grow: 1\">\n");
      out.write("                              <h4>Movie Title</h4>\n");
      out.write("                              <p>$20.50</p>\n");
      out.write("                          </div>\n");
      out.write("                          <div style=\"display: flex; align-items: center\">\n");
      out.write("                              <button type=\"button\" class=\"btn btn-danger\">\n");
      out.write("                                  Remove\n");
      out.write("                              </button>\n");
      out.write("                          </div>\n");
      out.write("                      </li>\n");
      out.write("                      <li class=\"list-group-item\" style=\"display: flex;\">\n");
      out.write("                          <div style=\"flex-grow: 1\">\n");
      out.write("                              <h4>Movie Title</h4>\n");
      out.write("                              <p>$20.50</p>\n");
      out.write("                          </div>\n");
      out.write("                          <div style=\"display: flex; align-items: center\">\n");
      out.write("                              <button type=\"button\" class=\"btn btn-danger\">\n");
      out.write("                                  Remove\n");
      out.write("                              </button>\n");
      out.write("                          </div>\n");
      out.write("                      </li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"panel panel-default\" style=\"width: 50%;  padding-left: 1rem; padding-right: 1rem; display: flex;\">\n");
      out.write("                    <div class=\"panel-body\" style=\"display: flex; flex-direction: column; flex-grow: 1\">\n");
      out.write("                        <h3 style=\"margin-top: 1rem; margin-bottom: 2rem;\">Order details</h3>\n");
      out.write("                        <div style=\"display: flex; flex-grow: 1\">\n");
      out.write("                            <p style=\"flex-grow: 1\">Total price</p>\n");
      out.write("                            <p>$61.50</p>\n");
      out.write("                        </div>\n");
      out.write("                        <div style=\"display: flex; justify-content: flex-end\">\n");
      out.write("                            <button type=\"button\" class=\"btn btn-danger\" style=\"margin-right: 1rem\">\n");
      out.write("                                Cancel\n");
      out.write("                            </button>\n");
      out.write("                            <button type=\"button\" class=\"btn btn-primary\">\n");
      out.write("                                Submit order\n");
      out.write("                            </button>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
