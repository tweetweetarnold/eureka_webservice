package services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUpload {
	public String[] imageUpload(byte[] file) throws IOException {
		Cloudinary cloudinary = new Cloudinary();
		String[] output = new String[2];
		Map config = new HashMap();
		config.put("cloud_name", "dezzlzkir");
		config.put("api_key", "422542863674283");
		config.put("api_secret", "q4VOqtTv2RuQk4ZtdRDDqyIg8UA");
		cloudinary = new Cloudinary(config);
		Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		String publicId = (String) uploadResult.get("public_id");
		output[1] = publicId;
		String url = cloudinary.url().generate(publicId);
		String htmltag = cloudinary.url().format("jpeg")
				.transformation(new Transformation().width(700).height(450).crop("fill")).imageTag(publicId);
		// If setting defining a public_id
		// String url = cloudinary.url().generate("new");
		// String htmltag = cloudinary.url().format("jpeg").transformation(new
		// Transformation().width(100).height(100).crop("fill")).imageTag("public_id");
		output[0] = url;
		System.out.println(url);
		System.out.println(htmltag);
		return output;
	}
	
	public boolean deleteImage(String publicId) throws IOException {
		Cloudinary cloudinary = new Cloudinary();
		//String[] output = new String[2];
		Map config = new HashMap();
		config.put("cloud_name", "dezzlzkir");
		config.put("api_key", "422542863674283");
		config.put("api_secret", "q4VOqtTv2RuQk4ZtdRDDqyIg8UA");
		cloudinary = new Cloudinary(config);
		Map uploadResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

		return true;
	}

}
