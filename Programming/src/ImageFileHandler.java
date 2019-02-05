import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageFileHandler extends FileHandler {
	
	int width; 
	int height;
	String image_type; 
	BufferedImage img; 
	
	public ImageFileHandler() {
		
	}
	
	public ImageFileHandler(String file_name) {
		super(file_name);
		img = null;
	}

	@Override
	public void readFile() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeFile(String file_name) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
