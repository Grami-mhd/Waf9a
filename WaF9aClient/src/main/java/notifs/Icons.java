package notifs;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.css.Styleable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Icons extends Button {

	private Node icon;

	private AnimationType type;

	private Label lab;

	public Icons(int number, String path, AnimationType type) {

		this.icon = createNotification(number, path);
		setGraphic(icon);
		this.type = type;
		if (number != 0)
			addAnimation();
	}

	private Node createNotification(int number, String path) {
		StackPane p = new StackPane();
		ImageView imgv = new ImageView(new Image(path));
		imgv.setFitHeight(35);
		imgv.setFitWidth(35);
		p.getChildren().addAll(imgv);
		lab = new Label("" + number);
		lab.setPadding(new Insets(0, 15, 15, 0));
		lab.setTextFill(Color.web("red"));
		Font f = new Font(20);
		lab.setFont(Font.font(f.getFamily(), FontWeight.EXTRA_BOLD, 20));
		p.getChildren().add(lab);
		return p;
	}

	private Timeline timeline;

	private void addBlinkAnimation() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		final KeyValue kv = new KeyValue(icon.opacityProperty(), 0.0);
		final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	private TranslateTransition translateTransition;

	private void addJumpAnimation() {
		translateTransition = new TranslateTransition(Duration.millis(200), icon);
		final double start = 0.0;
		final double end = start - 4.0;
		translateTransition.setFromY(start);
		translateTransition.setToY(end);
		translateTransition.setCycleCount(-1);
		translateTransition.setAutoReverse(true);
		translateTransition.setInterpolator(Interpolator.EASE_BOTH);
		translateTransition.play();
	}

	public enum AnimationType {

		BLINK, JUMP, NONE;
	};

	public void setLabText(int nb) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				lab.setText(nb + "");
				if (nb == 0) {
					if (translateTransition != null)
						translateTransition.stop();
					if (timeline != null)
						timeline.stop();
				} else {
					addAnimation();
				}
			}
		});
	}

	private void addAnimation() {
		switch (type) {
		case BLINK:
			addBlinkAnimation();
			break;
		case JUMP:
			addJumpAnimation();
			break;
		case NONE:
			break;
		default:
			break;
		}
	}

}