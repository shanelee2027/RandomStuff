package korean_vocab_tester;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
	
	static String directoryName;
	
	static Scanner in;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		directoryName = System.getProperty("user.dir") + "/src/korean_vocab_tester";
		
		
		in = new Scanner(System.in);
		System.out.println("Which file would you like to test (e.g. korean_vocab_1)");
		String testingFileName = in.nextLine() + ".txt";
		
		FileReader fr = new FileReader(directoryName + "/" + testingFileName);
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<String> englishWords = new ArrayList<>();
		ArrayList<String> koreanWords = new ArrayList<>();
		
		String line;
		while((line = br.readLine()) != null) {
			String[] words = line.split(", ");
			englishWords.add(words[0]);
			koreanWords.add(words[1]);
		}
		
		ArrayList<Integer> randomlyGeneratedList = new ArrayList();
		for(int i = 0; i < englishWords.size(); i++) {
			randomlyGeneratedList.add(i);
		}
		Collections.shuffle(randomlyGeneratedList);
		
		ArrayList<String> incorrectEnglishWords = new ArrayList<String>();
		ArrayList<String> incorrectKoreanWords = new ArrayList<String>();
		
		for(int i = 0; i < englishWords.size(); i++) {
			int index = randomlyGeneratedList.get(i);
			System.out.print(englishWords.get(index) + ": ");
			String answer = in.nextLine();
			if(answer.equals("save")) {
				saveIncorrectWords(incorrectEnglishWords, incorrectKoreanWords);
			}
			else if(answer.equals(koreanWords.get(index))) System.out.println("correct"); 
			else {
				System.out.println("incorrect: " + koreanWords.get(index));
				incorrectEnglishWords.add(englishWords.get(index));
				incorrectKoreanWords.add(koreanWords.get(index));
			}
		}
		
		System.out.println("You got " + incorrectEnglishWords.size() + " wrong");
		System.out.println("Would you like to save incorrect words");
		String answer = in.nextLine();
		if(answer.toLowerCase().equals("yes")) {
			saveIncorrectWords(incorrectEnglishWords, incorrectKoreanWords);
		}
		
	}
	
	public static void saveIncorrectWords(ArrayList<String> incorrectEnglishWords, ArrayList<String> incorrectKoreanWords) throws IOException {
		System.out.print("To what name?: ");
		String answer2 = in.nextLine();
		File incorrectWordsFile = new File(directoryName + "/" + answer2 + ".txt");
		incorrectWordsFile.createNewFile();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(incorrectWordsFile));
		
		for(int j = 0; j < incorrectEnglishWords.size(); j++) {
			writer.write(incorrectEnglishWords.get(j) + ", " + incorrectKoreanWords.get(j));
			writer.newLine();
		}
		
		writer.close();
	}

}
