package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class DataReaderServer {
	
	private static volatile boolean stop = false;
	private int port;
	private int frequency;
	
	public DataReaderServer(int port, int frequency) {
		stop = false;
		if(port<1 || port >65000)
			System.out.println("Unvalid port!");
		this.port = port;
		if(frequency < 1)
			System.out.println("Unvalid frequency");
		this.frequency = frequency;
	}
	
	public void runServer() {
		try {
			ServerSocket server=new ServerSocket(port);
			server.setSoTimeout(1000);
				try{
					while(!stop){
					Socket client=server.accept();
					BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
					String line = in.readLine();
					while(line != null){
						String[] paths = line.split(",");
						MyInterpreter.pathToValue.put("simX",Double.parseDouble(paths[0]));
						MyInterpreter.pathToValue.put("simY",Double.parseDouble(paths[1]));
						MyInterpreter.pathToValue.put("simZ",Double.parseDouble(paths[2]));
						line = in.readLine();
						try {Thread.sleep(1000/frequency);} catch (InterruptedException e) {e.printStackTrace();}
					}
					in.close();
					client.close();
					
					}	
				}catch(SocketTimeoutException e){}
			server.close();
		} catch (IOException e) {}
	}

	public static void close() {
		DataReaderServer.stop = true;
	}
}
