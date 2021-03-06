package main;

public class Fraction {
    public int numberator;//分子
    public int denominator;//分母

    public Fraction(String inp) {
        inp.trim();//去除两端空格
        int divLoc = inp.indexOf('/');
        if (divLoc == -1) {
            numberator = Integer.parseInt(inp);
            denominator = 1;
        } else {
            String tmp;
            boolean flag = inp.startsWith("-");
            if (flag) {
                tmp = inp.substring(1);
            } else {
                tmp = inp;
            }
            String[] frac = tmp.split("/");
            numberator = Integer.parseInt(frac[0]);
            denominator = Integer.parseInt(frac[1]);
            if (flag) {
                numberator = -numberator;
            }
        }
        this.reduct();
    }

    public Fraction(int a, int b) {
        numberator = a;
        denominator = b;
    }

    private void reduct() {
        if (denominator < 0){
            denominator = -denominator;
            numberator = -numberator;
        }
        int tmp = (numberator > 0) ? numberator : -numberator;
        int m = gcd(tmp, denominator);
        this.numberator /= m;
        this.denominator /= m;
    }

    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }

    public Fraction add(Fraction b) {
        Fraction ret = new Fraction(this.numberator * b.denominator +
                b.numberator * this.denominator,
                this.denominator * b.denominator);
        ret.reduct();
        return ret;
    }

    public Fraction mul(Fraction b) {
        Fraction ret = new Fraction(this.numberator * b.numberator,
                this.denominator *b.denominator);
        ret.reduct();
        return ret;
    }

    public Fraction div(Fraction b) {
        Fraction ret = new Fraction(this.numberator * b.denominator,
                this.denominator * b.numberator);
        ret.reduct();
        return ret;
    }

    public boolean isZero() {
        if (this.numberator == 0) return true;
        else return false;
    }

    public boolean isOne() {
        return this.numberator == this.denominator;
    }

    public String toString() {
        String[] frac = new String[2];
        int tmp_num;
        String ret;
        boolean flag;
        if(this.numberator < 0) {
            tmp_num = -this.numberator;
            flag = true;
        } else {
            tmp_num = this.numberator;
            flag = false;
        }
        frac[0] = String.valueOf(tmp_num);
        frac[1] = String.valueOf(this.denominator);
        if(flag) {
            if (this.denominator == 1)
                ret = "-" + frac[0];
            else
                ret = "-"+frac[0]+"/"+frac[1];
        } else {
            if (this.denominator == 1)
                ret = frac[0];
            else
                ret = frac[0] + "/" + frac[1];
        }
        return ret;
    }
}
