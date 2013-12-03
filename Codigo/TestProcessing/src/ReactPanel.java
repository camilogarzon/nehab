import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ReactPanel extends JPanel implements MouseMotionListener {
    boolean flag = false;
    Ellipse2D el = new Ellipse2D.Float(100, 100, 200, 200);
    
    public ReactPanel(){
        setPreferredSize(new Dimension(450, 450));
        setBackground(Color.GREEN);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Mouse dragged (" + e.getX() + ',' + e.getY() + ')');
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(el.contains(e.getX(),e.getY())){
            flag = true;
            repaint();
            //System.out.println("Mouse moved (" + e.getX() + ',' + e.getY() + ')');  
        } else{
            flag=false;
            repaint();
        }


    }
        public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        if (flag == false) {
            g2.fill(el);
        } 
    }
}
