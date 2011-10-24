package com.tan.dbunit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.TestCase;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class SampleTest extends TestCase
{
    public SampleTest(String name)
    {
        super( name );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "oracle.jdbc.driver.OracleDriver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:oracle:thin:@10.5.80.53:1521:orcl" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "jlyd_adc" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "jlyd_adc" );
	// System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "" );
    }

/*    protected IDataSet getDataSet() throws Exception
    {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
    }*/
    
    public void test1() throws Exception{
    	Class.forName( "oracle.jdbc.driver.OracleDriver" );
    	Connection jdbcConnection = DriverManager.getConnection("jdbc:oracle:thin:@10.5.80.53:1521:orcl","jlyd_adc","jlyd_adc");
    	IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
    	QueryDataSet dataSet = new QueryDataSet( connection );
    	dataSet.addTable( "NN_SPINFO", "SELECT * FROM NN_SPINFO" );
    	File file = new File( "NN_SPINFO.XML");
    	FlatXmlDataSet.write( dataSet, new FileOutputStream(file));
    	
    	//translateChinese( file );
    }

	private void translateChinese(File file) {
		if ( file.exists() && file.isFile() ) {
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream( file );
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally { 
				if ( null != fis ) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} fis = null;
			}
		}
	}
}