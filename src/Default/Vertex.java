package Default;

/**
 *
 * @author Monty Vanderlinde
 * @version 16 November 2017
 */
public class Vertex extends Point
{
    private Vertex()
    {
        super(0,0,0);
    }
    
    public Vertex(double xCoordinate, double yCoordinate, double zCoordinate)
    {
        super(xCoordinate, yCoordinate, zCoordinate);
    }
    
    @Override
    public String toString()
    {
        return "v  " + this.getXCoordinate() + "  " + this.getYCoordinate() + "  " + this.getZCoordinate();
    }
}