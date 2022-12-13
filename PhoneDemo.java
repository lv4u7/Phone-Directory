import java.util.*;
import java.io.*;
/**
*	Project : 
*		Phone directory.
*	By: 
*		Syed Muhammad Raza 023-21-0121.
*		Aba Salat 023-21-0167.
*	Class-Section:
*		BSCS-3-E.
*	Submitted to:
*		Mr.Saif Hassan.
**/

class Phone implements Serializable {
	
	String first;
	String last;
	String number;
	
	Phone(String first,String last,String number) {
		this.first = first;
		this.last = last;
		this.number = number;
	}
	
	public String toString() {
		return first+" "+last+" "+number;
	}
}

class PhoneDemo {
	
	public static boolean isNumeric(String string) {
		
		int intValue;
		String.format("Parsing string: \"%s\"", string);
		if(string == null || string.equals("")) {
			System.out.println("null input.");
			return false;
		}
		try {
			intValue = Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Input String is not numeric.");
		}
		return false;
	}
	
	public static void main(String args[]) throws Exception {
		
		int choice = -1;
		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		ArrayList<Phone> al = new ArrayList<Phone>(); // to save names and number
		File file = new File("Record.txt");
		File log = new File("Log.txt");
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		ListIterator li = null;
		Stack<String> st = new Stack<String>(); // for call log
		
		if(file.isFile()) { 
			// check if file is avaiavle so we read it to array list
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
			ois.close();
		} // if file not exist we will write in it
		
		do{
			System.out.println();
			System.out.println("1.INSERT");
			System.out.println("2.DISPLAY");
			System.out.println("3.SEARCH");
			System.out.println("4.DELETE");
			System.out.println("5.UPDATE");
			System.out.println("6.SORT BY FIRST NAME ASCENDING ON SCREEN");
			System.out.println("7.SORT BY FIRST NAME DESCENDING ON SCREEN");
			System.out.println("8.SORT BY FIRST NAME ASCENDING ON FILE");
			System.out.println("9.SORT BY FIRST NAME DESCENDING ON FILE");
			System.out.println("10.CALL NUMBER");
			System.out.println("11.SHOW RECENTLY CALLED NUMBER");
			System.out.println("0.EXIT");
			System.out.println();
			System.out.print("ENTER YOUR CHOICE:");
			while(true) {
				try {
					choice = s.nextInt();
					break;
				}
				catch(InputMismatchException e) {
					System.out.println("INVALID input \nENTER YOUR CHOICE AGAIN: ");
					s.next(); // skip the invalid token
				}
			}
			System.out.println();
			switch(choice) {
				case 1: // insert new record
					System.out.println("How many numbers you want to add: ");
					int n = 0;
					while(true) {
						try {
							n = s.nextInt();
							break;
						}
						catch(InputMismatchException e) {
							System.out.println("INVALID input \nHow many numbers you want to add: ");
							s.next(); // skip the invalid token
						}
					}
					for(int i=0;i<n;i++) {
						System.out.print("Enter first name:");
						String first = s1.next();
				
						System.out.print("Enter last name:");
						String last = s1.next();
					
						System.out.print("Enter number:");
						String number = s1.next();
						
						while(!isNumeric(number)) {
							System.out.println();
							System.out.print("INVALID ...! Enter number again: ");
							number = s1.next();
						}
						boolean found = false;
						li = al.listIterator();
						while(li.hasNext()) {
							Phone p1 = (Phone)li.next();
							if(p1.number.equals(number)) {
								found = true;
							}else{
									found = false;
							}
						}
						while(found == true) {
							System.out.print("This number is already exist,\n");
							System.out.print("Enter number again: ");
							number = s1.next();
							li = al.listIterator();
							while(li.hasNext()) {
							Phone p1 = (Phone)li.next();
								if(p1.number.equals(number)) {
									found = true;
								}else{
									found = false;
								}
							}
						}
						if(!found) {
							al.add(new Phone(first,last,number));
						}
						
						
					}
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
				break;
				case 2: // display records
					if(file.isFile()) { 
					// check if file is avaiavle so we read it to array list and print it
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
						ois.close();
						
						System.out.println("..............................");
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
						}
						System.out.println("..............................");
					}else {
						System.out.println("File not exist..");
					}
				break;
				case 3: // search 
					System.out.println("1.SEARCH BY NUMBER.");
					System.out.println("2.SEARCH BY NAME.");
					System.out.print("ENTER YOUR CHOICE:");
					int flag = 0;
					while(true) {
						try {
							flag = s.nextInt();
							break;
						}
						catch(InputMismatchException e) {
							System.out.println("INVALID input \nENTER YOUR CHOICE AGAIN: ");
							s.next(); // skip the invalid token
						}
					}
					switch(flag) {
						case 1: // search by number
							String number = "";
							// to search we need to write the data from file to colloction
							if(file.isFile()) {
								if(file.isFile()) { 
								// check if file is avaiavle so we read it to array list
								ois = new ObjectInputStream(new FileInputStream(file));
								al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
								ois.close();
								}
								
								boolean found = false;
								System.out.println("Enter phone number to search");
								number = s1.next();
								
								while(!isNumeric(number)) {
									System.out.println();
									System.out.print("Enter number again: ");
									number = s1.next();
								}	
								
								System.out.println("..............................");
								li = al.listIterator();
								while(li.hasNext()) {
									Phone p = (Phone)li.next();
									if(p.number.equals(number)) {
										System.out.println(p);
										found = true;
									}
								}
								if(!found) {
									System.out.println("Record not found...");
								}
								System.out.println("..............................");
							} else {
								System.out.println("File not exist..");
							}
						break;
						case 2: // search by name
							String fname = "";
							// to search we need to write the data from file to colloction
							if(file.isFile()) {
								if(file.isFile()) { 
								// check if file is avaiavle so we read it to array list
								ois = new ObjectInputStream(new FileInputStream(file));
								al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
								ois.close();
								}
								
								boolean foundf = false;
								System.out.println("Enter name to search");
								fname = s1.next();
								System.out.println("..............................");
								li = al.listIterator();
								while(li.hasNext()) {
									Phone p = (Phone)li.next();
									if(p.first.equals(fname)) {
										System.out.println(p);
										foundf = true;
									}
								}
								if(!foundf) {
									System.out.println("Record not found...");
								}
								System.out.println("..............................");
							} else {
								System.out.println("File not exist..");
							}
						break;
					}
				break;
				case 4: // delete
					String numbers = "";
					// to search we need to write the data from file to colloction
					if(file.isFile()) {
						if(file.isFile()) { 
						// check if file is avaiavle so we read it to array list
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
						ois.close();
						}
						
						boolean found = false;
						System.out.println("Enter phone number to delete");
						numbers = s1.next();
						
						while(!isNumeric(numbers)) {
							System.out.println();
							System.out.print("Enter number again: ");
							numbers = s1.next();
						}
						
						System.out.println("..............................");
						li = al.listIterator();
						while(li.hasNext()) {
							Phone p = (Phone)li.next();
							if(p.number.equals(numbers)) {
								li.remove();
								found = true;
							}
						}
						if(found) { 
						// delete from linked list and update your file
							// update out file
							oos = new ObjectOutputStream(new FileOutputStream(file));
							oos.writeObject(al);
							oos.close();
							System.out.println("Deleted succesfully>>");
						} else {
							System.out.println("Record not found...");
						}
						System.out.println("..............................");
					} else {
						System.out.println("File not exist..");
					}
				break;
				case 5: // reupdate
					String numberp = "";
					// to search we need to write the data from file to colloction
					if(file.isFile()) {
						if(file.isFile()) { 
						// check if file is avaiavle so we read it to array list
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
						ois.close();
						}
						
						boolean found = false;
						System.out.println("Enter phone number to update");
						numberp = s1.next();
						while(!isNumeric(numberp)) {
							System.out.println();
							System.out.print("Enter number again: ");
							numberp = s1.next();
						}
						System.out.println("..............................");
						li = al.listIterator();
						while(li.hasNext()) {
							Phone p = (Phone)li.next();
							if(p.number.equals(numberp)) {
								System.out.print("Enter new first name: ");
								String fst = s2.nextLine();
								System.out.print("Enter new last name: ");
								String lst = s2.nextLine();
								System.out.print("Enter new number: ");
								String num = s2.nextLine();
								while(!isNumeric(num)) {
									System.out.println();
									System.out.print("Enter number again: ");
									num = s1.next();
								}
								li.set(new Phone(fst,lst,num));
								found = true;
							}
						}
						if(found) { 
						// delete from linked list and update your file
							// update out file
							oos = new ObjectOutputStream(new FileOutputStream(file));
							oos.writeObject(al);
							oos.close();
							System.out.println("updated succesfully>>");
						} else {
							System.out.println("Record not found...");
						}
						System.out.println("..............................");
					} else {
						System.out.println("File not exist..");
					}
				break;
				case 6: //  sort asc screen
					if(file.isFile()) { 
					// check if file is avaiavle so we read it to array list and print it
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
						ois.close();
						Collections.sort(al, new Comparator<Phone>() { 
						// just sort on screen not in file
							// on which bases we will sort
							public int compare(Phone p1,Phone p2) {
								return p1.first.compareTo(p2.first);
							}
						});
						System.out.println("..............................");
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
						}
						System.out.println("..............................");
					}else {
						System.out.println("File not exist..");
					}
				break;
				case 7: // sort des screen
					if(file.isFile()) { 
					// check if file is avaiavle so we read it to array list and print it
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
						ois.close();
						Collections.sort(al, new Comparator<Phone>() { 
						// just sort on screen not in file
							// on which bases we will sort
							public int compare(Phone p1,Phone p2) {
								return p2.first.compareTo(p1.first);
							}
						});
						System.out.println("..............................");
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
						}
						System.out.println("..............................");
					}else {
						System.out.println("File not exist..");
					}
				break;
				case 8: // sort asc file + screen
					if(file.isFile()) { 
					// check if file is avaiavle so we read it to array list and print it
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
						ois.close();
						Collections.sort(al, new Comparator<Phone>() { 
						// sort on file
							// on which bases we will sort
							public int compare(Phone p1,Phone p2) {
								return p1.first.compareTo(p2.first);
							}
						});
						// writing the changes on the file
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						
						System.out.println("..............................");
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
						}
						System.out.println("..............................");
					}else {
						System.out.println("File not exist..");
					}
				break;
				case 9: // sert des file + screen
					if(file.isFile()) { 
					// check if file is avaiavle so we read it to array list and print it
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
						ois.close();
						Collections.sort(al, new Comparator<Phone>() {
							// on which bases we will sort
							public int compare(Phone p1,Phone p2) {
								return p2.first.compareTo(p1.first);
							}
						});
						// writing the changes on the file
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						
						System.out.println("..............................");
						li = al.listIterator();
						while(li.hasNext()) {
							System.out.println(li.next());
						}
						System.out.println("..............................");
					}else {
						System.out.println("File not exist..");
					}
				break;
				case 10: // call number
					
					String numberc = "";
					// if the number exist in our record it will show otherwise we will call directly
						if(file.isFile()) { 
						// check if file is avaiavle so we read it to array list
						ois = new ObjectInputStream(new FileInputStream(file));
						al = (ArrayList<Phone>)ois.readObject(); // cast to arraylsit
						ois.close();
						}
						
						boolean found = false;
						System.out.println("Enter phone number to call");
						numberc = s1.next();
						while(!isNumeric(numberc)) {
							System.out.println();
							System.out.print("Enter number again: ");
							numberc = s1.next();
						}
						System.out.println("..............................");
						li = al.listIterator();
						while(li.hasNext()) {
							Phone p = (Phone)li.next();
							if(p.number.equals(numberc)) {
								System.out.println(">>> CALLING......... <<<");
								System.out.println(p);
								st.add(numberc);
								found = true;
							}
						}
						if(!found) {
							System.out.println("Number is not exist in our record...");
							System.out.println(">>> CALLING......... <<<");
							st.add(numberc);
						}
						System.out.println("..............................");
						
						oos = new ObjectOutputStream(new FileOutputStream(log));
						oos.writeObject(st);
						oos.close();
						
				break;
				case 11:  // recently called
					if(log.isFile()) {
						
						if(file.isFile()) { 
						// check if file is avaiavle so we read it to the stack
						ois = new ObjectInputStream(new FileInputStream(log));
						st = (Stack<String>)ois.readObject(); // cast to Stack
						ois.close();
						}
						
						if(st.empty()) {
							System.err.println("Stack Empty....!");
						} else
							System.out.println("last number you have called is: "+st.peek());
						System.out.println();
					}
					else {
						System.out.println("File not exist..");
					}
				break;
				default:
					if(choice != 0)
						System.out.println("INVALID CHOICE ENTER AGAIN....!");
					System.out.println();
				break;
			}
		}while(choice != 0);
	}
}
