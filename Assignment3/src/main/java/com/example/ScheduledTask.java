package com.example;

import java.util.ArrayList;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {

	
	private ApplicationContext ctx;
	
	private DAO d;
	
private	TwitterScrap scrap;

ScheduledTask(ApplicationContext ctx,DAO d,TwitterScrap scrap){
	this.ctx=ctx;
	this.d=d;
	this.scrap=scrap;
}

	
	// Add your task here
	public void run() {

		this.scrap.startTwitter();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Data gathered for 5 secs
		scrap.stopTwitter();
		ArrayList<TwitterEntity> en=(ArrayList<TwitterEntity>) ctx.getBean("twitterEntityListBean");
		d.save(en);
	}
}
