package Default;

/**
 *
 * @author Monty Vanderlinde
 * @version 20 November 2017
 */
public class MathHelpers
{
    /**
     * 
     * @param n
     * @return
     * @throws IllegalArgumentException 
     */
    public static boolean isPowerOfFour(int n) throws IllegalArgumentException
    {
        if(n < 4)
            throw new IllegalArgumentException("isPowerOfFour does not work on numbers less than 4");
        
        int i = 1;
        
        do
        {
            i *= 4;
            
            if(n == i)
                return true;
            
            if(n < i)
                return false;
        }while(i < 1073741824);
        
        return false;
    }
    
    /**
     * 
     * @param n
     * @return 
     */
    public static int getSquareRoot_PowersOfFour(int n)
    {
        if(n < 4)
            throw new IllegalArgumentException("getSquareRoot_PowersOfFour does not work on numbers less than 4");
        
        if(n > 1073741824)
            throw new IllegalArgumentException("getSquareRoot_PowersOfFour does not work on numbers greater than 1,073,741,824");
        
        int i = 1;
        
        do
        {
            i *= 2;
            
            if(n == (i * i))
                return i;
            
            if(n < (i * i))
                throw new IllegalArgumentException("getSquareRoot_PowersOfFour only works on powers of four");
        }while((i * i) < 1073741824);
        
        throw new RuntimeException("Should not be able to reach here.  Problem with the program");
    }
}