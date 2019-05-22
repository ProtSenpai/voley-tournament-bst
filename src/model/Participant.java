package model;


public class Participant implements Comparable<Participant> {

	//Attributes
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String country;
	
	
	//Relations bst
	private Participant left;
	private Participant rigth;
	
	//Relations linked list
	private Participant next;
	private Participant prev;
	
	
	//Constructor
	
	public Participant(int id, String firstName, String lastName, String email, String gender, String country) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.country = country;
	
	}

	// Getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public Participant getLeft() {
		return left;
	}

	public void setLeft(Participant left) {
		this.left = left;
	}

	public Participant getRigth() {
		return rigth;
	}

	public void setRigth(Participant rigth) {
		this.rigth = rigth;
	}

	public Participant getNext() {
		return next;
	}

	public void setNext(Participant next) {
		this.next = next;
	}

	public Participant getPrev() {
		return prev;
	}

	public void setPrev(Participant prev) {
		this.prev = prev;
	}
	
	//Comparable
	
	@Override
	public int compareTo(Participant o) {
				
		if(id<o.id) {
			
			return -1;
		} else if (id>o.id) {
			
			return 1;
		}
		
		return 0;
	}
	

	public int nodosCompletos(Participant n) {
        if (n == null)
            return 0;
        else {
            if (n.getLeft() != null && n.getRigth() != null) {
                return nodosCompletos(n.getLeft()) + nodosCompletos(n.getRigth()) + 1;
            }
            
            return nodosCompletos(n.getLeft()) + nodosCompletos(n.getRigth());
        }
        
	}


}
