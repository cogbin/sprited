package eu.cogbin.sprited.core.io.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.SerializerBase;

/**
 * Serializes bitmap images as Base64 encoded PNGs
 * 
 * @author Danny
 * 
 */
public class BufferedImageSerializer extends SerializerBase<BufferedImage> {

	public BufferedImageSerializer() {
		super(BufferedImage.class);
	}

	@Override
	public void serialize(BufferedImage value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(value, "PNG", out);
		jgen.writeBinary(out.toByteArray());
	}

}
