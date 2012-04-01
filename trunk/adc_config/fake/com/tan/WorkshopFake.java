package com.tan;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Fake a weblogic for working!
 * @author dolphin
 *
 * 2011-11-2 上午9:55:28
 */
public class WorkshopFake {

	private JFrame frame;
	private transient Toolkit kit = Toolkit.getDefaultToolkit();
	 final JLabel imgLabel = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		forWorkshop();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkshopFake window = new WorkshopFake();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void forWorkshop() {
//		osgi.splashLocation
		
		System.setProperty( "osgi.splashLocation", "osgi.splashPath" );
	}

	/**
	 * Create the application.
	 */
	public WorkshopFake() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle( "Java - adc_op/src/com/zte/adc/web/action/LoginAction.java - Oracle Workshop for WebLogic" );
		frame.setBounds(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		fakeWorkshop();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI( frame );
	
			frame.addMouseListener( new MouseAdapter() {

				private final List<String>	 fileNames	= new ArrayList<String>();
				{
					/*File root = new File( WorkshopFake.class.getResource( "/" ).getFile() );
					root.list( new FilenameFilter() {
						@Override
						public boolean accept( File dir, String name ) {
							if ( name.matches( "[a-zA-Z]+\\d\\.png" ) ) {
								System.out.println( "Add a picture :" + name);
								fileNames.add( name );
							}
							return false;
						}
					} );*/
					
					for ( int i = 1; i <= 4; i++ ) {
						fileNames.add( "workshop" + i + ".png" );
					}
				}
				
				@Override
				public void mouseClicked( MouseEvent e ) {
					String name = fileNames.remove( 0 );
					fileNames.add( name );
					setBgImg( name );
				}

				
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fakeWorkshop() {
		URL url = WorkshopFake.class.getResource( "/oracle-logo-small.png" );
		Image image = kit.createImage( url );
		frame.setIconImage(  image );
		frame.getLayeredPane().add( imgLabel, new Integer( Integer.MIN_VALUE ) );
		setBgImg( "workshop.png" );
	}
	
	public void setBgImg( final String name ) {
		( ( JPanel ) frame.getContentPane() ).setOpaque( false );
		URL url = WorkshopFake.class.getResource( "/" + name );
		ImageIcon img = new ImageIcon( url );
		imgLabel.setIcon( img );
		imgLabel.setBounds( 0, 0, img.getIconWidth(), img.getIconHeight() );
	}

}
