/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledraw;

import java.util.List;

/**
 *
 * @author omouneu
 */
public class ConsoleView implements ShapeViews {
    public ConsoleView() {
        
    }
    public void notify( Drawing model ) {
        List<Shape> list = model.getShapes();
        System.out.println("List shapes");
        for (Shape aShape : list) 
            System.out.println(aShape);
    }
    
}
