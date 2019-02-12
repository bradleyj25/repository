import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MeasureImageQuality {

	public static void main(String[] args) {
		ImageFileHandler img_handler = new ImageFileHandler();
		
		BufferedImage org_image = img_handler.readFile("H:\\My Documents\\Advanced programming\\house_org.jpg");
		BufferedImage compressed_image = img_handler.readFile("H:\\My Documents\\Advanced programming\\house_org_compressed.jpg");
		
		
		MeasureImageQuality imgQuality = new MeasureImageQuality();
		
		imgQuality.displayImage(org_image, "original");
		imgQuality.displayImage(compressed_image, "compressed");
		
		// calculating the x and y of the image 
		
		double squared_sum = 0;
		for(int y = 0; y < org_image.getHeight(); y++) { 
			for(int x = 0; x < org_image.getWidth(); x++) {
				
				int rgbvalue_org = org_image.getRGB(x, y);
				
				int alpha = (rgbvalue_org >> 24) & 0xff;
				int red = (rgbvalue_org >> 16) & 0xff;
				int green = (rgbvalue_org >> 8) & 0xff;
				int blue = (rgbvalue_org) & 0xff;
				

				int grayscale_org = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
				
				int rgbvalue_compressed = compressed_image.getRGB(x, y);
				
				alpha = (rgbvalue_compressed >> 24) & 0xff;
				red = (rgbvalue_compressed >> 16) & 0xff;
				green = (rgbvalue_compressed >> 8) & 0xff;
				blue = (rgbvalue_compressed) & 0xff;
				
				int grayscale_compressd = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
				
				squared_sum += (Math.pow((grayscale_org - grayscale_compressd), 2));
			}
		}
			
		double mean_squared_error = squared_sum / (org_image.getHeight() * org_image.getWidth());
		
		double PSNR = 10 * Math.log10(Math.pow(255, 2) / mean_squared_error);
	
		System.out.printf("Computed PSNR: %.2f dB", PSNR);
		
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

