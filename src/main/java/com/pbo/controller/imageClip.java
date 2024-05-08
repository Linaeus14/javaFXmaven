package com.pbo.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;

public class imageClip {

    public static void mask(String path, ImageView profileImage) {
        // Crop image into 1:1
        Image originalImage = new Image(path);
        double targetSize = Math.min(originalImage.getWidth(), originalImage.getHeight());
        double xOffset = (originalImage.getWidth() - targetSize) / 2;
        double yOffset = (originalImage.getHeight() - targetSize) / 2;
        Image croppedImage = new WritableImage(originalImage.getPixelReader(), (int) xOffset, (int) yOffset,
                (int) targetSize, (int) targetSize);
        // clip image
        profileImage.setImage(croppedImage);
        Rectangle clip = new Rectangle(profileImage.getFitWidth(), profileImage.getFitHeight());
        clip.setArcWidth(profileImage.getFitWidth());
        clip.setArcHeight(profileImage.getFitHeight());
        profileImage.setClip(clip);
    }
}
