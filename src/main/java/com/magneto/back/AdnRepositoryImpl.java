package com.magneto.back;

import com.magneto.dto.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class AdnRepositoryImpl implements AdnRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insertAdn(Dna dna) {


        System.out.println("Hola mundo....."+ dna);
        final String INSERT_SQL = "INSERT INTO ADN(Count_mutant_dna,Count_human_dna) values(?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, dna.getCount_mutant_dna());
                ps.setDouble(2, dna.getCount_human_dna());
                return ps;
            }
        }, holder);
    }


    @Override
    public List<Dna> statistics() {

        return jdbcTemplate.query(
                "SELECT \n" +
                        "SUM(count_mutant_dna) as count_mutant_dna, \n" +
                        "SUM(count_human_dna) as count_human_dna,\n" +
                        "SUM(count_mutant_dna) / SUM(count_human_dna) as ratio\n" +
                        "FROM magneto.ADN ",
                (rs, rowNum) ->
                        new Dna(
                                rs.getDouble("ratio"),
                                rs.getDouble("count_mutant_dna"),
                                rs.getDouble("count_human_dna")
                        )
        );
    }


}
