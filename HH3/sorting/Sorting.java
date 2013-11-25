public class Sorting {
	public static void main(String[] args){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<Integer>();
		do{
			list.add(Integer.nextInt());
		} while(reader.ready());

		Collections.sort(list);

		for(Integer i : list){
			System.out.println(i);
		}
	}
}