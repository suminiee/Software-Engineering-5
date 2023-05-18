package com.vocabulary.domain.word.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.dto.WordSearchCond;
import com.vocabulary.domain.word.dto.WordUpdateForm;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.vocabulary.domain.word.domain.QWord.word;

@Repository
@Transactional
public class JpaWordRepository implements WordRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaWordRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void save(Word word) {
        em.persist(word);
    }

    @Override
    public void update(WordUpdateForm form) {
        Word findWord = em.find(Word.class, form.getWordId());
        findWord.setSpelling(form.getSpelling());
        findWord.setMean(form.getMean());
    }

    @Override
    public void delete(Integer wordId) {
        Word word = em.find(Word.class, wordId);
        em.remove(word);
    }

    @Override
    public Optional<Word> findById(Integer wordId) {
        Word word = em.find(Word.class, wordId);
        return Optional.ofNullable(word);
    }

    @Override
    public List<Word> findAll() {
        return em.createQuery("select w from Word w", Word.class)
                .getResultList();
    }

    @Override
    public List<Word> findAll(WordSearchCond cond) {
        String searchCond = cond.getSearchCond();
        return query
                .select(word)
                .from(word)
                .where(likeWordSpellingOrMean(searchCond))
                .fetch();
    }

    private BooleanExpression likeWordSpellingOrMean(String searchCond) {
        if (StringUtils.hasText(searchCond)) {
            return word.spelling.like("%" + searchCond + "%").or(word.mean.like("%" + searchCond + "%"));
        }
        return null;
    }
}
