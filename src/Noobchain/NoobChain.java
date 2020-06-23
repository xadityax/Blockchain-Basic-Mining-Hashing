package Noobchain;	
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class NoobChain {
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	public static void main(String[] args) throws IOException {	
		//add our blocks to the blockchain ArrayList:
		System.out.println("1:createBlock");
		System.out.println("2:verifyTransaction");
		System.out.println("3:mineBlock");
		System.out.println("4:viewUser");
		System.out.println("5:Exit");
        int i,f=0,flag=0;
        int c=-1;
        Scanner s = new Scanner(System.in);
        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
       String uid1,date1,model;
       int rating1;
       for(i=0;f!=5;i++)
        {
        	 
        	System.out.println("Enter your choice ");
        	 f = s.nextInt(); 
            if(f==1)
            {
            	c++;
            	System.out.println("Enter uid: ");
            	uid1 = inp.readLine();
            	System.out.println("Enter date: ");
            	date1 = inp.readLine();
            	System.out.println("Enter model: ");
            	model = inp.readLine();
            	System.out.println("Enter perfomance rating: ");
            	rating1=s.nextInt();
            	if(c==0)
        		blockchain.add(new Block(uid1,date1,model,rating1, "0"));
            	else
            	blockchain.add(new Block(uid1,date1,model,rating1,blockchain.get(blockchain.size()-1).hash));
            	
            	System.out.println("block created: "+(c+1));
            }
            if(f==2)
            {
            	//blockchain.add(new Block("132","23","123",12, "0"));
            	System.out.println("\nBlockchain Validity: " + isChainValid());
            	f=0;
            }
            if(f==3)
            {
        		System.out.println("Trying to Mine block "+(c+1));
            	blockchain.get(c).mineBlock(difficulty);
            	
            }
            if(f==4)
            {
            	String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        		System.out.println("\nThe block chain: ");
        		System.out.println(blockchainJson);
            }
            if(f==5)
            {
            	System.out.println("Exited");
            	break;
            }
       	}
 
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
}
}
