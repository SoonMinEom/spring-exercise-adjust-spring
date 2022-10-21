package com.likelion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Strategy패턴 : interface를 의존하게 해서 각 구현체(Strategy)를 선택해서 사용함
public interface StatementStrategy {
    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
