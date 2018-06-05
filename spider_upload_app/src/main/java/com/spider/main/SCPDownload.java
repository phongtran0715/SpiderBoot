package com.spider.main;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.xfer.FileSystemFile;

import java.io.IOException;
import java.util.Date;

/** This example demonstrates downloading of a file over SCP from the SSH server. */
public class SCPDownload {

	private SSHClient ssh;

	public boolean execute(String host, String userName, String passWord, 
			String remoteFile, String clientFolder) {
		boolean result = false;
		ssh = new SSHClient();
		// ssh.useCompression(); // Can lead to significant speedup (needs JZlib in classpath)
		try {
			ssh.loadKnownHosts();
			System.out.println("load know host OK");
			ssh.connect(host);
			System.out.println("connect to host OK");

			//ssh.authPublickey(System.getProperty("user.name"));
			ssh.authPassword(userName, passWord);
			System.out.println("Time start : " + new Date().toString());
			ssh.newSCPFileTransfer().download(remoteFile, new FileSystemFile(clientFolder));
			System.out.println("file tranfer complete");
			System.out.println("Time end : " + new Date().toString());
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}finally {
			try {
				ssh.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
