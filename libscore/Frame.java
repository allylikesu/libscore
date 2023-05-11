package libscore;

public class Frame {
    private boolean isTenth;
    private int pinfallFirst;
    private int pinfallSecond;
    private int pinfallThird;
    
    public Frame() {
        this(false);
    }
    public Frame(boolean isTenth) {
        this.isTenth = isTenth;
        pinfallFirst = pinfallSecond = pinfallThird = -1;
    }

    public boolean isEmpty() {
    	return pinfallFirst<0 && pinfallSecond<0 && pinfallThird<0;
    }
    
    public boolean isComplete() {
        if(pinfallFirst < 0) return false;
        if(pinfallFirst >= 10 && !isTenth) return true;
        if(pinfallSecond < 0) return false;
        if(isTenth) {
	    if((isStrike() || isSpare()) && pinfallThird > -1) return true;
            if(totalPinfall() >= 10) return false;
        }
        return true;
    }

    public boolean isSpare() {
        return pinfallFirst < 10 && totalPinfall() >= 10;
    }

    public boolean isStrike() {
        return pinfallFirst >= 10;
    }

    public boolean setNextBall(int i) {
        if(isComplete()) return false;
        if(i < 0) i = 0;
        if(i > 10) i = 10;
        if(pinfallFirst < 0) {
            pinfallFirst = i;
            return true;
        }
        if(pinfallSecond < 0) {
            if(!isTenth && i > 10 - pinfallFirst) i = 10 - pinfallFirst;
            if(isTenth && pinfallFirst < 10 && i > 10 - pinfallFirst) i = 10 - pinfallFirst;
            pinfallSecond = i;
            return true;
        }
        if(pinfallThird < 0) {
            pinfallThird = i;
            return true;
        }
        return false;
    }

    public int totalPinfall() {
        int p1 = pinfallFirst>0 ? pinfallFirst : 0;
        int p2 = pinfallSecond>0 ? pinfallSecond : 0;
        int p3 = pinfallThird>0 ? pinfallThird : 0;
        if(isTenth) {
            return p1 + p2 + p3;
        } else {
            return p1 + p2;
        }
    }

    public String toString() {
        if(!isTenth) {
            return "Frame["+pinfallFirst+","+pinfallSecond+"]";
        }
        return "Frame["+pinfallFirst+","+pinfallSecond+","+pinfallThird+"]";
    }

    /* GETTERS/SETTERS */
    public boolean isTenth() { return isTenth; }
    public int pinfallFirst() { return pinfallFirst>0 ? pinfallFirst : 0; }
    public int pinfallSecond() { return pinfallSecond>0 ? pinfallSecond : 0; }
    public int pinfallThird() {
        if(isTenth) {
            return pinfallThird>0 ? pinfallThird : 0;
        }
        return 0;
    }

    public boolean hasPinfallFirst() { return pinfallFirst < 0; }
    public boolean hasPinfallSecond() { return pinfallSecond < 0; }
    public boolean hasPinfallThird() { return pinfallThird < 0; }

    public int rawPinfallFirst() { return pinfallFirst; }
    public int rawPinfallSecond() { return pinfallSecond; }
    public int rawPinfallThird() { return pinfallThird; }

    public void setIsTenth(boolean b) {
        isTenth = b;
    }
    public void setPinfallFirst(int i) {
        pinfallFirst = i;
    }
    public void setPinfallSecond(int i) {
        pinfallSecond = i;
    }
    public void setPinfallThird(int i) {
        pinfallThird = i;
    }
}
