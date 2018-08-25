import java.io.File;
import java.lang.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
public class Main 
{
	static int countnumbers=0;
	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("INSTRUCTIONS\nInput should be three numbers seperated by space\nFormat:row<space>column<space>0(flag)/1(open the cell)\t(The number of row/column starts from 1)\nType 'undo'(w/o '') to undo your last step\t(Caution:You can only undo if the last cell you opened was a bomb)\nTo select difficulty,let's say easy,type either '1'/'Easy'/'easy'(w/o '')\n\n");
		System.out.println("*********Let us start the game*********\n\n");
		System.out.println("Select your difficulty:\n1.Easy\n2.Medium\n3.Hard\n4.Legen-waitforit-dary");
		int rows,cols,bombs;
		while(true)										//Setting the difficulty of the game
		{
			String tep=sc.nextLine();
			rows=Grid.setsize(tep);
			if(rows!=0)
				break;
		}
		cols=rows;bombs=rows*rows/3;
		Grid g=new Grid(rows,cols);
		Cells cell[][]=new Cells[rows+2][cols+2];
		for(int i=0;i<rows+2;i++)						//Initialising each cell of the grid
			for(int j=0;j<cols+2;j++)
				cell[i][j]=new Cells();
		for(int i=0;i<bombs;i++)						//Setting bombs at random locations
		{
			Random rand=new Random();
			int max=rows-1,min=0;
			int row=rand.nextInt(max-min+1)+min;
			int col=rand.nextInt(max-min+1)+min;
			g.setbomb(row,col,cell);
		}		
		int undo=5;
		g.setvalue(cell);								
		Cells [][]cell1=new Cells[rows][cols];
		Cells.play(cell,cell1,rows,cols);
		sc=new Scanner(System.in);
		int lose;
		int count=0;
		Cells.Print(cell, cell1, rows, cols);
		System.out.println("You have "+undo+" lives remaining");
		outer:
		while(true)
		{
			int counter=0;
			String temp12=sc.nextLine();
			for(int i=0;i<temp12.length();i++)
			{
				if(temp12.charAt(i)==' ')
				{
					counter++;
				}
			}
			String temp13[]=new String[3];
			temp13=temp12.split(" ");
			if(counter!=2 ||(counter==2 && (Integer.parseInt(temp13[0])>rows)||Integer.parseInt(temp13[1])>cols)||Integer.parseInt(temp13[0])<1||Integer.parseInt(temp13[1])<1)
			{
				System.out.println("Enter a valid input");
				continue outer;
			}
			int a,b,c;
			a=Integer.parseInt(temp13[0]);
			b=Integer.parseInt(temp13[1]);
			c=Integer.parseInt(temp13[2]);
			count++;
			lose=Cells.start(cell,cell1,a,b,c,rows,cols);
			if(lose==1 && undo!=0)
			{
				Cells.Print(cell,cell1,rows,cols);
				System.out.println("OH NO! you selected a bomb");
				int attempts=0;
				inner:
				while(true)
				{
					String temp=sc.nextLine();
					if(temp.equals("undo"))
					{
						count++;
						undo--;
						System.out.println("You have "+undo+" lives remaining");
						cell1[a-1][b-1].value="-";
						Cells.Print(cell,cell1,rows,cols);
						continue outer;
					}
					else
					{
						if(attempts==1)
						{
							System.out.println("You lose");
							break outer;
						}
						attempts++;
						System.out.println("Sorry invalid input\nEither type 'undo'(w/o '') or you lose the game");	continue inner;
					}
				}
			}
			else if(undo==0 && lose==1)
			{
				System.out.println("You lose");
				break;
			}
			else if(lose==2)
			{
				System.out.println("Total number of moves played is "+count);
				break;
			}
		}
	}
}
