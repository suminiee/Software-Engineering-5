package com.vocabulary.domain.word.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.dto.WordSearchCond;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

import static com.vocabulary.domain.word.domain.QWord.word;

@Repository
@Transactional
public class WordCustomRepositoryImpl implements WordCustomRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public WordCustomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void delete(Integer wordId) {
        Word word = em.find(Word.class, wordId);
        em.remove(word);
    }

    @Override
    public List<Word> findAllByCond(WordSearchCond cond) {
        String searchCond = cond.getSearchCond();
        return query
                .select(word)
                .from(word)
                .where(likeWordSpellingOrMean(searchCond))
                .fetch();
    }

    public List<Word> getRandomWords(Integer maxResultSize) {
        Query countQuery = em.createNativeQuery("select count(*) from Word");
        int number = getNumber((long)countQuery.getSingleResult());

        Query selectQuery = em.createQuery("select w from Word w")
                .setFirstResult(number)
                .setMaxResults(maxResultSize);
        return selectQuery.getResultList();
    }

    private int getNumber(long count) {
        Random random = new Random();
        int number = random.nextInt((int)count);
        return number;
    }

    private BooleanExpression likeWordSpellingOrMean(String searchCond) {
        if (StringUtils.hasText(searchCond)) {
            return word.spelling.like("%" + searchCond + "%").or(word.mean.like("%" + searchCond + "%"));
        }
        return null;
    }
}
