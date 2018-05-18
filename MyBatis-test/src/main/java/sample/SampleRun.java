package sample;

import org.springframework.web.bind.annotation.RestController;

import java.io.Reader;

import org.apache.taglibs.standard.resources.Resources;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class SampleRun {
	
	@RequestMapping("/hello")
	public String hello() {
		return "showMessage";
	}
}
