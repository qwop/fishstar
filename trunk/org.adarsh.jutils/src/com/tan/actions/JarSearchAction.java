package com.tan.actions;

import java.awt.EventQueue;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.tan.frames.JarSearchMain;


/**
 * 打开Jar搜寻器Action
 * @author Dolphin.
 * @see IWorkbenchWindowActionDelegate
 */
public class JarSearchAction implements IWorkbenchWindowActionDelegate {
	
	public JarSearchAction() {
	}

	public void run(IAction action) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JarSearchMain frame = new JarSearchMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow w) {
	}


}