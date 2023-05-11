package libscore;

import java.util.ArrayList;

public class Game {
    private ArrayList<Frame> frames;

    public Game() {
        frames = new ArrayList<Frame>();
        for(int i = 0; i < 9; i++) {
            Frame f = new Frame();
            frames.add(f);
        }
        Frame tenth = new Frame(true);
        frames.add(tenth);
    }

    public boolean isComplete() {
        for(Frame f: frames) {
            if(!f.isComplete()) return false;
        }
        return true;
    }

    public boolean setNextBall(int i) {
        Frame current = currentFrame();
        if(current == null) return false;
        return current.setNextBall(i);
    }

    public int getTotalScore() {
        int score = 0;
        for(int i = 0; i < frames.size() - 1; i++) {
            Frame current = frames.get(i);
            Frame next = frames.get(i + 1);
            score += current.totalPinfall();
            if(current.isSpare()) {
                score += next.pinfallFirst();
                continue;
            } 
            if(current.isStrike()) {
                score += next.pinfallFirst() + next.pinfallSecond();
                if(next.isTenth()) continue;
                if(next.isStrike()) {
                    score += frames.get(i + 2).pinfallFirst();
                }
                continue;
            }
        }
        Frame tenth = frames.get(frames.size() - 1);
        score += tenth.totalPinfall();

        return score;
    }

    public Frame currentFrame() {
        for(Frame f: frames) {
            if(!f.isComplete()) return f;
        }
        return null;
    }
    public int currentFrameIndex() {
        for(int i = 0; i < frames.size(); i++) {
            if(!frames.get(i).isComplete()) return i;
        }
        return -1;
    }

    public int getScoreAtFrame(int i ) {
        if(i < 0 || i > frames.size()) return 0;
        int score = 0;
        Frame thisFrame = frames.get(i);
        Frame nextFrame;
        Frame nextNextFrame;
        if(!thisFrame.isComplete()) return -1;

        int nextPinfall = 0;
        int nextNextPinfall = 0;
        switch(i) {
            case 9: // get next throws from the rest of the 10th frame
                return thisFrame.totalPinfall() + getScoreAtFrame(i - 1);
            case 8: // get next throws from first 2 throws of tenth
                nextFrame = frames.get(i + 1);
                nextPinfall = nextFrame.pinfallFirst();
                nextNextPinfall = nextFrame.pinfallSecond();
                break;
            default: // get next throws from the next frame or the next 2 frames
                nextFrame = frames.get(i + 1);
                nextNextFrame = frames.get(i + 2);
                nextPinfall = nextFrame.pinfallFirst();
                if(nextFrame.isStrike()) {
                    nextNextPinfall = nextNextFrame.pinfallFirst();
                } else {
                    nextNextPinfall = nextFrame.pinfallSecond();
                }
        }

        score += thisFrame.totalPinfall();
        if(thisFrame.isSpare()) {
            score += nextPinfall;
        }
        if(thisFrame.isStrike()) {
            score += nextPinfall + nextNextPinfall;
        }
        if(i > 0) {
            score += getScoreAtFrame(i - 1);
        }
        return score;
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public String toString() {
        String ret = "Game["+frames.get(0).toString();
        for(int i = 1; i < 10; i++) {
            ret+=","+frames.get(i).toString();
        }
        return ret+"]";
    }
}
