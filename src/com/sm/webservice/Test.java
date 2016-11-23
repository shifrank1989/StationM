package com.sm.webservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import javax.crypto.spec.PSource;
import javax.xml.rpc.ServiceException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataService_ServiceLocator service = new DataService_ServiceLocator();
		try {
			DataService_PortType proxy = service.getDataServicePort();
			try {
				File file=new File("D:\\try.txt");
				String xmlString = proxy.getMasterData(1047L,"afe1ab20e8934daa9d38bb258d2102d0", 1);
						
				//String icrString=proxy.getIncrMasterData(1047L,"1.1.036b", "afe1ab20e8934daa9d38bb258d2102d0");
				try {
					PrintStream ps=new PrintStream(new FileOutputStream(file));
					ps.println(xmlString);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(icrString);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}