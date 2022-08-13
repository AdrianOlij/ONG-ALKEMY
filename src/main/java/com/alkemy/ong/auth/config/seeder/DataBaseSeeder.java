package com.alkemy.ong.auth.config.seeder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

import com.alkemy.ong.models.entity.*;
import com.alkemy.ong.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.ong.auth.utility.RoleEnum;

import lombok.RequiredArgsConstructor;

@Service
@Profile("dev")
public class DataBaseSeeder {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder encoder;
    private final NewsRepository newsRepository;
    private final ActivityRepository activityRepository;
    private final MembersRepository membersRepository;
    private final CommentRepository commentRepository;

    public DataBaseSeeder(RoleRepository roleRepository, UserRepository userRepository,
                          CategoryRepository categoryRepository, PasswordEncoder encoder,
                          NewsRepository newsRepository, ActivityRepository activityRepository,
                          MembersRepository membersRepository, CommentRepository commentRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.encoder = encoder;
        this.newsRepository = newsRepository;
        this.activityRepository = activityRepository;
        this.membersRepository = membersRepository;
        this.commentRepository = commentRepository;
    }

    //Users
    private static final String PASSWORD = "12345678";
    private static final String HOST_EMAIL = "@test.com";
    private static final String firstNameUser[] = {"Patricia", "Ronald", "Carlos", "Nathaniel", "Caitlin", "Juan", "Thanos", "Will"};
    private static final String lastNameUser[] = {"Brett", "Kathleen", "Brandi", "Craig", "Katrina", "Peralta", "Smith", "Doll"};

    //Activities
    private static final String name[] = {"Apoyo Escolar para el nivel Primario", "Apoyo Escolar Nivel Secundaria", "Tutorías"};


    @EventListener
    public void seed(ContextRefreshedEvent event) throws IOException {
        if (this.roleRepository.findAll().isEmpty()) {
            createRoles();
        }
        if (this.userRepository.findAll().isEmpty()) {
            createUsers();
        }

        if (this.categoryRepository.findAll().isEmpty()) {
            createCategories();
        }

        if (this.newsRepository.findAll().isEmpty()) {
            createNews();
        }

        if (this.activityRepository.findAll().isEmpty()) {
            createActivity();
        }

        if (this.commentRepository.findAll().isEmpty()) {
            createComments();
        }

        if (this.membersRepository.findAll().isEmpty()) {
            createMembers();
        }
    }

    private void createRoles() {
        createRole(1L, RoleEnum.ADMIN);
        createRole(2L, RoleEnum.USER);
    }

    private void createUsers() {
        createUsers(RoleEnum.ADMIN);
        createUsers(RoleEnum.USER);
    }

    private void createUsers(RoleEnum applicationRole) {

        for (int index = 0; index < 8; index++) {
            this.userRepository.save(
                    UserEntity.builder()
                            .firstName(firstNameUser[index])
                            .lastName(lastNameUser[index])
                            .email(applicationRole.getSimpleRoleName().toLowerCase() + (index + 1) + HOST_EMAIL)
                            .password(encoder.encode(PASSWORD))
                            .roleId(createListRole(applicationRole))
                            .timestamp(new Timestamp(System.currentTimeMillis()))
                            .build());
        }
    }

    private Set<RoleEntity> createListRole(RoleEnum applicationRole) {
        Set<RoleEntity> roles = this.roleRepository.findByName(applicationRole.getFullRoleName());
        return roles;
    }

    private void createRole(Long id, RoleEnum applicationRole) {
        RoleEntity role = new RoleEntity();
        role.setId(id);
        role.setName(applicationRole.getFullRoleName());
        role.setDescription(applicationRole.name());
        this.roleRepository.save(role);
    }

    private void createCategories() {
        for (int i = 1; i < 25; i++) {
            this.categoryRepository.save(
               CategoryEntity.builder()
                  .name("Category " + i)
                  .description("Description " + i)
                  .image("url_image" + i)
                  .softDelete(false)
                  .build());
        }
    }

    private void createNews() {
        for (int i = 1; i < 13; i++) {
            this.newsRepository.save(NewsEntity.builder()
               .name("News " + i)
               .content("Content: " + i)
               .image("url_image " + i)
               .categoryId((long) i)
               .build());
        }
    }


    private void createActivity() {
        for (int index = 0; index < 3; index++) {
            this.activityRepository.save(
               ActivityEntity.builder()
                  .name(name[index])
                  .content("content " + (index + 1))
                  .image("image " + (index + 1))
                  .build());
        }
    }

    private void createComments() {
        for (int i = 1; i < 13; i++) {
            this.commentRepository.save(CommentEntity.builder()
               .body("body " + i)
               .newsId((long) i)
               .userId((long) i)
               .build());
        }
    }

    private void createMembers() {
        for (int i = 0; i < 20; i++) {
            this.membersRepository.save(
               MemberEntity.builder()
                  .name("Member: " + i)
                  .facebookUrl("facebook: " + i)
                  .instagramUrl("instagram: " + i)
                  .linkedinUrl("linkedIn: " + i)
                  .image("image: " + i)
                  .description("description: " + i)
                  .build()
            );
        }
    }
}

