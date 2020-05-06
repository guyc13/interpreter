package test;

public class VarCommand implements Command {

	public double doCommand(String[] args) {
		if (MyInterpreter.SymbolTbl.containsKey(args[0])) {
			System.out.println("unvalid var command");
			return -1;
		}
		MyInterpreter.SymbolTbl.put(args[0], 0.0);
		return 0;
	}
}
