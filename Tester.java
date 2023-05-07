import libscore.Frame;
public class Tester {
    public static void main(String[] args) {
        Game g = new Game();
        boolean go = true;
        while(go) {
            go = g.setNextBall(10);
            //System.out.println(g.getTotalScore());
        }
        System.out.println(g);
        for(int i = 0; i < 10; i++) {
            System.out.println(g.getScoreAtFrame(i));
        }
        System.out.println(g.getTotalScore());
    }
}
