import java.time.LocalTime;

public class T1 extends Thread{
    private NumberObject favoredThread;
    private BooleanObject t1WantsToEnter;
    private BooleanObject t2WantsToEnter;
    private Object ftLock;
    private Object t1Lock;

    public T1(NumberObject ft, BooleanObject t1, BooleanObject t2, Object ftL, Object t1L) {
        this.favoredThread = ft;
        this.t1WantsToEnter = t1;
        this.t2WantsToEnter = t2;
        this.ftLock = ftL;
        this.t1Lock = t1;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1WantsToEnter.setBoolean(true);
            while(favoredThread.getNum() == 2){
                while(t2WantsToEnter.getBoolean());
                favoredThread.setNum(1);
            }
            System.out.println("Thread 1 is in the critical section at: " + LocalTime.now());
            t1WantsToEnter.setBoolean(false);
            System.out.println("Thread 1 is NOT in the critical section at: " + LocalTime.now());
        }
    }
}
