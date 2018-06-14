/*****************************************************************************************************
 * Panos Valavanis, Abiram Hea
 * 
 * The following program implements two variations the A* algorithm to find the shortest path between two points.
 * The first one implements a straight line approach which gives the path with the shortest distance between the nodes,
 * while the second one implements a fewest links approach. Hashing is not used in the implementations, array indexes are.
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



/**
 * The following four object declarations create our vertices, edges, and nodes
 * to read in values.
 */
class NodeData {
	String NodeName;
	String NodeX;
	String NodeY;

	public NodeData(String NodeName, String NodeX, String NodeY) {
		this.NodeName = NodeName;
		this.NodeX = NodeX;
		this.NodeY = NodeY;
	}

	@Override
	public String toString() {
		return "NodeData [NodeName=" + NodeName + ", NodeX=" + NodeX
				+ ", NodeY=" + NodeY + "]";
	}
}

class Edge{
	public int vertID;
	public Edge edge;
	public Edge(int id, Edge e) {
		this.vertID = id;
		edge = e;
	}
}


class Vertex implements Comparable<Vertex>{		//implements comparable to be used with priorityqueue
	String vertex = "";
	Edge list;
	int x, y, f;
	Vertex(String vertex, Edge e, int x, int y){
		this.vertex = vertex;
		this.list = e;
		this.x = x;
		this.y = y;
		this.f = 0;
	}
	
	boolean equals (Vertex v)
	{
		return this.f == v.f;
	}
	
	public int compareTo(Vertex v)
	{
		if (this.equals(v))
			return 0;
		else if (f > v.f)
			return 1;
		else return -1;
	}

	@Override
	public String toString() {
		return "Vertex [vertex=" + vertex + ", x=" + x + ", y=" + y + ", f="
				+ f + "]";
	}
	
	
	
}


//The following is the graph class used to implement the DS and run the algorithms
public class Graph {
	
	static int option;
	public static Vertex[] list;
	
