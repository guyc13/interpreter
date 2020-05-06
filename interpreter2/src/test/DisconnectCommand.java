package test;

public class DisconnectCommand implements Command {

	public double doCommand(String[] args) {
		DataReaderServer.close();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ConnectCommand.out.println("bye");
		ConnectCommand.out.close();

		return 0;
	}

}
