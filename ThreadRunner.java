public class ThreadRunner {
    public static void main(String[] args){
        NumberObject favoredThread = new NumberObject(1);
        BooleanObject t1WantsToEnter = new BooleanObject(false);
        BooleanObject t2WantsToEnter = new BooleanObject(false);
        Object ftLock = new Object();
        Object t1Lock = new Object();
        Object t2Lock = new Object();

        Thread t1 = new T1(favoredThread, t1WantsToEnter, t2WantsToEnter, ftLock, t1Lock);
        Thread t2 = new T2(favoredThread, t1WantsToEnter, t2WantsToEnter, ftLock, t2Lock);

        t1.start();
        t2.start();
    }
}
