package test.jts.perf.math;

import java.math.*;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.util.*;

/**
 * Test performance of evaluating Triangle predicate computations
 * using 
 * various extended precision APIs.
 * 
 * @author Martin Davis
 *
 */
public class InCirclePerf 
{

	public static void main(String[] args) throws Exception
	{
		  InCirclePerf test = new InCirclePerf();
	    test.run();
	}

	public InCirclePerf() { }
	
  Coordinate pa= new Coordinate(687958.05, 7460725.97);
  Coordinate pb= new Coordinate(687957.43, 7460725.93);
  Coordinate pc= new Coordinate(687957.58, 7460721);
  Coordinate pp= new Coordinate(687958.13, 7460720.99);
  
	public void run() {
    System.out.println("InCircle perf");
    int n = 1000000;
		double doubleTime      = runDouble(n);
    double ddSelfTime      = runDDSelf(n);
    double ddSelf2Time     = runDDSelf2(n);
    double ddTime          = runDD(n);
//		double ddSelfTime = runDoubleDoubleSelf(10000000);
		
    System.out.println("DD VS double performance factor      = " + ddTime/doubleTime);
    System.out.println("DDSelf VS double performance factor  = " + ddSelfTime/doubleTime);
    System.out.println("DDSelf2 VS double performance factor = " + ddSelf2Time/doubleTime);
	}
	
	public double runDouble(int nIter)
	{
		Stopwatch sw = new Stopwatch();
		for (int i = 0; i < nIter; i++) {
      TriPredicate.isInCircle(pa, pb, pc, pp);
		}
		sw.stop();
		System.out.println("double:   nIter = " + nIter 
				+ "   time = " + sw.getTimeString());
		return sw.getTime() / (double) nIter;
	}
	
  public double runDD(int nIter)
  {
    Stopwatch sw = new Stopwatch();
    for (int i = 0; i < nIter; i++) {
      TriPredicate.isInCircleDD(pa, pb, pc, pp);
    }
    sw.stop();
    System.out.println("DD:       nIter = " + nIter 
        + "   time = " + sw.getTimeString());
    return sw.getTime() / (double) nIter;
  }
  
  public double runDDSelf(int nIter)
  {
    Stopwatch sw = new Stopwatch();
    for (int i = 0; i < nIter; i++) {
      TriPredicate.isInCircleDD2(pa, pb, pc, pp);
    }
    sw.stop();
    System.out.println("DD-Self:  nIter = " + nIter 
        + "   time = " + sw.getTimeString());
    return sw.getTime() / (double) nIter;
  }
  public double runDDSelf2(int nIter)
  {
    Stopwatch sw = new Stopwatch();
    for (int i = 0; i < nIter; i++) {
      TriPredicate.isInCircleDD3(pa, pb, pc, pp);
    }
    sw.stop();
    System.out.println("DD-Self2: nIter = " + nIter 
        + "   time = " + sw.getTimeString());
    return sw.getTime() / (double) nIter;
  }
  
 
	
}