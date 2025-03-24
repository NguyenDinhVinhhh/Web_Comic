package com.example.web_truyen.specification;

import com.example.web_truyen.entity.Category;
import com.example.web_truyen.form.category.CategoryFilterForm;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CategorySpectification {
    private static final String SEARCH ="SEARCH";
    public static Specification<Category> buildwhere(CategoryFilterForm form)
    {
        Specification<Category> whereSearch = new CategorySpectification.SpectificationImpl(SEARCH,form.getSearch());
        return Specification.where(whereSearch);
    }
    private static class SpectificationImpl implements Specification<Category>
    {
        private String key ;
        private Object value;

        public SpectificationImpl(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
