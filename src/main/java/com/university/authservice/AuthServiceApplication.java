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

			PermissionEntity createUser = PermissionEntity.builder()
					.permissionName(PermissionEnum.CREATE_USER)
					.build();

			PermissionEntity readUser = PermissionEntity.builder()
					.permissionName(PermissionEnum.READ_USER)
					.build();

			PermissionEntity updateUser = PermissionEntity.builder()
					.permissionName(PermissionEnum.UPDATE_USER)
					.build();

			PermissionEntity deleteUser = PermissionEntity.builder()
					.permissionName(PermissionEnum.DELETE_USER)
					.build();

			PermissionEntity disableUser = PermissionEntity.builder()
					.permissionName(PermissionEnum.DISABLE_USER)
					.build();

			PermissionEntity enableUser = PermissionEntity.builder()
					.permissionName(PermissionEnum.ENABLE_USER)
					.build();

			PermissionEntity createActivity = PermissionEntity.builder()
					.permissionName(PermissionEnum.CREATE_ACTIVITY)
					.build();

			PermissionEntity readActivity = PermissionEntity.builder()
					.permissionName(PermissionEnum.READ_ACTIVITY)
					.build();

			PermissionEntity updateActivity = PermissionEntity.builder()
					.permissionName(PermissionEnum.UPDATE_ACTIVITY)
					.build();

			PermissionEntity deleteActivity = PermissionEntity.builder()
					.permissionName(PermissionEnum.DELETE_ACTIVITY)
					.build();

			PermissionEntity disableActivity = PermissionEntity.builder()
					.permissionName(PermissionEnum.DISABLE_ACTIVITY)
					.build();

			PermissionEntity enableActivity = PermissionEntity.builder()
					.permissionName(PermissionEnum.ENABLE_ACTIVITY)
					.build();

			PermissionEntity enrollActivity = PermissionEntity.builder()
					.permissionName(PermissionEnum.ENROLL_ACTIVITY)
					.build();

			PermissionEntity unenrollActivity = PermissionEntity.builder()
					.permissionName(PermissionEnum.UNENROLL_ACTIVITY)
					.build();

			// Roles

			RoleEntity adminUsers = RoleEntity.builder()
					.roleName(RoleEnum.ADMIN_USERS)
					.permissionEntities(Set.of(createUser, readUser, updateUser, deleteUser, disableUser, enableUser))
					.build();

			RoleEntity adminActivities = RoleEntity.builder()
					.roleName(RoleEnum.ADMIN_ACTIVITIES)
					.permissionEntities(Set.of(createActivity, readActivity, updateActivity, deleteActivity, disableActivity, enableActivity))
					.build();

			RoleEntity student = RoleEntity.builder()
					.roleName(RoleEnum.STUDENT)
					.permissionEntities(Set.of(readActivity, enrollActivity, unenrollActivity))
					.build();

			// Users Test

			UserEntity adminUsersTest = UserEntity.builder()
					.username("admin_users_test")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roleEntities(Set.of(adminUsers))
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.isEnabled(true)
					.build();

			UserEntity adminActivitiesTest = UserEntity.builder()
					.username("admin_activities_test")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roleEntities(Set.of(adminActivities))
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.isEnabled(true)
					.build();

			UserEntity studentTest = UserEntity.builder()
					.username("student_test")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.roleEntities(Set.of(student))
					.accountNonExpired(true)
					.accountNonLocked(true)
					.credentialsNonExpired(true)
					.isEnabled(true)
					.build();

			userRepository.saveAll(List.of(adminUsersTest, adminActivitiesTest, studentTest));
		};
	}
}
