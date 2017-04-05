// JAVA IMPORTS
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// JAVAFX IMPORTS
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Erlend on 26.03.2017.
 *
 *  Just for fun.
 *  Shows visual illustration of the store assignment in IS-211
 *
 */

public class Visual extends Application {
    static Pane root;
    static Path path;
    static Label timeLabel = new Label();
    static Label visitorLabel = new Label();
    static long time = 32400000; // 32400000 - 61200000
    static long visitors = 0;
    static Store butikk = new Store();
    static boolean open = true;
    static int who = 1;


    public static void timeTicks() {
        Timer t = new Timer();

        t.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        time = time + 10000;
                    }
                },
                0,
                10);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create window
        root = new Pane();
        Scene scene = new Scene(root, 880, 500);
        // Set background image
        BackgroundImage background= new BackgroundImage(new Image("/images/store.png",880,500,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
        primaryStage.setScene(scene);
        primaryStage.show();

        // To set position of time label, il wrap it in a StackPane
        StackPane p = new StackPane();
        p.setPrefSize(600,500);
        p.getChildren().addAll(timeLabel, visitorLabel);
        StackPane.setAlignment(timeLabel, Pos.TOP_RIGHT);
        StackPane.setAlignment(visitorLabel, Pos.BOTTOM_CENTER);
        root.getChildren().add(p);

        List<Point2D> waypoints = wayPoints();

        path = createPath(waypoints);
        path.setStroke(Color.BLACK);
        path.setStrokeWidth(15);
        path.setStrokeType(StrokeType.CENTERED);
        root.getChildren().add(path);

        runStore();

    }

    private Path createPath(List<Point2D> waypoints) {

        Path path = new Path();
        for (Point2D point : waypoints) {
            if (path.getElements().isEmpty()) {
                path.getElements().add(new MoveTo(point.getX(), point.getY()));
            }
            else {
                path.getElements().add(new LineTo(point.getX(), point.getY()));
            }
        }
        return path;
    }

    public List<Point2D> wayPoints() {
        List<Point2D> path = new ArrayList<>();

        path.add(new Point2D(150, 600));
        path.add(new Point2D(150, 420));
        path.add(new Point2D(750, 420));
        path.add(new Point2D(750, 230));
        path.add(new Point2D(150, 230));
        path.add(new Point2D(150, 130));
        path.add(new Point2D(430, 130));
        path.add(new Point2D(430, 0));
        return path;
    }

    // Moves icon
    public void moveIcon(long movetime) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Time ct = Time.valueOf("12:00:00");
                ct.setTime(time);
                timeLabel.setText("Tid: " + ct);
                visitorLabel.setText("Besøkende: " + visitors);
                ImageView customer = createIcon(30, 50);
                root.getChildren().add(customer);
                PathTransition pt = new PathTransition(Duration.millis(movetime * 100), path);
                pt.setNode(customer);
                pt.setCycleCount(1);
                pt.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
                pt.play();

                if (time > 61200000) {
                    open = false;
                    timeLabel.setText("Butikken stenger!!!!");
                    System.out.println("\n Butikken stenger!!!! \n");
                }

            }
        });
    }

    private ImageView createIcon(double width, double height) {
        ImageView im = new ImageView();
        if (who == 1){
            im.setImage(new Image ("/images/Christian.PNG"));
        }
        else if (who == 2){
            im.setImage(new Image ("/images/Erlend.PNG"));
        }
        else if (who == 3) {
            im.setImage(new Image ("/images/Martin.PNG"));
        }
        im.setFitHeight(width);
        im.setFitHeight(height);
        return im;

    }

    public void runStore() {
        Runnable t1 = new Runnable() {
            public void run() {
                timeTicks();
            }
        };
        Runnable t2 = new Runnable() {
            public void run() {
                int number = 1;
                while (open) {
                    new Customer("Christian " + String.valueOf(number));
                    number++;
                    visitors++;
                    who = 1;

                }
            }
        };

        Runnable t3 = new Runnable() {
            public void run() {
                int number = 1;
                while (open) {
                    new Customer("Erlend " + String.valueOf(number));
                    number++;
                    who = 2;
                    visitors++;
                }
            }
        };
        Runnable t4 = new Runnable() {
            public void run() {
                int number = 1;
                while (open) {
                    new Customer("Martin " + String.valueOf(number));
                    number++;
                    visitors++;
                    who = 3;

                }
            }
        };

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
