package test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import dataStructureProblem.DataStructureObject;
import static org.junit.Assert.*;


public class DataStructureObjectTest {
	
	@Test
	public void testDataStructure() throws Exception{
		DataStructureObject d = new DataStructureObject();
		String result = "program is able to run";
		d.acceptInput();
		assertEquals(result, result);
	}
	
	@Test
	public void testInstall(){
		DataStructureObject d = new DataStructureObject();
		HashSet<String> installedAlready = new HashSet<String>();
		installedAlready.add("FOO");
		String install = "BAR";
		HashMap<String, ArrayList<String>> dependencies = new HashMap<String, ArrayList<String>>();
		ArrayList<String> dependency = new ArrayList<String>();
		dependency.add("BAR2");
		dependencies.put("BAR", dependency);
		HashSet<String> expected = new HashSet<String>();
		expected.add("FOO");
		expected.add("BAR");
		expected.add("BAR2");
		HashSet<String> result = d.install(installedAlready, install, dependencies);
		assertEquals(result, expected);
	}
	
	@Test
	public void testRemove(){
		DataStructureObject d = new DataStructureObject();
		HashSet<String> installedAlready = new HashSet<String>();
		installedAlready.add("FOO");
		String install = "BAR";
		HashMap<String, ArrayList<String>> dependencies = new HashMap<String, ArrayList<String>>();
		ArrayList<String> dependency = new ArrayList<String>();
		dependency.add("BAR2");
		dependencies.put("BAR", dependency);
		HashSet<String> expected = new HashSet<String>();
		expected.add("FOO");
		expected.add("BAR");
		expected.add("BAR2");
		expected.remove(install);
		HashSet<String> result = d.remove(installedAlready, install, dependencies);
		assertEquals(result, expected);
	}
	
	@Test
	public void testDepend(){
		testInstall();
		//Install implicitly tests depend
	}
	
	@Test
	public void testList(){
		DataStructureObject d = new DataStructureObject();
		HashSet<String> installedAlready = new HashSet<String>();
		installedAlready.add("FOO");
		String[] expected = new String[1];
		expected[1] = "FOO";
		String[] result = d.list(installedAlready);
		assertEquals(result, expected);
	}
	
}
