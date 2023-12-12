package az.expressbank.mssecurity.data.repository;

import az.expressbank.mssecurity.data.entity.Role;
import az.expressbank.mssecurity.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<Role> findByRole(Role role);
}
