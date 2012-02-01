package util;

import util.impl.StackSingleLinked;

public class MazeExitTest {
	public static void main( String[] args ) {
		MazeExitTest test = new MazeExitTest();
		char[][] maze = {
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '1', '1', '0', '0', '1', '1' },
				{ '1', '0', '0', '1', '1', '0', '0', '1', '0', '1' },
				{ '1', '0', '0', '0', '0', '0', '0', '1', '0', '1' },
				{ '1', '0', '0', '0', '0', '1', '1', '0', '0', '1' },
				{ '1', '0', '0', '1', '1', '1', '0', '0', '0', '1' },
				{ '1', '0', '0', '0', '0', '1', '0', '1', '0', '1' },
				{ '1', '0', '1', '1', '0', '0', '0', '1', '0', '1' },
				{ '1', '1', '0', '0', '0', '0', '1', '0', '0', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
		};
		test.mazeExit( maze , 1, 8, 8, 8 );
	}
	public void mazeExit( char[][] maze, int sx, int sy, int ex, int ey ) {
		Cell[][] cells = createMaze( maze ) ; // ���� �Թ�
		
		printMaze( cells ); // ��ӡ�Թ�
		
		Stack s = new StackSingleLinked(); // �����ջ
		Cell startCell = cells[ sx ][ sy ]; // ���
		Cell   endCell = cells[ ex ][ ey ]; // �յ�
		s.push( startCell ); // �����ջ
		
		startCell.visited = true; // �������Ѿ������ʹ�
		
		while ( !s.isEmpty() ) {
			Cell current = ( Cell ) s.peek();
			
			if ( endCell == current ) { // ·���Ѿ��ҵ�
				while ( !s.isEmpty() ) {
					Cell cell = ( Cell ) s.pop();
					
					cell.c = '*'; 
					
					while ( !s.isEmpty() && !isAdjoinCell( ( Cell ) s.peek(), cell ) ) s.pop();
				}
//				System.out.println( "�ҵ�����㵽�յ��·����" );
				System.out.println( "-------------------- S U C C E S S F U L --------------------" );
				printMaze( cells );
				return;
			} else {
				int x = current.x;
				int y = current.y;
				int count = 0 ;
				
				if ( isValidWayCell( cells[ x + 1 ] [ y ] )) { //����
					s.push( cells[ x + 1 ] [ y ] );
					cells[ x + 1 ] [ y ].visited = true; 
					count++;
				}
				if ( isValidWayCell( cells[ x ] [ y + 1 ] )) { //����
					s.push( cells[ x ] [ y + 1 ] );
					 cells[ x ] [ y + 1 ] .visited = true; 
					count++;
				}
				if ( isValidWayCell( cells[ x - 1 ] [ y ] )) { //����
					s.push( cells[ x - 1 ] [ y ] );
					cells[ x - 1 ] [ y ].visited = true; 
					count++;
				}
				if ( isValidWayCell( cells[ x ] [ y - 1 ] )) { //����
					s.push( cells[ x ] [ y - 1 ] );
					cells[ x ] [ y - 1 ].visited = true; 
					count++;
				}
				
				if ( 0 == count ) s.pop();  // ���� ��ջ
			}// end of if
		} // end of while
		
		
		System.out.println( "û���ҵ������յ��·��" );
	}

	private boolean isValidWayCell(Cell cell) {
		return cell.c == '0' && !cell.visited;
	}

	private boolean isAdjoinCell(Cell c1, Cell c2) {
		if ( c1.x == c2.x && Math.abs( c1.y - c2.y ) < 2 )  return true;
		if ( c1.y == c2.y && Math.abs( c1.x - c2.x ) < 2 )  return true;
		return false;
	}

	private void printMaze(Cell[][] cells) {
		for ( int i = 0 ; i < cells.length; i++ ) {
			for ( int j = 0; j < cells[ i ].length; j++ ) {
				System.out.print( cells[ i ][ j ].c + "  " );
			}
			System.out.println();
		}
	}

	private Cell[][] createMaze(char[][] maze) {
		Cell[][] cells = new Cell[ maze.length ][];
		for ( int x = 0; x < maze.length; x++ ) {
			char[] row = maze[ x ];
			cells[ x ] = new Cell[ row.length ];
			for ( int y = 0; y < row.length; y++ ) {
				cells[ x ][ y ] = new Cell( x, y, maze[ x ][ y ], false );
			}
		}
		return cells;
	}
}

class Cell {
	int x =  0 /*������*/, y = 0 /*������*/;
	boolean visited = false; // �Ƿ���ʹ�
	char c ; // ǽ '1' ·'0' ��㵽�յ��·��'*'
	
	public Cell( int x, int y, char c ,boolean visited ) {
		this.x = x; this.y = y;
		this.c = c; this.visited = visited;
	}
}
