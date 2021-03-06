package saturn.auth.repository.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import saturn.auth.domain.Account;

@Repository
public class AccountRepository extends BaseRepository<Account> implements saturn.auth.repository.AccountRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Session openSession(){
        return sessionFactory.openSession();
    }

    public void update(Account account){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(account);
        session.getTransaction().commit();
        session.close();
    }

    public Account find(String name, String password){
        Session session = sessionFactory.openSession();
        Account result = (Account) session
                .createCriteria(Account.class)
                .add(Restrictions.eq("name", name))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        session.close();
        return result;
    }

}
