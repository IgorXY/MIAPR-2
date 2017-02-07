package maximin.method;

/**
 * Created by User on 07.02.2017.
 */
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

import maximin.method.algorithm.MaximinExecutor;
import maximin.method.drawing.Drawer;
import maximin.method.generator.Randomizer;
import maximin.method.point.Centroid;
import maximin.method.point.Point;

/**
 * Created by Igor_Tonko on 07.02.2017.
 */
public class Executor {

    private Drawer drawer;
    private MaximinExecutor maximinExecutor;

    public Executor(Canvas canvas){
        drawer = new Drawer(canvas);
        drawer.cleanCanvas();
    }

    public void initializeValues(int pointCount, int centroidCount){
        Randomizer randomizer = new Randomizer((int) drawer.getXCoordinateBound(), (int) drawer.getYCoordinateBound());
        ArrayList<Point> pointList = randomizer.generateRandomPoints(pointCount);
        ArrayList<Centroid> centroidList = randomizer.generateRandomCentroids(centroidCount);

        maximinExecutor = new MaximinExecutor(pointList, centroidList);
        ArrayList<Centroid> allocatedCentroids = maximinExecutor.allocatePointsToCentroids();

        drawer.cleanCanvas();
        drawer.drawCentroids(allocatedCentroids);
    }

    public void performCalculation(){
        while (maximinExecutor.changeCentroidPositions()){
            ArrayList<Centroid> allocatedCentroids = maximinExecutor.allocatePointsToCentroids();
            drawer.cleanCanvas();
            drawer.drawCentroids(allocatedCentroids);
        }
    }

}

