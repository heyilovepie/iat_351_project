package iat351.project_v2.test.time;


import iat351.project_v2.project.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

public class Iterator {
	private Model model;
	private ActionListener timeListener;
	private Timer timer;
	
	public Iterator(){
		model = new Model();
		timeListener = new ActionListener() { // TODO move ActionListener to UIDelegate
			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = model.getDate();
				System.out.println(model.getTimeString(date));
			}
		};
		
		initClock();
	}
	
	
	public void initClock(){
		//the timer is here because it does not work within ui
		int timeOut = 1000; //every sec
		timer = new Timer(timeOut, timeListener);
		timer.start();
	}
}
