package com.cavecat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cavecat.model.Board;

/**
 * 참고 : http://netframework.tistory.com/353 <br>
 * 참고 : https://www.yammacode.com/view?id=122
 * 
 * @author sukmin
 *
 */
@Component
public class BoardDAO {

  @Autowired
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  public List<Board> selectAll() {
    Session session = sessionFactory.openSession();

    return (List<Board>) session.createCriteria(Board.class) //
        .addOrder(Order.desc("sequence")) //
        .list();
  }

  public Board select(Long sequence) {
    Session session = sessionFactory.openSession();
    return (Board) session.get(Board.class, sequence);
  }

  public Long insert(Board board) {
    Session session = sessionFactory.openSession();
    session.save(board);
    return board.getSequence();
  }

  public Board update(Board board) {
    Session session = sessionFactory.openSession();
    session.update(board);
    session.flush();
    return board;
  }

  public void delete(Board board) {
    Session session = sessionFactory.openSession();
    session.delete(board);
    session.flush();
  }
}
