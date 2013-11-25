import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Sorting {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<Integer>();
		do{
			list.add(Integer.parseInt(reader.readLine()));
		} while(reader.ready());

		Collections.sort(list);

		for(Integer i : list){
			System.out.println(i);
		}
	}
}