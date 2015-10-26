package geneticCracker.logic.languageAnalyzer.Impl;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableSortedMap;

import geneticCracker.App;
import geneticCracker.logic.languageAnalyzer.LanguageAnalyzer;
import geneticCracker.logic.ngramer.Ngramer;
import geneticCracker.logic.ngramer.Impl.NgramerImpl;
import geneticCracker.logic.textReader.fileToString;

@Component
public class LanguageAnalyzerImpl implements LanguageAnalyzer {

	private TreeMap<String, Integer> map;

	List<Path> filesToAnalyze;


	@Autowired
	Ngramer ngramer;


	public LanguageAnalyzerImpl() {
		super();
		filesToAnalyze=new ArrayList<Path>();
		map = new TreeMap<String, Integer>();
	}



	private void filterFiles(Path path) throws Exception {

		Files.list(path).filter(p -> p.getFileName().toString().endsWith(".txt"))
				.forEach(files -> filesToAnalyze.add(files));
	}



	public TreeMap<String, Integer> analyze(Path path, int length){
			try {
				filterFiles(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Path p:filesToAnalyze){
			String fileContent =	fileToString.getFileFromDirectory(p.toUri());
			ngramer.ngramText(fileContent, length, map);
			}

			return map;

	}



	@Override
	public TreeMap<String, Integer> getGlobalFrequencyMap(String path, int length) {

		Path dir = null;
		try {
			dir = Paths.get(App.class.getResource(path).toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		analyze(dir, length);

		return map;
	}



	public Ngramer getNgramer() {
		return ngramer;
	}






	@Override
	public void setNgramer(Ngramer ngramerImpl) {
ngramer=ngramerImpl;
	}











}
