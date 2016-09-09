public class Main {
	public static void main(String[] args) {
		char[] testChar = {'a','b','c'};
		int[] tags = new int[testChar.length];
		for (int i = 0; i < tags.length; i++) {
			tags[i] = -1;
		}
		getAllSubSets(testChar,tags,0);
	}

	public static void getAllSubSets(char[] set,int[] tag,int tagIndex) {
		if(tagIndex == set.length) {
			System.out.print("{ ");
			for (int i = 0; i < set.length; i++) {
				if(tag[i] == 1) {
					System.out.print(set[i] + " ");
				}
			}
			System.out.print("}");
			System.out.println();
			return;
		}
		tag[tagIndex] = 0;
		getAllSubSets(set,tag,tagIndex+1);
		tag[tagIndex] = 1;
		getAllSubSets(set,tag,tagIndex+1);
	}
}