package com.movies.finalproject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FinalProjectController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@Autowired
    //private IAdminService iAdminService;

    @Autowired
    private IFinalProjectService iFinalProjectService;

    boolean isLoggedIn=false;

    @GetMapping("/")
    public String getHomePage(Model model) {
       
        
        model.addAttribute("movies",iFinalProjectService.getAll());
        model.addAttribute("admin", new Admin());
        return "index";
    }
/*
    this is unsecure version which can be hacked by sql injection
    @GetMapping("/adminlogin")
    public String adminLogin(@ModelAttribute Admin admin, Model model){
        model.addAttribute("Admin", admin);
        
        //Admin result = iAdminService.
        String sql = "SELECT * FROM ADMIN WHERE name='"+admin.getName()+"' AND PASSWORD ='"+admin.getPassword()+"'";
        

        List<Admin> mAdmin =jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Admin.class));
        if(mAdmin.size()>0){
            //found user    
            isLoggedIn=true; 
            return "admin";
        }
        else
        return "index";
    }
 
    //this is the improved version where sql injection wont work by implmenting sha 512 we hashed the password 
     */
    @GetMapping("/adminlogin")
    public String adminLogin(@ModelAttribute Admin admin, Model model){
        model.addAttribute("Admin", admin);
        

         //encrypt data 
         
        
        //Admin result = iAdminService.
        //System.out.println(encryptdata(admin.getPassword()));
        String sql = "SELECT * FROM ADMIN WHERE name='"+admin.getName()+"' AND PASSWORD ='"+encryptdata(admin.getPassword())+"'";
       
        //String sql="INSERT INTO admin (name,password) VALUES ("+"admin23"+","+encryptdata("password")+")";
        
        
        


        List<Admin> mAdmin =jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Admin.class));
        if(mAdmin.size()>0){
            //found user   
            isLoggedIn=true; 
            model.addAttribute("movies",iFinalProjectService.getAll());
            return "admin";
        }
        else
        model.addAttribute("movies",iFinalProjectService.getAll());
        model.addAttribute("admin", new Admin());
        return "index";
    }

 








   /* @GetMapping("/searchItem")
    public String getsearchItem( Model model){
        model.addAttribute("Movies", new );
        return "searchResult";
    }
 */

    private  String encryptdata(String password) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;  
            }
            return hashtext;
        }
        catch(NoSuchAlgorithmException e){

        }
        return "";
    }




    @GetMapping("/showForm")
    public String showStudentForm(Movies movies, Model model) {
        if(isLoggedIn)
        return "addmovies";
        else {
            model.addAttribute("movies",iFinalProjectService.getAll());
            model.addAttribute("admin", new Admin());
            return "index";}
    }



    
    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        //return movieSystemService.getHomePage(model);
        
        model.addAttribute("movies",iFinalProjectService.getAll());
        return "admin";
    }




    @PostMapping("/searchitem")
    public String searchItems(@ModelAttribute Movies movie, Model model){
        model.addAttribute("Movies", movie);
        Movies result = iFinalProjectService.getById(movie.getMOVIE_ID());
        System.out.println(result.toString());
        return "searchResult";
    }

    @PostMapping("add")
	public String addmovies(Movies movies, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "addmovies";
		}
		
		this.iFinalProjectService.addMovies(movies);
		return "redirect:admin";
	}
    int updateId; 

    @GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable ("id") int id, Model model) {
		Movies movie = this.iFinalProjectService.getById(id);
		/*
		model.addAttribute("student", movie);

        updateId=movie.getMOVIE_ID();
        model.addAttribute("name",movie.getMOVIE_NAME());

        
        model.addAttribute("dscp",movie.getMOVIE_DESCRIPTION());
        
        model.addAttribute("cst",movie.getMOVIE_COST());
        model.addAttribute("rat",movie.getMOVIE_RATING());
 */
        model.addAttribute("movies", movie);
		return "updatemovies";
	}

    @PostMapping("update/{id}")
	public String updatemovie(@PathVariable("id") int id, Movies movies, BindingResult result, Model model) {
		if(result.hasErrors()) {
			movies.setMOVIE_ID(id);

			return "updatemovies";
		}
		
		// update movie
		//System.out.println(movies.getMOVIE_NAME());
		
        this.iFinalProjectService.deleteById(id);
        //movies.setMOVIE_ID(id);
         iFinalProjectService.addMovies(movies);
         
         

		// get all movie ( with update)
		model.addAttribute("movies", this.iFinalProjectService.getAll());
		return "admin";
	}

    @GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable ("id") int id, Model model) {
		
		this.iFinalProjectService.deleteById(id);;
		model.addAttribute("movies", this.iFinalProjectService.getAll());
		return "admin";
		
	}




    

/*
    @GetMapping("/adminLogin")
    public String getAdminLoginPage(Model model){
        model.addAttribute("admin",new Admin());
        movieSystemService.createTempararyAdminUser();
        return "adminLogin";
    }

    @PostMapping("/loginIntoAdmin")
    public String loginIntoAdmin(@ModelAttribute("admin")Admin admin ,Model model){
        if(movieSystemService.authenticateAdmin(admin))
            return "redirect:/admin";
        else
            return "redirect:/adminLogin";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model){
        model.addAttribute("movies",movieSystemService.getAllMovies());
        model.addAttribute("movie",new Movie());
        model.addAttribute("admin",new Admin());
        return "admin";
    }

    @PostMapping("/getPageToUpdateItem")
    public String getPageToUpdateItem(@RequestParam UUID id, Model model){
        movieSystemService.getUpdatePage(id,model);
        return "updateMovie";
    }

    @PostMapping("/saveMovieItem")
    public String saveMovieItem(@ModelAttribute("movie") Movie item){
        movieSystemService.saveMovie(item);
        return "redirect:/admin";
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute("admin") Admin item){
        movieSystemService.saveAdmin(item);
        return "redirect:/admin";
    }

    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies() {
        return movieSystemService.getAllMovies();
    }

    @GetMapping("/getMovieById/{id}")
    public Movie getMovieById(@PathVariable("id") UUID id) {
        return movieSystemService.getMovieById(id);
    }

    @PostMapping("/deleteMovieById")
    public String deleteMovieById(@RequestParam UUID id) {
        movieSystemService.deleteMovieById(id);
        return "redirect:/admin";
    }

    @PostMapping("/saveMovie")
    public Movie saveMovie(@RequestBody Movie movie) {
        return movieSystemService.saveMovie(movie);
    }

    @PostMapping("/updateMovie")
    public String updateMovie(@RequestParam UUID id, @ModelAttribute("movie")Movie movie) {
        movieSystemService.updateMovie(id, movie);
        return "redirect:/admin";
    }


 */
}
