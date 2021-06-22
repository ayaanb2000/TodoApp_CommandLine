import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Todo {
	public static void main(String args[]) throws IOException{
		SimpleDateFormat dte = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();



	     if(args.length < 1 || args[0].equals("help")){
	        System.out.println("Usage :-\n$ ./todo add \"todo item\"  # Add a new todo\n$ ./todo ls               # Show remaining todos\n$ ./todo del NUMBER       # Delete a todo\n$ ./todo done NUMBER      # Complete a todo\n$ ./todo help             # Show usage\n$ ./todo report           # Statistics");
	     }
	     else if(args[0].equals("add")){

			if(args.length != 2){
				System.out.println("Error: Missing todo string. Nothing added!");
				return;
			}
			try {
				FileWriter Writer = new FileWriter("todo.txt",true);
				Writer.write(args[1]+"\n");
				Writer.close();
				System.out.println("Added todo: \""+args[1]+"\"");
			  } catch (Exception e) {
				System.out.println("An error occurred.");
			  };
			  
		 }
		 else if(args[0].equals( "ls")){
			  
			    try{
					 
					List <String> todos = new ArrayList<String>();
					BufferedReader stream = new BufferedReader(new FileReader("todo.txt"));
					String task ;
					while((task = stream.readLine()) != null  ){
						if(task.length() != 0){ 
						todos.add(task);
						}
					}
					if(todos.size() == 0){
						System.out.println("There are no pending todos!");
						return;
					}
					if(stream != null ){
						stream.close();
					}
					 int cnt = todos.size();
					 Collections.reverse(todos);
					for(String item:todos){
						System.out.println("["+cnt+"] "+item);
						cnt--;
					}
				}catch (Exception e) {
					System.out.println("There are no pending todos!");
					return;
				  }
				
		}
		else if(args[0].equals( "del")){
			
			  try{
						BufferedReader stream = null;
						List <String> todos = new ArrayList<String>();
						stream = new BufferedReader(new FileReader("todo.txt"));
						
						String task ;
						while((task = stream.readLine()) != null  ){
							if(task.length() != 0){ 
								todos.add(task);
							}
						}
						if(stream != null ){
							stream.close();
						}
						if(0 >= Integer.parseInt(args[1]) || Integer.parseInt(args[1]) > todos.size()){
							System.out.println("Error: todo #"+args[1]+" does not exist. Nothing deleted.");
							return;
						}
						
						int rm = Integer.parseInt(args[1])-1;
						todos.remove(rm);
						FileWriter Writer = new FileWriter("todo.txt");
						for(String item:todos){
							Writer.write(item+"\n");
						}
						Writer.close();
						System.out.println("Deleted todo #"+(rm+1));


			  }
			  catch (Exception e) {
				System.out.println("Error: Missing NUMBER for deleting todo.");
			  };
		}
		else if(args[0].equals( "done")){
			BufferedReader stream = null;
			List <String> todos = new ArrayList<String>();
			stream = new BufferedReader(new FileReader("todo.txt"));
			  try{
				
						if(args.length != 2){
							System.out.println("Error: Missing NUMBER for marking todo as done.");
							return;
						}
						String task ;
						
						while((task = stream.readLine()) != null  ){
							if(task.length() != 0){ 
								todos.add(task);
							}
						}
						if(stream != null ){
							stream.close();
						}
						
						
						int rm = Integer.parseInt(args[1])-1;
						String done = todos.get(rm);
						todos.remove(rm);
						
						FileWriter Writer1 = new FileWriter("todo.txt");
						FileWriter Writer2 = new FileWriter("todo.txt");
						for(String item:todos){
							Writer1.write(item+"\n");
						}
						Writer2 = new FileWriter("done.txt",true);
						Writer2.write("x "+dte.format(date)+" "+done+"\n");
						Writer1.close();
						Writer2.close();
						System.out.println("Marked todo #"+(rm+1)+" as done. ");


			  }catch (Exception e) {
				System.out.println("Error: todo #"+args[1]+" does not exist.");
			  };
		}
		else if(args[0].equals( "report")){
			
			BufferedReader todolist = new BufferedReader(new FileReader("todo.txt"));
			BufferedReader donelist = new BufferedReader(new FileReader("done.txt"));
			int remaining = 0;
			int done = 0;
			String task;
			while((task = todolist.readLine()) != null){
				if(task.length() != 0){ 
					  remaining++;
				}
			}
			while((task = donelist.readLine()) != null){
				if(task.length() != 0){ 
					  done++;
				}
			}
            System.out.println(dte.format(date)+" Pending : "+remaining+" Completed : "+done);

		}

	    
	}
}
