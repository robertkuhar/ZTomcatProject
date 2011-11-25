package org.rekdev;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class EchoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
            IOException {
        echo( req, resp );
    }

    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException,
            IOException {
        echo( req, resp );
    }

    private void echo( HttpServletRequest pReq, HttpServletResponse pResp ) throws ServletException,
            IOException {
        pResp.setContentType( "text/html" );
        PrintWriter zOut = pResp.getWriter();
        zOut.println( "<head><title>Echo</title></head>" );
        zOut.println( "<body>" );

        String zReqURL = pReq.getRequestURL().toString();
        String zQuery = pReq.getQueryString();
        zOut.println( zReqURL + ( ( zQuery != null && !"".equals( zQuery ) ) ? "?" + zQuery : "" ) );

        zOut.println( "</body>" );
    }
}
