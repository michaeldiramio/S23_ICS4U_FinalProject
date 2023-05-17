import DLibX.*;
import java.awt.Color;

public class Main {

	public static void main(String[] args) {
		DConsole dc = new DConsole();
		dc.setPaint(Color.RED);
		dc.fillRect(200, 50, 80, 20);
		dc.redraw();
	}
	
	
}