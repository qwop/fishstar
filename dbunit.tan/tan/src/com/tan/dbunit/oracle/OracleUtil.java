package com.tan.dbunit.oracle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;

import com.tan.bean.UserTab;

/**
 * 
 * @author dolphin
 *
 * 2011-10-21 下午4:10:03
 */
public class OracleUtil {
	private static final Logger logger = Logger.getLogger( "OracleUtil" );
	
	private Properties prop;
	
	private Statement stmt;
	
	private ResultSet rs;
	
	private Connection conn;
	
	public OracleUtil() {
		this( "D:\\Eclipses\\eclipse-jee-indigo-win32\\workspace\\dbunit.tan\\tan\\res\\oracle.properties" );
	}

	public OracleUtil( final String name ) {
		this( new File( name ) );
	}

	public OracleUtil( final File file ) {
		prop = new Properties();
		
		try {
			prop.load( new FileInputStream( file ));
			
			Class.forName( prop.getProperty("class"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public List<UserTab> allTableNameAndRowCount() {
		conn();
		if (null != conn ) {
			List<UserTab> list = new ArrayList<UserTab>();
			UserTab tab;
			try {
				rs = stmt.executeQuery( "select table_name,num_rows from user_tables order by num_rows desc,table_name" );
				
				while ( rs.next() ) {
					tab = new UserTab();
//					list.add( rs.getString( "table_name" ) + "\t\t" + rs.getString( "num_rows" ) );
					tab.setTableName(rs.getString( "table_name" ));
					
					tab.setRowCount(rs.getLong( "num_rows" ));
					
					list.add( tab );
				}
				
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close();
		return null;
	}
	
	private void close() {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		rs = null;
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		stmt = null;
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn = null;
	}

	private void conn() {
		try {
			conn = DriverManager.getConnection( prop.getProperty("url"), prop );
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> allTable() {
		return null;
	}

	public static void allTableDataset(String outputDir,Properties prop) {
    	Connection jdbcConnection = null;
    	File dir = new File( outputDir );
    	// check the dir mkdirs
    	if ( dir.isFile() ) {
    		logger.info( "文件删除！" );
    		dir.delete(); 
    	} // delete the file.
    	
    	if ( !dir.exists() ) {
    		logger.info( "目录不删除！" );
    		if ( dir.mkdirs() ) {
    			logger.info( "目录创建成功 " + dir.getAbsolutePath() );
    		}
    	}
		try {
			jdbcConnection = DriverManager.getConnection(prop.getProperty("url"), prop);
			IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
			QueryDataSet dataSet = new QueryDataSet( connection );
			
//			ITable table = dataSet.getTable( "dual" );
//			
//			logger.info( "" + table.getRowCount() );
			
//	    	Connection jdbcConnection = DriverManager.getConnection("jdbc:oracle:thin:@10.5.80.53:1521:orcl","jlyd_adc","jlyd_adc");
//	    	IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
//	    	QueryDataSet dataSet = new QueryDataSet( connection );
			dataSet.addTable( "NN_SPINFO", "SELECT * FROM ALL_TAB_COMMENTS WHERE OWNER = 'JLYD_ADC'" );
//	    	dataSet.addTable( "NN_SPINFO", "SELECT * FROM NN_SPINFO" );
			
			
	    	ITableIterator iterator = dataSet.iterator();
	    	ITable table ;
	    	int rowCount;
	    	while ( iterator.next() ) {
	    		table = iterator.getTable();
	    		
	    		rowCount =  table.getRowCount() ;
	    		
	    		for ( int i = 0; i < rowCount; i++ ) {
	    			
	    			System.out.println( table.getValue( i , "TABLE_NAME" ) );
	    			
	    		}
	    		
	    	}
//	    	File file = new File( "NN_SPINFO.XML");
//	    	try {
//				FlatXmlDataSet.write( dataSet, new FileOutputStream(file));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

			
			//dataSet.addTable( "NN_SPINFO", "SELECT * FROM NN_SPINFO" );
			//FlatXmlDataSet.write( dataSet, new FileOutputStream(file));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatabaseUnitException e) {
			e.printStackTrace();
		}
	}
}
