package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog

        Image image = new Image(description,dimensions);

        Blog blog = blogRepository2.findById(blogId).get();
        image.setBlog(blog);

        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;
    }

    public void deleteImage(Integer id){

        Image image = imageRepository2.findById(id).get();
        Blog blog = image.getBlog();
        blog.getImageList().remove(image);
        blogRepository2.save(blog);

        imageRepository2.deleteById(id);
        return;
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image = imageRepository2.findById(id).get();

        String[] screen = screenDimensions.split("X");
        int screenArea = 1;
        for(String dim : screen){
            screenArea = screenArea * Integer.parseInt(dim);
        }

        String[] imageDim = image.getDimensions().split("X");
        int imageArea = 1;
        for(String dim : imageDim){
            imageArea = imageArea * Integer.parseInt(dim);
        }

        return screenArea/imageArea;
    }
}
