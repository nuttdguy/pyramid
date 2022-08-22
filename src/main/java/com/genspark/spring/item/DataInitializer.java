package com.genspark.spring.item;

import com.genspark.spring.item.domain.Detail;
import com.genspark.spring.item.domain.Review;
import com.genspark.spring.item.utils.AppContextUtil;
import com.genspark.spring.item.utils.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {

    public static void seedTheDb() {

        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();

        for (int i = 1; i < 6; i++) {
            Session session = sessionFactory.getCurrentSession();
            Transaction tx = session.beginTransaction();

            List<Review> reviews = new ArrayList<>();
            Review review1 = AppContextUtil.getAppContext().getBean( Review.class);
            Review review2 = AppContextUtil.getAppContext().getBean( Review.class);
            review1.setRating(i);
            review2.setRating(i+0.5);
            reviews.add(review1);
            reviews.add(review2);

            Detail detail = AppContextUtil.getAppContext().getBean( Detail.class);
            detail.setDescription("description :: " + i);
            detail.setImgUrl("https://via.placeholder.com/150");

            detail.setReview(reviews);
            review1.setDetail(detail);
            review2.setDetail(detail);

            session.save(detail);
            session.save(review1);
            session.save(review2);

            tx.commit();
            session.close();

        }

    }
}
