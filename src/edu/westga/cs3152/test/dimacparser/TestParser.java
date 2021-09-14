package edu.westga.cs3152.test.dimacparser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3152.model.DimacParser;

class TestParser {

	@Test
	void testFirstValid() {
		String fileLocation = "Input/small1.cnf";
		try {
			var parser = new DimacParser();
			parser.parseCnf(fileLocation);
			System.out.println(parser.getLiterals());
			System.out.println(parser.getVariables());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testInvalid() {
		String fileLocation = "Input/invalid.cnf";
		assertThrows(IllegalArgumentException.class, () -> {
			new DimacParser().parseCnf(fileLocation);
		});
	}
	
	@Test
	void testSecondValid() {
		String fileLocation = "Input/small2.cnf";
		try {
			var parser = new DimacParser();
			parser.parseCnf(fileLocation);
			System.out.println(parser.getLiterals());
			System.out.println(parser.getVariables());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testThirdValid() {
		String fileLocation = "Input/small3.cnf";
		try {
			var parser = new DimacParser();
			parser.parseCnf(fileLocation);
			System.out.println(parser.getLiterals());
			System.out.println(parser.getVariables());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
