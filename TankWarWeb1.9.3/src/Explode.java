import java.awt.*;

/**
 * Created by zhiboliu on 16-7-17.
 */
public class Explode {
    int x, y;
    private boolean live = true;

    private TankClient tc;

    //explosive circle diameter
    int[] diameter = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6};
    int step = 0;

    public Explode(int x, int y, TankClient tc) {
        this.x = x;
        this.y = y;
        this.tc = tc;
    }


    public void draw(Graphics g){
        if(!live){
            tc.explodes.remove(this);
            return;
        }

        if(step == diameter.length){
            live = false;
            step = 0;
            return;
        }

        Color c = g.getColor();
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, diameter[step], diameter[step]);//you need to know which step of explosive circle you r drawing, so you need a variable to know that
        g.setColor(c);

        step++;
    }
}
