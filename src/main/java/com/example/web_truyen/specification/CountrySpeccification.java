package com.example.web_truyen.specification;

import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.country.CountryFilterForm;
import com.example.web_truyen.form.user.UserFilterForm;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CountrySpeccification {

    private static final String SEARCH ="SEARCH";
    public static Specification<Country> buildwhere(CountryFilterForm form)
    {
        Specification<Country> whereSearch = new SpectificationImpl(SEARCH,form.getSearch());
        return Specification.where(whereSearch);
    }
    private static class SpectificationImpl implements Specification<Country>
    {
        private String key ;
        private Object value;

        public SpectificationImpl(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            if(value == null)
            {
                return null;
            }
            switch (key)
            {
                case SEARCH ->
                {
                    return criteriaBuilder.like(root.get("name"),"%"+value+"%");
                }
            }
            return null;
        }
    }
}
