package packageQueue;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class QueueADT {
	static int queueSize;
	
	public static class myQueue
	{
		int front, rear, size, maxQueueSize;
		Integer[] array;
		
		public myQueue(int queueSize)
		{
			maxQueueSize = queueSize;
			array = new Integer[queueSize];
			front = rear = -1;
			size = 0;
		}
		public boolean isFull()
		{
			if(size == maxQueueSize)
				return true;
			return false;
		}
		public boolean isEmpty()
		{
			if(size == 0)
				return true;
			return false;
		}
		public void enqueue(int data)
		{
			if(!isFull())
			{
				rear = (rear+1) % maxQueueSize;
				array[rear] = data;
				size+=1;
				if(front == -1) //first element
				{
					front = rear;
				}
				System.out.println("Enqueued : "+array[rear]);
			}
			else
			{
				System.out.println("Queue is Full!");
			}
		}
		public void dequeue()
		{
			if(!isEmpty())
			{
				System.out.println("Dequeued element: "+array[front]);
				array[front] = null;
				front = (front+1) % maxQueueSize;
				size-=1;
			}
			else
			{
				System.out.println("Queue is Empty!");
			}
		}
		public void printQueue()
		{
			int i;
			System.out.println("Printing Queue: ");
			if(rear>=front) //normal queue
			{
				for(i=front; i<=rear ; i++)
				{
					System.out.print(array[i]+" ");
				}
			}
			else
			{
				for(i=front; i<size ; i = i++)
				{
					System.out.print(array[i]+" ");
				}
				for(i=0; i<=rear ; i = i++)
				{
					System.out.print(array[i]+" ");
				}
			}
			
			System.out.println();
		}
		
		
	}
	static myQueue queue; 
	static Semaphore semaphore = new Semaphore(2);
	public static class ProducerConsumer extends Thread
	{
		
		String Id;
		public ProducerConsumer(String Id)
		{
			this.Id = Id;
		}
		public void run()
		{
			if(Id == "P") //producer
			{
				try 
				{
					int i = 0;
					semaphore.acquire();
					System.out.println("Semaphore Acquired by Producer!");
					Thread.sleep(2000);
					if(queue.isFull())
					{
						Thread.sleep(5000);
					}
					Random random = new Random();
					for(i=0;i<queue.maxQueueSize;i++)
					{
						Integer data = random.nextInt() % 50;
						queue.enqueue(data);
						Thread.sleep(1000);
					}
					Thread.sleep(1000);
					semaphore.release();
					
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else //consumer
			{
				try 
				{
					int i = 0;
					semaphore.acquire();
					System.out.println("Semaphore Acquired by Consumer!");
					Thread.sleep(2000);
					if(queue.isEmpty())
					{
						Thread.sleep(3000);
					}
					for(i=0;i<queue.maxQueueSize;i++)
					{
						queue.dequeue();
						Thread.sleep(1000);
					}
					semaphore.release();
				} catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
	

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		System.out.println("Enter the maximum queue size : ");
		Scanner sc = new Scanner(System.in);
		queueSize = sc.nextInt();
		queue = new myQueue(queueSize);
		sc.close();
		ProducerConsumer producer = new ProducerConsumer("P");
		producer.start();
		
		ProducerConsumer consumer = new ProducerConsumer("C");
		consumer.start();
		

	}

}
