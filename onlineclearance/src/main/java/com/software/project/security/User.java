// package com.software.project.security;

// import java.time.LocalDateTime;


// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;

//     private String username;

//     private String firstName;
//     private String lastName;
//     private String password;
//     private String roles;
//     private String email;
//     private String phone;

    
//     private Boolean status;
//     private String department;
//     private String year;
//     private LocalDateTime date;
//     private String reason;
//     private String libraryStatus;
//     private String sportStatus;

      


// }

package com.software.project.security;
 
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)
    @Size(min=3, max=20 , message = "put valid charactor length between 3 and 20")
    private String username;
   
    @Size(min=3, max=20 , message = "put valid charactor length between 3 and 20")
    private String firstName;
 
    @Size(min=3, max=20 , message = "put valid charactor length between 3 and 20")
    private String lastName;
 
    @Size(min=3, max=100 , message = "put valid charactor length between 3 and 20")
    private String password;
 
    @Size(min=3, max=30 , message = "put valid charactor length between 3 and 20")
    private String roles;

    @Email
    @Column(unique=true)
    @NotBlank(message="provide valid email address")
    private String email;
   
    @Size(min=1,max=20 , message = "put valid charactor length between 3 and 20")
    private String phone;
 
   
    private Boolean status;
    @Size(min=3, max=30 , message = "put valid charactor length between 3 and 20")
    private String department;
 
    @Size(min=1, max=20 , message = "put valid charactor length between 3 and 20")
    private String year;
 
    private LocalDateTime date;
    private String libraryStatus;
    private String sportStatus;
    private String reason;
 
 
 
 
 
}
 

