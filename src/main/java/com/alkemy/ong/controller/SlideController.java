package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.SlideRequest;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.models.response.SlidesBasicResponse;
import com.alkemy.ong.service.SlideService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.alkemy.ong.utils.ApiConstants.ROLE_ADMIN;
import static com.alkemy.ong.utils.ApiConstants.ROLE_USER;

@RestController
@RequestMapping("/slides")
public class SlideController {

    private final SlideService slideService;

    public SlideController(SlideService slideService) {
        this.slideService = slideService;
    }

    //Obtain a slide detail with ADMIN ROLE
    @GetMapping("/{id}")
    public ResponseEntity<Object> details(@PathVariable("id") @Valid @NotNull Long id) {
        return ResponseEntity.ok(slideService.details(id));
    }

    //Obtain a list of all slide with ADMIN ROLE
    @GetMapping
    public ResponseEntity<List<SlidesBasicResponse>> getAllSlide(){
        List<SlidesBasicResponse> slidesBasicResponse = this.slideService.getAllSlides();
        return ResponseEntity.ok().body(slidesBasicResponse);
    }

    //Obtain a PageList of 10 slides with ANY ROLE
    @GetMapping("/users")
    public ResponseEntity<List<SlideResponse>> getList4Users(@PathVariable ("id") Long organizationId){
        List<SlideResponse> slideResponses = this.slideService.getList4Users(organizationId);
        return ResponseEntity.ok(slideResponses);
    }

    //Create a Slide with ADMIN ROLE
    @PostMapping
    public ResponseEntity<SlideResponse> create(@RequestBody @Valid SlideRequest slideRequest) throws IOException {
        SlideResponse saveResponse = this.slideService.create(slideRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveResponse);
    }

    //Replace entire SlideEntity with another body with ADMIN ROLE
    @PutMapping("/{id}")
    public ResponseEntity<SlideResponse> update(
            @PathVariable Long id, @RequestBody @Valid SlideRequest slideRequest) throws IOException {
        SlideResponse updatedSlide = this.slideService.update(id, slideRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSlide);
    }

    //Delete a Slide with ADMIN ROLE
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") @Valid @NotNull Long id) {
        return ResponseEntity.ok(this.slideService.delete(id));
    }
}
