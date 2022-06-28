package com.alkemy.ong.controller;

import com.alkemy.ong.auth.config.seeder.DataBaseSeeder;
import com.alkemy.ong.models.request.ImageDTO;
import com.alkemy.ong.models.request.UserUpdateRequest;
import com.alkemy.ong.models.response.UserDetailsResponse;
import com.alkemy.ong.service.AwsS3Service;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.utils.Base64ToMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import static com.alkemy.ong.controller.ApiConstants.ROLE_ADMIN;

@RestController
@RequestMapping(path = "/users")
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   private DataBaseSeeder seeder;

   @Autowired
   private AwsS3Service awsS3Service;


   @PatchMapping(path = "/{id}")
   public ResponseEntity<Void> updateUser(@PathVariable("id") @Valid @NotNull Long id,
                                    @RequestBody @Valid UserUpdateRequest request) {
      userService.updateUser(id, request);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @DeleteMapping(path = "/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable("id") @Valid @NotNull Long id) {
      userService.deleteUser(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }

   @PreAuthorize(ROLE_ADMIN)
   @GetMapping(path = "/users")
   public ResponseEntity<List<UserDetailsResponse>> getUsers() {
      return ResponseEntity.ok(userService.getUsers());
   }

   @PostMapping(path = "/upload")
   public ResponseEntity<?> upload(@RequestBody ImageDTO imagendto) throws IOException {
      String fileUrl = awsS3Service.uploadFile(imagendto.getFile());
      return ResponseEntity.ok(fileUrl);
   }

}
