import java.io.IOException;

public class FileApplicationTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextFileHandler txt_reader = new TextFileHandler("H:\\My Documents\\Advanced programming/my_text_file.text.txt");
	
		try { 
			txt_reader.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try { 
			txt_reader.writeFile("H:\\My Documents\\Advanced programming/my_text_file2.text.txt");
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

}
