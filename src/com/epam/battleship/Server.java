package com.epam.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.sun.nio.sctp.SendFailedNotification;

public class Server {

	private static final String SHIPS_FILE = "ships.txt";
	private static ServerSocket myService = null;
	private static Socket clientSocket = null;
	private static BufferedReader input = null;
	private static PrintStream output = null;
	private static GameTable enemyTable = null;
	private static GameTable myTable = null;
	private static List<Ship> myShips = null;

	public static void main(String[] args) {

		// int port = Integer.parseInt(args[0]);
		// int width = Integer.parseInt(args[1]);
		// int height = Integer.parseInt(args[2]);

		int port = 3251;
		int width = 20;
		int height = 20;

		boolean gameIsOver = false;

		myShips = ShipGeneratorUtil.loadShipsFromFile(SHIPS_FILE);
		myTable = new GameTable(width, height);
		enemyTable = new GameTable(width, height);

		ShipGeneratorUtil.putShipsDown(myShips, myTable);

		try {
			myService = new ServerSocket(port);
			System.out.println("Waiting for connection.");
			clientSocket = myService.accept();
			clientSocket.setTcpNoDelay(true);
			System.out.println("Connected.");
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			output = new PrintStream(clientSocket.getOutputStream());
			String helloMessage = "HELLO " + width + " " + height;
			sendMessage(helloMessage);
			while (!gameIsOver) {
				String enemyMessage = waitForEnemy();
				processMessage(enemyMessage);
				Thread.sleep(5000);
				sendFireMessage(new CoordinatePair(2, 2));
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static final String waitForEnemy() throws IOException {
		System.out.println("Waiting for enemy.");
		String line = input.readLine();
		System.out.println("Received message:" + line);
		return line;
	}

	private static final void processMessage(String message) {
		if (message.contains("HIT")) {
			enemyTable.addFieldToTable(new TableField(new CoordinatePair(4, 4), FieldType.SHIP));
			System.out.println("it was a hit.");
			System.out.println(enemyTable);
		}
	}

	private static final void sendMessage(String message) throws IOException {
		System.out.println("Sending message:" + message);
		output.println(message);
	}

	private static final void sendFireMessage(CoordinatePair coordinates) throws IOException {
		String message = String.format("FIRE %d %d", coordinates.getX(), coordinates.getY());
		sendMessage(message);
	}
}
