package edu.westga.cs3152.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Parses cnf files
 * @author CodyVollrath
 * @version Fall 2021
 */
public class DimacParser {
	private static final char PROBLEM_LINE_INDICATOR = 'p';
	private static final char COMMENT_LINE_INDICATOR = 'c';
	private int variables;
	private int clauses;
	private int[][] literals;
	private int[][] clauseInstances;
	
	/**
	 * Parses the CNF file type
	 * @param filename the path to the file
	 * @throws IOException if the file can not be accessed for any reason
	 * @throws IllegalArgumentException if the file is not DICMAS standard
	 */
	public void parseCnf(String filename) throws IOException {
		int[][] problemSet = null;
		int[][] clausesSet = null;
		File file = new File(filename);
		int clauseCounter = 0;
		this.clauses = 0;
		this.variables = 0;
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNext()) {
				String line = scan.nextLine().trim();
				if (line.equals("")) {
					continue;
				}
				if (line.charAt(0) == COMMENT_LINE_INDICATOR) {
					continue;
				}
				if (line.charAt(0) == PROBLEM_LINE_INDICATOR) {
					String[] values = line.split(" ");
					this.variables = Integer.parseInt(values[2]);
					this.clauses = Integer.parseInt(values[3]);
					problemSet = new int[this.clauses][];
					continue;
				}
				if (line.charAt(line.length() - 1) != '0') {
					throw new IllegalArgumentException();
				}
				if (clauseCounter >= this.clauses) {
					break;
				}
				String[] values = line.split(" ");
				problemSet[clauseCounter] = new int[values.length - 1];
				int element = Integer.parseInt(values[0]);
				int index = 0;
				while (index < values.length - 1) {
					problemSet[clauseCounter][index] = element;
					index++;
					element = Integer.parseInt(values[index]);
				}
				clauseCounter++;
			}
			if (clauseCounter != this.clauses) {
				throw new IllegalArgumentException();
			}
		} catch (Exception exception) {
			throw new IllegalArgumentException("File not DIMACS complient");
		}
		clausesSet = this.getClauses(problemSet);
		this.literals = problemSet;
		this.clauseInstances = clausesSet;
	}
	
	private int[][] getClauses(int[][] problemSet) {
		int[][] variableClauses = new int[this.variables][];
		int[] tempArry = new int[this.variables];
		int[] valueOccurences = new int[this.variables];
		for (int row = 0; row < problemSet.length; row++) {
			for (int col = 0; col < problemSet[row].length; col++) {
				int variable = Math.abs(problemSet[row][col]) - 1;
				valueOccurences[variable]++;
			}
		}
		
		for (int index = 0; index < valueOccurences.length; index++) {
			variableClauses[index] = new int[valueOccurences[index]];
		}
		
		for (int row = 0; row < problemSet.length; row++) {
			for (int col = 0; col < problemSet[row].length; col++) {
				int offset = Math.abs(problemSet[row][col]) - 1;
				int index = tempArry[col];
				
				int clauseNumber = (row + 1);
				int clauseNumberNegated = clauseNumber * -1;
				variableClauses[offset][index] = problemSet[row][col] < 0 ? clauseNumberNegated : clauseNumber;
				
				tempArry[offset]++;
			}
		}
		return variableClauses;
	}
	
	/**
	 * Gets variable count
	 * @return the variable count
	 */
	public int getVariables() {
		return this.variables;
	}
	
	/**
	 * Gets the clause count
	 * @return the clause count
	 */
	public int getClauses() {
		return this.clauses;
	}
	
	/**
	 * Gets the literals count
	 * @return the literal count
	 */
	public int[][] getLiterals() {
		return this.literals;
	}
	
	/**
	 * Gets the clause instances
	 * @return the clause instances
	 */
	public int[][] getClauseInstances() {
		return this.clauseInstances;
	}
}
