/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harmonograph;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Lee
 */
public class Harmonograph {

    public static double timeStep = 0.005;
    public static int time = 2000;
    public static double[] Amplitude = new double[]{100,300};
    public static double[] Damping = new double[]{-0.05,0};
    public static double[] Frequencies = new double[]{10,40};
    public static double[] Phase = new double[]{0,6.28};
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
              JFrame frame = buildFrame();
              
        int[][] graph = makeGraph();
        
        JPanel pane;
        pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, 1000, 1000);
                g.setColor(Color.red);
                for(int i=0 ; i < time - 1 ; i ++)
                {
                    g.drawLine(graph[0][i],graph[1][i],graph[0][i+1],graph[1][i+1]);
                }
                
                
                
            }
        };


        frame.add(pane);
    }
    
    public static int[][] makeGraph()
    {   
        int[][] graph = new int[2][time];
        double[][] param = new double[4][4];
        Random r = new Random();
        for(int i = 0; i < 4; i++)
        {
                param[0][i] = (Amplitude[1]-Amplitude[0])*r.nextDouble()
                        + Amplitude[0];
                param[1][i] = (Frequencies[1]-Frequencies[0])*r.nextDouble()
                        + Frequencies[0];
                param[2][i] = (Damping[1]-Damping[0])*r.nextDouble()
                        + Damping[0];
                param[3][i] = (Phase[1]-Phase[0])*r.nextDouble()
                        + Phase[0];
        }
        
        for(int t=0; t < time; t++)
        {
            graph[0][t] = (int) (
                    
                    param[0][0]*Math.sin(param[1][0]*t*timeStep + param[3][0])*
                    Math.exp(t*timeStep*param[2][0])+ 
                   
                    //param[0][1]*Math.sin(param[1][1]*t*timeStep + param[3][1])*
                    //Math.exp(t*timeStep*param[2][1]) + 
                    
                    0.5)+400;
            
            graph[1][t] = (int) (
                    
                    param[0][2]*Math.sin(param[1][2]*t*timeStep + param[3][2])*
                    Math.exp(t*timeStep*param[2][2]) + 
                    
                    //param[0][3]*Math.sin(param[1][3]*t*timeStep*600 + param[3][3])*
                    //Math.exp(t/600*param[2][3]) + 
                    
                    0.5)+400;
        }
        return graph;
    }

    private static JFrame buildFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        return frame;
    }
    
}
