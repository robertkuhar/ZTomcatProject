package org.rekdev;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

public class ImageMaker {
    /**
     * Gins up a BufferedImage size of width, height, paints a big rectangle
     * with the background color, paints a circle in the middle with the
     * foreground color, and paints the message somewhere about the middle on
     * the bottom.
     * 
     * @param width
     * @param height
     * @param foreground
     * @param background
     * @param message
     * @return
     */
    public BufferedImage createImage( int width, int height, Color foreground, Color background,
            String message ) {
        BufferedImage img = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        img.createGraphics();
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setColor( background );
        g.fillRect( 0, 0, width, height );
        g.setColor( foreground );
        g.fillOval( width / 4, height / 4, width / 2, height / 2 );
        g.setPaint( Color.BLACK );
        if ( message != null ) {
            g.setFont( new Font( "Serif", Font.PLAIN, 12 ) );
            int messageX = (int) ( ( width / 2 ) - ( ( message.length() * 12 ) / 2 ) );
            messageX = ( messageX >= 0 ) ? messageX : 0;
            int messageY = (int) ( height * 0.9 );
            messageY = ( messageY >= 0 ) ? messageY : 0;
            g.drawString( message, messageX, messageY );
        }
        return img;
    }

    /**
     * Converts the passed in BufferedImage into a JPG and shove the result into
     * the passed in OutputStream.
     * 
     * @param img
     * @param out
     */
    public void saveImage( BufferedImage img, OutputStream out ) {
        try {
            ImageIO.setUseCache( false );
            ImageIO.write( img, "jpg", out );
        } catch ( IOException e ) {
            throw new RuntimeException( "something bad happened", e );
        }
    }
}
