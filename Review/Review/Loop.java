package Review;

public class Loop {
    public void intLoop () {
        int number;
        for (int i=1; i<=10; i++ ){
            if (i%7==0) break;
            if (i%2==0)continue;
            System.out.println(i + " ");
        }
    }

}
