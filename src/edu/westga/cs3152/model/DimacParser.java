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
	
	/**
	 * Parses the CNF file type
	 * @param filename the path to the file
	 * @return a sparse 2d array of clauses
	 * @throws IOException if the file can not be accessed for any reason
	 * @throws IllegalArgumentException if the file is not DICMAS standard
	 */
	public static Tuple<int[][], int[][]> parseCnf(String filename) throws IOException {
		int[][] problemSet = null;
		File file = new File(filename);
		int clauseCounter = 0;
		int clauses = 0;
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
					clauses = Integer.parseInt(values[3]);
					problemSet = new int[clauses][];
					continue;
				}
				if (line.charAt(line.length() - 1) != '0') {
					throw new IllegalArgumentException();
				}
				if (clauseCounter >= clauses) {
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
			if (clauseCounter != clauses) {
				throw new IllegalArgumentException();
			}
		} catch (Exception exception) {
			throw new IllegalArgumentException("File not DIMACS complient");
		}
		Tuple<int[][], int[][]> tuple = new Tuple<int[][], int[][]>(problemSet, null);
		return tuple;
	}
}
