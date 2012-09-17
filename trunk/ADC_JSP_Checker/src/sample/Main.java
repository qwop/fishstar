package sample;

import java.io.File;
import java.io.FileFilter;

public class Main {
	public static void main(String[] args) {
		travel();
		
//		single();
		
	}

	private static void single() {
		StyleChecker checker = new StyleChecker( "file:///D:/bea/user_projects/workspaces/default/adc_op/WebContent/dd/ectype_profession_add.jsp" );
		
		if ( !checker.isRight() ) {
			checker.display();
		}
	}

	private static void travel() {
		String jsp = 
			"D:\\bea\\user_projects\\workspaces\\default\\adc_op\\WebContent\\dd\\ectype_yearincome_modify.jsp";
		
		File root = new File( "D:\\bea\\user_projects\\workspaces\\default\\adc_op" );
		
		root.listFiles( new FileFilter()  {
			@Override
			public boolean accept(File file) {
				if ( file.isFile() ) {
					String name = file.getName().toLowerCase();
					
					if ( name.endsWith( ".jsp" ) ) {
						StyleChecker checker = new StyleChecker( file.getAbsolutePath() );
						
						if ( !checker.isRight() ) {
							checker.display();
						}
					}
				} else if ( file.isDirectory() ) {
					file.listFiles( this );
				}
				return false;
			}
			
		});
	}
}
