package clock;

public class Clock {
    private Display hour = new Display(24);
    private Display minute = new Display(60);
    private Display second = new Display(60);
    
    /**
     * use hour, minute, second to init clock time。
     * @param hour 
     * @param minute
     * @param second
     */
    public Clock(int hour, int minute, int second){
        if (!this.hour.setValue(hour)){
            System.err.println("change hour fail");
        }
        if (!this.minute.setValue(minute)) {
            System.err.println("change minute fail");
        }
        if (!this.second.setValue(second)) {
            System.err.println("change second fail");
        }
    }
    
    public Clock(){}

    /**
     * +1s
     */
    public void tick(){
        second.increase();
        if (second.getValue() == 0) {
            minute.increase();
            if (minute.getValue() == 0) {
                hour.increase();
            }
        }
    }
    
    /**
     * 返回一个String的值，以“hh:mm:ss“的形式表示当前时间。
     * 这里每个数值都占据两位，不足两位时补0。如“00:01:22"。
     * 注意其中的冒号是西文的，不是中文的。
     * @return String of time, format "hh:mm:ss"
     */
    public String toString(){
        return String.format("%02d:%02d:%02d", hour.getValue(), minute.getValue(), second.getValue());
    }

    /**
     * Clock running
     */
    public void start(){
        second.increase();
        if (second.getValue() == 0) {
            minute.increase();
            if (minute.getValue() == 0) {
                hour.increase();
            }
        }
        
        System.out.printf(
            "Now,time is %02d:%02d:%02d.\n", 
            hour.getValue(), 
            minute.getValue(), 
            second.getValue()
            );
    }

    public static void main(String[] args) {
        Clock clock = new Clock(12,24,60);
        while (true) {
            try 
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }

            clock.tick();
            System.out.println("tick success!");
            System.out.println(clock.toString());
            
            // clock.start();
        }
        
    }
}
