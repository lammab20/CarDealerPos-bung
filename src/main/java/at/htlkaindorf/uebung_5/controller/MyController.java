package at.htlkaindorf.uebung_5.controller;

import at.htlkaindorf.uebung_5.entities.Inventory;
import at.htlkaindorf.uebung_5.entities.MyUser;
import at.htlkaindorf.uebung_5.jwt.JwtAuthenticationFilter;
import at.htlkaindorf.uebung_5.jwt.JwtAuthorizedToken;
import at.htlkaindorf.uebung_5.service.MyService;
import jakarta.servlet.ServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("public/signIn")
    public ResponseEntity<JwtAuthorizedToken> signIn(@RequestBody MyUser myUser){
        JwtAuthorizedToken authorizedToken = new JwtAuthorizedToken();

        authorizedToken.setToken(myService.signIn(myUser.getName(), myUser.getPassword()));
        return ResponseEntity.ok(authorizedToken);
    }

    @GetMapping("user/getTotalCars")
    public ResponseEntity<String> getTotalCars(){
        return ResponseEntity.ok(myService.getTotalCars());
    }

    @GetMapping("user/getModel")
    public ResponseEntity<Inventory> getModel(@RequestParam String model){
        Inventory inventory = myService.getModel(model);

        return ResponseEntity.ok(inventory);
    }

    @GetMapping("admin/getDealerNames")
    public ResponseEntity<String> getDealerNames(){
        return ResponseEntity.ok(myService.getDealerNames());
    }

    @GetMapping("admin/getHighPsCars")
    public ResponseEntity<String> getHighPsCars(){
        return ResponseEntity.ok(myService.getHighPsCars());
    }
}
