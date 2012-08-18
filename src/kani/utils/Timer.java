package kani.utils;

public class Timer {

    long start;
    long end;
	
    public Timer()  {
        Reset();
    }
	
    public long getElapsedTime()  {
        return ( System.currentTimeMillis() - start );
    }
	
    public void Reset()
	
    {start = System.currentTimeMillis();
		
	}
	
}
