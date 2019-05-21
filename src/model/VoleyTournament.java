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
		int times = 0;
		while(line != null){
			String[] parts = line.split(",");
			/*URL url = new URL(parts[6]);
			URLConnection conn = url.openConnection();*/
			Participant nParticipant = new Participant(Integer.parseInt(parts[0]),parts[1],parts[2],parts[3],parts[4],parts[5]);
			addSpectatorIntoTree(nParticipant);
			line = br.readLine();
			times++;

			

		}
		fileReader.close();
		br.close();
		
			int m=(int)(times*0.5);
			for(int i=0;i<m;i++) {
			addingOficialParticipants(root);
			}
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
	
	 public int counting(Participant current) {    
		  if (current == null) {
			  return 0;
		  } else {
			  return 1 + counting(current.getLeft()) + counting(current.getRigth());
		  }

	}
	 
	 
	 public Participant searchSpectador(int id) {
			Participant searching= new Participant(id,"","","","","");
			return searchSpectador(root,searching);
		}
		
		private Participant searchSpectador(Participant current, Participant searching) {
			if(current!=null) {
				if(searching.compareTo(current)<0) {
					if(current.getLeft()!=null){
						return searchSpectador(current.getLeft(),searching);
					}else {
						return searchSpectador(current.getRigth(), searching);
					}
				}else if(searching.compareTo(current)>0){
					if(current.getRigth()!=null) {
						return searchSpectador(current.getRigth(), searching);
					}else {
						return searchSpectador(current.getLeft(), searching);
					}
				}else {
					return current;
				}
			}
			return current;
		}
		
		public Participant searchParticipant(int id) throws NullPointerException{ 
				
				boolean found = false;
				Participant searched = null;
			
			    while (first != null && !found) {
			        if(first.getId() == id) {
			        	found = true;
			        	searched = first;
			        } 
			        
			        first = first.getNext();
			    }
			return searched;	
		}
		
	
		public void addingOficialParticipants(Participant p){
			if(first == null){
				first = p;
			}else{
				Participant current = first;
				while(current.getNext() != null){
					current = current.getNext();
				}
				current.setNext(p);
				Participant temp = current;
				current = current.getNext();
				current.setPrev(temp);
			}
		}
	
}
