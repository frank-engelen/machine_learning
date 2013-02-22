package de.codecentric.ml;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.io.Files;

public class ClearMetadataFromFiles {

	private static final Charset CHARSET = Charset.forName("ISO-8859-1");

	public static void main(String[] args) throws Exception {

		// final File inPath = new
		// File("C:\\machine_learning\\data\\02-20_newsgroup_training\\");
		final File inPath = new File("C:\\machine_learning\\data\\03-20_newsgroup_java_in\\");

		System.out.println("Main: " + inPath.getAbsolutePath());
		System.out.println();
		final File[] newsgroupDirs = inPath.listFiles();
		for (final File newsgroupDir : newsgroupDirs) {

			if (!newsgroupDir.isDirectory()) {
				System.out.println("Skipping " + newsgroupDir.getAbsolutePath());
				continue;
			}

			System.out.println("Processing " + newsgroupDir.getAbsolutePath());
			for (final File file : newsgroupDir.listFiles()) {

				if (!file.isFile()) {
					System.out.println(" ... skipping " + file.getName());
					continue;
				}

				System.out.println(" ... processing " + file.getName());

				final List<String> inLines = Files.readLines(file, CHARSET);
				final StringBuilder output = new StringBuilder();

				boolean emptyLineFound = false;
				for (String line : inLines) {
					if (emptyLineFound) {
						output.append(line + "\n");
					} else if (line.isEmpty()) {
						emptyLineFound = true;
					} else if (startsWithRelevantHeader(line)) {
						output.append(line + "\n");
					}
				}

				Files.write(output, file, CHARSET);
			}
		}
	}

	private static boolean startsWithRelevantHeader(String line) {

		final String[] headers = { "Subject:", "Lines:", "From:", "Keywords:", "Summary:" };
		for (final String h : headers) {
			if (line.startsWith(h)) {
				return true;
			}
		}
		return false;
	}

}
