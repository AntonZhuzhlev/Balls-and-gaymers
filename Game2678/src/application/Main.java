package application;
	
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	private static final int width = 800;
	private static final int height = 600;
	
	
	private static final int Racket_width = 10;
	private static final int Racket_height = 90;
	
	
	private static final int Ball_radius = 30;
	
	
	double player_x = 0;
	double player_y = height/2 - Racket_height/2;
	
	double comp_x = width - Racket_width;
	double comp_y = height/2 - Racket_height/2;
	
	double ball_x = width/2 - Ball_radius/2;
	double ball_y = height/2 - Ball_radius/2;

	
	GraphicsContext gc;
	
	
	double ball_x_speed = 3;
	double ball_y_speed = 0.1;
	
	
	boolean gameStarted;
	
	
	private void drawTable() {
		gc.setFill(Color.YELLOW);
		gc.fillRect(0, 0, width, height);
		
		
		gc.setFill(Color.BLACK);
		gc.fillRect(width/2 - 1, 0, 2, height);
		
		
		if (gameStarted) {
			ball_x = ball_x + ball_x_speed;
			ball_y = ball_y + ball_y_speed;
			if (ball_x < width - width/4) {
				comp_y = ball_y - Racket_height/2;
			}
			gc.fillOval(ball_x, ball_y, Ball_radius, Ball_radius);
		}
		
		
		gc.fillOval(ball_x, ball_y, Ball_radius, Ball_radius);
		
		
		gc.fillRect(player_x, player_y, Racket_width, Racket_height);
		gc.fillRect(comp_x, comp_y, Racket_width, Racket_height);
	}
	
	
	@Override
	public void start(Stage root) {
		// PrimaryStage переименовываем в root чисто по фану
		Canvas canvas = new Canvas(width,height);
		
		root.setScene(new Scene(new StackPane(canvas)));
		root.setTitle("Gaymer");
		
		gc = canvas.getGraphicsContext2D();
		drawTable();
		
		//Duration.millis(10), e->drawTable()
		Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10),e -> drawTable()));
		t1.setCycleCount(Timeline.INDEFINITE); 
		
		canvas.setOnMouseClicked(e -> gameStarted = true);
		canvas.setOnMouseMoved(e -> player_y = e.getY());
		
		
		root.show();
		t1.play();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
