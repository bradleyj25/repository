import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ObjectRecognizer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ImageFileHandler img_handler = new ImageFileHandler();
		
		BufferedImage img1 = img_handler.readFile("H:\\My Documents\\Advanced programming\\background.jpg");
		BufferedImage img2 = img_handler.readFile("H:\\My Documents\\Advanced programming\\object.jpg");

		
		MeasureImageQuality imgQuality = new MeasureImageQuality();
		
		imgQuality.displayImage(img1, "original");
		imgQuality.displayImage(img2, "compressed");
	}
	
	
	public static double getMSE(BufferedImage img1, BufferedImage img2) {
		
		double squared_sum = 0;
		for(int y = 0; y < img1.getHeight(); y++) { 
			for(int x = 0; x < img2.getWidth(); x++) { 
				
int rgbvalue_org = img1.getRGB(x, y);
				
				int alpha = (rgbvalue_org >> 24) & 0xff;
				int red = (rgbvalue_org >> 16) & 0xff;
				int green = (rgbvalue_org >> 8) & 0xff;
				int blue = (rgbvalue_org) & 0xff;
				

				int grayscale_org = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
				
				int rgbvalue_compressed = img2.getRGB(x, y);
				
				alpha = (rgbvalue_compressed >> 24) & 0xff;
				red = (rgbvalue_compressed >> 16) & 0xff;
				green = (rgbvalue_compressed >> 8) & 0xff;
				blue = (rgbvalue_compressed) & 0xff;
				
				int grayscale_compressd = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
				
				squared_sum += (Math.pow((grayscale_org - grayscale_compressd), 2));
			
			}
		}

		double mean_squared_error = squared_sum / (img1.getHeight() * img2.getWidth());
		
		
	
		System.out.printf("Computed Mean Squared Error: %.2f dB", mean_squared_error);
		return mean_squared_error;
		
	}
	
	
public void displayImage(BufferedImage img, String title) { 
		
		BufferedImage rescaledImage = resize(img, img.getWidth()/4, img.getHeight()/4);
		
		JLabel picLabel = new JLabel(new ImageIcon(rescaledImage));
		
		JPanel jpanel = new JPanel();
		jpanel.add(picLabel);
		
		JFrame frame = new JFrame(title);
		frame.setSize(new Dimension(rescaledImage.getWidth(),
				rescaledImage.getHeight()));
		
		frame.add(jpanel);
		frame.setVisible(true);
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
		
		java.awt.Image tmp = img.getScaledInstance(newW, newH, java.awt.Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		
		return dimg;
	
	}

}
	
