import java.util.ArrayList;
import java.util.Scanner;
import java.io.*; 
import java.util.StringTokenizer;
public class Loan {
	private PriorityQueue<Applicant> activeList;
	private ArrayList<Applicant> tempActiveList;
	private ArrayList<Applicant> approvedList;
	private ArrayList<Applicant> rejectedList;
	private ArrayList<String> temp;
	private ArrayList<Integer> tempProfit;
	private int budget;
	
	public Loan () {
		activeList = new PriorityQueue<Applicant>();
		tempActiveList=new ArrayList<Applicant>();
		approvedList = new ArrayList<Applicant>();
		rejectedList = new ArrayList<Applicant>();
		temp=new ArrayList<String>();
		tempProfit=new ArrayList<Integer>();
		budget = 0;
	}
	
	
	public void run() {
		/* print the options for the Loan app  and execute them
		 * 
		 * Possible operations:
		 * Load applications
         * Set the budget
         * Make a decision
         * Print
         * Update an application (there is a method find() in PriorityQueue 
         *                        that you can use to find the applicant 
         *                        you would like to update)
		*/
		Scanner sc=new Scanner(System.in);
		
		int ch=0;
		int prevBudget=0;
		int i;
		int size;
		do
		{
			System.out.println("========= OPERATIONS =========");
			System.out.println("1. Load Applications.");
			System.out.println("2. Set the Budget.");
			System.out.println("3. Make a Decision.");
			System.out.println("4. Print.");
			System.out.println("5. Update an Application.");
			System.out.println("6. Exit.");
			System.out.println("==============================");
			System.out.print("Enter choice (from 1 - 6):");
			try
			{
				ch=Integer.parseInt(sc.next());
				switch(ch)
				{
					case 1:
					{
						//reading application.txt 
						  File file = new File("src/application.txt");
						  BufferedReader br = new BufferedReader(new FileReader(file)); 
						  String fileData; 
						  //loop applicant 1 by 1;
						  while ((fileData = br.readLine()) != null) 
						  {
							  
							 Applicant applicant=new Applicant();
							
							 //System.out.println(fileData); 
						  	StringTokenizer strToken=new StringTokenizer(fileData,"\t");
						  	
						  	while(strToken.hasMoreTokens())
						  	{
						  		temp.add(strToken.nextToken());
						  	}
						  	
						  	//setting data to applicants
						  	i=0;
						  	if(temp.size()>0)
						  	{
						  		applicant.setName(temp.get(i));
						  		i++;
						  		applicant.setLoanAmount(Integer.parseInt(temp.get(i)));
						  		i++;
						  		applicant.setYopEducation(Integer.parseInt(temp.get(i)));
						  		i++;
						  		applicant.setYopExperience(Integer.parseInt(temp.get(i)));
						  		i++;
						  	}
						  	while(i<temp.size())
						  	{
						  		tempProfit.add(Integer.parseInt(temp.get(i)));
						  		i++;
						  	}
						  	applicant.setAnualProfitList(tempProfit);
						  	
						  	//Approval process:
						  	if((applicant.getYopExperience()+applicant.getYopEducation()<10))
						  	{
						  		rejectedList.add(applicant);
						  	}
						  	else
						  	{
						  		activeList.push(applicant);
						  	}
						  	temp.clear();
						  	tempProfit.clear();
						  } 
						  br.close();
						  
						  System.out.println("Applications loaded successfully.");
						break;
					}
					case 2:
					{
						prevBudget=this.budget;
						System.out.print("Enter Budget:");
						this.budget=Integer.parseInt(sc.next());
						System.out.println("Budget updated from "+prevBudget+" to "+this.budget+" successfully.");
						break;
					}
					case 3:
					{
						Applicant applicant=new Applicant();
						size=activeList.size();
						i=0;
						while(i<size)
						{
							applicant=activeList.pop();
							if(applicant.getLoanAmount()<=this.budget)
							{
								approvedList.add(applicant);
								this.budget=this.budget-applicant.getLoanAmount();
							}
							else
							{
								tempActiveList.add(applicant);
							}
							i++;
						}
						for(i=0;i<tempActiveList.size();i++)
						{
							applicant=tempActiveList.get(i);
							activeList.push(applicant);
						}
						System.out.println("Decision made successfylly.");
						break;
					}
					case 4:
					{
						File approvedFile = new File("src/approved.txt");
						File rejectedFile = new File("src/rejected.txt");
						File activeFile = new File("src/active.txt");
						Applicant applicant=new Applicant();
						if(!approvedFile.exists())
						{
							approvedFile.createNewFile();
							//System.out.println("New approved.txt file is created.");
						}
						if(!rejectedFile.exists())
						{
							rejectedFile.createNewFile();
							//System.out.println("New rejected.txt file is created.");
						}
						if(!activeFile.exists())
						{
							activeFile.createNewFile();
							//System.out.println("New active.txt file is created.");
						}
						
						BufferedWriter writeApprovedFile = new BufferedWriter(new FileWriter(approvedFile, false));
						BufferedWriter writeRejectedFile = new BufferedWriter(new FileWriter(rejectedFile,false));
						BufferedWriter writeActiveFile = new BufferedWriter(new FileWriter(activeFile,false));
						
						
						for(i=0;i<approvedList.size();i++)
						{
							applicant=approvedList.get(i);
					        writeApprovedFile.write(applicant.getName()+"\t"+applicant.getLoanAmount()+"\n");
						}
						writeApprovedFile.close();
						
						for(i=0;i<rejectedList.size();i++)
						{
							applicant=rejectedList.get(i);
					        writeRejectedFile.write(applicant.getName()+"\t"+applicant.getLoanAmount()+"\n");
				
						}
						writeRejectedFile.close();
						
						size=activeList.size();
						for(i=0;i<size;i++)
						{
							applicant=activeList.pop();
					        writeActiveFile.write(applicant.getName()+"\t"+applicant.getLoanAmount()+"\t"+applicant.getScore()+"\n");				
						}
						writeActiveFile.close();
						
						System.out.println("File written successfully.");
						break;
					}
					case 5:
					{
						resetAll();
						File file = new File("src/application.txt");
						  BufferedReader br = new BufferedReader(new FileReader(file)); 
						  String fileData; 
						  //loop applicant 1 by 1;
						  while ((fileData = br.readLine()) != null) 
						  {
							  
							 Applicant applicant=new Applicant();
							
							 //System.out.println(fileData); 
						  	StringTokenizer strToken=new StringTokenizer(fileData,"\t");
						  	
						  	while(strToken.hasMoreTokens())
						  	{
						  		temp.add(strToken.nextToken());
						  	}
						  	
						  	//setting data to applicants
						  	i=0;
						  	if(temp.size()>0)
						  	{
						  		applicant.setName(temp.get(i));
						  		i++;
						  		applicant.setLoanAmount(Integer.parseInt(temp.get(i)));
						  		i++;
						  		applicant.setYopEducation(Integer.parseInt(temp.get(i)));
						  		i++;
						  		applicant.setYopExperience(Integer.parseInt(temp.get(i)));
						  		i++;
						  	}
						  	while(i<temp.size())
						  	{
						  		tempProfit.add(Integer.parseInt(temp.get(i)));
						  		i++;
						  	}
						  	applicant.setAnualProfitList(tempProfit);
						  	
						  	//Approval process:
						  	if((applicant.getYopExperience()+applicant.getYopEducation()<10))
						  	{
						  		rejectedList.add(applicant);
						  	}
						  	else
						  	{
						  		activeList.push(applicant);
						  	}
						  	temp.clear();
						  	tempProfit.clear();
						  } 
						  br.close();
						  
						  System.out.println("Applications updated successfully.");
					
						break;
					}
					case 6:
					{
						System.out.println("\nApplication Stopped.");
						System.exit(0);
						break;
					}
					default:
					{
						System.out.println("\nINVALID SELECTION : Please enter value from 1 to 6.\n");
						break;
					}
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("\nINVALID SELECTION : Please enter value from 1 to 6.\n");
			}
			catch(IOException e)
			{
				System.out.println("IOException : "+e);
			}
			
		}while(ch!=6);
	}
	public void resetAll () 
	{
		activeList.clear();
		tempActiveList.clear();
		approvedList.clear();
		rejectedList.clear();;
		temp.clear();
		tempProfit.clear();
	}
}
