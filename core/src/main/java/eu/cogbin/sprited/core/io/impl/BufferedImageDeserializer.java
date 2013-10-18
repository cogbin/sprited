package eu.cogbin.sprited.core.io.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

/**
 * 
 * @author Danny
 * 
 */
public class BufferedImageDeserializer extends StdDeserializer<BufferedImage> {

	public BufferedImageDeserializer() {
		super(BufferedImage.class);
	}

	@Override
	public BufferedImage deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		byte[] bytes = jp.getBinaryValue();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		return ImageIO.read(in);
	}

}