	public Graph(String loc, String con, String start, String end, int heuristic, List<String> Ex ) throws IOException {
		System.out.println(loc);	//location file
		System.out.println(con);	//connections file

		
		list = new Vertex[200];	//list  of vertices from graph
		List<NodeData> record = new ArrayList<NodeData>();	//data read in from file

		int i = 0;
		
		 try {
			 //following 2 file declarations are used for the locations and connections files			 	
			 	File loc_file = new File(loc);
			 	File con_file = new File(con);

			 	//used for locations and connections file, respectively
	            BufferedReader b1 = new BufferedReader(new FileReader(loc_file));
	            BufferedReader b2 = new BufferedReader(new FileReader(con_file));

	            String readLine = "";

	            //reads in locations file into array
	            while ((readLine = b1.readLine()) != null) {
     
	                String[] tokens = readLine.split(" ");

	                if (!tokens[0].equalsIgnoreCase("END")&&(!(Ex.contains(tokens[0])))) //reads in file, without excluded vertices
	               	{
						record.add(new NodeData(tokens[0], tokens[1], tokens[2]));
						 //the following adds the vertices to the list "list". 
		                //"list" is how we access everything
						list[i] = new Vertex(tokens[0], null, Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
						i++;
	               	}
	                else if (Ex.contains(tokens[0]))
	                {
	                	continue;
	                }
				}
	            b1.close();
	            
	            i = 0;
	            readLine = "";
	            //System.out.println(list[1].x);            
	            //reads in connections file into array
	            while ((readLine = b2.readLine()) != null)
	            {     
		                
		                String[] tokens = readLine.split(" ");
		                
		                
		                if (!tokens[0].equalsIgnoreCase("END")) 
		                {
							record.add(new NodeData(tokens[0], tokens[1], tokens[2]));
							
							//the following adds "edges" of the graph.
							//this is an example on how: for city A5, it has 2 neighbors, B3, B2.
							//list[index of A5] contains a linked list of vertices with B3 and B2
							for (int j = 0; j < Integer.parseInt(tokens[1]); j++)
							{
								
								if (!Ex.contains(tokens[0]) && !Ex.contains(tokens[j+2]))	//adds edges
								{
									addEdge(tokens[0], tokens[j+2]);
								}
															
								else continue;
							}		               
						
		                }
		        }
	            b2.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 if (heuristic == 1)
			 Astar_StraightLine(start, end);
		 else
			 Astar_FewestLinks(start, end);//Astar_FewestLinks(start, end);
		
	}

	static //function used to find index of a vertex. this is done instead of hashing
	int vertexIndex(String v) {
		for (int i = 0; i < list.length; i++)
		{
			if(!list[i].vertex.equalsIgnoreCase("")){
			//	System.out.println("v is :: "+ v);
				if (list[i].vertex.equals(v))	//if the string at index i equals v, return that index
				return i;
			}	
		}
		return -1;
	}
	
	
	//adds "edges" (meaning adds vertices to that index) to the list of vertices
	void addEdge(String one, String two) {
		int v1 = vertexIndex(one);	//index of first string (vertex)
		int v2 = vertexIndex(two);	//...second...
		
		list[v1].list = new Edge(v2, list[v1].list);	//adds a relationship between the vertex at i and the new one
	}
	

	public void print() {
		for (int i = 0; i < list.length - 1; i++)
		{
			System.out.println(list[i].vertex);
			for (Edge e = list[i].list; e != null; e = e.edge)	//how to access edges from list (access elements of linked list)
			{
				System.out.print(" -> " + list[e.vertID].vertex);
			}
			System.out.print("\n");
		}
		
	}
	
	//calculates distance between two vertices 
	static int distance(String s, String e)
	{
		return (int) Math.sqrt(Math.pow(list[vertexIndex(e)].y - list[vertexIndex(s)].y, 2) + 
				Math.pow(list[vertexIndex(e)].x - list[vertexIndex(s)].x, 2));
	}
	
	/*
	 * The following implements A* straight line algorithm (shortest distance) using a priorityqueue
	 */
	public static void Astar_StraightLine(String start, String end)
	{
		List<Vertex> finalList = new ArrayList<Vertex>();
		//f = g+h, g = cost from start to current node, h is cost from current node to finish
		int g = 0, h = 0;
		int startVertInd = vertexIndex(start);	//index of start vertex
		int endVertInd = vertexIndex(end);		//... end...		
		//	initial distance for heuristic 
		int startToEnd = (int) Math.sqrt(Math.pow(list[endVertInd].y - list[startVertInd].y, 2) + 
				Math.pow(list[endVertInd].x - list[startVertInd].x, 2));
		h = startToEnd;
				
		PriorityQueue<Vertex> open = new PriorityQueue<Vertex>(); //queue to add children which will be visited				
		open.add(list[startVertInd]);	//add to priority queue
		GraphicPath G = new GraphicPath();//<-------------
		//**************************************************************************
		String[] path = new String[200];	//used to store the path of the traversal
		for (int i = 0; i < path.length; i++)
			path[i] = "";	//initialize array 
		path[vertexIndex(start)] = start;
		//**************************************************************************
		
		Vertex current;	//to be used as current item being worked on
		
		while (true)
		{
			current = open.poll();
			
			if (current.vertex.equals(null))
			{
				break;	//queue is empty, therefore we have done all traversals
			}
			
			//following 'if-else' is used to properly print the desired output
			if (current.vertex.equals(end))
			{
				System.out.println(path[vertexIndex(end)]);
				finalList.add(current);
				/*here option is used to to call methods based on user inputs
				 * inside option ==2 if loop, we call for method to display final path
				 * inside option==1 if loop we call for the method to display final path for step by step option
				 * the proper step by stem method is in line 315
				 * 
				 */
				 
				if(option==2){
					try {
						new GraphicPath().PrintGraph_step(finalList,list);
					}catch (InterruptedException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(option==1)
				{
					
					G.PrintFinal(finalList);
				}
				return;		//return because we found our end node	
			}
			finalList.add(current);
			
			for (Edge e = list[vertexIndex(current.vertex)].list; e != null; e = e.edge)
			{
				
				g = distance(current.vertex, list[e.vertID].vertex);
				h = distance(list[e.vertID].vertex, end);
				int temp = g + h;
				list[e.vertID].f = temp;
				if (path[e.vertID].equals(""))
				{
					path[e.vertID] = path[vertexIndex(current.vertex)] + " -> " + list[e.vertID].vertex;	//construct path			
					open.add(list[e.vertID]);					
					
				}
			}
		
		}
		
	}

	
	
	/*
	 * the following implements A* to find the fewest links between two vertices, 
	 * using an approach similar to BFS
	 */
	public void Astar_FewestLinks(String start, String end)
	
	{	
		LinkedList<String> ll = new LinkedList<String>();	//list to hold vertices to be evaluated
		ll.add(start);
		ArrayList<Vertex> finalList = new ArrayList<Vertex>();
		String[] path = new String[200];	//used to store the path of the traversal
		for (int i = 0; i < path.length; i++)
			path[i] = "";	//initialize array 
		
		path[vertexIndex(start)] = start;
		GraphicPath G = new GraphicPath();
		while (!ll.isEmpty())	//keep running until list is empty, or until end is found
		{
			String vert = ll.poll();
			
			if (vert.equals(end))
			{
				break;
			}
			
			for (Edge e = list[vertexIndex(vert)].list; e != null; e = e.edge)
			{	
				if (path[e.vertID].equals(""))
				{
					path[e.vertID] = path[vertexIndex(vert)] + "-->" + list[e.vertID].vertex;	//construct path
					ll.add(list[e.vertID].vertex);//add element to list
						//finalList.add(list[e.vertID]);
				}
			}
		}
		System.out.println(path[vertexIndex(end)]);	//print constructed path
		String final1 = path[vertexIndex(end)];
		StringTokenizer token = new StringTokenizer(final1,"-->");
		while (token.hasMoreElements()) {
			finalList.add(list[vertexIndex(token.nextElement().toString())]);
		
		}
		if (option==2)
			G.drawEachNode_1(finalList);
		if (option==1)
			G.drawStep(finalList);
	}
	
	//main to run the program
	public static void main(String[] args) throws IOException {
		
Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter directory of locations file");
	   // String loc  = scan.nextLine();
	    //if (loc.equals(""))
	    	//loc = "C:/Users/pvala/Desktop/USF/Grad/Fall 17/CAP 5625-Artificial Intelligence/Project 1/locations.txt";
		final JFileChooser file = new JFileChooser();
		file.showOpenDialog(file);
		String	loc = file.getSelectedFile().toString();
	    
		System.out.println("Enter directory of connections file");
	   // String con = scan.nextLine();
	  //  if (con.equals(""))
	   // 	con = "C:/Users/pvala/Desktop/USF/Grad/Fall 17/CAP 5625-Artificial Intelligence/Project 1/connections.txt";
	    //final JFileChooser file = new JFileChooser();
		file.showOpenDialog(file);
		String	con = file.getSelectedFile().toString();
	    
	    String start, end;
	    System.out.println("Enter start city");
	    //start = scan.nextLine();
	    start = JOptionPane.showInputDialog(null, "Enter Start City");
	    System.out.println("Enter end city");
	    //end = scan.nextLine();
	    end = JOptionPane.showInputDialog(null, "Enter End City");
	    
	    System.out.println("Enter the number of cities to be excluded, or '0' for none.");
	    //int exclude = scan.nextInt();
	    int exclude = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of "
	    				+ "cities to be excluded, or '0' for none."));
	    
	    List<String> excludeList = new ArrayList<String>();	//list to hold excluded cities
		if(exclude != 0){
			for (int i = 0; i < exclude; i++) {
				System.out.println("Enter city #"+(i+1) + " to be excluded");
				//excludeList.add(scan.next());
				excludeList.add(JOptionPane.showInputDialog(null, "Enter city # " +(i+1) + " to be excluded"));
			}
		}
	    
	    System.out.println("Enter '1' for Stright Line heuristic, or '2' for Fewest Links heuristic");
	    //int heuristic = scan.nextInt();
	    int heuristic = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter '1' for Stright Line heuristic, "
	    		+ "or '2' for Fewest Links heuristic"));
	    //scan.close();
	    
	    System.out.println("Enter 1 for step by step or 2 for final path");
		//option=scan.nextInt();
	    option = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter 1 for step by step or 2 for final path"));

		scan.close();
	    //set up graph
		@SuppressWarnings("unused")
		Graph G = new Graph(loc, con, start, end, heuristic, excludeList);
		//G.print();
	}
}
