public class Grid 
{
	static int totrows;
	static int totcols;
	public static int setsize(String temp) 
	{
		switch(temp.charAt(0))
		{
		case 'E':
		case 'e':
		case '1':
			return 5;
		case 'M':
		case 'm':
		case '2':
			return 7;
		case 'H':
		case 'h':
		case '3':
			return 9;
		case 'L':
		case 'l':
		case '4':
			return 11;
		default:
			System.out.println("Select a valid difficulty");
			return 0; 
		}
	} 
	public Grid(int totrow,int totcol)
	{
		totrows=totrow;
		totcols=totcol;
	}	
	public static void setbomb(int row,int col,Cells [][]cell)
	{
		cell[row+1][col+1].bomb=true;
	}
	public static void setvalue(Cells[][] cell)
	{
		for(int i=0;i<totrows+2;i++)
			for(int j=0;j<totcols+2;j++)
				cell[i][j].value=" ";
		for(int i=1;i<=totrows;i++)
		{
			for(int j=1;j<=totcols;j++)
			{
				if(cell[i][j].bomb!=true)
				{
					int count=0;
					if(cell[i-1][j].bomb==true)
						count++;
					if(cell[i-1][j-1].bomb==true)
						count++;
					if(cell[i][j-1].bomb==true)
						count++;
					if(cell[i+1][j-1].bomb==true)
						count++;
					if(cell[i+1][j].bomb==true)
						count++;
					if(cell[i-1][j+1].bomb==true)
						count++;
					if(cell[i][j+1].bomb==true)
						count++;
					if(cell[i+1][j+1].bomb==true)
						count++;
					cell[i][j].value=Integer.toString(count);
				}
				else
					cell[i][j].value="*";
			}
		}
	}
}
