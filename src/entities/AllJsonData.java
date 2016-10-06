package entities;

import java.util.ArrayList;

import application.JSONHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AllJsonData {
	private static String token;
	private static ArrayList<Pokemon> pokelist;
	private static VBox view;
	private static ScrollPane ScrollForLog;
	private static Integer amountToCatch;
	private static Boolean pokeFarm;
	private static Integer waitingTime;

	public AllJsonData(String token, ArrayList<Pokemon> list, Integer amount, Boolean autoFarm, Integer waitingTime) {
		AllJsonData.token = token;
		AllJsonData.pokelist = list;
		AllJsonData.amountToCatch = amount;
		AllJsonData.pokeFarm = autoFarm;
		AllJsonData.waitingTime = waitingTime;
	}

	public static String getToken() {
		return token;
	}

	public static Boolean getPokeFarm() {
		return AllJsonData.pokeFarm;
	}

	public static void setPokeFarm(Boolean pokeFarm) {
		AllJsonData.pokeFarm = pokeFarm;
		JSONHandler.UpdatePokeList();
	}

	public static void setToken(String token) {
		AllJsonData.token = token;
		JSONHandler.UpdatePokeList();
	}

	public static ArrayList<Pokemon> getPokelist() {
		return pokelist;
	}

	public static void setPokelist(ArrayList<Pokemon> pokelist) {
		AllJsonData.pokelist = pokelist;
	}

	public static void setLog(VBox logArea) {
		AllJsonData.view = logArea;
	}

	public static VBox getLog() {
		return view;
	}

	public static ScrollPane getScrollForLog() {
		return AllJsonData.ScrollForLog;
	}

	public static void setScrollForLog(ScrollPane scrollForLog) {
		AllJsonData.ScrollForLog = scrollForLog;
	}

	public static Integer getAmountToCatch() {
		return amountToCatch;
	}

	public static void setAmountToCatch(Integer amountToCatch) {
		AllJsonData.amountToCatch = amountToCatch;
		JSONHandler.UpdatePokeList();
	}

	public static Integer getWaitingTime() {
		return waitingTime;
	}

	public static void setWaitingTime(Integer waitingTime) {
		AllJsonData.waitingTime = waitingTime;
	}
}
