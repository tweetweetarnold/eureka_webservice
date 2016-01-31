package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;

public class ChineseValidation {
	
	public boolean checkForChineseWords(String text) throws APIError {
		Pattern p = Pattern.compile("[a-zA-Z]+");
		
		Matcher m = p.matcher(text); 
		
		
		if(m.find()) {
			System.out.println("contains alphabet letters"); 
			return false;
		}	else{ 
				System.out.println("contains no letters"); 
				DetectLanguage.apiKey = "8e0943673c032a082b407658a77b68e9";
				String language = DetectLanguage.simpleDetect(text);
				if (language.equals("zh") || language.equals("zh-Hant")) {
					System.out.println(language);
					return true;
				}
			} 
	
		return false;
	}
}
