package Clients;

import Default.OBJHandler;

/**
 *
 * @author Monty Vanderlinde
 * @version 12 November 2017
 */
public class Client_TestDiagonals
{
    public static void main(String[] args)
    {
        double startWidth = 1.0, widthChange = 0.5, translationMultiplier = 3.0;
        
        double distance = startWidth * (translationMultiplier + 0.5);
        
        OBJHandler obj = new OBJHandler();
        
        obj.addTestAngle(0.0, 0.5 * startWidth, 0.0, startWidth, translationMultiplier * startWidth, 0.5 * startWidth);
        startWidth += widthChange;
        distance += (translationMultiplier + 0.5) * startWidth + 0.5;
        
        obj.addTestAngle(-1.0 * distance, 0.5 * startWidth,distance, startWidth, translationMultiplier * startWidth, 0.5 * startWidth);
        distance += (translationMultiplier + 0.5) * startWidth;
        startWidth += widthChange;
        distance += (translationMultiplier + 0.5) * startWidth + 0.5;
        
        obj.addTestAngle(-1.0 * distance, 0.5 * startWidth,distance, startWidth, translationMultiplier * startWidth, 0.5 * startWidth);
        distance += (translationMultiplier + 0.5) * startWidth;
        startWidth += widthChange;
        distance += (translationMultiplier + 0.5) * startWidth + 0.5;
        
        obj.addTestAngle(-1.0 * distance, 0.5 * startWidth,distance, startWidth, translationMultiplier * startWidth, 0.5 * startWidth);
        distance += (translationMultiplier + 0.5) * startWidth;
        startWidth += widthChange;
        distance += (translationMultiplier + 0.5) * startWidth + 0.5;
        
        obj.addTestAngle(-1.0 * distance, 0.5 * startWidth,distance, startWidth, translationMultiplier * startWidth, 0.5 * startWidth);
        distance += (translationMultiplier + 0.5) * startWidth;
        
        obj.createFile();
    }
}