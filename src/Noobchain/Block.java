package Noobchain;
import java.util.Date;
public class Block {
	public String hash;
	public String previousHash; 
	private String uid; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	private String date;
	private String model;
	public int rating;
	//Block Constructor.  
	public Block(String uid,String date,String model,int rating,String previousHash ) {
		this.uid = uid;
		this.model=model;
		this.date=date;
		this.rating=rating;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				uid 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
}
}