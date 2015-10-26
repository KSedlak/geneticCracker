package geneticCracker.logic.textReader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import geneticCracker.App;

public class fileToString {



public static String getFileFromDirectory(String dir){

	URI path = null;
	try {
		path = App.class.getResource(dir).toURI();
	} catch (URISyntaxException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	  String content = null;
	try {
		content = new String(Files.readAllBytes(Paths.get(path)));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return content.toUpperCase();
}


public static String getFileFromDirectory(URI dir){

  String content = null;
try {
	content = new String(Files.readAllBytes(Paths.get(dir)));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  return content;
}



}
