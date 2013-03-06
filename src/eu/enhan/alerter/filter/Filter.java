package eu.enhan.alerter.filter;

import com.google.common.collect.ImmutableSet;
import eu.enhan.alerter.action.ActionFactory;
import eu.enhan.alerter.common.Email;

import java.util.Set;

/**
 * @author Emmanuel Nhan
 */
public abstract class Filter {

    public static Builder newBuilder(){
        return new Builder();
    }

    public enum CombinationOperator{
        OR, AND, ALWAYS_TRUE
    }

    protected final Set<FilterRule> rules;
    private final ActionTemplate actionTemplate;

    protected Filter(Set<FilterRule> rules, ActionTemplate actionTemplate) {
        this.rules = rules;
        this.actionTemplate = actionTemplate;
    }

    abstract public boolean matches(Email email);

	public Set<Runnable> createActions(ActionFactory factory, Email email){
		return actionTemplate.instantiateActions(factory,email);
	}

    public static class Builder{
        private CombinationOperator operator = CombinationOperator.AND;
        private ImmutableSet.Builder<FilterRule> rules = ImmutableSet.builder();
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
	        switch (operator){
		        case ALWAYS_TRUE:
			        return new AllMatchesFilter(actionTemplate);
		        case AND:
			        return new AndFilter(rules.build(),actionTemplate);
		        case OR:
			        return new OrFilter(rules.build(), actionTemplate);
	        }

	        return null;

        }

    }
}
