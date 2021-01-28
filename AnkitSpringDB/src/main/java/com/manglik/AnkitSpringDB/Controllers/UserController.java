package com.manglik.AnkitSpringDB.Controllers;

import com.manglik.AnkitSpringDB.Models.Person;
import com.manglik.AnkitSpringDB.Repository.UserRepository;
import com.manglik.AnkitSpringDB.SModels.AuthenticationRequest;
import com.manglik.AnkitSpringDB.SModels.AuthenticationResponse;
import com.manglik.AnkitSpringDB.SService.MyUserDetailsService;
import com.manglik.AnkitSpringDB.Service.UserService;
import com.manglik.AnkitSpringDB.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
////@RequestMapping(path="/demo")

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtTokenUtil;
/*
    @GetMapping(path = "/addUser")
        Person addUser (@RequestParam String name
            , @RequestParam String email) {
        Person n = new Person();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return n;
    }
 */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Ankit Manglik";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw  new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping("/addUser")
    public boolean addUser(@RequestParam("name") String name, @RequestParam("email") String email){
        return userService.addUser(name,email);
    }
    @RequestMapping("/updateUser")
    public boolean updateUser(@RequestParam("id") int id,@RequestParam("name") String name){
        return userService.updateUser(name,id);
    }

    @RequestMapping("/deleteUser")
    public boolean deleteUser(@RequestParam("id") int id){
        return userService.delete(id);
    }

    @GetMapping(path = "/getUser")
    List<Person> getUser () {
       return userRepository.findAll();
    }
    /*
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam String name
            , @RequestParam String email) {

        Person n = new Person();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

     */
    /*
    public boolean delete(@PathVariable int id){
        Person n = userRepository.getOne(id);
        userRepository.delete(n);
        return true;
    }

     */

    /* To get User list : another way of sol'n
    @GetMapping(path="/getAllUser")
    public @ResponseBody Iterable<Person> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
     */
}

