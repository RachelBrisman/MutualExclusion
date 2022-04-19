import java.time.LocalTime;

public class T2 extends Thread{
    private NumberObject favoredThread;
    private BooleanObject t1WantsToEnter;
    private BooleanObject t2WantsToEnter;
    private Object ftLock;
    private Object t2Lock;

    public T2(NumberObject ft, BooleanObject t1, BooleanObject t2, Object ftL, Object t2L) {
        this.favoredThread = ft;
        this.t1WantsToEnter = t1;
        this.t2WantsToEnter = t2;
        this.ftLock = ftL;
        this.t2Lock = t2;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            t2WantsToEnter.setBoolean(true);
            while(favoredThread.getNum() == 1){
                while(t1WantsToEnter.getBoolean());
                favoredThread.setNum(2);
            }
            System.out.println("Thread 2 is in the critical section at: " + LocalTime.now());
            t2WantsToEnter.setBoolean(false);
            System.out.println("Thread 2 is NOT in the critical section at: " + LocalTime.now());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
 /*   T2 starts:
        ●	t2WantsToEnter = true;
        ●	Enters the while(favoredThread == 1)
        ●	Passes the while(t1WantsToEnter) because it is still false
        ●	Context switch
        T1 continues:
        ●	1WantsToEnter = true;
        ●	Skips the while(favoredThread = 2) because it is still 1
        ●	Enters the critical code
        ●	Context switch
        T2 continues:
        ●	favoredThread = 2;
        ●	Does not continue in the while loop because favoredThread != 1
        ●	Enters the critical code
*/
