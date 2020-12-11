import java.util.ArrayList;

public class Applicant implements Comparable<Applicant> 
{
	private String name;
	private int score;
	private int loanAmount;
	private int yopEducation;
	private int yopExperience;
	private ArrayList<Integer> anualProfitList;
	
	// add the rest of fields (education, experience, annual profit)
	
	//Complete this constructor 
	// you should calculate this.score based on estimated annual profit
	public Applicant() {
		//Complete this constructor 
		// you should calculate this.score based on estimated annual profit
		this.name=null;
		this.score=0;
		this.loanAmount=0;
		this.yopEducation=0;
		this.yopExperience=0;
		anualProfitList=new ArrayList<Integer>();
	}

	public int compareTo(Applicant other){
		if (this.score > other.score)
			return 1;
		else if (this.score < other.score)
			return -1;
		else
			return 0;
		// you can just write it in one line:
		// return this.score - other.score
	}
	
	// this method will be used to find an applicant
	public boolean equals(Applicant other) {
		return this.name.equals(other.name);
	}
	
	public String toString() {
		//complete this method
		//it will be useful for print option in class Loan 
		return "";
	}
	
	// add any other method you need
	
	
	//function to calculate final score
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	
	
	public void setLoanAmount(int loanAmount)
	{
		this.loanAmount=loanAmount;
	}
	public int getLoanAmount()
	{
		return this.loanAmount;
	}
	
	
	public void setYopEducation(int yopEducation)
	{
		this.yopEducation=yopEducation;
	}
	public int getYopEducation()
	{
		return this.yopEducation;
	}
	
	
	public void setYopExperience(int yopExperience)
	{
		this.yopExperience=yopExperience;
	}
	public int getYopExperience()
	{
		return this.yopExperience;
	}
	
	
	public void setAnualProfitList(ArrayList<Integer> anualProfitList)
	{
		this.anualProfitList=anualProfitList;
		this.score=calculateScore(this.anualProfitList);
	}
	public ArrayList<Integer> getAnualProfitList()
	{
		return this.anualProfitList;
	}
	
	public int getScore()
	{
		return this.score;
	}


	private int calculateScore(ArrayList<Integer> anualProfitList)
	{
		int score=0;
		int i;
		for(i=0;i<anualProfitList.size();i++)
		{
			score+=(anualProfitList.get(i)*1)/(i+1);
		}
		return score;
	}
	public static void main(String a[])
	{
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(0);
		list.add(0);
		list.add(24000);
		list.add(40000);
		list.add(50000);
		list.add(24000);
		Applicant o=new Applicant();
		o.setAnualProfitList(list);
		System.out.println("final score:"+o.score);
	}
}
