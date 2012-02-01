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
		Cell[][] cells = createMaze( maze ) ; // 创建 迷宫
		
		printMaze( cells ); // 打印迷宫
		
		Stack s = new StackSingleLinked(); // 构造堆栈
		Cell startCell = cells[ sx ][ sy ]; // 起点
		Cell   endCell = cells[ ex ][ ey ]; // 终点
		s.push( startCell ); // 起点入栈
		
		startCell.visited = true; // 标记起点已经被访问过
		
		while ( !s.isEmpty() ) {
			Cell current = ( Cell ) s.peek();
			
			if ( endCell == current ) { // 路径已经找到
				while ( !s.isEmpty() ) {
					Cell cell = ( Cell ) s.pop();
					
					cell.c = '*'; 
					
					while ( !s.isEmpty() && !isAdjoinCell( ( Cell ) s.peek(), cell ) ) s.pop();
				}
//				System.out.println( "找到从起点到终点的路径。" );
				System.out.println( "-------------------- S U C C E S S F U L --------------------" );
				printMaze( cells );
				return;
			} else {
				int x = current.x;
				int y = current.y;
				int count = 0 ;
				
				if ( isValidWayCell( cells[ x + 1 ] [ y ] )) { //向下
					s.push( cells[ x + 1 ] [ y ] );
					cells[ x + 1 ] [ y ].visited = true; 
					count++;
				}
				if ( isValidWayCell( cells[ x ] [ y + 1 ] )) { //向右
					s.push( cells[ x ] [ y + 1 ] );
					 cells[ x ] [ y + 1 ] .visited = true; 
					count++;
				}
				if ( isValidWayCell( cells[ x - 1 ] [ y ] )) { //向上
					s.push( cells[ x - 1 ] [ y ] );
					cells[ x - 1 ] [ y ].visited = true; 
					count++;
				}
				if ( isValidWayCell( cells[ x ] [ y - 1 ] )) { //向左
					s.push( cells[ x ] [ y - 1 ] );
					cells[ x ] [ y - 1 ].visited = true; 
					count++;
				}
				
				if ( 0 == count ) s.pop();  // 死点 出栈
			}// end of if
		} // end of while
		
		
		System.out.println( "没有找到起点和终点的路径" );
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
	int x =  0 /*所在行*/, y = 0 /*所在列*/;
	boolean visited = false; // 是否访问过
	char c ; // 墙 '1' 路'0' 起点到终点的路径'*'
	
	public Cell( int x, int y, char c ,boolean visited ) {
		this.x = x; this.y = y;
		this.c = c; this.visited = visited;
	}
}
