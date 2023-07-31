package br.com.amarques.login.repository;

import br.com.amarques.login.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
            SELECT t From Token t INNER JOIN User u\s
            ON t.user.id = u.id\s
            WHERE u.id = :id AND t.revoked = false\s
            """)
    List<Token> findAllValidTokenByUser(Long id);

    Optional<Token> findByToken(String token);
}
