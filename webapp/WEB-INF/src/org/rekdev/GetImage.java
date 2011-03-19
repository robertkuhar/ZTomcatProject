package org.rekdev;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class GetImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static Color[] sColors = new Color[] {
            Color.RED,
            Color.BLUE,
            Color.YELLOW,
            Color.GREEN,
            Color.ORANGE,
            new Color( 128, 0, 128 ) };

    @Override
    protected void doGet( HttpServletRequest pReq, HttpServletResponse pResp ) throws ServletException,
            IOException {
        String zMessage = pReq.getParameter( "message" );
        int zIntOfImageId = deriveIntFromMessage( zMessage );
        int zForegroundColorIx = zIntOfImageId % sColors.length;
        int zBackgroundColorIx = ( zForegroundColorIx + ( sColors.length / 2 ) ) % sColors.length;

        ImageMaker zImageMaker = new ImageMaker();
        BufferedImage zImage = zImageMaker.createImage( 200, 200, sColors[ zForegroundColorIx ],
                sColors[ zBackgroundColorIx ], zMessage );
        pResp.setContentType( "image/jpeg" );
        zImageMaker.saveImage( zImage, pResp.getOutputStream() );
    }

    @Override
    protected void doPost( HttpServletRequest pReq, HttpServletResponse pResp ) throws ServletException,
            IOException {
        doGet( pReq, pResp );
    }

    private int deriveIntFromMessage( String pMessage ) {
        int zInt = 0;
        if ( pMessage != null ) {
            try {
                zInt = Integer.valueOf( pMessage );
            } catch ( Exception e ) {
                zInt = pMessage.hashCode();
            }
        }
        return Math.abs( zInt );
    }
}
