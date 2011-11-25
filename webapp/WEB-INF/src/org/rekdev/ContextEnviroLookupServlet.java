package org.rekdev;

import java.io.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ContextEnviroLookupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
            IOException {
        resp.setContentType( "text/html" );
        PrintWriter zOut = resp.getWriter();
        zOut.println( "<head><title>JNDILookup</title></head>" );
        zOut.println( "<body>" );

        Context zInitCtx = null;
        Context zEnvCtx = null;
        try {
            String zContextBob = (String) this.getServletContext().getInitParameter( "contextBob" );

            zInitCtx = new InitialContext();
            zEnvCtx = (Context) zInitCtx.lookup( "java:/comp/env" );
            String zEnviroBob = (String) zEnvCtx.lookup( "enviroBob" );

            zOut.println( "contextBob&nbsp;=&nbsp;" + zContextBob + "<br/>" );
            zOut.println( "enviroBob&nbsp;=&nbsp;" + zEnviroBob + "<br/> " );
        } catch ( NamingException e ) {
            throw new ServletException( e );
        }
        zOut.println( "</body>" );
    }

}
