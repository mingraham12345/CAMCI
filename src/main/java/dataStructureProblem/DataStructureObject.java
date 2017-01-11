package dataStructureProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DataStructureObject {
	
	//In an enterprise application, there would be a separate file containing ENUMs declaring all text fields. This assists with decoupling and internationalization.
	static String exitText = "END";
	static String welcomeText = "Type " + exitText + " at any time to exit\n";
	static String depend = "DEPEND";
	static String install = "INSTALL";
	static String remove = "REMOVE";
	static String list = "LIST";
	
	
	public void acceptInput() throws Exception{
		
		HashMap<String,ArrayList<String>> dependencies = new HashMap<String,ArrayList<String>>();
		HashSet<String> installed = new HashSet<String>();
		Scanner scanner;
		int welcomeTextInt = 0;
		
		while(true){
		if(welcomeTextInt == 0){
		System.out.println(welcomeText);
		welcomeTextInt = 1;
		}
			 scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			System.out.println(input);
			if(input.equals(exitText)) break;
			String[] inputArray = input.split(" ");
			String firstInput = inputArray[0];
			String secondInput = "";
			switch(firstInput) {
				case "DEPEND":
					secondInput = inputArray[1];
					dependencies = depend(inputArray, secondInput,dependencies);
					break;
				case "INSTALL":
					secondInput = inputArray[1];
					installed = install(installed, secondInput, dependencies);
					break;
				case "REMOVE":
					secondInput = inputArray[1];
					installed = remove(installed, secondInput, dependencies);
					break;
				case "LIST":
					list(installed);
					break;
					default:				
			}		
		}
		}
	
	public static HashMap<String,ArrayList<String>> depend(String[] inputArray, String key, HashMap<String,ArrayList<String>> deps){
		ArrayList<String> dependency = new ArrayList<String>();
		dependency.add("");
		deps.putIfAbsent(key,dependency);
		dependency = deps.get(key);
		deps.put(key,dependency);
		List<String> list = Arrays.asList(inputArray);
		System.out.println(list);
		for(int i = 0; i < list.size(); ++i){
			String s = list.get(i);
			dependency.add(s);
		}
		dependency.remove(depend);
		dependency.remove(key);
		System.out.println(dependency);
		deps.put(key,dependency);
		System.out.println(deps);
		return deps;
	}

	public static HashSet<String> install(HashSet<String> installedAlready, String install, HashMap<String,ArrayList<String>> dependencies) {
		installedAlready.add(install);
		try{
		installedAlready.addAll(dependencies.get(install));
		}catch(Exception e){
		}
		return installedAlready;
	}

	public static HashSet<String> remove(HashSet<String> installedAlready, String install, HashMap<String,ArrayList<String>> dependencies) {
		installedAlready.remove(install);
		try{
		installedAlready.removeAll(dependencies.get(install));
		}catch(Exception e){
		}
		installedAlready = reinstallNeededDependencies(installedAlready, install, dependencies);
		return installedAlready;	
	}
	
	public static HashSet<String> reinstallNeededDependencies(HashSet<String> installedAlready, String install, HashMap<String,ArrayList<String>> dependenices){
		String[] installedArray = installedAlready.toArray(new String[installedAlready.size()]);
		for(int i = 0; i < installedAlready.size(); ++i){
			install(installedAlready, installedArray[i], dependenices);
		}
		return installedAlready;
	}
	
	public static String[] list(HashSet<String> installedAlready){
		String[] installedArray = installedAlready.toArray(new String[installedAlready.size()]);
		for(int i = 0; i < installedArray.length; ++i){
			System.out.println(installedArray[i]);
		}
		return installedArray;
	}

}
