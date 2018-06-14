import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GraphicPath extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 *StraightLine distance function plotting FinalPath using java-Swings 
	 */
	 
	void PrintGraph_step(java.util.List<Vertex> val, Vertex[] list) throws InterruptedException
	{

		GraphicPath panel = new GraphicPath();
		JFrame frame = new JFrame("StraightLine distance - Final Path");
		frame.setSize(1000, 1000);
		java.util.List<Vertex>dlist = Arrays.asList(list);
		frame.add(panel);
		frame.setVisible(true);
		panel.setVisible(true);
		Graphics g = frame.getGraphics();
		g.setColor(Color.black);

		for (int k = 0; k < dlist.size(); k++) 
		{
			if(dlist.get(k) !=null ){
				g.fillRect((dlist).get(k).x,(dlist).get(k).y,20 ,20);
				g.drawString(dlist.get(k).vertex, dlist.get(k).x, dlist.get(k).y);
				Thread.sleep(300);
			}
		}

		g.setColor(Color.RED);
		for(int i=0;i < val.size();i++)
		{
			Graphics2D g2 = (Graphics2D) g;
	        g2.setStroke(new BasicStroke(3));
			g2.fillRect(val.get(i).x,val.get(i).y,20,20);
			g2.drawString(val.get(i).vertex, val.get(i).x, val.get(i).y);
			if(!(i==val.size()-1))
			g2.drawLine(val.get(i).x,val.get(i).y, val.get(i+1).x,val.get(i+1).y);
		}
		
	}
	/*
	 *StraightLine distance function plotting FinalPath step by step using java-Swings 
	 */
	
	public void PrintFinal(java.util.List<Vertex> finalList){
		GraphicPath panel = new GraphicPath();
		JFrame frame = new JFrame("StraightLine distance - Step by Step");
		frame.setSize(1000, 1000);
		frame.add(panel);
		frame.setVisible(true);
		panel.setVisible(true);
		Graphics g = frame.getGraphics();
		Vertex current;
		try {
			g.setColor(Color.black);
			for(int i=0;i<Graph.list.length;i++){
				if(Graph.list[i] !=null ){
					g.fillRect(Graph.list[i].x,Graph.list[i].y,20,20);
					g.drawString(Graph.list[i].vertex, Graph.list[i].x, Graph.list[i].y);
					Thread.sleep(200);
				}
			}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		for(int k=0;k < finalList.size();k++){
			current = finalList.get(k);
			for (Edge e = Graph.list[Graph.vertexIndex(current.vertex)].list; e != null; e = e.edge){
				g.setColor(Color.blue);
				g.drawLine(current.x,current.y, Graph.list[e.vertID].x,Graph.list[e.vertID].y);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(!(k==finalList.size()-1))
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				g.setColor(Color.red);
				g.drawLine(current.x,current.y, finalList.get(k+1).x,finalList.get(k+1).y);
			}
		}
		
		for(int i=0;i < finalList.size();i++)
		{
			Graphics2D g2 = (Graphics2D) g;
	        g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.RED);
			g2.fillRect(finalList.get(i).x,finalList.get(i).y,20,20);
			g2.drawString(finalList.get(i).vertex, finalList.get(i).x, finalList.get(i).y);
			if(!(i==finalList.size()-1))
			g2.drawLine(finalList.get(i).x,finalList.get(i).y, finalList.get(i+1).x,finalList.get(i+1).y);
		}
			
	}
		
	

	public static void main(String[] args) {
		
	}
	
	/*
	 *FewestLinks distance function plotting FinalPath using java-Swings 
	 */
	
	public void drawEachNode_1(ArrayList<Vertex> vertices){
	
		
		GraphicPath panel = new GraphicPath();
		JFrame frame = new JFrame("FewestLinks distance- Final Path");
		frame.setSize(1000, 1000);
		frame.add(panel);
		frame.setVisible(true);
		panel.setVisible(true);
		Graphics g = frame.getGraphics();
		
		g.setColor(Color.black);
		
		try {
		
		for(int i=0;i<Graph.list.length;i++){
			if(Graph.list[i] !=null ){
				g.fillRect(Graph.list[i].x,Graph.list[i].y,20,20);
				g.drawString(Graph.list[i].vertex, Graph.list[i].x, Graph.list[i].y);
				Thread.sleep(200);
			}
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.setColor(Color.RED);
		for(int i=0;i < vertices.size();i++)
		{
			Graphics2D g2 = (Graphics2D) g;
	        g2.setStroke(new BasicStroke(3));
			g2.fillRect(vertices.get(i).x,vertices.get(i).y,20,20);
			g2.drawString(vertices.get(i).vertex, vertices.get(i).x, vertices.get(i).y);
			if(!(i==vertices.size()-1))
			g2.drawLine(vertices.get(i).x,vertices.get(i).y, vertices.get(i+1).x,vertices.get(i+1).y);
		}
	}
	/*
	 *FewestLinks distance function plotting FinalPath step by step using java-Swings 
	 */
	public void drawStep(ArrayList<Vertex> vert){
		
		GraphicPath panel = new GraphicPath();
		JFrame frame = new JFrame("FewestLinks distance- Step by Step");
		frame.setSize(1000, 1000);
		frame.add(panel);
		frame.setVisible(true);
		panel.setVisible(true);
		Graphics g = frame.getGraphics();
		Vertex current;
		g.setColor(Color.black);
		try {
			
			for(int i=0;i<Graph.list.length;i++){
				if(Graph.list[i] !=null ){
					g.fillRect(Graph.list[i].x,Graph.list[i].y,20,20);
					g.drawString(Graph.list[i].vertex, Graph.list[i].x, Graph.list[i].y);
					Thread.sleep(200);
				}
			}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for(int i=0;i < vert.size();i++){
			current=vert.get(i);
			for (Edge e = Graph.list[Graph.vertexIndex(current.vertex)].list; e != null; e = e.edge){
				g.setColor(Color.blue);
				g.drawLine(current.x,current.y, Graph.list[e.vertID].x,Graph.list[e.vertID].y);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(!(i==vert.size()-1))
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				g.setColor(Color.red);
				g.drawLine(current.x,current.y, vert.get(i+1).x,vert.get(i+1).y);
			}
			}
		g.setColor(Color.RED);
		for(int i=0;i < vert.size();i++)
		{
			Graphics2D g2 = (Graphics2D) g;
	        g2.setStroke(new BasicStroke(3));
			g2.fillRect(vert.get(i).x,vert.get(i).y,20,20);
			g2.drawString(vert.get(i).vertex, vert.get(i).x, vert.get(i).y);
			if(!(i==vert.size()-1))
			g2.drawLine(vert.get(i).x,vert.get(i).y, vert.get(i+1).x,vert.get(i+1).y);
		}
	}
}
