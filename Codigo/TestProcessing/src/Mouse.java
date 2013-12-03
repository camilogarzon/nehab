import java.awt.AWTException;
import java.awt.Robot;
import javax.swing.JOptionPane;


public class Mouse {

	public  Mouse (int x,int y){
        
	      /* x = JOptionPane.showInputDialog("digite coordenada x");
	       y=JOptionPane.showInputDialog("digite coordenada Y"); 
	       
	       X=Integer.parseInt(x) ;
	       Y=Integer.parseInt(y) ;
	        // TODO code application logic here
	     */
	        try{
	        Robot r = new Robot();
	        r.mouseMove(x,y);
	    
	       } catch (AWTException e) {
	            e.printStackTrace();
	   }  
	}
}
