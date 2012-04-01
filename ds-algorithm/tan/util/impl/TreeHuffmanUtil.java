package util.impl;

import java.util.List;

import util.HuffmanTreeNode;

public class TreeHuffmanUtil {
	
	public static HuffmanTreeNode buildHuffmanTree( HuffmanTreeNode[] nodes ) {
		if ( null == nodes ) { return null ; }
		if ( nodes.length < 2 ) { return nodes[ 0 ]; }
		List list = new ListArray();
		
		for ( HuffmanTreeNode node : nodes ) {
			insertTo( list, node );
			
		}
		
		return null;
	}

	private static void insertTo( List list, HuffmanTreeNode node ) {
		int size = list.size();
		for ( int i = 0; i < size; i++ ) {
			if ( node.getWeight() > (( HuffmanTreeNode ) list.get( i ) ).getWeight() ) { 
				insertTo( list, node );
			}
			
			list.add( list.size(), node );
		}
	}

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		// TODO Auto-generated method stub
		
	}
	
}
