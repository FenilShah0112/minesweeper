public class Cells 
{
	int rows,cols;
	String value;
	boolean bomb;
	boolean flag;
	static int abcd=0,countflag=0,countnumber=0;
	public static void play(Cells[][] cell,Cells[][] cell1,int rows,int cols)
	{
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
			{
				cell1[i][j]=new Cells();
				cell1[i][j].value="-";
			}
	}
	public static int start(Cells[][] cell, Cells[][] cell1, int a, int b,int c, int rows,int cols) 
	{
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				if(a==i+1 && b==j+1)
				{
					if(c==1)
					{
						cell1[i][j]=cell[i+1][j+1];
						countnumber++;
						if(cell[i+1][j+1].bomb==true)
						{
							countnumber--;
							return 1;
						}
						else if(cell[i+1][j+1].value.charAt(0)=='0')
						{
							Opensurrounding(cell,cell1,i+1,j+1);
						}
					}
					else if(c==0)
					{
						cell1[i][j].value="|";
						countflag++;
					}
					else
					{
						System.out.println("Enter a valid input");
						countnumber--;
						return 0;
					}
				}
		Print(cell,cell1,rows,cols);
		if(cell[a-1][b-1].countnumber+cell[a-1][b-1].countflag==rows*cols)
		{
			System.out.println("Congratulations!You won");
			return 2;
		}
		abcd++;
		switch(abcd%3)
		{
		case 0:
			System.out.println("Great move!!");
			break;
		case 1:
			System.out.println("Nicely played");
			break;
		case 2:
			System.out.println("That's fantastic");
			break;
		}
		return 0;
	}
	public static void Opensurrounding(Cells[][] cell, Cells[][] cell1, int i, int j) 
	{	
		for(int k=i-1;k<=i+1;k++)
		{
			for(int l=j-1;l<=j+1;l++)
			{
				if(cell[k][l].value.charAt(0)!=' ' && cell1[k-1][l-1].value.charAt(0)!='0')
				{
					countnumber++;
					cell1[k-1][l-1]=cell[k][l];
					if(cell[k][l].value.charAt(0)=='0')
					{
						Opensurrounding(cell,cell1,k,l);
					}
				}
			}
		}
	}
	static void Print(Cells[][] cell,Cells[][] cell1,int rows,int cols) 
	{
		for(int i=0; i<rows; i++) 
		{	
			for(int j=0; j<cols; j++) 
			{	
				System.out.print("\t" + cell1[i][j].value);
			}
			System.out.println();
		}
	}
}
