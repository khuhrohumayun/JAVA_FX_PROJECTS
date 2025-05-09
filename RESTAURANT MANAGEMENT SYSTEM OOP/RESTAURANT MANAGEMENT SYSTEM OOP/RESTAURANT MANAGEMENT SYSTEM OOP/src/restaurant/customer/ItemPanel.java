package restaurant.customer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

class ItemPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel imageLabel;
    private JLabel priceLabel;

    public ItemPanel(String itemName, String imagePath, double itemPrice) {
        nameLabel = new JLabel(itemName);
        imageLabel = new JLabel();
        priceLabel = new JLabel(Double.toString(itemPrice) + "$");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(nameLabel);
        add(imageLabel);
        add(priceLabel);

        // Download and set the image
        if(imagePath != null && imagePath != "")
            setImageFromURL(imagePath, 100, 100);
    }

    private void setImageFromURL(String imageUrl, int width, int height) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            if (image != null) {
                // Scale the image to fit the label
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
