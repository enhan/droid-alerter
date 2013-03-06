package eu.enhan.alerter.filter;

import eu.enhan.alerter.common.Email;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Emmanuel Nhan
 */
public abstract class Filter {

    public static Builder newBuilder(){
        return new Builder();
    }

    public enum CombinationOperator{
        OR, AND
    }

    protected final Set<FilterRule> rules;
    private final ActionTemplate actionTemplate;

    protected Filter(Set<FilterRule> rules, ActionTemplate actionTemplate) {
        this.rules = rules;
        this.actionTemplate = actionTemplate;
    }

    abstract public boolean matches(Email email);

    public static class Builder{
        private CombinationOperator operator = CombinationOperator.AND;
        private Set<FilterRule> rules = new HashSet<FilterRule>();
        private ActionTemplate actionTemplate ;

        public Builder withOperator(CombinationOperator operator){
            this.operator = operator;
            return this;
        }

        public Builder withActionTemplate(ActionTemplate template){
            this.actionTemplate = template;
            return this;
        }

        public Builder withRule(FilterRule rule){
            rules.add(rule);
            return this;
        }


        public Filter build(){
            if (operator == CombinationOperator.OR){
                return new OrFilter(rules,actionTemplate);
            }else{
                return new AndFilter(rules,actionTemplate );
            }

        }

    }
}
