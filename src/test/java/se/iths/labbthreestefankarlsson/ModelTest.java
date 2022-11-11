package se.iths.labbthreestefankarlsson;

import javafx.scene.paint.Color;
import se.iths.labbthreestefankarlsson.Model;
import se.iths.labbthreestefankarlsson.Circle;
import se.iths.labbthreestefankarlsson.Shape;
import se.iths.labbthreestefankarlsson.Rectangle;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Model model = new Model();

    @Test
    void testDefaultShapeValues(){

        var colorActual =new Model().getColor();
        var sizeActual = new Model().getSize();

        assertEquals(Color.BLACK,colorActual);
        assertEquals(50,sizeActual);

    }
    @Test
    void testInsideShapeCheck(){

        model.shapes.add(new Circle(75,75,50,Color.BLACK));

        double mousePositionX = 75.0;
        double mousePositionY = 75.0;

        var Expected = true;
        var Actual = false;


        for (var shape: model.shapes){
            if (shape.isInsideShape(mousePositionX,mousePositionY)){
                Actual = true;
            }
        }

        assertEquals(Expected,Actual);

    }

}