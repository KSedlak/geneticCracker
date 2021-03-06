package geneticCracker.logic.crosser.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import geneticCracker.logic.DNA.Key;
import geneticCracker.logic.creature.Creature;
import geneticCracker.logic.crosser.Crosser;
import geneticCracker.logic.fitnesser.FitnessMaker;
import geneticCracker.logic.fitnesser.FitnesserOnlyFrequentWord;
import geneticCracker.logic.mutator.Mutator;
import geneticCracker.logic.utils.RadnomIndexesGenerator;

@Service
public class SimpleRandomCross implements Crosser {



	double crossPoints=50;


	@Autowired
	Mutator mutator;
	List<Creature> childs=new ArrayList<Creature>();
	private Logger logger=Logger.getLogger(getClass());

	@Override
	public List<Creature> makeChild(Creature a, Creature b, FitnessMaker fit){
		//logger.info("STart child making");
		childs.clear();
		int dnaLength=b.getDna().getKey().length;
		int cr=(int) (crossPoints*dnaLength/100);
			List<Integer> indexes=RadnomIndexesGenerator.
					generateRandomIndexes(cr,dnaLength-1);
		//	logger.info("Indeexes generated");
			logger.info("Crosser:");
		logger.info("ParentA: "+a);
		logger.info("ParentB: "+b);


	Key dnaA=a.getDna().getCopy();
	Key dnaB=b.getDna().getCopy();

	Object[] aTable=new Object[dnaA.getKey().length];

	List<Object> childAddedA=new ArrayList<Object>();

	Object[] bTable=new Object[dnaA.getKey().length];
	List<Object> childAddedB=new ArrayList<Object>();
	for(Integer idx:indexes){
		childAddedA.add(dnaB.getKey()[idx]);

		aTable[idx]=dnaB.getKey()[idx];
		childAddedB.add(dnaA.getKey()[idx]);
		bTable[idx]=dnaA.getKey()[idx];

	}
//	logger.info("Table init Indexes: "+indexes.size());
/*	logger.info("A tab");
	logTable(aTable);

	logger.info("b tab");
*/
//	logTable(bTable);

List<Object> av1=new ArrayList<Object>();
for(Object o:dnaA.getKey()){
	if(!childAddedA.contains(o)){
	av1.add(o);}
}

List<Object> av2=new ArrayList<Object>();
for(Object o:dnaB.getKey()){
	if(!childAddedB.contains(o)){
	av2.add(o);}
}


for(int i=0;i<aTable.length;i++){

	if(aTable[i]==null){
		aTable[i]=av1.remove(0);
	//	logger.info("A kopiuje");
	}
	else{
	//	logger.info("A jest");
	}
	if(bTable[i]==null){
		bTable[i]=av2.remove(0);
	//	logger.info("B kopiuje");
	}
	else{
	//	logger.info("B jest");
	}


//logger.info("A: "+aTable[i]+" B: "+bTable[i]);
}
/*logger.info("A tab");
logTable(aTable);

logger.info("b tab");

logTable(bTable);*/
//logger.info("Generated");

dnaA.setKey(aTable);
dnaB.setKey(bTable);


		Creature first=new Creature(a.getText(),dnaA);
		Creature second=new Creature(a.getText(),dnaB);




		childs.add(first);
		childs.add(second);



		for(Creature c:childs){
			mutator.mutate(c);
			fit.testCreatureInLife(c);
		}
		logger.info("Child A: "+first);
		logger.info("Child B: "+second);

		return new ArrayList<Creature>(childs);



	}


private int addToUniqueList(List<Object> lis, Object o){
	if(!lis.contains(o)){
		lis.add(o);
	}
	return lis.size();
}

private void logTable(Object[] x){
	String res="";
	for(Object o:x){
		res=res+" "+o;
	}
	logger.info(res);
}
}
