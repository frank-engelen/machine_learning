package de.codecentric.ml;

import java.io.File;

import com.rapidminer.RapidMiner;
import com.rapidminer.RapidMiner.ExecutionMode;
import com.rapidminer.example.Attributes;
import com.rapidminer.example.Example;
import com.rapidminer.example.set.SimpleExampleSet;
import com.rapidminer.operator.IOContainer;
import com.rapidminer.operator.IOObject;
import com.rapidminer.repository.IOObjectEntry;
import com.rapidminer.repository.RepositoryLocation;

public class MainClassifier {

	public static void main(String[] args) throws Exception {
		// Path to process-definition
		final String processPath = "/machine_learning/rapidminer_repo/03-text-classification-in-Java.rmp";

		// Init RapidMiner
		RapidMiner.setExecutionMode(ExecutionMode.COMMAND_LINE);
		RapidMiner.init();

		// Load process
		final com.rapidminer.Process process = new com.rapidminer.Process(new File(processPath));

		// Load learned model
		final RepositoryLocation locWordList = new RepositoryLocation("//My Machine Learning Repo/02-text-processdata/20-newsgroups.model");
		final IOObject wordlist = ((IOObjectEntry) locWordList.locateEntry()).retrieveData(null);

		// Load Wordlist
		final RepositoryLocation locModel = new RepositoryLocation("//My Machine Learning Repo/02-text-processdata/20-newsgroups.wordlist");
		final IOObject model = ((IOObjectEntry) locModel.locateEntry()).retrieveData(null);

		// Execute Classification process with learned model and wordlist as
		// input. Additionally expects files in
		// /machine_learning/data/03-20_newsgroup_java_in
		final IOContainer ioInput = new IOContainer(new IOObject[] { wordlist, model });
		process.run(ioInput);
		process.run(ioInput);
		final long start = System.currentTimeMillis();
		final IOContainer ioResult = process.run(ioInput);
		final long end = System.currentTimeMillis();
		System.out.println("T:" + (end - start));

		// Print some results
		final SimpleExampleSet ses = ioResult.get(SimpleExampleSet.class);
		for (int i = 0; i < Math.min(5, ses.size()); i++) {
			final Example example = ses.getExample(i);
			final Attributes attributes = example.getAttributes();

			final String id = example.getValueAsString(attributes.getId());
			final String prediction = example.getValueAsString(attributes.getPredictedLabel());

			System.out.println("Path: " + id + ":\tPrediction:" + prediction);
		}
	}

}
