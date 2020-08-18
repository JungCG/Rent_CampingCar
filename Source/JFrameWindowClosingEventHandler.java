package Project;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

class JFrameWindowClosingEventHandler extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.out.println("==================");
		System.out.println(" 윈도우 창을 닫았습니다.");
		System.out.println("==================");

		new DBClass(0);

		JFrame frame = (JFrame) e.getWindow();
		frame.dispose();

		System.exit(0);
	}
}