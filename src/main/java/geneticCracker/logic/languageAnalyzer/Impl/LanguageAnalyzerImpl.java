package geneticCracker.logic.languageAnalyzer.Impl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import geneticCracker.App;
import geneticCracker.logic.languageAnalyzer.LanguageAnalyzer;
import geneticCracker.logic.ngramer.Ngramer;
import geneticCracker.logic.ngramer.Impl.NgramerImpl;
import geneticCracker.logic.textReader.fileToString;

@Component
public class LanguageAnalyzerImpl implements LanguageAnalyzer {

	private LinkedHashMap<String, Integer> map;

	List<Path> filesToAnalyze;


	@Autowired
	Ngramer ngramer;


	public LanguageAnalyzerImpl() {
		super();
		filesToAnalyze=new ArrayList<Path>();
		map = new LinkedHashMap<String, Integer>();
	}



	private void filterFiles(Path path) throws Exception {

		Files.list(path).filter(p -> p.getFileName().toString().endsWith(".txt"))
				.forEach(files -> filesToAnalyze.add(files));
	}



	public LinkedHashMap<String, Integer> analyze(Path path, int length){
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
	public LinkedHashMap<String, Integer> getGlobalFrequencyMap(String path, int length) {

		Path dir = null;
		try {
			dir = Paths.get(App.class.getResource(path).toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		analyze(dir, length);

		return sortByValues(map);
	}


	public static <String extends Comparable,Integer extends Comparable> LinkedHashMap<String,Integer> sortByValues(LinkedHashMap<String,Integer> map){
	    LinkedList<Entry<String, Integer>> entries = new LinkedList<Map.Entry<String,Integer>>(map.entrySet());

	    Collections.sort(entries, new Comparator<Entry<String,Integer>>() {


			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				int val=(int) o1.getValue();
				int val2=(int) o2.getValue();
				if(val==val2){
					return o1.getKey().compareTo(o2.getKey())*(-1);
				}
				return o1.getValue().compareTo(o2.getValue())*(-1);
				
			}
	    });

	    LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();
    	
    for(Map.Entry<String,Integer> entry: entries){
        sorted.put(entry.getKey(), entry.getValue());
    }
 
    return sorted;
	}
	public Ngramer getNgramer() {
		return ngramer;
	}

	@Override
	public List<String> getNmostFrequentNgrams(String path, int lengthNgram, int amount){
		return getGlobalFrequencyMap(path, lengthNgram).entrySet().stream()
			    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			    .limit(amount)
			    .map(ent->ent.getKey())
			    .collect(Collectors.toList());
		
	}





	@Override
	public void setNgramer(Ngramer ngramerImpl) {
ngramer=ngramerImpl;
	}

	@Override
	public List<String> getMostFrequentWords(String lang) throws IOException{
	String change=	"text/learn/"+lang+"/frequentWords";

	Path dir = null;
	try {
		dir = Paths.get(App.class.getResource(change).toURI());
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Path pa =Files.list(dir).filter(p -> p.getFileName().toString().endsWith(".txt")).findFirst().get();
	

	
	  
	List<String> list = Files.lines(pa).map(s -> s.trim()).collect(Collectors.toList());


	
	return list;

	
		
	}










}
