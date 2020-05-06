package test;

import java.util.ArrayList;

public class LoopCommand implements Command {
	Command[] commands;

	public double doCommand(String[] args) {
		double returnValue = 0;
		String var = args[0];
		String condition = args[1];
		int index = 5;
		ArrayList<String> lines = new ArrayList<>();
		double value = MyInterpreter.SymbolTbl.get(var);
		double upperBound = Double.parseDouble(args[2]);

		while (!args[index].equals("}")) {
			lines.add(args[index++]);
		}

		//condition recognizer
		switch (condition) {
		case "==":
			while (value == upperBound) {
				returnValue = MyInterpreter.parser(lines);
				value = MyInterpreter.SymbolTbl.get(var);
			}
			break;
		case "<=":
			while (value <= upperBound) {
				returnValue = MyInterpreter.parser(lines);
				value = MyInterpreter.SymbolTbl.get(var);
			}
			break;
		case ">=":
			while (value >= upperBound) {
				returnValue = MyInterpreter.parser(lines);
				value = MyInterpreter.SymbolTbl.get(var);
			}
			break;
		case "<":
			while (value < upperBound) {
				returnValue = MyInterpreter.parser(lines);
				value = MyInterpreter.SymbolTbl.get(var);
			}
			break;
		case ">":
			while (value > upperBound) {
				returnValue = MyInterpreter.parser(lines);
				value = MyInterpreter.SymbolTbl.get(var);
			}
			break;
		case "!=":
			while (value != upperBound) {
				returnValue = MyInterpreter.parser(lines);
				value = MyInterpreter.SymbolTbl.get(var);
			}
			break;
		}
		return returnValue;
	}
}
