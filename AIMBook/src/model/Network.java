package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;



public class Network {
		 
	 private ArrayList <Distributor> filteredDistributors = new ArrayList <Distributor> ();
	
	 

	 
	 public void printNetwork(Node root){
		 
		 Node node = new Node();
		 Queue<Node> q = new LinkedList<>();
		 
		 q.add(root);
		 
		 while(!q.isEmpty()){
			 node = q.remove(); // the node is discovered
			 System.out.print(node.getRegNumber() + "\t");
			 
			 
			 if(node.getLeftNode() != null){
				 q.add(node.getLeftNode());
				 
			 }
			 
			 if(node.getRightNode() != null){
				 q.add(node.getRightNode());
				 
				 if(q.size() % 2 == 0)
				 System.out.println();

				 
			 }
		 }
		 
		
	 }
	 
	//get all nodes and jsonfy each node
	 
	public  JSONObject getAllNodes(String rootRegNumber){
		ArrayList<Node> nodes = new ArrayList<>();
		
		Distributor distributor = new Distributor();
		ArrayList< Distributor> allDistributors, distributors;
		
		
		allDistributors	= distributor.getAllDistributors();
		
		distributors = filterDistributors(rootRegNumber,allDistributors);
		
		JSONObject jsonObj  = new JSONObject();
		JSONObject jsonTemp ;
		 
		Node leftNode,rightNode;
		
		for( Distributor distr : distributors){
			
			Node newNode = new Node();
			jsonTemp = new JSONObject();
			
//			newNode.setEmail( distr.getEmail());
			
			newNode.setPhoneNumber(distr.getPhoneNumber());
			newNode.setRegNumber(distr.getRegistrationNumber());
			newNode.setFirstName(distr.getFirstName());
			newNode.setLastName(distr.getLastName());
//			newNode.setLeftNode(leftNode);
			leftNode = newNode.createNode(distr.getLeftChild());
			rightNode = newNode.createNode(distr.getRightChild());
			newNode.setLeftNode(leftNode);
			newNode.setRightNode(rightNode);
			nodes.add(newNode);
			
			//jsonifying each node
			try {
				jsonTemp.put("firstName",distr.getFirstName());
				jsonTemp.put("lastName",distr.getLastName());
				jsonTemp.put("phoneNumber",distr.getPhoneNumber());
				jsonTemp.put("left ", distr.getLeftChild());
				jsonTemp.put("right ", distr.getRightChild());
				jsonObj.put(distr.getRegistrationNumber(), jsonTemp);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return jsonObj;
		
	}
	
	
	//filter out only needed distributors associated to upline(root) passed
	private  ArrayList<Distributor> filterDistributors(String rootRegNumber, ArrayList<Distributor> allDistributors) {
		
		for(Distributor distributor : allDistributors){
			
			if(distributor.getRegistrationNumber().equals(rootRegNumber)){
				filteredDistributors.add(distributor);
				
				if(distributor.getLeftChild() != null){
					String leftRegNumber = distributor.getLeftChild();
					filterDistributors(leftRegNumber, allDistributors);
				}
				
				if(distributor.getRightChild() != null){
					String rightRegNumber = distributor.getRightChild();
					filterDistributors(rightRegNumber, allDistributors);
				}
			}
		}
		
		return filteredDistributors;
	}



}
