package packageList;

import java.util.Scanner;

public class SelfOrgList {
	public static class node
	{
		Integer data;
		boolean isdeleted;
		node next;
		
		public node ()
		{
			this.data = null;
			this.next = null;
			this.isdeleted=false;
		}
		public node (int data)
		{
			this.data = data;
			this.next = null;
			this.isdeleted=false;
		}
						
	}
	
	public static class ListClass
	{
		private node head;
		
		public ListClass()
		{
			head = new node();
		}
		public void insert(int data) //inserts at the front
		{
			node newNode = new node(data);
			node temp = head.next;
			head.next = newNode;
			newNode.next = temp;
			
		}
		public void locate(int key)
		{
			node temp = head;
			int flag =0;
			while(temp.next!=null)
			{
				if(temp.next.data==key) //found
				{
					flag = 1;
					System.out.println("Found the key!"+" It is next to "+temp.data+" in the list");
					break;
				}
				temp = temp.next;
			}
			//do insertion here -- have flag before
			if (flag == 1)
			{
				node prevNode = temp;
				node keyHolder = prevNode.next;
				prevNode.next = keyHolder.next;
				node nextOfHead = head.next;
				head.next = keyHolder;
				keyHolder.next = nextOfHead;
				System.out.println("Printing the list after location!");
				printList();
				
			}
			else
				System.out.println("Key not found in list");
			
			
		}
		public void delete(int toDelete)
		{
			node temp = head.next;
			while(temp!=null)
			{
				if(temp.data==toDelete)
				{
					temp.isdeleted = true;
					System.out.println("Set delete = true for "+temp.data+"");
					break;
				}
				temp = temp.next;
			}
		}
		public void purge()
		{
			node temp = head;
			while(temp!=null)
			{
				if(temp.next!=null && temp.next.isdeleted==true)
				{
					System.out.println("Deleted : "+ temp.next.data);
					temp.next = temp.next.next;
				}
				if(temp.isdeleted == false)
					temp = temp.next;
			}
			
		}
		
		public void printList()
		{			
			node temp = head.next;
			while(temp!=null)
			{	
				if(temp.isdeleted==false)
				{
					if(temp.next!=null)
						System.out.print(temp.data+"->");
					else
						System.out.print(temp.data);
				}
				else
				{
					if(temp.next!=null)
						System.out.print(temp.data+"(del)"+"->");
					else
						System.out.print(temp.data+"(del)");
				}
				temp = temp.next;
				
			}
			System.out.println();
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListClass myList = new ListClass();
		int choice=0,data=0;
		Scanner sc = new Scanner(System.in);
		while(choice>=0)
		{
			System.out.println("\n----------OPTIONS----------");
			System.out.println("1. Insert\n2. Print\n3. Locate \n"
					+ "4. Delete \n5. Purge \nPress -1 to exit");
			choice = sc.nextInt();
			switch(choice)
			{
				case 1:
				{
					System.out.print("Enter the number to be inserted: ");
					data = sc.nextInt();
					myList.insert(data);
					break;
				}
				case 2:
				{
					System.out.println("\nPriting list");
					myList.printList();
					break;
				}
				case 3:
				{
					System.out.print("Enter the number to be located: ");
					data = sc.nextInt();
					myList.locate(data);
					break;
				}
				case 4:
				{
					System.out.print("Enter the number to be deleted: ");
					data = sc.nextInt();
					myList.delete(data);
					break;
				}
				case 5:
				{
					myList.purge();
					break;
				}
				default:
					System.out.println("Thank you!");
					
				
				
					
			}
			
			
		}
		sc.close();
		
	}

}
