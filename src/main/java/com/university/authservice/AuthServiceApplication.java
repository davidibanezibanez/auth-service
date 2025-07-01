package com.university.authservice;

import com.university.authservice.persistence.entity.*;
import com.university.authservice.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {

			// Permissions

			PermissionEntity CREATE_USER = PermissionEntity.builder()
					.permissionName(PermissionEnum.CREATE_USER)
					.build();

			PermissionEntity READ_USER = PermissionEntity.builder()
					.permissionName(PermissionEnum.READ_USER)
					.build();

			PermissionEntity UPDATE_USER = PermissionEntity.builder()
					.permissionName(PermissionEnum.UPDATE_USER)
					.build();

			PermissionEntity DELETE_USER = PermissionEntity.builder()
					.permissionName(PermissionEnum.DELETE_USER)
					.build();

			PermissionEntity DISABLE_USER = PermissionEntity.builder()
					.permissionName(PermissionEnum.DISABLE_USER)
					.build();

			PermissionEntity ENABLE_USER = PermissionEntity.builder()
					.permissionName(PermissionEnum.ENABLE_USER)
					.build();

			PermissionEntity CREATE_ACTIVITY = PermissionEntity.builder()
					.permissionName(PermissionEnum.CREATE_ACTIVITY)
					.build();

			PermissionEntity READ_ACTIVITY = PermissionEntity.builder()
					.permissionName(PermissionEnum.READ_ACTIVITY)
					.build();

			PermissionEntity UPDATE_ACTIVITY = PermissionEntity.builder()
					.permissionName(PermissionEnum.UPDATE_ACTIVITY)
					.build();

			PermissionEntity DELETE_ACTIVITY = PermissionEntity.builder()
					.permissionName(PermissionEnum.DELETE_ACTIVITY)
					.build();

			PermissionEntity DISABLE_ACTIVITY = PermissionEntity.builder()
					.permissionName(PermissionEnum.DISABLE_ACTIVITY)
					.build();

			PermissionEntity ENABLE_ACTIVITY = PermissionEntity.builder()
					.permissionName(PermissionEnum.ENABLE_ACTIVITY)
					.build();

			PermissionEntity ENROLL_ACTIVITY = PermissionEntity.builder()
					.permissionName(PermissionEnum.ENROLL_ACTIVITY)
					.build();

			PermissionEntity UNENROLL_ACTIVITY = PermissionEntity.builder()
					.permissionName(PermissionEnum.UNENROLL_ACTIVITY)
					.build();

			// Roles

			RoleEntity ADMIN_USERS = RoleEntity.builder()
					.roleName(RoleEnum.ADMIN_USERS)
					.permissionEntities(Set.of(CREATE_USER, READ_USER, UPDATE_USER, DELETE_USER, DISABLE_USER, ENABLE_USER))
					.build();

			RoleEntity ADMIN_ACTIVITIES = RoleEntity.builder()
					.roleName(RoleEnum.ADMIN_ACTIVITIES)
					.permissionEntities(Set.of(CREATE_ACTIVITY, READ_ACTIVITY, UPDATE_ACTIVITY, DELETE_ACTIVITY, DISABLE_ACTIVITY, ENABLE_ACTIVITY))
					.build();

			RoleEntity STUDENT = RoleEntity.builder()
					.roleName(RoleEnum.STUDENT)
					.permissionEntities(Set.of(READ_ACTIVITY, ENROLL_ACTIVITY, UNENROLL_ACTIVITY))
					.build();

			// Users Test

			UserEntity adminUsersTest = UserEntity.builder()
					.username("admin_users_test")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roleEntities(Set.of(ADMIN_USERS))
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.isEnabled(true)
					.build();

			UserEntity adminActivitiesTest = UserEntity.builder()
					.username("admin_activities_test")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roleEntities(Set.of(ADMIN_ACTIVITIES))
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.isEnabled(true)
					.build();

			UserEntity studentTest = UserEntity.builder()
					.username("student_test")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roleEntities(Set.of(STUDENT))
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.isEnabled(true)
					.build();

			userRepository.saveAll(List.of(adminUsersTest, adminActivitiesTest, studentTest));
		};
	}
}
