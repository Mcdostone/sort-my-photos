package app.model;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.jpeg.JpegDirectory;

import java.io.File;
import java.io.IOException;

/**
 * An image is a media, nothing else.
 *
 * @author Mcdostone
 */
public class Image extends Media {

    public Image(String path) {
        super('I', path);
    }

    @Override
    public void loadMediaProperties() {
        try {
            File image = new File(this.getPath());
            Metadata metadata = ImageMetadataReader.readMetadata(image);

            ExifSubIFDDirectory directoryConfig = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            ExifIFD0Directory directoryAPN = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            JpegDirectory jpeg = metadata.getFirstDirectoryOfType(JpegDirectory.class);

            if(directoryAPN != null)
                this.getProperties().put("camera", directoryAPN.getDescription(ExifIFD0Directory.TAG_MODEL));
            if(directoryConfig != null)
                this.getProperties().put("lens", directoryConfig.getDescription(ExifSubIFDDirectory.TAG_LENS_MODEL));

            if(jpeg != null) {
                this.getProperties().put("width", jpeg.getDescription(JpegDirectory.TAG_IMAGE_WIDTH));
                this.getProperties().put("height", jpeg.getDescription(JpegDirectory.TAG_IMAGE_HEIGHT));
            }

            if(directoryConfig != null) {
                this.getProperties().put("lens", directoryConfig.getDescription(ExifSubIFDDirectory.TAG_LENS_MODEL));
                this.getProperties().put("width", directoryConfig.getDescription(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH));
                this.getProperties().put("height", directoryConfig.getDescription(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT));
                this.getProperties().put("exposure time", directoryConfig.getDescription(ExifSubIFDDirectory.TAG_EXPOSURE_TIME));
                this.getProperties().put("aperture", directoryConfig.getDescription(ExifSubIFDDirectory.TAG_APERTURE));
                this.getProperties().put("ISO", directoryConfig.getDescription(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT));
                this.getProperties().put("focal length", directoryConfig.getDescription(ExifSubIFDDirectory.TAG_FOCAL_LENGTH));
            }

        } catch (ImageProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
