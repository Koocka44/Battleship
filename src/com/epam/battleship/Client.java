package com.epam.battleship;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

public class Client {

	private static final String SHIPS_FILE = "ships.txt";
	private static Socket myClient;
	private static PrintStream output;
	private static BufferedReader input;
	private static int port = 3251;
	private static int width;
	private static int height;
	private static boolean areTablesInitialized = false;
	private static boolean gameIsOver = false;
	
	private static GameTable myTable;
	private static GameTable enemyTable;
	private static List<Ship> ships;
	
	private static CoordinatePair currentTargetCoordinates;
	
	public static void main(String[] args) {

		ships = ShipGeneratorUtil.loadShipsFromFile(SHIPS_FILE);

		try {
			myClient = new Socket("localhost", port);
			myClient.setTcpNoDelay(true);
			input = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
			output = new PrintStream(myClient.getOutputStream());
			System.out.println("Client connected.");
			while(!areTablesInitialized){
				String line = input.readLine();
				processHelloMessage(line);
			}
			while(!gameIsOver){
				sendFireMessage(new CoordinatePair(2, 2));
				String enemyMessage = waitForEnemy();
				processMessage(enemyMessage);
				Thread.sleep(5000);
			}
		} catch (IOException | InterruptedException e) {
			System.out.println(e);
		}

	}
	
	private static final String waitForEnemy() throws IOException{
		System.out.println("Waiting for enemy.");
		String line = input.readLine();
		System.out.println("Received message:" + line);
		return line;
	}
	
	private static final void sendFireMessage(CoordinatePair coordinates) throws IOException{
		String message = String.format("FIRE %d %d", coordinates.getX(), coordinates.getY());
		sendMessage(message);
	}
	
	private static final void processMessage(String message){
		if(message.contains("HIT")){
			
		}
	}
	
	private static final void processHelloMessage(String message){
		if(message.contains("HELLO")){
			String[] messageParts = message.split(" ");
			width = Integer.parseInt(messageParts[1]);
			height = Integer.parseInt(messageParts[2]);
			System.out.printf("Width: %d, Height: %d\n",width,height);
			initTables();
		}
	}
	
	private static final void initTables() {
		myTable = new GameTable(width, height);
		enemyTable = new GameTable(width, height);
		ShipGeneratorUtil.putShipsDown(ships, myTable);
		System.out.printf("Tables initialized with dimensions: %d, %d\n",width,height);
		areTablesInitialized = true;
	}
	
	private static final void sendMessage(String message) throws IOException{
		System.out.println("Sending message:" + message);
		output.println(message);
	}

}
