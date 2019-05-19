package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class VoleyTournament {

	
	//Relations
	private Participant root;
	private Participant first;
	//Root to show the ABB
	private Participant showRoot;
	
	

	public static final String PATH = "data/information.csv";


	//Constructor
	public VoleyTournament() {

	}
	
	
	
	public Participant getRoot() {
		return root;
	}



	public void setRoot(Participant root) {
		this.root = root;
	}



	public Participant getFirst() {
		return first;
	}



	public void setFirst(Participant first) {
		this.first = first;
	}



	public Participant getShowRoot() {
		return showRoot;
	}



	public void setShowRoot(Participant showRoot) {
		this.showRoot = showRoot;
	}



	public void LoadFileAndAddToTree(String path) throws IOException {
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line = br.readLine();
		line = br.readLine();
		while(line != null){
			String[] parts = line.split(",");
			/*URL url = new URL(parts[6]);
			URLConnection conn = url.openConnection();*/
			Participant nParticipant = new Participant(Integer.parseInt(parts[0]),parts[1],parts[2],parts[3],parts[4],parts[5],null,parts[7]);
			addSpectatorIntoTree(nParticipant);
			line = br.readLine();
		}
		fileReader.close();
		br.close();
		
	}



	public void addSpectatorIntoTree(Participant p) {
		addSpectatorIntoTree(p, root);
	}
	
	private void addSpectatorIntoTree(Participant p, Participant current) {
		if(root == null) {
			root = p;
		}
		else {
			if(p.compareTo(current) <= 0) {
				if(current.getLeft() == null) {
					current.setLeft(p);
				}else{
					addSpectatorIntoTree(p, current.getLeft());
				}
			} else{
				if(current.getRigth() == null) {
					current.setRigth(p);
				} else {
					addSpectatorIntoTree(p, current.getRigth());
				}
			}
			
		}
	}
	
}
