/*
 * @Auther Guy Cohen          ID - 204536221
 * @Auther Itai Blumenkants   ID - 313260119
 * 
 */

package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MyInterpreter {

	public static HashMap<String, Command> commandMap = new HashMap<String, Command>();
	public static HashMap<String, String> varToPath = new HashMap<>();
	public static HashMap<String, Double> SymbolTbl = new HashMap<String, Double>();
	public static HashMap<String, Double> pathToValue = new HashMap<>();
	public static HashMap<String, LinkedList<String>> pathToVar = new HashMap<>();

	static {
		commandMap.put("disconnect", new DisconnectCommand());
		commandMap.put("return", new returnCommand());
		commandMap.put("=", new AssignCommand());
		commandMap.put("openDataServer", new OpenServerCommand());
		commandMap.put("while", new LoopCommand());
		commandMap.put("sleep", new SleepCommand());
		commandMap.put("var", new VarCommand());
		commandMap.put("connect", new ConnectCommand());
		commandMap.put("print", new PrintCommand());

	}

	public static double interpret(String[] lines) {
		return parser(lexer(lines));

	}

	
	public static ArrayList<String> lexer(String[] code) {
		ArrayList<String> arrayCode = new ArrayList<String>();
		String[] tmp;
		String[] tmp1;

		for (String cell : code) {
			tmp = cell.split("[ ]+");
			
			for(String s :tmp) {
				tmp1=s.split("(?<=[-+*/()=])|(?=[-+*/()=])");
				for (int i = 0; i < tmp1.length; i++) {
					tmp1[i]= tmp1[i].trim();
					arrayCode.add(tmp1[i]);
					
				}
			}
			arrayCode.add(";");
		}

		return arrayCode;
	}

	public static double parser(ArrayList<String> cli) {
		System.out.println("Starting Parser!!");
		double statusCode = 0;
		String word;
		String[] args;
		int i = 0;

		while (i < cli.size() - 1) {
			word = cli.get(i);				
			if (commandMap.containsKey(word)) {
				args = extractCommands(i, cli);
				i = Integer.parseInt(args[args.length - 1]);
				args[args.length - 1] = ";";
				statusCode = commandMap.get(word).doCommand(args);
			}
			i++;
		}
		return statusCode;
	}

	public static String[] extractCommands(int index, ArrayList<String> rawCode) {
		int i = index + 1;
		ArrayList<String> command = new ArrayList<String>();

		switch (rawCode.get(index)) {
		case "print":
			while (!rawCode.get(i).equals(";")) {
				i++;
				command.add(rawCode.get(i));
			}
			break;

		case "=":
			command.add(rawCode.get(index - 1));
			while (!rawCode.get(i).equals(";"))
				command.add(rawCode.get(i++));
			break;

		case "disconnect":
			break;

		case "var":
			command.add(rawCode.get(i));
			break;

		case "while":
			while (!rawCode.get(i).equals("}"))
				command.add(rawCode.get(i++));
			command.add(rawCode.get(i));
			break;

		default:
			while (!rawCode.get(i).equals(";")) {
				command.add(rawCode.get(i));
				i++;
			}

		}

		
		command.add(i + "");
		String[] cmd = new String[command.size()];
		command.toArray(cmd);

		return cmd;
	}

}
