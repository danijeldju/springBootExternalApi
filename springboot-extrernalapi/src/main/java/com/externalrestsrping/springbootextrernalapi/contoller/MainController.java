package com.externalrestsrping.springbootextrernalapi.contoller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.externalrestsrping.springbootextrernalapi.domain.City;
import com.externalrestsrping.springbootextrernalapi.service.CityService;
import com.externalrestsrping.springbootextrernalapi.service.DocsService;
import com.externalrestsrping.springbootextrernalapi.service.ParsingService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;





@RestController
@Controller
@ResponseBody
public class MainController {
	
	
	@Autowired
	private ParsingService parsingService;
	@Autowired
	private DocsService docsService;
	@Autowired
	private CityService cityService;
	

	public MainController(DocsService docsService) {
		this.docsService = docsService;
	}
	
	public MainController(CityService cityService) {
		this.cityService = cityService;
	}
	
	public MainController() {}
	
	//add some city from list
	@GetMapping({ "/", "/hello" })
    public ModelAndView home15( @RequestParam(defaultValue="") String name){
        ModelAndView modelAndView = new ModelAndView();
     	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		TypeReference <List<City>> typeReference  = new TypeReference<List<City>>(){} ;
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/result2.json");
		try {
			List<City> cities = mapper.readValue(inputStream,typeReference).subList(0, 30);
	
			modelAndView.addObject("cities", cities);
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        
        modelAndView.setViewName("home");
        return modelAndView;
	}
	
    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value="error" , required =false) String error ){
        ModelAndView modelAndView = new ModelAndView();
        if(error != null){
            modelAndView.setViewName("error");
        } else
        modelAndView.setViewName("login");
        return modelAndView;
    }
    
	 //Working state delete , or add 10 if empthy plus edit to finish
	 @RequestMapping(value="/edit", method = RequestMethod.GET)
	    public ModelAndView home12( @RequestParam(defaultValue="") String name){
	        ModelAndView modelAndView = new ModelAndView();
	        
	     List<City> listcities = cityService.findList();
	      modelAndView.addObject("listcities", listcities);
	  	ObjectMapper mapper = new ObjectMapper();
		
		//mc.puu(); call to write json
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		TypeReference <List<City>> typeReference  = new TypeReference<List<City>>(){} ;
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/result2.json");
		try {
			List<City> cities = mapper.readValue(inputStream,typeReference).subList(0, 10);
			 StringBuilder sbs =new StringBuilder();
			

		boolean citieslist=Stream.of(cityService.findList())
				.anyMatch(it-> it==null || it.isEmpty());
			if(citieslist) {
			cityService.save(cities);
			System.out.println("Cities Saved!");}
			else {
				System.out.println("There is enough cities");
		}
		} catch (IOException e){
			System.out.println("Unable to save cities: " + e.getMessage());
		}
  			System.out.println(" Saved10!");
	      modelAndView.setViewName("/edit");
	        return modelAndView;
	    }
	
	 
	 //adding city - cities
	 @RequestMapping(value="/addcities/{name}")
	    public ModelAndView addCities( @PathVariable(name = "name") String name, @Valid City ciity,  ModelMap model){
	        ModelAndView modelAndView = new ModelAndView();  
	        
	        //take from json file
	        ObjectMapper mapper = new ObjectMapper();
		      mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				TypeReference<List<City>> typeReference = new TypeReference<List<City>>(){};
				InputStream inputStream = TypeReference.class.getResourceAsStream("/json/result2.json");

				try {

					List<City> cities = mapper.readValue(inputStream,typeReference);

					City cityy=cities.stream()
							.filter(city -> city.getName().equals(name) )        
								.findAny()                                    
									.orElse(null);
					
					//new List
					List<City> citiess = new ArrayList<City>();
					int bListSize= cityService.findList().size();

					// If not found, return null
					System.out.println("pivoo"+cityy.getName());
					if(cityy.equals(null)) {
						modelAndView.addObject("message","Noo0 that beer!");

	System.out.println("messs55!");
					System.out.println("Beer is lost!");
					}
					else {
						cityService.save(citiess);

						//beerService.save(beer1);
						System.out.println("There is  beeer");
					}
				} catch (IOException e){
					System.out.println("Unable to save beers: " + e.getMessage());
				}
	       
	        return new ModelAndView("redirect:/edit", model);
	    }
	 
	 //edit
		@RequestMapping(value="/editcity/editcity/{id}", method = RequestMethod.GET)
	    public ModelAndView showBeer(@PathVariable(name = "id") Integer id){  
	            ModelAndView modelAndView = new ModelAndView("/editcity");  
	           City city = cityService.get(id);
	            System.out.println("city"+city.getName());
	            modelAndView.addObject("city", city);
	            
	                return modelAndView;
		}

				//save after edit
		 @RequestMapping(value="/addcity/{id}/{name}", method = RequestMethod.POST)
		    public ModelAndView addBeerEdit(@PathVariable(name = "id") Integer id, @PathVariable(name = "name") String name,  @Valid City city,  ModelMap model){
		        ModelAndView modelAndView = new ModelAndView(); 
		        System.out.println("Imeee"+  name);
		        City cityy = cityService.get(id);
		        cityService.save(cityy, city.getName());
		        return new ModelAndView("redirect:/edit", model);
		    }
	 
		 @RequestMapping("/deletecity/{id}")
	    public ModelAndView deleteCity(@PathVariable(name = "id") Integer id,  ModelMap model) {
		 cityService.delete(id);   
		 return new ModelAndView("redirect:/edit", model);
	    }
    
    
    


}
