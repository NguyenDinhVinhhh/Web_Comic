package com.example.web_truyen.specification;

import com.example.web_truyen.entity.User;
import com.example.web_truyen.form.user.UserFilterForm;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    private static final String SEARCH ="SEARCH";

    public static Specification<User> buildwhere(UserFilterForm form)
    {
        Specification<User> whereSearch = new SpectificationImpl(SEARCH,form.getSearch());
        return Specification.where(whereSearch);
    }
    private static class SpectificationImpl implements Specification<User>
    {
        private String key;
        private Object value;

        public SpectificationImpl(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if(value == null)
            {
                return null;
            }
            switch (key)
            {
                case SEARCH ->
                {
                    return criteriaBuilder.like(root.get("username"),"%"+value+"%");
                }
            }
            return null;
        }
    }
}
