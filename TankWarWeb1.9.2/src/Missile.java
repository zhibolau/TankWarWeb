import java.awt.*;
import java.util.List;

/**
 * Created by zhiboliu on 16-7-13.
 */
public class Missile {

    private static final int XSPEED = 10;
    private static final int YSPEED = 10;

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    private TankClient tc;

    int x,y;
    Direction dir;
    private boolean good;

    private boolean live = true; // if missile is alive


    public Missile(int x, int y, Direction dir) {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    public Missile(int x, int y, boolean good , Direction dir, TankClient tc) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.good = good;
        this.tc = tc;
    }

    public void draw(Graphics g){
        if(!live){//if current missile died, we do not need to draw it
            tc.missiles.remove(this);
            return;
        }

        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        move();
    }

    private void move() {
        switch (dir){
            case L :
                x -= XSPEED;
                break;
            case LU :
                x -= XSPEED;
                y -= YSPEED;
                break;
            case U :
                y -= YSPEED;
                break;
            case RU :
                x += XSPEED;
                y -= YSPEED;
                break;
            case R :
                x += XSPEED;
                break;
            case RD :
                 x += XSPEED;
                y += YSPEED;
                break;
            case D :
                y += YSPEED;
                break;
            case LD :
                x -= XSPEED;
                y += YSPEED;
                break;
        }

        if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT){
            live = false;
            //method 3 to remove missile
            tc.missiles.remove(this);//其实可以没有这步骤 因为在draw中我们检查了是否live 若否就不画了
        }
    }

    public boolean isLive() {
        return live;
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    //missile has a surrounded rectangle, so does tank, we can use rectangle class method to check if they touch
    public boolean hitTank(Tank t){
        if(this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()){//if tank is not live, missile can still touch that tank even though we do not draw that tank
            t.setLive(false);
            this.live = false;
            Explode e = new Explode(x, y, tc);
            tc.explodes.add(e);
            return true;
        }
        else{
            return false;
        }
    }

    //we need to check each missible if hit each enemy tank
    public boolean hitTanks(List<Tank> tanks){
        for(int i = 0; i < tanks.size(); i++){
            if(hitTank(tanks.get(i))){
                return true;
            }
        }
        return false;

    }

}
