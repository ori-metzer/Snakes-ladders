
public class Die {
    private int min;
    private int max;


    public Die(int firstNumber, int secondNumber){
        if(firstNumber>=secondNumber){ // determine the boundaries
            min =  secondNumber;
            max = firstNumber;
        }
        else {
            min = firstNumber;
            max = secondNumber;
        }

    }

    //default constructor
    public Die(){
        min = 1;
        max = 6;
    }

    public int roll (){
        int res= Main.rnd.nextInt((this.max-this.min)+1);
        res += this.min;
        return res;
    }






}
