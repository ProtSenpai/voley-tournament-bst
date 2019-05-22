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
		}
		fileReader.close();
		br.close();

    	choiceAleatoryParticipants(times);
		
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

		public Participant searchParticipant(int id) throws NullPointerException{ 
				
				boolean found = false;
				Participant searched = null;
			
			    while (first != null && found) {
			        if(first.getId() == id) {
			        	found = true;
			        	searched = first;
			        } 
			        
			        first = first.getNext();
			    }
			return searched;	
		}
		
		public void choiceAleatoryParticipants(int size) {
			
			int m=(int)(size*0.5);
			for(int i=0;i<m;i++) {
				int n=(int) (Math.random() * size) + 1;
				Participant s=searchSpectador(n);
				addingOficialParticipants(s);
			}
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
		
		public String showParticipantbyCountry(String country) {
			Participant p = first;
			String msg = "";
			
			if(p == null) {
				msg = "The participant wasn't found";
			} else {
				Participant current = p;
				while(current != null ) {
					if(current.getCountry() == country) {
					msg = current.getFirstName() + "\n" + current.getCountry() + "\n" + current.getId();
					
					} else {
						current = current.getNext();
					}
				}
			}
			
			return msg;
			
		}

	
}
