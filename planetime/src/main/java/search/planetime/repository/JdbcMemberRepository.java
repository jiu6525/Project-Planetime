package search.planetime.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import search.planetime.domain.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class JdbcMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        Map<String, Objects>parameters = new HashMap<>();
//        parameters.put("name", (Objects)member.getNAME());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        member.setMEMBER_ID(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(String MEMBER_ID) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String NAME) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
