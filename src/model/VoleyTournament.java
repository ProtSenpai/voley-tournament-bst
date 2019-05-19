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
			Participant nParticipant = new Participant(Integer.parseInt(parts[0]),parts[1],parts[2],parts[3],parts[4],parts[5]);
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
	
	 public void convertToList(Participant root){
		 
		 int total = counting(root)/2;
		 int times = 0;
		 
		 while(times<=total) {
	     if(root == null) {
	         return;
	     }
	   
	     convertToList(root.getLeft());
	     if(first == null) {
	         first = root;
	     }
	     Participant prev = root.getPrev();
	     if(first.getPrev() == null) {
	    	 first.setPrev(root);
	     } else {
	         root.setLeft(first.getPrev());
	         prev = root.getPrev();
	         prev.setRigth(root);
	     }
	     prev = root;
	     convertToList(root.getRigth());
	     if(root.getRigth() == null) {
	         first = root;
	     }
	     times++;
		 }
	 }
	 
	 public int counting(Participant current) {    
		  if (current == null) {
			  return 0;
		  } else {
			  return 1 + counting(current.getLeft()) + counting(current.getRigth());
		  }

	}
	 
	 public Participant searchSpectador(int id) {
			Participant s= new Participant(id,"","","","","");
			return searchSpectador(root,s);
		}
		
		private Participant searchSpectador(Participant current, Participant s) {
			if(current!=null) {
				if(s.compareTo(current)<0) {
					if(current.getLeft()!=null){
						return searchSpectador(current.getLeft(),s);
					}else {
						return searchSpectador(current.getRigth(), s);
					}
				}else if(s.compareTo(current)>0){
					if(current.getRigth()!=null) {
						return searchSpectador(current.getRigth(), s);
					}else {
						return searchSpectador(current.getLeft(), s);
					}
				}else {
					return current;
				}
			}
			return current;
		}
	
}
