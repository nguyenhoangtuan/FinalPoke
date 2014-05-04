package com.me.mygdx.HighScores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;

public class HighScoreManipulation {
	private HttpRequest httpRequest;
	private HttpMethods httpMethods;
	
	private String playerName;
	private double score;
	private String status;
	
	public HighScoreManipulation (String playerName, double score) {
		this.playerName = playerName;
		this.score = score;
	}

	public void updateHighScores() {
		
		
		
		String json = "{\"playerName\":\""+playerName+"\",\"score\":"+score+"}";
		httpRequest = new HttpRequest(HttpMethods.POST);
		httpRequest.setHeader("X-Parse-Application-Id", "D3JNG0t2znaXI6U2ekw93VPSRrDFe3iT6EtsiaMf");
		httpRequest.setHeader("X-Parse-REST-API-Key", "iwV02v2bhtCLZCRdghq6mxP4LCBNcnZqhWdMG4ZI");
		httpRequest.setHeader("Content-Type", "application/json");
		httpRequest.setUrl("https://api.parse.com/1/classes/GameScore");
		httpRequest.setContent(json);
		
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {
			
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				// TODO Auto-generated method stub
				status = httpResponse.getResultAsString();
			}
			
			@Override
			public void failed(Throwable t) {
				status = "Failed";
			}
		});
	}
	
	public void getHighScores() {
		httpRequest = new HttpRequest(HttpMethods.GET);
		httpRequest.setHeader("X-Parse-Application-Id", "D3JNG0t2znaXI6U2ekw93VPSRrDFe3iT6EtsiaMf");
		httpRequest.setHeader("X-Parse-REST-API-Key", "iwV02v2bhtCLZCRdghq6mxP4LCBNcnZqhWdMG4ZI");
		httpRequest.setUrl("https://api.parse.com/1/classes/GameScore");
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {
			
			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				String file = httpResponse.getResultAsString();
				Gdx.app.log("TEST",file);
			}
			
			@Override
			public void failed(Throwable t) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public String getPlayerName() {
		return playerName;
	}

	public double getScore() {
		return score;
	}
}
